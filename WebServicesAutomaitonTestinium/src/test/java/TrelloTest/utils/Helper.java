package TrelloTest.utils;

import java.util.Random;

public class Helper {
    public static int randomNumber(int low, int high) {
        Random r = new Random();
        return r.nextInt(high-low) + low;
    }
}
