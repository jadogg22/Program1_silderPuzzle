import java.util.Scanner;
import java.util.*;

public class Game {
    private static final String SOLVED_ID = "123456780";
    Board theBoard;
    String originalBoardID;
    String boardName;

    /**
     *  Solve the provided board
     * @param label Name of board (for printing)
     * @param b Board to be solved
     */
    public void playGiven(String label, Board b) {
        originalBoardID = b.getId();
        boardName = label;
        System.out.println("Board initial: " + boardName + " \n" + b.toString());
        solve(b);

    }

    /**
     * Create a random board (which is solvable) by jumbling jumnbleCount times.
     * Solve
     * @param label Name of board (for printing)
     * @param jumbleCount number of random moves to make in creating a board
     */
    public void playRandom(String label, int jumbleCount) {
        theBoard = new Board();
        theBoard.makeBoard(jumbleCount);
        System.out.println(label + "\n" + theBoard);
        playGiven(label, theBoard);


    }



    public static void main(String[] args) {
        String[] games = {"102453786", "123740658", "023156478", "413728065", "145236078", "123456870"};
        String[] gameNames = {"Easy Board", "Game1", "Game2", "Game3", "Game4", "Game5 No Solution"};
        Game g = new Game();
        Scanner in = new Scanner(System.in);
        Board b;
        String resp;
        for (int i = 0; i < games.length - 1; i++) {
            b = new Board(games[i]);
            g.playGiven(gameNames[i], b);
            System.out.println("Click any key to continue\n");
            resp = in.nextLine();
        }


        boolean playAgain = true;
        //playAgain = false;

        int JUMBLECT = 18;  // how much jumbling to do in random board
        while (playAgain) {
            g.playRandom("Random Board", JUMBLECT);

            System.out.println("Play Again?  Answer Y for yes\n");
            resp = in.nextLine().toUpperCase();
            playAgain = (resp != "") && (resp.charAt(0) == 'Y');
        }


    }

    /**
     * Solve the board
     */
    public static void solve(Board b) {
        System.out.println("Solving..."+ b.getId());

        Boolean solved = false;

        queue q = new queue();
        q.enqueue(b);

        int count = 0;

        while (!solved & count < 100){
            
            Board current = q.dequeue();
            if (isSolved(current.getId())){
                solved = true;
                System.out.println("Solved!");
                printSteps(b, current.getSequence());
            }
            else{
                Board tempU = new Board(current.getId(), current.getSequence());
                if (tempU.makeMove('U', current.getLastMove())) {
                    tempU.addMove('U');
                    q.enqueue(tempU);
                    
                }
                Board tempD = new Board(current.getId(), current.getSequence());
                if (tempD.makeMove('D', current.getLastMove())) {
                    tempD.addMove('D');
                    q.enqueue(tempD);
                    
                }
                Board tempL = new Board(current.getId(), current.getSequence());
                if (tempL.makeMove('L', current.getLastMove())) {
                    tempL.addMove('L');
                    q.enqueue(tempL);
                    
                }
                Board tempR = new Board(current.getId(), current.getSequence());
                if (tempR.makeMove('R', current.getLastMove())) {
                    tempR.addMove('R');
                    q.enqueue(tempR);
                    
                }

            }
            count++;
            if (count > 100){
                System.out.println("No solution found");
            }
        }
    }

    public static Boolean isSolved(String id){
        if (id.equals("123456780")){
            return true;
        }
        else{
            return false;
        }
    }

    public static void printSteps(Board b, String sequence){
        String steps = b.toString();
        char lastMove = 'N'; // N for none
        for(int i = 0; i < sequence.length(); i++) {
          char move = sequence.charAt(i);
          
          b.makeMove(move, lastMove);
          steps += move + " ===>\n";
          steps += b.toString();
          
          lastMove = move;
      
        }
        System.out.println(steps);
    }

}
