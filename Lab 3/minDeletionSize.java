import java.util.*;

public class minDeletionSize {

    public List<Integer> minDeletionSize(String [] A) {
        // Delete indexes
        List<Integer> chain = new ArrayList<Integer>();
        // Nested loop for checking each possibility
        for (int index = 0; index < A[0].length(); index++) {
           for (int i = 0; i < A.length - 1 ; i++) {
               // comparing to find the answer
               try {
                   if((A[i].charAt(index)) > (A[i + 1].charAt(index))) {
                       chain.add(index);
                       break;
                   }
               }
               // in case of any exception (-1)
               catch (Exception e){
                   chain.clear();
                   chain.add(-1);
                   return chain;
               }
           }
        }
        // return
        return chain;
    }

    // deleteProcess function
    void deleteProcess(String[] A, List<Integer> chain) {
        int row = 0;
        System.out.print("[");
        // nested loop for printing the edited list
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[row].length(); j++) {
                if(!chain.contains(j)) {
                    System.out.print(A[row].charAt(j));
                }
            }
            row++;
            // checking if the edited list is empty
            if((row < A.length) && !(chain.size() == A[0].length())) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }

    // main function
    public static void main(String[] args) {
        minDeletionSize core = new minDeletionSize();

        // TEST CASES:
        String[] A = {"cbaz","dafw","ghip"};
        //String[] A = {"a","b"};
        //String[] A = {"zyx","wvu","tsr"};
        //String[] A = {"Captain","Marvel","saved", "the", "Avengers"};

        List<Integer> chain = core.minDeletionSize(A);

        // printing the status of this operation
        for (int i = 0; i <= chain.size(); i++) {
            if(!chain.contains(-1) && !chain.contains(i) && chain.size() == 0) {
                // it was already sorted
                System.out.println("It was already sorted");
                System.out.println(chain);
                break;
            }  else if(chain.contains(-1)) {
                // not valid -1
                System.out.println("Not valid -1");
                System.out.println(chain);
                break;
            } else{
                // delete indexes
                System.out.println("Deleted index(es): ");
                System.out.println(chain);
                break;
            }
        }
        // printing the answers
        System.out.println("");
        System.out.println("Original A: ");
        System.out.println(Arrays.toString(A));
        System.out.println("");
        System.out.println("Edited A: ");
        core.deleteProcess(A, chain);
    }
}
