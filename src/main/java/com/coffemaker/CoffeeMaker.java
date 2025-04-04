package com.coffemaker;
/**
 * 
 * @author Brian Valiente Rodenas
 *
 * CoffeeMaker object
 */
public class CoffeeMaker {
	/** Array of recipes in coffee maker */
	private final Recipe [] recipeArray;
	/** Number of recipes in coffee maker */
	private final int NUM_RECIPES = 3;
	/** Array describing if the array is full */
	private final boolean [] recipeFull;
	/** Inventory of the coffee maker */
    private final Inventory inventory;
	
    /**
     * Constructor for the coffee maker
     *
     */
	public CoffeeMaker() {
	    recipeArray = new Recipe[NUM_RECIPES];
	    recipeFull = new boolean[NUM_RECIPES];
		for(int i = 0; i < NUM_RECIPES; i++) {
		   recipeArray[i] = new Recipe();
		   recipeFull[i] = false;
		}
		inventory = new Inventory();
	}

	/**
	 * Returns true if a recipe is successfully added to the 
	 * coffee maker
	 * @param r
	 * @return boolean
	 */
	public boolean addRecipe(Recipe r) {
        boolean canAddRecipe = true;
            
        //Check if the recipe already exists
        for(int i = 0; i < NUM_RECIPES; i++) {
            if(r.equals(recipeArray[i])) {
                canAddRecipe = false;
            }
        }
        
        //Check for an empty recipe, add recipe to first empty spot
        if(canAddRecipe) {
        	int emptySpot = -1;
        	for(int i = 0; i < NUM_RECIPES; i++) {
        		if(!recipeFull[i]) {
        			emptySpot = i;
        			canAddRecipe = true;
                    break;
        		}
        	}
        	if(emptySpot != -1) {
        		recipeArray[emptySpot] = r;
        		recipeFull[emptySpot] = true;
        	}
        	else {
        		canAddRecipe = false;
        	}
        }
        return canAddRecipe;
    }
    
	/**
	 * Returns true if the recipe was deleted from the 
	 * coffee maker
	 * @param r
	 * @return boolean
	 */
    public boolean deleteRecipe(Recipe r) {
        boolean canDeleteRecipe = false;
        if(!"No data".equals(r.getName())) {
	        for(int i = 0; i < NUM_RECIPES; i++) {
	            if(r.equals(recipeArray[i])) {
	                recipeArray[i] = new Recipe();
                    recipeFull[i] = false; 
	                canDeleteRecipe = true;
	            }
	        }
        }
        return canDeleteRecipe;
    }
    
    /**
     * Returns true if the recipe is successfully edited
     * @param oldRecipe
     * @param newRecipe
     * @return boolean
     */
    public boolean editRecipe(Recipe oldRecipe, Recipe newRecipe) {
        boolean canEditRecipe = false;
        if(!"No data".equals(oldRecipe.getName())) {
            for(int i = 0; i < NUM_RECIPES; i++) {
                if(!"No data".equals(recipeArray[i].getName())) {
                    if(oldRecipe.equals(recipeArray[i]) && oldRecipe.getName().equals(newRecipe.getName())) { 
                        canEditRecipe = true;
                        if(canEditRecipe) {
                            recipeArray[i] = newRecipe;
                            return canEditRecipe;
                        }
                    }
                }
            }
        }
        return canEditRecipe;
    }
    
    /**
     * Returns true if inventory was successfully added
     * @param amtCoffee
     * @param amtMilk
     * @param amtSugar
     * @param amtChocolate
     * @return boolean
     */
    public boolean addInventory(int amtCoffee, int amtMilk, int amtSugar, int amtChocolate) {
        boolean canAddInventory = true;
        if(amtCoffee < 0 || amtMilk < 0 || amtSugar < 0 || amtChocolate < 0) { 
            canAddInventory = false;
        }
        else {
	        inventory.setCoffee(inventory.getCoffee() + amtCoffee);
	        inventory.setMilk(inventory.getMilk() + amtMilk);
	        inventory.setSugar(inventory.getSugar() + amtSugar);
	        inventory.setChocolate(inventory.getChocolate() + amtChocolate);
        }
        return canAddInventory;
    }
    
    /**
     * Returns the inventory of the coffee maker
     * @return Inventory
     */
    public Inventory checkInventory() {
        return inventory;
    }
    
    /**
     * Returns the change of a user's beverage purchase, or
     * the user's money if the beverage cannot be made
     * @param r
     * @param amtPaid
     * @return int
     */
    public int makeCoffee(Recipe r, int amtPaid) {
        boolean canMakeCoffee = true;
        if ("No data".equals(r.getName())) {
            canMakeCoffee = false;
            System.out.println("No recipe available\n");
        }
        if(amtPaid < r.getPrice()) {
            canMakeCoffee = false;
            System.out.println("Not enough money\n");
        }
        if(!inventory.enoughIngredients(r)) {
            canMakeCoffee = false;
            System.out.println("Not enough ingredients\n");
        }
        if(canMakeCoffee) {
	        inventory.setCoffee(inventory.getCoffee() - r.getAmtCoffee()); 
	        inventory.setMilk(inventory.getMilk() - r.getAmtMilk());
	        inventory.setSugar(inventory.getSugar() - r.getAmtSugar());
	        inventory.setChocolate(inventory.getChocolate() - r.getAmtChocolate());
            System.out.println("Dispensing: " + r.getName() + "\n");
            return amtPaid - r.getPrice();
        }
        else {
            return amtPaid;
        }
    }

    /**
     * Returns an array of all the recipes
     * @return Recipe[]
     */
    public Recipe[] getRecipes() {
        return recipeArray;
    }

    /**
     * Returns the Recipe associated with the given name
     * @param name
     * @return Recipe
     */
	public Recipe getRecipeForName(String name) {
		Recipe r = new Recipe();
		for(int i = 0; i < NUM_RECIPES; i++) {
			if(!"No data".equals(recipeArray[i].getName())) { 
				if((recipeArray[i].getName()).equals(name)) {
					r = recipeArray[i];
				}
			}
		}
		return r;
	}
}
