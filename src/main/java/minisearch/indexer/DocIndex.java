package minisearch.indexer;

import com.fasterxml.jackson.databind.JsonNode;
import minisearch.core.Document;
import minisearch.core.Index;
import minisearch.core.Tokenizer;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;


public class DocIndex {

    final private Index titleIndex;
    final private Index descriptionIndex;
    final private Tokenizer tokenizer;
    private Integer docNumber = 0;
    private Map<String, Document>  docStorage;

    public DocIndex() {
        this.titleIndex = new Index();
        this.descriptionIndex = new Index();
        this.tokenizer =  new Tokenizer();
        this.docStorage = new HashMap<>();
    }

    public Integer genTitleTokensNumber() {
        return this.titleIndex.getNTerms();
    }

    public Integer getDescriptionTokensNumber() {
        return this.descriptionIndex.getNTerms();
    }

    public Integer getDocumentsAmount()  {
        return this.docNumber;
    }

    public void addDocument(JsonNode document) {
        String docId = UUID.randomUUID().toString();
        String title = document.get("title").asText();
        String description = document.get("description").asText();
        this.docStorage.put(docId, new Document(title=title, description=description));

        List<String> title_tokens = tokenizer.apply(title);
        this.titleIndex.addDocument(docId, title_tokens);

        List<String> description_tokens = tokenizer.apply(description);
        this.descriptionIndex.addDocument(docId, description_tokens);

        this.docNumber++;
    }

    public void printStats() {
        System.out.println("----------------------");
        System.out.println("Number of documents: " + this.getDocumentsAmount());
        System.out.println("Number of title tokens:" + this.genTitleTokensNumber());
        System.out.println("Number of description tokens: " + this.getDescriptionTokensNumber());
    }

    public void findTerm(String term) {
        final List<String> docIds = this.descriptionIndex.findTerm(term);

        Document document;

        for (String docId: docIds) {
            document = this.docStorage.get(docId);
            System.out.println(document.getTitle());
            System.out.println(document.getDescription());
            System.out.println("--------------------");
        }

    }

}