import java.util.ArrayList;
import java.util.List;

public class Game {

    private final String name;
    private final int MAX_PLAYERS;
    private final int MIN_PLAYERS;
    private final ArrayList<Player> players;

    public Game(String name, int minPlayers, int maxPlayers){
        this.name = name;
        this.MAX_PLAYERS = maxPlayers;
        this.MIN_PLAYERS = minPlayers;
        players = new ArrayList<>();

    }
    public final void startSession(){
        TextUI.displayMessage("Velkommen til Matador!"); // Welcome message
        if(FileIO.isEmpty("data/playerData.csv")){
            registerPlayers();
        } else {
            ArrayList<String> data = FileIO.readData("data/playerData.csv");
            while (true) {
                switch (Integer.parseInt(TextUI.promptText("Vil du lave et nyt spil (1)\nEller bruge nuværende data? (2)"))) {
                    case 1:
                        registerPlayers();
                        return;
                    case 2:
                        for (String s : data) {
                            //instantier en player
                            String[] values = s.split(",");
                            String name = values[0];
                            int balance = Integer.parseInt(values[1].trim());
                            Player p = new Player(name, balance);
                            players.add(p);
                        }
                        return;
                    default:
                        System.out.println("Forkert valg. Prøv igen!");
                }
            }

        }
    }

    public final void registerPlayers(){
        int playerCount = TextUI.promptNumeric("Tast antal deltagere");

        // Run method recursively until correct input is given.
        if (!(playerCount <= MAX_PLAYERS && playerCount >= MIN_PLAYERS)) {
            System.out.println("Ugyldigt spiller antal '" + playerCount + "'" + ". Gyldigt interval -> (" + this.MIN_PLAYERS + " - " + this.MAX_PLAYERS + ")");
            registerPlayers();
        }

        // Create a player until MAX_PLAYERS is reached.
        while(this.players.size() < playerCount) {
            String playerName = TextUI.promptText("Tast spiller navn");
            this.createPlayer(playerName, 0);
        }
    }


    private void createPlayer(String name, int score){
        Player p = new Player(name, score);
        players.add(p);
    }

    public void displayPlayers(){
        for(Player p:players){
            System.out.println(p);
        }
    }

    public void endSession(){
        ArrayList<String> playersData = new ArrayList<>();
        // persister data

        for(Player p:players){
            playersData.add(p.toString());

        }
        FileIO.saveData(playersData, "data/playerData.csv", "Name, Balance");
    }
}