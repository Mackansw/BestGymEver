package mackansw.nackademin;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BestGymEver {

    private Path customersFile = Paths.get("customers.txt");
    private Path visitsFile = Paths.get("visits.txt");
    private List <Customer> customers = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    private LocalDate lastYear = LocalDate.now().minusYears(1);

    private String now = String.valueOf(LocalDate.now());

    private String socialNumb = "";
    private String name = "";
    private String lastVist = "";

    public String takeInput() {
        System.out.println("Ange för och efternamn eller personnummer:");
        return input.nextLine();
    }

    private void createList() {
        try(Scanner reader = new Scanner(customersFile)) {
            while(reader.hasNext()) {
                socialNumb = reader.next().replace(",", "");
                name = reader.nextLine().substring(1);
                lastVist = reader.nextLine();
                customers.add(new Customer(socialNumb, name, lastVist));
            }
        }
        catch (IOException e) {
            System.out.println("Fel! kunde ej läsa data från kundfil!");
        }
    }

    public boolean checkForFile(Path path, boolean makeDir) {
        if(!Files.exists(path)) {
            if(makeDir) {
                try {
                    Files.createFile(path);
                }
                catch (IOException e) {
                    System.out.println("Fel! Filen finns redan!");
                }
            }
            return false;
        }
        else {
            return true;
        }
    }

    private void logCustomer(Customer customer) {
        checkForFile(visitsFile, true);
        try(PrintWriter writer = new PrintWriter(new FileWriter(visitsFile.toFile(), true))) {
            writer.println("[" + now + "]" + " " + customer.getName() + " | " + customer.getSocialNumber());
        }
        catch(FileNotFoundException e) {
            System.out.println("Fel! Kunde ej hitta logfilen!");
        }
        catch (IOException e) {
            System.out.println("Fel! kunde ej skriva till logfilen!");
        }
    }

    public boolean matchToList(String input, List <Customer> source) {
        boolean match = false;

        for (Customer customer : source) {
            if(customer.getSocialNumber().equals(input) || customer.getName().equals(input)) {
                LocalDate lastPaied = LocalDate.parse(customer.getLastVisit());

                if(lastPaied.isAfter(lastYear)) {
                    match = true;
                    System.out.println("Nuvarande medlem! (Betalade senast: " + lastPaied + ")");
                    logCustomer(customer);
                }
                else if(lastPaied.isBefore(lastYear)) {
                    match = true;
                    System.out.println("Före detta medlem! (Betalade senast: " + lastPaied + ")");
                }
            }
        }
        if(!match) {
            System.out.println("Obehörig!");
        }
        return match;
    }

    public static void main(String args[]) {
        BestGymEver gym = new BestGymEver();
        gym.createList();
        gym.matchToList(gym.takeInput(), gym.customers);
    }
}