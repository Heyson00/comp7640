package person.nicholas.crm.entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Data;

@Data
public class Tag {

//    private int tagId;
//    private String tagName;
    private final SimpleIntegerProperty tagId = new SimpleIntegerProperty();
    private final SimpleStringProperty tagName = new SimpleStringProperty();

    public Tag(){}

    public Tag(int tagId, String tagName) {
        this.tagId.set(tagId);
        this.tagName.set(tagName);
    }

    public int getTagId() { return tagId.get(); }
    public void setTagId(int tagId) { this.tagId.set(tagId); }
    public SimpleIntegerProperty tagIdProperty() { return tagId; }
    public String getTagName() { return tagName.get(); }
    public void setTagName(String tagName) { this.tagName.set(tagName); }
    public SimpleStringProperty tagNameProperty() { return tagName; }

}
