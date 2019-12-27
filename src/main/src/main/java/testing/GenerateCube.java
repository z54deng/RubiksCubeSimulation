package testing;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateCube {
    public static void generate() {
        int[] arr = {3,2,2,1,2,5,3,1,0,4,1,0,4,4,2,5,5,5,1,4,3,0,0,0,1,5,2,4,3,1,0,3,0,3,4,0,4,2,5,1,5,2,0,3,2,4,4,1,5,1,3,2,3,5};
        shuffleArray(arr);
        String str = Arrays.toString(arr).replace("," , "").replace(" ","");
        System.out.println(str);
    }

    static void shuffleArray(int[] ar)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

}
