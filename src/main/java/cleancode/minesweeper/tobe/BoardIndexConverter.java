package cleancode.minesweeper.tobe;

public class BoardIndexConverter { // CellInput이라는 String을 받아서 Index를 각각 변환해줌
    private static final char BASE_CHAR_FOR_COL = 'a';

    public int getSelectedRowIndex(String cellInput/*, int rowSize*/) {
        String cellInputRow = cellInput.substring(1);
        return convertRowFrom(cellInputRow/*, rowSize*/);
    }

    public int getSelectedColIndex(String cellInput/*, int colSize*/) {
        char cellInputCol = cellInput.charAt(0);
        return convertColFrom(cellInputCol/*, colSize*/);
    }

    private int convertRowFrom(String cellInputRow/*, int rowSize*/) { // '10'
        int rowIndex = Integer.parseInt(cellInputRow) - 1;
        if (rowIndex < 0 /*|| rowIndex >= rowSize*/) {
            throw new GameException("잘못된 입력입니다.");
        }
        return rowIndex;
    }

    private int convertColFrom(char cellInputCol/*, int colSize*/) {
        int colIndex = cellInputCol - BASE_CHAR_FOR_COL;
        if (colIndex < 0 /*|| colIndex >= colSize*/) {
            throw new GameException("잘못된 입력입니다.");
        }

        return colIndex;
    }

}
