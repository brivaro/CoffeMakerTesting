package com.coffemaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecipeTest {
    private Recipe recipe;

    @BeforeEach
    public void setUp() {
        recipe = new Recipe("Cappuccino", 50, 2, 1, 1, 1);
    }

    @Test
    void testConstructor() {
        Recipe defaultRecipe = new Recipe();
        assertEquals("No data", defaultRecipe.getName(), "El nombre por defecto debe ser 'No data'");
        assertEquals(0, defaultRecipe.getPrice(), "El precio por defecto debe ser 0");
        assertEquals(0, defaultRecipe.getAmtCoffee(), "La cantidad de café por defecto debe ser 0");
        assertEquals(0, defaultRecipe.getAmtMilk(), "La cantidad de leche por defecto debe ser 0");
        assertEquals(0, defaultRecipe.getAmtSugar(), "La cantidad de azúcar por defecto debe ser 0");
        assertEquals(0, defaultRecipe.getAmtChocolate(), "La cantidad de chocolate por defecto debe ser 0");
    }

    @Test
    void testSettersAndGetters() {
        recipe.setName("Latte");
        assertEquals("Latte", recipe.getName(), "El nombre no se ha establecido correctamente");
        
        recipe.setPrice(100);
        assertEquals(100, recipe.getPrice(), "El precio no se ha establecido correctamente");
        
        recipe.setAmtCoffee(3);
        assertEquals(3, recipe.getAmtCoffee(), "La cantidad de café no se ha establecido correctamente");
        
        recipe.setAmtMilk(2);
        assertEquals(2, recipe.getAmtMilk(), "La cantidad de leche no se ha establecido correctamente");
        
        recipe.setAmtSugar(2);
        assertEquals(2, recipe.getAmtSugar(), "La cantidad de azúcar no se ha establecido correctamente");
        
        recipe.setAmtChocolate(2);
        assertEquals(2, recipe.getAmtChocolate(), "La cantidad de chocolate no se ha establecido correctamente");
    }

    @Test
    void testNegativeValues() {
        recipe.setPrice(-10);
        assertEquals(0, recipe.getPrice(), "El precio negativo debe convertirse en 0");
        
        recipe.setAmtCoffee(-1);
        assertEquals(0, recipe.getAmtCoffee(), "La cantidad negativa de café debe convertirse en 0");
        
        recipe.setAmtMilk(-1);
        assertEquals(0, recipe.getAmtMilk(), "La cantidad negativa de leche debe convertirse en 0");
        
        recipe.setAmtSugar(-1);
        assertEquals(0, recipe.getAmtSugar(), "La cantidad negativa de azúcar debe convertirse en 0");
        
        recipe.setAmtChocolate(-1);
        assertEquals(0, recipe.getAmtChocolate(), "La cantidad negativa de chocolate debe convertirse en 0");
    }

    @Test
    void testEquals() {
        Recipe anotherRecipe = new Recipe("Cappuccino", 40, 2, 1, 1, 1);
        assertTrue(recipe.equals(anotherRecipe), "Las recetas con el mismo nombre deben considerarse iguales");
        
        Recipe differentRecipe = new Recipe("Espresso", 40, 2, 1, 1, 1);
        assertFalse(recipe.equals(differentRecipe), "Las recetas con nombres diferentes deben ser distintas");
    }

    @Test
    void testToString() {
        assertEquals("Cappuccino", recipe.toString(), "El método toString debe devolver el nombre de la receta");
    }
}
