package com.galaxite.client.render.camadas;

import com.galaxite.client.render.EstadoDoRenderDaGem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.Identifier;

public class CamadaDeRoupa<M extends EntityModel<EstadoDoRenderDaGem>> extends RenderLayer<EstadoDoRenderDaGem, M> {

    public CamadaDeRoupa(RenderLayerParent<EstadoDoRenderDaGem, M> parent) { super(parent); }
    
    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, EstadoDoRenderDaGem state, float yRot, float xRot) {
        if (state != null && state.clothingName != null && state.paletteName != null && state.clothingParts != null) {
            
            String pastaPaleta = state.paletteTextureFolder.replace("texturas/", "textures/");
            Identifier texturaPaleta = Identifier.fromNamespaceAndPath("galaxite", pastaPaleta + state.paletteName + ".png");
            
            String pastaRoupa = state.clothingTextureFolder.replace("texturas/", "textures/");
            if (!pastaRoupa.endsWith("/")) pastaRoupa += "/";
            
            for (String partName : state.clothingParts) {
                String caminho = pastaRoupa + state.clothingName + "/" + state.clothingName + "_" + partName + ".png";
                Identifier texturaBase = Identifier.fromNamespaceAndPath("galaxite", caminho);
                
                int linha = state.partRowMapper.apply(partName);
                String chave = "roupa_" + state.clothingName + "_" + partName + "_" + state.paletteName + "_r" + linha;
                
                Identifier texturaColorida = GerenciadorDePaletas.obterOuCriarTexturaColorida(texturaBase, texturaPaleta, linha, chave);
                submitNodeCollector.submitModel(this.getParentModel(), state, poseStack, RenderTypes.entityCutout(texturaColorida), lightCoords, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF, null);
            }
        }
    }
}