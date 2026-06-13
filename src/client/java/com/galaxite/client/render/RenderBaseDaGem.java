package com.galaxite.client.render;

import org.jspecify.annotations.NonNull;

import com.galaxite.client.render.camadas.CamadaDeCabelo;
import com.galaxite.client.render.camadas.CamadaDeOlho;
import com.galaxite.client.render.camadas.CamadaDePedra;
import com.galaxite.client.render.camadas.CamadaDePele;
import com.galaxite.client.render.camadas.CamadaDeRoupa;

import com.galaxite.entidades.BaseDasGems;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public abstract class RenderBaseDaGem<T extends BaseDasGems, M extends EntityModel<EstadoDoRenderDaGem>> extends MobRenderer<T, EstadoDoRenderDaGem, M> {

    public RenderBaseDaGem(EntityRendererProvider.Context context, M model, float shadowRadius) {
        super(context, model, shadowRadius);
        this.addLayer(new CamadaDeCabelo<>(this));
        this.addLayer(new CamadaDeRoupa<>(this));
        this.addLayer(new CamadaDePele<>(this));
        this.addLayer(new CamadaDeOlho<>(this));
        this.addLayer(new CamadaDePedra<>(this));
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
        state.hairstyleName = entity.getHairstyleName();
        state.eyeVariantName = entity.getEyeVariant();
        state.skinName = entity.getSkinName();
        state.paletteName = entity.getPaletteName();
        state.clothingTextureFolder = entity.getClothingTextureFolder();
        state.hairstyleTextureFolder = entity.getHairstyleTextureFolder();
        state.skinTextureFolder = entity.getSkinTextureFolder();
        state.eyeVariantTextureFolder = entity.getEyeVariantFolder();
        state.paletteTextureFolder = entity.getPaletteTextureFolder();

        // NOVOS REGISTROS DAS PARTES DINÂMICAS:
        state.clothingParts = this.getClothingParts(); 
        state.partRowMapper = entity::getClothingPartPaletteRow; // Passa a função de mapeamento de linhas

        // REGISTRO DINÂMICO DO CABELO MULTICAMADAS
        state.hairParts = entity.getHairParts();
        if (state.hairParts != null) {
            state.hairPartRows = new int[state.hairParts.length];
            for (int i = 0; i < state.hairParts.length; i++) {
                state.hairPartRows[i] = entity.getHairPartPaletteRow(state.hairParts[i]);
            }
        }

        // MATEMÁTICA OFICIAL VANILLA DE INTERPOLAÇÃO DO OLHAR (BÍPEDE)
        // Calcula a diferença entre para onde o corpo está virado e para onde a cabeça está olhando
        float bodyYaw = net.minecraft.util.Mth.rotLerp(partialTicks, entity.yBodyRotO, entity.yBodyRot);
        float headYaw = net.minecraft.util.Mth.rotLerp(partialTicks, entity.yHeadRotO, entity.yHeadRot);
        
        state.netHeadYaw = headYaw - bodyYaw; // Ângulo horizontal (olhar para os lados)
        state.headPitch = net.minecraft.util.Mth.lerp(partialTicks, entity.xRotO, entity.getXRot()); // Ângulo vertical (olhar para cima/baixo)
    }

    public Identifier getTextureLocationPalette(EstadoDoRenderDaGem state) {
        return Identifier.fromNamespaceAndPath("galaxite", state.paletteTextureFolder + state.paletteName + ".png");
    }

    public Identifier getTextureLocationClothing(EstadoDoRenderDaGem state) {
        return Identifier.fromNamespaceAndPath("galaxite", state.clothingTextureFolder + state.clothingName + ".png");
        
    }

    public Identifier getTextureLocationHairstyles(EstadoDoRenderDaGem state) {
        return Identifier.fromNamespaceAndPath("galaxite", state.hairstyleTextureFolder + state.hairstyleName + ".png");
        
    }

    public Identifier getTextureLocationSkins(EstadoDoRenderDaGem state) {
        return Identifier.fromNamespaceAndPath("galaxite", state.skinTextureFolder + state.skinName + ".png");
        
    }

    public Identifier getTextureLocationEyeVariants(EstadoDoRenderDaGem state) {
        return Identifier.fromNamespaceAndPath("galaxite", state.eyeVariantTextureFolder + state.eyeVariantName + ".png");
        
    }

    protected abstract String[] getClothingParts();
        
}
