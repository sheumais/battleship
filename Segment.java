public class Segment {
        private Ship ship;
        private boolean hit = false;
        public Segment(Ship s) {
                ship = s;
        }
        public boolean hit() {
                return hit;
        }
        public void attack() {
                if (hit == false) {
                        hit = true;
                }
        }
        public Ship getShip() {
                return ship;
        }
        public String toString() {
                return ship.toString();
        }
}
