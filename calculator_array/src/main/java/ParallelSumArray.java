import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSumArray extends RecursiveTask<Integer> {
    private final int[] array;

    public ParallelSumArray(int[] array) {
        this.array = array;
    }

    @Override
    protected Integer compute() {
        if (array.length <= 2) {
            return SumArray.sum(array);
        }
        ParallelSumArray left = new ParallelSumArray(Arrays.copyOfRange(array, 0, array.length / 2));
        ParallelSumArray right = new ParallelSumArray(Arrays.copyOfRange(array, array.length / 2, array.length));
        left.fork();
        right.fork();
        int leftNum = left.join();
        int rightNum = right.join();
        return leftNum + rightNum;
    }

    public static int sumAsync(int[] array) {
        ParallelSumArray task = new ParallelSumArray(array);
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        return pool.invoke(task);
    }
}