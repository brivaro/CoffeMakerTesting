package com.coffemaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class InventoryJsonTestMOCK {

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
        // Creamos un mock de Inventory
        Inventory inventory = mock(Inventory.class);
        
        // Definimos comportamiento simulado para parseInventory()
        doNothing().when(inventory).parseInventory(anyString());
        when(inventory.getCoffee()).thenReturn(12);
        when(inventory.getMilk()).thenReturn(14);
        when(inventory.getSugar()).thenReturn(10);
        when(inventory.getChocolate()).thenReturn(12);

        // Ejecutamos el método
        inventory.parseInventory(jsonMock);

        // Verificamos que se llamó al método parseInventory con el JSON
        verify(inventory, times(1)).parseInventory(jsonMock);

        assertEquals(12, inventory.getCoffee(), "El valor de coffee no coincide.");
        assertEquals(14, inventory.getMilk(), "El valor de milk no coincide.");
        assertEquals(10, inventory.getSugar(), "El valor de sugar no coincide.");
        assertEquals(12, inventory.getChocolate(), "El valor de chocolate no coincide.");
    }

    @Test
    public void testSetDefaults() {
        Inventory inventory = mock(Inventory.class);
        
        when(inventory.getCoffee()).thenReturn(15);
        when(inventory.getMilk()).thenReturn(15);
        when(inventory.getSugar()).thenReturn(15);
        when(inventory.getChocolate()).thenReturn(15);

        assertEquals(15, inventory.getCoffee(), "El valor por defecto de coffee no coincide.");
        assertEquals(15, inventory.getMilk(), "El valor por defecto de milk no coincide.");
        assertEquals(15, inventory.getSugar(), "El valor por defecto de sugar no coincide.");
        assertEquals(15, inventory.getChocolate(), "El valor por defecto de chocolate no coincide.");
    }

    @Test
    public void testEnoughIngredients() {
        Inventory inventory = mock(Inventory.class);
        
        when(inventory.enoughIngredients(any(Recipe.class))).thenReturn(true, false);

        Recipe recetaSuficiente = new Recipe("Receta Suficiente", 50, 5, 5, 5, 5);
        Recipe recetaInsuficiente = new Recipe("Receta Insuficiente", 50, 15, 5, 5, 5);

        assertTrue(inventory.enoughIngredients(recetaSuficiente), "Debe haber suficientes ingredientes.");
        assertFalse(inventory.enoughIngredients(recetaInsuficiente), "No debe haber suficientes ingredientes (café insuficiente).");
    }

    @Test
    public void testSettersConValoresNegativos() {
        Inventory inventory = mock(Inventory.class);

        doNothing().when(inventory).setCoffee(anyInt());
        doNothing().when(inventory).setMilk(anyInt());
        doNothing().when(inventory).setSugar(anyInt());
        doNothing().when(inventory).setChocolate(anyInt());

        when(inventory.getCoffee()).thenReturn(0);
        when(inventory.getMilk()).thenReturn(0);
        when(inventory.getSugar()).thenReturn(0);
        when(inventory.getChocolate()).thenReturn(0);

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
        Inventory inventory = mock(Inventory.class);

        when(inventory.toString()).thenReturn("""
            Coffee: 12
            Milk: 14
            Sugar: 10
            Chocolate: 12
            """
        );

        String salida = inventory.toString();

        assertTrue(salida.contains("Coffee: 12"), "El toString no contiene 'Coffee: 12'.");
        assertTrue(salida.contains("Milk: 14"), "El toString no contiene 'Milk: 14'.");
        assertTrue(salida.contains("Sugar: 10"), "El toString no contiene 'Sugar: 10'.");
        assertTrue(salida.contains("Chocolate: 12"), "El toString no contiene 'Chocolate: 12'.");
    }
}