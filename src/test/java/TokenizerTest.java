import static org.junit.jupiter.api.Assertions.assertEquals;

import minisearch.core.Tokenizer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;


public class TokenizerTest {

    @BeforeAll
    static void setup() {
        System.out.println("Before All procedure");
    }

    @BeforeEach
    void setupTest() {
        System.out.println("Before test");
    }

    private final Tokenizer tokenizer = new Tokenizer();

    @Test
    void simpleTest() {
        String test = "abc sec sdfsdf";
        List<String> y = List.of("abc", "sec", "sdfsdf");
        List<String> res = tokenizer.apply(test);
        assertEquals(y, res);
    }

    @Test
    void simple2Test() {
        String test = "Abc sEc sdfsdF";
        List<String> y = List.of("abc", "sec", "sdfsdf");
        List<String> res = tokenizer.apply(test);
        assertEquals(y, res);
    }


}
