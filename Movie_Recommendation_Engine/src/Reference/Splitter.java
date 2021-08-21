package Reference;

import java.util.Arrays;
import java.util.Scanner;

public class Splitter {

    public static void main (String[] args) {
        Scanner keyb = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String input = keyb.nextLine();
        String[] stringArray = input.split(",");

        for(int i=0; i < stringArray.length; i++){
            System.out.println(i + ": "  + stringArray[i]);
        }
        System.out.println(Arrays.toString(stringArray));
    }

}
