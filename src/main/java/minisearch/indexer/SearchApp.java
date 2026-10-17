package minisearch.indexer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import minisearch.core.DocIndex;

// import minisearch.core.DocIndex;

public class SearchApp {

    public static void main(String[] args){
        System.out.println("Run application");
        ObjectMapper mapper = new ObjectMapper();
        DocIndex docIndex = new DocIndex();

        String pthStr = "/Users/fm/Projects/search-engine/src/main/resources/ag_news.jsonl";
        List<String> dataLines = new ArrayList<>();
        Path pth = Path.of(pthStr);
        List<JsonNode> documents = new ArrayList<>();

        try {
            dataLines = Files.readAllLines(pth);
            System.out.println("Read documents: " + dataLines.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String jsonString : dataLines) {
            try {
                JsonNode rootNode = mapper.readTree(jsonString);
                documents.add(rootNode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (JsonNode document: documents) {
            docIndex.addDocument(document);
        }

        docIndex.printStats();

    }
}