package luongduongquan.com.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import luongduongquan.com.apporderfood.DTO.LoaiMonAnDTO;
import luongduongquan.com.apporderfood.Database.CreateDatabase;

/**
 * Created by luong.duong.quan on 10/12/2017.
 */

public class LoaiMonAnDAO {

	// Để lo việc giao tiếp với DataBase
	SQLiteDatabase database;


	public LoaiMonAnDAO(Context context){
		CreateDatabase createDatabase = new CreateDatabase(context);
		database = createDatabase.open();
	}

	public boolean themLoaiMonAn (String tenLoai){
		ContentValues contentValues = new ContentValues();
		contentValues.put(CreateDatabase.TB_LOAIMONAN_TENLOAI, tenLoai);

		long check = database.insert(CreateDatabase.TB_LOAIMONAN, null, contentValues);

		if (check != 0){
			return true;
		} else {
			return false;
		}
	}

	public List<LoaiMonAnDTO> layTatCaLoaiMonAn (){
		List<LoaiMonAnDTO> listLoaiMonAn = new ArrayList<LoaiMonAnDTO>();
		String truyvan = "SELECT * FROM " + CreateDatabase.TB_LOAIMONAN;
		Cursor cursor = database.rawQuery(truyvan, null);
		if (cursor != null ){
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				LoaiMonAnDTO loaiMonAn = new LoaiMonAnDTO();
				loaiMonAn.setMaLoai(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_LOAIMONAN_MALOAI)));
				loaiMonAn.setTenLoai(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_LOAIMONAN_TENLOAI)));
				listLoaiMonAn.add(loaiMonAn);
				cursor.moveToNext();
			}
		}
		return listLoaiMonAn;

	}

	public String LayHinhLoaiMonAn(int maloai){
		String hinhanh = "";
		String truyvan = "SELECT * FROM " + CreateDatabase.TB_MONAN + " WHERE " + CreateDatabase.TB_MONAN_MALOAI + " = '" + maloai + "' "
				+ " AND " + CreateDatabase.TB_MONAN_HINHANH + " != '' ORDER BY " + CreateDatabase.TB_MONAN_MAMON + " LIMIT 1";
		Cursor cursor = database.rawQuery(truyvan,null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			hinhanh = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_HINHANH));
			cursor.moveToNext();
		}

		return hinhanh;
	}



}
