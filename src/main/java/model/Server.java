package model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private Thread thread = new Thread(this);

    public Server() {
        this.tasks = new LinkedBlockingQueue<>();
        this.waitingPeriod = new AtomicInteger(0);
        this.thread.start();
    }

    public int getWaitingPeriod() {
        return waitingPeriod.intValue();
    }

    public void setWaitingPeriod(int waitingPeriod) {
        this.waitingPeriod.set(waitingPeriod);
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public Thread getThread() {
        return thread;
    }

    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    @Override
    public void run() {
        while (true) {
            Task currentTask = this.tasks.peek();
            while(currentTask != null && currentTask.getServiceTime() != 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                currentTask.setServiceTime(currentTask.getServiceTime() - 1);
                this.waitingPeriod.set(this.getWaitingPeriod() - 1);
            }
            this.tasks.poll();
        }
    }

    @Override
    public String toString() {
        String result = "";
        for(Task task : this.tasks) {
            result += "[" + task.getId() + " " + task.getArrivalTime() + " " + task.getServiceTime() + "]";
        }
        return result;
    }
}
