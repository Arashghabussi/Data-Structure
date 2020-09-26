import java.util.*;

public class KLists {

    // mergeKLists function
    public double [] mergeKLists (double [][] outerArray) {
        int size = 0;
        double answer[] = {};
        int index = 0;

        // finding the length of the outerArray to set the size for the answer list
        for (int i = 0; i < outerArray.length; i++) {
            for (int j = 0; j < outerArray[i].length; j++) {
                size++;
            }
        }
        // list keeper
        double listKeeper[] = new double[size];

        // adding all the elements to the listKeeper
        for (int i = 0; i < outerArray.length; i++) {
            for (int j = 0; j < outerArray[i].length; j++) {
                listKeeper[index] = outerArray[i][j];
                index++;
            }
        }
        // having all the elements in one main array
        System.out.println("Merged");
        System.out.println(Arrays.toString(listKeeper));

        // sorting - using bubble sort
        for (int j = 0; j < listKeeper.length; j++) {
            for (int i = 0; i < listKeeper.length - 1; i++) {
                if (listKeeper[i] > listKeeper[i + 1]) {
                    answer = swap(listKeeper, i, i + 1);
                }
            }
        }
        return answer;
    }

    // swap function
    public double[] swap(double[] arr, int first, int second) {
        double updatedList[] = arr;
        double temp = arr[first];
        updatedList[first] = updatedList[second];
        updatedList[second] = temp;
        return updatedList;
    }

    public static void main (String[] argv){
        double arr[][] = {{1.1, 4,4, 5,5}, {1.1, 3.3, 4.4}, {2.2, 6.6}};
        //double arr[][] = {{}};
        //double arr[][] = {{9.7, 17.1}, {15.8}, {12.7, 18.5, 21.27}};

        KLists core = new KLists();

        // printing the original given arr[][]
        // -----------------------

        System.out.println("Original");
        System.out.print("{ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print("{ ");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
                System.out.print("");
                if(j < arr[i].length - 1){
                    System.out.print(", ");
                }
            }
            System.out.print(" } ");
            if(i < arr.length - 1){
                System.out.print(", ");
            }
        }
        System.out.print("}");
        System.out.println();

        // -----------------------

        double answer[] = core.mergeKLists(arr);
        System.out.println("Edited");
        System.out.println(Arrays.toString(answer));
    }
}
