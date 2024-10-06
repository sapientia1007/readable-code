package cleancode.minesweeper.tobe.gamelevel;

public interface GameLevel { // 추상화를 direct로 표현
    int getRowSize();

    int getColSize();

    int getLandMineCount();

}
