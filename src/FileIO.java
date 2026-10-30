import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public final class FileIO {
    public static ArrayList<String> readData(String path){
        File file = new File(path);
        ArrayList<String> data = new ArrayList<>();
        try (Scanner scan = new Scanner(file)) {
            if (scan.hasNextLine()) { // Check if file has a next line!
                scan.nextLine(); //Skip header
                while(scan.hasNextLine()) {
                    data.add(scan.nextLine());
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("[FileIO] Could not read data from file. File not found!");
        }
        return data;
    }

    public static void saveData(ArrayList<String> data, String path, String header){
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

    public static boolean isEmpty(String path) {
        File file = new File(path);
        try (Scanner scan = new Scanner(file)) {
            if (scan.hasNextLine()) {
                return false;
            }
        } catch (IOException e) {
            System.out.println("[FileIO] File not found!");
        }
        return true;
    }
}

