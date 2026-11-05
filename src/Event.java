import java.util.Objects;
import java.util.UUID;

// Main parent class. Natural ordering: by date, then by ID.
public class Event implements Comparable<Event> {
    private final String id;
    private String name;
    private String date; // Use ISO format: yyyy-MM-dd
    private int guestCount;
    private double budget;

    public Event() {
        this(UUID.randomUUID().toString(), "Unnamed Event", "TBD", 1, 0.0);
    }

    public Event(String name, String date, int guestCount, double budget) {
        this(UUID.randomUUID().toString(), name, date, guestCount, budget);
    }

    // Constructor with an explicit ID is useful when importing existing data.
    public Event(String id, String name, String date, int guestCount, double budget) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Event ID cannot be empty.");
        }
        this.id = id;
        setName(name);
        setDate(date);
        setGuestCount(guestCount);
        setBudget(budget);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDate() { return date; }
    public int getGuestCount() { return guestCount; }
    public double getBudget() { return budget; }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Event name cannot be empty.");
        }
        this.name = name;
    }

    public void setDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("Event date cannot be empty.");
        }
        this.date = date;
    }

    public void setGuestCount(int guestCount) {
        if (guestCount <= 0) {
            throw new IllegalArgumentException("Guest count must be positive.");
        }
        this.guestCount = guestCount;
    }

    public void setBudget(double budget) {
        if (budget < 0) {
            throw new IllegalArgumentException("Budget cannot be negative.");
        }
        this.budget = budget;
    }

    public void displayInfo() {
        System.out.println("ID: " + id + " | Event: " + name
                + " | Date: " + date + " | Guests: " + guestCount
                + " | Budget: $" + budget);
    }

    @Override
    public int compareTo(Event other) {
        int dateResult = this.date.compareTo(other.date);
        if (dateResult != 0) return dateResult;
        return this.id.compareTo(other.id);
    }

    // The unique business key is ID.
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Event event = (Event) object;
        return id.equals(event.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), id);
    }
}
