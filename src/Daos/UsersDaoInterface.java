
package Daos;

/**
 *
 * Ben And Chris
 * @author Ben
 */
import Dtos.Users;
import java.util.ArrayList;

public interface UsersDaoInterface {
    public ArrayList<Users>getAllUsers();
    //return everyone in the database;
    public ArrayList<Users>getAllStandardUsers();
     //return all Standard users
    public ArrayList<Users>getAllAdminUsers();
      //return all admin users
    public boolean RegistorUser(Users u);
    //registering a user
    public Users LogingInUser(String name, String password);
    //logging in a user
    public Users getUserbyName(String name);
    //get user by name
    public ArrayList<Users>getUserContaingName(String name);
    //get user by name wildcard
    public boolean RemoveUser(int userID);
    //Remove a User (Note: An admin cannot delete another admin from the library).
}
