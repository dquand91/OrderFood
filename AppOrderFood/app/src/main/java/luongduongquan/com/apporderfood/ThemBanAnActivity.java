package luongduongquan.com.apporderfood;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import luongduongquan.com.apporderfood.DAO.BanAnDAO;

public class ThemBanAnActivity extends AppCompatActivity implements View.OnClickListener {

    // Cái này là 1 cái Dialog
    // Được khai báo trong AndroidManifest và file style
    // style: <style name="PopupActivity" parent="Theme.AppCompat.Dialog">
    // Manifest: <activity android:name=".ThemBanAnActivity"
    //              android:theme="@style/PopupActivity"/>

    public static final String TAG_RESULT_THEM = "KetQuaThem";

    EditText edtThemTenBanAn;
    Button btnDongY, btnThoat;
    BanAnDAO banAnDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ban_an);

        edtThemTenBanAn = (EditText) findViewById(R.id.edtThemTenBanAn);
        btnDongY = (Button) findViewById(R.id.btnDongY_ThemBanAn);
        banAnDAO = new BanAnDAO(this);
        btnDongY.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnDongY_ThemBanAn){
            String tenBanAnNew = edtThemTenBanAn.getText().toString();
            if (!TextUtils.isEmpty(tenBanAnNew)){
                boolean check = banAnDAO.themBanAn(tenBanAnNew);
                Intent intent = new Intent();
                intent.putExtra(TAG_RESULT_THEM, check);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        }
    }
}
