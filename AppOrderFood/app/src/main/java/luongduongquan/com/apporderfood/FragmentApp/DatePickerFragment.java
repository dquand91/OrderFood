package luongduongquan.com.apporderfood.FragmentApp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import luongduongquan.com.apporderfood.R;

/**
 * Created by User on 9/24/2017.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();

        int iNam = calendar.get(Calendar.YEAR);
        int iThang = calendar.get(Calendar.MONTH);
        int iNgay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),this,iNgay,iThang,iNam);
//        datePickerDialog.updateDate(calendar.getTime().getYear(), calendar.getTime().getMonth(), calendar.getTime().getDay());
        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        EditText edtNgaySinh = (EditText) getActivity().findViewById(R.id.edtNgaySinh);
        String ngaySinh = dayOfMonth + "/" + String.valueOf(month + 1) + "/" + dayOfMonth;
        edtNgaySinh.setText(ngaySinh);
    }


}
