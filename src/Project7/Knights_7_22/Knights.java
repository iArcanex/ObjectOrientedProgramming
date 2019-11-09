package Project7.Knights_7_22;

import java.util.ArrayList;
import java.util.Arrays;

/**Knights Project
 * CSci 2001-91
 */

/* Knights 7.22 - a, b, and c.
 * This exercise had us create a knights tour and ultimately setup
 * heuristics that can choose the most optimal spots. Therefore, my
 * program, using this built-in insight, can achieve a full tour
 * much faster (and efficiently), compared to not choosing
 * moves optimally. It should usually take 5 to 15 tours on average
 * to fully complete a tour. Otherwise, it usually almost complete
 * on other runs.
 *
 * By looking at https://pdfs.semanticscholar.org/ebdf/b585eaea47f52ab774759da2c39fb6d0d8e6.pdf,
 * it seems the exercise we've completed falls under h1b, while if we included
 * part d it would be h2, which yields a higher complete tour rate. This was a
 * helpful document that demonstrated strategies and the algorithm efficiency
 * that heuristics may improve. Overall, a good read!
 * */

class Knights {

    private final int NUM_ROWS = 8, NUM_COLS = 8;
    // Move sets:                  0  1   2   3   4   5  6  7
    private final int[] H_SHIFT = {2, 1, -1, -2, -2, -1, 1, 2};
    private final int[] V_SHIFT = {-1, -2, -2, -1, 1, 2, 2, 1};
    private int[][] board;
    private int[][] accessibility = { { 2, 3, 4, 4, 4, 4, 3, 2 },
                                      { 3, 4, 6, 6, 6, 6, 4, 3 },
                                      { 4, 6, 8, 8, 8, 8, 6, 4 },
                                      { 4, 6, 8, 8, 8, 8, 6, 4 },
                                      { 4, 6, 8, 8, 8, 8, 6, 4 },
                                      { 4, 6, 8, 8, 8, 8, 6, 4 },
                                      { 3, 4, 6, 6, 6, 6, 4, 3 },
                                      { 2, 3, 4, 4, 4, 4, 3, 2 } };

    private int locRow, locCol, moveNumber, moveCounter;
    private boolean tourOngoing;

    Knights() {

        board = new int[NUM_ROWS][NUM_COLS];
        resetBoard(); // Sets board values to 0
        printBoard(); // Prints it

        // Change start location if need be, set to be at center.
        locCol = board.length / 2; //(board.length / 2) is center of board
        locRow = board.length / 2;

        board[locRow][locCol] = 1;
        moveNumber = 1;
        moveCounter = 1;

        runTour();

    }

    // This method has a while set to continue running, as long
    // as the tour (or moves) can/will be made. makeMove contains
    // the majority of this logic. Ultimately if moveCounter is 64,
    // the tour is a success.
    private void runTour() {

        tourOngoing = true;

        while(tourOngoing) {

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

    }

    // makeMove also contains a loop. It'll keep running so long as moves are made
    // successfully. A fail counter keeps track of potential failures, but with
    // heuristics it'll largely be unused (but helpful to limit brute force)
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
                // System.out.println("locRow is: " + locRow + " and locCol is:" + locCol);
                moveCounter += 1;
                moveNumber++;

                board[locRow][locCol] = moveNumber;
                keepMoving = false;

                printBoard();

            }

            else {

                keepMoving = false;
                tourOngoing = false;
                System.out.println("The Knight is stuck. Failure.");

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

    // This method does a few things. Firstly it establishes a random Index,
    // an accessibility Level, and array lists for the accessibility values
    // 2, 3, 4, 6, and 8. A loop looks over the index of moves, and for each
    // move it'll determine what their level of access is. For each move, it
    // adds it to the corresponding list and decrements the access level to simulate
    // a shrinking board. After the loop, each access list will be checked
    // to determine the most optimal move to take. It favors <= 2 through the largest 8.
    private int pickMove(ArrayList<Integer> moves) {

        int randomIndex;
        int accessibilityLevel;
        int move = 0;

        ArrayList<Integer> accessibleTwo = new ArrayList<>();
        ArrayList<Integer> accessibleThree = new ArrayList<>();
        ArrayList<Integer> accessibleFour = new ArrayList<>();
        ArrayList<Integer> accessibleSix = new ArrayList<>();
        ArrayList<Integer> accessibleEight = new ArrayList<>();

        for (Integer integer : moves) {

            int accessRow = locRow + V_SHIFT[integer];
            int accessCol = locCol + H_SHIFT[integer];
            accessibilityLevel = accessibility[accessRow][accessCol];

            // System.out.println("DEBUG: i = " + i + ", Accessibility level of move: " + moves.get(i) + " is " + accessibilityLevel);

            if (accessibilityLevel <= 2) {

                accessibleTwo.add(integer);
                // System.out.println(accessibleTwo.size());

            } else if (accessibilityLevel == 3) {

                accessibleThree.add(integer);
                // System.out.println(accessibleThree.size());

            } else if (accessibilityLevel == 4 || accessibilityLevel == 5) {

                accessibleFour.add(integer);
                // System.out.println(accessibleFour.size());

            } else if (accessibilityLevel == 6 || accessibilityLevel == 7) {

                accessibleSix.add(integer);
                // System.out.println(accessibleSix.size());

            } else if (accessibilityLevel == 8) {

                accessibleEight.add(integer);
                // System.out.println(accessibleEight.size());

            }

            --accessibility[accessRow][accessCol];

        }

        if(accessibleTwo.size() > 0) {

            // System.out.println(accessibleTwo.toString());
            randomIndex = (int)(Math.random()*accessibleTwo.size());
            // System.out.println("DEBUG: random Index chosen is " + randomIndex +", which contains move: " + accessibleTwo.get(randomIndex));
            move = accessibleTwo.get(randomIndex);

        }

        else if(accessibleThree.size() > 0) {

            // System.out.println(accessibleThree.toString());
            randomIndex = (int)(Math.random()*accessibleThree.size());
            // System.out.println("DEBUG: random Index chosen is " + randomIndex +", which contains move: " + accessibleThree.get(randomIndex));
            move = accessibleThree.get(randomIndex);

        }

        else if(accessibleFour.size() > 0) {

            // System.out.println(accessibleFour.toString());
            randomIndex = (int)(Math.random()*accessibleFour.size());
            // System.out.println("DEBUG: random Index chosen is " + randomIndex +", which contains move: " + accessibleFour.get(randomIndex));
            move = accessibleFour.get(randomIndex);

        }

        else if(accessibleSix.size() > 0) {

            // System.out.println(accessibleSix.toString());
            randomIndex = (int)(Math.random()*accessibleSix.size());
            // System.out.println("DEBUG: random Index chosen is " + randomIndex +", which contains move: " + accessibleSix.get(randomIndex));
            move = accessibleSix.get(randomIndex);

        }

        else if(accessibleEight.size() > 0) {

            // System.out.println(accessibleEight.toString());
            randomIndex = (int)(Math.random()*accessibleEight.size());
            // System.out.println("DEBUG: random Index chosen is " + randomIndex +", which contains move: " + accessibleEight.get(randomIndex));

            if(!(moves.size() <= 0)) {

                move = accessibleEight.get(randomIndex);

            }

        }

        else {

            if(!(moves.size() <= 0)) {

                randomIndex = (int)(Math.random()*moves.size());
                move = moves.get(randomIndex);

            }

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
