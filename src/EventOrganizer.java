
public class EventOrganizer {
    private final String name;
    private final EventService service;

    public EventOrganizer(String name, EventService service) {
        this.name = name;
        this.service = service;
    }

    public void submitEvent(Event event, int priority) {
        service.submitRequest(event, name, priority);
    }
}
