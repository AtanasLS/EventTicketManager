package be;

public class UserEvent {
    private int ID;
    private int userId;
    private int eventId;

    public UserEvent(int eventId, int userId){
        this.userId = userId;
        this.eventId = eventId;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
