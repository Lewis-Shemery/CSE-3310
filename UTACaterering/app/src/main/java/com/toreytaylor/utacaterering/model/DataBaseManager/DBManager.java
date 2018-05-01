package com.toreytaylor.utacaterering.model.DataBaseManager;

import android.os.AsyncTask;

import com.toreytaylor.utacaterering.model.DataBaseManager.DataBase;
import com.toreytaylor.utacaterering.model.Objects.Event;
import com.toreytaylor.utacaterering.model.Objects.SystemUser;

import java.util.List;

public class DBManager {

    public  static class SearchSystemUser extends AsyncTask<Void, Void, Boolean> {
        private DataBase db;
        private String name;
        private static SystemUser systemUser;

        public SearchSystemUser(DataBase db, String name){
            this.db = db;
            this.name = name;
        }

        public SystemUser getSystemUser() {
            return systemUser;
        }

        @Override
        protected Boolean doInBackground(Void... voids){

            systemUser = db.daoSystemUser().findByUserName(name);
            return true;
        }
    }

    public  static class InsertSystemUser extends AsyncTask<Void, Void, Boolean> {
        private DataBase db;
        private SystemUser systemUser;

        public InsertSystemUser(DataBase db, SystemUser systemUser){
            this.db = db;
            this.systemUser = systemUser;
        }

        @Override
        protected Boolean doInBackground(Void... voids){

            db.daoSystemUser().insertOnlySingleSystemUser(systemUser);
            return true;
        }
    }

    public  static class InsertEvent extends AsyncTask<Void, Void, Boolean> {
        private DataBase db;
        private Event event;

        public InsertEvent(DataBase db, Event event){
            this.db = db;
            this.event = event;
        }

        @Override
        protected Boolean doInBackground(Void... voids){

            db.daoEvent().insertSingleEvent(event);
            return true;
        }
    }

    public  static class DeleteEvent extends AsyncTask<Void, Void, Boolean> {
        private DataBase db;
        private Event event;

        public DeleteEvent(DataBase db, Event event){
            this.db = db;
            this.event = event;
        }

        @Override
        protected Boolean doInBackground(Void... voids){

            db.daoEvent().deleteEvent(event.getEventId());
            return true;
        }
    }

    public static class SearchEventForUser extends AsyncTask<Void, Void, Boolean> {
        private DataBase db;
        private String userId;
        private static List<Event> events;

        public SearchEventForUser(DataBase db, String userId){
            this.db = db;
            this.userId = userId;
        }

        public List<Event> getList() {

            return events;
        }

        @Override
        protected Boolean doInBackground(Void... voids){

            events = db.daoEvent().findCustomerEvents(userId);
            return true;
        }
    }

    public static class SearchReservedForCaterer extends AsyncTask<Void, Void, Boolean> {
        private DataBase db;
        private static List<Event> events;

        public SearchReservedForCaterer(DataBase db){
            this.db = db;
        }

        public List<Event> getList() {

            return events;
        }

        @Override
        protected Boolean doInBackground(Void... voids){

            events = db.daoEvent().findCatererEvents(true);
            return true;
        }
    }

    public static class SearchPendingForCaterer extends AsyncTask<Void, Void, Boolean> {
        private DataBase db;
        private static List<Event> events;

        public SearchPendingForCaterer(DataBase db){
            this.db = db;
        }

        public List<Event> getList() {

            return events;
        }

        @Override
        protected Boolean doInBackground(Void... voids){

            events = db.daoEvent().findCatererEvents(false);
            return true;
        }
    }
}
