package com.mirea.course_work;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UniversityDao {
    @Insert
    void insert(University university);

    @Update
    void update(University university);

    @Delete
    void delete(University university);

    @Query("SELECT * FROM university")
    List<University> getAll();

    @Query("SELECT * FROM university WHERE name = :name")
    University getByName(String name);
}
