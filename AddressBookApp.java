package Solution;
import java.io.*;
import java.util.*;
import java.util.function.Predicate;

class Contact {
    private String name;
    private String phoneNumber;
    private String emailAddress;
    // Add other relevant details as needed

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    // Getters and setters (omitted for brevity)

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + emailAddress;
    }

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPhoneNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEmailAddress() {
		// TODO Auto-generated method stub
		return null;
	}
}

class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(final String name) {
        contacts.removeIf(new Predicate<Contact>() {
			@Override
			public boolean test(Contact contact) {
				return contact.getName().equalsIgnoreCase(name);
			}
		});
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public void displayAllContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    public void saveContactsToFile(String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Contact contact : contacts) {
                writer.println(contact.getName() + "," + contact.getPhoneNumber() + "," + contact.getEmailAddress());
            }
            System.out.println("Contacts saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadContactsFromFile(String filePath) {
        contacts.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Contact contact = new Contact(parts[0], parts[1], parts[2]);
                    contacts.add(contact);
                }
            }
            System.out.println("Contacts loaded from file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class AddressBookApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();

        while (true) {
            System.out.println("Address Book System");
            System.out.println("1. Add a contact");
            System.out.println("2. Remove a contact");
            System.out.println("3. Search for a contact");
            System.out.println("4. Display all contacts");
            System.out.println("5. Save contacts to file");
            System.out.println("6. Load contacts from file");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after reading the integer input

            switch (choice) {
                case 1:
                    System.out.print("Enter contact name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email address: ");
                    String emailAddress = scanner.nextLine();
                    Contact newContact = new Contact(name, phoneNumber, emailAddress);
                    addressBook.addContact(newContact);
                    System.out.println("Contact added successfully!");
                    break;

                case 2:
                    System.out.print("Enter name of the contact to remove: ");
                    String contactNameToRemove = scanner.nextLine();
                    addressBook.removeContact(contactNameToRemove);
                    System.out.println("Contact removed successfully!");
                    break;

                case 3:
                    System.out.print("Enter name of the contact to search: ");
                    String contactNameToSearch = scanner.nextLine();
                    Contact foundContact = addressBook.searchContact(contactNameToSearch);
                    if (foundContact != null) {
                        System.out.println("Contact found: " + foundContact);
                    } else {
                        System.out.println("Contact not found!");
                    }
                    break;

                case 4:
                    addressBook.displayAllContacts();
                    break;

                case 5:
                    System.out.print("Enter file path to save contacts: ");
                    String saveFilePath = scanner.nextLine();
                    addressBook.saveContactsToFile(saveFilePath);
                    break;

                case 6:
                    System.out.print("Enter file path to load contacts: ");
                    String loadFilePath = scanner.nextLine();
                    addressBook.loadContactsFromFile(loadFilePath);
                    break;

                case 7:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println(); // Empty line for better readability
        }
    }
}
