package praktikum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @Test
    void shouldReturnCorrectType() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Hot sauce", 100f);
        assertEquals(IngredientType.SAUCE, ingredient.getType());
    }

    @Test
    void shouldReturnCorrectName() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "Cutlet", 150f);
        assertEquals("Cutlet", ingredient.getName());
    }

    @Test
    void shouldReturnCorrectPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Sour cream", 200f);
        assertEquals(200f, ingredient.getPrice());
    }
}