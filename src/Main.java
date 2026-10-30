
public class Main {


    public static void main(String[] args) {


        Game g = new Game("Yatzy", 6);
        g.startSession();
        g.registerPlayers();
        g.displayPlayers();
        g.endSession();



    }
}