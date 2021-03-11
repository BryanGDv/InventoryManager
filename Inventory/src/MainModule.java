import inventory.InventoryData;

import javax.print.DocFlavor;
import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;

public class MainModule {

    private static Scanner scanner;
    private static InventoryData inventoryData;

    public static void main(String[] args){
        scanner = new Scanner(System.in);
        inventoryData = new InventoryData();

        System.out.println("Loading inventory...");
        System.out.println("What do you want to do? If you need help please put \"help\".");
        checkInventory();
    }

    private static void checkInventory(){

        HashMap<String, Integer> inventory = inventoryData.getInventory();

        String text = scanner.nextLine();

        if (text.equalsIgnoreCase("help")){
            System.out.println("List of args:");
            System.out.println("- add [name] [amount] (max: 64)");
            System.out.println("- inventory");
            System.out.println("- end");
            checkInventory();
            return;
        }
        if (text.equalsIgnoreCase("end")){
            return;
        }

        if (text.startsWith("add")){
            String[] itemSintaxs = text.split(" ");

            System.out.println(itemSintaxs.length);
            if (itemSintaxs.length < 2){
                System.out.println("You need to put a name");
                checkInventory();
                return;
            }

            if (itemSintaxs.length < 3){
                inventoryData.addItem(itemSintaxs[1], 1);
                checkInventory();
                return;
            }

            int amount;

            try {
                amount = Integer.parseInt(itemSintaxs[2]);
            }catch (NumberFormatException numberFormatException){
                System.out.println("You need to put a number.");
                checkInventory();
                return;
            }

            inventoryData.addItem(itemSintaxs[1], amount);

            checkInventory();
            return;
        }
        if (text.startsWith("remove")){

            String[] itemSintaxs = text.split(" ");

            if (itemSintaxs.length < 2){
                System.out.println("Please put a name");
                checkInventory();
                return;
            }

            if (inventory.get(itemSintaxs[1]) == null){
                System.out.println("The item " + itemSintaxs[1] + "  don't exists.");
                checkInventory();
                return;
            }

            inventoryData.removeItem(itemSintaxs[1]);
            checkInventory();
            return;
        }

        if (text.equalsIgnoreCase("inventory")){

            if (inventory.isEmpty()){
                System.out.println("- The inventory is empty.");
                checkInventory();
                return;
            }

            for (String keys : inventory.keySet()){
                System.out.println("- " + keys +" : " + inventory.get(keys) + " items.");
            }
            checkInventory();
            return;
        }


        checkInventory();
    }
}
