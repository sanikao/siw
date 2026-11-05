public class BirthdayParty extends Event {
    private String celebrantName;
    private int age;

    public BirthdayParty(String name, String date, int guestCount, double budget,
                         String celebrantName, int age) {
        super(name, date, guestCount, budget);
        setCelebrantName(celebrantName);
        setAge(age);
    }

    public BirthdayParty(String id, String name, String date, int guestCount, double budget,
                         String celebrantName, int age) {
        super(id, name, date, guestCount, budget);
        setCelebrantName(celebrantName);
        setAge(age);
    }

    public String getCelebrantName() { return celebrantName; }
    public int getAge() { return age; }

    public void setCelebrantName(String celebrantName) {
        if (celebrantName == null || celebrantName.trim().isEmpty())
            throw new IllegalArgumentException("Celebrant name cannot be empty");
        this.celebrantName = celebrantName;
    }

    public void setAge(int age) {
        if (age <= 0 || age > 120)
            throw new IllegalArgumentException("Age must be between 1 and 120");
        this.age = age;
    }

    public void orderCake() {
        System.out.println("Ordering a cake for " + getGuestCount()
                + " guests and " + celebrantName);
    }

    public double calculateGiftBagCost() {
        return getGuestCount() * 4.0;
    }

    public void playGame() {
        System.out.println("Starting party games for " + celebrantName + "'s birthday!");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Birthday | Celebrant: " + celebrantName
                + " | Turning: " + age);
    }

    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
