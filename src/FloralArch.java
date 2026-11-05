public class FloralArch {
    private String style = "Classic";
    private int flowerCount = 1;

    public FloralArch(String style, int flowerCount) {
        setStyle(style);
        setFlowerCount(flowerCount);
    }

    public String getStyle() { return style; }
    public void setStyle(String style) {
        if (style == null || style.trim().isEmpty()) {
            System.out.println("Validation error: arch style cannot be empty");
            return;
        }
        this.style = style;
    }

    public int getFlowerCount() { return flowerCount; }
    public void setFlowerCount(int flowerCount) {
        if (flowerCount <= 0) {
            System.out.println("Validation error: flower count must be positive.");
            return;
        }
        this.flowerCount = flowerCount;
    }

    public double calculateCost() {
        return flowerCount * 1.5;
    }

    public void build() {
        System.out.println("   Building a floral arch (" + style + ", " + flowerCount + " flowers)");
    }

    public void displayInfo() {
        System.out.println("   FloralArch: " + style + " | Flowers: " + flowerCount
                + " | Cost: $" + calculateCost());
    }
}