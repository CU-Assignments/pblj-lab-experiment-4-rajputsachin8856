class TicketBookingSystem {
    private final boolean[] seats;
    
    public TicketBookingSystem(int totalSeats) {
        seats = new boolean[totalSeats];
    }

    public synchronized void bookSeat(String userName, int seatNumber, boolean isVIP) {
        if (seatNumber < 1 || seatNumber > seats.length) {
            System.out.println("Invalid seat number!");
            return;
        }

        if (seats[seatNumber - 1]) {
            System.out.println(userName + ": Seat " + seatNumber + " is already booked!");
        } else {
            seats[seatNumber - 1] = true;
            System.out.println(userName + (isVIP ? " (VIP)" : "") + " booked seat " + seatNumber);
        }
    }
}

class BookingThread extends Thread {
    private final TicketBookingSystem system;
    private final String userName;
    private final int seatNumber;
    private final boolean isVIP;

    public BookingThread(TicketBookingSystem system, String userName, int seatNumber, boolean isVIP) {
        this.system = system;
        this.userName = userName;
        this.seatNumber = seatNumber;
        this.isVIP = isVIP;
    }

    @Override
    public void run() {
        system.bookSeat(userName, seatNumber, isVIP);
    }
}

public class TicketBookingApp {

    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(5);

        System.out.println("Test Case 1: No Seats Available Initially");
        // No users attempt to book, so just show the initial status.
        System.out.println("No bookings yet.\n");

        System.out.println("Test Case 2: Successful Booking");
        BookingThread user1 = new BookingThread(system, "Anish", 1, true);  // VIP
        BookingThread user2 = new BookingThread(system, "Bobby", 2, false);  // Regular
        BookingThread user3 = new BookingThread(system, "Charlie", 3, true);  // VIP
        
        user1.start();
        user2.start();
        user3.start();
        
        try {
            user1.join();
            user2.join();
            user3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nTest Case 3: Thread Priorities (VIP First)");
        BookingThread user4 = new BookingThread(system, "Bobby", 4, false);
        BookingThread user5 = new BookingThread(system, "Anish", 4, true);  // VIP (High priority)

        user5.setPriority(Thread.MAX_PRIORITY);
        user4.setPriority(Thread.MIN_PRIORITY);

        user5.start();
        user4.start();
        
        try {
            user5.join();
            user4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nTest Case 4: Preventing Double Booking");
        BookingThread user6 = new BookingThread(system, "Anish", 1, true);  // VIP
        BookingThread user7 = new BookingThread(system, "Bobby", 1, false);  // Regular
        
        user6.start();
        user7.start();
        
        try {
            user6.join();
            user7.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nTest Case 5: Booking After All Seats Are Taken");
        BookingThread user8 = new BookingThread(system, "Mike", 3, false);  // Regular
        user8.start();
        
        try {
            user8.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nTest Case 6: Invalid Seat Selection");
        BookingThread user9 = new BookingThread(system, "Sarah", 0, false);  // Invalid seat
        BookingThread user10 = new BookingThread(system, "Tim", 6, false);  // Invalid seat

        user9.start();
        user10.start();
        
        try {
            user9.join();
            user10.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nTest Case 7: Simultaneous Bookings (Concurrency Test)");
        for (int i = 1; i <= 10; i++) {
            BookingThread user = new BookingThread(system, "User" + i, i % 5 + 1, i % 2 == 0);  // Alternating VIP and Regular
            user.start();
        }
    }
}
