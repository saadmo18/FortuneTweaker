package net.mo.supahotfortune;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

public class BlockBreakEventHandler {

    private static final Set<BlockPos> playerPlacedBlocks = new HashSet<>();

    public static void register() {
        // Listen for block-breaking events
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
            if (world instanceof ServerWorld serverWorld) {
                // Pass both the serverWorld and pos arguments to isPlayerPlaced
                if (isPlayerPlaced(pos)) return true;

                // Check for Fortune enchantment
                ItemStack tool = player.getMainHandStack();
                if (tool != null && EnchantmentHelper.getLevel(Enchantments.FORTUNE, tool) > 0) {
                    applyFortune(serverWorld, player, pos, state, tool);
                }
            }
            return true; // Allow the block break
        });

        // Add block placement tracking
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            if (world instanceof ServerWorld) {
                playerPlacedBlocks.add(pos.toImmutable());
            }
        });
    }
    private static boolean isPlayerPlaced(BlockPos pos) {
        return playerPlacedBlocks.contains(pos);
    }
   // private static boolean isPlayerPlaced(ServerWorld world, BlockPos pos) {
    //    return PlayerPlacedBlocksManager.get(world).isPlayerPlaced(pos);
  //  }


    private static void applyFortune(ServerWorld world, PlayerEntity player, BlockPos pos, BlockState state, ItemStack tool) {
        int fortuneLevel = EnchantmentHelper.getLevel(Enchantments.FORTUNE, tool);

        // Calculate Fortune multiplier
        int multiplier = 1 + world.random.nextInt(fortuneLevel + 1);

        // Prevent default drops
        state.getBlock().dropStacks(state, world, pos, null, player, tool);

        // Apply custom Fortune-enhanced drops
        for (int i = 0; i < multiplier - 1; i++) { // Drop additional items based on Fortune
            state.getBlock().dropStacks(state, world, pos, null, player, tool);
        }

    }

}
