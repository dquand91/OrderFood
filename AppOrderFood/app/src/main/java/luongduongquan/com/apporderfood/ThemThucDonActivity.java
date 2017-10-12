package luongduongquan.com.apporderfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import luongduongquan.com.apporderfood.CustomAdapter.AdapterHienThiLoaiThucDon;
import luongduongquan.com.apporderfood.DAO.LoaiMonAnDAO;
import luongduongquan.com.apporderfood.DTO.LoaiMonAnDTO;

/**
 * Created by luong.duong.quan on 10/12/2017.
 */

public class ThemThucDonActivity extends AppCompatActivity implements View.OnClickListener {

	ImageButton imgThemLoaiThucDon;
	Spinner spinLoaiThucDon;
	public static final int REQUEST_THEMLOAITHUCDON = 113;
	LoaiMonAnDAO loaiMonAnDAO;
	List<LoaiMonAnDTO> mlistLoaiMonAn;
	AdapterHienThiLoaiThucDon adapterHienThiLoaiThucDon;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_themthucdon);

		loaiMonAnDAO = new LoaiMonAnDAO(this);

		imgThemLoaiThucDon = (ImageButton) findViewById(R.id.ibtnThemLoaiThucDon);
		imgThemLoaiThucDon.setOnClickListener(this);
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
		}


	}
}
