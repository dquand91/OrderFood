package luongduongquan.com.apporderfood.FragmentApp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.List;

import luongduongquan.com.apporderfood.CustomAdapter.AdapterHienThiDanhSachMonAn;
import luongduongquan.com.apporderfood.DAO.MonAnDAO;
import luongduongquan.com.apporderfood.DTO.MonAnDTO;
import luongduongquan.com.apporderfood.R;
import luongduongquan.com.apporderfood.TrangChuActivity;

/**
 * Created by User on 10/22/2017.
 */

public class HienThiDanhSachMonAnFragment extends Fragment {

    GridView gridView;
    MonAnDAO monAnDAO;
    List<MonAnDTO> monAnDTOList;
    AdapterHienThiDanhSachMonAn adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthithucdon, container, false);
        // Phải set cái này thành True thì mới onCreateOptionsMenu và onOptionsItemSelected có tác dụng
        // cái này để enable cái Option menu lên
        setHasOptionsMenu(true);
        ((TrangChuActivity) getActivity()).getSupportActionBar().setTitle(R.string.danhsachmonan);

        gridView = (GridView) view.findViewById(R.id.gvHienThiThucDon);

        monAnDAO = new MonAnDAO(getActivity());


        Bundle bundleNhan = getArguments();


        if (bundleNhan!=null){
            int maLoai = bundleNhan.getInt("maloai");
            monAnDTOList = monAnDAO.layDanhSachMonAnTheoLoai(maLoai);

            adapter = new AdapterHienThiDanhSachMonAn(getActivity(),R.layout.item_hienthidanhsachmonan, monAnDTOList);
            gridView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }


        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    getFragmentManager().popBackStack("HienThiLoai", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }

                return false;
            }
        });

        return view;
    }


}
