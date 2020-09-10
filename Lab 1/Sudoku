// Arash Ghabussi & Edwin Ye

import java.util.Scanner;

public class Sudoku {
	//create a 9*9 board || filled with sample testing
    public static int map[][] = {
            {9,0,0,1,0,0,0,0,5},
            {0,0,5,0,9,0,2,0,1},
            {8,0,0,0,4,0,0,0,0},
            {0,0,0,0,8,0,0,0,0},
            {0,0,0,7,0,0,0,0,0},
            {0,0,0,0,2,6,0,0,9},
            {2,0,0,3,0,0,0,0,6},
            {0,0,0,2,0,0,9,0,0},
            {0,0,1,9,0,4,5,7,0},
    };

    // Enter Board by user method
    public void enterBoard(int x, int y) {
        Scanner myObj = new Scanner(System.in);
        int slot = myObj.nextInt();
        map[x][y] = slot;
    }

    // Checker Method - checks if the number works for that spot or not
    public static boolean checker(int row, int col, int num) {
		//check for each row and column to find the matching number
    	//if found then return false
	    for (int i = 0; i < 9; i++) {
	    	if (map[row][i] == num) {
	    		return false;
	    	}
	    	if (map[i][col] == num) {
				return false;
			}
	    }
	    //check for each boxes to find the matching number
	    //if found then return false
		int ro = row - row % 3;
		int co = col - col % 3;

		for (int r = ro; r < ro + 3; r++) {
			for (int c = co; c < co + 3; c++) {
				if (map[r][c] == num) {
					return false;
				}
			}
		}
		//if there is no problem, return true
		return true;
    }

    public boolean fillBoard() {
    	//set row and column to -1 and empty to true in case if not use
    	int x =- 1, y =- 1;
    	boolean empty = true;
        //checking for zeros
        for(int row=0; row < 9; row++) {
            for(int col = 0; col < 9; col++) {
            	//found zero then break
                if(map[row][col] == 0) {
                	x = row;
                	y = col;
                	// not empty any more
                	empty = false;
                	break;
                }
            }
            if (!empty) {
            	//System.out.println("is not empty");
            	break;
            }
        }
        //the map is filled, it is not empty
        if(empty) {
        	//System.out.println("is empty");
        	return true;
        }

        // trying each possible number to find the right answer
        for(int answer = 1; answer <= 9; answer++) {
        	//check if the answer is good or not
            if(checker(x,y,answer)) { // if it's true then good to go
            	map[x][y] = answer;
                if(fillBoard()) {
                    return true;
                }
                else {
                	//if not then replace
                    map[x][y] = 0;
                }
            }
        }
        // After checking, if code reaches here, means cannot find a solution
        return false;
    }

    // Printing the board
    public void printBoard() {
        for(int y = 0; y < 9; y++) {
            for(int x = 0; x < 9; x++) {
                System.out.print(map[x][y]);
                System.out.print(", ");
            }
            System.out.println();
        }
    }

    // Main
    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        System.out.println("Sodoku! - enter numbers 9 * 9");
        for(int y = 0; y < 9; y++) {
            for(int x = 0; x < 9; x++) {
                sudoku.enterBoard(x, y);
                // EnterBoard
            }
        }
        System.out.println("--------WHAT YOU ENTERED--------");
        sudoku.printBoard();
        System.out.println("--------------------------------");
        System.out.println("--------------SOLVED------------");
        System.out.println("--------------------------------");

        //fillBoard Exception
        if(!sudoku.fillBoard()) {
            System.out.println("no answer");
        }
        sudoku.printBoard();
        System.out.println("--------------------------------");
    }
}