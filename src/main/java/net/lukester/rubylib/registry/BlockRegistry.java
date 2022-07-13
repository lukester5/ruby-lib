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


    @Override
    public Block register(String id, Block block) {
        Identifier identifier = new Identifier(this.getModId(), id);
        BlockItem item = null;
        item = new BlockItem(block, makeItemSettings());

        registerBlockItem(id, item);
        if (block.getDefaultState().getMaterial().isBurnable() && FlammableBlockRegistry.getDefaultInstance().get(block).getBurnChance() == 0) {
            FlammableBlockRegistry.getDefaultInstance().add(block, 5, 5);
        }

        Block newBlock = Registry.register(Registry.BLOCK, identifier, block);
        getModBlocks(identifier.getNamespace()).add(block);

        return newBlock;
    }

    public Block registerBlockOnly(String id, Block block) {
        Identifier identifier = new Identifier(this.getModId(), id);
        getModBlocks(identifier.getNamespace()).add(block);
        return Registry.register(Registry.BLOCK, identifier, block);
    }

    private Item registerBlockItem(String id, Item item) {
        registerItem(id, item);
        return item;
    }

    @Override
    public void registerItem(String id, Item item) {
        Identifier identifier = new Identifier(this.getModId(), id);
        if (item != null && item != Items.AIR) {
            Registry.register(Registry.ITEM, identifier, item);
            getModBlockItems(identifier.getNamespace()).add(item);
        }
    }
    
}
