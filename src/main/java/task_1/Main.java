package task_1;

public class Main {
    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("myThreadGroup");

        Thread firstThread = new MyThread(group, "Thread 1");
        Thread secondThread = new MyThread(group, "Thread 2");
        Thread thirdThread = new MyThread(group, "Thread 3");
        Thread fourthThread = new MyThread(group, "Thread 4");

        try {
            Thread.sleep(15000);
            group.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}