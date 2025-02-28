package com.zumba.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zumba.bean.Batch;
import com.zumba.bean.Participants;
import com.zumba.resource.DatabaseResource;

public class ParticipantsDao {

	public List<Participants> viewAllParticipants() {
		List<Participants> listOfParticipants = new ArrayList<Participants>();
		try {
		Connection con = DatabaseResource.getDbConnection();
		PreparedStatement pstmt = con.prepareStatement("select * from participants");
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Participants pb = new Participants();
			pb.setPid(rs.getInt("pid"));
			pb.setName(rs.getString("name"));
			pb.setAge(rs.getInt("age"));
			pb.setBid(rs.getInt("bid"));
			listOfParticipants.add(pb);
		}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return listOfParticipants;
	}
	
	// Method to add a new participant into the database
    public boolean addParticipant(Participants participant) {
        try {
            Connection con = DatabaseResource.getDbConnection();
            String query = "INSERT INTO participants (pid, name, age, bid) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, participant.getPid());
            pstmt.setString(2, participant.getName());
            pstmt.setInt(3, participant.getAge());
            pstmt.setInt(4, participant.getBid());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;  // Return true if the participant was successfully added
        } catch (Exception e) {
            System.err.println(e.toString());
            return false;  // Return false if there was an error
        }
    }
    
    // Method to delete a participant by pid
    public boolean deleteParticipant(int pid) {
        try {
            Connection con = DatabaseResource.getDbConnection();
            String query = "DELETE FROM participants WHERE pid = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, pid);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;  // Return true if the participant was deleted successfully
        } catch (Exception e) {
            System.err.println(e.toString());
            return false;  // Return false if there was an error
        }
    }
	
 // New method to update a participant from the database
    public boolean updateParticipants(Participants participant) {
        try {
            Connection con = DatabaseResource.getDbConnection();
            String query = "UPDATE participants SET name = ?, age = ?, bid = ? WHERE pid = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, participant.getName());
            pstmt.setInt(2, participant.getAge());
            pstmt.setInt(3, participant.getBid());
            pstmt.setInt(4, participant.getPid());            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Return true if a row was deleted
        } catch (Exception e) {
            System.err.println(e.toString());
            return false; // Return false if there was an error
        }
    }
    
    // Method to fetch a participant by its ID
    public Participants getParticipantsById(int pid) {
        Participants participant = null;
        try {
            Connection con = DatabaseResource.getDbConnection();
            String query = "SELECT * FROM participants WHERE pid = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, pid);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                participant = new Participants();
                participant.setPid(rs.getInt("pid"));
                participant.setName(rs.getString("name"));
                participant.setAge(rs.getInt("age"));
                participant.setBid(rs.getInt("bid"));
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return participant;
    }
}