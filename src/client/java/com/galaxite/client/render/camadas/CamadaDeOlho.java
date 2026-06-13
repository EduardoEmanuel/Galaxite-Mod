package com.galaxite.client.render.camadas;

import com.galaxite.client.render.EstadoDoRenderDaGem;
import com.galaxite.entidades.BaseDasGems;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class CamadaDeOlho<M extends EntityModel<EstadoDoRenderDaGem>> extends RenderLayer<EstadoDoRenderDaGem, M> {

    public CamadaDeOlho(RenderLayerParent<EstadoDoRenderDaGem, M> parent) {
        super(parent);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords,
                       EstadoDoRenderDaGem state, float yRot, float xRot) {
           
        if (state != null && state.paletteName != null && state.eyeVariantName != null) {
            
            // 1. Caminho da paleta corrigido
            String pastaPaleta = state.paletteTextureFolder.replace("texturas/", "textures/");
            Identifier texturaPaleta = Identifier.fromNamespaceAndPath(
                "galaxite",
                pastaPaleta + state.paletteName + ".png"
            );
            
            // 2. Caminho da pasta de olhos corrigido
            String pastaOlhos = state.eyeVariantTextureFolder.replace("texturas/", "textures/");

            // ==========================================
            // PASSO A: RENDERIZAR A ESCLERA (LINHA 3)
            // ==========================================
            int linhaEsclera = BaseDasGems.PalettePart.SCLERA.getRowIndex(); // Retorna 3
            
            // Busca o arquivo. Ex: "textures/entidades/safira/olhos/default/default_esclera.png"
            Identifier texturaBaseEsclera = Identifier.fromNamespaceAndPath(
                "galaxite",
                pastaOlhos + state.eyeVariantName + "/" + state.eyeVariantName + "_esclera.png"
            );
            
            String chaveCacheEsclera = "esclera_" + state.eyeVariantName + "_" + state.paletteName;
            
            Identifier texturaEscleraColorida = GerenciadorDePaletas.obterOuCriarTexturaColorida(
                texturaBaseEsclera,
                texturaPaleta,
                linhaEsclera,
                chaveCacheEsclera
            );
            
            submitNodeCollector.submitModel(
                this.getParentModel(), state, poseStack, 
                RenderTypes.entityCutout(texturaEscleraColorida), 
                lightCoords, OverlayTexture.NO_OVERLAY, 0, null
            );

        }
    }
}