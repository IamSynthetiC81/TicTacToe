public class AI {

    public static Board.Cell human = Board.Cell.X;
    public static Board.Cell ai = Board.Cell.O;

    public static Move getBestMove(Board board){
        Move bestMove = null;
        int bestScore = -100;
        for(Move move : board.findAvailableMoves(ai)){
            board.Move(move);
            int score = minimax(board);
            board.clearMove(move);
            if(score > bestScore){
                bestScore = score;
                bestMove = move;
            }
        }
    return bestMove;
    }

    public static int minimax (Board board) {
        Board.Result winner = board.GetResult();
        if (winner != null) {
            if(winner == Board.Result.OWins){
                return 1;
            }else if (winner == Board.Result.XWins){
                return -1;
            }else if (winner == Board.Result.Tie){
                return 0;
            }
        }

        int aiCount = 0;
        int humanCount = 0;
        for(int i = 0 ; i < board.board.length ; i++ ){
            for ( int j = 0 ; j < board.board[i].length ; j++ ){
                if(board.board[i][j] == ai){
                    aiCount++;
                }else if(board.board[i][j] == human){
                    humanCount++;
                }
            }
        }

        int bestScore;
        if (humanCount > aiCount) {
            bestScore = -1;
        }else{
            bestScore = 1;
        }

        for(Move move : board.findAvailableMoves(humanCount > aiCount ? ai : human)){
            board.Move(move);
            int currentScore = minimax(board);
            board.clearMove(move);
            if (humanCount > aiCount ? currentScore > bestScore : currentScore < bestScore) {
                bestScore = currentScore;
            }
        }
        return bestScore;
    }
}

//class Node{
//    int player = 1;
//    Node[] _children;
//    Board _board;
//    Game.Move _move;
//    Board.Result _boardOutlook;
//    int mark;
//
//    Node(){
//        _board = new Board();
//        _boardOutlook = _board.GetResult();
//        _move = null;
//
//        List<Game.Move> _moves = _board.findAvailableMoves(player);
//        _children = new Node[_moves.size()];
//
//        if(_boardOutlook == Board.Result.Unknown){
//            for(int i = 0; i < _moves.size() ; i++) {
//                _children[i] = new Node(_board,_moves.get(i),player*(-1));
//            }
//        }
//    }
//
//    Node(Board board,Game.Move move,int player){
//        _board = new Board();
//        _move = move;
//
//        for(int i = 0 ; i < board.board.length ; i++ ){
//            for(int j = 0 ; j < board.board[i].length ; j++ ){
//                _board.board[i][j] = board.board[i][j];
//            }
//        }
//        _board.makeMove(_move);
//        _boardOutlook = board.GetResult();
//
//        List<Game.Move> _moves = board.findAvailableMoves(player);
//        _children = new Node[_moves.size()];
//
//        if(board.GetResult() == Board.Result.Unknown){
//             for(Game.Move eachMove : _moves){
//                int i = _moves.indexOf(eachMove);
//                _children[i] = new Node(_board,eachMove,player*(-1));
//
//                if (_children[i]._boardOutlook == Board.Result.OWins) {
//                    _children[i].mark = -1;
//                } else if (_children[i]._boardOutlook == Board.Result.XWins) {
//                    _children[i].mark = 1;
//                } else {
//                    _children[i].mark = 0;
//                }
//            }
//        }
////        for(Node child : _children){
////            if(player == 1){
////
////            }
////        }
//
//    }
//
//
//}