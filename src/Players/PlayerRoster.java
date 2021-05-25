package Players;

import DynamicMemory.List;

import javax.swing.*;
import java.io.*;
import java.util.NoSuchElementException;

public class PlayerRoster implements Players{
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
            if(allPlayers.get(i).getName().equals(player.getName())){
                JOptionPane.showMessageDialog(null, "Player "+player.getName()+" is already inserted!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
        if(player.getName() == "") {
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
            allNames[i] = allPlayers.get(i).getName();

        return allNames;
    }

    public Player findPlayer(String name) {
        for(int i = 0; i < allPlayers.size(); i++)
            if(allPlayers.get(i).getName().equals(name))
                return allPlayers.get(i);

        System.out.println("No player found with this name.");
        return null;
    }

    public List<Player> getPlayers(){
        return allPlayers;
    }

    public void storePlayers() {
        ObjectOutputStream os = null;
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream("tuctactoe.ser");
            os = new ObjectOutputStream(fos);

            for(Player player : allPlayers){
                os.writeObject(player);
            }
            System.out.println("All players stored successfully.");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {os.close(); fos.close();} catch (Exception e){
            }
        }
    }

    public void loadPlayers() {
        ObjectInputStream is = null;
        FileInputStream fis = null;
        try{
            fis = new FileInputStream("tuctactoe.ser");
            is = new ObjectInputStream(fis);

            while(fis.available() > 0) {
                Player player = (Player) is.readObject();
                this.allPlayers.add(player);
            }

            System.out.println(allPlayers.size() + " players loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found!");
        } finally {
            try{is.close(); fis.close();} catch (Exception e) {
            }
        }


        boolean HALL_Present = false;
        boolean MrBean_Present = false;
        for(Player player : allPlayers){
            if(player.getName().equals(HALL.getName())){
                HALL_Present = true;
            }
            if(player.getName().equals(MrBean.getName())){
                MrBean_Present = true;
            }
        }
        if(!HALL_Present){
            this.addPlayer(HALL);
        }if(!MrBean_Present){
            addPlayer(MrBean);
        }
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
