import java.util.List;

public class Main {
    public static void main(String[] args) {
        EventService service = new EventService();

        System.out.println("Creating events");
        Event corporate = new Event("Team Building", "2026-11-05", 40, 3000);
        Wedding wedding = new Wedding("Toi Wedding", "2026-06-20",
                120, 15000, "Aruuke & Tilek", false);
        Conference conference = new Conference("Tech Summit", "2026-09-12",
                300, 45000, "Something technological", 8);
        BirthdayParty birthday = new BirthdayParty("Birthday Bash", "2026-12-01",
                25, 1200, "Aisulu", 21);
        Wedding wedding2 = new Wedding("Kyz-Uzatuu Wedding", "2027-02-14",
                60, 9000, "Nurdzhigit & Ayana", true);

        System.out.println("\n Adding events (list,set,map) ⠀");
        service.addEvent(corporate);
        service.addEvent(wedding);
        service.addEvent(conference);
        service.addEvent(birthday);
        service.addEvent(wedding2);

        System.out.println("\n Search by id");
        Event found = service.findById(wedding.getId());
        if (found != null) found.displayInfo();

        System.out.println("\n Grouping by type");
        List<Event> weddings = service.getEventsByType("Wedding");
        System.out.println("Number of weddings: " + weddings.size());

        System.out.println("\n Natural order");
        for (Event event : service.getNaturallySortedEvents()) {
            System.out.println(event.getDate() + " - " + event.getName());
        }

        System.out.println("\n Custom comparators");
        System.out.println("By name:");
        for (Event event : service.getSortedEvents(EventService.BY_NAME))
            System.out.println(event.getName());

        System.out.println("By budget:");
        for (Event event : service.getSortedEvents(EventService.BY_BUDGET))
            System.out.println(event.getName() + " - $" + event.getBudget());

        System.out.println("By guest count (highest first):");
        for (Event event : service.getSortedEvents(EventService.BY_GUEST_COUNT))
            System.out.println(event.getName() + " - " + event.getGuestCount());

        System.out.println("\n FILTERING ");
        for (Event event : service.searchByMinimumBudget(5000))
            System.out.println(event.getName() + " has budget $" + event.getBudget());

        System.out.println("\n ROLE WORKFLOW AND PRIORITY QUEUE ");
        EventOrganizer organizer = new EventOrganizer("Sanira", service);
        EventManager manager = new EventManager(service);

        Event charity = new Event("Charity Event", "2027-03-10", 150, 7000);
        Event workshop = new Event("Design Workshop", "2027-03-15", 35, 1800);

        organizer.submitEvent(charity, 10);
        organizer.submitEvent(workshop, 5);

        manager.processAllRequests();

        System.out.println("\n UPDATE EVENT (CRUD) ");
        service.updateEvent(corporate.getId(), event -> {
            event.setBudget(3500);
            event.setGuestCount(50);
        });
        service.findById(corporate.getId()).displayInfo();

        System.out.println("\n REMOVE EVENT (CRUD) ");
        service.removeEvent(birthday.getId());
        System.out.println("Remaining events: " + service.getAllEvents().size());

        System.out.println("\n ANALYTICS ");
        service.printAnalytics();

        System.out.println("\n SUBCLASS METHODS ");
        wedding.sendInvitations();
        wedding.hireLiveBand();
        System.out.println("Floral decoration cost: $"
                + wedding.calculateFloralDecorationCost());

        conference.addSpeaker();
        conference.printSchedule();
        System.out.println("Ticket price: $" + conference.calculateTicketPrice());

        birthday.orderCake();
        birthday.playGame();
        System.out.println("Gift bags cost: $" + birthday.calculateGiftBagCost());
    }
}
