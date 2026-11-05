public class Venue {
    private String name = "Unknown venue";
    private int capacity = 1;
    private double rentalPrice = 0.0;

    public Venue(String name, int capacity, double rentalPrice) {
        setName(name);
        setCapacity(capacity);
        setRentalPrice(rentalPrice);
    }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Validation error: venue name cannot be empty");
            return;
        }
        this.name = name;
    }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) {
        if (capacity <= 0) {
            System.out.println("Validation error: capacity must be positive");
            return;
        }
        this.capacity = capacity;
    }

    public double getRentalPrice() { return rentalPrice; }
    public void setRentalPrice(double rentalPrice) {
        if (rentalPrice < 0) {
            System.out.println("Validation error: rental price cannot be negative");
            return;
        }
        this.rentalPrice = rentalPrice;
    }

    public boolean canHost(int guests) {
        return guests <= capacity;
    }

    public void displayInfo() {
        System.out.println("Venue: " + name + " | Capacity: " + capacity
                + " | Rental: $" + rentalPrice);
    }
}