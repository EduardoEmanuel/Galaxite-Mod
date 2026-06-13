package com.galaxite.client.render;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import java.io.InputStream;
import java.util.Optional;

public class AuxiliarDePaletas {

    /**
     * Extrai a cor da linha GEM_COLOR (Linha index 4) de um arquivo de paleta física.
     */
    public static int extrairCorDaLinhaGem(String pasta, String nomePaleta) {
        try {
            // Garante o formato correto de carregamento de assets interno do Minecraft
            String pastaFormatada = pasta.replace("texturas/", "textures/");
            Identifier idDaPaleta = Identifier.fromNamespaceAndPath("galaxite", pastaFormatada + nomePaleta + ".png");

            Optional<net.minecraft.server.packs.resources.Resource> recurso = 
                Minecraft.getInstance().getResourceManager().getResource(idDaPaleta);

            if (recurso.isPresent()) {
                try (InputStream stream = recurso.get().open()) {
                    NativeImage imagem = NativeImage.read(stream);

                    int linhaGemColor = 4; // Equivalente ao PalettePart.GEM_COLOR
                    int colunaTom = 4;     // Pegamos um tom médio central da paleta para o item

                    int x = Math.min(colunaTom, imagem.getWidth() - 1);
                    int y = Math.min(linhaGemColor, imagem.getHeight() - 1);

                    // Pega o pixel usando o método correto da 1.21.4
                    int pixelColor = imagem.getPixel(x, y);

                    // Converte o formato interno ABGR invertido do NativeImage para o ARGB do Minecraft
                    int alpha = (pixelColor >> 24) & 0xFF;
                    int blue  = (pixelColor >> 16) & 0xFF;
                    int green = (pixelColor >> 8) & 0xFF;
                    int red   = pixelColor & 0xFF;

                    imagem.close();
                    return (alpha << 24) | (red << 16) | (green << 8) | blue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0xFFFFFF; // Branco de segurança caso falhe
    }
}