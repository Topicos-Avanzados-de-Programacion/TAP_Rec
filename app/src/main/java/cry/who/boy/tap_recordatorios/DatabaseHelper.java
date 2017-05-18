package cry.who.boy.tap_recordatorios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Olguin & Brandon on 23/04/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Rec.db";
    public static final String TABLE_NAME = "rec_table";
    public static final String COL_1 ="TITULO";
    public static final String COL_2 ="FECHA";
    public static final String COL_3 ="HORA";
    public static final String COL_4 ="DESCRIPCION";
    public static final String COL_5 ="IMPORTANCIA";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("+COL_1+" TEXT, "+COL_2+" TEXT, "+COL_3+" TEXT, "+COL_4+" TEXT, "+COL_5+" INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(Rec rec){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,rec.getTitulo());
        contentValues.put(COL_2,rec.getFecha());
        contentValues.put(COL_3, rec.getHora());
        contentValues.put(COL_4, rec.getDescripcion());
        contentValues.put(COL_5, rec.getImportancia());
        long veri=db.insert(TABLE_NAME, null, contentValues);
        db.close();
        if (veri==-1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String titulo,String fecha,String hora,String descripcion,int importancia){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,titulo);
        contentValues.put(COL_2,fecha);
        contentValues.put(COL_3, hora);
        contentValues.put(COL_4, descripcion);
        contentValues.put(COL_5, importancia);
        db.update(TABLE_NAME,contentValues,COL_1+" = ?",new String[]{titulo});
        return true;
    }

    public Integer deleteData(String titulo){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,COL_1+" = ?",new String[] {titulo});
    }
}
