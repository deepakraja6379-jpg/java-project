package com.java;
import java.util.Scanner;


public class MovieTicket {
	static Scanner sc=new Scanner(System.in);
	
//	false- Availabell & true- booked
	
	static boolean[][] seats = new boolean [10][10];
	
	static  double ticketPrice =150;
	static double totalRating =0;
	static int ratingCount =0;
	
	// show availabel seats
	
	static void showSeats() {
		System.out.println("\n-----Theater Seats-----");
		for (int i=0;i<10; i++) {
			for (int j=0; j<10;j++) {
				
				if(seats[i][j]) {
					System.out.println("[x]");
				}else {
					System.out.println("["+(i+1)+"-"+(j+1)+"]");
				}
			}
			System.out.println();
		}
//		System.out.println("x =Booked");
	}

//	book ticket
	static void bookTicket() {
		showSeats();
		System.out.println("Enter row (1-10)");
		int row =sc.nextInt();
		
		System.out.println("Enter seat(1-10)");
		int seat =sc.nextInt();
		
		if (row < 1 || row > 9|| seat <1 || seat> 10) {
			System.out.println("Invalid seat number");
			return;
		
		}
		if (seats[row-1][seat-1]) {
			System.out.println("seats already Booked");
		}else {
			seats[row -1][seat -1]=true;
			double price =ticketPrice;
			System.out.println("Enter day :");
			String day=sc.next();
			
//			weekend pricing 
			
			if(day.equalsIgnoreCase("saturdat")||day.equalsIgnoreCase("sunday")) {
				
				price +=50;
				
			}
			System.out.println("Enter coupon code :");
			String coupon =sc.next();
			
//			discount coupon
			
			if (coupon.equalsIgnoreCase("save")) {
				price=price -(price *0.10);
				
				System.out.println("10% discount applied");
			}
			System.out.println("Ticket Booked Successfully");
			System.out.println("seat :"+row +"-"+seat);
			System.out.println("ticket price: RS."+ price);
		}
		
	}
//	cancel ticket
	static void cancelTicket() {
		System.out.println("Enter row number:");
		int row=sc.nextInt();
		System.out.println("Enter seat number: ");
		int seat =sc.nextInt();
		
		if(row < 1 || row > 10 || seat < 1 || seat > 10) {
			System.out.println("Invalid seat Number ");
			return;
		}
		
		if(seats[row -1][seat-1]) {
			seats[row-1][seat-1]=false;
			System.out.println("Ticket cnacelled Successfully");
		}
		else {
			System.out.println("seat is not Booked");
		}
	}
//	movie rating
	
	static void movieRating(){
		System.out.println("Enter movie rating (1-5):");
		
		int rating =sc.nextInt();
		if(rating >=1&& rating <=5) {
			totalRating += rating;
			ratingCount++;
			
			System.out.println("rating submited successfully");
			System.out.println("average rating:" +(totalRating/ratingCount));
		}else {
			System.out.println("please enter rating Between 1 and 5");
		}
	}
//	main menu
	public static void main(String[] args) {
		int choice;
		do {
			System.out.println("\n=====Movie Ticket Booking=====");
			System.out.println("1. Show Availabel Seat");
			System.out.println("2. Book TIcket");
			System.out.println("3. cancel Ticket");
			System.out.println("4. Movie rating");
			System.out.println("5. Exit");
			
			System.out.println("Enter your Choice");
			
			choice=sc.nextInt();
			 
			switch(choice) {
			case 1:
			showSeats();
			break;
			
			case 2:
				bookTicket();
				break;
			case 3:
				cancelTicket();
				break;
			case 4:
				movieRating();
				break;
			case 5:
				System.out.println("thank you for using our System");
				break;
				
				default:
					System.out.println("Invalid choice");
			}
			
		}while(choice !=10);
		sc.close();
	}
}
