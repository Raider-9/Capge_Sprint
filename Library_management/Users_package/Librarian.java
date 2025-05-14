//package Users_package;
public class Librarian extends User{
    private boolean onDuty = true;

    //This is the constructor fo the class that sends the name and id to the super class which is the abstract 
    //user class
    public Librarian(String id, String name) {
        super(id, name);
    }

    //Function to get whether the librarian is on duty ? 
    boolean isOnDuty() {
        return onDuty;
    }

    //Funciton to set the librarian on or off duty.
    void setonDuty(boolean onDuty) {
        this.onDuty = onDuty;
    }

    //This is the function that shows the profile of the librarian by overriding the abstract fucntion 
    //in abstract class user. 
    @Override
    void showProfile() {
        System.out.println("-------------------------");
        System.out.println("Librarian ID: " + id);
        System.out.println("Librarian Name: " + name);
        System.out.println("On Duty: " + (onDuty ? "Yes" : "No"));
        System.out.println("-------------------------");
    }
}
