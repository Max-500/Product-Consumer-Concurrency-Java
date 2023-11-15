package models;

import java.util.Arrays;
import java.util.Random;

public class Monitor {
    private final int TOTAL = 10;
    private int buffer[] = new int[TOTAL];
    private int lleno;
    private Random random = new Random(System.currentTimeMillis());

    public Monitor() {
        for (int i = 0; i < TOTAL; i++) {
            this.buffer[i] = 0;
        }
        lleno = 0;
    }

    public synchronized void insert() {
        int value = random.nextInt(400) + 100;
        int index = 0;
        while (lleno == TOTAL) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while ((buffer[index] != 0)) {
            index++;
        }
        buffer[index] = value;
        System.out.println("P: " + toString());
        lleno++;
        this.notifyAll();

    }

    public synchronized void extract() {
        int index = 0;
        while (lleno == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (buffer[index] == 0) {
            index++;
        }
        buffer[index] = 0;
        System.out.println("C: " + toString());
        lleno--;
        this.notifyAll();

    }

    @Override
    public String toString() {
        return "Monitor{" + "buffer=" + Arrays.toString(buffer) + "}";
    }
}