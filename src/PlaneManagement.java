import java.util.InputMismatchException;
import java.util.Scanner;

public class PlaneManagement {

    // Creating a array for tickets
    static Ticket[] tickets = new Ticket[52];

    // Counter for the number of tickets
    static int ticketCounter = 0;

    //Price for the ticket
    static int price;

    //Total Price
    static int TotalPrice;

    //seat as a static
    private static int[][] FlightSeat;
    static {
        FlightSeat = new int[4][];
        FlightSeat[0] = new int[14];
        FlightSeat[1] = new int[12];
        FlightSeat[2] = new int[12];
        FlightSeat[3] = new int[14];
    }

    // Main Method .....

    public static void main(String[] args) {

        System.out.println();
        System.out.println("Welcome to the Plane Management Application");
        System.out.println();

        // getting user input
        Scanner input = new Scanner(System.in);

        int option;

        boolean check_input = false;
        while (!check_input) {
            try{
                do{

                    Choice_list();

                    System.out.print("Please select an option: ");

                    option = input.nextInt();
                    input.nextLine();

                    // Using the Switch option to identify the input and call the function

                    switch (option) {
                        case 1:
                            Buy_seat(input);
                            break;

                        case 2:
                            Cancel_seat(input);
                            break;

                        case 3:
                            Find_first_availabe_seat();
                            break;

                        case 4:
                            Show_seating_plan();
                            break;

                        case 5:
                            Print_tickets_infomation_and_total_sales();
                            break;

                        case 6:
                            Search_ticket(input);
                            break;

                        case 0:
                            System.out.println("Thanks for using Plane Management Application!");
                            System.out.println("Exiting the program...");
                            System.out.println();
                            break;
                        default:
                            System.out.println("Invalid option!!, Try again...");
                    }


                }while (option != 0);

                check_input = true;
                // Closing the scanner
                input.close();

            } catch (InputMismatchException e){
                System.out.println("Invalid option!!, Try again...");
                input.nextLine();

            }

        }
    }



    public static void Choice_list() {
        // Printing the choice list

        System.out.println("**************************************************");
        System.out.println("*                  MENU OPTIONS                  *");
        System.out.println("**************************************************");
        System.out.println("1) Buy a seat");
        System.out.println("2) Cancel a seat");
        System.out.println("3) Find first available seat");
        System.out.println("4) Show seating plan");
        System.out.println("5) Print tickets information and total sales");
        System.out.println("6) Search ticket");
        System.out.println("0) Quit");
        System.out.println("**************************************************");

    }

    //<<<<<<<<<< Method for delete ticket after the cancellation >>>>>>>>>
    public static void Del_ticket(char row, int column){
        for (int i = 0; i < ticketCounter; i++){
            if (tickets[i].getRow() == row && tickets[i].getSeat()== column + 1) {
                tickets[i] = null;
                for (int j = i; j < ticketCounter - 1; j++){
                    tickets[j] = tickets[j + 1];
                }
                ticketCounter--;



            }
        }




    }

    // Option 1: Buy a seat

