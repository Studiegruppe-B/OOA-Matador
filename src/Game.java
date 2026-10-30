import java.util.ArrayList;
import java.util.List;

public class Game {

    private final String name;
    private final int maxPlayers;
    private final ArrayList<Player> players;

    public Game(String name, int maxPlayers){
        this.name = name;
        this.maxPlayers = maxPlayers;
        players = new ArrayList<>();

    }
    public void startSession(){
        TextUI.displayMessage("Velkommen til Matador!"); // Welcome message
        if(FileIO.isEmpty("data/playerData.csv")){
            registerPlayers();
        } else {
            ArrayList<String> data = FileIO.readData("data/playerData.csv");
            while (true) {
                int answer = Integer.parseInt(TextUI.promptText("Vil du lave et nyt spil(1)\n Eller bruge nuværende data?(2)"));
                switch (answer) {
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
                        throw new IllegalArgumentException("Ugyldigt input, tast 1 eller 2: " + answer);
                }
            }

        }
    }

    public void registerPlayers(){
        int playerNumber = TextUI.promptNumeric("Tast antal deltagere");

        while(this.players.size() < playerNumber) {
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