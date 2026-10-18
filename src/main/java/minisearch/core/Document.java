/*
One document instance
 */

package minisearch.core;

public class Document {

    private String title;
    private String description;

    public Document() {
    }

    public Document(String title, String description) {
        this();
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

}
