package AIHelperFunctions;

public interface AIStatistics {

    /*      WORK IN PROGRESS           */

    int MAX_TREE_DEPTH = 10;
    long[] NodesPerDepth            = new long[MAX_TREE_DEPTH + 1];
    long[] TrimmedNodesPerDepth     = new long[MAX_TREE_DEPTH + 1];
    long[] TrimmedSymmetricBranches = new long[MAX_TREE_DEPTH + 1];

        default void addNodes(int depth, long amount){
            NodesPerDepth[depth] = NodesPerDepth[depth] + amount;
        }

        default void addTrimmedNodes(int depth){
            TrimmedNodesPerDepth[depth]++;
        }

        default void addSymmetricTrimmedBranches(int depth, long amount){
            TrimmedSymmetricBranches[depth] = TrimmedSymmetricBranches[depth] + amount;
        }

        default void resetStats(){
            for(int i = 0 ; i <= MAX_TREE_DEPTH ; i++ ){
                NodesPerDepth[i] = 0;
                TrimmedNodesPerDepth[i] = 0;
                TrimmedSymmetricBranches[i] = 0;
            }
        }

        default long[] getNodesPerDepth(){
            return NodesPerDepth;
        }

        default long[] getTrimmedNodesPerDepth(){
            return TrimmedNodesPerDepth;
        }

        default long[] getTrimmedSymmetricBranches(){
            return TrimmedSymmetricBranches;
        }

        default void printStats(){
            System.out.println("\n====NodesPerDepth====");
            for(int i = 0 ; i <= MAX_TREE_DEPTH ; i++){
                System.out.println("["+i+"] - "+NodesPerDepth[i]);
            }
            System.out.println("\n====TrimmedNodesPerDepth====");
            for(int i = 0 ; i <= MAX_TREE_DEPTH ; i++){
                System.out.println("["+i+"] - "+TrimmedNodesPerDepth[i]);
            }
            System.out.println("\n====TrimmedSymmetricBranches====");
            for(int i = 0 ; i <= MAX_TREE_DEPTH ; i++){
                System.out.println("["+i+"] - "+TrimmedSymmetricBranches[i]);
            }
        }
    }
