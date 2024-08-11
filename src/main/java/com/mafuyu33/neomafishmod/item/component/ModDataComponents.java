package com.mafuyu33.neomafishmod.item.component;

import com.mafuyu33.neomafishmod.NeoMafishMod;
import com.mafuyu33.neomafishmod.item.component.custom.MyCustomData;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apache.http.impl.cookie.PublicSuffixDomainFilter;

import java.util.List;
import java.util.function.UnaryOperator;

public class ModDataComponents {
    public static DeferredRegister<DataComponentType<?>> DATA_COMPONENTS = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, NeoMafishMod.MODID);


    public static final DeferredHolder<DataComponentType<?>,DataComponentType<List<String>>> RUBY_STAFF_DES = register(
            "ruby_staff_des", builder -> builder.persistent(Codec.STRING.listOf())
    );


    public static final DeferredHolder<DataComponentType<?>,DataComponentType<MyCustomData>> MY_CUSTOM_DATA = register(
            "my_custom_data", builder -> builder.persistent(MyCustomData.CODEC).networkSynchronized(MyCustomData.STREAM_CODEC)
    );

    private static <T> DeferredHolder<DataComponentType<?>,DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builder) {
        return DATA_COMPONENTS.register(name,()->  builder.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus){
        DATA_COMPONENTS.register(eventBus);
    }
}
