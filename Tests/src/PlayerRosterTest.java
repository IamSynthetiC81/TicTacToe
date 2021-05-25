import DynamicMemory.List;
import Players.Player;
import Players.PlayerRoster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class PlayerRosterTest extends main {
    PlayerRoster UUT;
    @BeforeEach
    void setUp() throws Exception {
        final int PLAYERS_NUMBER = 10;
        UUT = new PlayerRoster();
        Player[] players = new Player[]{
                new Player("HALL", Player.Algorithm.HALL),
                new Player("MrBean", Player.Algorithm.MrBean),
                new Player("MAKIS"),
                new Player("TAKIS"),
                new Player("FAKIS"),
                new Player("SAKIS"),
                new Player("TOTOS")

        };

        for(int i = 0 ; i < players.length ; i++) {
            UUT.addPlayer(players[i]);
        }

    }

    @Test
    void addPlayer() throws Exception {
        Player[] InvalidNames = new Player[]{
            new Player("HALL") ,            /* Name already inserted  */
            new Player("") ,                /* 0 Characters          */
        };

        for (Player invalidName : InvalidNames) {
            assertThrows(Exception.class, () -> {
                UUT.addPlayer(invalidName);
            });
        }
        Player TestPlayer = new Player("TestPlayer");
        UUT.addPlayer(TestPlayer);

        assertEquals(UUT.findByName("TestPlayer"),TestPlayer,"TestPlayer should be added in database");

    }

    @Test
    void findByNameTest() {
        String[] InvalidNames = new String[]{
                "Mark",
                "NIKO",
                "MARIO",
        };

        String[] ValidNames = new String[]{
                "HALL",
                "MrBean",
                "MAKIS",
                "TAKIS",
                "FAKIS",
                "SAKIS",
                "TOTOS"
        };

        for(int i = 0 ; i < InvalidNames.length ; i++ ){
            int finalI = i;
            assertThrows(NoSuchElementException.class,()-> {
                UUT.findByName(InvalidNames[finalI]);
            },"this should throw NoSuchElementException");
        }

        for(int i = 0 ; i < ValidNames.length ; i++ ){
            assertEquals(UUT.getPlayers().get(i), UUT.findByName(ValidNames[i]),"This should find and return players by name");
        }

    }

    @Test
    void sortPlayers() {
        List<Player> Players = UUT.getPlayers();

        Players.get(0).addWin();

        Players.get(1).addWin();
        Players.get(1).addLoss();

        Players.get(2).addWin();
        Players.get(2).addWin();

        Players.get(3).addLoss();

        Players.get(4).addTies();

        Players.get(5).addLoss();
        Players.get(5).addLoss();


        UUT.sortPlayers();

        assertAll("Asserting positions",
                ()->{
                    assertEquals( Players.get(0).getName(),"MAKIS","Player 0 was HALL, after sorting it should be MAKIS ");
                    assertEquals( Players.get(1).getName(),"HALL","Player 1 was MrBean, after sorting it should be HALL ");
                    assertEquals( Players.get(2).getName(),"MrBean","Player 2 was MAKIS, after sorting it should be MrBean ");
                    assertEquals( Players.get(3).getName(),"FAKIS","Player 3 was TAKIS, after sorting it should be FAKIS ");
                    assertEquals( Players.get(4).getName(),"SAKIS","Player 4 was FAKIS, after sorting it should be SAKIS ");
                    assertEquals( Players.get(5).getName(),"TAKIS","Player 5 was SAKIS, after sorting it should be TAKIS ");
                    assertEquals( Players.get(6).getName(),"TOTOS","Player 6 should remain in his position");
        });

    }

    @Test
    void findPlayersName() {
        String[] Names = UUT.findPlayersName();

        assertAll("Asserting names",()->{
            assertEquals("HALL",Names[0],"Should be HALL");
            assertEquals("MrBean",Names[1],"Should be MrBean");
            assertEquals("MAKIS",Names[2],"Should be MAKIS");
            assertEquals("TAKIS",Names[3],"Should be TAKIS");
            assertEquals("FAKIS",Names[4],"Should be FAKIS");
            assertEquals("SAKIS",Names[5],"Should be SAKIS");
            assertEquals("TOTOS",Names[6],"Should be TOTOS");
        });
    }

    @Test
    void findHallOfFame() {
        final int PLAYERS_NUM = 3 ;
        List<Player> Players = UUT.getPlayers();

        Players.get(0).addWin();

        Players.get(1).addWin();
        Players.get(1).addLoss();

        Players.get(2).addWin();
        Players.get(2).addWin();

        Players.get(3).addLoss();

        Players.get(4).addTies();

        Players.get(5).addLoss();
        Players.get(5).addLoss();

        List<Player> TestList = UUT.findHallOfFame(PLAYERS_NUM);

        assertAll("Asserting hall of fame",()->{
            assertEquals( TestList.get(0).getName(),"MAKIS","Player 0 was HALL, after sorting it should be MAKIS ");
            assertEquals( TestList.get(1).getName(),"HALL","Player 1 was MrBean, after sorting it should be HALL ");
            assertEquals( TestList.get(2).getName(),"MrBean","Player 2 was MAKIS, after sorting it should be MrBean ");
        });
    }
}