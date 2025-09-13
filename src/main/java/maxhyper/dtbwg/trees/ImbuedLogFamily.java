package maxhyper.dtbwg.trees;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import com.dtteam.dynamictrees.api.lazyvalue.MutableLazyValue;
import com.dtteam.dynamictrees.api.registry.RegistryHandler;
import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.block.branch.BasicBranchBlock;
import com.dtteam.dynamictrees.block.branch.BranchBlock;
import com.dtteam.dynamictrees.block.branch.ThickBranchBlock;
import com.dtteam.dynamictrees.data.Generator;
import com.dtteam.dynamictrees.data.Generator.Dependencies;
import com.dtteam.dynamictrees.data.generator.BranchStateGenerator;
import com.dtteam.dynamictrees.data.provider.DTBlockStateProvider;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.utility.Optionals;
import com.dtteam.dynamictrees.utility.ResourceLocationUtils;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ImbuedLogFamily extends Family {

    public static final TypedRegistry.EntryType<Family> TYPE = TypedRegistry.newType(ImbuedLogFamily::new);

    protected Supplier<BranchBlock> imbuedBranch;
    protected Block primitiveImbuedLog;
    protected final MutableLazyValue<ImbuedBranchStateGenerator> imbuedBranchStateGenerator;

    public ImbuedLogFamily(ResourceLocation name) {
        super(name);
        imbuedBranchStateGenerator = MutableLazyValue.supplied(ImbuedBranchStateGenerator::new);
    }

    @Override
    public void setupBlocks() {
        super.setupBlocks();

        this.imbuedBranch = setupBranch(createImbuedBranch(getBranchName("imbued_")), false);
    }

    protected BranchBlock createImbuedBranchBlock(ResourceLocation name) {
        BasicBranchBlock branch = new ThickBranchBlock(name, this.getProperties()){
            @Override
            public Optional<Block> getPrimitiveLog() {
                if (getFamily() instanceof ImbuedLogFamily imbuedLogFamily)
                    return imbuedLogFamily.getPrimitiveImbuedLog();
                return super.getPrimitiveLog();
            }
        };
        if (this.isFireProof()) {
            branch.setFireSpreadSpeed(0).setFlammability(0);
        }

        return branch;
    }

    protected Supplier<BranchBlock> createImbuedBranch(ResourceLocation name) {
        return RegistryHandler.addBlock(ResourceLocationUtils.suffix(name, this.getBranchNameSuffix()), () -> this.createImbuedBranchBlock(name));
    }

    public Family setPrimitiveImbuedLog(Block primitiveLog) {
        this.primitiveImbuedLog = primitiveLog;
        imbuedBranch.get().setPrimitiveLogDrops(new ItemStack(primitiveLog));
        return this;
    }

    public Optional<BranchBlock> getImbuedBranch() {
        return Optionals.ofBlock(imbuedBranch.get());
    }

    public Optional<Block> getPrimitiveImbuedLog() {
        return Optionals.ofBlock(primitiveImbuedLog);
    }

    public void generateStateData(DTBlockStateProvider provider) {
        super.generateStateData(provider);
        (this.imbuedBranchStateGenerator.get()).generate(provider, this);
    }

    public void addBranchTextures(BiConsumer<String, ResourceLocation> textureConsumer, ResourceLocation primitiveLogLocation, Block sourceBlock) {
        Optional<Block> primImbued = getPrimitiveImbuedLog();
        if (primImbued.isPresent() && primImbued.get() == sourceBlock){
            ResourceLocation bark = primitiveLogLocation;
            ResourceLocation rings = ResourceLocationUtils.suffix(primitiveLogLocation, "_top");
            if (this.textureOverrides.containsKey("imbued_branch")) {
                bark = this.textureOverrides.get("imbued_branch");
            }

            if (this.textureOverrides.containsKey("imbued_branch_top")) {
                rings = this.textureOverrides.get("imbued_branch_top");
            }
            textureConsumer.accept("bark", bark);
            textureConsumer.accept("rings", rings);
            return;
        }
        super.addBranchTextures(textureConsumer, primitiveLogLocation, sourceBlock);
    }

    public static class ImbuedBranchStateGenerator extends BranchStateGenerator{
        public @NotNull Dependencies gatherDependencies(@NotNull Family input) {
            if (input instanceof ImbuedLogFamily castedInput)
                return (new Dependencies()).append(BRANCH, castedInput.getImbuedBranch()).append(PRIMITIVE_LOG, castedInput.getPrimitiveImbuedLog());
            return super.gatherDependencies(input);
        }
    }

}
