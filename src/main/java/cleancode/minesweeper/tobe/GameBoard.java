package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.cell.*;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.position.CellPosition;
import cleancode.minesweeper.tobe.position.RelativePosition;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class GameBoard {
    private static Cell[][] board ;
    private final int landMineCount;

    public GameBoard(GameLevel gameLevel){
        int rowSize = gameLevel.getRowSize();
        int colSize = gameLevel.getColSize();
        board = new Cell[rowSize][colSize];

        landMineCount = gameLevel.getLandMineCount();
    }
    public void flagAt(CellPosition cellPosition) {
        Cell cell = findCell(cellPosition);
        cell.flag();
    }


    public boolean isInvalidCellPosition(CellPosition cellPosition) {
        int rowSize = getRowSize();
        int colSize = getColSize();

        return cellPosition.isRowIndexMoreThanOrEqual(rowSize)
                || cellPosition.isColIndexMoreThanOrEqual(colSize);
    }

    public void initializeGame() {
        int rowSize = getRowSize(); int colSize = getColSize();
        for (int row = 0; row < rowSize; row++) { // 단순 i, j가 아닌 row, col로 변경
            for (int col = 0; col < colSize; col++) {
                board[row][col] = new EmptyCell();
            }
        }

        for (int i = 0; i < landMineCount; i++) {
            int landMineCol = new Random().nextInt(colSize);
            int landMineRow = new Random().nextInt(rowSize);
//            LandMineCell landMineCell = new LandMineCell();
//            landMineCell.turnOnLandMine();
            board[landMineRow][landMineCol] = new LandMineCell();
        }

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                CellPosition cellPosition = CellPosition.of(row, col);
                if (isLandMineCellAt(cellPosition)) {
                    continue;
                }
                int count = countNearbyLandMines(cellPosition);
                if (count == 0) continue;
                board[row][col] = new NumberCell(count);
            }
        }
    }

    public boolean isAllCellChecked() {
        return Arrays.stream(board) // Stream<String[]>
                .flatMap(Arrays::stream) // Stream<String[]>
                .allMatch(Cell::isChecked);
//                .noneMatch(CLOSED_CELL_SIGN::equals);  // 닫힌 셀이 하나도 없으면 다 열린것 => 상수를 조건으로 해서 안전하게 수정 (cell -> CLOSED_CELL_SIGN.equals(cell))
    }
    public void openAt(CellPosition cellPosition) {
        Cell cell = findCell(cellPosition);
        cell.open();
    }

    public void openSurroundedCells(CellPosition cellPosition) {
        if (cellPosition.isRowIndexMoreThanOrEqual(getRowSize())
                || cellPosition.isColIndexMoreThanOrEqual(getColSize())) {
            return;
        }
        if (isOpenedCell(cellPosition)) {
            return;
        }
        if (isLandMineCellAt(cellPosition)) {
            return;
        }

        openAt(cellPosition);

        if (doesCellHaveLandMineCount(cellPosition)) {
            return;
        }

        List<CellPosition> surroundedPositions = calculateSurroundedPositions(cellPosition, getRowSize(), getColSize());
        surroundedPositions.forEach(this::openSurroundedCells);

        for (RelativePosition relativePosition : RelativePosition.SURROUNDED_POSITIONS) {
            if (canMovePosition(cellPosition, relativePosition)) {
                CellPosition nextCellPosition = cellPosition.calculatePositionBy(relativePosition);
                openSurroundedCells(nextCellPosition);
            }
        }
    }

    private boolean canMovePosition(CellPosition cellPosition, RelativePosition relativePosition) {
        return cellPosition.canCalculatePositionBy(relativePosition);
    }

    private boolean doesCellHaveLandMineCount(CellPosition cellPosition) {
        return findCell(cellPosition).hasLandMineCount();
    }

    private boolean isOpenedCell(CellPosition cellPosition) {
        Cell cell = findCell(cellPosition);
        return cell.isOpened();
    }

    public String getSign(CellPosition cellPosition) {
        Cell cell = findCell(cellPosition) ;
        return cell.getSign();
    }

    private Cell findCell(CellPosition cellPosition){
        return board[cellPosition.getRowIndex()][cellPosition.getColIndex()];
    }

    public int getRowSize() {
        return board.length;
    }
    public int getColSize() {
        return board[0].length;
    }
    public int countNearbyLandMines(CellPosition cellPosition) {
        int rowSize = getRowSize(); int colSize = getColSize();

        long count = calculateSurroundedPositions(cellPosition, rowSize, colSize).stream()
                .filter(this::isLandMineCellAt)
                .count();
        return (int) count;
    }

    private List<CellPosition> calculateSurroundedPositions(CellPosition cellPosition, int rowSize, int colSize) {
        return RelativePosition.SURROUNDED_POSITIONS.stream()
                .filter(cellPosition::canCalculatePositionBy)
                .map(cellPosition::calculatePositionBy)
                .filter(position -> position.isRowIndexLessThan(rowSize))
                .filter(position -> position.isColIndexLessThan(colSize))
                .toList();
    }


    public boolean isLandMineCellAt(CellPosition cellPosition) {
        Cell cell = findCell(cellPosition);
        return cell.isLandMine();
    }


}
