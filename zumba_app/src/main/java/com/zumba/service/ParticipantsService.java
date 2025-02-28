package com.zumba.service;

import java.util.List;

import com.zumba.bean.Batch;
import com.zumba.bean.Participants;
import com.zumba.dao.ParticipantsDao;

public class ParticipantsService {

	ParticipantsDao pd = new ParticipantsDao();
	
	// Existing method to view all participants
	public List<Participants> viewAllParticipantsDetails() {
		return pd.viewAllParticipants(); // Call the DAO method to view the participants
	}
	
	// Method to add a participant
    public boolean addParticipant(Participants participant) {
        return pd.addParticipant(participant);  // Call the DAO method to add the participant
    }
    
    // New method to delete a participant by pid
    public boolean deleteParticipant(int pid) {
        return pd.deleteParticipant(pid);
    }

 // New method to update a participant in the database
    public boolean updateParticipant(Participants participant) {
        return pd.updateParticipants(participant);  // Call the DAO method to update the participant
    }
    
    // Method to fetch a participant by its ID
    public Participants getParticipantsById(int pid) {
        return pd.getParticipantsById(pid);  // Call DAO to get the participant by its ID
    }
}
