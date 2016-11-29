package cheetahmail_drools_java;

/**
 * Created by depatel on 11/26/2016.
 */
public class CampaignEvent {

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;

    public int getEventType() {
        return eventType;
    }

    public void setEventType() {
        if(this.value != null){
            this.eventType = Integer.parseInt(this.value.split("\\|")[0]);
        } else {
            this.eventType = 0;
        }
    }

    private int eventType;

    public boolean isValidEvent() {
        return validEvent;
    }

    public void setValidEvent(boolean parsed) {
        validEvent = parsed;
    }

    private boolean validEvent = false;

    public CampaignEvent(String value) {
        setValue(value);
        setEventType();
    }
}
