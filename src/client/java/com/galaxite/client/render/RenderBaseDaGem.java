package com.galaxite.client.render;

import org.jspecify.annotations.NonNull;

import com.galaxite.entidades.BaseDasGems;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.Identifier;
import com.mojang.blaze3d.vertex.PoseStack;

public abstract class RenderBaseDaGem<T extends BaseDasGems, M extends EntityModel<EstadoDoRenderDaGem>> extends MobRenderer<T, EstadoDoRenderDaGem, M> {

    public RenderBaseDaGem(EntityRendererProvider.Context context, M model, float shadowRadius) {
        super(context, model, shadowRadius);
        this.addLayer(new GemClothingLayer<>(this));
    }

    @Override
    public EstadoDoRenderDaGem createRenderState() {
        return new EstadoDoRenderDaGem();
    }

  @Override
    public void extractRenderState(T entity, @NonNull EstadoDoRenderDaGem state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        
        // Seus registros dinâmicos existentes...
        state.gemPlacement = entity.getGemPlacement();
        state.clothingName = entity.getClothingName();
        state.paletteName = entity.getPaletteName();
        state.clothingTextureFolder = entity.getClothingTextureFolder();
        state.paletteTextureFolder = entity.getPaletteTextureFolder();

        // MATEMÁTICA OFICIAL VANILLA DE INTERPOLAÇÃO DO OLHAR (BÍPEDE)
        // Calcula a diferença entre para onde o corpo está virado e para onde a cabeça está olhando
        float bodyYaw = net.minecraft.util.Mth.rotLerp(partialTicks, entity.yBodyRotO, entity.yBodyRot);
        float headYaw = net.minecraft.util.Mth.rotLerp(partialTicks, entity.yHeadRotO, entity.yHeadRot);
        
        state.netHeadYaw = headYaw - bodyYaw; // Ângulo horizontal (olhar para os lados)
        state.headPitch = net.minecraft.util.Mth.lerp(partialTicks, entity.xRotO, entity.getXRot()); // Ângulo vertical (olhar para cima/baixo)
    }

    @Override
    public Identifier getTextureLocation(EstadoDoRenderDaGem state) {
        return Identifier.fromNamespaceAndPath("galaxite", state.paletteTextureFolder + state.paletteName + ".png");
    }

    protected abstract String[] getClothingParts();

    // --- CAMADA INTERNA DO NOVO SISTEMA DE ROUPAS ---
    private static class GemClothingLayer<T extends BaseDasGems, M extends EntityModel<EstadoDoRenderDaGem>> extends RenderLayer<EstadoDoRenderDaGem, M> {

        public GemClothingLayer(RenderBaseDaGem<T, M> renderer) {
            super(renderer);
        }

        @Override
        public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, EstadoDoRenderDaGem state, float limbSwing, float limbSwingAmount) {
            RenderBaseDaGem<T, M> renderer = (RenderBaseDaGem<T, M>) this.getParentRenderer();
            String[] parts = renderer.getClothingParts();

            if (parts == null || state.isInvisible) return;

            for (String part : parts) {
                String fullPath = state.clothingTextureFolder + state.clothingName + "/" + state.clothingName + "_" + part + ".png";
                Identifier texture = Identifier.fromNamespaceAndPath("galaxite", fullPath);
                
                renderColoredCutoutModel(this.getParentModel(), texture, poseStack, buffer, packedLight, state, -1);
            }
        }
    }
}