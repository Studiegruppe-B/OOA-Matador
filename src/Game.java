import java.util.ArrayList;
import java.util.List;

public class Game {

    private final String name;
    private final int maxPlayers;
    private final ArrayList<Player> players;

    public Game(String name){
        this.name = name;
        this.maxPlayers = 6;
        players = new ArrayList<>();

    }
    public void startSession(){
        TextUI.displayMessage("Velkommen til Matador!"); // Welcome message
        ArrayList<String> data = FileIO.readData("data/playerData.csv"); // Read data from save file
        if(data.isEmpty()){
            registerPlayers();
        } else {
            boolean loop = true;
            while (loop) {
                int answer = Integer.parseInt(TextUI.promptText("Vil du lave et nyt spil(1)\n Eller bruge nuværende data?(2)"));
                switch (answer) {
                    case 1:
                        registerPlayers();
                        loop = false;
                        break;

                    case 2:
                        for (String s : data) {
                            //instantier en player
                            String[] values = s.split(",");// "tess, 0". --> ["tess", "0"]
                            String name = values[0];
                            int balance = Integer.parseInt(values[1].trim());
                            Player p = new Player(name, balance);
                            players.add(p);
                        }
                        loop = false;
                        break;
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