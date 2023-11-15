package threads;

import java.util.concurrent.ThreadLocalRandom;

import models.Monitor;

public class Consumer implements Runnable {
    private Monitor monitor;
    
    public Consumer(Monitor monitor){
        this.monitor = monitor;
    }

    @Override
    public void run(){
        while (true) {
            monitor.extract();
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(500) + 500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
