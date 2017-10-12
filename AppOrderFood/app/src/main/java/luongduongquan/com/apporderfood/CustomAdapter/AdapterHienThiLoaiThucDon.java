package luongduongquan.com.apporderfood.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import luongduongquan.com.apporderfood.DTO.LoaiMonAnDTO;
import luongduongquan.com.apporderfood.R;

/**
 * Created by luong.duong.quan on 10/12/2017.
 */

public class AdapterHienThiLoaiThucDon extends BaseAdapter {

	Context mcontext;
	int mLayout;
	List<LoaiMonAnDTO> mListLoaiThucDon;
	ViewHolderLoaiMonAn viewHolderLoaiMonAn;

	public AdapterHienThiLoaiThucDon (Context context, int layout, List<LoaiMonAnDTO> listLoaiThucDon){
		this.mcontext = context;
		this.mLayout = layout;
		this.mListLoaiThucDon = listLoaiThucDon;
	}

	@Override
	public int getCount() {
		return mListLoaiThucDon.size();
	}

	@Override
	public Object getItem(int i) {
		return mListLoaiThucDon.get(i);
	}

	@Override
	public long getItemId(int i) {
		return mListLoaiThucDon.get(i).getMaLoai();
	}

	public class ViewHolderLoaiMonAn {
		TextView tvTenLoai;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		View view1 = convertView;
		if (view1 == null){
			viewHolderLoaiMonAn = new ViewHolderLoaiMonAn();
			LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view1 = inflater.inflate(R.layout.item_loaithucdon, parent, false);

			viewHolderLoaiMonAn.tvTenLoai = (TextView) view1.findViewById(R.id.itTenLoaiThucDon);
			view1.setTag(viewHolderLoaiMonAn);
		} else {
			viewHolderLoaiMonAn = (ViewHolderLoaiMonAn) view1.getTag();
		}

		LoaiMonAnDTO itemLoaiMonAn = mListLoaiThucDon.get(position);
		viewHolderLoaiMonAn.tvTenLoai.setText(itemLoaiMonAn.getTenLoai());
		viewHolderLoaiMonAn.tvTenLoai.setTag(itemLoaiMonAn.getMaLoai());


		return view1;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		View view1 = view;
		if (view1 == null){
			viewHolderLoaiMonAn = new ViewHolderLoaiMonAn();
			LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view1 = inflater.inflate(R.layout.item_loaithucdon, viewGroup, false);

			viewHolderLoaiMonAn.tvTenLoai = (TextView) view1.findViewById(R.id.itTenLoaiThucDon);
			view1.setTag(viewHolderLoaiMonAn);
		} else {
			viewHolderLoaiMonAn = (ViewHolderLoaiMonAn) view1.getTag();
		}

		LoaiMonAnDTO itemLoaiMonAn = mListLoaiThucDon.get(i);
		viewHolderLoaiMonAn.tvTenLoai.setText(itemLoaiMonAn.getTenLoai());
		viewHolderLoaiMonAn.tvTenLoai.setTag(itemLoaiMonAn.getMaLoai());


		return view1;
	}
}
