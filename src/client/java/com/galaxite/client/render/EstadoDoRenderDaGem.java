package com.galaxite.client.render;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

import com.galaxite.entidades.BaseDasGems.GemPlacement;

public class EstadoDoRenderDaGem extends LivingEntityRenderState {
    public GemPlacement gemPlacement;
    public String clothingName;
    public String hairstyleName;
    public String skinName;
    public String eyeVariantName;
    public String paletteName;

    public String clothingTextureFolder;
    public String hairstyleTextureFolder;
    public String eyeVariantTextureFolder;
    public String skinTextureFolder;
    public String paletteTextureFolder;
    public String gemTextureFolder;

    public String[] clothingParts;
    public java.util.function.Function<String, Integer> partRowMapper;
    
    public String[] hairParts;
    public int[] hairPartRows;
    
    // VARIÁVEIS PRÓPRIAS: Garantem compatibilidade universal de rotação
    public float netHeadYaw;
    public float headPitch;




}
