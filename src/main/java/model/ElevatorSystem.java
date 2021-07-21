/*
 * Ersteller: Nicolas Philipp
 */

package model;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ElevatorSystem {
    private final ExecutorService executorService;
    private final List<Elevator> elevatorList;

    public ElevatorSystem(Elevator[] elevators){
        this.elevatorList = new ArrayList<>();
        this.elevatorList.addAll(Arrays.asList(elevators));
        this.executorService = Executors.newFixedThreadPool(elevators.length);
    }

    public void addRequest(Request request){
        Optional<Elevator> optElevator = elevatorList.stream().filter(elevator -> !elevator.isOccupied()).findFirst();

        if(optElevator.isPresent()){
            Elevator e = optElevator.get();
            e.travel(request);
            executorService.submit(e);

        } else{
            System.out.println("All elevators occupied");

            while(!optElevator.isPresent()){
                optElevator = elevatorList.stream().filter(elevator -> !elevator.isOccupied()).findFirst();
            }

            Elevator e = optElevator.get();
            e.travel(request);

            System.out.println("Taking elevator with ID: " + e.getId());

            executorService.submit(e);
        }
    }

    public void checkAvailableElevators(){
        List<Elevator> elevators = elevatorList.stream().filter(elevator -> !elevator.isOccupied()).collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        sb.append("The following elevators (ID) are available: ");

        if(elevators.isEmpty()){
            sb.append("none");
        } else{
            for(Elevator e : elevators){
                sb.append(e.getId() + ", ");
            }

            sb.delete(sb.length() - 2, sb.length());
        }

        System.out.println(sb.toString());
    }

    public void shutdown(){
        executorService.shutdown();
    }

    @Override
    public String toString() {
        return "ElevatorSystem{" +
                "elevatorList=" + elevatorList +
                '}';
    }
}
