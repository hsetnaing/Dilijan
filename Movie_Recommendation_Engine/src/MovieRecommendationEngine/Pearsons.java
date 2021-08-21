package MovieRecommendationEngine;

//implements means?
//Animal will a tail
//Animal has 4 legs
//Dog implements Animal
//Dog knows how to bark
//in addition to everything animal does

//first create a class Animal
//breathe();
//tails = 1;
//legs = 4;

//to not repeat the code, we just say
//Dog implements Animal
//it means Dog can use Animal's functions




public class Pearsons implements Comparable<Pearsons>  {

    String ID;
    double PearsonCo; //Pearson's coefficient

    //comparable makes it so it knows what's bigger/smaller and we can use it in .sort
    public int compareTo(Pearsons p) {
        int comp = 0; //if they are equal
        if (PearsonCo < p.PearsonCo) {
            comp = -1;
        } else if (PearsonCo > p.PearsonCo) {
            comp = 1;
        }
        return comp;

    }
}


/*note:
Use int Double.compare(double d1, double d2).
You also should not do
this.field - other.field,
since that may return incorrect result in case of numeric overflow.
Use int Integer.compare(int x, int y).

https://stackoverflow.com/questions/37670328/comparable-with-doubles
 */