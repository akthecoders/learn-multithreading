package learn.java.multithreading;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreadJoinAndCoordination {
    public static void main(String[] args) throws InterruptedException {
        List<Long> inputNumbers = Arrays.asList(1000000L, 35L, 354L, 24L, 46L, 23L, 556L);

        List<FactorialThread> factThreads = new ArrayList<>();

        for(Long lng: inputNumbers) {
            factThreads.add(new FactorialThread(lng));
        }

        for(Thread thread: factThreads) {
            thread.start();
        }

        for(Thread thread: factThreads) {
            thread.join(500);
        }

        for (int i = 0; i < inputNumbers.size(); i++) {
            FactorialThread factorialThread = factThreads.get(i);
            if (factorialThread.isFinished()) {
                System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
            } else {
                System.out.println("The calculation for " + inputNumbers.get(i) + " is still in progress");
            }
        }

        for(Thread thread: factThreads) {
            thread.interrupt();
        }
    }

    public static class FactorialThread extends Thread {
        private long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;

        public FactorialThread(long inputNumber) {
            this.inputNumber = inputNumber;
        }

        @Override
        public void run() {
            this.result = factorial(inputNumber);
            this.isFinished = true;
        }

        public BigInteger factorial(long n) {
            BigInteger tempResult = BigInteger.ONE;

            for (long i = n; i > 0; i--) {
                if(Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " is interuppted !");
                    return tempResult;
                }
                tempResult = tempResult.multiply(new BigInteger((Long.toString(i))));
            }
            return tempResult;
        }

        public BigInteger getResult() {
            return result;
        }

        public boolean isFinished() {
            return isFinished;
        }
    }
}
