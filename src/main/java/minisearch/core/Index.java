/*
A basic class to create inverted index
 */

package minisearch.core;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class Index {

    private Map<String, PostingList> postingMap;
    private Integer nDocuments;

    public Index() {
        this.postingMap = new HashMap<>();
        this.nDocuments = 0;
    }


    public boolean addDocument(List<String> terms) {
        Integer documentId = this.nDocuments + 1;

        for (String term: terms) {
            if (! this.postingMap.containsKey(term)) {
                this.postingMap.put(term, new PostingList(documentId));
            } else {
                PostingList pl = this.postingMap.get(term);
                pl.addDocument(documentId);
            }
        }
        return true;
    }

    public List<String> getDocumentIds(List<String> terms) {
        return new ArrayList<>();
    }


}
