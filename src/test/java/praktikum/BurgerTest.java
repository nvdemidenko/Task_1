package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class BurgerTest {

    private Burger burger;
    private Bun bun;
    private Ingredient ingredient1;
    private Ingredient ingredient2;

    @BeforeEach
    void setUp() {
        burger = new Burger();
        bun = new Bun("Test bun", 150f);
        ingredient1 = new Ingredient(IngredientType.FILLING, "Cutlet", 100f);
        ingredient2 = new Ingredient(IngredientType.SAUCE, "Sauce", 50f);
    }

    @Test
    void shouldSetBun() {
        burger.setBuns(bun);
        assertSame(bun, burger.bun);
    }

    @Test
    void shouldAddIngredient() {
        burger.addIngredient(ingredient1);
        assertTrue(burger.ingredients.contains(ingredient1));
    }

    @Test
    void shouldRemoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);
        assertFalse(burger.ingredients.contains(ingredient1));
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    void shouldMoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);
        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test
    void shouldThrowIndexOutOfBoundsOnRemove() {
        burger.addIngredient(ingredient1);
        assertThrows(IndexOutOfBoundsException.class, () -> burger.removeIngredient(5));
    }

    @Test
    void shouldThrowIndexOutOfBoundsOnMove() {
        burger.addIngredient(ingredient1);
        assertThrows(IndexOutOfBoundsException.class, () -> burger.moveIngredient(5, 0));
    }

    @ParameterizedTest
    @CsvSource({
            "150, 100, 400",
            "200, 50, 450",
            "0, 0, 0"
    })
    void shouldCalculatePriceCorrectly(float bunPrice, float ingredientPrice, float expectedPrice) {
        Bun testBun = new Bun("Price bun", bunPrice);
        Ingredient testIngredient = new Ingredient(IngredientType.SAUCE, "Test", ingredientPrice);

        burger.setBuns(testBun);
        burger.addIngredient(testIngredient);

        assertEquals(expectedPrice, burger.getPrice());
    }

    @Test
    void shouldGenerateCorrectReceipt() {
        Bun testBun = new Bun("STUB_BUN", 100f);
        Ingredient testIngredient = new Ingredient(IngredientType.SAUCE, "STUB_SAUCE", 50f);

        burger.setBuns(testBun);
        burger.addIngredient(testIngredient);

        String actual = burger.getReceipt()
                .replace(",", ".") // Приводим к точке
                .replace("\r\n", "\n"); // На всякий случай убираем Windows-переносы

        String expected = "(==== STUB_BUN ====)\n" +
                "= sauce STUB_SAUCE =\n" +
                "(==== STUB_BUN ====)\n" +
                "\nPrice: 250.000000\n";

        assertEquals(expected, actual);
    }
}