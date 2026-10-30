import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {


    public ArrayList<String> readData(String path){
        File file = new File(path);
        ArrayList<String> data = new ArrayList<>(); //"Tess, 0", "Lina, 0", "Signe, 0"
        try (Scanner scan = new Scanner(file)) {
            scan.nextLine(); //Skip header
            while(scan.hasNextLine()) {
                data.add(scan.nextLine());
            }
        } catch(FileNotFoundException e) {
            System.out.println("[FileIO] Could not read data from file. File not found!");
        }
        return data;
    }

    public void saveData(ArrayList<String> data, String path, String header){
        File file = new File(path);
        try (FileWriter writer = new FileWriter(file)){
            writer.write(header+"\n"); // Write header
            for(String s: data) {
                writer.write(s+"\n");
            }
        } catch (IOException e) {
            System.out.println("[FileIO] Could not save data to file. File not found!");
        }



    }

}

