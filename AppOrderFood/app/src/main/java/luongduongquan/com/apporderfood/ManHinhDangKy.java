package luongduongquan.com.apporderfood;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import luongduongquan.com.apporderfood.DAO.NhanVienDAO;
import luongduongquan.com.apporderfood.DTO.NhanVienDTO;
import luongduongquan.com.apporderfood.FragmentApp.DatePickerFragment;

public class ManHinhDangKy extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    EditText edtTenDangNhapDK;
    EditText edtMatKhauDK;
    EditText edtNgaySinhDK;
    EditText edtCMNDDK;
    Button btnDongYDK;
    Button btnThoatDK;
    RadioGroup grGioiTinh;
    String stringGioiTinh;

    NhanVienDAO nhanVienDAO;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_dangky);

        initView();


	}

	private void initView(){
        edtCMNDDK = (EditText) findViewById(R.id.edtCMND);
        edtTenDangNhapDK = (EditText) findViewById(R.id.edtTenDangNhap);
        edtMatKhauDK = (EditText) findViewById(R.id.edtMatKhau);
        edtNgaySinhDK= (EditText) findViewById(R.id.edtNgaySinh);
        edtNgaySinhDK.setOnFocusChangeListener(this);

        grGioiTinh= (RadioGroup) findViewById(R.id.rdGroupGioiTinh);

        btnDongYDK = (Button) findViewById(R.id.btnDongY);
        btnDongYDK.setOnClickListener(this);
        btnThoatDK = (Button) findViewById(R.id.btnThoat);
        btnThoatDK.setOnClickListener(this);

        nhanVienDAO = new NhanVienDAO(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDongY :
                String tenDangNhap = edtTenDangNhapDK.getText().toString();
                String matKhau = edtMatKhauDK.getText().toString();

                switch (grGioiTinh.getCheckedRadioButtonId()){
                    case R.id.rdNam:
                        stringGioiTinh = "Nam";
                        break;
                    default:
                        stringGioiTinh = "Nu";
                        break;
                }

                String ngaySinh = edtNgaySinhDK.getText().toString();
                int CMND = Integer.parseInt(edtCMNDDK.getText().toString());

                if(tenDangNhap == null || TextUtils.isEmpty(tenDangNhap)){
                    Toast.makeText(this,getResources().getString(R.string.warning_tendangnhap) + "",Toast.LENGTH_SHORT).show();
                } else if (matKhau == null || TextUtils.isEmpty(matKhau)){
                    Toast.makeText(this,getResources().getString(R.string.warning_matkhau) + "",Toast.LENGTH_SHORT).show();
                } else {
                    NhanVienDTO nhanVienDTO = new NhanVienDTO();
                    nhanVienDTO.setTenDN(tenDangNhap);
                    nhanVienDTO.setMatKhau(matKhau);
                    nhanVienDTO.setCMND(CMND);
                    nhanVienDTO.setNgaySinh(ngaySinh);
                    nhanVienDTO.setGioiTinh(stringGioiTinh);
                    long check = nhanVienDAO.ThemNhanVienDAO(nhanVienDTO);
                    if( check != 0){
                        Toast.makeText(this,getResources().getString(R.string.warning_thanhcong) + "",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this,getResources().getString(R.string.warning_thathbai) + "",Toast.LENGTH_SHORT).show();
                    }

                }

                break;
            case R.id.btnThoat :




                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.edtNgaySinh :
                if(hasFocus){
                    // Xuat Popup ngay sinh
                    DatePickerFragment datePickerFragment = new DatePickerFragment();
                    datePickerFragment.show(getFragmentManager(),"Năm Sinh");
                }

                break;
            default:
                break;
        }
    }
}
