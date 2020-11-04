import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Phone myPhone = new Phone("0 543 378 08 08");

    public static void main(String[] args) {

        boolean quit = false;
        startPhone();
        printActions();
        while (!quit) {
            System.out.println("\n Enter action: (6 to show available actions)");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    System.out.println("\nShutting down...");
                    quit = true;
                    break;

                case 1:
                    myPhone.printContacts();
                    break;

                case 2:
                    addNewContact();
                    break;

                case 3:
                    updateContact();
                    break;

                case 4:
                    removeContact();
                    break;

                case 5:
                    queryContact();
                    break;

                case 6:
                    printActions();
                    break;
            }
        }


    }

    private static void addNewContact() {
        System.out.println("Enter new contact name");
        String name = scanner.nextLine();
        System.out.println("Enter phone number");
        String phoneNumber = scanner.nextLine();
        Contact newContact = Contact.createContact(name, phoneNumber);
        if(myPhone.addNewContact(newContact)) {
            System.out.println("New contact added: name = " + name + ", phone = "+ phoneNumber);
        } else {
            System.out.println("Cannot add, " + name + " already on file");
        }
    }

    private static void updateContact() {
        System.out.println("Enter existing contact name");
        String name = scanner.nextLine();
        Contact existingContactRecord = myPhone.queryContact(name);
        if (existingContactRecord == null) {
            System.out.println("Contact not found");
            return;
        }

        System.out.println("Enter new contact name");
        String newName = scanner.nextLine();

        System.out.println("Enter new contact number");
        String newNumber = scanner.nextLine();
        Contact newContact = Contact.createContact(newName, newNumber);
        if (myPhone.updateContact(existingContactRecord, newContact)) {
            System.out.println("Successfully updated record");
        } else System.out.println("Updating failed");
    }

    private static void removeContact() {
        System.out.println("Enter existing contact name:");
        String name = scanner.nextLine();
        Contact existingContactRecord = myPhone.queryContact(name);
        if (existingContactRecord == null) {
            System.out.println("Contact not found");
            return;
        }
        if (myPhone.removeContact(existingContactRecord)) {
            System.out.println("Successfully deleted");
        } else {
            System.out.println("Error deleting contact");
        }
    }

    private static void queryContact() {
        System.out.println("Enter existing contact name:");
        String name = scanner.nextLine();
        Contact existingContactRecord = myPhone.queryContact(name);
        if (existingContactRecord == null) {
            System.out.println("Contact not found.");
            return;
        }
        System.out.println("Name " + existingContactRecord.getName() + " ,number is " + existingContactRecord.getPhoneNumber());
    }

    private static void startPhone() {
        System.out.println("Phone is starting...");
    }

    public static void printActions() {

        System.out.println("\nAvailable actions:\npress");
        System.out.println("0 - to shutdown\n" +
                "1 - to print contacts\n" +
                "2 - to add a new contact\n" +
                "3 - to update an existing contact\n" +
                "4 - to remove an existing contact\n" +
                "5 - query if an existing contact exist\n" +
                "6 - to to print a list of available actions.\n");
        System.out.println("Choose your action: ");

    }
}
