package cleancode.minesweeper.tobe.cell;

public interface Cell { // 추상 클래스는 인스턴스 생성 불가

    String FLAG_SIGN = "⚑";
    String UNCHECKED_SIGN = "□";

    void flag();

    void open() ;
    boolean isChecked() ;
    boolean isOpened() ;
    boolean isLandMine();
    boolean hasLandMineCount() ;
    String getSign() ;
}
