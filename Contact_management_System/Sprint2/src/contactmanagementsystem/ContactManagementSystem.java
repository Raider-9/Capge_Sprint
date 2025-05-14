package contactmanagementsystem;

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
            
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            
            switch (choice) {
                case 1:
                    System.out.print("Enter contact ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    manager.addContact(new Contact(id, name, phone, email));
                    System.out.println("Contact added successfully.");
                    break;
                    
                case 2:
                    System.out.print("Enter name to search: ");
                    String searchName = scanner.nextLine();
                    manager.searchByName(searchName);
                    break;
                    
                case 3:
                    System.out.print("Enter phone number to search: ");
                    String searchPhone = scanner.nextLine();
                    manager.searchByNumber(searchPhone);
                    break;
                    
                case 4:
                    System.out.print("Enter contact ID to delete: ");
                    int deleteId = Integer.parseInt(scanner.nextLine());
                    manager.deleteById(deleteId);
                    break;
                    
                case 5:
                    System.out.print("Enter contact ID to update: ");
                    int updateId = Integer.parseInt(scanner.nextLine());
                    manager.updateContact(updateId);
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
        }
        
        scanner.close();
        manager.closeScanner();
    }
}
