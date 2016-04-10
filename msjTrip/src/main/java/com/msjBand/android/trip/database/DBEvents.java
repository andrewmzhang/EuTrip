package com.msjBand.android.trip.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import android.widget.Toast;
import com.msjBand.android.trip.extras.MyApplication;
import com.msjBand.android.trip.pojo.Event;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by andrewmzhang on 4/2/2016.
 *
 * Handles all the database actions in sql.
 */
public class DBEvents {
    public static final int EVENTS_MASTER = 0;
    public static final int EVENTS_PERSONAL = 1;
    private static final int EVENTS_ADD = 2;

    private EventsHelper mHelper;
    private SQLiteDatabase mDatabase;

    public static final Uri EVENTS_URI = Uri.parse("DBEvents://com.msjBand.android.trip");

    public DBEvents(Context context) {

        mHelper = new EventsHelper(context);
        mDatabase = mHelper.getWritableDatabase();
    }

    public void insertEvents(int table, ArrayList<Event> Events, boolean clearPrevious) {
        if (clearPrevious) {
            deleteEvents(table);
        }

        String sql = "INSERT INTO " + (table == EVENTS_MASTER ? EventsHelper.TABLE_EVENTS: EVENTS_PERSONAL) + " VALUES (?,?,?,?,?,?,?,?);";

        SQLiteStatement statement = mDatabase.compileStatement(sql);
        mDatabase.beginTransaction();
        for (int i = 0; i < Events.size(); i++) {
            Event e = Events.get(i);
            statement.clearBindings();

            statement.bindString(2, e.getId());
            statement.bindString(3, e.getTitle());
            statement.bindString(4, e.getDesc());
            statement.bindString(5, e.getAuthor());
            statement.bindString(6, e.getDate().toString() );
            statement.bindString(7, e.getTimezone());
            statement.bindLong(8, e.getGroupRelevancy());

            statement.execute();
        }

//        Toast.makeText(MyApplication.getAppContext(),
//               "Inserting entries " + Events.size() + new Date(System.currentTimeMillis()),
//                Toast.LENGTH_LONG)
//                .show();

        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
    }

    public ArrayList<Event> readEvents(int table) {
        ArrayList<Event> Events = new ArrayList<>();

        String[] columns = {
                EventsHelper.COLUMN_UID,
                EventsHelper.COLUMN_ID,
                EventsHelper.COLUMN_TITLE,
                EventsHelper.COLUMN_DESC,
                EventsHelper.COLUMN_AUTHOR,
                EventsHelper.COLUMN_DATE,
                EventsHelper.COLUMN_TIMEZONE,
                EventsHelper.COLUMN_GR
        };
        Cursor cursor = mDatabase.query((table == EVENTS_MASTER ? EventsHelper.TABLE_EVENTS : EventsHelper.TABLE_PERSONAL), columns, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            //Toast.makeText(MyApplication.getAppContext(), "Loading entries " + cursor.getCount() + new Date(System.currentTimeMillis()), Toast.LENGTH_LONG).show();
            do {
                String id = cursor.getString(cursor.getColumnIndex(EventsHelper.COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndex(EventsHelper.COLUMN_TITLE));
                String desc = cursor.getString(cursor.getColumnIndex(EventsHelper.COLUMN_DESC));
                String author = cursor.getString(cursor.getColumnIndex(EventsHelper.COLUMN_AUTHOR));
                String date = cursor.getString(cursor.getColumnIndex(EventsHelper.COLUMN_DATE));
                String timezone = cursor.getString(cursor.getColumnIndex(EventsHelper.COLUMN_TIMEZONE));
                int groupRelevancy = cursor.getInt(cursor.getColumnIndex(EventsHelper.COLUMN_GR));


                Event event = new Event(id, title, desc, author, date, timezone, groupRelevancy);
                Events.add(event);
            }
            while (cursor.moveToNext());
        }
        return Events;
    }

    public void deleteEvents(int table) {
        mDatabase.delete((table == EVENTS_MASTER ? EventsHelper.TABLE_EVENTS : EventsHelper.TABLE_PERSONAL), null, null);
    }

    private static class EventsHelper extends SQLiteOpenHelper {
        public static final String TABLE_EVENTS = "events_master";
        public static final String TABLE_PERSONAL = "events_personal";

        public static final String COLUMN_UID = "_id";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESC = "desc";
        public static final String COLUMN_AUTHOR = "author";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TIMEZONE = "timezone";
        public static final String COLUMN_GR = "groupRelevancy";


        private static final String CREATE_TABLE_FOR_EVENTS_MASTER = "CREATE TABLE " + TABLE_EVENTS + " (" +
                COLUMN_UID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_ID + " TEXT," +
                COLUMN_TITLE + " TEXT," +
                COLUMN_DESC + " TEXT," +
                COLUMN_AUTHOR + " TEXT," +
                COLUMN_DATE + " TEXT," +
                COLUMN_TIMEZONE + " TEXT," +
                COLUMN_GR + " INTEGER" +
                ");";


        private static final String DB_NAME = "events_db";
        private static final int DB_VERSION = 1;
        private Context mContext;

        public EventsHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            mContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_FOR_EVENTS_MASTER);
            //Toast.makeText(MyApplication.getAppContext(), "Created table", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //Toast.makeText(MyApplication.getAppContext(), "update called", Toast.LENGTH_LONG).show();
            db.execSQL(" DROP TABLE " + TABLE_EVENTS + " IF EXISTS;");
            onCreate(db);
        }
    }


}
