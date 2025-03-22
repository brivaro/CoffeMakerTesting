package com.coffemaker;

public class InventoryTestable extends Inventory {
    
    private final String jsonContent;

    public InventoryTestable(String jsonContent) {
        super("dummy");
        this.jsonContent = jsonContent;
    }
    
    @Override
    public void parseInventory(String ignoredContent) {
        // Se ignora el contenido recibido y se utiliza el JSON inyectado.
        super.parseInventory(jsonContent);
    }
}
