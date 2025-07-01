package ru.mrbedrockpy.swaphotbar;

import org.bukkit.inventory.ItemStack;
import ru.mrbedrockpy.bedrocklib.serialization.Serializer;
import ru.mrbedrockpy.bedrocklib.serialization.ItemSerializationUtil;

public class SerializeConfig extends ru.mrbedrockpy.bedrocklib.serialization.SerializeConfig<Plugin> {

    public SerializeConfig(Plugin plugin) {
        super(plugin);
        registerAll(
                new Serializer<>(String.class, s -> s, s -> s),
                new Serializer<>(ItemStack[].class, ItemSerializationUtil::itemStackArrayToBase64, this::hotBarFromBase64)
        );
    }

    public ItemStack[] hotBarFromBase64(String data) {
        try {
            return ItemSerializationUtil.itemStackArrayFromBase64(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
