package ΑΙ;

import DynamicMemory.List;
import TicTacToe.BoardHandler;
import TicTacToe.Move;
import TicTacToe.Board;

import java.util.Random;

public interface Mr_Bean extends BoardHandler {
    Random random = new Random();

    default Move getMove(Board.Cell player, Board target){
        List<Move> AvailableMoves = findAvailableMoves(target,player);
        return AvailableMoves.get(random.nextInt(AvailableMoves.size()));
    }

}
