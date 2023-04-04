package ru.yandex.praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static ru.yandex.praktikum.IngredientType.FILLING;
import static ru.yandex.praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class IngredientTests {
    private final String name;
    private final float price;
    private final IngredientType type;
    private Ingredient ingredient;

    public IngredientTests(String name, float price, IngredientType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    @Parameterized.Parameters(name = "Название, цена и тип ингридиента: {0}: {1}: {2}")
    public static Object[][] testsData() {
        return new Object[][]{
                {"Соус Spicy-X", Float.MAX_VALUE, SAUCE},
                {"Мясо бессмертных моллюсков Protostomia", Float.MIN_NORMAL, FILLING},
                {" ", Float.MIN_VALUE, SAUCE},
                {null, -123, FILLING},
                {null, 0, null}
        };
    }

    @Before
    public void setup() {
        ingredient = new Ingredient(type, name, price);
    }

    @Test
    public void getNameWithValidDataOk() {
        Assert.assertEquals("value in the field Name is incorrect.", name, ingredient.getName());
    }

    @Test
    public void getPriceWithValidDataOk() {
        Assert.assertEquals("value in the field Price is incorrect.", price, ingredient.getPrice(), 0);
    }

    @Test
    public void getTypeWithValidDataOk() {
        Assert.assertEquals("value in the field Type is incorrect.", type, ingredient.getType());
    }

}
