package TicTacToe;

import DynamicMemory.List;

public class PlayerRoster {
    List<Player> allPlayers;

    public PlayerRoster() {
        allPlayers = new List<>();
    }

    public void addPlayer(Player player) {
        for(int i = 0; i < allPlayers.size(); i++)
            if(allPlayers.get(i).name.equals(player.name)){
                System.out.println("This player is already inserted!");
                return;
            }

        allPlayers.add(player);
    }

    public void sortPlayers() {
        Player tempPlayer;

        for(int i = 0; i < allPlayers.size() - 1; i++)
            for(int j = i + 1; j < allPlayers.size(); j++)
                if(allPlayers.get(i).score < allPlayers.get(j).score)
                    allPlayers.changeOrder(i, j);

    }

//   public void findPlayersName() {
//      // for(int i = 0; i < allPlayers.size(); i++)

//   }

    public Player findPlayer(String name) {
        for(int i = 0; i < allPlayers.size(); i++)
            if(allPlayers.get(i).name.equals(name))
                return allPlayers.get(i);

        System.out.println("No player found with this name.");
        return null;
    }

    public void findHallOfFame(int n) {

        if(n >= allPlayers.size() || n < 0){
            throw new IndexOutOfBoundsException("Index " + n + ", Size " + n);
        }

        sortPlayers();

        for(int i = 0; i < n; i++)
            if(allPlayers.get(i) != null)
                System.out.println(i + ". " + allPlayers.get(i).name + " " + allPlayers.get(i).score + " " + allPlayers.get(i).gamesNum);

    }
}
