package luongduongquan.com.apporderfood.FragmentApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import luongduongquan.com.apporderfood.R;
import luongduongquan.com.apporderfood.ThemThucDonActivity;
import luongduongquan.com.apporderfood.TrangChuActivity;

/**
 * Created by User on 10/11/2017.
 */

public class HienThiThucDonFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthithucdon, container, false);
        // Phải set cái này thành True thì mới onCreateOptionsMenu và onOptionsItemSelected có tác dụng
        // cái này để enable cái Option menu lên
        setHasOptionsMenu(true);
        ((TrangChuActivity) getActivity()).getSupportActionBar().setTitle(R.string.thucdon);

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
        MenuItem itemThemBanAn = menu.add(1,R.id.itThemThucDon,1,R.string.themthucdon);
        // Để set icon cho cái item đó
        itemThemBanAn.setIcon(R.drawable.logodangnhap);
        // Sẽ hiển thị cái item này ra nếu như Action Bar còn chỗ trống
        itemThemBanAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.itThemThucDon:
                Intent iThemThucDon = new Intent(getActivity(), ThemThucDonActivity.class);
                startActivity(iThemThucDon);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
