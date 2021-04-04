import java.util.List;

public class AI {
    Element[][] _board;

    class Move{
        int x;
        int y;

        Move(){
            this.x = -1;
            this.y = -1;
        }

        Move(int x, int y){
            this.x = x;
            this.y = y;
        }

        void setMove(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    AI(Board board){
        this._board = board.getElementArray();
    }

    private List<Move> findAvailableMoves(){

        List<Move> moves = null;
        Move buffer = new Move();

        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                if (_board[i][j].isEmpty()){
                    buffer.setMove(i,j);
                    moves.add(buffer);
                }
            }
        }
        return moves;
    }

    private Move findNextMove(Player player){
        List<Move> moves = findAvailableMoves();
        for (Move move : moves) {
            Board newBoard = new Board();
            newBoard.makeMove(move,Player);
        }
    }

}
