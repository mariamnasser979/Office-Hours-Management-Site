/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoFinder;
import dao.DaoMessage;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Message;
import model.User;

/**
 *
 * @author DELL
 */
@WebServlet(name = "MessageAllSubjectStaff", urlPatterns = {"/MessageAllSubjectStaff"})
public class MessageAllSubjectStaff extends HttpServlet {
    
    DaoFinder daoFinder = new DaoFinder();
    DaoMessage daoMessage = new DaoMessage();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public void sendMessageMail(String from, String to,String subjectMail, String message){
        SendMail sendMail = new SendMail();
        if(sendMail.send(to,subjectMail,message,from,"offorg123"))
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
        PrintWriter out = response.getWriter();
        try{
            ArrayList<User> staffMembers = new ArrayList<>();
            Integer subjectID = 0;
            Integer fromUserID = 0;
            String fromUserName = request.getParameter("fromStudentDisplayName").toString();
            String messageSubject = request.getParameter("messageSubject").toString();
            String actualMessage = request.getParameter("messageContent").toString();
            String date = "";

            String fromUserEmail = daoFinder.findContactForUser(fromUserID);
                
            
            staffMembers = daoFinder.findStaffBySubject(subjectID);
            for (int i = 0; i < staffMembers.size(); i++) {
                Integer toUserID = staffMembers.get(i).getUserID();
                
                Message message = new Message();
                message.setFromUserID(fromUserID);
                message.setToUserID(toUserID);
                message.setActualMessage(actualMessage);
                message.setDate(date);

                sendMessageMail(fromUserEmail, staffMembers.get(i).getUserEmail(), messageSubject,actualMessage);
                boolean status = daoMessage.insertMessage(message);
                if (status == true){
                    out.print("operation done successfully");
                }
                else{
                    out.print("operation failed!");
                }
            }
        }catch (Exception e) {
           System.out.print("@@@@#### Error in MessageAllSubjectStaff @@@@####  " + e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
