package contactmanagementsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactManager {
    private List<Contact> contacts;
    private Scanner scanner;
    
    public ContactManager() {
        contacts = new ArrayList<>();
        scanner = new Scanner(System.in);
        // Initialize with some sample contacts
        contacts.add(new Contact(1, "John Doe", "1234567890", "john@example.com"));
        contacts.add(new Contact(2, "Jane Smith", "0987654321", "jane@example.com"));
    }
    
    public void addContact(Contact contact) {
        contacts.add(contact);
    }
    
    public void searchByName(String name) {
        System.out.println("\nSearch Results for '" + name + "':");
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(contact);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No contacts found with that name.");
        }
    }
    
    public void searchByNumber(String number) {
        System.out.println("\nSearch Results for '" + number + "':");
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().contains(number)) {
                System.out.println(contact);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No contacts found with that phone number.");
        }
    }
    
    public void deleteById(int id) {
        boolean removed = contacts.removeIf(contact -> contact.getId() == id);
        if (removed) {
            System.out.println("Contact with ID " + id + " deleted successfully.");
        } else {
            System.out.println("No contact found with ID " + id);
        }
    }
    
    public void updateContact(int id) {
        Contact contactToUpdate = null;
        for (Contact contact : contacts) {
            if (contact.getId() == id) {
                contactToUpdate = contact;
                break;
            }
        }
        
        if (contactToUpdate == null) {
            System.out.println("No contact found with ID " + id);
            return;
        }
        
        System.out.println("Current contact details:");
        System.out.println(contactToUpdate);
        
        System.out.print("Enter new name (leave blank to keep current): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            contactToUpdate.setName(newName);
        }
        
        System.out.print("Enter new phone number (leave blank to keep current): ");
        String newPhone = scanner.nextLine();
        if (!newPhone.isEmpty()) {
            contactToUpdate.setPhoneNumber(newPhone);
        }
        
        System.out.print("Enter new email (leave blank to keep current): ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isEmpty()) {
            contactToUpdate.setEmail(newEmail);
        }
        
        System.out.println("Contact updated successfully: " + contactToUpdate);
    }
    
    public void displayAllContacts() {
        System.out.println("\nAll Contacts:");
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
        } else {
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }
    
    public void closeScanner() {
        scanner.close();
    }
}
