/* package com.galaxite.entidades;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class QuartzoAuraAngelical extends BaseDasGems {

    protected QuartzoAuraAngelical(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        
    }

    @Override
    public String[] getHairParts() {
        // Definimos 3 camadas de texturas que se sobrepõem no modelo (Suporta até 4)
        // A ordem do array importa: "base" desenha primeiro, "mechas" por cima, "brilho" por último!
        return new String[]{"base", "mechas", "brilho"};
    }

    @Override
    public int getHairPartPaletteRow(String partName) {
        return switch (partName) {
            case "base" -> 0;    // A base do cabelo usa a Linha 0 (HAIR) padrão da paleta
            case "mechas" -> 5;  // As mechas usam a Linha 5 (CLOTHING_COLOR) para ganhar uma cor contrastante!
            case "brilho" -> 7;  // Os reflexos finais usam uma linha totalmente nova (Ex: Linha 7) para cores vibrantes
            default -> 0;
        };
    }

    @Override
    protected String getRandomClothingId(RandomSource random) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRandomClothingId'");
    }

    @Override
    protected String getRandomHairstyleId(RandomSource random) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRandomHairstyleId'");
    }

    @Override
    protected String getRandomEyeVariantId(RandomSource random) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRandomEyeVariantId'");
    }

    @Override
    protected String getRandomVariantId(RandomSource random) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRandomVariantId'");
    }

    @Override
    protected String getRandomSkinId(RandomSource random) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRandomSkinId'");
    }

    @Override
    protected Item getGemItemFromId(String variantId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGemItemFromId'");
    }

    @Override
    public Item getGemWeapon() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGemWeapon'");
    }

    @Override
    public String getPaletteTextureFolder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPaletteTextureFolder'");
    }

    @Override
    public String getClothingTextureFolder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClothingTextureFolder'");
    }

    @Override
    public int getClothingPartPaletteRow(String partName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClothingPartPaletteRow'");
    }

    @Override
    public String getSkinTextureFolder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSkinTextureFolder'");
    }

    @Override
    public String getHairstyleTextureFolder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHairstyleTextureFolder'");
    }

    @Override
    public String getEyeVariantFolder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEyeVariantFolder'");
    }
} */
