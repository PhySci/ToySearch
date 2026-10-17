/*
Basic class of posting list

@TODO: implement ordered insert
 */
package minisearch.core;

import java.util.List;
import java.util.ArrayList;

public class PostingList {

    private Integer tokenFrequency;
    private List<Integer> docIds;

    public PostingList(Integer docId) {
        this();
        this.addDocument(docId);
    }

    public PostingList() {
        this.tokenFrequency = 0;
        this.docIds = new ArrayList<>();
    }

    public boolean addDocument(Integer docId) {
        this.docIds.add(docId);
        this.tokenFrequency += 1;
        return true;
    }

    public Integer getTokenFrequency() {
        return this.tokenFrequency;
    }

}
