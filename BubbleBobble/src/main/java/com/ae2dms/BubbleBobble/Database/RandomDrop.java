package com.ae2dms.BubbleBobble.Database;

import com.ae2dms.BubbleBobble.World.InteractableWorld;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * The {@link com.ae2dms.BubbleBobble.Database.RandomDrop} class extends Service class
 * to generate Task to periodically generate new fruit in random area of the map
 * in the {@link InteractableWorld}.
 * It is used in {@link DataManager#nextLevel() }
 */
public class RandomDrop extends Service<Object> {
    String name;

    /**
     * The constructor of {@code TaskService}
     */
    public RandomDrop(String name){
        this.name=name;
    }

    /**
     * creates a task
     * @return the task
     */
    @Override
    public Task<Object> createTask() {
        return new Task<>() {
            @Override
            protected Integer call() {
                int iterations;
                for (iterations = 0; iterations < 10; iterations++) {
                    if (isCancelled()) {
                        break;
                    }
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException interrupted) {
                    if (isCancelled()) {
                        updateMessage("Cancelled");
                    }
                }
                return iterations;
            }
        };
    }

    /**
     * task succeeded
     */
    @Override
    protected void succeeded(){
        super.succeeded();
        System.out.println(this+"isDone!");
    }

    /**
     * task cancelled
     */
    @Override
    protected void cancelled(){
        super.cancelled();
        System.out.println(this+"isCancelled!");
    }

    /**
     * task failed
     */
    @Override
    protected void failed(){
        super.failed();
        System.out.println(this+"isFailed!");
    }

    /**
     * task running
     */
    @Override
    protected void running(){
        super.running();
        System.out.println(this+"isRunning!");
    }

    /**
     * task scheduled
     */
    @Override
    protected void scheduled(){
        super.scheduled();
        System.out.println(this+"isScheduled!");
    }

    /**
     * get task name
     */
    public String getName() {
        return name;
    }

    /**
     * task name
     */
    @Override
    public String toString() {
        return "Task [ name = " + name + " ]";
    }
}
