package com.zumba.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zumba.bean.Batch;
import com.zumba.bean.Participants;
import com.zumba.service.BatchService;
import com.zumba.service.ParticipantsService;


@WebServlet("/AddParticipantsController")
public class AddParticipantsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 // Create service objects for Participants
	ParticipantsService ps = new ParticipantsService();
	 BatchService bs = new BatchService(); // Service to fetch batch details

	// Handle GET request (fetch batches and handle delete action)
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     String action = request.getParameter("action");

	     // Fetch all batches and set them in session for dropdown in JSP
	     List<Batch> listOfBatches = bs.viewAllBatchDetails();  // Fetch the batches
	     HttpSession session = request.getSession();
	     session.setAttribute("batches", listOfBatches);  // Store batches in session
	     List<Participants> listOfParticipants = ps.viewAllParticipantsDetails();
	     session.setAttribute("participants", listOfParticipants);
	     if (action != null && action.equals("edit")) {
	         int pid = Integer.parseInt(request.getParameter("pid"));
	         // Fetch the participant from the database using the pid
	         Participants participant = ps.getParticipantsById(pid);

	         // Set the participant object as a request attribute (use 'participant' as the key)
	         request.setAttribute("participant", participant);

	         // Forward to the JSP page for editing
	         request.getRequestDispatcher("addParticipants.jsp").forward(request, response);
	     } else if (action != null && action.equals("delete")) {
	         int pid = Integer.parseInt(request.getParameter("pid"));
	         boolean isDeleted = ps.deleteParticipant(pid);

	         if (isDeleted) {
	             response.sendRedirect("addParticipants.jsp?msg=Participant+deleted+successfully");
	         } else {
	             response.sendRedirect("addParticipants.jsp?msg=Error+deleting+participant");
	         }
	     } else {
	         // Show the add participant form if no action (i.e., add participants)
	         request.getRequestDispatcher("addParticipants.jsp").forward(request, response);
	     }
	 }


    // Handle POST request to process the form and add the batch to the database
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the batch details from the form
    	String action = request.getParameter("action");
        int pid = Integer.parseInt(request.getParameter("pid"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        int bid = Integer.parseInt(request.getParameter("bid"));

        // Create a new Batch object with the form data
        Participants updatedParticipant = new Participants(pid, name, age, bid);

        boolean isSuccess = false;

        if ("update".equals(action)) {
            // Update the participant details (keep the same pid)
            isSuccess = ps.updateParticipant(updatedParticipant);
        } else if ("add".equals(action)) {
            // Handle adding a new participant (optional case)
            isSuccess = ps.addParticipant(updatedParticipant);
        }

        // Redirect with success or failure message
        if (isSuccess) {
            response.sendRedirect("addParticipants.jsp?msg=Participant+updated+successfully");
        } else {
            response.sendRedirect("addParticipants.jsp?msg=Error+updating+participant");
        }
    }
    
    
}