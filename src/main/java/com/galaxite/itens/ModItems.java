package com.galaxite.itens;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class ModItems {

    // Suas gemas declaradas explicitamente como ItemPedraGem
    public static final ItemPedraGem PEDRA_SAFIRA_AZUL = registrarGem("pedra_safira_azul");
    public static final ItemPedraGem PEDRA_SAFIRA_ROSA = registrarGem("pedra_safira_rosa");
    public static final ItemPedraGem PEDRA_SAFIRA_AMARELA = registrarGem("pedra_safira_amarela");
    public static final ItemPedraGem PEDRA_SAFIRA_VERDE = registrarGem("pedra_safira_verde");
    public static final ItemPedraGem PEDRA_SAFIRA_PADPARADSCHA = registrarGem("pedra_safira_padparadscha");

    private static ItemPedraGem registrarGem(String name) {
        // 1. Criamos o Identifier normal para o registro
        Identifier id = Identifier.fromNamespaceAndPath("galaxite", name);
        
        // 2. Criamos a ResourceKey exigida pela 1.21.2+
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id);
        
        // 3. Criamos a instância do ItemPedraGem separadamente
        ItemPedraGem gema = new ItemPedraGem(new Item.Properties().setId(key));
        
        // 4. Registramos no jogo usando o 'id'
        Registry.register(BuiltInRegistries.ITEM, id, gema);
        
        // 5. Retornamos o objeto original que preserva o tipo ItemPedraGem perfeitamente!
        return gema;
    }

    public static void inicializar() {
    }
}