import DynamicMemory.List;
import Players.Player;
import Players.Players;
import TicTacToe.Board;
import TicTacToe.Game;
import TicTacToe.GameRecord;
import TicTacToe.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

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
    void insertPlayersName() throws Exception {
        assertEquals(UUT.getName(),playerName,"Name should be given by the constructor");

        String NameTest_10_chars = "1234567890";
        UUT.insertPlayersName(NameTest_10_chars);
        assertEquals(UUT.getName(),NameTest_10_chars,"10 Char names should pass");

        String NameTest_20_chars = "12345678901234567890";
        UUT.insertPlayersName(NameTest_20_chars);
        assertEquals(UUT.getName(),NameTest_20_chars,"20 Char names should pass");
//
//        /*Names of 20+ characters can not be added by the GUI*/
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
    void betterGame() {
        Player PL1 = UUT = new Player("PL1", Player.Algorithm.HALL);
        Player PL2 =       new Player("PL2", Player.Algorithm.MrBean);

        GameRecord testRecord1 = new GameRecord(PL1,PL2);
        GameRecord testRecord2 = new GameRecord(PL1,PL2);

        /**/

        testRecord1.setState(GameRecord.State.Win);
        testRecord2.setState(GameRecord.State.Defeat);

        assertTrue(UUT.betterGame(testRecord1,testRecord2));
        /**/

        testRecord1.setState(GameRecord.State.Win);
        testRecord1.setOpponentScore(100);

        testRecord2.setState(GameRecord.State.Win);
        testRecord2.setOpponentScore(50);

        assertTrue(UUT.betterGame(testRecord1,testRecord2));

        /**/

        testRecord1.setState(GameRecord.State.Win);
        testRecord1.setOpponentScore(100);
        testRecord1.setCurrentDateTime(LocalDateTime.now());


        testRecord2.setState(GameRecord.State.Win);
        testRecord2.setOpponentScore(100);
        testRecord2.setCurrentDateTime(LocalDateTime.now().minusDays(1));

        assertTrue(UUT.betterGame(testRecord1,testRecord2));

    }

    @Test
    @DisplayName("Testing method insertBestGame")
    void testInsertBestGame() {
        Player PL1 = UUT = new Player("PL1", Player.Algorithm.HALL);
        Player PL2 =       new Player("PL2", Player.Algorithm.MrBean);

        GameRecord[] testGameRecords = new GameRecord[]{
                new GameRecord(PL1,PL2),
                new GameRecord(PL1,PL2),
                new GameRecord(PL1,PL2),
                new GameRecord(PL1,PL2),
                new GameRecord(PL1,PL2)
        };

        testGameRecords[0].setState(GameRecord.State.Tie);
        testGameRecords[1].setState(GameRecord.State.Win);

        for (int i = 0 ; i < 2 ; i++) {
            UUT.insertBestGame(testGameRecords[i]);
        }

        assertSame(testGameRecords[0], UUT.getBestGames()[1],"This game record should be seconds");
        assertSame(testGameRecords[1], UUT.getBestGames()[0],"This game record should be first");
        assertNull(UUT.getBestGames()[2],"this should be null");
        assertNull(UUT.getBestGames()[3],"this should be null");
        assertNull(UUT.getBestGames()[4],"this should be null");


        testGameRecords[2].setState(GameRecord.State.Tie);
        testGameRecords[2].setOpponentScore(30);

        testGameRecords[3].setState(GameRecord.State.Win);
        testGameRecords[3].setOpponentScore(60);

        testGameRecords[4].setState(GameRecord.State.Tie);
        testGameRecords[4].setOpponentScore(60);

        for (int i = 2 ; i < testGameRecords.length ; i++) {
            UUT.insertBestGame(testGameRecords[i]);
        }

        assertSame(testGameRecords[3], UUT.getBestGames()[0],"This game records should be first");
        assertSame(testGameRecords[1], UUT.getBestGames()[1],"This game records should be second");
        assertSame(testGameRecords[4], UUT.getBestGames()[2],"This game records should be third");
        assertSame(testGameRecords[2], UUT.getBestGames()[3],"This game records should be fourth");
        assertSame(testGameRecords[0], UUT.getBestGames()[4],"This game records should be fifth");

        GameRecord betterGameRecord = new GameRecord(PL1,PL2);

        betterGameRecord.setState(GameRecord.State.Win);
        betterGameRecord.setOpponentScore(100);

        UUT.insertBestGame(betterGameRecord);

        assertSame(betterGameRecord,   UUT.getBestGames()[0],"This game records should be first");
        assertSame(testGameRecords[3], UUT.getBestGames()[1],"This game records should be second");
        assertSame(testGameRecords[1], UUT.getBestGames()[2],"This game records should be third");
        assertSame(testGameRecords[4], UUT.getBestGames()[3],"This game records should be fourth");
        assertSame(testGameRecords[2], UUT.getBestGames()[4],"This game records should be fifth");

        for (GameRecord bestGame : UUT.getBestGames()) {
            assertNotEquals(testGameRecords[0], bestGame,"This game record is not included in the bestGames array");
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