import g38496.tournament.business.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class MainTournamentTest {

    /* addPlayer() */

    @Test
    public void addPlayer_case1() {
        MainTournament mainTournament = new MainTournament();
        Player player = new Player("player");
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Player> expectedPlayers = new ArrayList<>();

        mainTournament.openInscription();

        expectedPlayers.add(player);
        assertTrue(mainTournament.addPlayer(player));

        players = mainTournament.getPlayers();

        assertEquals(expectedPlayers.size(), players.size());

        for (int i = 0; i < players.size(); i++) {
            assertEquals(expectedPlayers.get(i), players.get(i));
        }
    }

    @Test
    public void addPlayer_case2() {
        MainTournament mainTournament = new MainTournament();
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Player> expectedPlayers = new ArrayList<>();

        mainTournament.openInscription();
        
        for (int i = 0; i < (Config.PLAYER_MAX_NUMBER - 1); i++) {
            expectedPlayers.add(new Player("player"));
        }
        for (int i = 0; i < (Config.PLAYER_MAX_NUMBER - 1); i++) {
            mainTournament.addPlayer(expectedPlayers.get(i));
        }

        players = mainTournament.getPlayers();

        assertEquals(expectedPlayers.size(), players.size());
    }

    @Test
    public void addPlayer_case3() {
        MainTournament mainTournament = new MainTournament();
        mainTournament.openInscription();
        for (int i = 0; i < (Config.PLAYER_MAX_NUMBER); i++) {
            mainTournament.addPlayer(new Player("player"));
        }
        assertFalse(mainTournament.addPlayer(new Player("player")));
    }

    // no addPlayer_case4()

    @Test
    public void addPlayer_case5() {
        MainTournament mainTournament = new MainTournament();
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Player> expectedPlayers = new ArrayList<>();
        
        mainTournament.openInscription();
        for (int i = 0; i < 10; i++) {
            expectedPlayers.add(new Player("player"));
        }
        for (int i = 0; i < 10; i++) {
            mainTournament.addPlayer(expectedPlayers.get(i));
        }
        mainTournament.closeInscription();
        
        assertFalse(mainTournament.addPlayer(new Player("player")));
        players = mainTournament.getPlayers();
        assertEquals(expectedPlayers.size(), players.size());
    }
    
    @Test
    public void addPlayer_case6() {
        MainTournament mainTournament = new MainTournament();
        mainTournament.openInscription();
        assertFalse(mainTournament.addPlayer(null));
    }

    /* removePlayer() */

    @Test
    public void removePlayer_case1() {
        MainTournament mainTournament = new MainTournament();
        mainTournament.openInscription();
        assertFalse(mainTournament.removePlayer(new Player("player")));
    }

    @Test
    public void removePlayer_case2() {
        MainTournament mainTournament = new MainTournament();
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Player> expectedPlayers = new ArrayList<>();
        
        mainTournament.openInscription();
        for (int i = 0; i < 10; i++) {
            expectedPlayers.add(new Player("player"));
        }
        for (int i = 0; i < 10; i++) {
            mainTournament.addPlayer(expectedPlayers.get(i));
        }

        assertTrue(mainTournament.removePlayer(expectedPlayers.get(0)));

        players = mainTournament.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            assertEquals(expectedPlayers.get(i + 1), players.get(i));
        }
    }

    @Test
    public void removePlayer_case3() {
        MainTournament mainTournament = new MainTournament();
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Player> expectedPlayers = new ArrayList<>();
        
        mainTournament.openInscription();
        for (int i = 0; i < 10; i++) {
            expectedPlayers.add(new Player("player"));
        }
        for (int i = 0; i < 10; i++) {
            mainTournament.addPlayer(expectedPlayers.get(i));
        }
        
        assertTrue(mainTournament.removePlayer(expectedPlayers.get(
                expectedPlayers.size() - 1)));

        players = mainTournament.getPlayers();
        for (int i = 0; i < players.size() - 1; i++) {
            assertEquals(expectedPlayers.get(i), players.get(i));
        }
    }

    @Test
    public void removePlayer_case4() {
        MainTournament mainTournament = new MainTournament();
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Player> expectedPlayers = new ArrayList<>();
        
        mainTournament.openInscription();
        for (int i = 0; i < 10; i++) {
            expectedPlayers.add(new Player("player"));
        }
        for (int i = 0; i < 10; i++) {
            mainTournament.addPlayer(expectedPlayers.get(i));
        }

        assertTrue(mainTournament.removePlayer(expectedPlayers.get(0)));

        players = mainTournament.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            assertEquals(expectedPlayers.get(i + 1), players.get(i));
        }
    }
    
    @Test
    public void removePlayer_case5() {
        MainTournament mainTournament = new MainTournament();
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Player> expectedPlayers = new ArrayList<>();
        
        mainTournament.openInscription();
        for (int i = 0; i < 10; i++) {
            expectedPlayers.add(new Player("player"));
        }
        for (int i = 0; i < 10; i++) {
            mainTournament.addPlayer(expectedPlayers.get(i));
        }
        mainTournament.closeInscription();

        assertFalse(mainTournament.removePlayer(expectedPlayers.get(5)));

        players = mainTournament.getPlayers();
        assertEquals(expectedPlayers.size(), players.size());
    }

    @Test
    public void removePlayer_case6() {
        MainTournament mainTournament = new MainTournament();
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Player> expectedPlayers = new ArrayList<>();
        
        mainTournament.openInscription();
        for (int i = 0; i < 10; i++) {
            expectedPlayers.add(new Player("player"));
        }
        for (int i = 0; i < 10; i++) {
            mainTournament.addPlayer(expectedPlayers.get(i));
        }

        assertFalse(mainTournament.removePlayer(null));

        players = mainTournament.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            assertEquals(expectedPlayers.get(i), players.get(i));
        }
    }

    /* setTurnResult() */
