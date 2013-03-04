package com.confluence.mychores;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import com.confluence.mychores.MainActivity.DatePickerNames;

public class DatePickerFragment extends DialogFragment implements
		DatePickerDialog.OnDateSetListener {

	private OnDateSetListenable activityToCallback;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	@Override
	public void onDateSet(DatePicker view, int year, int month, int day) {
		// Probably call back to the activity and fill up the text box
		String tag = this.getTag();
		DatePickerNames datePicker = DatePickerNames.valueOf(tag);
		activityToCallback.onDateSet(year, month, day, datePicker);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception
		try {
			activityToCallback = (OnDateSetListenable) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}
	}

	/**
	 * Must be implemented by the person interested in listening to Date set
	 * events.
	 * 
	 */
	public interface OnDateSetListenable {
		public void onDateSet(int year, int month, int day,
				DatePickerNames datePicker);
	}

}