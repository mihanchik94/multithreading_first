import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ParallelSumArray extends RecursiveTask<Integer> {
    private final int[] array;

    public ParallelSumArray(int[] array) {
        this.array = array;
    }

    @Override
    protected Integer compute() {
        if (array.length <= 2) {
            return ForkJoinTask.invokeAll(createSubTasks())
                    .stream()
                    .mapToInt(ForkJoinTask::join)
                    .sum();
        } else {
            return SumArray.sum(array);
        }
    }

    private Collection<ParallelSumArray> createSubTasks() {
        List<ParallelSumArray> dividedTasks = new ArrayList<>();
        dividedTasks.add(new ParallelSumArray(
                Arrays.copyOfRange(array, 0, array.length / 2)));
        dividedTasks.add(new ParallelSumArray(
                Arrays.copyOfRange(array, array.length / 2, array.length)));
        return dividedTasks;
    }

    public static int sumAsync(int[] array) {
        ParallelSumArray task = new ParallelSumArray(array);
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        return pool.invoke(task);
    }
}