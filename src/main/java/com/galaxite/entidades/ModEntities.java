package com.galaxite.entidades;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries; // IMPORTANTE: Novo import
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey; // IMPORTANTE: Novo import
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public class ModEntities {

    // 1. Criamos a ResourceKey oficial para a Safira
    public static final ResourceKey<EntityType<?>> SAFIRA_KEY = ResourceKey.create(
        Registries.ENTITY_TYPE,
        Identifier.fromNamespaceAndPath("galaxite", "safira")
    );

    // 2. Registramos usando a chave criada
    public static final EntityType<Safira> SAFIRA = Registry.register(
        BuiltInRegistries.ENTITY_TYPE,
        SAFIRA_KEY, // Passamos a chave diretamente aqui também
        EntityType.Builder.of(Safira::new, MobCategory.CREATURE)
            .sized(0.6f, 1.8f)
            .build(SAFIRA_KEY) // <--- CORREÇÃO: Agora passamos a ResourceKey em vez de String
    );

    public static void inicializar() {
        FabricDefaultAttributeRegistry.register(SAFIRA, Safira.createMobAttributes());
    }
}