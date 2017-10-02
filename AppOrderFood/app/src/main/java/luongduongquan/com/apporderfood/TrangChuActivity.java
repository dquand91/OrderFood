package luongduongquan.com.apporderfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import luongduongquan.com.apporderfood.Utils.LogUtils;

/**
 * Created by luong.duong.quan on 10/2/2017.
 */

public class TrangChuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

	protected String TAG = getClass().getSimpleName() + '@'
			+ Integer.toHexString(hashCode());

	DrawerLayout drawerLayout;
	NavigationView navigationView;
	Toolbar toolbar;
	TextView tvTenNhanVien_Navigation;


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_trangchu);

		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		navigationView = (NavigationView) findViewById(R.id.navigationView);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		View view = navigationView.inflateHeaderView(R.layout.header_navigation);
		tvTenNhanVien_Navigation = (TextView) view.findViewById(R.id.tvTenNhanVien);

		setSupportActionBar(toolbar);
		getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.mo, R.string.dong){
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}
		};

		drawerLayout.setDrawerListener(actionBarDrawerToggle);
		actionBarDrawerToggle.syncState();

		navigationView.setItemIconTintList(null);


		Intent intentFromTrangChu = getIntent();
		String tenDangNhap = intentFromTrangChu.getStringExtra(DangNhapActivity.TAG_TenDangNhap);
		LogUtils.trace(TAG, "tenDangNhap= " + tenDangNhap);
		tvTenNhanVien_Navigation.setText(tenDangNhap);
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		return false;
	}
}
