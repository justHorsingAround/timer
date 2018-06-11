package com.codecool;



import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Timer{
    String nameBuffer;
    Clock clock;


    public void getInput(){
        while(true) {
            Scanner s = new Scanner(System.in);
            System.out.println("Please enter your command (start, check, stop, x) then the thread's name");
            String[] input = s.nextLine().split(" ");
            if(input.length > 1) {
                setNameBuffer(input[1]);
            }
            checkCommand(input[0]);
        }
    }

    public void setNameBuffer(String name){
        if(name != null){
            nameBuffer = name;

        }
    }

    public Runnable getThreadByName(String threadName) {
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        int noThreads = currentGroup.activeCount();
        Thread[] lstThreads = new Thread[noThreads];
        currentGroup.enumerate(lstThreads);

        for (int i = 0; i < noThreads; i++) {
            if(lstThreads[i].getName().equals(threadName)){
                return lstThreads[i];
            }
        }
        return null;
    }


    public void listAllThreads(String name){
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        int noThreads = currentGroup.activeCount();
        Thread[] lstThreads = new Thread[noThreads];
        currentGroup.enumerate(lstThreads);

        for (int i = 0; i < noThreads; i++) {
            System.out.println("Thread No:" + i + " = " + lstThreads[i].getName());
        }
    }

    public void checkCommand(String command){
        if(command.equals("start")){
            clock = new Clock();
            clock.setName(nameBuffer);
            clock.start();
        } else if (command.equals("check")){
            Runnable r = getThreadByName(nameBuffer);
            Clock c = (Clock)r;
            System.out.println("Elapsed time in " + c.getName() + " " + TimeUnit.NANOSECONDS.toSeconds(c.elapsedTime()) + "sec");

        } else if (command.equals("stop")){
            Runnable r = getThreadByName(nameBuffer);
            Clock c = (Clock)r;
            c.kill();
        }
        else if (command.equals("list")){
            listAllThreads(nameBuffer);
        }
        else if(command.equals("exit")){
            System.exit(0);
        }
        else {
            System.out.println("Wrong input");
        }



    }


    public static void main(String[] args) {
        Timer t = new Timer();
        t.getInput();

    }
}
