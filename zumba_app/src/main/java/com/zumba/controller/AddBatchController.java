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
import com.zumba.service.BatchService;

@WebServlet("/AddBatchController")
public class AddBatchController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    BatchService bs = new BatchService();

    // Handle GET request (to add, delete, or edit a batch)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        List<Batch> listOfBatches = bs.viewAllBatchDetails();  // Fetch the batches
	     HttpSession session = request.getSession();
	     session.setAttribute("batches", listOfBatches); 
        if (action != null && action.equals("edit")) {
            int bid = Integer.parseInt(request.getParameter("bid"));
            // Fetch the batch from the database using the bid
            Batch batch = bs.getBatchById(bid);

            // Set the batch object as a request attribute
            request.setAttribute("batch", batch);

            // Forward to the JSP page for editing
            request.getRequestDispatcher("addBatch.jsp").forward(request, response);
        } else if (action != null && action.equals("delete")) {
            int bid = Integer.parseInt(request.getParameter("bid"));
            boolean isDeleted = bs.deleteBatch(bid);

            if (isDeleted) {
                response.sendRedirect("addBatch.jsp?msg=Batch+deleted+successfully");
            } else {
                response.sendRedirect("addBatch.jsp?msg=Error+deleting+batch");
            }
        } else {
            // Show the add batch form if no action (i.e., add batch)
            request.getRequestDispatcher("addBatch.jsp").forward(request, response);
        }
    }

    // Handle POST request to add or update a batch
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int bid = Integer.parseInt(request.getParameter("bid"));
        String typeofbatch = request.getParameter("typeofbatch");
        String time = request.getParameter("time");

        Batch batch = new Batch(bid, typeofbatch, time);

        boolean isSuccess;
        if ("update".equals(action)) {
            isSuccess = bs.updateBatch(batch);  // Update the batch in the database
        } else {
            isSuccess = bs.addBatch(batch);  // Add the batch to the database
        }

        if (isSuccess) {
            response.sendRedirect("addBatch.jsp?msg=" + (action.equals("update") ? "Batch+updated+successfully" : "Batch+added+successfully"));
        } else {
            response.sendRedirect("addBatch.jsp?msg=" + (action.equals("update") ? "Error+updating+batch" : "Error+adding+batch"));
        }
    }
}