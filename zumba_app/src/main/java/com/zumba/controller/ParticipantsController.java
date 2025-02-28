package com.zumba.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zumba.bean.Participants;
import com.zumba.service.ParticipantsService;

@WebServlet("/ParticipantsController")
public class ParticipantsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ParticipantsController() {
        super();
       
    }

    ParticipantsService bs = new ParticipantsService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		List<Participants> listOfParticipants = bs.viewAllParticipantsDetails();
		HttpSession hs = request.getSession();
		System.out.println(listOfParticipants.size());
		hs.setAttribute("participants", listOfParticipants);
		response.sendRedirect("viewParticipants.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
