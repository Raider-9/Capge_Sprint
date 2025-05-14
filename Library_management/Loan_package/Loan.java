//package Loan_package;
import book_package.*;
//import Users_package.Member;
public class Loan {
    Member member;
    Book book;
    String status;

    public Loan(Member member, Book book) {
        this.member = member;
        this.book = book;
        this.status = "active"; // By default, a new loan is active
    }

    public void completeLoan() {
        if (book.isAvailable()) {
            book.setAvailable(false);
            status = "completed";
            System.out.println("Loan completed for book: " + book.getTitle());
        } else {
            System.out.println("Book is not available for loan.");
        }
    }
    
}
