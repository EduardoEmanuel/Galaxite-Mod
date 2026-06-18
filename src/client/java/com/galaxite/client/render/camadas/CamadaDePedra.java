package com.galaxite.client.render.camadas;

import com.galaxite.client.render.EstadoDoRenderDaGem;
import com.galaxite.entidades.BaseDasGems;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class CamadaDePedra<M extends EntityModel<EstadoDoRenderDaGem>> extends RenderLayer<EstadoDoRenderDaGem, M> {

    public CamadaDePedra(RenderLayerParent<EstadoDoRenderDaGem, M> parent) {
        super(parent);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords,
                       EstadoDoRenderDaGem state, float yRot, float xRot) {
           
        if (state != null && state.paletteName != null) {
            String pastaPaleta = state.paletteTextureFolder.replace("texturas/", "textures/");
            Identifier texturaPaleta = Identifier.fromNamespaceAndPath("galaxite", pastaPaleta + state.paletteName + ".png");
            
            // Corrige para apontar na raiz da entidade (ex: textures/entidades/safira/pedra.png)
            String pastaRaiz = pastaPaleta.replace("paletas/", "");
            Identifier texturaBasePedra = Identifier.fromNamespaceAndPath("galaxite", pastaRaiz + "pedra.png");
            
            int linhaPaletaAlvo = BaseDasGems.PalettePart.GEM_COLOR.getRowIndex();
            String chaveCache = "pedra_gem_cor_" + state.paletteName;
            
            Identifier texturaPedraColorida = GerenciadorDePaletas.obterOuCriarTexturaColorida(texturaBasePedra, texturaPaleta, linhaPaletaAlvo, chaveCache);
            
            submitNodeCollector.submitModel(this.getParentModel(), state, poseStack, RenderTypes.entityCutout(texturaPedraColorida), lightCoords, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF, null);
        }
    }
}