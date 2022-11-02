import java.util.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.stream.Collectors;

public class NuchangeAssignment {

    private static Map<String, String[]> urlDetails = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true){
            printCommand();
            String input = sc.nextLine();
            if(!isValidCommand(input)){
                System.out.println("[ERROR] Please enter a valid command.");
                continue;
            }
            if(input.startsWith("storeurl")){
                storeurl(input);
            }
            if(input.startsWith("get")){
                getUrlSortKey(input);
            }
            if(input.startsWith("count")){
                getCount(input);
            }
            if(input.equals("list")){
                listUrl();
            }
            if(input.equals("exit")){
                exit();
            }
        }

    }

    private static void printCommand(){
        System.out.println("--------------------------------------------------------");
        System.out.println("Commands:");
        System.out.println("storeurl <URL>");
        System.out.println("get <URL>");
        System.out.println("count <URL>");
        System.out.println("list");
        System.out.println("exit");
        System.out.println("--------------------------------------------------------");
        System.out.println("Please enter the command:");
    }

    private static boolean isValidCommand(String input){
        if(input.equals("exit") || input.equals("list")){
            return true;
        } else {
            String[] commandSplit = input.split("\\s+");
            if(commandSplit.length !=2){
                return false;
            }else {
                if(commandSplit[0].equals("storeurl")||
                        commandSplit[0].equals("get")||
                        commandSplit[0].equals("count")){
                    return true;
                }
            }
        }
        return false;
    }

    private static void storeurl(String input){
        String[] commandSplit = input.split("\\s+");
        if(urlDetails.containsKey(commandSplit[1])){
            System.out.println("[WARNING] Url: "+ commandSplit[1] + " already exist in the database");
            return;
        }
        String sortkey = Base64.getEncoder().encodeToString(commandSplit[1].getBytes());
        int count = 0;
        urlDetails.put(commandSplit[1], new String[]{sortkey, "" + count});
    }

    private static void getUrlSortKey(String input){
        String[] commandSplit = input.split("\\s+");
        if(!urlDetails.containsKey(commandSplit[1])){
            System.out.println("[ERROR] Url: "+ commandSplit[1] + " doesn't exist in the database");
            return ;
        }
        System.out.println("url sort key: : " + urlDetails.get(commandSplit[1])[0]);
        urlDetails.put(commandSplit[1], new String[]{urlDetails.get(commandSplit[1])[0],
                "" + (Integer.parseInt(urlDetails.get(commandSplit[1])[1]) + 1)});
    }

    private static void getCount(String input){
        String[] commandSplit = input.split("\\s+");
        if(!urlDetails.containsKey(commandSplit[1])){
            System.out.println("[ERROR] Url: "+ commandSplit[1] + " doesn't exist in the database");
            return;
        }
        System.out.println("url count: " + urlDetails.get(commandSplit[1])[1]);
    }

    private static void listUrl(){
        if(urlDetails.isEmpty()){
            System.out.println("Url database is empty.");
            return;
        }
        System.out.println("list json format: {\"url\":\"count\"}");
        String json = "{"+urlDetails.entrySet().stream()
                .map(e -> "\""+ e.getKey() + "\":\"" + String.valueOf(e.getValue()[1]) + "\"")
             .collect(Collectors.joining(", "))+"}";
        System.out.println(json);
    }

    private static void exit(){
        System.exit(0);
    }

}
