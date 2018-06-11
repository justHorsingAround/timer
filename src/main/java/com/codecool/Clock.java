package com.codecool;

import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

public class Clock extends Thread {
    long startingTime;
    private AtomicBoolean running = new AtomicBoolean(true);

    public Clock() {
        startingTime = System.nanoTime();
    }

    public void kill(){
        running.set(false);
    }

    public void interrupt() {
        running.set(false);
        this.interrupt();
    }

    public long elapsedTime(){
       return System.nanoTime() - startingTime;
    }

    public void setThreadName(String name){
        Thread.currentThread().setName(name);
    }


    public void run(){
        System.out.println("MyThread running " +  Thread.currentThread().getName());
        while (running.get()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
