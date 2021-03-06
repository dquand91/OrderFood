package luongduongquan.com.apporderfood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import luongduongquan.com.apporderfood.CustomAdapter.AdapterHienThiLoaiThucDon;
import luongduongquan.com.apporderfood.DAO.LoaiMonAnDAO;
import luongduongquan.com.apporderfood.DAO.MonAnDAO;
import luongduongquan.com.apporderfood.DTO.LoaiMonAnDTO;
import luongduongquan.com.apporderfood.DTO.MonAnDTO;

/**
 * Created by luong.duong.quan on 10/12/2017.
 */

public class ThemThucDonActivity extends AppCompatActivity implements View.OnClickListener {

	ImageButton imgThemLoaiThucDon;
	Spinner spinLoaiThucDon;
	public static final int REQUEST_THEMLOAITHUCDON = 113;
	public static final int REQUEST_OPENGALLERY = 114;
	LoaiMonAnDAO loaiMonAnDAO;
	MonAnDAO monAnDAO;
	List<LoaiMonAnDTO> mlistLoaiMonAn;
	AdapterHienThiLoaiThucDon adapterHienThiLoaiThucDon;
	ImageView imgHinhMonAn;
	Button btnDongY_ThemMonAn, btnThoat_ThemMonAn;
	EditText edtTenMonAn, edtGiaTien;
	String linkHinh = "";


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_themthucdon);

		loaiMonAnDAO = new LoaiMonAnDAO(this);
		monAnDAO = new MonAnDAO(this);

		imgThemLoaiThucDon = (ImageButton) findViewById(R.id.ibtnThemLoaiThucDon);
		imgHinhMonAn = (ImageView) findViewById(R.id.imgHinhMonAn_ThemThucDon);
		btnDongY_ThemMonAn = (Button) findViewById(R.id.btnDongY_ThemThucDon);
		btnThoat_ThemMonAn = (Button) findViewById(R.id.btnThoat_ThemThucDon);
		edtTenMonAn = (EditText) findViewById(R.id.edtThemMonAn_ThemThucDon);
		edtGiaTien = (EditText) findViewById(R.id.edtGiaTien_ThemThucDon);

		imgThemLoaiThucDon.setOnClickListener(this);
		imgHinhMonAn.setOnClickListener(this);
		btnDongY_ThemMonAn.setOnClickListener(this);
		btnThoat_ThemMonAn.setOnClickListener(this);
		spinLoaiThucDon = (Spinner) findViewById(R.id.spinLoaiThucDon);

		HienThiSpinnerLoaiMonAn();

	}

	private void HienThiSpinnerLoaiMonAn(){
		mlistLoaiMonAn = loaiMonAnDAO.layTatCaLoaiMonAn();
		adapterHienThiLoaiThucDon = new AdapterHienThiLoaiThucDon(ThemThucDonActivity.this, R.layout.item_loaithucdon, mlistLoaiMonAn);
		spinLoaiThucDon.setAdapter(adapterHienThiLoaiThucDon);
		adapterHienThiLoaiThucDon.notifyDataSetChanged();
	}


	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.ibtnThemLoaiThucDon:
				Intent iThemLoaiMonAn = new Intent(ThemThucDonActivity.this, ThemLoaiThucDonActivity.class);
				startActivityForResult(iThemLoaiMonAn, REQUEST_THEMLOAITHUCDON);

				break;
			case R.id.imgHinhMonAn_ThemThucDon:

				Intent iOpenGallery = new Intent();
				iOpenGallery.setType("image/*");
				iOpenGallery.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(Intent.createChooser(iOpenGallery, "Choose an image..."), REQUEST_OPENGALLERY);

				break;

			case R.id.btnDongY_ThemThucDon:
				String tenMonAn = edtTenMonAn.getText().toString();
				String giaTien = edtGiaTien.getText().toString();
				int vitri = spinLoaiThucDon.getSelectedItemPosition();
				int maLoai = mlistLoaiMonAn.get(vitri).getMaLoai();
				if(tenMonAn.isEmpty() || giaTien.isEmpty()){
					Toast.makeText(this,R.string.vuilongnhapdulieu, Toast.LENGTH_SHORT).show();
				} else {
					MonAnDTO monAn = new MonAnDTO();
					monAn.setTenMonAn(tenMonAn);
					monAn.setGiaTien(giaTien);
					monAn.setMaLoai(maLoai);
					monAn.setHinhAnh(linkHinh);

					boolean check = monAnDAO.themMonAn(monAn);
					if(check){
						Toast.makeText(this,R.string.warning_thanhcong, Toast.LENGTH_SHORT).show();
						finish();
					} else {
						Toast.makeText(this,R.string.warning_thathbai, Toast.LENGTH_SHORT).show();
					}

				}



				break;
			case R.id.btnThoat_ThemThucDon:
				break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQUEST_THEMLOAITHUCDON){
			if (resultCode == RESULT_OK){
				Intent iDuLieuNhan = data;
				boolean check = iDuLieuNhan.getBooleanExtra("kiemtraloaithucdon", false);
				if(check){
					HienThiSpinnerLoaiMonAn();
					Toast.makeText(this,R.string.warning_thanhcong, Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(this,R.string.warning_thathbai, Toast.LENGTH_SHORT).show();
				}
			}
		} else if (requestCode == REQUEST_OPENGALLERY){
			if (resultCode == RESULT_OK && data != null){
				try {
					linkHinh = data.getData().toString();
					Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
					imgHinhMonAn.setImageBitmap(bitmap);

					imgHinhMonAn.setImageURI(data.getData());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


	}
}
