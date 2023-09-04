public class Solve {
    public static void main(String[] args) {
        Board b = new Board("413728065");
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
