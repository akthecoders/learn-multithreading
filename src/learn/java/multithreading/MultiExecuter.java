package learn.java.multithreading;

import java.util.List;

public class MultiExecuter {
    List<Runnable> tasks;
    public MultiExecuter(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    public void executeAll() {
        for(Runnable run: tasks) {
            Thread newThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    run.run();
                }
            });
            newThread.run();
        }
    }
}
