package be;

public class TicketObs {
    private String customerName;
    private String eventName;

    public TicketObs(String customerName, String eventName) {
        this.customerName = customerName;
        this.eventName = eventName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
