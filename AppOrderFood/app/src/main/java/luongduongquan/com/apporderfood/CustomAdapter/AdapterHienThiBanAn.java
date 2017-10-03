package luongduongquan.com.apporderfood.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import luongduongquan.com.apporderfood.DTO.BanAnDTO;
import luongduongquan.com.apporderfood.R;

/**
 * Created by luong.duong.quan on 10/3/2017.
 */

public class AdapterHienThiBanAn extends BaseAdapter implements View.OnClickListener {

	Context mContext;
	int mLayout;
	List<BanAnDTO> mListBanAn;
	ViewHolder viewHolder;

	public AdapterHienThiBanAn (Context context, int layout, List<BanAnDTO> listBanAn){
		mContext = context;
		mLayout = layout;
		mListBanAn = listBanAn;
	}

	@Override
	public int getCount() {
		return mListBanAn.size();
	}

	@Override
	public Object getItem(int i) {
		return mListBanAn.get(i);
	}

	@Override
	public long getItemId(int i) {
		return mListBanAn.get(i).getMaBan();
	}

	private class ViewHolder{
		TextView tenBanAn;
		ImageView imgTinhTrangBanAn, imgHuy, imgGoiMon, imgThanhToan;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {

		View itemView = view;
		if(itemView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			itemView = inflater.inflate(mLayout, viewGroup, false);
			viewHolder = new ViewHolder();

			viewHolder.tenBanAn = (TextView) itemView.findViewById(R.id.tvTenBanAn_HienThi);
			viewHolder.imgTinhTrangBanAn = (ImageView) itemView.findViewById(R.id.imgBanAn_HienThi);
			viewHolder.imgHuy = (ImageView) itemView.findViewById(R.id.btnHuy);
			viewHolder.imgGoiMon = (ImageView) itemView.findViewById(R.id.btnGoiMon);
			viewHolder.imgThanhToan = (ImageView) itemView.findViewById(R.id.btnThanhToan);
			itemView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) itemView.getTag();
		}
		BanAnDTO itemBanAn = mListBanAn.get(i);
		viewHolder.tenBanAn.setText(itemBanAn.getTenBan());
		if(itemBanAn.isTinhTrang()){
			viewHolder.imgTinhTrangBanAn.setImageResource(R.drawable.banantrue);
		} else {
			viewHolder.imgTinhTrangBanAn.setImageResource(R.drawable.banan);
		}
		viewHolder.imgTinhTrangBanAn.setOnClickListener(this);

		return itemView;
	}

	@Override
	public void onClick(View view) {
		// Chỗ này để lấy được cái view hiện tại mình đang click.
		// Chứ nếu ko có chỗ này thì nó sẽ ko hiểu mình đang bấm vô cái item nào.
		viewHolder = (ViewHolder) ((View) view.getParent()).getTag();
		switch (view.getId()){
			case R.id.imgBanAn_HienThi:
				viewHolder.imgHuy.setVisibility(View.VISIBLE);




				break;
			default:
				break;
		}


	}
}
