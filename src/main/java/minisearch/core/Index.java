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
    private Float k1 = 1.2f;
    private Float b = 0.75f;

    public Index() {
        this.postingMap = new HashMap<String, PostingList>();
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

    public List<String> findTerms(List<String> terms) {
        /*
        Calculate BM25 of the query
         */
        Map<String, Float> findings = new HashMap<String, Float>();
        for (String term : terms) {
            // берём очередной терм в запросе

            PostingList pl = this.postingMap.get(term);
            if (pl == null) {
                // надо продумать что тут должно быть
                continue;
            }

            // inverse document frequency
            Float idf = (float) ((this.nDocuments - pl.getDocumentFrequency() + 0.5)/(pl.getDocumentFrequency()+0.5) + 1);

            for (Posting posting: pl.getPostings()) {
                // term frequency
                Float tf = (float) ((posting.termFrequency()*(1+this.k1))/(posting.termFrequency()+this.k1*(1-this.b+this.b*1)));

                if (!findings.containsKey(posting.documentId())) {
                    findings.put(posting.documentId(), tf*idf);
                } else {
                    findings.computeIfPresent(posting.documentId(),
                            (k, v) -> v + tf*idf
                    );
                }
            }


        }

    }

}
