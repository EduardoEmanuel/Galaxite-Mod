package com.galaxite.client.render;

import org.jspecify.annotations.NonNull;

import com.galaxite.client.render.camadas.CamadaDeCabelo;
import com.galaxite.client.render.camadas.CamadaDeOlho;
import com.galaxite.client.render.camadas.CamadaDePedra;
import com.galaxite.client.render.camadas.CamadaDeRoupa;
import com.galaxite.client.render.camadas.GerenciadorDePaletas;
import com.galaxite.entidades.BaseDasGems;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public abstract class RenderBaseDaGem<T extends BaseDasGems, M extends EntityModel<EstadoDoRenderDaGem>> extends MobRenderer<T, EstadoDoRenderDaGem, M> {

    public RenderBaseDaGem(EntityRendererProvider.Context context, M model, float shadowRadius) {
        super(context, model, shadowRadius);
        // PELE REMOVIDA DAQUI: O modelo principal agora vai renderizar a pele colorida diretamente!
        this.addLayer(new CamadaDeCabelo<>(this));
        this.addLayer(new CamadaDeRoupa<>(this));
        this.addLayer(new CamadaDeOlho<>(this));
        this.addLayer(new CamadaDePedra<>(this));
    }

    @Override
    public EstadoDoRenderDaGem createRenderState() {
        return new EstadoDoRenderDaGem();
    }

    @Override
    public void extractRenderState(T entity, @NonNull EstadoDoRenderDaGem state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        
        state.gemPlacement = entity.getGemPlacement();
        state.clothingName = entity.getClothingName();
        state.hairstyleName = entity.getHairstyleName();
        state.eyeVariantName = entity.getEyeVariant();
        state.skinName = entity.getSkinName();
        state.paletteName = entity.getPaletteName();
        state.clothingTextureFolder = entity.getClothingTextureFolder();
        state.hairstyleTextureFolder = entity.getHairstyleTextureFolder();
        state.skinTextureFolder = entity.getSkinTextureFolder();
        state.eyeVariantTextureFolder = entity.getEyeVariantFolder();
        state.paletteTextureFolder = entity.getPaletteTextureFolder();

        state.clothingParts = this.getClothingParts(); 
        state.partRowMapper = entity::getClothingPartPaletteRow;

        state.hairParts = entity.getHairParts();
        if (state.hairParts != null) {
            state.hairPartRows = new int[state.hairParts.length];
            for (int i = 0; i < state.hairParts.length; i++) {
                state.hairPartRows[i] = entity.getHairPartPaletteRow(state.hairParts[i]);
            }
        }

        float bodyYaw = net.minecraft.util.Mth.rotLerp(partialTicks, entity.yBodyRotO, entity.yBodyRot);
        float headYaw = net.minecraft.util.Mth.rotLerp(partialTicks, entity.yHeadRotO, entity.yHeadRot);
        
        state.netHeadYaw = headYaw - bodyYaw; 
        state.headPitch = net.minecraft.util.Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());

        this.aquecerCacheTexturas(state);
    }

    // 🔥 ADICIONADO: Método obrigatório do Minecraft que resolve o erro do default.png
    @Override
    public Identifier getTextureLocation(EstadoDoRenderDaGem state) {
        if (state != null && state.skinName != null && state.paletteName != null) {
            String pastaPele = state.skinTextureFolder.replace("texturas/", "textures/");
            String pastaPaleta = state.paletteTextureFolder.replace("texturas/", "textures/");
            
            // Aponta diretamente para o ficheiro correto dentro da pasta da gema
            Identifier base = Identifier.fromNamespaceAndPath("galaxite", pastaPele + state.skinName + "/" + state.skinName + "_corpo.png");
            Identifier texturaPaleta = Identifier.fromNamespaceAndPath("galaxite", pastaPaleta + state.paletteName + ".png");
            int linhaPele = com.galaxite.entidades.BaseDasGems.PalettePart.SKIN.getRowIndex();
            String chave = "pele_" + state.skinName + "_corpo_" + state.paletteName;
            
            return GerenciadorDePaletas.obterOuCriarTexturaColorida(base, texturaPaleta, linhaPele, chave);
        }
        // Fallback seguro (uma textura branca padrão do Minecraft) caso os dados não estejam prontos
        return Identifier.fromNamespaceAndPath("minecraft", "textures/misc/white.png");
    }

    private void aquecerCacheTexturas(EstadoDoRenderDaGem state) {
        if (state == null || state.paletteName == null) return;

        String pastaPaleta = state.paletteTextureFolder.replace("texturas/", "textures/");
        Identifier texturaPaleta = Identifier.fromNamespaceAndPath("galaxite", pastaPaleta + state.paletteName + ".png");

        // 2. Aquecer Cache da Pele
        if (state.skinName != null) {
            String pastaPele = state.skinTextureFolder.replace("texturas/", "textures/");
            int linhaPele = com.galaxite.entidades.BaseDasGems.PalettePart.SKIN.getRowIndex();
            String[] partesPele = {"corpo"}; 
            for (String parte : partesPele) {
                Identifier base = Identifier.fromNamespaceAndPath("galaxite", pastaPele + state.skinName + "/" + state.skinName + "_" + parte + ".png");
                String chave = "pele_" + state.skinName + "_" + parte + "_" + state.paletteName;
                GerenciadorDePaletas.obterOuCriarTexturaColorida(base, texturaPaleta, linhaPele, chave);
            }
        }

        // 3. Aquecer Cache do Cabelo
        if (state.hairstyleName != null && state.hairParts != null) {
            String pastaCabelo = state.hairstyleTextureFolder.replace("texturas/", "textures/");
            int linhaCabelo = com.galaxite.entidades.BaseDasGems.PalettePart.HAIR.getRowIndex();
            for (String parte : state.hairParts) {
                Identifier base = Identifier.fromNamespaceAndPath("galaxite", pastaCabelo + state.hairstyleName + "/" + state.hairstyleName + "_" + parte + ".png");
                String chave = "cabelo_" + state.hairstyleName + "_" + parte + "_" + state.paletteName;
                GerenciadorDePaletas.obterOuCriarTexturaColorida(base, texturaPaleta, linhaCabelo, chave);
            }
        }

        // 4. Aquecer Cache da Roupa
        if (state.clothingName != null && state.clothingParts != null && state.partRowMapper != null) {
            String pastaRoupa = state.clothingTextureFolder.replace("texturas/", "textures/");
            for (String parte : state.clothingParts) {
                String caminho = pastaRoupa + state.clothingName + "/" + state.clothingName + "_" + parte + ".png";
                Identifier base = Identifier.fromNamespaceAndPath("galaxite", caminho);
                int linhaRoupa = state.partRowMapper.apply(parte);
                String chave = "roupa_" + state.clothingName + "_" + parte + "_" + state.paletteName + "_r" + linhaRoupa;
                GerenciadorDePaletas.obterOuCriarTexturaColorida(base, texturaPaleta, linhaRoupa, chave);
            }
        }

        // 5. Aquecer Cache dos Olhos
        if (state.eyeVariantName != null) {
            String pastaOlhos = state.eyeVariantTextureFolder.replace("texturas/", "textures/");
            
            int linhaEsclera = com.galaxite.entidades.BaseDasGems.PalettePart.SCLERA.getRowIndex();
            Identifier baseEsclera = Identifier.fromNamespaceAndPath("galaxite", pastaOlhos + state.eyeVariantName + "/" + state.eyeVariantName + "_esclera.png");
            String chaveEsclera = "esclera_" + state.eyeVariantName + "_" + state.paletteName;
            GerenciadorDePaletas.obterOuCriarTexturaColorida(baseEsclera, texturaPaleta, linhaEsclera, chaveEsclera);
            
            int linhaIris = com.galaxite.entidades.BaseDasGems.PalettePart.EYES.getRowIndex();
            Identifier baseIris = Identifier.fromNamespaceAndPath("galaxite", pastaOlhos + state.eyeVariantName + "/" + state.eyeVariantName + "_iris.png");
            String chaveIris = "iris_" + state.eyeVariantName + "_" + state.paletteName;
            GerenciadorDePaletas.obterOuCriarTexturaColorida(baseIris, texturaPaleta, linhaIris, chaveIris);
            
            int linhaPupila = com.galaxite.entidades.BaseDasGems.PalettePart.EYES.getRowIndex();
            Identifier basePupila = Identifier.fromNamespaceAndPath("galaxite", pastaOlhos + state.eyeVariantName + "/" + state.eyeVariantName + "_pupila.png");
            String chavePupila = "pupila_" + state.eyeVariantName + "_" + state.paletteName;
            GerenciadorDePaletas.obterOuCriarTexturaColorida(basePupila, texturaPaleta, linhaPupila, chavePupila);
        }

        // 6. Aquecer Cache da Pedra da Gem Dinamicamente
        if (state.skinTextureFolder != null) {
            String pastaPele = state.skinTextureFolder.replace("texturas/", "textures/");
            String pastaEntidade = pastaPele.replace("peles/", "");
            if (!pastaEntidade.endsWith("/")) {
                pastaEntidade += "/";
            }
            
            Identifier basePedra = Identifier.fromNamespaceAndPath("galaxite", pastaEntidade + "pedra.png");
            String chavePedra = "pedra_gem_cor_" + state.paletteName;
            GerenciadorDePaletas.obterOuCriarTexturaColorida(basePedra, texturaPaleta, 4, chavePedra);
        }
    }

    protected abstract String[] getClothingParts();

    public Identifier getTextureLocationPalette(EstadoDoRenderDaGem state) { return Identifier.fromNamespaceAndPath("galaxite", state.paletteTextureFolder + state.paletteName + ".png"); }
    public Identifier getTextureLocationClothing(EstadoDoRenderDaGem state) { return Identifier.fromNamespaceAndPath("galaxite", state.clothingTextureFolder + state.clothingName + ".png"); }
    public Identifier getTextureLocationHairstyles(EstadoDoRenderDaGem state) { return Identifier.fromNamespaceAndPath("galaxite", state.hairstyleTextureFolder + state.hairstyleName + ".png"); }
    public Identifier getTextureLocationSkins(EstadoDoRenderDaGem state) { return Identifier.fromNamespaceAndPath("galaxite", state.skinTextureFolder + state.skinName + ".png"); }
    public Identifier getTextureLocationEyeVariants(EstadoDoRenderDaGem state) { return Identifier.fromNamespaceAndPath("galaxite", state.eyeVariantTextureFolder + state.eyeVariantName + ".png"); }

    public Identifier getTextureLocationGem(EstadoDoRenderDaGem state) {
        if (state != null && state.skinTextureFolder != null) {
            String pastaPele = state.skinTextureFolder.replace("texturas/", "textures/");
            String pastaEntidade = pastaPele.replace("peles/", "");
            if (!pastaEntidade.endsWith("/")) {
                pastaEntidade += "/";
            }
            return Identifier.fromNamespaceAndPath("galaxite", pastaEntidade + "pedra.png");
        }
        return Identifier.fromNamespaceAndPath("galaxite", "textures/entidades/gem/pedra.png");
    }
}