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
        for (String term: terms) {
            if (! this.postingMap.containsKey(term)) {
                this.postingMap.put(term, new PostingList(docId));
            } else {
                PostingList pl = this.postingMap.get(term);
                pl.addDocument(docId);
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
