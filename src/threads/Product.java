package threads;

import java.util.concurrent.ThreadLocalRandom;

import models.Monitor;

public class Product implements Runnable {
    private Monitor monitor;
    
    public Product(Monitor monitor){
        this.monitor = monitor;
    }

    @Override
    public void run(){
        while (true) {
            monitor.insert();
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(500) + 100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
