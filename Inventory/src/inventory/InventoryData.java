package inventory;

import java.util.HashMap;

public class InventoryData {


    private final HashMap<String, Integer> inventory;

    public InventoryData() {
        this.inventory = new HashMap<>();
    }

    public void addItem(String item, int amount){
        if (amount > 64) {

            if (amount > 54 * 64 - inventory.size() * 64){
                return;
            }

            int time = amount / 64;
            int newAmount = amount - (64 * time);

            int stacks = 1;
            while (stacks < time) {

                inventory.put(item + " (" + stacks + ") ", 64);
                stacks++;
            }

            inventory.put(item + " (" + stacks + 1 + ") ", newAmount);
            System.out.println("Item added! " + item + " - " + amount + " items.");
            return;
        }

        inventory.put(item, amount);
        System.out.println("Item added! " + item + " - " + amount + " items.");

    }

    public void removeItem(String itemName){
        inventory.remove(itemName);
        System.out.println("Item removed! " + itemName);
    }

    public HashMap<String, Integer> getInventory(){
        return inventory;
    }
}
