import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import minisearch.core.PostingList;
import minisearch.core.Tokenizer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;


public class PostingListTest {


    @Test
    void createTest() {
        PostingList postingList = new PostingList();
        assertEquals(postingList.getTokenFrequency(), 0);
    }

    @Test
    void create2Test() {
        PostingList postingList = new PostingList("sdfsdf");
        assertEquals(postingList.getTokenFrequency(), 1);
    }

    @Test
    void create3Test() {
        PostingList postingList = new PostingList("sdfsdf");
        postingList.addDocument("sd");
        assertEquals(postingList.getTokenFrequency(), 2);
    }

}
