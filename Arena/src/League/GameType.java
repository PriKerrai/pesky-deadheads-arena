//Fredrik
package League;

public class GameType {
        private String name;
        private int minPlayer;
        private int maxPlayer;
       
        public GameType(String name, int minPlayer, int maxPlayer) {
                this.name = name;
                this.minPlayer = minPlayer;
                this.maxPlayer = maxPlayer;
        }

        public String getName() {
                return name;
        }
       
        public void setName(String name) {
                this.name = name;
        }

        public int getMaxPlayer() {
                return maxPlayer;
        }
       
        public int getMinPlayer() {
                return minPlayer;
        }

    public void setNumberOfPlayers(int minimum, int maximum) {
        if (minimum > 0) {this.minPlayer = minimum;}
        if (maximum >= 0) {this.maxPlayer = maximum;}
    }
}