/*
    @Test(expected=TournamentException.class)
    public void setTurnResult_case1() throws TournamentException {
        MainTournament mainTournament = new MainTournament();
        
        mainTournament.openInscription();
        for (int i = 0; i < 10; i++) {
            mainTournament.addPlayer(new Player("player"));
        }
        mainTournament.closeInscription();

        mainTournament.setTurnResult(-5, ResultEnum.PLAYER1);
    }

    @Test
    public void setTurnResult_case2() throws TournamentException {
        MainTournament mainTournament = new MainTournament();
        int matchId;
        
        mainTournament.openInscription();
        for (int i = 0; i < 10; i++) {
            mainTournament.addPlayer(new Player("player"));
        }
        mainTournament.closeInscription();

        matchId = mainTournament.getMatchs().get(2).getId();
        mainTournament.setTurnResult(matchId, ResultEnum.PLAYER1);

        assertEquals(ResultEnum.PLAYER1,
                mainTournament.getMatchs().get(2).getResult());
    }

    @Test
    public void setTurnResult_case3() throws TournamentException {
        MainTournament mainTournament = new MainTournament();
        int matchId;
        
        mainTournament.openInscription();
        for (int i = 0; i < 10; i++) {
            mainTournament.addPlayer(new Player("player"));
        }
        mainTournament.closeInscription();

        matchId = mainTournament.getMatchs().get(2).getId();
        mainTournament.setTurnResult(matchId, ResultEnum.PLAYER2);

        assertEquals(ResultEnum.PLAYER2,
                mainTournament.getMatchs().get(2).getResult());
    }

    @Test(expected=TournamentException.class)
    public void setTurnResult_case4() throws TournamentException {
        MainTournament mainTournament = new MainTournament();
        int matchId;
        
        mainTournament.openInscription();
        for (int i = 0; i < 10; i++) {
            mainTournament.addPlayer(new Player("player"));
        }
        mainTournament.closeInscription();

        matchId = mainTournament.getMatchs().get(2).getId();
        mainTournament.setTurnResult(matchId, ResultEnum.DRAW);
    }

    @Test(expected=NullPointerException.class)
    public void setTurnResult_case5() throws TournamentException, 
            NullPointerException {
        MainTournament mainTournament = new MainTournament();
        int matchId;
        
        mainTournament.openInscription();
        for (int i = 0; i < 10; i++) {
            mainTournament.addPlayer(new Player("player"));
        }
        mainTournament.closeInscription();

        matchId = mainTournament.getMatchs().get(2).getId();
        mainTournament.setTurnResult(matchId, null);
    }

    @Test(expected=TournamentException.class)
    public void setTurnResult_case6() throws TournamentException {
        MainTournament mainTournament = new MainTournament();
        int matchId;
        
        mainTournament.openInscription();
        for (int i = 0; i < 10; i++) {
            mainTournament.addPlayer(new Player("player"));
        }
        mainTournament.closeInscription();

        matchId = mainTournament.getMatchs().get(2).getId();
        mainTournament.setTurnResult(matchId, ResultEnum.NOT_PLAYED);
    }
*/

    /* getMatchs() */
