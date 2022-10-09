import java.util.Arrays;

public class ArrayGenerator {
    public static int[] fill(int size) {
        int[] array = new int[size];
        Arrays.fill(array, 1);
        return array;
    }
}