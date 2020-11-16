package learn.java.multithreading.Hackers;

public class PoliceThread extends Thread {
    @Override
    public void run() {
        for(int i = 10; i > 0; i--) {
            try {
                Thread.sleep(1000);
                System.out.println("Time left " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Game Over , You got caught !");
        System.exit(0);
    }
}
