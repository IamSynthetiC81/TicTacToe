package Players;

import DynamicMemory.List;
import Players.Player;

import javax.swing.*;
import java.util.NoSuchElementException;

public class PlayerRoster {
    List<Player> allPlayers;

    public PlayerRoster() {
        allPlayers = new List<>();
    }

    public Player findByName(String name){
        for (Player player : allPlayers){
            if(player.getName().equals(name)){
                return player;
            }
        }
        throw new NoSuchElementException("Player " +name+ " does not exist in database");
    }

    public void addPlayer(Player player) {
        for(int i = 0; i < allPlayers.size(); i++)
            if(allPlayers.get(i).name.equals(player.name)){
                JOptionPane.showMessageDialog(null, "Player "+player.getName()+" is already inserted!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
        if(player.name == "") {
            JOptionPane.showMessageDialog(null, "Insert a valid name!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        else
            allPlayers.add(player);
    }

    public void sortPlayers() {
        Player tempPlayer;

        for(int i = 0; i < allPlayers.size() - 1; i++)
            for(int j = i + 1; j < allPlayers.size(); j++)
                if(allPlayers.get(i).getScore() < allPlayers.get(j).getScore())
                    allPlayers.changeOrder(i, j);

    }

    public String[] findPlayersName() {
        String[] allNames = new String[allPlayers.size()];

        for(int i = 0; i < allPlayers.size(); i++)
            allNames[i] = allPlayers.get(i).name;

        return allNames;
    }

    public Player findPlayer(String name) {
        for(int i = 0; i < allPlayers.size(); i++)
            if(allPlayers.get(i).name.equals(name))
                return allPlayers.get(i);

        System.out.println("No player found with this name.");
        return null;
    }

    public List<Player> getPlayers(){
        return allPlayers;
    }

    public List<Player> findHallOfFame(int n) {

        if(/*n >= allPlayers.size() || */n < 0){
            throw new IndexOutOfBoundsException("Index " + n + ", Size " + n);
        }

        sortPlayers();

        List<Player> HallOfFame = new List<Player>();

        for(int i = 0; i < (n > allPlayers.size() ? allPlayers.size() : n); i++) {
            if (allPlayers.get(i) != null) {
                HallOfFame.add(allPlayers.get(i));
            }
        }
        return HallOfFame;

    }
}
