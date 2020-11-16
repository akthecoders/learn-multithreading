package learn.java.multithreading.Interruption;

import java.math.BigInteger;

public class Example2 {
    public static void main(String[] args) {
        Thread thread = new Thread(new LongComputation(new BigInteger("2"), new BigInteger("10")));
        thread.start();
        thread.interrupt(); // This is not going to work because we have not done any logic for InterruptedException in the below thread.
        // ONly when we will have implemented the Interupped Exception logic then only it will work.
        // If we don't want to use thread.interrrupt then we can first make this thread as daemon thread
        // like thread.setDaemon(true); After this the thread will terminate with the main thread.
    }

    private static class LongComputation implements Runnable {
        BigInteger base;
        BigInteger power;

        public LongComputation(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + " ^ " + power + " = " + pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;
            for(BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0 ; i = i.add(BigInteger.ONE)) {
                if(Thread.currentThread().isInterrupted()) {
                    System.out.println("Prematurly exiting ! Interuppted detected");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }
            return result;
        }
    }
}
