package person.nicholas.crm.entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author xujingling
 */
public class Vendor {
    private final SimpleIntegerProperty vendorId = new SimpleIntegerProperty(this, "vendorId");
    private final SimpleStringProperty businessName = new SimpleStringProperty(this, "businessName");
    private final SimpleIntegerProperty feedbackScore = new SimpleIntegerProperty(this, "feedbackScore");
    private final SimpleStringProperty geographicalPresence = new SimpleStringProperty(this, "geographicalPresence");

    private Date createdDate;
    private Date updatedDate;

    public Vendor() {
    }

    public Vendor(int vendorId, String businessName, int feedbackScore, String geographicalPresence) {
        this.vendorId.set(vendorId);
        this.businessName.set(businessName);
        this.feedbackScore.set(feedbackScore);
        this.geographicalPresence.set(geographicalPresence);
        this.createdDate = new Date();
        this.updatedDate = new Date();
    }
    // 省略其他部分

    public int getVendorId() {
        return vendorId.get();
    }

    public void setVendorId(int vendorId) {
        this.vendorId.set(vendorId);
    }

    public SimpleIntegerProperty vendorIdProperty() {
        return vendorId;
    }

    public String getBusinessName() {
        return businessName.get();
    }

    public SimpleStringProperty businessNameProperty() {
        return businessName;
    }

    public int getFeedbackScore() {
        return feedbackScore.get();
    }

    public SimpleIntegerProperty feedbackScoreProperty() {
        return feedbackScore;
    }

    public String getGeographicalPresence() {
        return geographicalPresence.get();
    }

    public SimpleStringProperty geographicalPresenceProperty() {
        return geographicalPresence;
    }

    public void setBusinessName(String businessName) {
        this.businessName.set(businessName);
    }

    // 省略其他部分
}