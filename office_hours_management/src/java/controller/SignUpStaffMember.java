/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoSignUpStaffMember;
import database_connection.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author User
 */
@WebServlet(name = "SignUpStaffMember", urlPatterns = {"/SignUpStaffMember"})
public class SignUpStaffMember extends HttpServlet {

    DaoSignUpStaffMember daoSignUpStaffMember= new  DaoSignUpStaffMember();
    
    public String generatePassword(){
        
        int n = 20;
        // length is bounded by 256 Character 
        byte[] array = new byte[256]; 
        new Random().nextBytes(array); 
  
        String randomString 
            = new String(array, Charset.forName("UTF-8")); 
  
        // Create a StringBuffer to store the result 
        StringBuffer r = new StringBuffer(); 
  
        // Append first 20 alphanumeric characters 
        // from the generated random String into the result 
        for (int k = 0; k < randomString.length(); k++) { 
  
            char ch = randomString.charAt(k); 
  
            if (((ch >= 'a' && ch <= 'z') 
                 || (ch >= 'A' && ch <= 'Z') 
                 || (ch >= '0' && ch <= '9')) 
                && (n > 0)) { 
  
                r.append(ch); 
                n--; 
            } 
        } 
  
        // return the resultant string 
        return r.toString(); 

    }
    
    public void sendConfirmationMail(String from, String to,String subjectMail, String message){
        SendMail sendMail = new SendMail();
        if(sendMail.send(to,subjectMail,message,from,"maryasmoh109"))
        {
            out.println("Mail send successfully");
        }
        else{
             out.println("Failed sending mail!!!");
        }
    
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");   
        try (PrintWriter out = response.getWriter()) {
        
  System.out.print("@@@@#### " + "Tro tro Tro DONE!!");
        User user = new User();   
        user.setUserName(request.getParameter("name"));
        user.setUserDisplayName(request.getParameter("displayName"));
        user.setUserEmail(request.getParameter("email"));
        user.setUserType(request.getParameter("type"));
        user.setUserSubject(request.getParameter("subject"));
        
       String  password = generatePassword();
       
        while(daoSignUpStaffMember.isPasswordExisted(password))
        {
             password = generatePassword();
        }      
        user.setUserPassword(password);
       
        
        if(daoSignUpStaffMember.signUp(user))
        {
             
           sendConfirmationMail("mariamnasser979@gmail.com",user.getUserEmail(),"office hours management password",
                   "Hello, "+user.getUserName()+ "\n" +"Your passwrd is: " + password);
           
           response.sendRedirect("go_to_email_page.jsp");
        }
        } catch (Exception e) {
           System.out.print("@@@@#### " + e);
        }
  
    }
    
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

/*

               out.print("<html>\n" +
"    <head>\n" +
"        <title>TODO supply a title</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    </head>\n" +
"    <body>\n" +
"        <div>"+  generatePassword()+"</div>\n" +
       " <div>"+  generatePassword()+"</div>\n" +
"    </body>\n" +
"</html>");
       ;

*/