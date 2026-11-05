public class InvoiceGenerator {

    public void printInvoice(Event event) {

        if (event == null) {
            System.out.println("Error: Event not found.");
            return;
        }

        System.out.println("----- INVOICE -----");

        System.out.println("Event: " + event.getName());
        System.out.println("Event Type: " + event.getClass().getSimpleName());
        System.out.println("Date: " + event.getDate());
        System.out.println("Guests: " + event.getGuestCount());

        double total = event.getBudget();

        System.out.println("TOTAL BUDGET: $" + total);

        System.out.println("-------------------");
    }
}