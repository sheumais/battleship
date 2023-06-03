
/*  Type        Name        Length  Grid Representation
    ---------------------------------------------------
    battleship  Battleship  4       B 
    carrier     Carrier     5       C
    destroyer   Destroyer   3       D
    submarine   Submarine   3       S
    patrol boat Patrol Boat 2       P                   */

public class Ship {
    private String type = "";
    private Segment[] segments;

    private Ship(String t) {
        type = t;
        segments = new Segment[5];
        for (int i = 0; i < segments.length; i++) {
            segments[i] = new Segment(this);
        }
    }

    public int length() {
        switch(type) {
            case "battleship":
            return 4;

            case "carrier":
            return 5;

            case "destroyer":
            return 3;

            case "submarine":
            return 3;

            case "patrol boat":
            return 2;
        }
        return 0;
    }

    public String name() {
        switch(type) {
            case "battleship":
            return "Battleship";

            case "carrier":
            return "Carrier";

            case "destroyer":
            return "Destroyer";

            case "submarine":
            return "Submarine";

            case "patrol boat":
            return "Patrol Boat";
        }
        return "";
    }

    public boolean sunk() {
        for (int i = 0; i < length(); i++) {
            if (!segments[i].hit()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        switch(type) {
            case "battleship":
            return "B";

            case "carrier":
            return "C";

            case "destroyer":
            return "D";

            case "submarine":
            return "S";

            case "patrol boat":
            return "P";
        } 
        return "";
    }

    public Segment getSegment(int index) {
        if (index > length() || index < 1) {return null;}
        return segments[index-1];
    }

    public static Ship createShip(String type) {
        switch(type.toLowerCase()) {
            case "battleship":
            return new Ship("battleship");

            case "carrier":
            return new Ship("carrier");

            case "destroyer":
            return new Ship("destroyer");

            case "submarine":
            return new Ship("submarine");

            case "patrol boat":
            return new Ship("patrol boat");
        } 
        return null;
    }
}
