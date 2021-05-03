package model;

public class Move {
    private int x;
    private int y;
    Board.Cell inp;

    boolean hasSymmetries;
    List<Move> symmetries;

    public Move(int x, int y, Board.Cell inp){
        this.x = x;
        this.y = y;
        this.inp = inp;
        symmetries = new List<>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
