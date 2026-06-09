package com.galaxite.entidades;

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

    String id_da_gem = "safira";
    
    // DEFINIÇÃO EXCLUSIVA: O número de variantes e itens pertencem apenas à Safira!
    public enum SapphireVariant {
        BLUE("blue", Items.LAPIS_LAZULI),
        PINK("pink", Items.AMETHYST_SHARD),
        YELLOW("yellow", Items.GOLD_NUGGET),
        GREEN("green", Items.EMERALD);

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
            return BLUE;
        }
    }

    // NOVO ENUM: Roupas exclusivas da Safira!
    public enum SapphireClothing {
        DEFAULT("default"),
        NOBLE("nobre"),
        GUARD("guarda"),
        HOMEWORLD("homeworld");

        private final String codename;
        SapphireClothing(String codename) { this.codename = codename; }
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

    // --- PREENCHENDO OS NOVOS GANCHOOS DE ROUPA ---

    @Override
    protected String getRandomClothingId(RandomSource random) {
        SapphireClothing[] roupas = SapphireClothing.values();
        return roupas[random.nextInt(roupas.length)].getCodename();
    }

    @Override
    protected String getClothingTextureFolder() {
        return "textures/entity/sapphire/clothing/";
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
    public Item getGemWeapon() { return Items.AIR; }

   @Override
    protected String getPaletteTextureFolder() {
        return "texturas/entidades/safira/paletas/";
    }
  
}
