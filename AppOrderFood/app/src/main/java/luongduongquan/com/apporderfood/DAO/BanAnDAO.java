package luongduongquan.com.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import luongduongquan.com.apporderfood.DTO.BanAnDTO;
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

    public List<BanAnDTO> layTatCaBanAn (){
        List<BanAnDTO> listBanAn = new ArrayList<BanAnDTO>();
        String truyvan = "SELECT * FROM " + CreateDatabase.TB_BANAN;
        Cursor cursor = database.rawQuery(truyvan, null);
        if (cursor != null ){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                BanAnDTO banAn = new BanAnDTO();
                banAn.setMaBan(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_BANAN_MABAN)));
                banAn.setTenBan(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_BANAN_TENBAN)));
                banAn.setTinhTrang(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_BANAN_TINHTRANG)) == 1 ? true : false);
                listBanAn.add(banAn);

                cursor.moveToNext();
            }
        }
        return listBanAn;

    }

}
