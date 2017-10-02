package luongduongquan.com.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import luongduongquan.com.apporderfood.DTO.NhanVienDTO;
import luongduongquan.com.apporderfood.Database.CreateDatabase;
import luongduongquan.com.apporderfood.Utils.LogUtils;

/**
 * Created by User on 9/24/2017.
 */

public class NhanVienDAO {
    protected String TAG = getClass().getSimpleName() + '@'
            + Integer.toHexString(hashCode());
    SQLiteDatabase database;


    public NhanVienDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public long ThemNhanVienDAO (NhanVienDTO nhanvien){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_NHANVIEN_TENDN, nhanvien.getTenDN());
        contentValues.put(CreateDatabase.TB_NHANVIEN_MATKHAU, nhanvien.getMatKhau());
        contentValues.put(CreateDatabase.TB_NHANVIEN_CMND, nhanvien.getCMND());
        contentValues.put(CreateDatabase.TB_NHANVIEN_GIOITINH, nhanvien.getGioiTinh());
        contentValues.put(CreateDatabase.TB_NHANVIEN_NGAYSINH, nhanvien.getNgaySinh());

        long check = database.insert(CreateDatabase.TB_NHANVIEN, null, contentValues);

        return check;
    }

    public boolean KiemTraNhanVien (){
        String truyvan = "SELECT * FROM " + CreateDatabase.TB_NHANVIEN;
        Cursor cursor = database.rawQuery(truyvan, null);
        if (cursor != null && cursor.getCount() != 0){
            return true;
        } else {
            return false;
        }
    }

    public long KiemTraDangNhap(String tenDangNhap){
        LogUtils.enter(TAG, "KiemTraDangNhap");
        long idNhanVien = 0;
        String truyvan = "SELECT * FROM " + CreateDatabase.TB_NHANVIEN
                + " WHERE " + CreateDatabase.TB_NHANVIEN_TENDN
                + " = " + "'" + tenDangNhap + "'";
        Cursor cursor = database.rawQuery(truyvan, null);
        LogUtils.trace(TAG, "truyvan= " + truyvan);
        if (cursor != null && cursor.moveToFirst()) {
                idNhanVien = cursor.getInt(0);
            LogUtils.trace(TAG, "idNhanVien= " + idNhanVien);
            LogUtils.leave(TAG, "KiemTraDangNhap");
            return idNhanVien;
        }
        LogUtils.leave(TAG, "KiemTraDangNhap");
        return idNhanVien;

    }

}
