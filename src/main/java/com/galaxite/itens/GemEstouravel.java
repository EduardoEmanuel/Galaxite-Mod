package com.galaxite.itens;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface GemEstouravel {
  // Obrigamos a implementação a retornar o próprio item
    Item asItem();

    default ItemStack criarGemDrop(String local, String cabelo, String olhos, String roupa) {
        // Agora o 'asItem()' existe porque definimos acima
        ItemStack stack = new ItemStack(this.asItem()); 
        stack.set(GemData.GEM_INFO, new GemData(local, cabelo, olhos, roupa));
        return stack;
    }
}