package Reference;

public class Pearson implements Comparable<Pearson> {

    String ID;
    double pearsoncoefficient;

    public int compareTo(Pearson b) {
        int comp = 0;
        if (pearsoncoefficient < b.pearsoncoefficient) {
            comp = -1;
        }
        else if (pearsoncoefficient > b.pearsoncoefficient) {
            comp = 1;
        }
        return comp;
    }

    //constructor bc I create new instances of the object in the main
    public Pearson(String a, double pr) {
        ID = a;
        pearsoncoefficient = pr;
    }



    //for print statement
    public String toString() {
        return  "ID: " + ID + ", Pearson's coefficient: " + pearsoncoefficient;
    }
}