/*
    @Test
    public void getMatchs_case1() {
        MainTournament mainTournament = new MainTournament();

        mainTournament.openInscription();
        for (int i = 0; i < 6; i++) {
            mainTournament.addPlayer(new Player("player"));
        }
        mainTournament.closeInscription();

        assertEquals(3, mainTournament.getMatchs().size());
    }

    @Test
    public void getMatchs_case2() {
        MainTournament mainTournament = new MainTournament();

        mainTournament.openInscription();
        for (int i = 0; i < 9; i++) {
            mainTournament.addPlayer(new Player("player"));
        }
        mainTournament.closeInscription();

        assertEquals(4, mainTournament.getMatchs().size());
    }

    @Test
    public void getMatchs_case3() {
        MainTournament mainTournament = new MainTournament();

        mainTournament.openInscription();
        mainTournament.addPlayer(new Player("player"));
        mainTournament.closeInscription();

        assertEquals(0, mainTournament.getMatchs().size());
    }

    @Test
    public void getMatchs_case4() throws TournamentException {
        MainTournament mainTournament = new MainTournament();
        int matchId;

        mainTournament.openInscription();
        for (int i = 0; i < 9; i++) {
            mainTournament.addPlayer(new Player("player"));
        }
        mainTournament.closeInscription();

        matchId = mainTournament.getMatchs().get(0).getId();

        mainTournament.setTurnResult(matchId, ResultEnum.PLAYER1);
        mainTournament.setTurnResult(matchId + 1, ResultEnum.PLAYER1);

        assertEquals(4, mainTournament.getMatchs().size());
    }
*/
    /* getMatchsDone() */
/*
    @Test
    public void getMatchsDone_case1() {
        MainTournament mainTournament = new MainTournament();

        mainTournament.openInscription();
        for (int i = 0; i < 6; i++) {
            mainTournament.addPlayer(new Player("player"));
        }
        mainTournament.closeInscription();

        assertEquals(0, mainTournament.getMatchsDone().size());
    }

    @Test
    public void getMatchsDone_case2() throws TournamentException {
        MainTournament mainTournament = new MainTournament();
        int matchId;

        mainTournament.openInscription();
        for (int i = 0; i < 6; i++) {
            mainTournament.addPlayer(new Player("player"));
        }
        mainTournament.closeInscription();

        matchId = mainTournament.getMatchs().get(0).getId();

        mainTournament.setTurnResult(matchId, ResultEnum.PLAYER1);
        mainTournament.setTurnResult(matchId + 1, ResultEnum.PLAYER1);

        assertEquals(2, mainTournament.getMatchsDone().size());
    }

    @Test
    public void getMatchsDone_case3() throws TournamentException {
        MainTournament mainTournament = new MainTournament();
        int matchId;

        mainTournament.openInscription();
        for (int i = 0; i < 7; i++) {
            mainTournament.addPlayer(new Player("player"));
        }
        mainTournament.closeInscription();
        
        matchId = mainTournament.getMatchs().get(0).getId();

        mainTournament.setTurnResult(matchId, ResultEnum.PLAYER1);
        mainTournament.setTurnResult(matchId + 1, ResultEnum.PLAYER1);
        mainTournament.setTurnResult(matchId + 2, ResultEnum.PLAYER1);


        assertEquals(3, mainTournament.getMatchsDone().size());
    }
*/
    /* getMatchsToPlay() */
/*
    @Test
    public void getMatchsToPlay_case1() {
        MainTournament mainTournament = new MainTournament();

        mainTournament.openInscription();
        for (int i = 0; i < 6; i++) {
            mainTournament.addPlayer(new Player("player"));
        }
        mainTournament.closeInscription();

        assertEquals(3, mainTournament.getMatchsToPlay().size());
    }

    @Test
    public void getMatchsToPlay_case2() throws TournamentException {
        MainTournament mainTournament = new MainTournament();
        int matchId;

        mainTournament.openInscription();
        for (int i = 0; i < 6; i++) {
            mainTournament.addPlayer(new Player("player"));
        }
        mainTournament.closeInscription();

        matchId = mainTournament.getMatchs().get(0).getId();

        mainTournament.setTurnResult(matchId, ResultEnum.PLAYER1);

        assertEquals(2, mainTournament.getMatchsToPlay().size());
    }

    @Test
    public void getMatchsToPlay_case3() throws TournamentException {
        MainTournament mainTournament = new MainTournament();
        int matchId;

        mainTournament.openInscription();
        for (int i = 0; i < 7; i++) {
            mainTournament.addPlayer(new Player("player"));
        }
        mainTournament.closeInscription();

        matchId = mainTournament.getMatchs().get(0).getId();

        mainTournament.setTurnResult(matchId, ResultEnum.PLAYER1);
        mainTournament.setTurnResult(matchId + 1, ResultEnum.PLAYER1);
        mainTournament.setTurnResult(matchId + 2, ResultEnum.PLAYER1);

        assertEquals(0, mainTournament.getMatchsToPlay().size());
    }
*/
}
