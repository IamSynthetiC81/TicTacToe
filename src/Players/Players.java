package Players;

public interface Players{


    Player              HALL            = new Player("HALL", Player.Algorithm.HALL);
    Player              MrBean          = new Player("MrBean", Player.Algorithm.MrBean);

    PlayerRoster        playerRoster    = new PlayerRoster();

}
