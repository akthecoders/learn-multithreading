package learn.java.multithreading;

public class ResourceSharing {

    public static void main(String[] args) throws InterruptedException {
        IncDec incDec = new IncDec();

        IncThread inc = new IncThread(incDec);
        DecThread dec = new DecThread(incDec);

        inc.start();
        dec.start();
        inc.join();
        dec.join();

        System.out.println("Value : " + incDec.getCounter());
    }

    private static class DecThread extends Thread {
        IncDec incDec;

        public DecThread(IncDec incDec) {
            this.incDec = incDec;
        }

        @Override
        public void run() {
            for(int i = 0; i < 1000; i++) {
                this.incDec.dec();
            }
        }
    }

    private static class IncThread extends Thread {
        IncDec incDec;

        public IncThread(IncDec incDec) {
            this.incDec = incDec;
        }

        @Override
        public void run() {
            for(int i = 0; i < 1000; i++) {
                this.incDec.inc();
            }
        }
    }

    private static class IncDec {
        int counter = 0;

        public void inc() {
            counter++;
        }

        public void dec() {
            counter--;
        }

        public int getCounter() {
            return counter;
        }
    }
}
