package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "todo.db";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db 시작될 때 호출된다

        //데이터베이스 --> 테이블 ->컬럼 ->값
        db.execSQL("CREATE TABLE IF NOT EXISTS TodoList (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, content TEXT NOT NULL, writeDate TEXT NOT NULL)");
        //------------------------------ Todolist (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, content TEXT NOT NULL, writeDate TEXT NOT NULL) 는 컬럼값임
        // 위 규약에 맞춰서 데이터를 만질 수 있음.

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);

    }

    // SELECT 문 (할 일 목록들을 조회)
    public ArrayList<TodoItem> getTodoList(){
        ArrayList<TodoItem> todoItems = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TodoList ORDER BY writeDate DESC", null);
        if(cursor.getCount() != 0){
            //조회한 데이터가 있을 때 내부 수행
            while (cursor.moveToNext()){


                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
                String writeDate = cursor.getString(cursor.getColumnIndexOrThrow("writeDate"));

                TodoItem todoItem = new TodoItem();
                todoItem.setId(id);
                todoItem.setTitle(title);
                todoItem.setContent(content);
                todoItem.setWriteDate(writeDate);
                todoItems.add(todoItem);
            }
        }
        cursor.close();

        return todoItems;
    }

    // INSET 문을  public void로 만들어 줘서 어디서든 가져다 쓸 수 있게 한다.
    // 할 일 목록을 DB에 넣는 역할
    public void InsertTodo(String _title, String _content, String _writeDate){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO TodoList(title, content, writeDate) VALUES('" + _title + "','" + _content + "','" + _writeDate + "');");
    }

    //UPDATE 문(할 일 목록을 수정한다)
    public void UpdateTodo(String _title, String _content, String _writeDate, int _id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE TodoList SET title = '"+ _title +"', content = '"+ _content +"', writeDate = '"+ _writeDate +"' WHERE id = '"+ _id +"'");
    }


    //DELETE 문 (할 일 목록을 제거함)

    public void deleteTodo(int _id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM TodoList WHERE id = '" + _id + "'");
    }

}
