
package App;

import java.util.*;
import Daos.*;
import Dtos.*;

/**
 *
 * @author Ben,Chris,Aleks
 */
public class AdminTestApp {
    public static void main(String[] args) 
    {
        int switchvar = 0;
        UsersDao uDao = new UsersDao("libaryca");
        BookStockDao bDao = new BookStockDao("libaryca");
        BookLoanedDao lDao = new BookLoanedDao("libaryca");
        ArrayList<Users> users = uDao.getAllUsers();
        ArrayList<BookStock> books = bDao.getAllBooks();

        Scanner input = new Scanner(System.in);
      
        System.out.println("Do you wish to register or login");
        String choice = input.nextLine();
        
        boolean flag = true;
        while(flag == true)
        {
            
            
            if(choice.toLowerCase().equals("register"))
            {
                Users userReg = new Users();
                System.out.println("please enter your username?");
                userReg.setUsername(input.nextLine());
                
                System.out.println("please enter password?");
                userReg.setPassword(input.nextLine());
                
                System.out.println("please enter firstname");
                userReg.setFname(input.nextLine());
                
                System.out.println("please enter lastname");
                userReg.setLname(input.nextLine());
                
                System.out.println("please enter address");
                userReg.setAddress(input.nextLine());
                
                System.out.println("please enter email");
                String emailCheck = input.nextLine();
                userReg.setEmail(emailCheck);
                
                userReg.setBooksLoaned(0);
                userReg.setAdmin(0);
                
                if(emailCheck.contains("@"))
                {
                    uDao.RegistorUser(userReg);
                    
                    flag = false;
                }
                else if(!(emailCheck.contains("@")))
                {
                    System.out.println("There is something missing in your email e.g(@)");
                }
            }
            else if(choice.toLowerCase().equals("login"))
            {
                Users userlog = new Users();
                
                
                System.out.println("please enter usrname?");
                String name = input.nextLine();
                
                System.out.println("please enter password");
                String password = input.nextLine();
                
                userlog.setUsername(name);
                userlog.setPassword(password);
                
                
                if(uDao.LogingInUser(name, password).equals(userlog))
                {
                    int userid = uDao.getUserbyName(name).getUser_id();
                    
                    //we are now logged in
                    System.out.println("You have succesfully logged in");
                    
                    System.out.println("What Do you want to do dislpay all titles, whats on loan, borrow a copy, return, logout");
                    String displayAns = input.nextLine().toLowerCase();
                    
                    /*
                        Typing in an answer will set the value of "switchvar" allowing the user to navigate
                        the app using switch statements
                    */
                    if(displayAns.equals("display"))
                    {
                        switchvar = 1;
                    }
                    else if(displayAns.equals("on loan"))
                    {
                        switchvar = 2;
                    }
                    else if(displayAns.equals("loan"))
                    {
                        switchvar = 3;
                    }
                    else if(displayAns.equals("return"))
                    {
                        switchvar = 4;
                    }
                    else if(displayAns.equals("logout"))
                    {
                        switchvar = 5;
                    }
                    else
                        switchvar = 6;
                    
                    switch (switchvar)
                    {
                        //Displays all the book
                        case 1: 
                        for(BookStock b : books)
                        {
                            bDao.getAllBooks().toString();
                        }
                        break;
                        
                        //Gets all the books on loan
                        case 2:
                        for(int i = 0; i < books.size(); i++)
                        {
                            BookStock bookloan = bDao.getAllBooks().get(i);
                            lDao.getAllBooksOnLoan(bookloan).toString();
                        }
                        break;
                        
                        //Option to take out a book
                        case 3:
                        System.out.println("Which book do you wish to loan eg(bookid please)");
                        int id = input.nextInt();
                        for(int i = 0; i < 999; i++)
                        {
                             bDao.takeOutABook(id);
                             BookStock bookEntry = bDao.getABookById(id);
                             lDao.BorrowABook(bookEntry, userid);
                        }
                        break;
                        
                        //Books a user has loaned out
                        case 4://figure this out
                            /*
                        System.out.println("The following is the books you have loaned");
                        
                        lDao.getAllBooksOnLoan(book);
                        */
                        break;
                        
                        //logging out
                        case 5:
                            System.out.println("Are you sure you want to logout eg(yes and no)");
                            String ans = input.nextLine().toLowerCase();
                            //Logs the user out and ends the while loop
                            if(ans.equals("yes"))
                            {
                                flag = false;
                            }
                    }
                }
                
                //If the user doesn't type in the right information
                else if(!(uDao.LogingInUser(name, password).equals(userlog)))
                {
                    System.out.println("The details you have entered is incorrect try again");
                }
                
                //Checks to see if the user is an admin.
                else if(uDao.LogingInUser(name, password).equals(userlog) && uDao.getAdminByName(name).getAdmin() == 1)
                {
                    System.out.println("Welcome " + name + " you have logged in as an admin");
                    /*
                    Because of the way this as been designed the admin and normal user
                    stuff is the same and copy and pasted into.
                    This may or may not change as we learn more about how to adapt the program
                    */
                    int userid = uDao.getUserbyName(name).getUser_id();
                    
                    
                    System.out.println("What Do you want to do dislpay all titles, whats on loan, borrow a copy, return, logout");
                    System.out.println("As admin you can also, add titles, edit details , change amount of copies ,\n Remove a book or delete a member");    
                    
                    String displayAns = input.nextLine().toLowerCase();
                    
                    /*
                        Typing in an answer will set the value of "switchvar" allowing the user to navigate
                        the app using switch statements
                    */
                    if(displayAns.equals("display"))
                    {
                        switchvar = 1;
                    }
                    else if(displayAns.equals("on loan"))
                    {
                        switchvar = 2;
                    }
                    else if(displayAns.equals("loan"))
                    {
                        switchvar = 3;
                    }
                    else if(displayAns.equals("return"))
                    {
                        switchvar = 4;
                    }
                    else if(displayAns.equals("logout"))
                    {
                        switchvar = 5;
                    }
                    else if(displayAns.equals("add"))
                    {
                        switchvar = 6;
                    }
                    else if(displayAns.equals("edit"))
                    {
                        switchvar = 7;
                    }
                    else if(displayAns.equals("change copies"))
                    {
                        switchvar = 8;
                    }
                    else if(displayAns.equals("Remove"))
                    {
                        switchvar = 9;
                    }
                    else if(displayAns.equals("delete"))
                    {
                        switchvar = 10;
                    }
                    
                    switch (switchvar)
                    {
                        //Displays all the book
                        case 1: 
                        for(BookStock b : books)
                        {
                            bDao.getAllBooks().toString();
                        }
                        break;
                        
                        //Gets all the books on loan
                        case 2:
                        for(int i = 0; i < books.size(); i++)
                        {
                            BookStock bookloan = bDao.getAllBooks().get(i);
                            lDao.getAllBooksOnLoan(bookloan).toString();
                        }
                        break;
                        
                        //Option to take out a book
                        case 3:
                        System.out.println("Which book do you wish to loan eg(bookid please)");
                        int id = input.nextInt();
                        for(int i = 0; i < 999; i++)
                        {
                             bDao.takeOutABook(id);
                             BookStock bookEntry = bDao.getABookById(id);
                             lDao.BorrowABook(bookEntry, userid);
                        }
                        break;
                        
                        //Books a user has loaned out
                        case 4://figure this out
                            /*
                        System.out.println("The following is the books you have loaned");
                        
                        lDao.getAllBooksOnLoan(book);
                        */
                        break;
                        
                        //logging out
                        case 5:
                            System.out.println("Are you sure you want to logout eg(yes and no)");
                            String ans = input.nextLine().toLowerCase();
                            //Logs the user out and ends the while loop
                            if(ans.equals("yes"))
                            {
                                flag = false;
                            }
                            break;
                        case 6:
                            System.out.println("Enter in the details of the book");
                            System.out.println("Book name");
                            String bName = input.nextLine();
                            System.out.println("Book Author");
                            String bAuthor = input.nextLine();
                            System.out.println("Publisher");
                            String bPublisher = input.nextLine();
                            
                            
                    }
                    
                }
            }
            else
            {
                
            }
                      
         }
        
        //END OF THE WHILE LOOP
        
        
    }
}
