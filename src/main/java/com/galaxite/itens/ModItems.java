package com.galaxite.itens;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

public class ModItems {

    // Agora todas as suas gemas são instâncias de ItemBaseGem.
    // Elas herdam automaticamente a interface GemEstouravel e a lógica de salvar dados.
    public static final ItemPedraGem PEDRA_SAFIRA_AZUL = registrarGem("safira_azul");
    public static final ItemPedraGem PEDRA_SAFIRA_ROSA = registrarGem("safira_rosa");
    public static final ItemPedraGem PEDRA_SAFIRA_AMARELA = registrarGem("safira_amarela");
    public static final ItemPedraGem PEDRA_SAFIRA_VERDE = registrarGem("safira_verde");

    // Método privado que faz o registro unificado
    private static ItemPedraGem registrarGem(String nome) {
        return Registry.register(
            BuiltInRegistries.ITEM, 
            Identifier.fromNamespaceAndPath("galaxite", nome), 
            new ItemPedraGem(new Item.Properties())
        );
    }

    public static void inicializar() {
        // Método opcional para inicialização, se necessário no futuro
    }
}