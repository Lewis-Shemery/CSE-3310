package com.toreytaylor.utacaterering.model.DataAccessObj;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.toreytaylor.utacaterering.model.Objects.SystemUser;

import java.util.List;

@Dao
public interface DaoSystemUser {
    @Query("SELECT * FROM SystemUser ")
    List<SystemUser> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOnlySingleSystemUser(SystemUser systemUser);

    @Query("SELECT * FROM SystemUser where username LIKE :username")
    SystemUser findByUserName(String username);
    //    @Query("SELECT * FROM customer ")
//    List<Customer> getAll();
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertOnlySingleCustomer(Customer customer);

//    @Insert
//    void insertMultipleCustomers(List<Customer> customerList);

//    @Query("SELECT * FROM Customer WHERE userId= :userId")
//    Customer fetchOneCustomerbyuserID(int userId);


//    @Update
//    void updateCustomer(Customer customer );
//
//    @Delete
//    void deleteCustomer(Customer customer);

}

