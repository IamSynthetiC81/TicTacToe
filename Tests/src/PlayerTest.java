import DynamicMemory.List;
import GUI.GameBoard.TicTacToeBoard;
import Players.Player;
import Players.Players;
import TicTacToe.Board;
import TicTacToe.Game;
import TicTacToe.GameRecord;
import TicTacToe.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest implements Players {
    private static Player UUT;
    private static final String playerName = "UUT";


    @BeforeEach
    void beforeAll() {
        UUT = new Player(playerName);
    }

    @Test
    @DisplayName("Player objects are created")
    void playerCreation(){
        assertAll(() -> {UUT = new Player(playerName);
            UUT = new Player("abcdefg");
            UUT = new Player("1234567890");
            UUT = new Player("!@#$%^&(*)");
            UUT = new Player("Ελληνικά");
            UUT = new Player("asAS12!@ας"+2);
            UUT = new Player(Integer.toString(2+2));
        });
    }

    @Test
    @DisplayName("Player object should be nameable")
    void insertPlayersName(){
        assertEquals(UUT.getName(),playerName,"Name should be given by the constructor");

        String NameTest_10_chars = "1234567890";
        UUT.insertPlayersName(NameTest_10_chars);
        assertEquals(UUT.getName(),NameTest_10_chars,"10 Char names should pass");

        String NameTest_20_chars = "12345678901234567890";
        UUT.insertPlayersName(NameTest_20_chars);
        assertEquals(UUT.getName(),NameTest_20_chars,"20 Char names should pass");

        /*Names of 20+ characters can not be added by the GUI*/
//        String NameTest_21_chars = "123456789012345678901";
//        assertThrows(InvalidPropertiesFormatException.class, () -> UUT.insertPlayersName(NameTest_21_chars));
    }

    @Test
    @DisplayName("Checks scoring")
    void scoreCalculator() {
        UUT.addWin();
        UUT.addWin();

        assertEquals(UUT.getScore(), 100,"Score should be 100%");

        UUT.addLoss();
        UUT.addLoss();

        assertEquals(UUT.getScore(), 50,"Score should be 50%");

        UUT.addTies();
        UUT.addTies();

        assertEquals(UUT.getScore(), 50,"Score should be 75%");

    }

    @Test
    @DisplayName("Check recent scoring")
    void recentScoreCalculator(){
        UUT.addWin();
        UUT.addWin();

        UUT.getRecentScore();
    }

    @Test
    @DisplayName("Test adding recent games")
    void addingRecentGames() {
        final int RECORD_NUMBER = 10;

        GameRecord[] testRecords = new GameRecord[RECORD_NUMBER];

        UUT = new Player("HALL", Player.Algorithm.HALL);
        Player PL2 = new Player("MrBean", Player.Algorithm.MrBean);
        for(int i = 0 ; i < RECORD_NUMBER ; i++ ){
            testRecords[i] = new GameRecord(UUT,PL2);
            UUT.insertRecentGame(testRecords[i]);
            if(i < 5) {
                for (int j = i; j > 0 ; j--) {
                    assertEquals(testRecords[j], UUT.getRecentGames()[j], "testRecord[" + j + "] should be in element number " + j);
                }
            }else{
                for(int j = 0 ; j < 5 ; j++ ){
                    assertEquals(testRecords[i- j], UUT.getRecentGames()[4-j], "testRecord[" +(i-j)+ "] should be in element number " +(4-j));
                }
            }
        }
    }

    @Test
    void insertBestGame() {
    }

    @Test
    void betterGame() {
        final int RECORD_NUMBER = 10;

        Player PL1 = UUT = new Player("PL1", Player.Algorithm.HALL);
        Player PL2 =       new Player("PL2", Player.Algorithm.MrBean);

        PL1.addWin();
        PL1.addWin();
        PL1.addWin();
        PL1.addWin();
        PL1.addWin();

        PL2.addWin();
        PL2.addWin();
        PL2.addWin();

        PL2.addLoss();
        PL2.addLoss();
        PL2.addLoss();

        assertAll(()->{
            assertEquals(100,PL1.getScore(),"PL1s score should be 100%");
            assertEquals(50 ,PL2.getScore(),"PL2s score should be 50%");
        });

        GameRecord[] testRecords = new GameRecord[RECORD_NUMBER];
        for(int i = 0 ; i < RECORD_NUMBER ; i++){
            testRecords[i] = new GameRecord(PL1,PL2);

//            testRecords[i].setResult();
        }

        Game game = TicTacToeBoard.newGame(PL1,PL2);
        while(game.board.GetResult() == Board.Result.Unknown){
            game.parseMove(PL1.getMove(game.board, Board.Cell.X));
            if(game.board.GetResult() == Board.Result.Unknown){
                game.parseMove(PL2.getMove(game.board, Board.Cell.O));
            }
        }
    }

    @Test
    @DisplayName("Checks HALLs Starting move")
    void getMove(){
        UUT = new Player("HALL", Player.Algorithm.HALL);
        boolean pass = false;

        List<Move> WinningMoves = new List<>();
        WinningMoves.add(new Move(0,0, Board.Cell.X));
        WinningMoves.add(new Move(2,2, Board.Cell.X));
        WinningMoves.add(new Move(0,2, Board.Cell.X));
        WinningMoves.add(new Move(2,0, Board.Cell.X));

        Move AIMove = UUT.getMove(new Board(), Board.Cell.X);
        AIMove.symmetries = null;
        AIMove.hasSymmetries = false;

        for(Move move : WinningMoves){
            if (move.x == AIMove.x & move.y == AIMove.y) {
                pass = true;
                break;
            }
        }
        assertTrue(pass);
    }

    @Test
    @DisplayName("Checks HALLs priority")
    void HallInPuzzle_1(){
        UUT = new Player("Hall", Player.Algorithm.HALL);
        Player player2 = new Player("MrBean", Player.Algorithm.MrBean);
        Game game = new Game(UUT,player2);
        game.board.board = new Board.Cell[][]{
                {Board.Cell.X, Board.Cell.X, Board.Cell.BLANK},
                {Board.Cell.BLANK, Board.Cell.BLANK, Board.Cell.BLANK},
                {Board.Cell.O, Board.Cell.O, Board.Cell.BLANK}
        };
        Move WinningMove = new Move(0,2, Board.Cell.X);
        Move AIMove = UUT.getMove(game.board, Board.Cell.X);
        assertTrue(WinningMove.x == AIMove.x && WinningMove.y == AIMove.y);

    }

    @Test
    @DisplayName("Checks HALLs response to traps (1/2)")
    void HallInTrap_1()  {
        UUT = new Player("Hall", Player.Algorithm.HALL);
        Player player1 = new Player("MrBean", Player.Algorithm.MrBean);
        Game game = new Game(player1,UUT);
        game.board.board = new Board.Cell[][]{
                {Board.Cell.X, Board.Cell.BLANK, Board.Cell.BLANK},
                {Board.Cell.O, Board.Cell.BLANK, Board.Cell.BLANK},
                {Board.Cell.O, Board.Cell.BLANK, Board.Cell.BLANK}
        };
        Move WinningMove = new Move(1,1, Board.Cell.O);
        Move AIMove = UUT.getMove(game.board, Board.Cell.O);
        assertTrue(WinningMove.x == AIMove.x && WinningMove.y == AIMove.y);
    }

    @Test
    @DisplayName("Checks HALLs response to traps (2/2)")
    void HallInTrap_2()  {
        UUT = new Player("Hall", Player.Algorithm.HALL);
        Player player1 = new Player("MrBean", Player.Algorithm.MrBean);
        Game game = new Game(player1,UUT);
        game.board.board = new Board.Cell[][]{
                {Board.Cell.BLANK, Board.Cell.BLANK, Board.Cell.BLANK},
                {Board.Cell.BLANK, Board.Cell.X, Board.Cell.BLANK},
                {Board.Cell.BLANK, Board.Cell.BLANK, Board.Cell.BLANK}
        };
        Move AIMove = UUT.getMove(game.board, Board.Cell.O);

        List<Move> WinningMoves = new List<>();
        WinningMoves.add(new Move(0,0, Board.Cell.O));
        WinningMoves.add(new Move(2,2, Board.Cell.O));
        WinningMoves.add(new Move(0,2, Board.Cell.O));
        WinningMoves.add(new Move(2,0, Board.Cell.O));

        boolean pass = false;

        for(Move move : WinningMoves){
            if (move.x == AIMove.x & move.y == AIMove.y) {
                pass = true;
                break;
            }
        }
        assertTrue(pass);

    }



}