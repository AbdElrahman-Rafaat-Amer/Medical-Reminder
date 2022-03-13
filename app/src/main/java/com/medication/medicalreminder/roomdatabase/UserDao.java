package com.medication.medicalreminder.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.medication.medicalreminder.model.MedicinePojoo;


@Dao
public interface UserDao {



   // @Query("Select * from movie where movie_name like :title")
   // Movie getMovieByName(String title);

  //  @Insert(onConflict = OnConflictStrategy.IGNORE)
  //  void insertMovie(Movie movie);

  //  @Query("Select * from user where movie_name like :title")
  //   MedicinePojoo getMovieByName(String title);

  //  @Delete
 //   void deleteMedicine(String UID);

   // @Delete
   // void insertMedicine(MedicinePojoo medicine);


}
