package com.galaxite.client.render.entidades;

import com.galaxite.client.modelos.ModeloSafira2;
import com.galaxite.client.render.EstadoDoRenderDaGem;
import com.galaxite.client.render.ModModelLayers;
import com.galaxite.client.render.RenderBaseDaGem;
import com.galaxite.entidades.Safira;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class SafiraRender extends RenderBaseDaGem<Safira, ModeloSafira2> {

    public SafiraRender(EntityRendererProvider.Context context) {
        // 1. Passamos o contexto do renderizador.
        // 2. Instanciamos o ModeloSafira injetando a sua camada de geometria assada (bakeLayer).
        // 3. Definimos o raio da sombra no chão (0.5f é o padrão para formato humano/Steve).
        super(context, new ModeloSafira2(context.bakeLayer(ModModelLayers.SAFIRA)), 0.5f);
    }

    
    @Override
    public Identifier getTextureLocation(EstadoDoRenderDaGem state) {
        return Identifier.fromNamespaceAndPath("galaxite", "textures/entidades/vazio.png");
    }

     /*   @Override
    protected String[] getClothingParts() {
        // IMPORTANTE: Este array deve conter exatamente as partes que a sua classe Safira.java, essa versão comentada é para suporte
        // mapeia dentro do método 'getClothingPartPaletteRow'.
        // Isso garante que o loop de renderização da roupagem saiba o que processar.
        return new String[]{
            "corpo", "vestido", "peitoral", 
            "sapato", "botas", "meias", 
            "capa", "luvas", "detalhes"
        };
         */
        
    @Override
    protected String[] getClothingParts() {
        // IMPORTANTE: Este array deve conter exatamente as partes que a sua classe Safira.java
        // mapeia dentro do método 'getClothingPartPaletteRow'.
        // Isso garante que o loop de renderização da roupagem saiba o que processar.
        return new String[]{
            "top",
            "sapatos", 
            "luvas", "detalhes"
        };
    }
}