package com.galaxite.client.render.camadas;

import com.galaxite.client.render.EstadoDoRenderDaGem;
import com.galaxite.entidades.BaseDasGems;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class CamadaDePele<M extends EntityModel<EstadoDoRenderDaGem>> extends RenderLayer<EstadoDoRenderDaGem, M> {

    public CamadaDePele(RenderLayerParent<EstadoDoRenderDaGem, M> parent) {
        super(parent);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords,
                       EstadoDoRenderDaGem state, float yRot, float xRot) {
           
        if (state != null && state.paletteName != null && state.skinName != null) {
            
            // 1. Corrige o caminho da paleta para garantir o formato "textures/" do Minecraft
            String pastaPaleta = state.paletteTextureFolder.replace("texturas/", "textures/");
            Identifier texturaPaleta = Identifier.fromNamespaceAndPath(
                "galaxite",
                pastaPaleta + state.paletteName + ".png"
            );
            
            // 2. Garante o formato correto para a pasta da pele (Ex: "textures/entidades/safira/peles/")
            String pastaPele = state.skinTextureFolder.replace("texturas/", "textures/");
            
            // 3. Definição das partes da pele:
            // Se o modelo da sua Gem usar apenas um arquivo de pele completo, deixe apenas {"corpo"}.
            // Se a pele for dividida (Ex: texturas separadas para cabeça e corpo), basta adicionar ao array abaixo!
            String[] partesPele = {"corpo"};
            
            // 4. Obtém automaticamente o índice 1 (SKIN) definido no Enum da BaseDasGems
            int linhaPaletaAlvo = BaseDasGems.PalettePart.SKIN.getRowIndex(); // Retorna 1
            
            // 5. Loop dinâmico para processar e renderizar cada parte do corpo da Gem
            for (String parte : partesPele) {
                
                // Monta o caminho da textura base em tons de cinza
                // Exemplo: "textures/entidades/safira/peles/default/default_corpo.png"
                Identifier texturaBasePele = Identifier.fromNamespaceAndPath(
                    "galaxite",
                    pastaPele + state.skinName + "/" + state.skinName + "_" + parte + ".png"
                );
                
                // Cria uma chave única de cache combinando a variante da pele, a parte e a cor da paleta
                String chaveCache = "pele_" + state.skinName + "_" + parte + "_" + state.paletteName;
                
                // Solicita a fusão da textura da pele com a linha 1 da paleta atual
                Identifier texturaPeleColorida = GerenciadorDePaletas.obterOuCriarTexturaColorida(
                    texturaBasePele,
                    texturaPaleta,
                    linhaPaletaAlvo,
                    chaveCache
                );
                
                // Configura o tipo de renderização translúcido/recortado padrão para entidades
                RenderType tipoDeRender = RenderTypes.entityCutout(texturaPeleColorida);
                
                // Envia a parte da pele colorida para o pipeline de renderização do Minecraft
                submitNodeCollector.submitModel(
                    this.getParentModel(), 
                    state,                 
                    poseStack,             
                    tipoDeRender,          
                    lightCoords,           
                    OverlayTexture.NO_OVERLAY, 
                    0,                         
                    null                       
                );
            }
        }
    }
}
