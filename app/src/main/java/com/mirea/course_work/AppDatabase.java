package com.mirea.course_work;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {University.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UniversityDao universityDao();
}
