package Reference;



public class mean {

    public static void main(String[] args) {
        double UserMean;
        double sum = 0;
        int n = 10;
        int[] array = new int[]{1,2,3,4,5,6,7,8,9,10};
        int arrayLength = array.length;



        for(int k = 0; k < arrayLength; k++)
            sum = sum + array[k];

        UserMean = sum/n;

        System.out.println(UserMean);
    }
}
