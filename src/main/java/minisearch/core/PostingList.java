/*
Basic class of posting list

@TODO: implement ordered insert
 */
package minisearch.core;

import java.util.List;
import java.util.ArrayList;

public class PostingList {

    private Integer tokenFrequency;
    private List<String> docIds;

    public PostingList(String docId) {
        this();
        this.addDocument(docId);
    }

    public PostingList() {
        this.tokenFrequency = 0;
        this.docIds = new ArrayList<>();
    }

    public boolean addDocument(String docId) {
        this.docIds.add(docId);
        this.tokenFrequency += 1;
        return true;
    }

    public Integer getTokenFrequency() {
        return this.tokenFrequency;
    }

    public List<String> getDocumentIds() {
        return this.docIds;
    }

}
