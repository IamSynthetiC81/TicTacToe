public class Board {

    Board(Player[] players){
        this._players = players;
    }

    Board(){

    }

    private Element[][] board = new Element[3][3];

    public void makeMove(AI.Move move, Player player){

        board[move.x][move.y].setPlayer(player);
    }

    public Element[][] getElementArray(){
        return board;
    }

    public Player hasWon(){
        for (Player player : _players) {
            if(board[0][0].getPlayer() == player && board[1][1].getPlayer() == player && board[2][2].getPlayer() == player){
                return player;
            }
            if(board[0][2].getPlayer() == player && board[1][1].getPlayer() == player && board[2][0].getPlayer() == player){
                return player;
            }
            for(int i = 0 ; i < 3 ; i++ ){
                if(board[i][0].getPlayer() == player && board[i][1].getPlayer() == player && board[i][2].getPlayer() == player){
                    return player;
                }
                if(board[0][i].getPlayer() == player && board[1][i].getPlayer() == player && board[2][i].getPlayer() == player){
                    return player;
                }
            }
        }
        return null;
    }

    Player[] _players;
}

class Element{

    private Player player;

    Element(){
        this.player = null;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isEmpty(){
        return player == null;
    }
}

class Player{
    int playerID;
    char playerChar;
    boolean isHuman;

    Player(int ID, char Char, boolean isHuman){
        this.playerID = ID;
        this.playerChar = Char;
        this.isHuman = isHuman;
    }

    class Player1{
        Player1(boolean Human){
            playerID = 0;
            playerChar = 'O';
            isHuman = Human;
        }

    }
    class Player2{
        Player2(boolean human){
            playerID = 1;
            playerChar = 'X';
            isHuman = human;
        }
    }


}
