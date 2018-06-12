package com.example.duongthuhien.kltn.SQLiteData;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.duongthuhien.kltn.Model.Kanji1;
import com.example.duongthuhien.kltn.Model.NewWordMCCB;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duong Thu Hien on 6/5/2018.
 */

public class SQLiteDataController extends SQLiteOpenHelper {
    private String DB_PATH = "/data/data/com.example.duongthuhien.kltn/databases/";
    private SQLiteDatabase myDatabase;
    private Context context;
    private static final String TAG = "hiendt_SQLiteData";


    // Phiên bản
    private static final int DATABASE_VERSION = 5;


    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "learn_japanese.sqlite";


    // Tên bảng: Note.
    //Bang MCCB
    private static final String TABLE_PHRASE = "phrase";
    private static final String COLUMN_PHRASE_ID = "_id";
    private static final String COLUMN_PHRASE_CATEGORY_ID = "category_id";
    private static final String COLUMN_PHRASE_PHIENAM = "pinyin";
    private static final String COLUMN_PHRASE_JNEWWORD = "japanese";
    private static final String COLUMN_PHRASE_VNEWWORD = "vietnamese";
    private static final String COLUMN_PHRASE_SOUND_NEWWORD = "voice";

    //Bang Kanji
    private static final String TABLE_KANJI = "ikanji";
    private static final String COLUMN_KANJI_ID = "id";
    private static final String COLUMN_KANJI_LESSON_ID = "lesson";
    private static final String COLUMN_KANJI_ON = "onjomi";
    private static final String COLUMN_KANJI_KUN = "kunjomi";
    private static final String COLUMN_KANJI_JWORD = "word";
    private static final String COLUMN_KANJI_VWORD = "vi_mean";
    private static final String COLUMN_KANJI_SOUND_NEWWORD = "id";
    private static final String COLUMN_KANJI_IMAGE = "image";
    private static final String COLUMN_KANJI_MOTA_V = "remember";
    private static final String COLUMN_KANJI_RON = "ronjomi";
    private static final String COLUMN_KANJI_RKUN = "rkunjomi";
    private static final String COLUMN_KANJI_CNWORD = "cn_mean";
    private static final String COLUMN_KANJI_NOTE = "note";


    public SQLiteDataController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        boolean dbexist = checkdatabase();

