
public class Main {


    public static void main(String[] args) {


        Game g = new Game("Yatzy", 2, 6);
        g.startSession();
        g.displayPlayers();
        g.endSession();
    }
}