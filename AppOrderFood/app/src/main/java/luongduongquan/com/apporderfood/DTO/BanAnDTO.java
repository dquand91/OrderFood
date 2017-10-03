package luongduongquan.com.apporderfood.DTO;

/**
 * Created by luong.duong.quan on 10/3/2017.
 */

public class BanAnDTO {

	int MaBan;
	String TenBan;
	boolean TinhTrang;

	public int getMaBan() {
		return MaBan;
	}

	public void setMaBan(int maBan) {
		MaBan = maBan;
	}

	public String getTenBan() {
		return TenBan;
	}

	public void setTenBan(String tenBan) {
		TenBan = tenBan;
	}

	public boolean isTinhTrang() {
		return TinhTrang;
	}

	public void setTinhTrang(boolean tinhTrang) {
		TinhTrang = tinhTrang;
	}
}
