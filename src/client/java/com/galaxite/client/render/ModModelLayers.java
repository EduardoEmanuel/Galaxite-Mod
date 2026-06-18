package com.galaxite.client.render;

import com.galaxite.Galaxite;
import com.galaxite.client.modelos.ModeloSafira2;

import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

// #region model_layer
public class ModModelLayers {
	public static final ModelLayerLocation SAFIRA = createMain("safira");

	private static ModelLayerLocation createMain(String name) {
		return new ModelLayerLocation(Identifier.fromNamespaceAndPath(Galaxite.MOD_ID, name), "main");
	}

	public static void registerModelLayers() {
		ModelLayerRegistry.registerModelLayer(ModModelLayers.SAFIRA, ModeloSafira2::getTexturedModelData);
	}
}

    // No futuro, quando adicionar mais Gems, basta registá-las aqui da mesma forma:
    // public static final EntityModelLayer RUBI = new EntityModelLayer(Identifier.fromNamespaceAndPath("galaxite", "rubi"), "main");
    // public static final EntityModelLayer AMETISTA = new EntityModelLayer(Identifier.fromNamespaceAndPath("galaxite", "ametista"), "main");