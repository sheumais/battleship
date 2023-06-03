public class Cell {
    private boolean occupied;
    private boolean hit;
    private Segment seg;

    public Cell() {
        occupied = false;
        hit = false;
    }

    public void placeSegment(Segment s) {
        if (occupied == false && s != null) {
            seg = s;
            occupied = true;
        }
    }

    public void attack() {
        hit = true;
        if (occupied) {
            seg.attack();
        }
    }

    public boolean hasBeenHit() {
        return hit;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public String displaySetup() {
        if (occupied == false) {return ".";}
        else {return seg.getShip().toString();}
    }

    @Override
    public String toString() {
        if (!hit) {return ".";}
        if (hit && !occupied) {return "O";}
        if (hit && occupied && !seg.getShip().sunk()) {return "X";}
        return displaySetup();
    }
}
