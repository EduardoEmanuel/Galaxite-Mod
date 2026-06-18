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

        System.out.println("[GALAXITE] Processando textura dinâmica para a chave: " + chaveCache);

        Minecraft mc = Minecraft.getInstance();
        try {
            Optional<Resource> recursoBase = mc.getResourceManager().getResource(texturaBase);
            Optional<Resource> recursoPaleta = mc.getResourceManager().getResource(texturaPaleta);

            if (recursoBase.isEmpty() || recursoPaleta.isEmpty()) {
                return texturaBase; 
            }

            try (InputStream streamBase = recursoBase.get().open();
                 InputStream streamPaleta = recursoPaleta.get().open()) {

                NativeImage imgBase = NativeImage.read(streamBase);
                NativeImage imgPaleta = NativeImage.read(streamPaleta);

                int larguraBase = imgBase.getWidth();
                int alturaBase = imgBase.getHeight();

                // 1. MAPEAMENTO INTELIGENTE DA PALETA:
                // Descobre quantas cores reais (não-transparentes) existem nesta linha da paleta
                int colunasValidas = 0;
                if (linhaPaletaAlvo >= 0 && linhaPaletaAlvo < imgPaleta.getHeight()) {
                    for (int c = 0; c < imgPaleta.getWidth(); c++) {
                        int corCheck = imgPaleta.getPixel(c, linhaPaletaAlvo);
                        int alphaCheck = (corCheck >> 24) & 0xFF;
                        if (alphaCheck > 0) {
                            colunasValidas = c + 1; // Registra o índice da última cor válida encontrada
                        }
                    }
                }

                // Fallback caso a linha inteira seja transparente
                if (colunasValidas == 0) colunasValidas = imgPaleta.getWidth();

                // 2. PROCESSAMENTO DOS PIXELS DA ENTIDADE
                for (int x = 0; x < larguraBase; x++) {
                    for (int y = 0; y < alturaBase; y++) {
                        int pixelBase = imgBase.getPixel(x, y);
                        int alphaBase = (pixelBase >> 24) & 0xFF;

                        // Se o pixel da gema for visível, nós colorimos
                        if (alphaBase > 0) {
                            int tomDeCinza = pixelBase & 0xFF; // Canal Red serve como nível de brilho (0-255)

                            // Converte a escala de cinza (0-255) proporcionalmente para o número de cores da paleta
                            int colunaX = (tomDeCinza * (colunasValidas - 1)) / 255;

                            // Garante segurança estrita de limites
                            if (colunaX < 0) colunaX = 0;
                            if (colunaX >= imgPaleta.getWidth()) colunaX = imgPaleta.getWidth() - 1;

                            if (linhaPaletaAlvo >= 0 && linhaPaletaAlvo < imgPaleta.getHeight()) {
                                int corPaleta = imgPaleta.getPixel(colunaX, linhaPaletaAlvo);
                                int alphaPaleta = (corPaleta >> 24) & 0xFF;

                                // SÓ APLICA A COR SE O PIXEL DA PALETA NÃO FOR TRANSPARENTE
                                if (alphaPaleta > 0) {
                                    int redPaleta = corPaleta & 0xFF;
                                    int greenPaleta = (corPaleta >> 8) & 0xFF;
                                    int bluePaleta = (corPaleta >> 16) & 0xFF;

                                    // Reconstrói o pixel mantendo o formato ABGR nativo do Minecraft
                                    int pixelColorido = (alphaBase << 24) | (bluePaleta << 16) | (greenPaleta << 8) | redPaleta;
                                    imgBase.setPixel(x, y, pixelColorido);
                                }
                                // Se a paleta for transparente aqui, o pixel mantém o tom original (não fica branco!)
                            }
                        }
                    }
                }

                DynamicTexture texturaDinamica = new DynamicTexture(() -> "dinamico_" + chaveCache.toLowerCase(), imgBase);
                texturaDinamica.upload();
                imgPaleta.close();

                Identifier idDinamico = Identifier.fromNamespaceAndPath("galaxite", "dinamico/" + chaveCache.toLowerCase());
                mc.getTextureManager().register(idDinamico, texturaDinamica);
                
                CACHE_TEXTURAS.put(chaveCache, idDinamico);
                return idDinamico;
            }

        } catch (Exception e) {
            System.err.println("[GALAXITE ERRO] Falha crítica ao colorir os pixels do modelo:");
            e.printStackTrace(); 
            return texturaBase;
        }
    }
}