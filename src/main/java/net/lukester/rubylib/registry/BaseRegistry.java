package net.lukester.rubylib.registry;

import java.util.List;
import java.util.Map;

import org.apache.commons.compress.utils.Lists;

import com.google.common.collect.Maps;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

public abstract class BaseRegistry<T> {
    private static final List<BaseRegistry<?>> REGISTRIES = Lists.newArrayList();
    private static final Map<String, List<Item>> MOD_BLOCK_ITEMS  = Maps.newHashMap();
    private static final Map<String, List<Block>> MOD_BLOCKS = Maps.newHashMap();
    private static final Map<String, List<Item>> MOD_ITEMS  = Maps.newHashMap();
    
    protected final ItemGroup itemGroup;

    private String MOD_ID;

    protected BaseRegistry(ItemGroup itemGroup, String modid) {
        this.itemGroup = itemGroup;
        this.MOD_ID = modid;
        REGISTRIES.add(this);
    }

    protected String getModId() {
        return MOD_ID;
    }

    public abstract T register(Identifier id, T obj);

    public abstract void registerItem(Identifier id, Item item);

    public FabricItemSettings makeItemSettings() {
        FabricItemSettings properties = new FabricItemSettings();
        return (FabricItemSettings) properties.group(itemGroup);
    }

    private void registerInternal() {
    }

    public static Map<String, List<Item>> getRegisteredBlocks() {
        return MOD_BLOCK_ITEMS;
    }

    public static Map<String, List<Item>> getRegisteredItems() {
        return MOD_ITEMS;
    }

    public static List<Item> getModBlockItems(String modId) {
        if (MOD_BLOCK_ITEMS.containsKey(modId)) {
            return MOD_BLOCK_ITEMS.get(modId);
        }
        List<Item> modBlocks = Lists.newArrayList();
        MOD_BLOCK_ITEMS.put(modId, modBlocks);
        return modBlocks;
    }

    public static List<Item> getModItems(String modId) {
        if (MOD_ITEMS.containsKey(modId)) {
            return MOD_ITEMS.get(modId);
        }
        List<Item> modBlocks = Lists.newArrayList();
        MOD_ITEMS.put(modId, modBlocks);
        return modBlocks;
    }

    public static List<Block> getModBlocks(String modId) {
        if (MOD_BLOCKS.containsKey(modId)) {
            return MOD_BLOCKS.get(modId);
        }
        List<Block> modBlocks = Lists.newArrayList();
        MOD_BLOCKS.put(modId, modBlocks);
        return modBlocks;
    }


    public static void register() {
        REGISTRIES.forEach(BaseRegistry::registerInternal);
    }
}
