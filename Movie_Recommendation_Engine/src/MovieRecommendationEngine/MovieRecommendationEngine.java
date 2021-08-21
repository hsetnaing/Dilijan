package MovieRecommendationEngine;

//purpose is to come up with a rating

import java.io.*;
import java.nio.file.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class MovieRecommendationEngine {

    static ArrayList<User> OldUsers = new ArrayList<User>(); //Keep info of all users in this arraylist
    private Path Movie;


    //purpose of function: record user's rating for a particular movie
    //input: UserID (cuz we're trying to find them)
    //input: MovieFile
    //TODO: change back to void
    private void RecordMovieRating(String MovieFile, ArrayList<User> ListofUsers) throws FileNotFoundException {

        File file = new File(MovieFile);
        Scanner sc = new Scanner(file);
        String userID;
        String rating;
        ArrayList<String> Ratings = new ArrayList<String>();
        //ArrayList<Integer> Ratingsnum = new ArrayList<Integer>(Ratings.size());
        //int numrating; //to convert String rating to int, for the return statement

        if (sc.hasNextLine()) //I need to include this because I'm using Delimiter and I want to ignore the first line in each and every file
            sc.nextLine();

        while (sc.hasNextLine()) {
            Scanner read = new Scanner(sc.nextLine()); //we are creating a Scanner that reads this new line
            //starts reading every line in the text file
            read.useDelimiter(",");
            userID = read.next();
            rating = read.next();

            for (int i = 0; i < ListofUsers.size(); i++) {
                if (ListofUsers.get(i).ID.equals(userID)) {
                    //return rating (and movie)
                    //however, this means I need to look at the entire line and look at the thing after the delimiter
                    //Ratingsnum = Ratingsnum.add(Integer.parseInt(rating)); //converts String to int, for the return statement

                    MovieRating newMovieRating = new MovieRating(); //I need to create a new object of class MovieRating
                    newMovieRating.Rating = Integer.parseInt(rating); //records rating, but not name of movie
                    newMovieRating.Movie = MovieFile; //record name of movie
                    ListofUsers.get(i).MovieRatings.add(newMovieRating); //add the object to the MovieRatings array list

                    //ListofUsers.get(i).NumberOfMovies++; //increment number of movies to count how many they've watched

                    //System.out.println("I found the user");
                    //System.out.println(userID);
                    //System.out.println(rating);
                }
            }
        }

    }

    private boolean checkIfNew(String UserID) {
        for (int i = 0; i < OldUsers.size(); i++) {
            if (OldUsers.get(i).ID.equals(UserID)) {
                return false;
            }
        }
        return true;
    }

    /*
    private void printUser(User user) {
        System.out.println("User : {ID:" + user.ID + ", mean:" + user.mean + ", SD:" + user.SD + ", count:" + user.NumberOfMovies);
        System.out.println(", MovieRatings: [");
        for (int i = 0; i < user.MovieRatings.size(); i++) {
            System.out.println("{Movie:" + user.MovieRatings.get(i).Movie + ", Rating:" + user.MovieRatings.get(i).Rating + "}");
        }
        System.out.println("]}");
    }
     */


    //return UserID ArrayList
    private ArrayList<String> ReturnUserID(String MovieFile) throws FileNotFoundException {
        File file = new File(MovieFile); //read file
        Scanner sc = new Scanner(file); //create new Scanner

        ArrayList<String> UserIDArrayList = new ArrayList<>();

        //read the first line. after this function, rest of the text is left. this ignores the "1:"
        if (sc.hasNextLine()) {
            sc.nextLine();
        }

        while (sc.hasNextLine()) { //Scanner is connected directly to the file, and it reads it. Does it have next line?

            Scanner scanner = new Scanner(sc.nextLine()); //we are creating a Scanner that reads this new line
            scanner.useDelimiter(","); //only reads what comes before ",", everything else is ignored
            //now scanner is looking at the very first user ID

            //String input = sc.nextLine();
            UserIDArrayList.add(scanner.next()); //adds new empty bucket, put value inside bucket

        }
        //System.out.println(UserIDArrayList);
        return UserIDArrayList;
    }


    //writing our own function instead of using HashMap
    //FindUserIndex goes through each element inside Users,
    // compares UserID with the UserID inside of each element in the class User
    // and see if the user is there
    //i.e. see if we already have him in the database/his info is already recorded


    //Open all movie files to find first person in Array List (and record their rating)
    //Records rating, movies watched, mean and SD of a user
    //output: ArrayList of type Object
    //The list of users input should be exactly the same as the list of users output

    //TODO: change output back to ArrayList<User>
    private ArrayList<User> RecordUserInfo(ArrayList<String> ListofUsers) throws FileNotFoundException {
        //there are 2205 movies

        //future use: have a function to check if this person is an OldUser

        //if new user: record their data

        //create a new user, assign its ID value and append to OldUsers
        //go through each movie file,use function ReadFile,
        // record rating & store it (condition: if user exists, record movie name too)
        //check: prints OldUsers array list


        //TODO: check with Davyd. changed type from String to User.
        ArrayList<User> newUsers = new ArrayList<User>();

        for (int i = 0; i < ListofUsers.size(); i++) {
            if (checkIfNew(ListofUsers.get(i))) {
                User newUser = new User();
                newUser.ID = ListofUsers.get(i);
                newUsers.add(newUser);
            }
        }

        //test
        for(int num = 0; num < newUsers.size(); num++) {
           // System.out.println("These are the new users: " + newUsers.get(num).ID);
        }


        //RecordMovieRating records ratings
        ArrayList<String> movies = GetListofAllMovies();

        for (int i = 0; i < movies.size(); i++) {

            RecordMovieRating("moviedata/" + movies.get(i), newUsers);

        }

        //test
        //System.out.println("newUsers size: "+newUsers.size());
        //System.out.println("third person movie array size: "+newUsers.get(2).MovieRatings.size());

        //test
        for(int j = 0; j < newUsers.size();j++) {

            for (int k = 0; k < newUsers.get(j).MovieRatings.size(); k++) {
                //System.out.println("User: " + newUsers.get(j).ID + ", Movie: " + newUsers.get(j).MovieRatings.get(k).Movie + ", Rating: " + newUsers.get(j).MovieRatings.get(k).Rating);
            }
        }
        //newUsers list only has ID, rating at this point
        //System.out.println(newUsers.size());
        //System.out.println(newUsers.get(0).ID);
        //System.out.println("the size of 127122: "+ newUsers.get(144).MovieRatings.size());

        for (int i = 0; i < newUsers.size(); i++) {
            //record mean and SD here outside of for loop after all movie/rating data has been collected

            double UserMean;
            double sum = 0;

            //adds all the ratings for a user
            for (int k = 0; k < newUsers.get(i).MovieRatings.size(); k++) {
                sum = sum + newUsers.get(i).MovieRatings.get(k).Rating;
            }

            //UserMean = sum / newUsers.get(i).NumberOfMovies;
            UserMean = sum / newUsers.get(i).MovieRatings.size();
            newUsers.get(i).mean = UserMean; //assign mean value to the new user

            //now for standard deviation
            //for each rating, subtract mean from it. then square the result.
            //find mean of all those squared differences.
            //take the square root of answer found above.

            double subtractedresult = 0;
            double squaredresult = 0;

            ArrayList<Double> SquaredDifferences = new ArrayList<>(); //create array list for squared differences

            for (int z = 0; z < newUsers.get(i).MovieRatings.size(); z++) {
                subtractedresult = newUsers.get(i).MovieRatings.get(z).Rating - UserMean;
                squaredresult = subtractedresult * subtractedresult; //square
                SquaredDifferences.add(squaredresult); //appends squared result to array list
            }

            double SquareDiffMean;
            double UserSD;
            double sum2 = 0;

            for (int y = 0; y < SquaredDifferences.size(); y++) {
                sum2 = sum2 + SquaredDifferences.get(y);
            }
            SquareDiffMean = sum2 / SquaredDifferences.size();

            UserSD = Math.sqrt(SquareDiffMean);
            newUsers.get(i).SD = UserSD;

            //printUser(newUsers.get(i));
        }
        //now all essential info recorded for newUsers

        //Check results
        /*
       System.out.println(newUser.ID);
        for(int num = 0; num < newUser.MovieRatings.size(); num++) {
            System.out.println(newUser.MovieRatings.get(num).Movie);
            System.out.println(newUser.MovieRatings.get(num).Rating);
        }
        System.out.println(count);
        System.out.println(UserMean);
        System.out.println(SquaredDifferences);
        System.out.println(UserSD);
        System.out.print(OldUsers);

         */
        //System.out.println("This is what I'm looking for " + OldUsers);
        //System.out.println(ListofUsers);
        //add newUsers to old users array list
        for (int i = 0; i < newUsers.size(); i++) {
            OldUsers.add(newUsers.get(i));
        }

        //why we need one last function?
        //input array list is not equal to output array list
        //if they are old users, we're not adding them to the newUsers array list
        //if old users already exist in input array list, the output array list will be missing some people
        //our final function wants to convert the String array list to an Object array list, with all the additional info
        //basically String -> Object array list conversion
        //example
        /*
        for(int a = 0; a < StringNeighbours.size(); a++){
            User newUser = new User();
            newUser.ID = StringNeighbours.get(a);
            Neighbours.add(newUser);
        }
         */

        //function for-loop
        //Loop through the original <String>ListofUsers array list
        //now every user is part of <User>OldUsers array list right?
        //find them first
        //then get(i) it
        //add it to the output array list

        //reminder: every person is now an OldUser
        ArrayList<User> outputUsers = new ArrayList<User>(); //the output for this function
        for (int i = 0; i < ListofUsers.size(); i++) {

            int index = 0;
            index = FindUserIndex(ListofUsers.get(i)); //this throws the ID inside FindUserIndex's argument
            User newOutputUser = new User();

            //their info in the OldUsers array list is copied to the outputUsers array list
            newOutputUser.ID = ListofUsers.get(i);
            newOutputUser.mean = OldUsers.get(index).mean;
            newOutputUser.SD = OldUsers.get(index).SD;
            newOutputUser.NumberOfMovies = OldUsers.get(index).NumberOfMovies;
            newOutputUser.MovieRatings = OldUsers.get(index).MovieRatings;

            outputUsers.add(newOutputUser);

        }
        //outsource the task to a function outside this RecordUserInfo
        //it should be find them

        //the function return Object
        //private int FindUserIndex(String UserID)

        //or it returns the index in oldusers array list

        //finish the code using FindUserIndex
        //hint for FindUserIndex -> use checkIfNew function as reference
        //you could actually remove checkIfNew function from everywhere
        //useFindUserIndex instead (it will return -1 if user is new, return index if user is old)


        //test
        for(int hello=0; hello<outputUsers.size(); hello++) {
            //System.out.println("The ID: "+outputUsers.get(hello).ID +", the mean: "+ outputUsers.get(hello).mean + ", the SD: " + outputUsers.get(hello).SD);
        }

        return outputUsers; //array list of users of type <Object> (specifically <Users>)

    }

    /*
     private boolean checkIfNew(String UserID) {
        for (int i = 0; i < OldUsers.size(); i++) {
            if (OldUsers.get(i).ID.equals(UserID)) {
                return false;
            }
        }
        return true;
    }
     */

    //returns index in OldUsers array list
    //input is user's ID
    //output is that user's location in the OldUsers array list, else
    //output is -1 if that person is new
    private int FindUserIndex(String UserID){
        //go through the IDs of the array
        //return the index
        int index = -1;
        for(int j = 0; j < OldUsers.size(); j++) {
            if (OldUsers.get(j).ID.equals(UserID)) {
                index = j;
                break;
            }
        }
        return index;
    }


    //output is Array list of ALL movies
    public ArrayList<String> GetListofAllMovies() {

        ArrayList<String> AllMovies = new ArrayList<String>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("moviedata"))) {
            for (Path file : stream) {
                Movie = file.getFileName();
                AllMovies.add(Movie.toString());
            }

        } catch (IOException | DirectoryIteratorException ex) {
            System.err.println(ex);
        }
        //System.out.println(AllMovies);

        return AllMovies;
    }


    //constructor
    //public Animal();
    private MovieRecommendationEngine() throws FileNotFoundException {
        //Animal = breathe();
        //Animal = eat();
        //Animal = die();


    }

    //ArrayList<CommonMovie> CommonMovies = new ArrayList<CommonMovie>();


    // input: User A and B from User class, which has all their info.
    // output: A list of movies A and B have watched.
    //Pearson calls commonRatings
    private ArrayList<CommonMovie> commonRatings(User A, User B) {

        /*
                    MovieRating newMovieRating = new MovieRating(); //I need to create a new object of class MovieRating
                    newMovieRating.Rating = rating; //records rating, but not name of movie
                    newMovieRating.Movie = movies.get(i); //record name of movie
                    newUser.MovieRatings.add(newMovieRating); //appends the object (newMovieRating) to arraylist 'MovieRatings'
                    count++;

         */
        /*
                    User newUser = new User(); //create a new user. newUser is an object - treat it like a human being.
         */

        ArrayList<CommonMovie> CommonMovies = new ArrayList<CommonMovie>(); //this creates an empty list that we will use to store data i.e. list of movies A and B watched. The type is a class 'CommonMovie'

        //CommonMovie newCommonMovie = new CommonMovie(); //I need to create a new object of class CommonMovie
        //placing this here
        //overwrites objects instead of adding them

        String MovieName;
        double rA; //User A's rating
        double rB; //User B's rating

        //test
        //System.out.println("A's ID"+A.ID);
        //System.out.println("B's ID"+B.ID);
        //System.out.println(A.MovieRatings.size());
        //System.out.println(B.MovieRatings.size());
        /*
        for(int g=0; g< A.MovieRatings.size(); g++){
            System.out.println("Movies User A has watched: " + A.MovieRatings.get(g).Movie + ", " + A.MovieRatings.get(g).Rating);
        }

        for(int h=0; h< B.MovieRatings.size(); h++){
            System.out.println("Movies User B has watched: " + B.MovieRatings.get(h).Movie + ", " + B.MovieRatings.get(h).Rating);
        }

         */

        //System.out.println("are you even here");
        //System.out.println("OldUsers: "+OldUsers.size());
       //System.out.println("A's size: "+ A.MovieRatings.size());
        //System.out.println("B's size: "+ B.MovieRatings.size());

        //end of test

        int start = 0;
        for (int i = 0; i < A.MovieRatings.size(); i++) {

            //System.out.println("inside the first loop");

            for (int j = start; j < B.MovieRatings.size(); j++) {


                if (A.MovieRatings.get(i).Movie.equals(B.MovieRatings.get(j).Movie)) { //condition 3



                    CommonMovie newCommonMovie = new CommonMovie(); //I need to create a new object of class CommonMovie

                    //do we have a common movie? yes. now add it to the array list
                    MovieName = A.MovieRatings.get(i).Movie; //we got the movie! Now we store it in this variable.
                    //System.out.println(MovieName);
                    newCommonMovie.Name = MovieName; //now it's also stored in the class

                    //let's also store A and B's ratings for this movie
                    rA = A.MovieRatings.get(i).Rating;
                    rB = B.MovieRatings.get(j).Rating;

                    //Now let's store it in the object CommonMovie class
                    newCommonMovie.ri = rA;
                    newCommonMovie.rj = rB;

                    //System.out.println(newCommonMovie.Name + " " + newCommonMovie.ri + " " + newCommonMovie.rj);

                    CommonMovies.add(newCommonMovie); //the array is in the newCommonMovie class
                    //System.out.println(newCommonMovie.Name + " " + newCommonMovie.ri + " " + newCommonMovie.rj);

                    //commonMovies.add(A.MovieRatings.get(i).Movie);
                    start = j + 1;
                    break;
                } else if (A.MovieRatings.get(i).Movie.compareTo(B.MovieRatings.get(j).Movie) < 0) { //condition 1 //s1 < s2 returns negative value
                    break;  //breaks inner for loop -> i = i+1
                } else {
                    start = j + 1; //condition 2
                }
            }
        }

        //System.out.println("Common movies for user A and B: "+ CommonMovies);
        //System.out.println("Number of movies in common: " + CommonMovies.size());
        //System.out.println(CommonMovies.get(1).Name);
        //System.out.println(CommonMovies.get(1).ri);
        //System.out.println(CommonMovies.get(1).rj);

        //CommonMovies : {Name: ____, ri:___, rj:_____}
        /*
        CommonMovie newCommonMovie = new CommonMovie();
        for(int h = 0; h < CommonMovies.size(); h++) {
            System.out.println("Common Movies: {Name: " + newCommonMovie.Name + ", ri: " + newCommonMovie.ri + ", rj: " + newCommonMovie.rj);
        }
         */

       // System.out.println(CommonMovies.size());

        //return commonMovies; //list of movies A and B both watched
        return CommonMovies;    //this is the array in the class
        //return commonMovies; //output requires an array list. so there it is. Type is <Object> not <String>. <Object> here is <CommonMovie>

    }


    //TODO: Pearson's coefficient
    /* Formula:
   for movie 1,
   Find (User A's rating - their mean) = X
   Find (User B's rating - their mean) = Y
   Find the product XY.
   XY1 -> ratings for movie 1
   XY2 -> ratings for movie 2 etc.
   For n common movies,
   Find sum of XY1 + XY2 + .. + XYn = SUM
   Divide this sum by number of common ratings -> SUM/n.
   Eij = SUM/n
     */

    //I called Pearson in NeighboursPearson function
    private double Pearson(User A, User B) {
        //First find Ei,j
        double Eij;
        double SUM = 0;
        double Pearsons;
        double rik;
        double rjk;

        ArrayList<CommonMovie> CommonMovies;

        CommonMovies = commonRatings(A,B); //commonRatings function outputs an array list of type Object <CommonMovies)/list of movies A and B have watched.

        //System.out.println(CommonMovies.size()); //TODO: there is a problem here. it's 0

        //First result = numerator
	        for (int k = 0; k < CommonMovies.size(); k++) { //for every movie
              //String Movie = commonRatings(A,B).get(k);
                rik = CommonMovies.get(k).ri;
                //System.out.println(rik);
                rjk = CommonMovies.get(k).rj;
                //System.out.println(rjk);
                SUM = SUM + (rik - A.mean) * (rjk - B.mean);
                //System.out.println(SUM);
            }
        //System.out.println("SUM: " +SUM);
        Eij = SUM / CommonMovies.size();
	     //System.out.println("Common Movies: "+CommonMovies.size());
        //System.out.println("Eij: "+Eij);
        Pearsons = Eij / (A.SD * B.SD);
        //System.out.println("This is User A's ID: " + A.ID);
        //System.out.println("This is User B's ID: " + B.ID);
        //System.out.println("This is User A's SD: " + A.SD);
        //System.out.println("This is User B's SD: " + B.SD);
        //ln("This is User A's mean: " + A.mean);
        //System.out.println("This is User B's mean: " + B.mean);

        //test
        if(B.ID.equals("1283204")){
            //System.out.println("SUM: " +SUM);
            //System.out.println("Eij: "+Eij);
            //System.out.println("SD: "+B.SD);
            //System.out.println("SD: "+A.SD);
            //System.out.println(CommonMovies.size());
        }

        //System.out.println("This is Pearson's coefficient: " + Pearsons);

        return Pearsons;
    }


    private int FindUserIndexNeighbours(String UserID){
        //go through the IDs of the array
        //return the index
        int index = -1;
        for(int j = 0; j < OldUsers.size(); j++) {
            if (OldUsers.get(j).ID.equals(UserID)) {
                index = j;
                break;
            }
        }
        return index;
    }

    //oldusers array list will be filled with neighbours
    private ArrayList<User> FindNeighbours(String UserID, String MovieFile) throws FileNotFoundException {
        //String UserID is the chosen person. We're removing them from the array list
        ArrayList<User> Neighbours = new ArrayList<User>(); //should exclude chosen user. this array is currently empty
        ArrayList<String> StringNeighbours = (ArrayList)ReturnUserID(MovieFile).clone();
        //StringNeighbours.remove(UserID); //list has now been modified

        //RecordUserInfo(StringNeighbours);

        //String to Object array list conversion
        /*
        for(int a = 0; a < StringNeighbours.size(); a++){
            User newUser = new User();
            newUser.ID = StringNeighbours.get(a);
            Neighbours.add(newUser);
        }

         */

        Neighbours = RecordUserInfo(StringNeighbours); //this is now an array list of type object...

        //test
        //System.out.println("neighbours with chosen user size: "+Neighbours.size());



        //other method: for loop to go through neighbours list and remove UserA if the ID matches
        int index;
        for(int i = 0; i<Neighbours.size(); i++){
            if(Neighbours.get(i).ID.equals(UserID)) {
                index = i;
                Neighbours.remove(index);
            }
        }

        //test
        //System.out.println("neighbours without chosen user size: "+Neighbours.size());
        //System.out.println("inside find neighbours: "+Neighbours.get(0).MovieRatings.size());
        //System.out.println(Neighbours.get(0).ID);



        return Neighbours;
    }

    //input: User A's ID (String) thrown in from PredictRating function
    private ArrayList<Pearsons> NeighboursPearson(String ChosenUser, String MovieFile) throws FileNotFoundException {

        //String ChosenUser is UserA's ID (String)

        User UserB;
        User UserA;

        //array list just for userA bc we need to have their info recorded in OldUsers array list
        //recorduserinfo(userA)

        //ArrayList<String> JustUserA = new ArrayList<>();
        //JustUserA.add(ChosenUser);
        //RecordUserInfo(JustUserA); //the output is an arraylist of type Object

        ArrayList<User> Neighbours = new ArrayList<>();
        Neighbours = FindNeighbours(ChosenUser, MovieFile); //this is my array list of neighbours of type Object <User> //RecordUserInfo called in FindNeighbours

        //test
        //System.out.println("inside neighbours pearson: "+Neighbours.size());

        int index = FindUserIndex(ChosenUser); //index of UserA in OldUsers array list
        UserA = OldUsers.get(index);
        // System.out.println("this is UserA's ID: " +UserA.ID);

        /*
        ArrayList<Double> PearsonNeighbours = new ArrayList<>();

        //System.out.println("Pearson for A and first person: "+ Pearson(UserA,Neighbours.get(0)));

        //calculate Pearson and store it in an array
        for(int i = 0; i < Neighbours.size(); i++) {
            //User UserB = new User();
            UserB = Neighbours.get(i);
            //Pearson(UserA,UserB); //arguments: User A, User B
            // System.out.println("This is User B's ID: " + UserB.ID);
            PearsonNeighbours.add(Pearson(UserA,UserB)); //adding the pearson result to array list
        }

         */

        //System.out.println("Double array list: " + PearsonNeighbours);
        //object instead of double
        //2 - id of user in the neighbours array list, (double) Pearson coefficient
        //sort according to the Pearson
        //I'll know the 5 highest Pearsons

        //create the PearsonNeighboursList arraylist
        ArrayList<Pearsons> PearsonNeighboursList = new ArrayList<>();

        //append a new Pearsons object to the PearsonNeighboursList
        for(int i = 0; i < Neighbours.size(); i++) {
            UserB = Neighbours.get(i);
            Pearsons newPearsons = new Pearsons();
            newPearsons.PearsonCo = Pearson(UserA,UserB); //adding the pearson result to array list
            newPearsons.ID = UserB.ID;
            PearsonNeighboursList.add(newPearsons);

        }

        //this prints everyone in their list, incl. their ID and P's coefficient
        for(int j=0; j<PearsonNeighboursList.size(); j++) {
            //System.out.println("ID: "+PearsonNeighboursList.get(j).ID + ", Pearson's coefficient: " + PearsonNeighboursList.get(j).PearsonCo);
        }
        //System.out.println("this should be size of array before removing undesirables: "+PearsonNeighboursList.size());

        //create new object of that type
        //assign value to attribute of object
        //append object to arraylist of type object

        //remove Pearson's Coefficients that are out of range and are NaN
        for(int j =0; j<PearsonNeighboursList.size();j++){
            if(PearsonNeighboursList.get(j).PearsonCo > 1 || PearsonNeighboursList.get(j).PearsonCo < -1 || Double.isNaN(PearsonNeighboursList.get(j).PearsonCo)){
                PearsonNeighboursList.remove(j);
            }
        }
        //System.out.println("this should be size of array after removing undesirables: "+PearsonNeighboursList.size());

        //Print final PearsonNeighboursList (unsorted)
        for(int jaguar=0; jaguar<PearsonNeighboursList.size(); jaguar++) {
           //System.out.println("unsorted list: ID: "+PearsonNeighboursList.get(jaguar).ID + ", Pearson's coefficient: " + PearsonNeighboursList.get(jaguar).PearsonCo);
        }

       // System.out.println("this should be size of sorted array after removing undesirables: "+PearsonNeighboursList.size());

        //sort in descending order
        Collections.sort(PearsonNeighboursList, Collections.reverseOrder());

        //print sorted list
        for(int jelly =0; jelly<PearsonNeighboursList.size(); jelly++){
           // System.out.println("Sorted list: "+PearsonNeighboursList.get(jelly).ID + ", Pearson's coefficient: "+PearsonNeighboursList.get(jelly).PearsonCo);
        }

        //print top 5
        for(int jupiter =0; jupiter<5; jupiter++){
           // System.out.println("top 5: "+PearsonNeighboursList.get(jupiter).ID + ", Pearson's coefficient: "+PearsonNeighboursList.get(jupiter).PearsonCo);

        }

        //new array list with just top 5
        ArrayList<Pearsons> KNN = new ArrayList<>();

        //append the objects to the new array list
        for(int jupiter =0; jupiter<5; jupiter++){
            KNN.add(PearsonNeighboursList.get(jupiter));
        }

        //System.out.println(KNN.size());
        return KNN;


    }

    //output: User A's predicted rating for a selected movie
    //input: User A's ID (String) and name of movie (String)
    private double PredictRating(String UserA_ID, String MovieFile) throws FileNotFoundException {
        //Predicted rating:
        //Top SUM = A and j's pearson's coefficient*(rating j gave to movie m - j's mean) + A and l's pearson coefficient*(rating l gave to movie m - j's mean)+...
        //Bottom SUM = magnitude of (A and j's pearson's coefficient + A and l's pearson's coefficient... +...)
        //Predicted rating = (Top SUM/Bottom SUM) + A's mean


        //required values:
        //the Pearson's coefficient of top 5
        //the rating of top 5 for movie m
        //mean of each top 5
        //mean of user A

        //this used to be the start of code for this function
        ArrayList<Pearsons> KNN = new ArrayList<>();
        KNN = NeighboursPearson(UserA_ID, MovieFile); //array list of top 5 neighbours

        //TODO: for RMSE, check if predicted rating already exists in the arraylist
        //UserA
        double userAmean = 0;
        int userAindex=0;
        userAindex = FindUserIndex(UserA_ID);
        User UserA = OldUsers.get(userAindex);
        userAmean = UserA.mean;
        int movieindex = 0;

        //I need this to get the actual rating
        for(int r = 0; r < UserA.MovieRatings.size(); r++) {
            if (UserA.MovieRatings.get(r).Movie.equals(MovieFile)) {
                movieindex = r;
            }
        }

        //if predicted rating already exists
        if (UserA.MovieRatings.get(movieindex).PredictedRating != -1)
            return UserA.MovieRatings.get(movieindex).PredictedRating;


        //this used to be the start of code for this function
        //ArrayList<Pearsons> KNN = new ArrayList<>();
        //KNN = NeighboursPearson(UserA_ID);

        double predictedrating = 0;
        double rjm = 0;
        double mean = 0;
        int foundindex;
        double SUM = 0;
        double BOTTOMSUM = 0;


        for(int q = 0; q < KNN.size(); q++) {

            foundindex = FindUserIndex(KNN.get(q).ID); //we got the index of first person in oldusers array list

            User UserB = OldUsers.get(foundindex);

            //get User B's rating for Movie M
            for(int r = 0; r < UserB.MovieRatings.size(); r++) {
                if (UserB.MovieRatings.get(r).Movie.equals(MovieFile)){
                    rjm = UserB.MovieRatings.get(r).Rating;
                    mean = UserB.mean;

                    //test
                    System.out.println("UserID: " + UserB.ID + ", rjm: " + rjm + ", mean: " + mean);
                    break;
                }
            }
            SUM = SUM + KNN.get(q).PearsonCo * (rjm - mean);
        }

        //test
        for (int k=0; k <KNN.size(); k++){
            System.out.println("Pearson's coefficient in order: " + KNN.get(k).PearsonCo);
        }


        //Denominator
        for (int j=0; j<KNN.size();j++){
            BOTTOMSUM = BOTTOMSUM + Math.abs(KNN.get(j).PearsonCo);
        }

        //UserA variables used to be here

        predictedrating = (SUM / BOTTOMSUM) + userAmean;
        System.out.println("User A's mean: "+ userAmean);

        //predicted rating
        System.out.println("User A's predicted rating is: " + predictedrating);


        // code for finding index of movie used to be here


        //System.out.println("User A has watched " + OldUsers.get(userAindex).MovieRatings.size() + "movies.");

        //actual rating
        System.out.println("User A's actual rating is: " + UserA.MovieRatings.get(movieindex).Rating);

        return predictedrating;
    }


    private void RMSEUser(String UserA_ID) throws FileNotFoundException {
        //values I need:
        //number of movies a user has watched (size of MovieRatings array)
        //actual rating for each movie
        //predicted rating for each movie

        //important: we have to run predict rating first!
        //there will be no such thing as an OldUsers array list UNTIL we run PredictRating();

        //I need to have a working OldUsers array list...
        //I have called on this function just so I have all the info that I need
        //but I won't use it. Sole purpose is to have a working OldUsers array list with all the info
        PredictRating(UserA_ID, "moviedata/mv_0000002.txt"); //it's ok to hardcode movie here bc when you try to find a particular user, you will already know what movie file they are in
        //this works perfectly. Now we just need to find the rating for all other movies


        int index;
        index = FindUserIndex(UserA_ID);
        User UserA = OldUsers.get(index);
        int numberofmovies = UserA.MovieRatings.size();
        double predictedrating;
        String MovieFile;

        System.out.println("USERID: "+ UserA.ID);

        for(int i = 0; i < UserA.MovieRatings.size(); i++){


            MovieFile = UserA.MovieRatings.get(i).Movie;

            System.out.println("MovieFile: "+MovieFile);

            predictedrating = PredictRating(UserA_ID, MovieFile); //iterate through all movie files

            System.out.println("predicted rating: " + predictedrating);

            UserA.MovieRatings.get(i).PredictedRating = predictedrating; //assign value to attribute of object
        }

        System.out.println("Size of array: " + UserA.MovieRatings.size());


        for(int i = 0; i < UserA.MovieRatings.size();i++){
            System.out.println("Predicted Rating: " + UserA.MovieRatings.get(i).PredictedRating + ", Actual Rating: " + UserA.MovieRatings.get(i).Rating);
        }



        double SUM = 0;
        double diff = 0;
        for(int i=0; i <UserA.MovieRatings.size();i++){
           diff = (UserA.MovieRatings.get(i).PredictedRating - UserA.MovieRatings.get(i).Rating);
            SUM = SUM + diff*diff;
        }

        double rmseUser = 0;
        rmseUser = Math.sqrt(SUM/numberofmovies);

        System.out.println("rmse_user: "+rmseUser);

    }

    private void RMSEMovie(String MovieFile) throws FileNotFoundException {
        //number of ratings -> found using ReturnUserID
        ArrayList<String> MovieWatchers = new ArrayList<>();
        MovieWatchers = ReturnUserID(MovieFile); //this is the array list
        int numberofratings = MovieWatchers.size();



    }

    public static void main(String[] args) throws Exception {

        long startTime = System.currentTimeMillis();

        MovieRecommendationEngine Engine = new MovieRecommendationEngine(); //as soon as we start the engine, we go through the file and record everyone's ID and ratings

        //uncomment the following code and
        //hardcode the chosen user and movie here ONLY if I want to know predicted rating
        //Engine.PredictRating("1283204", "moviedata/mv_0000002.txt");

        //for RMSE
        Engine.RMSEUser("1283204");
        //Engine.RMSEMovie("moviedata/mv_0000002.txt");


        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        //System.out.println(totalTime);

        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.print("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds");


    }
}









