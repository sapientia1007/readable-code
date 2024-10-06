package cleancode.minesweeper.tobe;

public class Cell {

    private static final String FLAG_SIGN = "⚑";
    private static final String LAND_MINE_SIGN = "☼";
    private static final String UNCHECKED_SIGN = "□";
    private static final String EMPTY_SIGN = "■";

    private int nearByLandMineCount;
    private boolean isLandMine;
    private boolean isFlagged;
    private boolean isOpened;

    // Cell이 가진 속성 : 근처 지뢰 숫자, 지뢰 여부
    // Cell의 상태 : 깃발 유무, 열렸다/닫혔다, 사용자가 확인함

    private Cell(int nearByLandMineCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        this.nearByLandMineCount = nearByLandMineCount;
        this.isLandMine = isLandMine;
        this.isFlagged = isFlagged;
        this.isOpened = isOpened;
    }

    public static Cell of(int nearByLandMineCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        return new Cell(nearByLandMineCount, isLandMine, isFlagged, isOpened);
    }

    public static Cell create() {
        return of(0, false, false, false);
    }

    public void turnOnLandMine() {
        this.isLandMine = true;
    }

    public void updateNearbyLandMineCount(int count) {
        this.nearByLandMineCount = count;
    }

    public void flag() {
        this.isFlagged = true; // 깃발이 꽂힌 상태
    }

    public void open() {
        this.isOpened = true;
    }

    public boolean isChecked() {
        return isFlagged || isOpened;
    }

    public boolean isLandMine() {
        return isLandMine;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public boolean hasLandMineCount() {
        return this.nearByLandMineCount != 0;
    }

    public String getSign() {
        if (isOpened) {
            if (isLandMine) return LAND_MINE_SIGN;
            if (hasLandMineCount()) return String.valueOf(nearByLandMineCount);
            return EMPTY_SIGN;
        }

        // 닫힌 쪽
        if (isFlagged) {
            return FLAG_SIGN;
        }

        return UNCHECKED_SIGN;
    }
    // getter setter은 처음부터 X

}
