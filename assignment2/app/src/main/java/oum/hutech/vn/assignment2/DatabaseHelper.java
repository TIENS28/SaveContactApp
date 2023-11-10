package oum.hutech.vn.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "mydb.sqlite", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE Friends(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "name text," +
                    "phone integer," +
                    "address text)");
    }

    public int insertFriends(Friends friends){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", friends.getName());
        contentValues.put("phone", friends.getPhoneNo());
        contentValues.put("address", friends.getAddress());
        int result = (int) db.insert("Friends", null, contentValues);
        db.close();
        return result;
    }

    public ArrayList<Friends> getIdFriends(int id) {
        ArrayList<Friends> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Friends where id= " + id, null);
        if (cursor != null)
            cursor.moveToFirst();

        Friends friends = new Friends(cursor.getInt(0), cursor.getString(1),
                cursor.getInt(2), cursor.getString(3));
        list.add(friends);
        cursor.close();
        db.close();
        return list;
    }

    public ArrayList<Friends> getAllFriends() {
        ArrayList<Friends> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Friends", null);
        if (cursor != null)
            cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new Friends(cursor.getInt(0), cursor.getString(1),
                    cursor.getInt(2), cursor.getString(3)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }


    public int updateFriends(Friends friends) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", friends.getName());
        contentValues.put("phone", friends.getPhoneNo());
        contentValues.put("address", friends.getAddress());
        String whereClause = "id=?";
        String whereArgs[] = {friends.getId() + " "};
        int result = db.update("Friends", contentValues, whereClause, whereArgs);

        db.close();
        return result;
    }

    public int deleteFriends(int phoneNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = "phone=?";
        String whereArgs[] = {phoneNo + ""};
        int result = db.delete("Friends", whereClause, whereArgs);

        db.close();
        return result;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Friends");
        onCreate(db);
    }
}
