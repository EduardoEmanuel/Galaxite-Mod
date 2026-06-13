package com.galaxite.client.render.camadas;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.Resource;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GerenciadorDePaletas {
    
    private static final Map<String, Identifier> CACHE_TEXTURAS = new HashMap<>();

    public static Identifier obterOuCriarTexturaColorida(Identifier texturaBase, Identifier texturaPaleta, int linhaPaletaAlvo, String chaveCache) {
        if (CACHE_TEXTURAS.containsKey(chaveCache)) {
            return CACHE_TEXTURAS.get(chaveCache);
        }

        Minecraft mc = Minecraft.getInstance();
        try {
            Optional<Resource> recursoBase = mc.getResourceManager().getResource(texturaBase);
            Optional<Resource> recursoPaleta = mc.getResourceManager().getResource(texturaPaleta);

            if (recursoBase.isEmpty() || recursoPaleta.isEmpty()) {
                return texturaBase; // Fallback de segurança
            }

            try (InputStream streamBase = recursoBase.get().open();
                 InputStream streamPaleta = recursoPaleta.get().open()) {

                NativeImage imgBase = NativeImage.read(streamBase);
                NativeImage imgPaleta = NativeImage.read(streamPaleta);

                for (int x = 0; x < imgBase.getWidth(); x++) {
                    for (int y = 0; y < imgBase.getHeight(); y++) {
                        // Método público e correto na 1.21.4: getPixel(x, y)
                        int pixelBase = imgBase.getPixel(x, y);

                        // Extração do canal Alpha usando Bitwise pura (Formato interno: ABGR)
                        int alphaBase = (pixelBase >> 24) & 0xFF;
                        if (alphaBase == 0) continue;

                        // O tom de cinza (Canal Red) dita a coluna X na paleta.
                        // No formato ABGR do NativeImage, o Red fica nos primeiros 8 bits.
                        int colunaX = pixelBase & 0xFF;

                        if (colunaX < imgPaleta.getWidth() && linhaPaletaAlvo < imgPaleta.getHeight()) {
                            int corPaleta = imgPaleta.getPixel(colunaX, linhaPaletaAlvo);

                            // Separa os canais de cores da paleta (também em formato ABGR)
                            int redPaleta = corPaleta & 0xFF;
                            int greenPaleta = (corPaleta >> 8) & 0xFF;
                            int bluePaleta = (corPaleta >> 16) & 0xFF;

                            // Recombina tudo de volta mantendo o Alpha original da gema
                            int pixelColorido = (alphaBase << 24) | (bluePaleta << 16) | (greenPaleta << 8) | redPaleta;

                            // Método público e correto na 1.21.4: setPixel(x, y, cor)
                            imgBase.setPixel(x, y, pixelColorido);
                        }
                    }
                }

                DynamicTexture texturaDinamica = new DynamicTexture(null, imgBase);
                Identifier idDinamico = Identifier.fromNamespaceAndPath("galaxite", "dinamico/" + chaveCache.toLowerCase());
                
                mc.getTextureManager().register(idDinamico, texturaDinamica);
                CACHE_TEXTURAS.put(chaveCache, idDinamico);
                return idDinamico;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return texturaBase;
        }
    }

    public static void limparCache() {
        CACHE_TEXTURAS.clear();
    }
}