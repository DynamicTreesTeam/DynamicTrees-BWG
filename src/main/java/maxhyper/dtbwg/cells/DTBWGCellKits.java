package maxhyper.dtbwg.cells;

import com.dtteam.dynamictrees.api.cell.Cell;
import com.dtteam.dynamictrees.api.cell.CellKit;
import com.dtteam.dynamictrees.api.cell.CellNull;
import com.dtteam.dynamictrees.api.cell.CellSolver;
import com.dtteam.dynamictrees.api.registry.Registry;
import com.dtteam.dynamictrees.api.voxmap.SimpleVoxmap;
import com.dtteam.dynamictrees.systems.cell.*;
import maxhyper.dtbwg.DynamicTreesBWG;
import maxhyper.dtbwg.cells.cell.*;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

public class DTBWGCellKits {

    public static void register(final Registry<CellKit> registry) {
        registry.registerAll(PALM, SPARSE, POPLAR, SMALL_DECIDUOUS, WILLOW, ROUND_CONIFER, SYTHIAN_FUNGUS, LAMENT, SKYRIS, ALLIUM, ROSE);
    }

    public static final CellKit PALM = new CellKit(DynamicTreesBWG.location("palm")) {

        private final Cell palmBranch = new Cell() {
            @Override
            public int getValue() {
                return 5;
            }

            @Override
            public int getValueFromSide(Direction side) {
                return side == Direction.UP ? getValue() : 0;
            }

        };

        private final Cell[] palmFrondCells = {
                CellNull.NULL_CELL,
                new PalmFrondCell(1),
                new PalmFrondCell(2),
                new PalmFrondCell(3),
                new PalmFrondCell(4),
                new PalmFrondCell(5),
                new PalmFrondCell(6),
                new PalmFrondCell(7)
        };

        private final CellKits.BasicSolver palmSolver = new CellKits.BasicSolver(new short[]{0x0514, 0x0413, 0x0312, 0x0221});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return palmFrondCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return radius == 3? palmBranch : CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return LeafClusters.PALM;
        }

        @Override
        public CellSolver getCellSolver() {
            return palmSolver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

    };

    public static final CellKit SPARSE = new CellKit(DynamicTreesBWG.location("sparse")) {

        private final Cell sparseBranch = new SparseBranchCell();
        private final Cell sparseLeaves = new NormalCell(1);

        private final CellSolver solver = new CellKits.BasicSolver(new short[] {0x0211});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return hydro > 0 ? sparseLeaves : CellNull.NULL_CELL;
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return radius == 1 ? sparseBranch : CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTBWGLeafClusters.SPARSE;
        }

        @Override
        public CellSolver getCellSolver() {
            return solver;
        }

