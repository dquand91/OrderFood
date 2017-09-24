package luongduongquan.com.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import luongduongquan.com.apporderfood.DTO.NhanVienDTO;
import luongduongquan.com.apporderfood.Database.CreateDatabase;

/**
 * Created by User on 9/24/2017.
 */

public class NhanVienDAO {
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

}
