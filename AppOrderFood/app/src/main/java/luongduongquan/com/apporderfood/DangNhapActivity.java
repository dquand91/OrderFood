package luongduongquan.com.apporderfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import luongduongquan.com.apporderfood.DAO.NhanVienDAO;
import luongduongquan.com.apporderfood.Utils.LogUtils;

/**
 * Created by luong.duong.quan on 9/28/2017.
 */

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener {

	protected String TAG = getClass().getSimpleName() + '@'
			+ Integer.toHexString(hashCode());

	Button btnDangKyDN, btnDangNhapDN;
	EditText edtTenDangNhapDN, edtMatKhauDN;
	NhanVienDAO nhanVienDAO;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_dangnhap);

		btnDangKyDN = (Button) findViewById(R.id.btnDangKy_DN);
		btnDangNhapDN = (Button) findViewById(R.id.btnDangNhap_DN);
		btnDangKyDN.setOnClickListener(this);
		btnDangNhapDN.setOnClickListener(this);
		edtMatKhauDN = (EditText) findViewById(R.id.edtMatKhau_DN);
		edtTenDangNhapDN = (EditText) findViewById(R.id.edtTenDangNhap_DN);
		nhanVienDAO = new NhanVienDAO(this);
		showButtonDangKyDangNhap();

	}

	@Override
	protected void onResume() {
		super.onResume();
		showButtonDangKyDangNhap();
	}

	private void showButtonDangKyDangNhap(){
		boolean check = nhanVienDAO.KiemTraNhanVien();
		if(check){
			btnDangNhapDN.setVisibility(View.VISIBLE);
			btnDangKyDN.setVisibility(View.GONE);
		}
		else {
			btnDangNhapDN.setVisibility(View.GONE);
			btnDangKyDN.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.btnDangKy_DN:
				Intent intent = new Intent(DangNhapActivity.this,ManHinhDangKy.class);
				startActivity(intent);
				break;
			case R.id.btnDangNhap_DN:
				DangNhap();
				break;
			default:
				break;
		}
	}

	private void DangNhap(){
		LogUtils.enter(TAG, "DangNhap");
		String tenDangNhap = edtTenDangNhapDN.getText().toString();
		String matKhau = edtMatKhauDN.getText().toString();
		long check = nhanVienDAO.KiemTraDangNhap(tenDangNhap);
		if (check != 0){
			Toast.makeText(this,"Thanh Cong", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this,"!!!!!!!!!!!!!!!!!!! That Bai !!!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
		}
		LogUtils.leave(TAG, "DangNhap");
	}
}
