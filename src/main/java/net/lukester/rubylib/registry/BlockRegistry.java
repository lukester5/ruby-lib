package net.lukester.rubylib.registry;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/*
 * Registrations
 */
public class BlockRegistry extends BaseRegistry<Block> {

    protected BlockRegistry(ItemGroup itemGroup, String modid) {
        super(itemGroup, modid);
    }

    public Block register(String id, Block block) {
        return this.register(new Identifier(this.getModid(), id), block);
    }

    @Override
    public Block register(Identifier id, Block block) {
        BlockItem item = null;
        item = new BlockItem(block, makeItemSettings());

        registerBlockItem(id, item);
        if (block.getDefaultState().getMaterial().isBurnable() && FlammableBlockRegistry.getDefaultInstance().get(block).getBurnChance() == 0) {
            FlammableBlockRegistry.getDefaultInstance().add(block, 5, 5);
        }

        block = Registry.register(Registry.BLOCK, id, block);
        getModBlocks(id.getNamespace()).add(block);

        return block;
    }

    public Block registerBlockOnly(Identifier id, Block block) {
        getModBlocks(id.getNamespace()).add(block);
        return Registry.register(Registry.BLOCK, id, block);
    }

    private Item registerBlockItem(Identifier id, Item item) {
        registerItem(id, item);
        return item;
    }

    @Override
    public void registerItem(Identifier id, Item item) {
        if (item != null && item != Items.AIR) {
            Registry.register(Registry.ITEM, id, item);
            getModBlockItems(id.getNamespace()).add(item);
        }
    }
    
}
