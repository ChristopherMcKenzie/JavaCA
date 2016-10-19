package Daos;

/**
 *
 * @author Chris
 */
import Dtos.BookStock;
import Dtos.BookStockDaoInterface;
import Dtos.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class BookStockDao extends Dao implements BookStockDaoInterface {

    public BookStockDao(String databaseName) {
        super(databaseName);
    }

    @Override
    public ArrayList<BookStock> getAllBooks() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<BookStock> bookStock = new ArrayList();
        
        try
        {
            con = getConnection();
            
            String query = "Select * from bookstock";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next())
            {
                BookStock book = new BookStock(rs.getInt("bookID") , rs.getString("bookName"), rs.getString("author"), rs.getString("publisher"), rs.getInt("copies"));
                bookStock.add(book);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getAllBooks() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllBooks() method: " + e.getMessage());
            }
    }
        return bookStock;
}

    @Override
    public ArrayList<BookStock> getABookByName(String bookName) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<BookStock> bookStock = new ArrayList();
        try
        {
            con = getConnection();
            
            String query = "Select * from bookstock where bookName = ? ";
            ps = con.prepareStatement(query);
            ps.setString(1, bookName);
            rs = ps.executeQuery();
            while(rs.next())
            {
                BookStock book = new BookStock(rs.getInt("bookID") , rs.getString("bookName"), rs.getString("author"), rs.getString("publisher"), rs.getInt("copies"));
                bookStock.add(book);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getABookByName() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getABookByName() method: " + e.getMessage());
            }
    }
        return bookStock;
    }

    @Override
    public ArrayList<BookStock> getABookContainingName(String bookName) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<BookStock> bookStock = new ArrayList();
        try
        {
            con = getConnection();
            
            String query = "Select * from bookStock where bookName LIKE ? ";
            ps = con.prepareStatement(query);
            ps.setString(1,"%" +  bookName + "%");
            rs = ps.executeQuery();
            while(rs.next())
            {
                BookStock book = new BookStock(rs.getInt("bookID") , rs.getString("bookName"), rs.getString("author"), rs.getString("publisher"), rs.getInt("copies"));
                bookStock.add(book);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getABookContainingName() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getABookContainingName() method: " + e.getMessage());
            }
    }
        return bookStock;
    }

    @Override
    public boolean ReturnABook(String bookName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean RemoveABook(int bookID) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        try
        {
            con = getConnection();
            
            String query = "DELETE FROM bookStock where bookID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, bookID);
            rowsAffected = ps.executeUpdate();
            
        }catch (SQLException e) {
            System.out.println("Exception occured in the removeABook() method: " + e.getMessage());
        } finally {
            try {
                
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the removeABook() method: " + e.getMessage());
            }
    }
        if(rowsAffected < 0)
        {
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean AddingABook(BookStock b) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        
        try{
            con = getConnection();

            String query = "Insert into bookStock values(?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, b.getBookID());
            ps.setString(2, b.getBookName());
            ps.setString(3, b.getAuthor());
            ps.setString(4, b.getPublisher());
            ps.setInt(5, b.getCopies());
       
            rowsAffected = ps.executeUpdate(); 
            
        }catch (SQLException e) {
            System.out.println("Exception occured in the AddingABook() method: " + e.getMessage());
            
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the AddingABook() method");
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
    public boolean EditingABook(int bookID) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        
        try{
            con = getConnection();

            String query = "UPDATE bookstock set bookID WHERE bookID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, bookID);
         
            rowsAffected = ps.executeUpdate(); 
            
        }catch (SQLException e) {
            System.out.println("Exception occured in the EditingABook() method: " + e.getMessage());
            
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the EditingABook() method");
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
    public boolean IncreasingCopiesBook(int bookID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean DescresingCopiesBook(int bookId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
