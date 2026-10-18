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

    public boolean addDocument(String docId, List<String> terms) {

        Map<String, Integer> tokenCounter =  new HashMap<>();
        for (String term: terms) {
            tokenCounter.merge(term, 1, Integer::sum);
        }

        for (Map.Entry<String, Integer> entry: tokenCounter.entrySet()) {
            if (! this.postingMap.containsKey(entry.getKey())) {
                this.postingMap.put(entry.getKey(), new PostingList(docId, entry.getValue()));
            } else {
                PostingList pl = this.postingMap.get(entry.getKey());
                pl.addDocument(docId, entry.getValue());
            }
        }
        return true;
    }

    public List<String> getDocumentIds(List<String> terms) {
        return new ArrayList<>();
    }

    public Integer getNDocuments() {
        return this.nDocuments;
    }

    public Integer getNTerms() {
        return this.postingMap.size();
    }

    public List<String> findTerm(String term) {
        PostingList pl = this.postingMap.get(term);
        if (pl == null) {
            return new ArrayList<>();
        } else {
            return pl.getDocumentIds();
        }
    }

}
