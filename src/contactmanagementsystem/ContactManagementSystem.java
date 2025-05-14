package contactmanagementsystem;

import customexceptions.*;
import java.util.Scanner;

public class ContactManagementSystem {
    public static void main(String[] args) {
        ContactManager manager = new ContactManager();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Contact Management System ===");
        
        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Add a new contact");
            System.out.println("2. Search contacts by name");
            System.out.println("3. Search contacts by phone number");
            System.out.println("4. Delete a contact by ID");
            System.out.println("5. Update a contact");
            System.out.println("6. Display all contacts");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        try {
                            System.out.print("Enter contact ID: ");
                            int id = Integer.parseInt(scanner.nextLine());
                            String name = manager.getValidName();
                            int phone = manager.getValidPhoneNumber();
                            String email = manager.getValidEmail();
                            
                            manager.addContact(new Contact(id, name, phone, email));
                            System.out.println("Contact added successfully.");
                        } catch (NumberFormatException e) {
                            System.out.println("Error: ID must be a number");
                        } catch (InvalidInputException e) {
                            System.out.println("Failed to add contact: " + e.getMessage());
                        }
                        break;
                        
                    case 2:
                        System.out.print("Enter name to search: ");
                        String searchName = scanner.nextLine();
                        manager.searchByName(searchName);
                        break;
                        
                    case 3:
                        try {
                            System.out.print("Enter phone number to search: ");
                            String searchPhoneInput = scanner.nextLine();
                            int searchPhone = Integer.parseInt(searchPhoneInput);
                            manager.searchByNumber(searchPhone);
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Phone number must contain only digits");
                        }
                        break;
                        
                    case 4:
                        try {
                            System.out.print("Enter contact ID to delete: ");
                            int deleteId = Integer.parseInt(scanner.nextLine());
                            manager.deleteById(deleteId);
                        } catch (NumberFormatException e) {
                            System.out.println("Error: ID must be a number");
                        }
                        break;
                        
                    case 5:
                        try {
                            System.out.print("Enter contact ID to update: ");
                            int updateId = Integer.parseInt(scanner.nextLine());
                            manager.updateContact(updateId);
                        } catch (NumberFormatException e) {
                            System.out.println("Error: ID must be a number");
                        } catch (InvalidInputException e) {
                            System.out.println("Failed to update contact: " + e.getMessage());
                        }
                        break;
                        
                    case 6:
                        manager.displayAllContacts();
                        break;
                        
                    case 7:
                        running = false;
                        System.out.println("Exiting Contact Management System. Goodbye!");
                        break;
                        
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number for menu choice");
            }
        }
        
        scanner.close();
        manager.closeScanner();
    }
}