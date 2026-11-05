//manager takes requests from the queue and registers events.
public class EventManager {
    private final EventService service;

    public EventManager(EventService service) {
        this.service = service;
    }

    public void processNextRequest() {
        EventRequest request = service.getNextRequest();

        if (request == null) {
            System.out.println("There are no pending requests");
            return;
        }

        System.out.println("Manager is processing request from "
                + request.getSubmittedBy());
        service.addEvent(request.getEvent());
    }

    public void processAllRequests() {
        while (service.getQueueSize() > 0) {
            processNextRequest();
        }
    }
}
