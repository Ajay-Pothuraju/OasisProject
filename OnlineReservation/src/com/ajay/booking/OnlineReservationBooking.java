package com.ajay.booking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class OnlineReservationBooking extends User{
	public OnlineReservationBooking() {
		super();
		System.out.println("OnlineReservationBooking 0-Param Constructor.");
	}

	public static String train_number="35A45B";
	public static String train_name="Golconda Express";
	
	public static ArrayList<User> registeredUsers = new ArrayList<>();

	static {
	    User user1 = new User();
	    user1.setUserId("ajay123");
	    user1.setPassword("pass123");
	    registeredUsers.add(user1);

	    User user2 = new User();
	    user2.setUserId("admin");
	    user2.setPassword("admin123");
	    registeredUsers.add(user2);
	}

	
	
	private String name;
	private long phoneNumber;
	private int age;
	
	private String class_type;
	private LocalDate ld;
	private String sourcePlace;
	private String destinationPlace;
	private String pnr;
	
	public static String getTrain_number() {
		return train_number;
	}
	public static void setTrain_number(String train_number) {
		OnlineReservationBooking.train_number = train_number;
	}
	public static String getTrain_name() {
		return train_name;
	}
	public static void setTrain_name(String train_name) {
		OnlineReservationBooking.train_name = train_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getClass_type() {
		return class_type;
	}
	public void setClass_type(String class_type) {
		this.class_type = class_type;
	}
	public LocalDate getLd() {
		return ld;
	}
	public void setLd(LocalDate ld) {
		this.ld = ld;
	}
	public String getSourcePlace() {
		return sourcePlace;
	}
	public void setSourcePlace(String sourcePlace) {
		this.sourcePlace = sourcePlace;
	}
	public String getDestinationPlace() {
		return destinationPlace;
	}
	public void setDestinationPlace(String destinationPlace) {
		this.destinationPlace = destinationPlace;
	}
	public String getPnr() {
		return pnr;
	}
	public void setPnr(String pnr) {
		this.pnr = pnr;
	}
	
	public static String generatePNR() {
		return "PNR"+(int) (Math.random()*1000000);
	}
	
	
	public void login(String uid,String password,Scanner scanner) throws InvalidInputException{
		boolean validUser=false;
		for(User user:registeredUsers) {
			if(user.getUserId().equals(uid) && user.getPassword().equals(password)) {
				validUser=true;
				break;
			}
		}
		if(validUser) {
			System.out.println("Enter the Name of Passenger: ");
			setName(scanner.next());
			System.out.println("Enter the Age of Passenger: ");
			setAge(scanner.nextInt());
			System.out.println("Enter the PhoneNumber Of Passenger: ");
			setPhoneNumber(scanner.nextLong());
			System.out.println("Enter the Class Type: ");
			setClass_type(scanner.next());
			System.out.println("Enter Date of the Journey (yyyy-MM-dd): ");
			try {
			    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
			    setLd(LocalDate.parse(scanner.next(), formatter));
			} catch (DateTimeParseException e) {
			    System.out.println("Invalid date format. Please enter as yyyy-MM-dd.");
			    return;
			}
			System.out.println("Enter the Source Place: ");
			setSourcePlace(scanner.next());
			System.out.println("Enter the Destination Place: ");
			setDestinationPlace(scanner.next());
			this.setPnr(generatePNR());
			System.out.println("Reservation Successful. Your PNR: " + this.getPnr());
		}
			
		else {
			throw new InvalidInputException("Enter the Valid Login Id and Password.");
		}
	}
	
	public static void cancelTicket(String pnr, ArrayList<OnlineReservationBooking> reservations,Scanner scanner) {
		boolean found=false;
		Iterator<OnlineReservationBooking> itr=reservations.iterator();
		while(itr.hasNext()) {
			OnlineReservationBooking orb=itr.next();
			if(orb.getPnr().equals(pnr)) {
				System.out.println("PNR: " + orb.getPnr());
				System.out.println("Name: " + orb.getName());
				System.out.println("Phone: " + orb.getPhoneNumber());
				System.out.println("Age: " + orb.getAge());
				System.out.println("Class Type: " + orb.getClass_type());
				System.out.println("From: " + orb.getSourcePlace());
				System.out.println("To: " + orb.getDestinationPlace());
				System.out.println("Date of Journey: " + orb.getLd());
				System.out.println("You want to Cancel the Ticket(yes/no): ");
				String input=scanner.next();
				if(input.equalsIgnoreCase("yes")) {
					itr.remove();
					System.out.println("Ticket Cancelled Successfully.");
				}
				found=true;
				break;
			}
		}
		if(!found) {
			System.out.println("No Ticket Found With PNR Number.");
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<OnlineReservationBooking> reservations=new ArrayList<OnlineReservationBooking>();
		System.out.println("Welcome to Reservation Booking..!");
		System.out.println("Train Number: "+train_number+" Train Name: "+train_name);
		while(true) {
			System.out.println("\n1.Book Ticket\n2.Cancel Ticket\n3.Exit");
			System.out.println("Please Enter Your Choice: ");
            int choice=scanner.nextInt();
            switch(choice) {
            case 1:
            	OnlineReservationBooking booking=new OnlineReservationBooking();
            	User loginUser=new User();
            	System.out.println("Enter the User id: ");
            	loginUser.setUserId(scanner.next());
            	System.out.println("Enter the User PassWord: ");
            	loginUser.setPassword(scanner.next());
            	try {
            		booking.login(loginUser.getUserId(),loginUser.getPassword(),scanner);
            		reservations.add(booking);
            	}
            	catch(InvalidInputException e){
            		System.out.println(e.getMessage());
            	}
            	break;
            case 2:
            	System.out.println("Enter PNR number to cancel.");
            	String pnr=scanner.next();
            	OnlineReservationBooking.cancelTicket(pnr, reservations,scanner);
            	System.out.println("Your Booking Successfully Cancelled.");
            	break;
            case 3:
            	System.out.println("Thank you for Using the System.");
            	return;
            default:
            	System.out.println("Invalid Choice.");
            }
		}
	}
}
