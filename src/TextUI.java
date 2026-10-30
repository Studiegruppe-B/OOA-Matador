import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class TextUI {
    /*

' shows the options, promts for choices until limit is reached, and returns the user's choices as a list of Strings
+ ArrayList<String> promptChoice(ArrayList<String> options, int limit, String msg)

    */
    private static Scanner scan = new Scanner(System.in);

    public static String promptText(String msg){
        System.out.println(msg);
        String input  = scan.nextLine();
        return input;
    }

    public static boolean promptBinary(String msg){
        System.out.println(msg + " Y/N");
        String input  = scan.nextLine();
        boolean x;
        if (input.equalsIgnoreCase("y")){
            x = true;
            return x;
        }
        if (input.equalsIgnoreCase("N")){
            x = false;
            return x;
        } else {
            System.out.println("Ugyldigt input!: " + input);
             return promptBinary(msg);
        }
    }

    public static void displayMessage(String message){
        System.out.println(message);
    }

    public static int promptNumeric(String msg){
        System.out.println(msg);
        int input = 0;
        try {
            input = scan.nextInt();
        }catch (InputMismatchException e){
            scan.nextLine();
            System.out.println("Skriv et tal");
            input = promptNumeric(msg);
        }
        scan.nextLine();
        return input;
    }

    public static void displayList(ArrayList<String> list, String msg, boolean numbered){//parameter der forstæller OM listen er nummereret eller ej.
        System.out.println(msg);

        for(int i = 0; i< list.size(); i++){

            // String s = numbered?i+1+". "+ list.get(i):list.get(i);
            String s;
            if(numbered){
                s = i+1+". "+ list.get(i);

            }else{

                s = list.get(i);
            }
            System.out.println(s);
        }

       /*
       int i = 0;
       for(String s : list){
            // s = list.get(i);
            System.out.println(s);
            i++;

        }

        */



    }

    public static ArrayList<String> promptChoice(ArrayList<String> options, int limit, String msg){
        ArrayList<String> choices =  new ArrayList<>();


        //kør et while loop, så længe limit ikke er nået
        while(choices.size()<limit ) {
            int input = promptNumeric(msg);
            if(input > 0 && input <= options.size()) {
                String choice = options.get(input-1);
                choices.add(choice);
            }else{
                System.out.println("ugyldigt input");
            }

        }


        return choices;
    }

    public static int promptMenuChoice(ArrayList<String> menu, String msg){
        displayList(menu, msg, true);
        int input = promptNumeric("tast et tal");
        return input;
    }
}