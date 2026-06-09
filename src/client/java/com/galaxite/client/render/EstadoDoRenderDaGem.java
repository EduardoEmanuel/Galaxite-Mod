package com.galaxite.client.render;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import com.galaxite.entidades.BaseDasGems.GemPlacement;

public class EstadoDoRenderDaGem extends LivingEntityRenderState {
    public GemPlacement gemPlacement;
    public String clothingName;
    public String paletteName;
    public String clothingTextureFolder;
    public String paletteTextureFolder;

    // VARIÁVEIS PRÓPRIAS: Garantem compatibilidade universal de rotação
    public float netHeadYaw;
    public float headPitch;
}
