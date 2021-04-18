import java.util.Scanner;

public class Game {

    Board board = new Board();

    Player Player1 = new Player(+1,'X',"Player1");
    Player Player2 = new Player(-1,'O',"Player2");

    boolean isXTurn = true;

    public Game() {
        GUI ui = new GUI();
        Scanner inp = new Scanner(System.in);
        Board.Cell player = Board.Cell.X;
        while(board.GetResult() == Board.Result.Unknown){
            String in = inp.nextLine();
            String[] arr = in.split("\\.",2);
            int x = Integer.parseInt(arr[0]);
            int y = Integer.parseInt(arr[1]);
            if(x > 2 || y > 2 || x < 0 || y < 0){
                System.out.println("OUT OF BOUNDS MOTHERF@CKER");
            }else if(board.board[x][y] == Board.Cell.BLANK){
                Move move = new Move(x,y,player);
                board.Move(move);
                board.Move(AI.getBestMove(board));
                ui.printBoard(board);
            }else{
                System.out.println("Wrong moth@rf@cker");
            }
        }

    }



    static class Player{
        int _ID;
        char _c;
        String _name;

        Player(int ID, char c, String name ){
            this._ID = ID;
            this._c = c;
            this._name = name;
        }


    }

    static class GUI{
        public void printBoard(Board board){
            for(int i = 0 ; i < board.board.length ; i++ ){
                for(int j = 0 ; j < board.board[i].length ; j++){
                    if(board.board[i][j] == Board.Cell.BLANK){
                        System.out.print("-");
                    }else if(board.board[i][j] == Board.Cell.X){
                        System.out.print('X');
                    }else{
                        System.out.print('O');
                    }
                }
                System.out.println();
            }
        }
    }

}
