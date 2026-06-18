package com.galaxite.itens;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModCreativeTabs {

    // 1. ABA DAS GEMS
    public static final CreativeModeTab GEMS_TAB = FabricCreativeModeTab.builder()
        .icon(() -> new ItemStack(ModItems.PEDRA_SAFIRA_AZUL)) // Ícone da aba
        .title(Component.translatable("itemGroup.galaxite.gems")) // Nome traduzível
        .displayItems((parameters, output) -> {
            // Aqui você joga os itens que pertencem a essa aba
            output.accept(ModItems.PEDRA_SAFIRA_AZUL);
            output.accept(ModItems.PEDRA_SAFIRA_ROSA);
            output.accept(ModItems.PEDRA_SAFIRA_AMARELA);
            output.accept(ModItems.PEDRA_SAFIRA_VERDE);
        })
        .build();

    // 2. ABA DOS BLOCOS
    public static final CreativeModeTab BLOCKS_TAB = FabricCreativeModeTab.builder()
        .icon(() -> new ItemStack(Items.AMETHYST_BLOCK)) // Use um bloco seu futuramente como ícone
        .title(Component.translatable("itemGroup.galaxite.blocks"))
        .displayItems((parameters, output) -> {
            // Exemplo de como adicionar itens (quando você tiver blocos, coloque-os aqui):
            // output.accept(ModBlocks.MEU_BLOCO_CUSTOMIZADO);
        })
        .build();

    // 3. ABA DOS MOBS (Criaturas não-Gems e Spawn Eggs)
    public static final CreativeModeTab MOBS_TAB = FabricCreativeModeTab.builder()
        .icon(() -> new ItemStack(Items.CHICKEN_SPAWN_EGG)) // Use um spawn egg seu como ícone depois
        .title(Component.translatable("itemGroup.galaxite.mobs"))
        .displayItems((parameters, output) -> {
            // Aqui você colocará os itens de invocação (Spawn Eggs) das suas criaturas
            // output.accept(ModItems.SAFIRA_SPAWN_EGG);
        })
        .build();

    // 4. ABA DE UTILITÁRIOS / ESPECIAIS (Modificadores, ferramentas de teste)
    public static final CreativeModeTab SPECIAL_TAB = FabricCreativeModeTab.builder()
        .icon(() -> new ItemStack(Items.COMMAND_BLOCK)) // Ícone temporário
        .title(Component.translatable("itemGroup.galaxite.special"))
        .displayItems((parameters, output) -> {
            // Aqui entram os modificadores de gems, ferramentas do criativo, etc.
            // output.accept(ModItems.MODIFICADOR_DE_GEMS);
        })
        .build();

    // Método para registrar todas as abas de uma vez no jogo
    public static void registrarTabs() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, 
                Identifier.fromNamespaceAndPath("galaxite", "gems_tab"), GEMS_TAB);
                
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, 
                Identifier.fromNamespaceAndPath("galaxite", "blocks_tab"), BLOCKS_TAB);
                
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, 
                Identifier.fromNamespaceAndPath("galaxite", "mobs_tab"), MOBS_TAB);
                
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, 
                Identifier.fromNamespaceAndPath("galaxite", "special_tab"), SPECIAL_TAB);
    }
}