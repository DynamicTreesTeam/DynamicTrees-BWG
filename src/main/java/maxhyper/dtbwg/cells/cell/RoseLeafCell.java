package maxhyper.dtbwg.cells.cell;

import com.dtteam.dynamictrees.systems.cell.MatrixCell;

public class RoseLeafCell extends MatrixCell {

    public RoseLeafCell(int value) {
        super(value, valMap);
    }

    static final byte[] valMap = {
            0, 1, 0, 3, 0, 0, 0, 3, //D Maps
            0, 1, 2, 3, 4, 5, 6, 0, //U Maps
            0, 1, 0, 3, 0, 0, 0, 7, //N Maps
            0, 1, 0, 3, 0, 0, 0, 7, //S Maps
            0, 1, 0, 3, 0, 0, 0, 7, //W Maps
            0, 1, 0, 3, 0, 0, 0, 7  //E Maps
    };

}

