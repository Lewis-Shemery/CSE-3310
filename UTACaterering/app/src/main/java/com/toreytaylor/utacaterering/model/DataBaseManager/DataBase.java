package com.toreytaylor.utacaterering.model.DataBaseManager;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.toreytaylor.utacaterering.model.DataAccessObj.DaoEvent;
import com.toreytaylor.utacaterering.model.DataAccessObj.DaoSystemUser;
import com.toreytaylor.utacaterering.model.Objects.Event;
import com.toreytaylor.utacaterering.model.Objects.SystemUser;

@Database(entities = {SystemUser.class, Event.class}, version = 2, exportSchema = false)
public abstract class DataBase extends RoomDatabase{
    private static DataBase DataBase;

    public abstract DaoSystemUser daoSystemUser();
    public abstract DaoEvent daoEvent();

    // Static DataBase for Singleton
    public static DataBase getInstance(Context context){
        if (null == DataBase) {
            DataBase = buildDatabaseInstance(context);
        }
        return DataBase;
    }

    @NonNull
    private  static DataBase buildDatabaseInstance(Context context){
        return Room.databaseBuilder(context,
                DataBase.class,
                "this.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    public static void cleanUp(){
        DataBase = null;
    }
}
