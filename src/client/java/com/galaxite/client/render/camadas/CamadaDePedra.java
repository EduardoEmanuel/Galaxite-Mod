package com.galaxite.client.render.camadas;

import com.galaxite.client.render.EstadoDoRenderDaGem;
import com.galaxite.entidades.BaseDasGems;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class CamadaDePedra<M extends EntityModel<EstadoDoRenderDaGem>> extends RenderLayer<EstadoDoRenderDaGem, M> {

    public CamadaDePedra(RenderLayerParent<EstadoDoRenderDaGem, M> parent) {
        super(parent);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords,
                       EstadoDoRenderDaGem state, float yRot, float xRot) {
           
        if (state != null && state.paletteName != null) {
            
            // 1. Corrige o caminho da paleta para garantir o formato "textures/" do Minecraft
            String pastaPaleta = state.paletteTextureFolder.replace("texturas/", "textures/");
            Identifier texturaPaleta = Identifier.fromNamespaceAndPath(
                "galaxite",
                pastaPaleta + state.paletteName + ".png"
            );
            
            // 2. Define a textura ÚNICA da pedra em tons de cinza.
            // Em vez de procurar por partes corporais, busca diretamente o arquivo estático "pedra.png"
            // Caminho resultante: "textures/entidades/safira/pedra/pedra.png"
            String pastaPedra = pastaPaleta.replace("paletas/", "pedra/");
            Identifier texturaBasePedra = Identifier.fromNamespaceAndPath(
                "galaxite",
                pastaPedra + "pedra.png"
            );
            
            // 3. Obtém o índice 4 (GEM_COLOR) definido no Enum da BaseDasGems para pintar a pedra
            int linhaPaletaAlvo = BaseDasGems.PalettePart.GEM_COLOR.getRowIndex(); // Retorna 4
            
            // 4. CHAVE DE CACHE ULTRA OTIMIZADA:
            // Como a imagem base é única, se houverem 30 Safiras Azuis no mapa (não importa onde a gema esteja), 
            // a textura será gerada apenas 1 vez na memória e reutilizada para todas!
            String chaveCache = "pedra_gem_cor_" + state.paletteName;
            
            // 5. Solicita a fusão da textura única da pedra com a linha 4 da paleta atual
            Identifier texturaPedraColorida = GerenciadorDePaletas.obterOuCriarTexturaColorida(
                texturaBasePedra,
                texturaPaleta,
                linhaPaletaAlvo,
                chaveCache
            );
            
            // 6. Configura o tipo de renderização com suporte a recortes transparentes
            RenderType tipoDeRender = RenderTypes.entityCutout(texturaPedraColorida);
            
            // 7. Envia o modelo da gema com a pedra colorida para o pipeline de renderização do Minecraft 1.21.4
            // O próprio modelo da entidade se encarregará de mapear as UVs corretas nos cubos da gema.
            submitNodeCollector.submitModel(
                this.getParentModel(), 
                state,                 
                poseStack,             
                tipoDeRender,          
                lightCoords,           
                OverlayTexture.NO_OVERLAY, 
                0,                         
                null                       
            );
        }
    }
}