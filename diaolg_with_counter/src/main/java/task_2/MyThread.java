package task_2;

import java.util.concurrent.Callable;

public class MyThread extends Thread implements Callable<String> {
    private final int iterations;


    public MyThread(ThreadGroup group, String name, int iterations) {
        super(group, name);
        this.iterations = iterations;
    }

    @Override
    public String call() {
        int count;
        try {
            for (count = 0; count <= iterations; count++) {
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Hello everyone! I'm " + Thread.currentThread().getName());
            }
        } finally {
            System.out.printf("%s is finished %n", Thread.currentThread().getName());
        }
        return Thread.currentThread().getName() + " printed " + count + " messages";
    }
}