
package Daos;

/**
 *
 * @author Ben
 */
import Dtos.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDao extends Dao implements UsersDaoInterface{

    public UsersDao(String databaseName) {
        super(databaseName);
    }

    @Override
    public ArrayList<Users> getAllUsers() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Users> users = new ArrayList();
        
        try{
            con = getConnection();

            String query = "Select * from users";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(); 
            
            while(rs.next())
            {
                Users u = new Users(rs.getString("userName"), rs.getString("password"), rs.getString("Fname"), rs.getString("Lname"), rs.getString("Address"), rs.getString("email"),  rs.getInt("booksLoaned"), rs.getInt("Admin"));
                users.add(u);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getAllUsers() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getAllUsers() method: " + e.getMessage());
            }
        }
        
        return users;
    }

    @Override
    public ArrayList<Users> getAllStandardUsers() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Users> users = new ArrayList();
        
        try{
            con = getConnection();

            String query = "Select * from users where Admin = false";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(); 
            
            while(rs.next())
            {
                Users u = new Users(rs.getString("userName"), rs.getString("password"), rs.getString("Fname"), rs.getString("Lname"), rs.getString("Address"), rs.getString("email"),  rs.getInt("booksLoaned"), rs.getInt("Admin"));
                users.add(u);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getAllStandardUsers() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getAllStandardUsers() method: " + e.getMessage());
            }
        }
        
        return users;
    }

    @Override
    public ArrayList<Users> getAllAdminUsers() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Users> users = new ArrayList();
        
        try{
            con = getConnection();

            String query = "Select * from users Where Admin = true";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(); 
            
            while(rs.next())
            {
                Users u = new Users(rs.getString("userName"), rs.getString("password"), rs.getString("Fname"), rs.getString("Lname"), rs.getString("Address"), rs.getString("email"), rs.getInt("booksLoaned"), rs.getInt("Admin"));
                users.add(u);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getAllAdminUsers() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getAllAdminUsers() method: " + e.getMessage());
            }
        }
        
        return users;
    }

    @Override
    public boolean RegistorUser(Users u) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        
        try{
            con = getConnection();

            String query = "Insert into users values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, u.getUser_id());
            ps.setString(2, u.getUsername());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getFname());
            ps.setString(5, u.getLname());
            ps.setString(6, u.getAddress());
            ps.setString(7, u.getEmail());
            ps.setInt(8, u.getBooksLoaned());
            ps.setInt(9, 0);
            

                       
            rowsAffected = ps.executeUpdate(); 
            
        }catch (SQLException e) {
            System.out.println("Exception occured in the RegistorUser() method: " + e.getMessage());
            
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the RegistorUser() method");
                e.getMessage();
                
            }
        }
        if(rowsAffected > 0)
        {
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean LogingInUser(String name, String password) {
       Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        
        try{
            con = getConnection();

            String query = "UPDATE users set LoggedIn = 1 Where username = ? AND password = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, password);
           
                     
            rowsAffected = ps.executeUpdate(); 
            
        }catch (SQLException e) {
            System.out.println("Exception occured in the RemoveUser() method: " + e.getMessage());
            
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the RemoveUser() method");
                e.getMessage();
                
            }
        }
        if(rowsAffected > 0)
        {
            return true;
        }
        else
            return false;
    }

    @Override
    public ArrayList<Users> getUserbyName(String name) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Users> users = new ArrayList();
        
        try{
            con = getConnection();

            String query = "Select * from users Where username = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            rs = ps.executeQuery(); 
            
            while(rs.next())
            {
                Users u = new Users(rs.getString("userName"), rs.getString("password"), rs.getString("Fname"), rs.getString("Lname"), rs.getString("Address"), rs.getString("email"), rs.getInt("booksLoaned"), rs.getInt("Admin"));
                users.add(u);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getUserbyName() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getUserbyName() method: " + e.getMessage());
            }
        }
        
        return users;
    }

    @Override
    public ArrayList<Users> getUserContaingName(String name) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Users> users = new ArrayList();
        
        try{
            con = getConnection();

            String query = "Select * from users Where username = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery(); 
            
            while(rs.next())
            {
                Users u = new Users(rs.getString("userName"), rs.getString("password"), rs.getString("Fname"), rs.getString("Lname"), rs.getString("Address"), rs.getString("email"), rs.getInt("booksLoaned"), rs.getInt("Admin"));
                users.add(u);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getUserContaingName() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getUserContaingName() method: " + e.getMessage());
            }
        }
        
        return users;
    }

    @Override
    public boolean RemoveUser(int userID) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        
        try{
            con = getConnection();

            String query = "DELETE FROM users Where userID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, userID);
            
            

                       
            rowsAffected = ps.executeUpdate(); 
            
        }catch (SQLException e) {
            System.out.println("Exception occured in the RemoveUser() method: " + e.getMessage());
            
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the RemoveUser() method");
                e.getMessage();
                
            }
        }
        if(rowsAffected > 0)
        {
            return true;
        }
        else
            return false;
    }

    
}
