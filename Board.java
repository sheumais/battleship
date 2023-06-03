import java.util.Arrays;
import java.util.List;

public class Board {
    Board board;
    private Cell[][] cells;

    public Board() {
        cells = new Cell[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    private boolean isOnBoard(String POS) throws InvalidPositionException {
        String pos = POS.toLowerCase();
        List<String> verticals = Arrays.asList("a","b","c","d","e","f","g","h","i","j");
        
        if (pos.length() < 2 || pos.length() > 3) {
            throw new InvalidPositionException("");
        }

        String vert = String.valueOf(pos.charAt(0));
        String num = pos.substring(1);
    
        try {
            int hori = Integer.parseInt(num);
            if (!verticals.contains(vert) || hori < 1 || hori > 10) {
                throw new InvalidPositionException("");}
        } catch (NumberFormatException e) {
            throw new InvalidPositionException("");}
        
        return true;
    }

    /// assumes cell exists
    private Cell getCell(String POS) throws InvalidPositionException {
        String pos = POS.toLowerCase();
        List<String> verticals = Arrays.asList("a","b","c","d","e","f","g","h","i","j");
        String vert = String.valueOf(pos.charAt(0));
        int hori = Integer.parseInt(pos.substring(1));
        return cells[verticals.indexOf(vert)][hori-1];
    }

    /// also assumes cell exists
    private Cell getCellFromIndex(int[] indices) throws InvalidPositionException {
        Cell cell = cells[indices[0]][indices[1]];
        return cell;
    }

    /// also assumes cell exists
    private int[] getIndices(String POS) {
        String pos = POS.toLowerCase();
        List<String> verticals = Arrays.asList("a","b","c","d","e","f","g","h","i","j");
        String vert = String.valueOf(pos.charAt(0));
        int hori = Integer.parseInt(pos.substring(1));
        int[] z = {verticals.indexOf(vert), hori-1};
        return z;
    } 

    public void placeShip(Ship ship, String pos, String direction)  throws InvalidPlacementException, InvalidPositionException, InvalidShipTypeException {
        if (!direction.equals("across") && !direction.equals("down")) {
            throw new InvalidPlacementException("");}
        if (!isOnBoard(pos)) {
            throw new InvalidPositionException("");}
        if (ship == null) {
            throw new InvalidShipTypeException("");}

        int length = ship.length();
        int[] indices = getIndices(pos);
        if (direction.equals("across")) {
            if (indices[1]+length > 10) { // goes off the board
                throw new InvalidPlacementException("");}

            for (int i=0; i<length; i++) {
                int[] newIndex = {indices[0],indices[1]+i};
                if (getCellFromIndex(newIndex).isOccupied()) {
                    throw new InvalidPlacementException("");
                }
            }

            for (int i=0; i<length; i++) {
                int[] newIndex = {indices[0],indices[1]+i};
                Segment segment = ship.getSegment(i+1);
                getCellFromIndex(newIndex).placeSegment(segment);
            }


        } else {
            assert(direction.equals("down"));
            List<String> verticals = Arrays.asList("a","b","c","d","e","f","g","h","i","j");
            if (indices[0]+length > 10) {
                throw new InvalidPlacementException("");}

            for (int i=0; i<length; i++) {
                int[] newIndex = {indices[0]+i,indices[1]};
                if (getCellFromIndex(newIndex).isOccupied()) {
                    throw new InvalidPlacementException("");
                }
            }

            for (int i=0; i<length; i++) {
                int[] newIndex = {indices[0]+i,indices[1]};
                Segment segment = ship.getSegment(i+1);
                getCellFromIndex(newIndex).placeSegment(segment);
            }

        }
    }

    public void attack(String pos) throws InvalidPositionException {
        if (!isOnBoard(pos)) {
            throw new InvalidPositionException("");}
        getCell(pos).attack();
    }

    public boolean hasBeenHit(String pos) throws InvalidPositionException {
        if (!isOnBoard(pos)) {
            throw new InvalidPositionException("");}
        Cell cell = getCell(pos);
        boolean beenHit = cell.hasBeenHit();
        return beenHit;
    }

    @Override
    public String toString() {
        StringBuilder display = new StringBuilder();
        display.append("  1 2 3 4 5 6 7 8 9 10\n");
        List<String> verticals = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        
        for (int i = 0; i < cells.length; i++) {
            display.append(verticals.get(i)).append(" ");
            for (int j = 0; j < cells[i].length; j++) {
                if (j == 9) {display.append(cells[i][j].toString());break;}
                display.append(cells[i][j].toString()).append(" ");
            }
            display.append("\n");
        }
        return display.toString();
    }

    public String displaySetup() {
        StringBuilder display = new StringBuilder();
        display.append("  1 2 3 4 5 6 7 8 9 10\n");
        List<String> verticals = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");

        for (int i = 0; i < cells.length; i++) {
            display.append(verticals.get(i)).append(" ");
            for (int j = 0; j < cells[i].length; j++) {
                if (j == 9) {display.append(cells[i][j].displaySetup());break;}
                display.append(cells[i][j].displaySetup()).append(" ");
            }
            display.append("\n");
        }
        return display.toString();
    }
}
