package com.galaxite.client.render.camadas;

import net.fabricmc.fabric.api.client.renderer.v1.Renderer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

// Importe o seu RenderState customizado (ajuste o pacote se necessário)
import com.galaxite.client.render.EstadoDoRenderDaGem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

/**
 * Camada de renderização separada para gerenciar as roupas de forma modular.
 * Na 1.21.2+, estendemos FeatureRenderer usando o GemRenderState.
 */
public class GemClothingLayer<M extends EntityModel<EstadoDoRenderDaGem>> extends FeatureRenderer<EstadoDoRenderDaGem, M> {

    public GemClothingLayer(RenderLayerParent<EstadoDoRenderDaGem, M> context) {
        super(context);
    }

    @Override
    public void render(PoseStack matrices, SubmitNodeCollector submitNodeCollector, int light, EstadoDoRenderDaGem state, float limbAngle, float limbDistance) {
        // 1. Verifica se a Gem possui alguma roupa ativa salva no State
        if (state.clothingName != null && !state.clothingName.isEmpty()) {
            
            // 2. Cria o caminho da textura usando o padrão moderno Identifier.of(...)
            Identifier texturaRoupa = Identifier.fromNamespaceAndPath(
                "seu_mod_id", // Substitua pelo ID minúsculo do seu mod
                state.clothingTextureFolder + "/" + state.clothingName + ".png"
            );

            // 3. Obtém o buffer gráfico correto para renderizar texturas de roupas/armaduras sem Cull na 1.21.2+
            // O método getArmorCutoutNoCull é perfeito para isso, pois evita artefatos visuais e renderiza o verso das malhas
              VertexConsumer vertexConsumer = vertexConsumer.getBuffer(RenderTypes.armorCutoutNoCull(texturaRoupa));

  

            // 4. Desenha a roupa usando o modelo base da Gem (Parent)
            // O valor -1 no final representa a cor branca padrão sem tonalidade (ARGB em formato int)
            this.getContextModel().renderToBuffer(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, -1);
        }
    }
}