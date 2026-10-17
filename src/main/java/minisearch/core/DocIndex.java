package minisearch.core;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocIndex {

    private Map<String, List<Integer>> titleIndex;
    private Map<String, List<Integer>> descriptionIndex;
    private Tokenizer tokenizer;
    private Integer docNumber = 0;

    public DocIndex() {
        this.titleIndex = new HashMap<>();
        this.descriptionIndex = new HashMap<>();
        this.tokenizer =  new Tokenizer();
    }

    public Integer genTitleTokensNumber() {
        return this.titleIndex.size();
    }

    public Integer getDescriptionTokensNumber() {
        return this.descriptionIndex.size();
    }

    public Integer getDocumentsAmount()  {
        return this.docNumber;
    }

    public void addToken(String token, String collection) {
        if (collection.equals("title")) {
            if (this.titleIndex.containsKey(token)) {
                this.titleIndex.get(token).add(this.docNumber);
            } else {
                List<Integer> postings = new ArrayList<>();
                postings.add(this.docNumber);
                this.titleIndex.put(token, postings);
            }
        } else if (collection.equals("description")) {
            if (this.descriptionIndex.containsKey(token)) {
                this.descriptionIndex.get(token).add(this.docNumber);
            } else {
                List<Integer> postings = new ArrayList<>();
                postings.add(this.docNumber);
                this.descriptionIndex.put(token, postings);
            }
        } else {
            System.out.println("Unknown collection name " + collection);
        }
    }

    public void addDocument(JsonNode document) {
        String title = document.get("title").asText();
        String description = document.get("description").asText();
        this.docNumber++;


        List<String> title_tokens = tokenizer.apply(title);
        for (String token: title_tokens) {
            this.addToken(token, "title");
        }

        List<String> description_tokens = tokenizer.apply(description);
        for (String token: description_tokens) {
            this.addToken(token, "description");
        }
    }

    public void printStats() {
        System.out.println("----------------------");
        System.out.println("Number of documents: " + this.getDocumentsAmount());
        System.out.println("Number of title tokens:" + this.genTitleTokensNumber());
        System.out.println("Number of description tokens: " + this.getDescriptionTokensNumber());
    }

}