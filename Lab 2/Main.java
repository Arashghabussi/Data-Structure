package com.arash;

// Main class
public class Main {
    // TwoSum method that find the target
    public int [] twoSum(int[] list, int sum) {
        // setting the values as default
        int[] answer = {-1, -1};
        // two nested loop that iterate for all the possibility
        for (int i = 0; i < list.length; i++) {
            for (int h = 0; h < list.length; h++) {
                // Using the loop and finding the target
                if ((list[i] + list[h]) == (sum)) {
                    answer[0] = list[i];
                    answer[1] = list[h];
                    return answer;
                } // Finding the bug in case of being same numbers
                else if (list[i] == list[h] && i != h) {
                    answer[0] = -1;
                    answer[1] = -1;
                    System.out.println("ERROR -1, duplication found!");
                    return answer;
                }
            }
        }
        return answer;
    }

    // Main method
    public static void main(String[] args) {
        Main runner = new Main();
        int list[] = {2, 12, 8, 7};
        // printing the list
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i]);
            System.out.print(" ");
        }
        System.out.println();
        System.out.println("-------List-----");
        // <----------------------------------------------------------------------------------->
        // -----------------------------calling the towSum method-----------------------------
        int[] status = runner.twoSum(list, 9);
        // <----------------------------------------------------------------------------------->
        // printing the answer
        System.out.println("Sum Index: ");
        System.out.print("(");
        // looping to find the index of each answer and printing
        int index = 0;
        for (int i = 0; i < list.length; i++) {
            if(list[i] == status[index]){
                System.out.print(i);
                System.out.print(", ");
            } else if(list[i] == status[index+1]){
                System.out.print(i);
            }
        }
        // solved
        System.out.print(")");
        System.out.println();
        System.out.println("-----Solved-----");
    }
}
