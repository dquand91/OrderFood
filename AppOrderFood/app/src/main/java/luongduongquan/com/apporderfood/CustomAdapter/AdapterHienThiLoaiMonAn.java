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

import luongduongquan.com.apporderfood.DAO.LoaiMonAnDAO;
import luongduongquan.com.apporderfood.DTO.LoaiMonAnDTO;
import luongduongquan.com.apporderfood.R;

/**
 * Created by User on 10/16/2017.
 */

public class AdapterHienThiLoaiMonAn extends BaseAdapter {

    Context mcontext;
    int mLayout;
    List<LoaiMonAnDTO> mListLoaiMonAn;
    ViewHolderHienThiLoaiMonAn viewHolderHienThiLoaiMonAn;
    LoaiMonAnDAO loaiMonAnDAO;


    public AdapterHienThiLoaiMonAn(Context context, int layout, List<LoaiMonAnDTO> listLoaiMonAn){
        this.mcontext = context;
        this.mLayout = layout;
        this.mListLoaiMonAn = listLoaiMonAn;
        loaiMonAnDAO = new LoaiMonAnDAO(context);
    }

    @Override
    public int getCount() {
        return mListLoaiMonAn.size();
    }

    @Override
    public Object getItem(int position) {
        return mListLoaiMonAn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mListLoaiMonAn.get(position).getMaLoai();
    }

    public class ViewHolderHienThiLoaiMonAn {
        TextView tvTenLoai;
        ImageView imgHinhLoaiMonAn;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null) {
            viewHolderHienThiLoaiMonAn = new ViewHolderHienThiLoaiMonAn();
            LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(mLayout, parent, false);

            viewHolderHienThiLoaiMonAn.imgHinhLoaiMonAn = (ImageView) v.findViewById(R.id.imgHienThiMonAn);
            viewHolderHienThiLoaiMonAn.tvTenLoai = (TextView) v.findViewById(R.id.tvHienThiMonAn);

             v.setTag(viewHolderHienThiLoaiMonAn);
        } else {
            viewHolderHienThiLoaiMonAn = (ViewHolderHienThiLoaiMonAn) v.getTag();
        }
        LoaiMonAnDTO loaiMonAnDTO = mListLoaiMonAn.get(position);
        int maLoai = loaiMonAnDTO.getMaLoai();
        String hinhanh = loaiMonAnDAO.LayHinhLoaiMonAn(maLoai);

        Uri uri = Uri.parse(hinhanh);
        viewHolderHienThiLoaiMonAn.tvTenLoai.setText(loaiMonAnDTO.getTenLoai());
        viewHolderHienThiLoaiMonAn.imgHinhLoaiMonAn.setImageURI(uri);



        return v;
    }
}
