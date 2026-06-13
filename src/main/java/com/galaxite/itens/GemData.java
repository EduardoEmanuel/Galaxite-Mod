package com.galaxite.itens;

import net.minecraft.core.component.DataComponentType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record GemData(String localPedra, String estiloCabelo, String corOlhos, String roupa) {
    // Codec para salvar e carregar os dados automaticamente
    public static final Codec<GemData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        Codec.STRING.fieldOf("localPedra").forGetter(GemData::localPedra),
        Codec.STRING.fieldOf("estiloCabelo").forGetter(GemData::estiloCabelo),
        Codec.STRING.fieldOf("corOlhos").forGetter(GemData::corOlhos),
        Codec.STRING.fieldOf("roupa").forGetter(GemData::roupa)
    ).apply(instance, GemData::new));

    // Registro do componente (precisa ser registrado no seu ModDataComponentTypes)
    public static final DataComponentType<GemData> GEM_INFO = DataComponentType.<GemData>builder()
            .persistent(CODEC)
            .build();
}