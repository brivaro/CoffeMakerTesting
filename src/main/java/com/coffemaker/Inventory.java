package com.coffemaker;
 
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * 
 * @author Brian Valiente Rodenas
 *
 * Inventory for the coffee maker
 */
public class Inventory {
    
    private static int coffee;
    private static int milk;
    private static int sugar;
    private static int chocolate;
    private final String path = "src\\main\\java\\com\\coffemaker\\data\\Inventario.json";

    /**
     * Constructor por defecto, carga el inventario desde la ruta.
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Inventory() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
            parseInventory(content);
        } catch (IOException e) {
            setDefaults();
        }
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Inventory(String pathJSON) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathJSON)), StandardCharsets.UTF_8);
            parseInventory(content);
        } catch (IOException e) {
            setDefaults();
        }
    }
    
    public void parseInventory(String jsonContent) {
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(jsonContent, JsonObject.class);
        JsonArray ingredientes = json.getAsJsonArray("ingredientes");
        for (int i = 0; i < ingredientes.size(); i++) {
            JsonObject ing = ingredientes.get(i).getAsJsonObject();
            String nombre = ing.get("nombre").getAsString();
            int cantidad = ing.get("cantidad").getAsInt();
            switch (nombre.toLowerCase()) {
                case "coffee" -> setCoffee(cantidad);
                case "milk" -> setMilk(cantidad);
                case "sugar" -> setSugar(cantidad);
                case "chocolate" -> setChocolate(cantidad);
            }
        }
    }
    
    public void setDefaults() {
        setCoffee(15);
        setMilk(15);
        setSugar(15);
        setChocolate(15);
    }

    public int getChocolate() {
        return chocolate;
    }
    public void setChocolate(int chocolate) {
    	if(chocolate >= 0) {
    		Inventory.chocolate = chocolate;
    	}
    	else {
    		Inventory.chocolate = 0;
    	}
        
    }
    public int getCoffee() {
        return coffee;
    }
    public void setCoffee(int coffee) {
    	if(coffee >= 0) {
    		Inventory.coffee = coffee;
    	}
    	else {
    		Inventory.coffee = 0;
    	}
    }
    public int getMilk() {
        return milk;
    }
    public void setMilk(int milk) {
    	if(milk >= 0) {
    		Inventory.milk = milk;
    	}
    	else {
    		Inventory.milk = 0;
    	}
    }
    public int getSugar() {
        return sugar;
    }
    public void setSugar(int sugar) {
    	if(sugar >= 0) {
    		Inventory.sugar = sugar;
    	}
    	else {
    		Inventory.sugar = 0;
    	}
    }
    
    /**
     * Returns true if there are enough ingredients to make
     * the beverage.
     * @param r
     * @return boolean
     */
    public boolean enoughIngredients(Recipe r) {
        boolean isEnough = true;
        if(Inventory.coffee < r.getAmtCoffee()) {
            isEnough = false;
        }
        if(Inventory.milk < r.getAmtMilk()) {
            isEnough = false;
        }
        if(Inventory.sugar < r.getAmtSugar()) {
            isEnough = false;
        }
        if(Inventory.chocolate < r.getAmtChocolate()) {
            isEnough = false;
        }
        return isEnough;
    }
    
    @Override
    public String toString() {
    	return "Coffee: " + getCoffee() + "\n" +
			"Milk: " + getMilk() + "\n" +
			"Sugar: " + getSugar() + "\n" +
			"Chocolate: " + getChocolate() + "\n";
    }
}
