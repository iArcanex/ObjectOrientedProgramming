package Project7.Knights_7_23;

import java.util.ArrayList;
import java.util.Arrays;

/**Knights Project
 * CSci 2001-91
 */

/* Knights 7.23 - a, b, c, d.
 * This exercise had us adapt knights tour to randomly choose
 * moves without optimal move heuristics. This of course produces
 * results that one can both expect and learn from. Part D is answered
 * below and has us compare the approaches in 7.22 to 7.23.
 *
 * ANSWER TO D: When working on both 7.22 and 7.23, the most time I've dedicated
 * to was 7.22. This is because I was required to develop heuristics that accounted
 * for the most optimal spaces to move towards. 7.23 was easier to program as it only chose its
 * moves randomly, based on which moves could be made. Ultimately, a few runs of
 * 7.22 would yield me much more favorable results compared to 7.23, where even after
 * having the tour run up to 100,000 times, I couldn't get one that was complete.
 * In the long-term, heuristics provide a way to efficiently target information and
 * yield more desirable results. Brute force programs are necessary for purposes where
 * one may not be able to cater their program to be biased. It can also be favorable when
 * brute force is NECESSARY for the problem. For this project, implementing a brute force
 * tour was beneficial for my learning as it clearly demonstrates the amount of time a few
 * lines of code can save. Looking in advance, it makes sense that looking at optimal spaces
 * would save us some time. In conclusion, brute force programming is viable for certain
 * instances, but for a problem such as KnightsTour that has a few solutions, catering
 * our program to lead towards those solutions is the best option.
 * */


class Knights {

    private final int NUM_ROWS = 8, NUM_COLS = 8;
    // Move sets:                  0  1   2   3   4   5  6  7
    private final int[] H_SHIFT = {2, 1, -1, -2, -2, -1, 1, 2};
    private final int[] V_SHIFT = {-1, -2, -2, -1, 1, 2, 2, 1};

    ArrayList<Integer> attemptedRuns = new ArrayList<>();
    private int[][] board;

    private int locRow, locCol, moveCounter;
    private boolean tourOngoing;

    Knights() {

        board = new int[NUM_ROWS][NUM_COLS];
        resetBoard(); // Sets board values to 0
        printBoard(); // Prints it

        // Change start location if need be, set to be at center.
        locCol = (board.length / 2); //(board.length / 2) is center of board
        locRow = (board.length / 2);

        board[locRow][locCol] = 1;
        moveCounter = 1;

        runTour();

    }

    // This method has a while set to continue running, as long
    // as the tour (or moves) can/will be made. makeMove contains
    // the majority of this logic. Ultimately if moveCounter is 64,
    // the tour is a success.
    private void runTour() {

        while(attemptedRuns.size() < 100000) {

            tourOngoing = true;

            while (tourOngoing) {

                makeMove();

                if (moveCounter == 64) {

                    tourOngoing = false;
                    System.out.println("A successful tour!");

                }

            }

            if (moveCounter < 64) {

                System.out.println("A failed tour...");

            }

            System.out.println("FINAL POSITION - " + "Row: " + locRow + " Col: " + locCol);
            System.out.println(moveCounter + " moves where made during this tour.");
            System.out.println("Final board: ");
            printBoard();

            System.out.println("CURRENT RUN: " + attemptedRuns.size());

        }

        System.out.println("+-=-=-=- PROCEDURE FINISHED -=-=-=-+");
        System.out.println("Attempted Runs: " + attemptedRuns.size());

        for(int i = 0; i < attemptedRuns.size(); i++) {

            System.out.println("Run [" + i + "]" + " - Moves Made: " + attemptedRuns.get(i));

        }

        System.out.println("Completed Runs:");
        System.out.print("[");

        for(int i = 0; i < attemptedRuns.size(); i++) {

            if(attemptedRuns.get(i) == 64) {

                System.out.print("i ");

            }

        }

        System.out.print("]");

    }

    // makeMove also contains a loop. It'll keep running so long as moves are made
    // successfully.
    // Otherwise, moves are first analyzed with findAvailableMoves, afterwards
    // they'll be chosen with pickMove(). If the move is within the necessary
    // bounds, it'll proceed. If not, the tour will end.
    private void makeMove() {

        boolean keepMoving = true;

        while(keepMoving) {

            ArrayList<Integer> moves = findAvailableMoves();

            System.out.println("Found the following moves:");

            System.out.print("[");

            for (Object move : moves) {

                System.out.print(move + " ");

            }

            System.out.print("]\n");

            int chosenMove = pickMove(moves);

            System.out.println("Chose move: " + chosenMove);

            System.out.println("Knight currently at - " + "Row: " + locRow + " Col: " + locCol);

            // If moves are available, move!
            if (moves.size() > 0) {

                System.out.println("The way is clear! Making move: " + chosenMove);
                locRow = locRow + V_SHIFT[chosenMove];
                locCol = locCol + H_SHIFT[chosenMove];
                moveCounter += 1;

                board[locRow][locCol] = 1;
                keepMoving = false;

                printBoard();

            }

            else {

                keepMoving = false;
                tourOngoing = false;
                System.out.println("The Knight is stuck. Failure.");

                attemptedRuns.add(moveCounter);

                // Reset
                resetBoard();
                moveCounter = 0;

            }

        }

    }

    // By using an array list and validateCell(), the for loop here
    // can determine which moves can be utilized. This is a helpful bounds
    // check.
    private ArrayList<Integer> findAvailableMoves(){

        ArrayList<Integer> moves = new ArrayList<>();

        for(int i = 0; i < H_SHIFT.length; i++) {

            if(validateCell(locRow + V_SHIFT[i], locCol + H_SHIFT[i], board)) {

                moves.add(i);

            }

        }

        return moves;

    }

    // This method picks a move. Firstly it establishes a random Index.
    // This is the randomly chosen index that will be used
    // to produce a move that qualifies. Since this is done
    // randomly, results will vary.
    private int pickMove(ArrayList<Integer> moves) {

        int move = 0;
        int randomIndex = (int)(Math.random() * moves.size());
        System.out.println("Move sizes: " + moves.size());

        if(!(moves.size() <= 0)) {

            move = moves.get(randomIndex);

        }

        return move;

    }

    // Looks at both validateRow/Col methods and utilizes them to
    // generate a boolean that will inform us if a certain cell
    // is out of bounds or not.
    private boolean validateCell(int row, int col, int[][] arr) {

        return validateRow(row, arr) && validateCol(row, col, arr) && board[row][col] < 1;

    }

    // Looks at row and generates boolean that is not out of bounds.
    private boolean validateRow(int row, int[][] arr) {

        return row > -1 && row < arr.length;

    }

    // Looks at col and generates boolean that is not out of bounds.
    private boolean validateCol(int row, int col, int[][] arr) {

        return col > -1 && col < arr[row].length;

    }

    // Just traverses board and fills it with 0 to make the board.
    private void resetBoard() {

        for (int[] ints : board) {

            Arrays.fill(ints, 0); // Takes in array board[i], and fills them with 0

        }

    }

    // Prints the current board.
    private void printBoard() {

        System.out.println("+-=-=-=- CURRENT BOARD -=-=-=-+");

        for(int[] arr : board)
            System.out.println(Arrays.toString(arr));


    }

}
