package cleancode.minesweeper.tobe.cell;

public abstract class Cell { // 추상 클래스는 인스턴스 생성 불가

    protected static final String FLAG_SIGN = "⚑";
    protected static final String UNCHECKED_SIGN = "□";

    protected boolean isFlagged;
    protected boolean isOpened;

    public void flag() {
        this.isFlagged = true; // 깃발이 꽂힌 상태
    }
    public void open() {
        this.isOpened = true;
    }
    public boolean isChecked() {
        return isFlagged || isOpened;
    }
    public abstract boolean isLandMine();
    public boolean isOpened() {
        return isOpened;
    }
    public abstract boolean hasLandMineCount() ;
    public abstract String getSign() ;
}
