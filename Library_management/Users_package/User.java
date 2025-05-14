//package Library_management.Users_package;
public abstract class User {
//The abstract parent class User holding the overall structure and strcuture of the User

//Common attributes of the User class
 protected String name;
 protected String id; 
 
//constructor of the User class
 public User(String id, String name) {
     this.name = name;
     this.id = id;
 }

//abstract method to show the profile of the user 
 abstract void showProfile();
}
