package com.coffemaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;

public class InventoryJsonTest {

    @Test
    public void testInventoryInitializationWithMock() {
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

        Inventory inventory = new InventoryTestable(jsonMock);
        inventory.parseInventory(anyString());

        // Verificamos que los valores se asignaron correctamente.
        assertEquals(12, inventory.getCoffee(), "El valor de coffee no coincide.");
        assertEquals(14, inventory.getMilk(), "El valor de milk no coincide.");
        assertEquals(10, inventory.getSugar(), "El valor de sugar no coincide.");
        assertEquals(12, inventory.getChocolate(), "El valor de chocolate no coincide.");
    }
}