        if(dbexist)
        {
            Log.d(TAG, "Database exist");
        }
        else
        {
            Log.d(TAG, "Database isn't existed");
            createDatabse();
        }
    }

    // Tạo các bảng.
    public void createDatabse() {

        this.getReadableDatabase();

        try
        {
            copyDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    private void copyDatabase() throws IOException {
        Log.d(TAG,"copyDatabase");
        InputStream myinput = context.getAssets().open(DATABASE_NAME);

        String outFileName = DB_PATH + DATABASE_NAME;

        OutputStream myOutput = new FileOutputStream
                (outFileName);

        byte[] buffer = new byte[1024];
        int length;

        while ((length = myinput.read(buffer)) > 0)
        {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myinput.close();
    }
    public void open()
    {
        String myPath = DB_PATH + DATABASE_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void close()
    {
        myDatabase.close();
        super.close();
    }

    private boolean checkdatabase() {

        boolean checkdb = false;

        try
        {
            String myPath = DB_PATH + DATABASE_NAME;
            File dbfile = new File(myPath);
            checkdb = dbfile.exists();
        }
        catch (SQLException e)
        {
            System.out.println("Databse doesn't exist!");
        }

        return checkdb;
    }
    public SQLiteDatabase getMyDatabase()
    {
        return myDatabase;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public NewWordMCCB getNewWord(int id) {
        Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PHRASE, new String[]{COLUMN_PHRASE_ID,
                        COLUMN_PHRASE_CATEGORY_ID, COLUMN_PHRASE_JNEWWORD,
                        COLUMN_PHRASE_PHIENAM, COLUMN_PHRASE_VNEWWORD, COLUMN_PHRASE_SOUND_NEWWORD},
                COLUMN_PHRASE_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        NewWordMCCB newWord = new NewWordMCCB(cursor.getInt(0), cursor.getInt(1),
                cursor.getString(6), cursor.getString(3), cursor.getString(5),
                cursor.getString(4));
        // return note
        return newWord;
    }

    public Kanji1 getWord(int id) {
        Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_KANJI, new String[]{COLUMN_KANJI_ID,
                        COLUMN_KANJI_LESSON_ID, COLUMN_KANJI_ON,
                        COLUMN_KANJI_KUN, COLUMN_KANJI_JWORD, COLUMN_KANJI_VWORD,COLUMN_KANJI_SOUND_NEWWORD
                ,COLUMN_KANJI_IMAGE,COLUMN_KANJI_MOTA_V,COLUMN_KANJI_CNWORD,COLUMN_KANJI_RKUN,COLUMN_KANJI_RON},
                COLUMN_KANJI_LESSON_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Kanji1 kanji1 = new Kanji1();
        kanji1.setId(cursor.getInt(cursor.getColumnIndex("id")));
        kanji1.setStr_Sothutu(cursor.getInt(cursor.getColumnIndex("id")));
        kanji1.setStr_JWord_K(cursor.getString(cursor.getColumnIndex("word")));
        kanji1.setStr_Kun(cursor.getString(cursor.getColumnIndex("kunjomi")));
        kanji1.setStr_MoTa(cursor.getString(cursor.getColumnIndex("image")));
        kanji1.setStr_MoTa_V(cursor.getString(cursor.getColumnIndex("remember")));
        kanji1.setStr_On(cursor.getString(cursor.getColumnIndex("onjomi")));
        kanji1.setStr_VWord_K(cursor.getString(cursor.getColumnIndex("vi_mean")));
        kanji1.setSoundK("sound_"+ cursor.getString(cursor.getColumnIndex("id")));
        kanji1.setStr_AmHan(cursor.getString(cursor.getColumnIndex("cn_mean")));
        kanji1.setStr_ronjomi(cursor.getString(cursor.getColumnIndex("ronjomi")));
        kanji1.setStr_rkunjomi(cursor.getString(cursor.getColumnIndex("rkunjomi")));
        kanji1.setStr_ViDu(cursor.getString(cursor.getColumnIndex("note")));
        // return note
        return kanji1;
    }



    public List<NewWordMCCB> getAllNewWord() {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... ");

        List<NewWordMCCB> wordList = new ArrayList<NewWordMCCB>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PHRASE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                NewWordMCCB newWordMCCB = new NewWordMCCB();
                newWordMCCB.setId(cursor.getInt(0));
                newWordMCCB.setCatagory_id(cursor.getInt(1));
                newWordMCCB.setSound_NewWord(cursor.getString(6));
                newWordMCCB.setJNewWord(cursor.getString(5));
                newWordMCCB.setVNewWord(cursor.getString(9));
                newWordMCCB.setPhienAm(cursor.getString(4));
                // Thêm vào danh sách.
                wordList.add(newWordMCCB);
            } while (cursor.moveToNext());
        }

        // return note list
        return wordList;
    }

    public List<NewWordMCCB> getbyCatagoryID(int id) {

        List<NewWordMCCB> wordList = new ArrayList<NewWordMCCB>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PHRASE + " WHERE " + COLUMN_PHRASE_CATEGORY_ID
                + " = ?";

        SQLiteDatabase db = this.getWritableDatabase();
        String[] arg = {String.valueOf(id+1)};
        Cursor cursor = db.rawQuery(selectQuery, arg);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                NewWordMCCB newWordMCCB = new NewWordMCCB();
                newWordMCCB.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                newWordMCCB.setCatagory_id(cursor.getInt(cursor.getColumnIndex("category_id")));
                newWordMCCB.setSound_NewWord(cursor.getString(cursor.getColumnIndex("voice")));
                newWordMCCB.setJNewWord(cursor.getString(cursor.getColumnIndex("japanese")));
                newWordMCCB.setVNewWord(cursor.getString(cursor.getColumnIndex("vietnamese")));
                newWordMCCB.setPhienAm(cursor.getString(cursor.getColumnIndex("pinyin")));
                // Thêm vào danh sách.
                wordList.add(newWordMCCB);
            } while (cursor.moveToNext());
        }

        // return note list
        return wordList;
    }
    public List<Kanji1> getbylessionID(int id) {

        List<Kanji1> wordListKanji1 = new ArrayList<Kanji1>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_KANJI + " WHERE " + COLUMN_KANJI_LESSON_ID
                + " = ?";

        SQLiteDatabase db = this.getWritableDatabase();
        String[] arg = {String.valueOf(id+1)};
        Cursor cursor = db.rawQuery(selectQuery, arg);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                Kanji1 kanji1 = new Kanji1();
                kanji1.setId(cursor.getInt(cursor.getColumnIndex("id")));
                kanji1.setStr_Sothutu(cursor.getInt(cursor.getColumnIndex("id")));
                kanji1.setStr_JWord_K(cursor.getString(cursor.getColumnIndex("word")));
                kanji1.setStr_Kun(cursor.getString(cursor.getColumnIndex("kunjomi")));
                kanji1.setStr_MoTa(cursor.getString(cursor.getColumnIndex("image")));
                kanji1.setStr_MoTa_V(cursor.getString(cursor.getColumnIndex("remember")));
                kanji1.setStr_On(cursor.getString(cursor.getColumnIndex("onjomi")));
                kanji1.setStr_VWord_K(cursor.getString(cursor.getColumnIndex("vi_mean")));
                kanji1.setSoundK("sound_"+ cursor.getString(cursor.getColumnIndex("id")));
                kanji1.setStr_AmHan(cursor.getString(cursor.getColumnIndex("cn_mean")));
                kanji1.setStr_ronjomi(cursor.getString(cursor.getColumnIndex("ronjomi")));
                kanji1.setStr_rkunjomi(cursor.getString(cursor.getColumnIndex("rkunjomi")));
                kanji1.setStr_ViDu(cursor.getString(cursor.getColumnIndex("note")));
                wordListKanji1.add(kanji1);
            } while (cursor.moveToNext());
        }

        // return note list
        return wordListKanji1;
    }


    public int getWordCount() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... ");

        String countQuery = "SELECT  * FROM " + TABLE_PHRASE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }


//    public int updateNote() {
//        Log.i(TAG, "MyDatabaseHelper.updateNote ... " + note.getNoteTitle());
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_NOTE_TITLE, note.getNoteTitle());
//        values.put(COLUMN_NOTE_CONTENT, note.getNoteContent());
//
//        // updating row
//        return db.update(TABLE_NOTE, values, COLUMN_NOTE_ID + " = ?",
//                new String[]{String.valueOf(note.getNoteId())});
//    }

}
