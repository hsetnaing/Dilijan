package Reference;

import java.util.ArrayList;
import java.util.Collections;

public class TestPearson {

    public static void main(String[] args) {
        ArrayList<Pearson> pearsons = new ArrayList<Pearson>();

        pearsons.add(new Pearson("2059652", 0.13308850666863561));
        pearsons.add(new Pearson("1666394", 0.16159235103212016));
        pearsons.add(new Pearson("1759415", 0.1298149646742004));
        pearsons.add(new Pearson("1959936", 0.09883324222148014));

        pearsons.add(new Pearson("998862", -0.07216518960661622));
        pearsons.add(new Pearson("573975", 0.31065789927072224));
        pearsons.add(new Pearson("392722", -0.06446344895439944));
        pearsons.add(new Pearson("1401650", 0.9080304129098492));
        pearsons.add(new Pearson("988104", 0.21903493678374486));
        pearsons.add(new Pearson("977632", -0.02907419023147904));
        pearsons.add(new Pearson("2557870", 0.30706618005325187));
        pearsons.add(new Pearson("1793899", 0.307544332586326));

        pearsons.add(new Pearson("1888322", 0.19469903858660567));
        pearsons.add(new Pearson("1283598", 0.0019138975058774585));
        pearsons.add(new Pearson("1784150", 0.016627106076487853));
        pearsons.add(new Pearson("2271251", 0.214384686220632));
        pearsons.add(new Pearson("65932", -0.37995714703518857));


        System.out.println(pearsons.size());

        System.out.println("unsorted array: "+ pearsons);
        Collections.sort(pearsons, Collections.reverseOrder());
        System.out.println("sorted array: "+ pearsons);
    }
}