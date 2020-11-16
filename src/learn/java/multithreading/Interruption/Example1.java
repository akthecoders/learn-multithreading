package learn.java.multithreading.Interruption;

public class Example1 {
    public static void main(String[] args) {
        Thread blockThread = new Thread(new BlockingThread());
        blockThread.start();
        blockThread.interrupt(); // IF we don't use this then the main thread will exit but the blockThread will keep on running until finished.
        // By using this we ask our system to destroy the blockThread as well.
    }

    private static class BlockingThread implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        }
    }
}