        @Override
        public int getDefaultHydration() {
            return 1;
        }

    };

    public static final CellKit POPLAR = new CellKit(DynamicTreesBWG.location("poplar")) {

        private final Cell poplarBranch = new PoplarBranchCell();
        private final Cell poplarTopBranch = new PoplarTopBranchCell();
        private final Cell poplarUpperTrunk = new NormalCell(4);

        private final Cell[] poplarLeaves = new Cell[] {
                CellNull.NULL_CELL,
                new PoplarLeafCell(1),
                new PoplarLeafCell(2),
                new PoplarLeafCell(3),
                new PoplarLeafCell(4),
        };

        private final CellSolver solver = new CellKits.BasicSolver(new short[] {
                0x0412, 0x0311, 0x0211
        });

        @Override
        public Cell getCellForLeaves(int hydro) {
            return poplarLeaves[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            if (meta == MetadataCell.TOP_BRANCH) return poplarTopBranch;
            if (radius == 1) return poplarBranch;
            if (radius < 4) return poplarUpperTrunk;
            return CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTBWGLeafClusters.POPLAR;
        }

        @Override
        public CellSolver getCellSolver() {
            return solver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

    };

    public static final CellKit SMALL_DECIDUOUS = new CellKit(DynamicTreesBWG.location("small_deciduous")) {

        private final Cell sparseBranch = new NormalCell(4);
        private final Cell sparseLeaves = new NormalCell(1);

        private final CellSolver solver = new CellKits.BasicSolver(new short[] {0x0211});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return hydro > 0 ? sparseLeaves : CellNull.NULL_CELL;
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return radius == 1 ? sparseBranch : CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTBWGLeafClusters.SPARSE;
        }

        @Override
        public CellSolver getCellSolver() {
            return solver;
        }

        @Override
        public int getDefaultHydration() {
            return 1;
        }

    };

    // TODO: Still needs some work.
    public static final CellKit WILLOW = new CellKit(DynamicTreesBWG.location("willow")) {

        private final Cell branch = new WillowBranchCell();

        private final Cell[] willowLeafCells = {
                CellNull.NULL_CELL,
                new WillowLeafCell(1),
                new WillowLeafCell(2),
                new WillowLeafCell(3),
                new WillowLeafCell(4),
                new WillowLeafCell(5),
                new WillowLeafCell(6),
                new WillowLeafCell(7)
        };

        private final CellKits.BasicSolver solver = new CellKits.BasicSolver(new short[]{0x0817, 0x0726, 0x0625, 0x0714, 0x0614, 0x0514, 0x0413, 0x0312, 0x0211});

        @Override
        public Cell getCellForLeaves(int distance) {
            return this.willowLeafCells[distance];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return radius == 1 ? this.branch : CellNull.NULL_CELL;
        }

        @Override
        public CellSolver getCellSolver() {
            return this.solver;
        }

        // TODO: Willow leaf cluster.
        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTBWGLeafClusters.WILLOW;
        }

        @Override
        public int getDefaultHydration() {
            return 7;
        }
    };

    public static final CellKit ROUND_CONIFER = new CellKit(DynamicTreesBWG.location("round_conifer")) {

        private final Cell coniferBranch = new NormalCell(3);
        private final Cell coniferTopBranch = new ConiferTopBranchCell();

        private final Cell[] coniferLeafCells = {
                CellNull.NULL_CELL,
                new ConiferLeafCell2(1),
                new ConiferLeafCell2(2),
                new ConiferLeafCell2(3),
                new ConiferLeafCell2(4),
                new ConiferLeafCell2(5),
                new ConiferLeafCell2(6),
                new ConiferLeafCell2(7)
        };

        private final CellKits.BasicSolver coniferSolver =
                new CellKits.BasicSolver(new short[]{0x0514, 0x0413, 0x0312, 0x0221});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return coniferLeafCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            if (meta == MetadataCell.TOP_BRANCH) {
                return coniferTopBranch;
            } else if (radius == 1) {
                return coniferBranch;
            } else {
                return CellNull.NULL_CELL;
            }
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTBWGLeafClusters.ROUND_CONIFER;
        }

        @Override
        public CellSolver getCellSolver() {
            return coniferSolver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

    };

    public static final CellKit BUSHY = new CellKit(DynamicTreesBWG.location("bushy")) {

        private final Cell branchCell = new BushyBranchCell();
        private final Cell coniferTopBranch = new ConiferTopBranchCell();

        private final Cell[] coniferLeafCells = {
                CellNull.NULL_CELL,
                new BushyLeafCell(1),
                new BushyLeafCell(2),
                new BushyLeafCell(3),
                new BushyLeafCell(4),
                new BushyLeafCell(5),
                new BushyLeafCell(6),
                new BushyLeafCell(7)
        };

        private final CellKits.BasicSolver solver = new CellKits.BasicSolver(new short[]{0x0614, 0x0513, 0x0423, 0x0322, 0x0411, 0x0211});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return coniferLeafCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            if (meta == MetadataCell.TOP_BRANCH) {
                return coniferTopBranch;
            } else if (radius == 1) {
                return branchCell;
            } else {
                return CellNull.NULL_CELL;
            }
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTBWGLeafClusters.BUSHY;
        }

        @Override
        public CellSolver getCellSolver() {
            return solver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

    };

    public static final CellKit SYTHIAN_FUNGUS = new CellKit(DynamicTreesBWG.location("sythian_fungus")) {

        private final Cell sythianBranch = new SythianWartCell(3);
        private final Cell sythianTopBranch = new SythianWartCell(4);

        private final Cell[] sythianLeafCells = {
                CellNull.NULL_CELL,
                new SythianWartCell(1),
                new SythianWartCell(2),
                new SythianWartCell(3),
                new SythianWartCell(4),
                new SythianWartCell(5),
                new SythianWartCell(6),
                new SythianWartCell(7)
        };

        private final CellKits.BasicSolver sythianSolver = new CellKits.BasicSolver(new short[]{0x0411, 0x0312, 0x0221});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return sythianLeafCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            if (meta == MetadataCell.TOP_BRANCH) {
                return sythianTopBranch;
            } else if (radius == 3){
                return sythianBranch;
            } else {
                return CellNull.NULL_CELL;
            }
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTBWGLeafClusters.SYTHIAN_FUNGUS;
        }

        @Override
        public CellSolver getCellSolver() {
            return sythianSolver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

    };

    public static final CellKit LAMENT = new CellKit(DynamicTreesBWG.location("lament")) {

        private final Cell lamentBranch = new Cell() {
            @Override
            public int getValue() {
                return 5;
            }

            final int[] map = {0, 2, 4, 4, 4, 4};

            @Override
            public int getValueFromSide(Direction side) {
                return map[side.ordinal()];
            }

        };

        private final Cell[] lamentLeafCells = {
                CellNull.NULL_CELL,
                new LamentLeafCell(1),
                new LamentLeafCell(2),
                new LamentLeafCell(3),
                new LamentLeafCell(4),
                new LamentLeafCell(5),
                new LamentLeafCell(6),
                new LamentLeafCell(7)
        };

        private final CellKits.BasicSolver lamentSolver = new CellKits.BasicSolver(new short[]{0x0413, 0x0322, 0x0311, 0x0211});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return lamentLeafCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return radius == 1 ? lamentBranch : CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTBWGLeafClusters.LAMENT;
        }

        @Override
        public CellSolver getCellSolver() {
            return lamentSolver;
        }

        @Override
        public int getDefaultHydration() {
            return 3;
        }

    };

    public static final CellKit SKYRIS = new CellKit(DynamicTreesBWG.location("skyris")) {
        private final Cell skyrisBranch = new Cell() {
            final int[] map = new int[]{0, 3, 5, 5, 5, 5};

            public int getValue() {
                return 5;
            }

            public int getValueFromSide(Direction side) {
                return this.map[side.ordinal()];
            }
        };
        private final Cell coniferTopBranch = new ConiferTopBranchCell();
        private final Cell[] skyrisLeafCells;
        private final CellKits.BasicSolver skyrisSolver;

        {
            this.skyrisLeafCells = new Cell[]{CellNull.NULL_CELL, new AcaciaLeafCell(1), new AcaciaLeafCell(2), new AcaciaLeafCell(3), new AcaciaLeafCell(4), new AcaciaLeafCell(5), new AcaciaLeafCell(6), new AcaciaLeafCell(7)};
            this.skyrisSolver = new CellKits.BasicSolver(new short[]{1300, 1059, 1042, 786, 529});
        }

        public Cell getCellForLeaves(int hydro) {
            return this.skyrisLeafCells[hydro];
        }

        public Cell getCellForBranch(int radius, int meta) {
            if (meta == 1) {
                return this.coniferTopBranch;
            } else {
                return radius == 1 ? this.skyrisBranch : CellNull.NULL_CELL;
            }
        }

        public SimpleVoxmap getLeafCluster() {
            return LeafClusters.ACACIA;
        }

        public CellSolver getCellSolver() {
            return this.skyrisSolver;
        }

        public int getDefaultHydration() {
            return 4;
        }
    };

    public static final CellKit ALLIUM = new CellKit(DynamicTreesBWG.location("allium")) {

        private final Cell alliumBranch = new NormalCell(4);

        private final Cell[] alliumLeafCells = {
                CellNull.NULL_CELL,
                new AlliumLeafCell(1),
                new AlliumLeafCell(2),
                new AlliumLeafCell(3),
                new AlliumLeafCell(4),
                new AlliumLeafCell(5),
                new AlliumLeafCell(6),
                new AlliumLeafCell(7)
        };

        private final CellKits.BasicSolver alliumSolver = new CellKits.BasicSolver(new short[]{0x0413, 0x0322, 0x0311, 0x0211});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return alliumLeafCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return radius == 3 ? alliumBranch : CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTBWGLeafClusters.ALLIUM;
        }

        @Override
        public CellSolver getCellSolver() {
            return alliumSolver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

    };

    public static final CellKit ROSE = new CellKit(DynamicTreesBWG.location("rose")) {

        private final Cell roseBranch = new Cell() {
            final int[] map = new int[]{0, 3, 8, 8, 8, 8};

            public int getValue() {
                return 8;
            }

            public int getValueFromSide(Direction side) {
                return this.map[side.ordinal()];
            }
        };

        private final Cell[] roseLeafCells = {
                CellNull.NULL_CELL,
                new RoseLeafCell(1),
                new RoseLeafCell(2),
                new RoseLeafCell(3),
                new RoseLeafCell(4),
                new RoseLeafCell(5),
                new RoseLeafCell(6),
                new RoseLeafCell(7)
        };

        private final CellKits.BasicSolver roseSolver = new CellKits.BasicSolver(new short[]{0x0817, 0x0726, 0x0715, 0x0514, 0x0613, 0x0311, 0x0412, 0x0211});
        @Override
        public Cell getCellForLeaves(int hydro) {
            return roseLeafCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return radius == 3 ? roseBranch : CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTBWGLeafClusters.ROSE;
        }

        @Override
        public CellSolver getCellSolver() {
            return roseSolver;
        }

        @Override
        public int getDefaultHydration() {
            return 7;
        }

    };

}
