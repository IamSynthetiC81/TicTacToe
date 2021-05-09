package Players;

import GUI.Panels.Panels;
import ΑΙ.AI;

public interface Players{


    Player              HALL            = new Player("HALL", AI.Algorithm.HALL);
    Player              MrBean          = new Player("MrBean", AI.Algorithm.MrBean);

    PlayerRoster        playerRoster    = new PlayerRoster();

}
