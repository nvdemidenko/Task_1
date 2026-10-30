package praktikum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BunTest {

    @Test
    void shouldReturnCorrectName() {
        Bun bun = new Bun("Black bun", 100f);
        assertEquals("Black bun", bun.getName());
    }

    @Test
    void shouldReturnCorrectPrice() {
        Bun bun = new Bun("White bun", 200f);
        assertEquals(200f, bun.getPrice());
    }
}