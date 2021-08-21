package MovieRecommendationEngine;

import java.util.ArrayList;

public class CommonMovie {

    //CommonMovies : {Name: ____, ri:___, rj:_____}

    String Name; //Name of the chosen MOVIE
    double ri; //rating User i/A gave for movie k
    double rj; //rating User j/B gave for movie k

    //ArrayList<CommonMovie> CommonMovies = new ArrayList<CommonMovie>();

    // overridding toString() to print name
    @Override
    public String toString(){
        return Name;
    }
}
