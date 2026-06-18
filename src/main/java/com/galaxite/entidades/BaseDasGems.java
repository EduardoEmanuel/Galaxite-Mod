package com.galaxite.entidades;

import org.jspecify.annotations.NonNull;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public abstract class BaseDasGems extends PathfinderMob {

    // Registradores globais (Sincronização Servidor <-> Cliente)
    private static final EntityDataAccessor<String> GEM_PALETTE_NAME = 
            SynchedEntityData.defineId(BaseDasGems.class, EntityDataSerializers.STRING);
            
    private static final EntityDataAccessor<String> GEM_PLACEMENT = 
            SynchedEntityData.defineId(BaseDasGems.class, EntityDataSerializers.STRING);

    // NOVO: Registrador para armazenar o codinome da vestimenta da Gem individual
    private static final EntityDataAccessor<String> GEM_CLOTHING_NAME = 
            SynchedEntityData.defineId(BaseDasGems.class, EntityDataSerializers.STRING);

    private static final EntityDataAccessor<String> HAIRSTYLE_NAME = 
            SynchedEntityData.defineId(BaseDasGems.class, EntityDataSerializers.STRING);

    private static final EntityDataAccessor<String> SKIN_NAME = 
            SynchedEntityData.defineId(BaseDasGems.class, EntityDataSerializers.STRING);

    private static final EntityDataAccessor<String> EYE_VARIANT = 
            SynchedEntityData.defineId(BaseDasGems.class, EntityDataSerializers.STRING);
            
    
    protected BaseDasGems(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    // --- ENUMS GLOBAIS ---
    public enum GemPlacement {
        FOREHEAD("forehead"), CHEST("chest"), BACK("back"), NAVEL("navel"),
        LEFT_HAND("left_hand"), RIGHT_HAND("right_hand"), RIGHT_EYE("right_eye"), LEFT_EYE("left_eye");

        private final String modelPartName;
        GemPlacement(String modelPartName) { this.modelPartName = modelPartName; }
        public String getModelPartName() { return this.modelPartName; }
    }

    public enum PalettePart {
        HAIR(0), SKIN(1), EYES(2), SCLERA(3), GEM_COLOR(4), CLOTHING_COLOR(5);
        private final int rowIndex;
        PalettePart(int rowIndex) { this.rowIndex = rowIndex; }
        public int getRowIndex() { return this.rowIndex; }
    }
    

    // --- OS DOIS GANCHOOS ABSTRATOS PARA PADRONIZAR VARIANTES ---

    /** Retorna um codinome de roupa aleatório baseado na lista exclusiva da Gem filha */
    protected abstract String getRandomClothingId(RandomSource random);

    protected abstract String getRandomHairstyleId(RandomSource random);

     protected abstract String getRandomEyeVariantId(RandomSource random);
    
    /** Retorna um ID de variante aleatório baseado nas opções da Gem filha */
    protected abstract String getRandomVariantId(RandomSource random);
    
    /** Retorna um ID de variante aleatório baseado nas opções da Gem filha */
    protected abstract String getRandomSkinId(RandomSource random);

    /** Converte a String de variação salva de volta no Item físico de drop */
    protected abstract Item getGemItemFromId(String variantId);

    // --- ATUALIZAÇÃO DO SPAWN NATURAL (Adicionado Sorteio de Roupa) ---
    @Override
    public SpawnGroupData finalizeSpawn(@SuppressWarnings("null") ServerLevelAccessor level, @SuppressWarnings("null") DifficultyInstance difficulty, @SuppressWarnings("null") EntitySpawnReason spawnReason, @SuppressWarnings("null") SpawnGroupData spawnGroupData) {
        SpawnGroupData data = super.finalizeSpawn(level, difficulty, spawnReason, spawnGroupData);
        RandomSource random = level.getRandom();
        this.setGemPlacement(GemPlacement.values()[random.nextInt(GemPlacement.values().length)]);
        this.setPaletteName(this.getRandomVariantId(random));
        this.setClothingName(this.getRandomClothingId(random));
        this.setSkinName(this.getRandomSkinId(random));
        this.setHairstyleName(this.getRandomHairstyleId(random));
        this.setEyeVariant(this.getRandomEyeVariantId(random));

        return data;
    }

   // --- AUTOMATIZAÇÃO DO DROP (POOF) CORRIGIDA ---
    protected void spawnGemStoneItem() {
        // Verifica se estamos no lado do servidor e já converte para ServerLevel
        if (this.level() instanceof net.minecraft.server.level.ServerLevel serverLevel) {
            Item itemParaDropar = this.getGemItemFromId(this.getPaletteName());
            if (itemParaDropar != null) {
                // Passa o serverLevel explicitamente como o Minecraft agora exige
                this.spawnAtLocation(serverLevel, itemParaDropar);
            }
        }
    }
    
    // --- MÉTODOS ABSTRATOS RESTANTES ---
    public abstract Item getGemWeapon();

    public abstract String getPaletteTextureFolder();
    /** Retorna a pasta raiz de roupas daquela Gem. Ex: "textures/entity/sapphire/clothing/" */
    public abstract String getClothingTextureFolder();

    public abstract int getClothingPartPaletteRow(String partName);

    public abstract String getSkinTextureFolder();

    public abstract String getHairstyleTextureFolder();

    public abstract String getEyeVariantFolder();

    
    // --- INICIALIZAÇÃO DOS DADOS SINCROINIZADOS ---
    @Override
    protected void defineSynchedData(@SuppressWarnings("null") SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(GEM_PALETTE_NAME, "default");
        builder.define(GEM_PLACEMENT, GemPlacement.CHEST.name()); // Valor inicial seguro
        builder.define(GEM_CLOTHING_NAME, "default"); // Roupa padrão inicial
        builder.define(HAIRSTYLE_NAME, "default"); // Roupa padrão inicial
        builder.define(EYE_VARIANT, "default"); // Roupa padrão inicial
        builder.define(SKIN_NAME, "default"); // Roupa padrão inicial
    }

    //GETTERS
    public String getPaletteName() { return this.entityData.get(GEM_PALETTE_NAME); }

    public String getClothingName() { return this.entityData.get(GEM_CLOTHING_NAME); }

    public String getHairstyleName() { return this.entityData.get(HAIRSTYLE_NAME); }

     public String getSkinName() { return this.entityData.get(SKIN_NAME); }

    public String getEyeVariant() { return this.entityData.get(EYE_VARIANT); }

    public GemPlacement getGemPlacement() {
        try {
            return GemPlacement.valueOf(this.entityData.get(GEM_PLACEMENT));
        } catch (IllegalArgumentException e) {
            return GemPlacement.CHEST; // Fallback de segurança
        }
    }

    //SETTERS
    public void setPaletteName(String name) { this.entityData.set(GEM_PALETTE_NAME, name); }

    public void setClothingName(String clothingName) { this.entityData.set(GEM_CLOTHING_NAME, clothingName); }

    public void setHairstyleName(String hairstyleName) { this.entityData.set(HAIRSTYLE_NAME, hairstyleName); }

    public void setSkinName(String skinName) { this.entityData.set(SKIN_NAME, skinName); }

    public void setEyeVariant(String eyeVariant) { this.entityData.set(EYE_VARIANT, eyeVariant); }

    public void setGemPlacement(GemPlacement placement) {
        this.entityData.set(GEM_PLACEMENT, placement.name());
    }

    // --- CONTROLE DE TEXTURA ---
    public Identifier getTexturePaletteLocation() {
        String caminhoCompleto = getPaletteTextureFolder() + this.getPaletteName() + ".png";
        return Identifier.fromNamespaceAndPath("galaxite", caminhoCompleto); 
    }

    // --- MÉTODOS DE LOCALIZAÇÃO DINÂMICA DE ROUPAS ---
    /**
     * Monta dinamicamente o caminho da parte da roupa conforme sua regra!
     * Exemplo se partName for "sapato": 
     * "textures/entity/sapphire/clothing/nobre/nobre_sapato.png"
     */
    public Identifier getClothingPartLocation(String partName) {
        String codinome = this.getClothingName();
        String caminhoCompleto = getClothingTextureFolder() + codinome + "/" + codinome + "_" + partName + ".png";
        return Identifier.fromNamespaceAndPath("galaxite", caminhoCompleto);
    }

    public Identifier getHairstylePartLocation(String partName) {
        String codinome = this.getHairstyleName();
        String caminhoCompleto = getHairstyleTextureFolder() + codinome + "/" + codinome + "_" + partName + ".png";
        return Identifier.fromNamespaceAndPath("galaxite", caminhoCompleto);
    }

    public String[] getHairParts() {
    return new String[]{"cabelo"}; // Vai buscar "[nome_do_cabelo]_cabelo.png"
    }
    
    /** Retorna qual linha da paleta deve pintar cada mecha/camada de cabelo específica */
    public int getHairPartPaletteRow(String partName) {
        return 0; // Por padrão, usa a Linha 0 (HAIR) definida no seu Enum
    }

    public Identifier getSkinPartLocation(String partName) {
        String codinome = this.getSkinName();
        String caminhoCompleto = getSkinTextureFolder() + codinome + "/" + codinome + "_" + partName + "_corpo.png";
        return Identifier.fromNamespaceAndPath("galaxite", caminhoCompleto);
    }

    public Identifier getEyeVariantLocation(String partName) {
        String codinome = this.getEyeVariant();
        String caminhoCompleto = getEyeVariantFolder() + codinome + "/" + codinome + "_" + partName + ".png";
        return Identifier.fromNamespaceAndPath("galaxite", caminhoCompleto);
    }

    // --- SALVAMENTO UNIFICADO NO NBT (Paleta + Local da Pedra) ---
    @Override
    protected void addAdditionalSaveData(final @NonNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putString("GemPaletteName", this.getPaletteName());
        output.putString("GemPlacementLocation", this.getGemPlacement().name());
        output.putString("clothingName", this.getClothingName());
        output.putString("hairstyleName", this.getHairstyleName());
        output.putString("skinName", this.getSkinName());
        output.putString("eyeVariant", this.getEyeVariant());
        
    }

    @Override
    protected void readAdditionalSaveData(final @NonNull ValueInput input) {
        super.readAdditionalSaveData(input);
        this.setPaletteName(input.getStringOr("GemPaletteName", "default"));
        this.setClothingName(input.getStringOr("ClothingName", "default"));
        this.setHairstyleName(input.getStringOr("HairstyleName", "default"));
        this.setSkinName(input.getStringOr("SkinName", "default"));
        this.setEyeVariant(input.getStringOr("EyeVariant", "default"));
        
        String localSalvo = input.getStringOr("GemPlacementLocation", GemPlacement.CHEST.name());
        try {
            this.setGemPlacement(GemPlacement.valueOf(localSalvo));
        } catch (IllegalArgumentException e) {
            this.setGemPlacement(GemPlacement.CHEST);
        }
    }

    public void poof() {
        if (!this.level().isClientSide()) {
            this.spawnGemStoneItem();
            this.discard();
        }
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }
    
}