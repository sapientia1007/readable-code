package cleancode.minesweeper.tobe;
/* 커리큘럼 : https://docs.google.com/spreadsheets/d/1K3Pp4uVxSiySfJ6vD869lMARTa-knRCgHSMUdC4m3ZE/edit?gid=1413082304#gid=1413082304 */

import cleancode.minesweeper.tobe.gamelevel.Advanced;
import cleancode.minesweeper.tobe.gamelevel.Beginner;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.gamelevel.Middle;
import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutputHandler;
import cleancode.minesweeper.tobe.io.InputHandler;
import cleancode.minesweeper.tobe.io.OutputHandler;

public class GameApplication { // 프로그램 게임 실행 진입점
    public static void main(String[] args) {
        GameLevel gameLevel = new Beginner();

        InputHandler inputHandler = new ConsoleInputHandler();
        OutputHandler outputHandler = new ConsoleOutputHandler();

        Minesweeper minesweeper = new Minesweeper(gameLevel, inputHandler, outputHandler); // 지뢰찾기 게임 실행
        minesweeper.initialize();
        minesweeper.run();
    }
}