/* package net.mo.supahotfortune;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.PersistentState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.PersistentState;
import java.util.HashSet;
import java.util.Set;

public class PlayerPlacedBlocksManager extends PersistentState {

    private static final String KEY = "player_placed_blocks";
    private final Set<BlockPos> playerPlacedBlocks = new HashSet<>();

    // Retrieves or creates a new state
    public static PlayerPlacedBlocksManager get(ServerWorld world) {
        return world.getPersistentStateManager().getOrCreate(
                PlayerPlacedBlocksManager::new,
                () -> new PlayerPlacedBlocksManager(),
                KEY
        );
    }

    // Default constructor for creating new state
    public PlayerPlacedBlocksManager() {
    }

    // Constructor for loading state from NBT
    public PlayerPlacedBlocksManager(NbtCompound nbt) {
        readNbt(nbt);
    }

    // Read from NBT (must match superclass)
    @Override
    public void readNbt(NbtCompound nbt) {
        // Read data from the provided NBT compound
        playerPlacedBlocks.clear();
        int[] positions = nbt.getIntArray("playerPlacedBlocks");
        for (int i = 0; i < positions.length; i += 3) {
            playerPlacedBlocks.add(new BlockPos(positions[i], positions[i + 1], positions[i + 2]));
        }
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        // Write data to the provided NBT compound
        int[] positions = new int[playerPlacedBlocks.size() * 3];
        int i = 0;
        for (BlockPos pos : playerPlacedBlocks) {
            positions[i++] = pos.getX();
            positions[i++] = pos.getY();
            positions[i++] = pos.getZ();
        }
        nbt.putIntArray("playerPlacedBlocks", positions);
        return nbt;
    }

    // Add a block to the tracked set
    public void addBlock(BlockPos pos) {
        playerPlacedBlocks.add(pos.toImmutable());
        markDirty(); // Marks state as dirty so it gets saved
    }

    // Check if a block is player-placed
    public boolean isPlayerPlaced(BlockPos pos) {
        return playerPlacedBlocks.contains(pos);
    }
}
 */