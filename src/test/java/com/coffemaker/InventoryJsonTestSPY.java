package com.coffemaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InventoryJsonTestSPY {

    // JSON de prueba inyectado
    String jsonMock = """
        {
            "ingredientes": [
                {"nombre": "coffee", "cantidad": 12},
                {"nombre": "milk", "cantidad": 14},
                {"nombre": "sugar", "cantidad": 10},
                {"nombre": "chocolate", "cantidad": 12}
            ]
        }
    """;

    @Test
    public void testInventoryInitializationWithMock() {
        // Creamos un spy de Inventory, usando un path que no exista para forzar que se establezcan los valores por defecto
        Inventory inventory = spy(new Inventory("rutaInexistente.json"));
        // Inyectamos manualmente el JSON de prueba
        inventory.parseInventory(jsonMock);
        // Verificamos que se llamó al método parseInventory con el JSON inyectado
        verify(inventory, times(1)).parseInventory(jsonMock);
        
        assertEquals(12, inventory.getCoffee(), "El valor de coffee no coincide.");
        assertEquals(14, inventory.getMilk(), "El valor de milk no coincide.");
        assertEquals(10, inventory.getSugar(), "El valor de sugar no coincide.");
        assertEquals(12, inventory.getChocolate(), "El valor de chocolate no coincide.");
    }

    @Test
    public void testSetDefaults() {
        Inventory inventory = new Inventory("rutaInexistente.json");
        assertEquals(15, inventory.getCoffee(), "El valor por defecto de coffee no coincide.");
        assertEquals(15, inventory.getMilk(), "El valor por defecto de milk no coincide.");
        assertEquals(15, inventory.getSugar(), "El valor por defecto de sugar no coincide.");
        assertEquals(15, inventory.getChocolate(), "El valor por defecto de chocolate no coincide.");
    }

    @Test
    public void testEnoughIngredients() {
        Inventory inventory = new Inventory("rutaInexistente.json");
        inventory.setCoffee(10);
        inventory.setMilk(10);
        inventory.setSugar(10);
        inventory.setChocolate(10);

        // Creamos una receta que requiere menos ingredientes que los disponibles
        Recipe recetaSuficiente = new Recipe("Receta Suficiente", 50, 5, 5, 5, 5);
        assertTrue(inventory.enoughIngredients(recetaSuficiente), "Debe haber suficientes ingredientes.");

        // Creamos una receta que requiere más café que el disponible
        Recipe recetaInsuficiente = new Recipe("Receta Insuficiente", 50, 15, 5, 5, 5);
        assertFalse(inventory.enoughIngredients(recetaInsuficiente), "No debe haber suficientes ingredientes (café insuficiente).");
    }

    @Test
    public void testSettersConValoresNegativos() {
        Inventory inventory = new Inventory("rutaInexistente.json");
        inventory.setCoffee(-5);
        inventory.setMilk(-3);
        inventory.setSugar(-10);
        inventory.setChocolate(-1);

        assertEquals(0, inventory.getCoffee(), "El valor de coffee negativo debe quedar en 0.");
        assertEquals(0, inventory.getMilk(), "El valor de milk negativo debe quedar en 0.");
        assertEquals(0, inventory.getSugar(), "El valor de sugar negativo debe quedar en 0.");
        assertEquals(0, inventory.getChocolate(), "El valor de chocolate negativo debe quedar en 0.");
    }

    @Test
    public void testToString() {
        Inventory inventory = spy(new Inventory("rutaInexistente.json"));
        inventory.parseInventory(jsonMock);
        verify(inventory, times(1)).parseInventory(jsonMock);
        
        String salida = inventory.toString();
        
        assertTrue(salida.contains("Coffee: 12"), "El toString no contiene 'Coffee: 12'.");
        assertTrue(salida.contains("Milk: 14"), "El toString no contiene 'Milk: 14'.");
        assertTrue(salida.contains("Sugar: 10"), "El toString no contiene 'Sugar: 10'.");
        assertTrue(salida.contains("Chocolate: 12"), "El toString no contiene 'Chocolate: 12'.");
    }
}