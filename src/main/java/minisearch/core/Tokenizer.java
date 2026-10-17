package minisearch.core;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Tokenizer {

    public static void main(String[] args) {
        System.out.println("I'm here");
    }

    public List<String> apply(String str) {
        String processed_str = str.toLowerCase(Locale.ENGLISH);
        return Arrays.stream(processed_str.split(" "))
                .map(token -> token.replace("-", " "))
                .map(token -> token.replace(".", " "))
                .map(token -> token.strip())
                .filter(token-> !token.isEmpty())
                .toList();
    }
}


