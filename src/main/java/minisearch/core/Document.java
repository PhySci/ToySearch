/*
One document instance
 */

package minisearch.core;

public class Document {

    private String id;
    private String title;
    private String description;

    public Document() {
    }

    public Document(String id, String title, String description) {
        this();
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

}
