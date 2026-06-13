package com.galaxite.entidades;

import com.galaxite.itens.ModItems;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class Safira extends BaseDasGems {
    
    // DEFINIÇÃO EXCLUSIVA: O número de variantes e itens pertencem apenas à Safira!
    public enum SapphireVariant {
        AZUL("azul", ModItems.PEDRA_SAFIRA_AZUL),
        ROSA("rosa", ModItems.PEDRA_SAFIRA_ROSA),
        AMARELA("amarela", ModItems.PEDRA_SAFIRA_AMARELA),
        VERDE("verde", ModItems.PEDRA_SAFIRA_VERDE);

        private final String id;
        private final Item gemItem;

        SapphireVariant(String id, Item gemItem) {
            this.id = id;
            this.gemItem = gemItem;
        }

        public String getId() { return this.id; }
        public Item getGemItem() { return this.gemItem; }

        public static SapphireVariant byId(String id) {
            for (SapphireVariant variant : values()) {
                if (variant.id.equalsIgnoreCase(id)) return variant;
            }
            return AZUL;
        }
    }

    public enum SapphireClothing {
        DEFAULT("default"),
        NOBLE("nobre"),
        GUARD("guarda"),
        HOMEWORLD("homeworld");

        private final String codename;
        SapphireClothing(String codename) { this.codename = codename; }
        public String getCodename() { return this.codename; }
    }

    public enum SapphireHairstyle {
        DEFAULT("default"),
        NOBLE("nobre"),
        GUARD("guarda"),
        HOMEWORLD("homeworld");

        private final String codename;
        SapphireHairstyle(String codename) { this.codename = codename; }
        public String getCodename() { return this.codename; }
    }

    public enum SapphireSkinVariants {
        DEFAULT("default"),
        NOBLE("nobre"),
        GUARD("guarda"),
        HOMEWORLD("homeworld");

        private final String codename;
        SapphireSkinVariants(String codename) { this.codename = codename; }
        public String getCodename() { return this.codename; }
    }

    public enum SapphireEyeVariants {
        DEFAULT("default"),
        NOBLE("nobre"),
        GUARD("guarda"),
        HOMEWORLD("homeworld");

        private final String codename;
        SapphireEyeVariants(String codename) { this.codename = codename; }
        public String getCodename() { return this.codename; }
    }

    public Safira(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)       // 10 Corações de vida
                .add(Attributes.MOVEMENT_SPEED, 0.25)   // Velocidade padrão de um aldeão/humano
                .add(Attributes.ARMOR, 2.0);            // Pequena resistência natural
    }

    // --- NOVO: MAPEAMENTO DINÂMICO DE LINHAS DA PALETA ---
    @Override
    public int getClothingPartPaletteRow(String partName) {
        return switch (partName.toLowerCase()) {
            case "corpo", "vestido", "peitoral" -> 5; // Linha 5 da paleta (CLOTHING_COLOR)
            case "sapato", "botas", "meias"      -> 6; // Nova linha 6 focada em calçados
            case "capa", "luvas", "detalhes"    -> 7; // Nova linha 7 focada em acessórios/adornos
            default -> 5;                             // Fallback caso venha uma parte genérica
        };
    }

    // --- PREENCHENDO OS GANCHOS DE ROUPA ---

    @Override
    protected String getRandomClothingId(RandomSource random) {
        SapphireClothing[] roupas = SapphireClothing.values();
        return roupas[random.nextInt(roupas.length)].getCodename();
    }

    @Override
    protected String getRandomVariantId(RandomSource random) {
        SapphireVariant[] variantes = SapphireVariant.values();
        return variantes[random.nextInt(variantes.length)].getId();
    }

    @Override
    protected Item getGemItemFromId(String variantId) {
        return SapphireVariant.byId(variantId).getGemItem();
    }

    @Override
    protected String getRandomHairstyleId(RandomSource random) {
        SapphireHairstyle[] cabelos = SapphireHairstyle.values();
        return cabelos[random.nextInt(cabelos.length)].getCodename();
    }

    @Override
    protected String getRandomSkinId(RandomSource random) {
        SapphireSkinVariants[] peles = SapphireSkinVariants.values();
        return peles[random.nextInt(peles.length)].getCodename();
    }

    @Override
    protected String getRandomEyeVariantId(RandomSource random) {
        SapphireEyeVariants[] olhos = SapphireEyeVariants.values();
        return olhos[random.nextInt(olhos.length)].getCodename();
    }

    @Override
    public Item getGemWeapon() { return Items.AIR; }

    // CORRIGIDO: Modificado de "texturas/" para "textures/" para manter o padrão do Minecraft
    @Override
    public String getPaletteTextureFolder() {
        return "textures/entidades/safira/paletas/";
    }
   
    @Override
    public String getClothingTextureFolder() {
        return "textures/entidades/safira/roupas/";
    }

    @Override
    public String getHairstyleTextureFolder() {
        return "textures/entidades/safira/cabelos/";
    }
   
    @Override
    public String getSkinTextureFolder() {
        return "textures/entidades/safira/peles/";
    }

    @Override
    public String getEyeVariantFolder() {
        return "textures/entidades/safira/olhos/";
    }
}