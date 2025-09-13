package maxhyper.dtbwg.cells.cell;

import com.dtteam.dynamictrees.systems.cell.MatrixCell;

public class SythianWartCell extends MatrixCell {

    public SythianWartCell(int value) {
        super(value, valMap);
    }

    static final byte[] valMap = {
            0, 0, 0, 0, 0, 0, 0, 0, //D Maps
            0, 0, 0, 0, 4, 0, 0, 0, //U Maps
            0, 1, 2, 3, 3, 0, 0, 0, //N Maps
            0, 1, 2, 3, 3, 0, 0, 0, //S Maps
            0, 1, 2, 3, 3, 0, 0, 0, //W Maps
            0, 1, 2, 3, 3, 0, 0, 0  //E Maps
    };

}
