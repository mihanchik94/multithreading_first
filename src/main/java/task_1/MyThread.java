package task_1;

public class MyThread extends Thread {

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        start();
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Hello everyone! I'm " + Thread.currentThread().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.printf("%s is finished %n", Thread.currentThread().getName());
        }
    }
}