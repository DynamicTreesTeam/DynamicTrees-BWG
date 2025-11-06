package maxhyper.dtbwg.trees;

import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.block.branch.BasicBranchBlock;
import com.dtteam.dynamictrees.block.branch.BranchBlock;
import com.dtteam.dynamictrees.block.branch.ThickBranchBlock;
import com.dtteam.dynamictrees.block.leaves.DynamicLeavesBlock;
import com.dtteam.dynamictrees.systems.GrowSignal;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.species.Species;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class DiagonalPalmFamily extends Family {

    public static final TypedRegistry.EntryType<Family> TYPE = TypedRegistry.newType(DiagonalPalmFamily::new);

    public DiagonalPalmFamily(ResourceLocation name) {
        super(name);
    }
    
    @Override
    protected BranchBlock createBranchBlock(ResourceLocation name) {
        final BasicBranchBlock branch = isThick() ? new ThickBranchBlock(name, this.getProperties()){
            @Override
            public @NotNull GrowSignal growIntoAir(Level world, BlockPos pos, GrowSignal signal, int fromRadius) {
                return DiagonalPalmFamily.growIntoAir(world, pos, signal, fromRadius, this);
            }
        } : new BasicBranchBlock(name, this.getProperties()){
            @Override
            public @NotNull GrowSignal growIntoAir(Level world, BlockPos pos, GrowSignal signal, int fromRadius) {
                return DiagonalPalmFamily.growIntoAir(world, pos, signal, fromRadius, this);
            }
        };
        if (this.isFireProof())
            branch.setFireSpreadSpeed(0).setFlammability(0);
        return branch;
    }

    private static GrowSignal growIntoAir(Level world, BlockPos pos, GrowSignal signal, int fromRadius, BranchBlock branchBlock) {
        final Species species = signal.getSpecies();

        final DynamicLeavesBlock leaves = species.getLeavesBlock().orElse(null);
        if (leaves != null) {
            if (fromRadius == branchBlock.getFamily().getPrimaryThickness()) {// If we came from a twig (and we're not a stripped branch) then just make some leaves
                if (BranchBlock.isNextToBranch(world, pos, signal.dir.getOpposite())){
                    signal.success = false;
                    return signal;
                }
                // TODO: check if this got clobbered
                signal.success = (leaves.growLeavesIfLocationIsSuitable(world, species.getLeavesProperties(), pos.above(), 0) != 0);
                if (signal.success)
                    return leaves.branchOut(world, pos, signal);
            } else {// Otherwise make a proper branch
                return leaves.branchOut(world, pos, signal);
            }
        } else {
            //If the leaves block is null, the branch grows directly without checking for leaves requirements
            if (BranchBlock.isNextToBranch(world, pos, signal.dir.getOpposite())){
                signal.success = false;
                return signal;
            }
            branchBlock.setRadius(world, pos, branchBlock.getFamily().getPrimaryThickness(), null);
            signal.radius = branchBlock.getFamily().getSecondaryThickness();
            signal.success = true;
        }
        return signal;
    }
}
