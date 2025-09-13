package maxhyper.dtbwg.blocks;

import com.dtteam.dynamictrees.tree.TreeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.potionstudios.biomeswevegone.world.level.block.plants.tree.branch.TreeBranchBlock;

public class DynamicWitchHazelBranch extends TreeBranchBlock {

    private final Block baseBlock;
    public DynamicWitchHazelBranch(Block baseBlock) {
        super();
        this.baseBlock = baseBlock;
    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return super.canSurvive(state, level, pos) || TreeHelper.isBranch(level.getBlockState(pos.relative(state.getValue(FACING).getOpposite())));
    }

    @Override
    public Item asItem() {
        return baseBlock.asItem();
    }

}
