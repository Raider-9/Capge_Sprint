package contactmanagementsystem;

import customexceptions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactManager {
    private List<Contact> contacts;
    private Scanner scanner;
    
    public ContactManager() {
        contacts = new ArrayList<>();
        scanner = new Scanner(System.in);
       
    }
    
    public void addContact(Contact contact) throws DuplicateContactException {
        // Checking for duplicate ID
        if (contacts.stream().anyMatch(c -> c.getId() == contact.getId())) {
            throw new DuplicateContactException("Contact with ID " + contact.getId() + " already exists");
        }
        
        // Checking for duplicate phone number
        if (contacts.stream().anyMatch(c -> c.getPhoneNumber() == contact.getPhoneNumber())) {
            throw new DuplicateContactException("Phone number " + contact.getPhoneNumber() + " already exists");
        }
        
        // Checking for duplicate email
        if (contacts.stream().anyMatch(c -> c.getEmail().equalsIgnoreCase(contact.getEmail()))) {
            throw new DuplicateContactException("Email " + contact.getEmail() + " already exists");
        }
        
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
    
    public void searchByNumber(int number) {
        System.out.println("\nSearch Results for '" + number + "':");
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber() == number) {
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
    
    public void updateContact(int id) throws InvalidInputException, DuplicateContactException {
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
            validateName(newName);
            contactToUpdate.setName(newName);
        }
        
        System.out.print("Enter new phone number (leave blank to keep current): ");
        String phoneInput = scanner.nextLine();
        if (!phoneInput.isEmpty()) {
            int newPhone = validatePhoneNumber(phoneInput);
            
            if (contacts.stream()
                    .filter(c -> c.getId() != id)
                    .anyMatch(c -> c.getPhoneNumber() == newPhone)) {
                throw new DuplicateContactException("Phone number " + newPhone + " already exists");
            }
            
            contactToUpdate.setPhoneNumber(newPhone);
        }
        
        System.out.print("Enter new email (leave blank to keep current): ");
        String emailInput = scanner.nextLine();
        if (!emailInput.isEmpty()) {
            validateEmail(emailInput);
            
            if (contacts.stream()
                    .filter(c -> c.getId() != id)
                    .anyMatch(c -> c.getEmail().equalsIgnoreCase(emailInput))) {
                throw new DuplicateContactException("Email " + emailInput + " already exists");
            }
            
            contactToUpdate.setEmail(emailInput);
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
    
    // Validation methods
    private void validateName(String name) throws NameFormatException {
        if (name.matches(".*\\d.*")) {
            throw new NameFormatException("Name cannot contain numbers");
        }
    }
    
    private int validatePhoneNumber(String phoneInput) throws PhoneNumberFormatException {
        try {
            long phoneLong = Long.parseLong(phoneInput);
            if (phoneInput.length() != 10) {
                throw new PhoneNumberFormatException("Phone number must be exactly 10 digits");
            }
            return (int) phoneLong;
        } catch (NumberFormatException e) {
            throw new PhoneNumberFormatException("Phone number must contain only digits");
        }
    }
    
    private void validateEmail(String email) throws EmailFormatException {
        if (email == null || email.isEmpty()) {
            throw new EmailFormatException("Email cannot be empty");
        }
        
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new EmailFormatException("Invalid email format");
        }
    }
    
    //  methods for user input
    public String getValidName() {
        while (true) {
            try {
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                validateName(name);
                return name;
            } catch (NameFormatException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
    }
    
    public int getValidPhoneNumber() {
        while (true) {
            try {
                System.out.print("Enter phone number: ");
                String phoneInput = scanner.nextLine();
                return validatePhoneNumber(phoneInput);
            } catch (PhoneNumberFormatException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
    }
    
    public String getValidEmail() throws DuplicateContactException {
        while (true) {
            try {
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                validateEmail(email);
                
                if (contacts.stream().anyMatch(c -> c.getEmail().equalsIgnoreCase(email))) {
                    throw new DuplicateContactException("Email " + email + " already exists");
                }
                
                return email;
            } catch (EmailFormatException | DuplicateContactException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
    }
}