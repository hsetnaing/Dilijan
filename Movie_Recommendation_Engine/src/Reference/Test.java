package Reference;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {


    private void Nike() {

        ArrayList<User> OldUsers = new ArrayList<User>();

        User oldUser1 = new User(); //create a new user. newUser is an object - treat it like a human being.
        oldUser1.ID = "1"; //assign its ID value. newUser is an object with an ID. We want to assign the input value UserID to this object's attribute newUser.ID.
        oldUser1.mean = 4;
        OldUsers.add(oldUser1);

        User oldUser2 = new User(); //create a new user. newUser is an object - treat it like a human being.
        oldUser2.ID = "2"; //assign its ID value. newUser is an object with an ID. We want to assign the input value UserID to this object's attribute newUser.ID.
        oldUser2.mean = 4;
        OldUsers.add(oldUser2);

        User oldUser3 = new User(); //create a new user. newUser is an object - treat it like a human being.
        oldUser3.ID = "3"; //assign its ID value. newUser is an object with an ID. We want to assign the input value UserID to this object's attribute newUser.ID.
        oldUser3.mean = 2;
        OldUsers.add(oldUser3);

        User oldUser4 = new User(); //create a new user. newUser is an object - treat it like a human being.
        oldUser4.ID = "4"; //assign its ID value. newUser is an object with an ID. We want to assign the input value UserID to this object's attribute newUser.ID.
        oldUser3.mean = 1;
        OldUsers.add(oldUser4);

        for(int i = 0; i < OldUsers.size(); i++){
            System.out.println("ID: " + OldUsers.get(i).ID + ", mean: " + OldUsers.get(i).mean);
        }


        for(int j =0; j <OldUsers.size(); j++) {
            if (OldUsers.get(j).ID.equals("4")) {
                //int index;
                //index = j;
                System.out.println("we found the index: " + j);

            }

        }

        /*
        if (checkIfNew(ListofUsers.get(i))) {
            MovieRecommendationEngine.User newUser = new MovieRecommendationEngine.User();
            newUser.ID = ListofUsers.get(i);
            newUsers.add(newUser);
        }
         */


    }

    private ArrayList<Integer> MergeSort(){
        ArrayList<Integer> unsortedArray = new ArrayList<>();

        unsortedArray.add(1);
        unsortedArray.add(5);
        unsortedArray.add(7);
        unsortedArray.add(3);
        unsortedArray.add(2);
        unsortedArray.add(9);

        System.out.println(unsortedArray);
        return unsortedArray;


    }




    public static void main(String[] args) {
    Test engine = new Test();

    engine.Nike();
    ArrayList<Integer> array = new ArrayList<>();
    array = engine.MergeSort();
    //Arrays.sort(array);


    }
}
