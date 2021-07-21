/*
 * Ersteller: Nicolas Philipp
 */

package model;

public class Elevator implements Runnable {
    private final int id;
    private boolean occupied;
    private Request currentRequest;

    public Elevator(int id){
        this.id = id;
        this.occupied = false;
        this.currentRequest = null;
    }

    public void travel(Request request){
        this.setOccupied(true);
        this.currentRequest = request;
    }

    public int getId() {
        return id;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Request getCurrentRequest() {
        return currentRequest;
    }

    public void setCurrentRequest(Request currentRequest) {
        this.currentRequest = currentRequest;
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "id=" + id +
                ", occupied=" + occupied +
                ", currentRequest=" + currentRequest +
                '}';
    }

    @Override
    public void run() {
        Request r = currentRequest;
        System.out.println("Elevator " + id + " running: " + r.toString() + " | " + Thread.currentThread().getName());
        int time = 0;

        if(r.getDirection() == Direction.UP){
            time = r.getDestination().get(r.getDestination().size() - 1);
        } else{
            time = r.getCurrent();
        }

        try {
            Thread.sleep(time * 100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        currentRequest = null;
        this.setOccupied(false);
    }
}
