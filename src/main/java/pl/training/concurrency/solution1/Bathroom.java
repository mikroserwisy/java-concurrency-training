package pl.training.concurrency.solution1;

import java.util.concurrent.Semaphore;

public class Bathroom {

    private enum State {

        WOMAN, MEN, NONE

    }

    private final Semaphore maxEmployees = new Semaphore(3);
    private State inUseBy = State.NONE;
    private int employeesInBathroom = 0;

    public void maleUseBathroom(String name) throws InterruptedException {
        synchronized (this) {
            while (inUseBy == State.WOMAN) {
                this.wait();
            }
            maxEmployees.acquire();
            employeesInBathroom++;
            inUseBy = State.MEN;
        }
        enterBathroom(name);
        maxEmployees.release();
        synchronized (this) {
            employeesInBathroom--;
            if (employeesInBathroom == 0) {
                inUseBy = State.NONE;
            }
            this.notifyAll();
        }
    }

    public void femaleUseBathroom(String name) throws InterruptedException {
        synchronized (this) {
            while (inUseBy == State.MEN) {
                this.wait();
            }
            maxEmployees.acquire();
            employeesInBathroom++;
            inUseBy = State.WOMAN;
        }
        enterBathroom(name);
        maxEmployees.release();
        synchronized (this) {
            employeesInBathroom--;
            if (employeesInBathroom == 0) {
                inUseBy = State.NONE;
            }
            this.notifyAll();
        }
    }

    private void enterBathroom(String name) throws InterruptedException {
        System.out.println(name + " is using bathroom. Employees in bathroom = " + employeesInBathroom);
        Thread.sleep(1_000);
        System.out.println(name + " is leaving bathroom ");
    }

}