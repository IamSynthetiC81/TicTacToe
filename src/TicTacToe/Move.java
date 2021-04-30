package TicTacToe;

import DynamicMemory.List;

public class Move{
    public int x;
    public int y;
    public Board.Cell inp;

    public boolean hasSymmetries;
    public List<Move> symmetries;

    public Move(int x, int y, Board.Cell inp){
        this.x = x;
        this.y = y;
        this.inp = inp;
        symmetries = new List<>();
    }
}
