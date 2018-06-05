package team4.ajoubuscarpool;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.concurrent.ExecutionException;

public class mySQLiteOpenHelper extends SQLiteOpenHelper {
    SQLiteDatabase list;
    private SQLiteOpenHelper openHelper;

    public mySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.v("DBHelper", "Constructure executed!!");
        list = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        list = db;
        db.execSQL("CREATE TABLE CARPOOL ("
                + "Idx INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "Time TEXT, "
                + "DeparturePoint TEXT, "
                + "Destination TEXT, "
                + "MinPrice INTEGER, "
                + "MaxPrice INTEGER, "
                + "MinPeople INTEGER, "
                + "MaxPeople INTEGER, "
                + "ServicePeriod TEXT, "
                + "RegisterPeriod TEXT, "
                + "driverInfo INTEGER);");

        db.execSQL("CREATE TABLE DRIVER ("
                + "Idx INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "Name TEXT, "
                + "CarInfo TEXT, "
                + "Career TEXT, "
                + "Evaluation REAL, "
                + "Comments TEXT);");

        db.execSQL("CREATE TABLE REQUEST ("
                + "Idx INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "DeparturePoint TEXT, "
                + "Destination TEXT, "
                + "Time TEXT, "
                + "MinPrice INTEGER, "
                + "MaxPrice INTEGER, "
                + "Count INTEGER, "
                + "ServicePeriod TEXT);");

        db.execSQL("CREATE TABLE PAY ("
                + "Idx INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "customerName TEXT, "
                + "customerInfo TEXT);");

        insert("CARPOOL", "1, '07:30', 'SUWON', 'NORYANGJIN', 10000, 50000, 13, 18, '1 weeks', '1 weeks', 1");
        insert("DRIVER", "1,'Hyeon','COUNTY','12years experience',3.8,'Nice Driver!'");
        insert("DRIVER", "2,'Hojin','COUNTY','12years experience',4.1,'Nice Driver!'");
        insert("DRIVER", "3,'Yongbin','COUNTY','12years experience',2.5,'Nice Driver!'");
        insert("DRIVER", "4,'Junhyeong','COUNTY','12years experience',3.3,'Nice Driver!'");
        insert("DRIVER", "5,'Heon-gi','COUNTY','12years experience',4.8,'Nice Driver!'");
        Log.v("DBHelper", "onCreate executed!");
    }
    /**
     * 데이터베이스에 insert문 쿼리를 삽입한다.
     * 'INSERT INTO "table이름(어트리뷰트)" VALUES("어트리뷰트 당 할당할 값");'이 Query로 변환되어 삽입된다.
     * table은 table의 이름과 삽입할 어트리뷰트를 값으로 받는다. ex) TRANSACTION(Id, Date, Amount)
     * value는 어트리뷰트에 대칭되는 값을 받아온다.              ex) 1, '2016-11-17', 20000
     */
    public void insert(String table, String value)
    {
        String query = "INSERT INTO " + table + " VALUES(" + value + ");";
        list.execSQL(query);
    }
    /**
     * 데이터베이스에 update문 쿼리를 삽입한다.
     * 'UPDATE "table이름" SET "어트리뷰트" WHERE "조건";'이 Query로 변환되어 삽입된다.
     * table은 table의 이름과 삽입할 어트리뷰트를 값으로 받는다. ex) TRANSACTION
     * set은 업데이트할 어트리뷰트와 변경사항을 받아온다.        ex) Date = '2016-10-11'
     * where은 조건을 받아온다                                   ex) Id = 1
     */
    public void update(String table, String set, String where)
    {
        list.execSQL("UPDATE " + table + " SET " + set + " WHERE " + where + ";");
    }
    /**
     *  데이터베이스에 delete문 쿼리를 삽입한다.
     *  'DELETE FROM "table이름" WHERE "조건";'이 Query로 변환되어 삽입된다.
     *  table은 table 이름을 값으로 받는다.    ex) TRANSACTION
     *  where은 조건을 받는다.                 ex) Id = 1
     */
    public void delete(String table, String where)
    {
        list.execSQL("DELETE FROM " + table + " WHERE " + where + ";");
    }
    /**
     * 데이터베이스에 쿼리를 삽입한다.
     * query에는 SELECT FROM WHERE문의 전체를 받아온다.
     * ex) SELECT * FROM TRANSACTION WHERE DATA = '2016-11-12';
     */
    public Cursor select(String query)
    {
        Cursor cursor = list.rawQuery(query, null);
        return cursor;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}