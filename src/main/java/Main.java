import exception.NotfoundException;
import model.*;
import model.enumeration.BusType;
import model.enumeration.Gender;
import service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @author Mahsa Alikhani m-58
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static AdminService adminService;
    private static UserService userService;
    private static TripService tripService;
    private static TicketService ticketService;
    private static PassengerService passengerService;
    private static BusService busService;
    public static void main(String[] args) {
        adminService = new AdminService();
        userService = new UserService();
        tripService = new TripService();
        ticketService = new TicketService();
        passengerService = new PassengerService();
        busService = new BusService();
        System.out.println("1.User\n2.Admin");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                try{
                    userLogin();
                }catch (NotfoundException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                adminLogin();
        }
    }

    private static void adminLogin() {
        System.out.println("username:");
        String username = scanner.next();
        System.out.println("password:");
        String password = scanner.next();
        if(adminService.adminValidation(username, password)){
            showBusReports();
        }else
            throw new NotfoundException("Not found");
    }

    private static void showBusReports() {
        System.out.println("bus type;\n1.VIP\n2.Economic");
        int type = scanner.nextInt();
        BusType busType = null;
        if(type == 1){
            busType = BusType.VIP;
        }else if(type == 2){
            busType = BusType.ECONOMIC;
        }
        List<BusDto> busReportsList = busService.getBusReportsByType(busType);
        for (BusDto item: busReportsList) {
            System.out.println(item);
        }
    }

    private static void userLogin() {
        System.out.println("username:");
        String username = scanner.next();
        System.out.println("password:");
        String password = scanner.next();
        if(userService.userValidation(username, password)){
            showAvailableTripsInfo();
        }else
            throw new NotfoundException("Not found");
    }

    private static void showAvailableTripsInfo() {
        System.out.println("origin:");
        String origin = scanner.next();
        System.out.println("destination:");
        String destination = scanner.next();
        System.out.println("departure date:");
        String departureDateStr = scanner.next();
        Date departureDate = null;
        try {
            departureDate = new SimpleDateFormat("yyyy-MM-dd").parse(departureDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("corporation name:");
        String corporationName = scanner.next();
        System.out.println("Bus type:\n1.VIP\n2.Economic");
        int busChoice = scanner.nextInt();
        BusType busType;
        if(busChoice == 1){
            busType = BusType.VIP;
        }else if(busChoice == 2){
            busType = BusType.ECONOMIC;
        }else{
            busType = null;
        }
        Double[] priceRange = new Double[2];
        System.out.println("max price and min price:");
        priceRange[0] = scanner.nextDouble();
        priceRange[1] = scanner.nextDouble();
        Date[] departureTimeRange = new Date[2];
        System.out.println("max departure time and min departure time: (like 22:20 0r 05:00)");
        try {
            departureTimeRange[0] = new SimpleDateFormat("hh:mm").parse(scanner.next());
            departureTimeRange[1] = new SimpleDateFormat("hh:mm").parse(scanner.next());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("number of result:");
        int pageSize = scanner.nextInt();
        int startIndex = 0;
        int currentPage = 0;
        long count = tripService.getCountOfTrips(origin, destination, departureDate,
                corporationName, busType, priceRange, departureTimeRange);

        List<TripDto> tripsInfo = tripService.getAvailableTripsInfo(origin, destination, departureDate,
                corporationName, busType, priceRange, departureTimeRange, pageSize, startIndex);
        for (TripDto item: tripsInfo) {
            System.out.println(item);
        }
        currentPage++;
        int lastPage = (int) (Math.ceil(count / pageSize));
        if(count != 0){
            while(true){
                if(currentPage == 1 && count > pageSize){
                    System.out.println("1.next page\n2.book ticket\n3.exit");
                    int select = scanner.nextInt();
                    if(select == 1){
                        tripsInfo = tripService.getAvailableTripsInfo(origin, destination, departureDate,
                                corporationName, busType, priceRange, departureTimeRange, pageSize, startIndex + pageSize);
                        currentPage++;
                    }else  if(select == 2){
                        viewTripDetail();
                    }else if(select == 3)
                        break;
                }
                if(1<currentPage && currentPage<lastPage && count>pageSize){
                    System.out.println("1.next page\n2.previous page\n3.book ticket\n4.exit");
                    int select = scanner.nextInt();
                    if(select == 1 && currentPage != lastPage){
                        tripsInfo = tripService.getAvailableTripsInfo(origin, destination, departureDate,
                                corporationName, busType, priceRange, departureTimeRange, pageSize, startIndex + pageSize);
                        currentPage++;
                    }else if(select == 2 && currentPage != 0){
                        tripsInfo = tripService.getAvailableTripsInfo(origin, destination, departureDate,
                                corporationName, busType, priceRange, departureTimeRange, pageSize, startIndex - pageSize);
                        currentPage--;
                    }else if(select == 3){
                        viewTripDetail();
                    }else if(select == 4){
                        break;
                    }
                }
                if(currentPage == lastPage && count > pageSize){
                    System.out.println("1.previous page\n2.book ticket\n3.exit");
                    int select = scanner.nextInt();
                    if(select == 1){
                        tripsInfo = tripService.getAvailableTripsInfo(origin, destination, departureDate,
                                corporationName, busType, priceRange, departureTimeRange, pageSize, startIndex - pageSize);
                        currentPage--;
                    }else  if(select == 2){
                        viewTripDetail();
                    }else if(select == 3)
                        break;
                }
                for (TripDto item: tripsInfo) {
                    System.out.println(item);
                }
            }
        }else{
            System.out.println("there is no scheduled trip!");
        }
    }

    private static void viewTripDetail() {
        System.out.println("select a trip id and view details");
        int id = scanner.nextInt();
        Trip trip = tripService.getTripDetailById(id);
        System.out.println(trip);
        bookTicket(trip);
    }

    private static void bookTicket(Trip trip) {
        System.out.println("y/n?");
        String choice = scanner.next();
        if(choice.equalsIgnoreCase("y")){
            System.out.println("number of tickets:");
            int passengerNumbers = scanner.nextInt();
            if(trip.getRemainingSeats() >= passengerNumbers){
                int remainingSeats = trip.getRemainingSeats() - passengerNumbers;
                trip.setRemainingSeats(remainingSeats);
                tripService.updateRemainingSeats(trip);
            }else {
                System.out.println("number of passengers are greater than remaining seats!");
            }
            List<Ticket> tickets = new ArrayList<>();
            for (int i = 0; i < passengerNumbers; i++){
                System.out.println("name, family, phone number, national number, age");
                String name = scanner.next();
                String family = scanner.next();
                String phoneNumber = scanner.next();
                long nationalNumber = scanner.nextLong();
                int age = scanner.nextInt();
                System.out.println("gender: 1.Female 2. Male");
                int genderChoice = scanner.nextInt();
                Gender gender;
                if(genderChoice == 1){
                    gender = Gender.FEMALE;
                }else if(genderChoice == 2){
                    gender = Gender.MALE;
                }else {
                    gender = null;
                }
                Passenger passenger = new Passenger();
                passenger.setName(name);
                passenger.setFamily(family);
                passenger.setPhoneNumber(phoneNumber);
                passenger.setNationalNumber(nationalNumber);
                passenger.setAge(age);
                passenger.setGender(gender);
                passengerService.saveNewPassenger(passenger);
                Ticket ticket = new Ticket();
                ticket.setTrip(trip);
                ticket.setPassenger(passenger);
                tickets.add(ticket);
                ticketService.saveNewTicket(ticket);
            }
            for (Ticket ticket: tickets) {
                System.out.println(ticket);
            }
        }else{
            //todo exit
        }
    }
}
