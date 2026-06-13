package com.galaxite.client.render.camadas;

import com.galaxite.client.render.EstadoDoRenderDaGem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector; // Novo coletor da 1.21.4
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.Identifier; // Mantido o Identifier do seu projeto Fabric/Yarn

public class CamadaDeRoupa<M extends EntityModel<EstadoDoRenderDaGem>> extends RenderLayer<EstadoDoRenderDaGem, M> {

    public CamadaDeRoupa(RenderLayerParent<EstadoDoRenderDaGem, M> parent) {
        super(parent);
    }
    
    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords,
                       EstadoDoRenderDaGem state, float yRot, float xRot) {
           
        if (state != null && state.clothingName != null && state.paletteName != null && 
            state.clothingParts != null && state.partRowMapper != null) {
            
            // Corrige o caminho caso a pasta venha com "texturas/" em vez de "textures/"
            String pastaPaleta = state.paletteTextureFolder.replace("texturas/", "textures/");
            Identifier texturaPaleta = Identifier.fromNamespaceAndPath(
                "galaxite",
                pastaPaleta + state.paletteName + ".png"
            );
            
            // LOOP: Renderiza cada parte da roupa separadamente, aplicando sua respectiva linha da paleta
            for (String partName : state.clothingParts) {
                
                // Monta o caminho com base na sua regra: pasta/codinome/codinome_parte.png
                String caminhoCompleto = state.clothingTextureFolder + state.clothingName + "/" + state.clothingName + "_" + partName + ".png";
                Identifier texturaBaseParte = Identifier.fromNamespaceAndPath("galaxite", caminhoCompleto);
                
                // Descobre dinamicamente a linha Y da paleta para esta parte específica
                int linhaPaletaAlvo = state.partRowMapper.apply(partName);
                String chaveCache = "roupa_" + state.clothingName + "_" + partName + "_" + state.paletteName + "_r" + linhaPaletaAlvo;
                Identifier texturaParteColorida = GerenciadorDePaletas.obterOuCriarTexturaColorida(
                    texturaBaseParte,
                    texturaPaleta,
                    linhaPaletaAlvo, // Passa o int direto aqui!
                    chaveCache
                );
                RenderType tipoDeRender = RenderTypes.entityCutout(texturaParteColorida);
                
                // Envia o modelo bípede atualizado para a fila de renderização do Minecraft 1.21.4
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
}