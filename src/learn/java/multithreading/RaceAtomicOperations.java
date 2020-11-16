package learn.java.multithreading;

import java.util.Random;

public class RaceAtomicOperations {
    public static void main(String[] args) {
        Metrics metrics = new Metrics();

        BusinessLogic bs1 = new BusinessLogic(metrics);
        BusinessLogic bs2 = new BusinessLogic(metrics);

        MetricsPrinter printer = new MetricsPrinter(metrics);

        bs1.start();
        bs2.start();

        printer.start();
    }

    private static class MetricsPrinter extends Thread {
        Metrics metric;
        public MetricsPrinter(Metrics metric) {
            this.metric = metric;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                }
                double currentAverage = metric.getAverage();
                System.out.println("Current Average is " + currentAverage);
            }
        }
    }

    private static class BusinessLogic extends Thread {
        Metrics metric;
        Random rand;
        public BusinessLogic(Metrics metric) {
            this.metric = metric;
            this.rand = new Random();
        }

        @Override
        public void run() {
            while(true) {
                long start = System.currentTimeMillis();
                try {
                    Thread.sleep(this.rand.nextInt(15));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long end = System.currentTimeMillis();
                metric.addSample(end - start);
            }
        }
    }

    private static class Metrics {
        private long count = 0;
        private volatile double average = 0.0;

        public synchronized void addSample(long sample) {
            double currentSum = average * count;
            count++;
            average = (currentSum + sample) / count;
        }

        public double getAverage() {
            return average;
        }
    }
}
