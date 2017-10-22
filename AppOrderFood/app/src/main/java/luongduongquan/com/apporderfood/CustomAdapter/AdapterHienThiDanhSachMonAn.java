package luongduongquan.com.apporderfood.CustomAdapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import luongduongquan.com.apporderfood.DTO.MonAnDTO;
import luongduongquan.com.apporderfood.R;

/**
 * Created by User on 10/22/2017.
 */

public class AdapterHienThiDanhSachMonAn extends BaseAdapter {

    Context mContext;
    int mLayout;
    List<MonAnDTO> mListMonAn;
    ViewHolderHienThiDanhSachMonAn viewHolderHienThiDanhSachMonAn;

    public AdapterHienThiDanhSachMonAn(Context context, int layout, List<MonAnDTO> listMonAn){
        mContext = context;
        mLayout = layout;
        mListMonAn = listMonAn;
    }


    @Override
    public int getCount() {
        return mListMonAn.size();
    }

    @Override
    public Object getItem(int position) {
        return mListMonAn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mListMonAn.get(position).getMaMonAn();
    }

    public class ViewHolderHienThiDanhSachMonAn {
        TextView tvTenMonAn;
        TextView tvGiaTien;
        ImageView imgHinhMonAn;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(v == null) {
            viewHolderHienThiDanhSachMonAn = new ViewHolderHienThiDanhSachMonAn();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(mLayout, parent, false);

            viewHolderHienThiDanhSachMonAn.imgHinhMonAn = (ImageView) v.findViewById(R.id.imgHienThiDSMonAn);
            viewHolderHienThiDanhSachMonAn.tvTenMonAn = (TextView) v.findViewById(R.id.tvTenDSMonAn);
            viewHolderHienThiDanhSachMonAn.tvGiaTien = (TextView) v.findViewById(R.id.tvGiaTienDSMonAn);

            v.setTag(viewHolderHienThiDanhSachMonAn);
        } else {
            viewHolderHienThiDanhSachMonAn = (ViewHolderHienThiDanhSachMonAn) v.getTag();
        }


        MonAnDTO monAnDTO = mListMonAn.get(position);

        Uri uri = Uri.parse(monAnDTO.getHinhAnh());
        viewHolderHienThiDanhSachMonAn.tvTenMonAn.setText(monAnDTO.getTenMonAn());
        viewHolderHienThiDanhSachMonAn.tvGiaTien.setText("Giá: " + monAnDTO.getGiaTien());
        viewHolderHienThiDanhSachMonAn.imgHinhMonAn.setImageURI(uri);



        return v;


    }
}
