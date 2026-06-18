package com.galaxite;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.galaxite.entidades.ModEntities;
import com.galaxite.itens.ModCreativeTabs;
import com.galaxite.itens.ModItems;

public class Galaxite implements ModInitializer {
	public static final String MOD_ID = "galaxite";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
	// 1. Registra os seus itens estáticos primeiro
        ModItems.inicializar();
        // 2. Registra as suas entidades e seus respectivos atributos de vida/armadura
        ModEntities.inicializar();
		ModCreativeTabs.registrarTabs();

		LOGGER.info("Hello Fabric world!");
	}
}