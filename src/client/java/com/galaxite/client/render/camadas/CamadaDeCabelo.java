package com.galaxite.client.render.camadas;

import com.galaxite.client.render.EstadoDoRenderDaGem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class CamadaDeCabelo<M extends EntityModel<EstadoDoRenderDaGem>> extends RenderLayer<EstadoDoRenderDaGem, M> {

    public CamadaDeCabelo(RenderLayerParent<EstadoDoRenderDaGem, M> parent) {
        super(parent);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords,
                       EstadoDoRenderDaGem state, float yRot, float xRot) {
           
        if (state != null && state.paletteName != null && state.hairstyleName != null && state.hairParts != null) {
            
            // 1. Corrige o caminho da paleta para garantir o formato "textures/"
            String pastaPaleta = state.paletteTextureFolder.replace("texturas/", "textures/");
            Identifier texturaPaleta = Identifier.fromNamespaceAndPath(
                "galaxite",
                pastaPaleta + state.paletteName + ".png"
            );
            
            // 2. Garante o formato correto para a pasta de cabelos
            String pastaCabelo = state.hairstyleTextureFolder.replace("texturas/", "textures/");
            
            // 3. Loop dinâmico por todas as camadas de cabelo permitidas por esta Gem (Suporta até 4)
            for (int i = 0; i < state.hairParts.length; i++) {
                String parte = state.hairParts[i];
                
                // Obtém a linha correspondente do array de linhas extraído da entidade
                int linhaPaletaAlvo = (state.hairPartRows != null && i < state.hairPartRows.length) ? state.hairPartRows[i] : 0;
                
                // Monta o caminho dinâmico do arquivo da mecha em tons de cinza
                // Exemplo comum: "textures/entidades/safira/cabelos/default/default_cabelo.png"
                // Exemplo especial: "textures/entidades/quartzo/cabelos/nobre/nobre_base.png"
                Identifier texturaBaseCabelo = Identifier.fromNamespaceAndPath(
                    "galaxite",
                    pastaCabelo + state.hairstyleName + "/" + state.hairstyleName + "_" + parte + ".png"
                );
                
                // Chave de cache ultra precisa: evita misturar o processamento gráfico de mechas diferentes
                String chaveCache = "cabelo_" + state.hairstyleName + "_" + parte + "_" + state.paletteName;
                
                // Solicita a fusão da textura da mecha com a linha de cor selecionada da paleta
                Identifier texturaCabeloColorida = GerenciadorDePaletas.obterOuCriarTexturaColorida(
                    texturaBaseCabelo,
                    texturaPaleta,
                    linhaPaletaAlvo,
                    chaveCache
                );
                
                RenderType tipoDeRender = RenderTypes.entityCutout(texturaCabeloColorida);
                
                // Envia a camada atual do cabelo para o pipeline do Minecraft
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