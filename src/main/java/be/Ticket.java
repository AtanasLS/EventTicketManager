package be;

public class Ticket {
    private int ticketId;
    private int customerId;
    private int eventId;

    public Ticket(int ticketId, int customerId, int eventId){
        this.ticketId = ticketId;
        this.customerId = customerId;
        this.eventId = eventId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
