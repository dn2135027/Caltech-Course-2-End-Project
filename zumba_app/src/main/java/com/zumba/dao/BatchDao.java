package com.zumba.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zumba.bean.Batch;
import com.zumba.resource.DatabaseResource;

public class BatchDao {

	public List<Batch> viewAllBatch() {
		List<Batch> listOfBatch = new ArrayList<Batch>();
		try {
		Connection con = DatabaseResource.getDbConnection();
		PreparedStatement pstmt = con.prepareStatement("select * from batch");	// as string format 
		ResultSet rs = pstmt.executeQuery();		// in while loop we need to convert to object ie batch object 
		while(rs.next()) {
			Batch bb = new Batch();
			bb.setBid(rs.getInt("bid"));
			bb.setTypeofbatch(rs.getString("typeofbatch"));
			bb.setTime(rs.getString("time"));
			// converted as batch object 
			listOfBatch.add(bb);		// store in list 
		}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return listOfBatch;
	}
	
	 // New method to insert a new batch into the database
    public boolean addBatch(Batch batch) {
        try {
            Connection con = DatabaseResource.getDbConnection();
            String query = "INSERT INTO batch (bid, typeofbatch, time) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, batch.getBid());
            pstmt.setString(2, batch.getTypeofbatch());
            pstmt.setString(3, batch.getTime());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;  // If rows are affected, it means the batch was added
        } catch (Exception e) {
            System.err.println(e.toString());
            return false;  // Return false if there was an error
        }
    }
 // New method to delete a batch from the database
    public boolean deleteBatch(int bid) {
        try {
            Connection con = DatabaseResource.getDbConnection();
            String query = "DELETE FROM batch WHERE bid = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, bid);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Return true if a row was deleted
        } catch (Exception e) {
            System.err.println(e.toString());
            return false; // Return false if there was an error
        }
    }
 // New method to update a batch from the database
    public boolean updateBatch(Batch batch) {
        try {
            Connection con = DatabaseResource.getDbConnection();
            String query = "UPDATE batch SET typeofbatch = ?, time = ? WHERE bid = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, batch.getTypeofbatch());
            pstmt.setString(2, batch.getTime());
            pstmt.setInt(3, batch.getBid());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Return true if a row was deleted
        } catch (Exception e) {
            System.err.println(e.toString());
            return false; // Return false if there was an error
        }
    }
    
    // Method to fetch a batch by its ID
    public Batch getBatchById(int bid) {
        Batch batch = null;
        try {
            Connection con = DatabaseResource.getDbConnection();
            String query = "SELECT * FROM batch WHERE bid = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, bid);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                batch = new Batch();
                batch.setBid(rs.getInt("bid"));
                batch.setTypeofbatch(rs.getString("typeofbatch"));
                batch.setTime(rs.getString("time"));
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return batch;
    }
}