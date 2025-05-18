package Actions;

import java.util.Map;
import Items.Food;
import Items.Items;
import Items.Recipe;
import Player.Player;

public class Cooking extends Action {
    private Player player;
    private Recipe recipe;
    private Food food;
    private int fuelRequired;

    // bikin constructor yang parameternya cuma player?
    
    public Cooking(Player player, Recipe recipe, Food food, int fuelRequired) {
        super("Cooking", 10, 60);
        this.player = player;
        this.recipe = recipe;
        this.food = food;
        this.fuelRequired = fuelRequired;
    }

    public void startCooking() {
        if (recipe.isAvailable(player.getInventory())) {
            if (player.getEnergy() >= energyRequired) {
                player.decreaseEnergy(energyRequired);

                Map <Items, Integer> fuel = player.getInventory().checkInventory();
                Items fuelUsed = null;
                int foodCount = 1;

                for (Items item : fuel.keySet()) {
                    String name = item.getName();
                    int qty = fuel.get(item);

                    if (name.equals("Firewood") && qty >= 1) {
                        fuelUsed = item;
                        foodCount = 1;
                    }
                    else if (name.equals("Coal") && qty >= 1) {
                        fuelUsed = item;
                        foodCount = 2;
                    }
                }

                if (fuelUsed == null) {
                    System.out.println("Not enough fuel to cook!");
                }
                else {
                    for (Map.Entry<Items, Integer> entry : recipe.getIngredient().entrySet()) {
                        Items ingredient = entry.getKey();
                        int qty = entry.getValue() * foodCount;
                        player.removeItemFromInventory(ingredient, qty);
                    }

                    player.removeItemFromInventory(fuelUsed, 1);

                    System.out.println("Cooking " + food.getName() + "...");
                    player.addItemToInventory(food, foodCount);
                    System.out.println("Cooking successful! " + food.getName() + " is ready.");
                }
            }
            else {
                System.out.println("Not enough energy to cook!");
            }
        }
        else {
            System.out.println("Required ingredients are not available in your inventory.");
        }
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Food getFood() {
        return food;
    }

    public int getFuelRequired() {
        return fuelRequired;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void setFuelRequired(int fuelRequired) {
        this.fuelRequired = fuelRequired;
    }

    @Override
    public void executeAction() {
        startCooking();
    }
}