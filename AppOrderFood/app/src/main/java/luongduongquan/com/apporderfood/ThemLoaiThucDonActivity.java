package luongduongquan.com.apporderfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import luongduongquan.com.apporderfood.DAO.LoaiMonAnDAO;

public class ThemLoaiThucDonActivity extends AppCompatActivity implements View.OnClickListener {

	EditText edtTenLoai;
	Button btnDongY;
	LoaiMonAnDAO loaiMonAnDAO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_them_loai_thuc_don);
		setTitle(R.string.themloaithucdon);

		loaiMonAnDAO = new LoaiMonAnDAO(this);

		edtTenLoai = (EditText) findViewById(R.id.edtThemLoaiThucDon);
		btnDongY = (Button) findViewById(R.id.btnDongY_ThemLoaiThucDon);

		btnDongY.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		if(view.getId() == R.id.btnDongY_ThemLoaiThucDon){
			String tenLoaiThucDonMoi = edtTenLoai.getText().toString();
			if(!tenLoaiThucDonMoi.isEmpty()){
				boolean check = loaiMonAnDAO.themLoaiMonAn(tenLoaiThucDonMoi);
				Intent iDuLieu = new Intent();
				iDuLieu.putExtra("kiemtraloaithucdon", check);
				setResult(Activity.RESULT_OK, iDuLieu);
				finish();

			} else {
				Toast.makeText(this,R.string.vuilongnhapdulieu, Toast.LENGTH_SHORT).show();
			}



		}

	}
}
