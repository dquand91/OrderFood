package luongduongquan.com.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import luongduongquan.com.apporderfood.Database.CreateDatabase;

/**
 * Created by User on 10/2/2017.
 */

public class BanAnDAO {
    // Để lo việc giao tiếp với DataBase
    SQLiteDatabase database;


    public BanAnDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public boolean themBanAn (String tenBanAn){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_BANAN_TENBAN, tenBanAn);
        contentValues.put(CreateDatabase.TB_BANAN_TINHTRANG, false);

        long check = database.insert(CreateDatabase.TB_BANAN, null, contentValues);

        if (check != 0){
            return true;
        } else {
            return false;
        }

    }

}
