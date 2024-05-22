import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ticket {

    public static int length;
    private char row;
    private int seat;
    private double price;
    private Person person;

    public Ticket(char row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    // Getters

    public char getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    public Person getPerson() {
        return person;
    }

    // Setters

    public void setRow(char row) {
        this.row = row;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    //Method to print ticket's information

    public void printInfo() {
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: " + price);
        System.out.println("First Name: " + person.getName());
        System.out.println("Last Name: " + person.getSurname());
        System.out.println("Email: " + person.getEmail());
    }

    //Method to save ticket's information to a file

    public void saveTicket() {
        String fileName =  row + "" + seat + ".txt";

        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("Ticket Information\n");
            writer.write("******************\n");
            writer.write(" \n");
            writer.write("Row: " + row + "\n");
            writer.write("Seat: " + seat + "\n");
            writer.write("Price: " + price + "\n");
            writer.write("First Name: " + person.getName() + "\n");
            writer.write("Last Name: " + person.getSurname() + "\n");
            writer.write("Email: " + person.getEmail() + "\n");
            writer.close();
            System.out.println(" ");
            System.out.println("Ticket saved");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //Method to delete the ticket

    public static void deleteTicket(char row, int column) {
        File file = new File(row + Integer.toString(column) + ".txt");

        if (file.exists()) {
            boolean deleted_txt = file.delete();
            if (deleted_txt) {
                System.out.println("Ticket " + file + " deleted");
            } else {
                System.out.println("Failed to delete the ticket");
            }
        } else {
            System.out.println("Ticket does not exist");
        }
    }

}

