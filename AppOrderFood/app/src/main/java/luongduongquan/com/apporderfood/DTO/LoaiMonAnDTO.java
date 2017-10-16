package luongduongquan.com.apporderfood.DTO;

/**
 * Created by luong.duong.quan on 10/12/2017.
 */

public class LoaiMonAnDTO {

	int MaLoai;
	String tenLoai;
	String hinhAnh;

	public int getMaLoai() {
		return MaLoai;
	}

	public void setMaLoai(int maLoai) {
		MaLoai = maLoai;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
}
