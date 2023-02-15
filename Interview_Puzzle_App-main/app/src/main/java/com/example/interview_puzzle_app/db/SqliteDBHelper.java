package com.example.interview_puzzle_app.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class SqliteDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Interview_Puzzle_App.db";
    private static final String DB_PATH_SUFFIX = "/databases/";
    @SuppressLint("StaticFieldLeak")
    static Context ctx;

    public SqliteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        ctx = context;
    }

    public void CopyDataBaseFromAsset() throws IOException {
        InputStream myInput = ctx.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = getDatabasePath();

        // if the path doesn't exist first, create it
        File f = new File(ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
        if (!f.exists())
            f.mkdir();

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }


    private static String getDatabasePath() {
        return ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX
                + DATABASE_NAME;
    }

    public SQLiteDatabase openDataBase() throws SQLException {
        Log.d("14.04", "openDataBase0101: ");
        File dbFile = ctx.getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                System.out.println("Copying success from Assets folder");
                Log.d("14.04", "openDataBase: ");
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }
        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    //--------------------------------------------------------------------------------------------------

    //Create new account of new user
    //1.Check if already exist
    public Boolean isAlreadyRegister(String email){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from user where email=?",new String[]{String.valueOf(email)});
        if(cursor.getCount()>0) {
            DB.close();
            return true;
        }
        DB.close();
        return false;
    }

    //2.Register USer
    public Boolean RegisterUser(String email,int avtar,String name,int age,int coins,int microsoft,int google,int yahoo,int meta,int apple,int netflix){
        SQLiteDatabase DB = this.getWritableDatabase();
        //email, logedIn, avtar, name, age, coins, microsoft, google, yahoo, meta, apple, netflix
        ContentValues CV = new ContentValues();
        CV.put("email", email);
        CV.put("avtar", avtar);
        CV.put("name", name);
        CV.put("age", age);
        CV.put("coins", coins);
        CV.put("microsoft", microsoft);
        CV.put("google", google);
        CV.put("yahoo", yahoo);
        CV.put("meta", meta);
        CV.put("apple", apple);
        CV.put("netflix", netflix);
        long result = DB.insert("user",null,CV);
        DB.close();
        return result != -1;
    }


    //3. Loging In USer
    public void logingIn(String email){
        Log.d("14.04","Setting User Login Status at logingIn in database");
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();
        Cursor cursor = DB.rawQuery("select*from user where email=?",new String[]{String.valueOf(email)});
        if(cursor.getCount()>0) {
            CV.put("logedIn", 1);
            long logIn = DB.update("user", CV, "email=?", new String[]{String.valueOf(email)});
            CV.put("logedIn", 0);
            long logOut = DB.update("user", CV, "email!=?", new String[]{String.valueOf(email)});
//            return result != -1;
        }
        DB.close();
//        else return false;
    }

    //4. LogedIn User Info/Id
    public UserData getUserInfo(){
        Log.d("14.04","Getting User Data");
        SQLiteDatabase DB = this.getWritableDatabase();
        int loginStatus = 1;
        UserData userData = new UserData();
        Cursor cursor = DB.rawQuery("select * from user where logedIn=?",new String[]{String.valueOf(loginStatus)});
        Log.d("14.04","Record Found Count = "+cursor.getCount());
        if(cursor.getCount()>0) {
            while (cursor.moveToNext()) {
            userData = new UserData(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getInt(6),
                    cursor.getInt(7),
                    cursor.getInt(8),
                    cursor.getInt(9),
                    cursor.getInt(10),
                    cursor.getInt(11),
                    cursor.getInt(12));
                /*
                Log.d("14.04", "Data = \n" +
                        "ID : " + cursor.getInt(0) + "\n" +
                        "Email : "+cursor.getString(1)+"\n" +
                        "Log In Status : "+cursor.getInt(2)+"\n" +
                        "Avtar : "+cursor.getInt(3)+"\n" +
                        "Name : "+cursor.getString(4)+"\n" +
                        "Age : "+cursor.getInt(5)+"\n" +
                        "Coin : "+cursor.getInt(6)+"\n" +
                        "Micro : "+cursor.getInt(7)+"\n" +
                        "Google : "+cursor.getInt(8)+"\n" +
                        "Yahoo : "+cursor.getInt(9)+"\n" +
                        "Meta : "+cursor.getInt(10)+"\n" +
                        "apple : "+cursor.getInt(11)+"\n" +
                        "Netflix : "+cursor.getInt(12));
                 */
            }
            cursor.close();
        }
        DB.close();
        return userData;
    }

    //5. updating avatar
    public Boolean updatedata(int id, int updatedAvatar){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put("avtar", updatedAvatar);

        Cursor cursor = DB.rawQuery("select*from user where id=?",new String[]{String.valueOf(id)});
        if(cursor.getCount()>0) {
            long result = DB.update("user", CV, "id=?", new String[]{String.valueOf(id)});
            DB.close();
            return result != -1;
        }
        else {
            DB.close();
            return false;
        }
    }

    //5. updateUser
    public Boolean updateUser(String email,String name,int age,int microsoft,int google,int yahoo,int meta,int apple,int netflix){
        SQLiteDatabase DB = this.getWritableDatabase();
        //email, logedIn, avtar, name, age, coins, microsoft, google, yahoo, meta, apple, netflix
        ContentValues CV = new ContentValues();
        CV.put("name", name);
        CV.put("age", age);
        CV.put("microsoft", microsoft);
        CV.put("google", google);
        CV.put("yahoo", yahoo);
        CV.put("meta", meta);
        CV.put("apple", apple);
        CV.put("netflix", netflix);

        Cursor cursor = DB.rawQuery("select*from user where email=?",new String[]{String.valueOf(email)});
        if(cursor.getCount()>0) {
            long result = DB.update("user", CV, "email=?", new String[]{String.valueOf(email)});
            DB.close();
            return result != -1;
        }
        else {
            DB.close();
            return false;
        }
    }

    //6. Update Coins
    public Boolean updateCoins(int id, int coin){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put("coins", coin);

        Cursor cursor = DB.rawQuery("select*from user where id=?",new String[]{String.valueOf(id)});
        if(cursor.getCount()>0) {
            long result = DB.update("user", CV, "id=?", new String[]{String.valueOf(id)});
            DB.close();
            return result != -1;
        }
        else {
            DB.close();
            return false;
        }
    }

}

