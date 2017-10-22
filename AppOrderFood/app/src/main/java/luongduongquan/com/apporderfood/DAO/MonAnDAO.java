package luongduongquan.com.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import luongduongquan.com.apporderfood.DTO.MonAnDTO;
import luongduongquan.com.apporderfood.Database.CreateDatabase;

/**
 * Created by User on 10/14/2017.
 */

public class MonAnDAO {

    // Để lo việc giao tiếp với DataBase
    SQLiteDatabase database;


    public MonAnDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public boolean themMonAn (MonAnDTO monAn){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_MONAN_TENMONAN, monAn.getTenMonAn());
        contentValues.put(CreateDatabase.TB_MONAN_GIATIEN, monAn.getGiaTien());
        contentValues.put(CreateDatabase.TB_MONAN_HINHANH, monAn.getHinhAnh());
        contentValues.put(CreateDatabase.TB_MONAN_MALOAI, monAn.getMaLoai());

        long check = database.insert(CreateDatabase.TB_MONAN, null, contentValues);

        if (check != 0){
            return true;
        } else {
            return false;
        }
    }

    public List<MonAnDTO> layDanhSachMonAnTheoLoai (int maLoai){
        List<MonAnDTO> monAnDTOs = new ArrayList<>();


        String truyvan = "SELECT * FROM " + CreateDatabase.TB_MONAN + " WHERE " + CreateDatabase.TB_MONAN_MALOAI + " = '" + maLoai + "' ";
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            MonAnDTO monAnDTO = new MonAnDTO();
            monAnDTO.setHinhAnh(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_HINHANH)));
            monAnDTO.setTenMonAn(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_TENMONAN)));
            monAnDTO.setGiaTien(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_GIATIEN)));
            monAnDTO.setMaMonAn(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_MAMON)));
            monAnDTO.setMaLoai(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_MALOAI)));
            monAnDTOs.add(monAnDTO);



            cursor.moveToNext();
        }

        return monAnDTOs;

    }

}
