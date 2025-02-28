package com.zumba.service;

import java.util.List;

import com.zumba.bean.Batch;
import com.zumba.dao.BatchDao;

public class BatchService {

	BatchDao batchDao = new BatchDao();

    // Existing method to view all batches
    public List<Batch> viewAllBatchDetails() {
        return batchDao.viewAllBatch();
    }

    // New method to add a batch to the database
    public boolean addBatch(Batch batch) {
        return batchDao.addBatch(batch);  // Call the DAO method to insert the batch
    }
    
 // New method to delete a batch from the database
    public boolean deleteBatch(int bid) {
        return batchDao.deleteBatch(bid);  // Call the DAO method to delete the batch
    }
    
 // New method to update a batch in the database
    public boolean updateBatch(Batch batch) {
        return batchDao.updateBatch(batch);  // Call the DAO method to update the batch
    }
    
    // Method to fetch a batch by its ID
    public Batch getBatchById(int bid) {
        return batchDao.getBatchById(bid);  // Call DAO to get the batch by its ID
    }
}