/*
 * Ersteller: Nicolas Philipp
 */

import model.Direction;
import model.Elevator;
import model.Request;
import model.ElevatorSystem;

public class ElevatorService {

    public static void main(String[] args) {
        Elevator[] elevators = {new Elevator(0),
                new Elevator(1),
                new Elevator(2),
                new Elevator(3),
                new Elevator(4),
                new Elevator(5),
                new Elevator(6)};
        ElevatorSystem es = new ElevatorSystem(elevators);

        Request[] requests = {new Request(Direction.UP, 0, 28),
                new Request(Direction.DOWN, 42, 0),
                new Request(Direction.UP, 0, 10, 15, 30, 41),
                new Request(Direction.UP, 0, 16, 24, 35),
                new Request(Direction.DOWN, 35, 28, 17, 11, 0),
                new Request(Direction.DOWN, 13, 0),
                new Request(Direction.DOWN, 48, 15, 0),
                new Request(Direction.UP, 0, 55),
                new Request(Direction.UP, 0, 55)};

        es.addRequest(requests[0]);
        es.addRequest(requests[1]);
        es.addRequest(requests[2]);
        es.addRequest(requests[3]);
        es.addRequest(requests[4]);
        es.addRequest(requests[5]);
        es.checkAvailableElevators();    //expected: only elevator 6 should be available
        es.addRequest(requests[6]);
        es.addRequest(requests[7]);      //expected: all elevators occupied
        es.checkAvailableElevators();    //expected: no available elevators
        es.addRequest(requests[8]);      //expected: all elevators occupied

        try {
            Thread.sleep(3000);     //5500
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        es.checkAvailableElevators();
        es.shutdown();
    }
}