    public static void Buy_seat(Scanner input){

        char row;
        int column;

        boolean ok = false;
        while (!ok) {
            try {

                //Get row input
                System.out.print("Enter the row (A-D): ");
                row = input.next().toUpperCase().charAt(0);
                input.nextLine();

                //check the validity of the row
                if (row < 'A' || row > 'D') {
                    System.out.println("Invalid row, try again...");
                    continue;
                }

                //Get column input
                System.out.print("Enter the column: ");
                column = input.nextInt()-1;
                input.nextLine();

                int seatRow = row - 'A';

                //check the validity of the column based on the row
                if ((seatRow == 1 || seatRow == 2) && (column < 0 || column >= 12)) {
                    System.out.println("Invalid seat choosen, try again...");
                    continue;
                }

                if (seatRow >= 0 && seatRow < FlightSeat.length && column >= 0 && column < FlightSeat[seatRow].length) {
                    //check if the seat is already taken
                    if (FlightSeat[seatRow][column] == 1) {
                        System.out.println("Seat already taken, try again...");
                    } else {
                        FlightSeat[seatRow][column] = 1;

                        System.out.print("Enter your First Name: ");
                        String name = input.nextLine();
                        System.out.print("Enter your Last Name: ");
                        String surname = input.nextLine();
                        System.out.print("Enter your Email: ");
                        String email = input.nextLine();

                        //Person object
                        Person person = new Person(name, surname, email);

                        //Price prediction
                        if (column >= 0 && column <= 5) {
                            price = 200;
                        } else if(column >= 5 && column <= 8){
                            price = 150;
                        } else {
                            price = 180;
                        }

                        //Ticket object
                        Ticket ticket = new Ticket(row, column + 1, price, person);

                        //Adding the ticket to the array
                        tickets[ticketCounter++] = ticket;

                        TotalPrice += price;

                        //Saving ticket as text..
                        System.out.println("Ticket booking success!!!");
                        ticket.saveTicket();
                        ok = true;
                    }
                } else {
                    System.out.println("Invalid seat choosen, try again...");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, try again...");
                input.nextLine();
            }
        }
    }


    // Option 2: Cancel a seat

    public static void Cancel_seat(Scanner input){

        char row;
        int column;

        boolean ok = false;
        while(!ok){
            try{

                //user input for the row
                System.out.print("Enter the row (A-D): ");
                row = input.next().charAt(0);
                input.nextLine();

                // check the validity of the row
                if (row < 'A' || row > 'D') {
                    System.out.println("Invalid row, try again...");
                    continue;
                }

                // user input for the column
                System.out.print("Enter the column: ");
                column = input.nextInt()-1;
                input.nextLine();

                int seatRow = row - 'A';

                //check the validation of the seat based on the row
                if ((seatRow == 1 || seatRow == 2) && (column < 0 || column >= 12)) {
                    System.out.println("Invalid seat choosen, try again...");
                    continue;
                }

                //check the validation of the seat
                if (seatRow < 0 || seatRow >= FlightSeat.length || column < 0 || column >= FlightSeat[0].length) {
                    System.out.println("Invalid seat choosen, try again...");
                    continue;
                }

                // check already taken
                if (FlightSeat[seatRow][column] != 1) {
                    System.out.println("Seat is still available");
                    break;
                } else {
                    FlightSeat[seatRow][column] = -1;
                    System.out.println("Seat cancelled.......");

                    // total price reduction and the txt file removing
                    TotalPrice -= price;

                    Ticket.deleteTicket(row,(column + 1));

                    //removing the ticket from the array using written method
                    Del_ticket(row, column);

                    ok = true;
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid input, try again...");
                input.nextLine();
            }
        }

    }

    // Option 3: Find first available seat

    public static void Find_first_availabe_seat(){
        for (int i = 0; i < FlightSeat.length; i++){
            for (int j = 0; j < FlightSeat[i].length; j++){
                if (FlightSeat[i][j] == 0){
                    System.out.println("First available seat is: " + (char)(i + 'A') + (j + 1));
                    return;
                }
            }
        }
    }

    // Option 4: Show seating plan

    public static void Show_seating_plan(){
        System.out.println("Seating Plan: ");
        for (int i = 0; i < FlightSeat.length; i++){
            for (int j = 0; j < FlightSeat[i].length; j++){
                if (FlightSeat[i][j] == 1){
                    System.out.print("X ");
                } else {
                    System.out.print("O ");
                }
            }
            System.out.println();
        }
    }

    // Option 5: Print tickets information and total sales

    public static void Print_tickets_infomation_and_total_sales(){
        for (int i = 0; i < ticketCounter; i++){
            System.out.println("Number" + (i + 1) + ">");
            tickets[i].printInfo();
            System.out.println();
        }
        System.out.println("Total sales: " + TotalPrice);
    }

    // Option 6: Search ticket

    public static void Search_ticket(Scanner input){
        boolean ok = false;

        while(!ok){
            try {

                //get row letter
                System.out.print("Enter the row (A-D): ");
                char row = input.next().charAt(0);
                input.nextLine();

                //checking the validity of the row
                if (row < 'A' || row > 'D') {
                    System.out.println("Invalid row, try again...");
                    continue;
                }

                //get column number
                System.out.print("Enter the column: ");
                int column = input.nextInt();
                input.nextLine();

                int seatRow = row - 'A';


                //checking the validity of the column
                if (seatRow < 0 || seatRow >= FlightSeat.length || seatRow < 0 || seatRow >= FlightSeat[0].length) {
                    System.out.println("Invalid seat choosen, try again...");
                    continue;
                }

                //checking if the seat is already taken

                for (int i = 0; i < ticketCounter; i++){
                    if (tickets[i] != null && tickets[i].getRow() == row && tickets[i].getSeat() == column){
                        System.out.println();
                        System.out.println("Ticket found:");
                        tickets[i].printInfo();
                        ok = true;
                        break;
                    } else {
                        System.out.println("Already available");
                        ok = true;
                    }
                }


            } catch (InputMismatchException e){
                System.out.println("Invalid input, try again...");
                input.nextLine();
            }
        }

    }
} 