package learn.java.multithreading;

public class FunctionalThread {
    public static void main(String args[]) {
        Thread childThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("We are in the new thread " + Thread.currentThread().getName());
            }
        });
        childThread.setName("New Worker Thread");
        System.out.println("We are in the thread " + Thread.currentThread().getName() + "before starting");
        childThread.start();
        System.out.println("We are in the thread " + Thread.currentThread().getName() + "after starting");
    }
}
