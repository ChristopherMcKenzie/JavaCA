package Daos;

import Dtos.BookLoaned;
import Dtos.BookStock;
import Dtos.Users;
import Daos.BookStockDao;
import Daos.UsersDao;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Aleksander Matraszek
 */
public class BookLoanedDao extends Dao implements BookLoanedDaoInterface {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BookStock bk = null;
        Users usr = null;
        BookStockDao bksDao = new BookStockDao("libraryca");
        UsersDao uDao = new UsersDao("libraryca");
    public BookLoanedDao(String databaseName) {
        super(databaseName);
    }
    //expermenting
    @Override
    public ArrayList<BookLoaned>getAllInfoOnLoan(){
        
        ArrayList<BookLoaned> bksLoaned = new ArrayList();
        
        try {
            con = getConnection();

            String query = "Select * from bookLoaned";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            
            
            while (rs.next()) {
                String title = rs.getString("bookName");
                String uname = rs.getString("userID");
                bk = bksDao.getABookByName(title);
                usr = uDao.getUserbyName(uname);
                
                BookLoaned bl = new BookLoaned(rs.getInt("loanID"), rs.getInt("bookID"), rs.getInt("userID"));
                bksLoaned.add(bl);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the selectCustomersByName() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllBooksLoaned() method: " + e.getMessage());
            }
        }
        return bksLoaned;
    }

    @Override
    public ArrayList<BookLoaned> getAllBooksOnLoan(BookStock book) {
        
        ArrayList<BookLoaned> bksLoaned = new ArrayList();
        
        int num = book.getBookID();
       
        try {
            con = getConnection();

            String query = "Select * from bookLoaned where bookID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, num);
            rs = ps.executeQuery();

            while (rs.next()) {
                String title = rs.getString("bookName");
                String uname = rs.getString("userID");
                bk = bksDao.getABookByName(title);
                usr = uDao.getUserbyName(uname);
                
                BookLoaned bl = new BookLoaned(rs.getInt("loanID"), bk, usr);
                bksLoaned.add(bl);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the selectCustomersByName() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllBooksLoaned() method: " + e.getMessage());
            }
        }
        return bksLoaned;
    }

    @Override
    public boolean BorrowABook(BookStock book, int userID) {
        
        int rowsAffected = 0;
        
        try {
            con = getConnection();
            String query = "Insert into bookLoaned (bookID, userID) values ?, ?";
            ps = con.prepareStatement(query);
            ps.setInt(2, book.getBookID());
            ps.setInt(3, userID);
            rowsAffected = ps.executeUpdate();
           
        } catch (SQLException e) {
            System.out.println("Exception occured in the selectCustomersByName() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the BorrowABook() method: " + e.getMessage());
            }
        }
        if(rowsAffected >0 ){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean ReturnBook(int bookID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
