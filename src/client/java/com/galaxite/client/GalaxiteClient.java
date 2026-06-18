package com.galaxite.client;

import com.galaxite.client.render.ModModelLayers;
import com.galaxite.client.render.entidades.SafiraRender;
import com.galaxite.entidades.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class GalaxiteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
       // 1. REGISTRE A GEOMETRIA DO MODELO (O que estava faltando!)
        // Isso ensina ao Minecraft o que "galaxite:safira#main" realmente significa em formato 3D.
        ModModelLayers.registerModelLayers();

        // 2. O seu registro do renderizador que você já deve ter feito:
        EntityRenderers.register(ModEntities.SAFIRA, SafiraRender::new);
    }
}