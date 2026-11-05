import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class EventService {
    // List: keeps the main collection of events.
    private final List<Event> events = new ArrayList<>();

    // Set: prevents duplicate events according to equals() and hashCode().
    private final Set<Event> uniqueEvents = new HashSet<>();

    // Map: O(1) average-time lookup by primary key (ID).
    private final Map<String, Event> eventsById = new HashMap<>();

    // Map: groups events by their concrete type.
    private final Map<String, List<Event>> eventsByType = new HashMap<>();

    // PriorityQueue: higher-priority requests are processed first.
    private final Queue<EventRequest> requestQueue = new PriorityQueue<>(
            Comparator.comparingInt(EventRequest::getPriority).reversed()
                    .thenComparingLong(EventRequest::getSequence)
    );

    private long requestSequence = 0;

    // Three custom comparators required by the assignment.
    public static final Comparator<Event> BY_NAME =
            Comparator.comparing(Event::getName, String.CASE_INSENSITIVE_ORDER);

    public static final Comparator<Event> BY_BUDGET =
            Comparator.comparingDouble(Event::getBudget);

    public static final Comparator<Event> BY_GUEST_COUNT =
            Comparator.comparingInt(Event::getGuestCount).reversed();

    // Synchronized CRUD: every related collection/index is updated together.
    public synchronized void addEvent(Event event) {
        if (event == null) throw new IllegalArgumentException("Event cannot be null.");
        if (eventsById.containsKey(event.getId()))
            throw new IllegalArgumentException("An event with this ID already exists.");

        events.add(event);
        uniqueEvents.add(event);
        eventsById.put(event.getId(), event);
        rebuildTypeIndex();
        System.out.println("Event added: " + event.getName());
    }

    public synchronized Event findById(String id) {
        return eventsById.get(id);
    }

    public synchronized boolean updateEvent(String id, Consumer<Event> changes) {
        Event event = eventsById.get(id);
        if (event == null) return false;
        if (changes == null) throw new IllegalArgumentException("Changes cannot be null.");

        changes.accept(event);
        rebuildTypeIndex();
        return true;
    }

    public synchronized boolean removeEvent(String id) {
        Event event = eventsById.remove(id);
        if (event == null) return false;

        events.remove(event);
        uniqueEvents.remove(event);
        rebuildTypeIndex();
        System.out.println("Event removed: " + event.getName());
        return true;
    }

    private void rebuildTypeIndex() {
        eventsByType.clear();
        for (Event event : events) {
            String type = event.getClass().getSimpleName();
            eventsByType.computeIfAbsent(type, key -> new ArrayList<>()).add(event);
        }
    }

    public synchronized List<Event> getAllEvents() {
        return Collections.unmodifiableList(new ArrayList<>(events));
    }

    public synchronized Set<Event> getUniqueEvents() {
        return Collections.unmodifiableSet(new HashSet<>(uniqueEvents));
    }

    public synchronized List<Event> getEventsByType(String type) {
        List<Event> result = eventsByType.get(type);
        if (result == null) return Collections.emptyList();
        return Collections.unmodifiableList(new ArrayList<>(result));
    }

    public synchronized List<Event> searchByMinimumBudget(double minimumBudget) {
        return events.stream()
                .filter(event -> event.getBudget() >= minimumBudget)
                .collect(Collectors.toList());
    }

    public synchronized List<Event> getSortedEvents(Comparator<Event> comparator) {
        List<Event> result = new ArrayList<>(events);
        result.sort(comparator);
        return result;
    }

    // Natural sorting uses Event.compareTo(): by date, then by ID.
    public synchronized List<Event> getNaturallySortedEvents() {
        List<Event> result = new ArrayList<>(events);
        Collections.sort(result);
        return result;
    }

    public synchronized void submitRequest(Event event, String organizer, int priority) {
        requestQueue.offer(new EventRequest(event, organizer, priority, requestSequence++));
        System.out.println("Request submitted by " + organizer
                + " with priority " + priority);
    }

    public synchronized EventRequest getNextRequest() {
        return requestQueue.poll(); // removes and returns the next request
    }

    public synchronized int getQueueSize() {
        return requestQueue.size();
    }

    public synchronized void printAnalytics() {
        double totalBudget = 0;
        int totalGuests = 0;

        for (Event event : events) {
            totalBudget += event.getBudget();
            totalGuests += event.getGuestCount();
        }

        System.out.println("\n EVENT ANALYTICS ");
        System.out.println("Total events: " + events.size());
        System.out.println("Unique events: " + uniqueEvents.size());
        System.out.println("Total planned budget: $" + totalBudget);
        System.out.println("Total guests: " + totalGuests);
        System.out.println("Average guests per event: "
                + (events.isEmpty() ? 0 : (double) totalGuests / events.size()));
        System.out.println("Events by type:");

        for (Map.Entry<String, List<Event>> entry : eventsByType.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().size());
        }
    }
}

