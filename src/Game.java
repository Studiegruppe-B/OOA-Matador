import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

    private final String GAME_NAME;
    private final int MAX_PLAYERS;
    private final int MIN_PLAYERS;
    private int playerCount;
    private final ArrayList<Player> players;
    private Player currentPlayer;
    private int roundCount;
    private boolean continueGame;

    public Game(String name, int minPlayers, int maxPlayers){
        this.GAME_NAME = name;
        this.MAX_PLAYERS = maxPlayers;
        this.MIN_PLAYERS = minPlayers;
        this.continueGame = true;
        players = new ArrayList<>();

    }
    private void startSession(){
        TextUI.displayMessage("Velkommen til " + this.GAME_NAME + "!"); // Welcome message
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
                        TextUI.displayMessage("Forkert valg. Prøv igen!");
                }
            }

        }
    }

    public final void runGameLoop() {
        this.startSession();

        this.roundCount = 0;
        while(continueGame) {
            this.currentPlayer = players.get(this.roundCount % this.playerCount);

            continueGame = TextUI.promptBinary("Fortsæt?");
            this.roundCount++;
        }
        this.endSession();
    }

    private void registerPlayers(){
        int playerCount = TextUI.promptNumeric("Tast antal deltagere");

        if (playerCount <= MAX_PLAYERS && playerCount >= MIN_PLAYERS) {
            while(this.players.size() < playerCount) { // Create a player until MAX_PLAYERS is reached.
                String playerName = TextUI.promptText("Tast spiller navn");
                this.createPlayer(playerName, 0);
            }
            this.playerCount = players.size(); // Initialize player count variable.
            Collections.shuffle(players); // Shuffle player List
            return;
        }

        // Run method recursively until correct input is given.
        TextUI.displayMessage("Ugyldigt spiller antal '" + playerCount + "'" + ". Gyldigt interval -> (" + this.MIN_PLAYERS + " - " + this.MAX_PLAYERS + ")");
        registerPlayers();
    }


    private void createPlayer(String name, int score){
        Player p = new Player(name, score);
        players.add(p);
    }

    public void displayPlayers(){
        for(Player p : players){
            System.out.println(p);
        }
    }

    private void endSession(){
        TextUI.promptText("Lukker spil. Tak for at spille!");

        ArrayList<String> playersData = new ArrayList<>();

        for(Player p : players){
            playersData.add(p.toString());
        }
        FileIO.saveData(playersData, "data/playerData.csv", "Name, Balance"); // persister data
    }
}