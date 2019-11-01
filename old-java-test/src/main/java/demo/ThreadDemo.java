package demo;

public class ThreadDemo implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(300);
                System.out.println("hello other thread");
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
