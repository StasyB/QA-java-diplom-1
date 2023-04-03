package ru.yandex.praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.yandex.praktikum.IngredientType.FILLING;
import static ru.yandex.praktikum.IngredientType.SAUCE;

public class BurgerTests {
    private Burger burger;
    private static final String BUN_NAME = "new bun";
    private static final float BUN_PRICE = 199.99F;
    private static final String INGREDIENT_NAME = "new ingredient";
    private static final float INGREDIENT_PRICE = 0.99F;

    public Bun getBunMok(String name, float price) {
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn(name);
        when(bun.getPrice()).thenReturn(price);
        return bun;
    }

    public Ingredient getIngredientMok(IngredientType type, String name, float price) {
        Ingredient ingredient = mock(Ingredient.class);
        when(ingredient.getType()).thenReturn(type);
        when(ingredient.getName()).thenReturn(name);
        when(ingredient.getPrice()).thenReturn(price);
        return ingredient;
    }

    @Mock
    Bun bun = getBunMok(BUN_NAME, BUN_PRICE);
    Ingredient ingredientSauce = getIngredientMok(SAUCE, INGREDIENT_NAME, INGREDIENT_PRICE);
    Ingredient ingredientFilling = getIngredientMok(FILLING, INGREDIENT_NAME, INGREDIENT_PRICE);

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsWithMockOK() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientWithTwoMockIngredientsOK() {
        burger.addIngredient(ingredientSauce);
        burger.addIngredient(ingredientFilling);
        assertEquals("count burger ingredients is incorrect", 2, burger.ingredients.size());
    }

    @Test
    public void removeIngredientWithMockIngredientOK() {
        burger.addIngredient(ingredientSauce);
        burger.removeIngredient(0);
        assertEquals("ingredient isn't removed", 0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientWithMockOK() {
        burger.setBuns(bun);
        burger.addIngredient(ingredientSauce);
        burger.addIngredient(ingredientFilling);
        burger.moveIngredient(0, 1);
        assertEquals("ingredients aren't swapped", ingredientFilling, burger.ingredients.get(0));
    }

    @Test
    public void getPriceWithMockOK() {
        burger.setBuns(bun);
        burger.addIngredient(ingredientSauce);
        burger.addIngredient(ingredientFilling);
        float expectedPrice = 401.96F;
        assertEquals("burger price is incorrect", expectedPrice, burger.getPrice(), 0);
    }

    @Test
    public void getReceiptWithMockOk() {
        burger.setBuns(bun);
        burger.addIngredient(ingredientSauce);
        burger.addIngredient(ingredientFilling);
        burger.getPrice();
        String expected = String.format(
                "(==== new bun ====)%n" +
                "= sauce new ingredient =%n" +
                "= filling new ingredient =%n" +
                "(==== new bun ====)%n" +
                "%n" +
                "Price: 401,959991%n");

        assertEquals("receipt is incorrect printed", expected, burger.getReceipt());
        }
    }

