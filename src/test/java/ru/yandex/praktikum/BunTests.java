package ru.yandex.praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BunTests {
    private Bun bun;
    private final String name;
    private final float price;

    public BunTests(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Название и цена булочки: {0}: {1}")
    public static Object[][] testsData() {
        return new Object[][] {
                {"Краторная булка N-200i", Float.MIN_VALUE},
                {"Флюоресцентная булка R2-D3", Float.MAX_VALUE},
                {" ", 1234},
                {null, -123}
        };
    }
    @Before
    public void setUp() {
        bun = new Bun(name, price);
    }

    @Test
    public void getNameWithValidDataOk() {
        Assert.assertEquals("value in the field Name is incorrect.", name, bun.getName());
    }

    @Test
    public void getPriceWithValidDataOk() {
        Assert.assertEquals("value in the field Price is incorrect.", price, bun.getPrice(),0);
    }

}
