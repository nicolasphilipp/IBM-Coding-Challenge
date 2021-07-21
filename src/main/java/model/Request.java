/*
 * Ersteller: Nicolas Philipp
 */

package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Request {
    private int current;
    private List<Integer> destination = new ArrayList<>();
    private Direction direction;

    public Request(Direction direction, int current, int destination){
        if(direction == null){
            throw new IllegalArgumentException("direction must not be null");
        }

        this.direction = direction;
        this.current = current;
        this.destination.add(destination);
    }

    public Request(Direction direction, int current, int... destination){
        if(direction == null){
            throw new IllegalArgumentException("direction must not be null");
        }

        this.direction = direction;
        this.current = current;
        Arrays.stream(destination).forEach(x -> this.destination.add(x));
    }

    @Override
    public String toString(){
        return "current floor: " + current + ", destination floor(s): " + destination + ", direction: " + direction;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public List<Integer> getDestination() {
        return destination;
    }

    public void setDestination(List<Integer> destination) {
        this.destination = destination;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
