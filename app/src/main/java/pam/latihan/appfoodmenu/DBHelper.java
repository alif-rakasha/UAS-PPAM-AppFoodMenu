package pam.latihan.appfoodmenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    // static variable
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "FoodDataDB";

    // table name
    private static final String TABLE_MENU = "food";

    // column tables
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "foodname";
    private static final String KEY_DESC = "fooddesc";
    private static final String KEY_PRICE = "foodprice";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_FOOD_TABLE = "CREATE TABLE " + TABLE_MENU + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_DESC + " TEXT," + KEY_PRICE + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_FOOD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
        onCreate(sqLiteDatabase);
    }

    public void addData(Foods food){
        SQLiteDatabase sqLiteDatabase  = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.name);
        values.put(KEY_DESC, food.desc);
        values.put(KEY_PRICE, food.price);

        sqLiteDatabase.insert(TABLE_MENU, null, values);
        sqLiteDatabase.close();
    }

    // get All Record
    public List<Foods> getFoodList(Context context) {
        List<Foods> contactList = new ArrayList<Foods>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MENU;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<Integer> gambar = FoodData.getImages();

        int index = 0;
        if (cursor.moveToFirst()) {
            do {
                if(index%10==0) {
                    index = 0;
                }
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                String price = cursor.getString(3);

                Foods menu = new Foods(title, description, price, context.getDrawable(gambar.get(index)));

                contactList.add(menu);
                index++;
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
}
