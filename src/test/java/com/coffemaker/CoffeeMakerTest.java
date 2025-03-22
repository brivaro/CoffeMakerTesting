package com.coffemaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CoffeeMakerTest {

    private CoffeeMaker coffeeMaker;
    private Recipe recipe;

    @BeforeEach
    public void setup() {
        coffeeMaker = new CoffeeMaker();
        recipe = new Recipe();
        recipe.setName("TestRecipe");
        recipe.setPrice(50);
        recipe.setAmtCoffee(3);
        recipe.setAmtMilk(2);
        recipe.setAmtSugar(1);
        recipe.setAmtChocolate(1);
        coffeeMaker.addRecipe(recipe);
    }

    // Test para addRecipe: se espera true al agregar una receta nueva y false al agregar duplicado.
    @Test
    public void testAddRecipe() {
        Recipe newRecipe = new Recipe();
        newRecipe.setName("NewRecipe");
        newRecipe.setPrice(60);
        newRecipe.setAmtCoffee(2);
        newRecipe.setAmtMilk(2);
        newRecipe.setAmtSugar(2);
        newRecipe.setAmtChocolate(2);
        
        boolean added = coffeeMaker.addRecipe(newRecipe);
        assertTrue(added, "La receta nueva debería poder agregarse.");
        
        boolean duplicateAdded = coffeeMaker.addRecipe(newRecipe);
        assertFalse(duplicateAdded, "La receta duplicada no debería agregarse.");
    }
    
    // Test para deleteRecipe: se espera que devuelva true y que la receta se elimine (por ejemplo, su nombre quede nulo o vacío).
    @Test
    public void testDeleteRecipe() {
        boolean deleted = coffeeMaker.deleteRecipe(recipe);
        assertTrue(deleted, "deleteRecipe debería devolver true al eliminar la receta.");
        
        Recipe result = coffeeMaker.getRecipeForName(recipe.getName());
        assertTrue("No data".equals(result.getName()), "La receta eliminada ya no debería tener un nombre válido.");
    }
    
    // Test para editRecipe: se espera que se actualice la receta y se retorne true.
    @Test
    public void testEditRecipe() {
        Recipe newRecipe = new Recipe();
        newRecipe.setName("EditedRecipe");
        newRecipe.setPrice(70);
        newRecipe.setAmtCoffee(4);
        newRecipe.setAmtMilk(3);
        newRecipe.setAmtSugar(2);
        newRecipe.setAmtChocolate(2);
        
        boolean edited = coffeeMaker.editRecipe(recipe, newRecipe);
        assertTrue(edited, "editRecipe debería devolver true al editar la receta existente.");
        
        Recipe result = coffeeMaker.getRecipeForName("EditedRecipe");
        assertNotNull(result, "La receta editada debería existir.");
        assertEquals("EditedRecipe", result.getName(), "El nombre de la receta editada debe coincidir.");
    }
    
    // Test para addInventory: se espera que se retorne true para cantidades válidas y false para parámetros inválidos.
    @Test
    public void testAddInventory() {
        // Caso válido: todos los valores positivos
        boolean validAdd = coffeeMaker.addInventory(1, 1, 1, 1);
        assertTrue(validAdd, "addInventory debería aceptar cantidades positivas válidas.");
        
        // Caso inválido: algún parámetro negativo
        boolean invalidAdd = coffeeMaker.addInventory(-1, 1, 1, 1);
        assertFalse(invalidAdd, "addInventory no debería aceptar cantidades negativas.");
    }
    
    // Test para checkInventory: se verifica que el inventario inicial tenga los valores predeterminados o modificados.
    @Test
    public void testCheckInventory() {
        Inventory inv = coffeeMaker.checkInventory();
        assertNotNull(inv, "El inventario no debería ser nulo.");
        assertTrue(inv.getCoffee() >= 0, "El inventario de café debe ser mayor o igual a 0.");
        assertTrue(inv.getMilk() >= 0, "El inventario de leche debe ser mayor o igual a 0.");
        assertTrue(inv.getSugar() >= 0, "El inventario de azúcar debe ser mayor o igual a 0.");
        assertTrue(inv.getChocolate() >= 0, "El inventario de chocolate debe ser mayor o igual a 0.");
    }
    
    // Test para makeCoffee: se verifica que se devuelva el cambio correcto y que se actualice el inventario.
    @Test
    public void testMakeCoffee() {
        Inventory invAntes = coffeeMaker.checkInventory();
        int cafeAntes = invAntes.getCoffee();
        int milkAntes = invAntes.getMilk();
        int sugarAntes = invAntes.getSugar();
        int chocolateAntes = invAntes.getChocolate();
        
        int amtPaid = 100; // dinero para pagar la receta
        int change = coffeeMaker.makeCoffee(recipe, amtPaid);
        
        assertEquals(amtPaid - recipe.getPrice(), change, "El cambio devuelto es incorrecto.");
        
        Inventory invDespues = coffeeMaker.checkInventory();
        assertTrue(invDespues.getMilk() == milkAntes - recipe.getAmtMilk(), 
                   "El inventario de leche no se actualizó correctamente.");
        assertTrue(invDespues.getSugar() == sugarAntes - recipe.getAmtSugar(), 
                   "El inventario de azúcar no se actualizó correctamente.");
        assertTrue(invDespues.getChocolate() == chocolateAntes - recipe.getAmtChocolate(), 
                   "El inventario de chocolate no se actualizó correctamente.");
        assertTrue(invDespues.getCoffee() == cafeAntes - recipe.getAmtCoffee(), 
                   "El inventario de café no se actualizó correctamente (debería disminuir).");
    }
    
    // Test para getRecipes: se verifica que se retorne el array con las recetas agregadas.
    @Test
    public void testGetRecipes() {
        Recipe[] recipes = coffeeMaker.getRecipes();
        boolean found = false;
        for (Recipe r : recipes) {
            if (r.getName() != null && recipe.equals(r)) {
                found = true;
                break;
            }
        }
        assertTrue(found, "El arreglo de recetas debería contener la receta 'TestRecipe'.");
    }
    
    // Test para getRecipeForName: se verifica que se retorne la receta buscada.
    @Test
    public void testGetRecipeForName() {
        Recipe result = coffeeMaker.getRecipeForName("TestRecipe");
        assertNotNull(result, "getRecipeForName debería devolver una receta no nula.");
        assertEquals("TestRecipe", result.getName(), "El nombre de la receta devuelta es incorrecto.");
    }
}