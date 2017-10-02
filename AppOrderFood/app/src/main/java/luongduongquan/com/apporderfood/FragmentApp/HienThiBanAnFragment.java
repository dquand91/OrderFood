package luongduongquan.com.apporderfood.FragmentApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import luongduongquan.com.apporderfood.R;
import luongduongquan.com.apporderfood.ThemBanAnActivity;

/**
 * Created by User on 10/2/2017.
 */

public class HienThiBanAnFragment extends android.support.v4.app.Fragment {

    public static int REQUEST_CODE_THEM = 111;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthibanan, container, false);

        // Phải set cái này thành True thì mới onCreateOptionsMenu và onOptionsItemSelected có tác dụng
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // Các thông số bên dưới:
        // 1 => groupId, do mình tự đặt
        // R.id.itThemBanAn => id trong file menu do mình tạo
        // 1 => Vị trí của cái item này
        // R.string.itThemBanAn => cái title hiển thị khi bấm vào item đó
        MenuItem itemThemBanAn = menu.add(1,R.id.itThemBanAn,1,R.string.itThemBanAn);
        // Để set icon cho cái item đó
        itemThemBanAn.setIcon(R.drawable.thembanan);
        // Sẽ hiển thị cái item này ra nếu như Action Bar còn chỗ trống
        itemThemBanAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.itThemBanAn:
                Intent intentThamBanAn = new Intent(getActivity(), ThemBanAnActivity.class);
                startActivityForResult(intentThamBanAn, REQUEST_CODE_THEM);

                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_THEM){
            if (resultCode == Activity.RESULT_OK){
                boolean check = data.getBooleanExtra(ThemBanAnActivity.TAG_RESULT_THEM, false);
                if (check){
                    Toast.makeText(getActivity(), getResources().getString(R.string.warning_thanhcong), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.warning_thathbai), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
