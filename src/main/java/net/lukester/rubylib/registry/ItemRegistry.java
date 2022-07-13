package net.lukester.rubylib.registry;

import net.lukester.rubylib.items.BaseMusicDiscItem;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;

public class ItemRegistry extends BaseRegistry<Item> {

    protected ItemRegistry(ItemGroup itemGroup, String modid) {
        super(itemGroup, modid);
    }

    public Item registerMusicDisc(Identifier id, int power, SoundEvent soundEvent, int lengthInSeconds) {
        MusicDiscItem item = new BaseMusicDiscItem(lengthInSeconds, soundEvent, makeItemSettings());
        register(id, item);
        return item;
    }

    public Item registerSpawnEgg(Identifier id, EntityType<? extends MobEntity> type, int background, int dots) {
        SpawnEggItem item = new SpawnEggItem(type, background, dots, makeItemSettings());

        DispenserBehavior behavior = new DispenserBehavior() {
            @Override
            public ItemStack dispense(BlockPointer pointer, ItemStack stack) {
                Direction direction = pointer.getBlockState().get(DispenserBlock.FACING);
                EntityType<?> entityType = ((SpawnEggItem) stack.getItem()).getEntityType(stack.getNbt());
                entityType.spawn(
                    pointer.getWorld(), 
                    stack.getNbt(), 
                    stack.getName(), 
                    null,
                    pointer.getPos().offset(direction), 
                    SpawnReason.DISPENSER, 
                    direction != Direction.UP, 
                    false
                );
                

                stack.decrement(1);
                return stack;
            }
            
        };

        DispenserBlock.registerBehavior(item, behavior);
        return register(id, item);
    }

    @Override
    public Item register(Identifier id, Item item) {
        registerItem(id, item);
        return item;
    }

    @Override
    public void registerItem(Identifier id, Item item) {
        if (item != null && item != Items.AIR) {
            Registry.register(Registry.ITEM, id, item);
            getModItems(id.getNamespace()).add(item);
        }
    }


    
}
