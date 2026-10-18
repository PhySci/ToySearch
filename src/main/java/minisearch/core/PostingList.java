/*
Basic class of posting list

@TODO: implement ordered insert
 */
package minisearch.core;

import java.util.List;
import java.util.ArrayList;

public class PostingList {

    private Integer documentFrequency;
    private List<Posting> postings;

    public PostingList(String docId, Integer termFrequency) {
        this();
        this.addDocument(docId, termFrequency);
    }

    public PostingList() {
        this.documentFrequency = 0;
        this.postings = new ArrayList<>();
    }

    public boolean addDocument(String documentId, Integer termFrequency) {
        this.postings.add(new Posting(documentId=documentId, termFrequency=termFrequency));
        this.documentFrequency += 1;
        return true;
    }

    public Integer getDocumentFrequency() {
        return this.documentFrequency;
    }

    public List<String> getDocumentIds() {
        return this.;
    }

}
