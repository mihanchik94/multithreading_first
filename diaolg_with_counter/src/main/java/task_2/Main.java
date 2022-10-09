package task_2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    private static final ThreadGroup group = new ThreadGroup("myThreadGroup");
    private static final ExecutorService SERVICE_1 = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final ExecutorService SERVICE_2 = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final List<Callable<String>> TASKS_LIST = new ArrayList<>();
    private static final int MAX_TASKS = 4;
    private static final int MAX_ITERATIONS = 3;

    private static void createTaskList() {
        for (int i = 1; i <= MAX_TASKS; i++) {
            TASKS_LIST.add(new MyThread(group, "Thread " + i, MAX_ITERATIONS));
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        createTaskList();

        String task1 = SERVICE_1.invokeAny(TASKS_LIST);
        System.out.println(task1);

        List<Future<String>> task2 = SERVICE_2.invokeAll(TASKS_LIST);
        task2.forEach(stringFuture -> {
            try {
                System.out.println(stringFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        SERVICE_1.shutdown();
        SERVICE_2.shutdown();
    }
}