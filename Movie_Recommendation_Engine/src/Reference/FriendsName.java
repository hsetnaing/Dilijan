package Reference;

import java.util.Scanner;

public class FriendsName {


    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        //Decide the number of friends
        System.out.print("Enter how many friends: ");
        int numOfFriends = Integer.parseInt(scan.nextLine()); //TODO: ask Davyd what this line means

        //Create a string array to store the names of your friends
        String arrayOfNames[] = new String[numOfFriends];
        for (int i = 0; i < arrayOfNames.length; i++) {
            System.out.print("Enter the name of friend " + (i+1) + " : ");
            arrayOfNames[i] = scan.nextLine();
        }

        //Now show your friend's name one by one
        for (int i = 0; i < arrayOfNames.length; i++) {
            System.out.print("My friend " + (i+1) + " : ");
            System.out.print(arrayOfNames[i] + "\n");
        }




    }

}