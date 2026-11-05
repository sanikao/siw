public class Wedding extends Event {
    private String coupleNames;
    private boolean hasLiveBand;

    public Wedding(String name, String date, int guestCount, double budget,
                   String coupleNames, boolean hasLiveBand) {
        super(name, date, guestCount, budget);
        setCoupleNames(coupleNames);
        this.hasLiveBand = hasLiveBand;
    }

    public Wedding(String id, String name, String date, int guestCount, double budget,
                   String coupleNames, boolean hasLiveBand) {
        super(id, name, date, guestCount, budget);
        setCoupleNames(coupleNames);
        this.hasLiveBand = hasLiveBand;
    }

    public String getCoupleNames() { return coupleNames; }
    public boolean isHasLiveBand() { return hasLiveBand; }

    public void setCoupleNames(String coupleNames) {
        if (coupleNames == null || coupleNames.trim().isEmpty())
            throw new IllegalArgumentException("Couple names cannot be empty");
        this.coupleNames = coupleNames;
    }

    public void setHasLiveBand(boolean hasLiveBand) { this.hasLiveBand = hasLiveBand; }

    public double calculateFloralDecorationCost() {
        return getGuestCount() * 12.5;
    }

    public void sendInvitations() {
        System.out.println("Sending " + getGuestCount()
                + " invitations for " + coupleNames);
    }

    public void hireLiveBand() {
        hasLiveBand = true;
        System.out.println("Live band hired for " + getName());
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Wedding | Couple: " + coupleNames
                + " | Live band: " + (hasLiveBand ? "yes" : "no"));
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
