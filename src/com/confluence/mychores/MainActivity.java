package com.confluence.mychores;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicReference;

import org.joda.time.DateTime;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.example.mychores.R;

public class MainActivity extends Activity implements
		DatePickerFragment.OnDateSetListenable {
	private final AtomicReference<Calendar> lastFromDate = new AtomicReference<Calendar>();
	private final AtomicReference<Calendar> lastToDate = new AtomicReference<Calendar>();

	public static enum DatePickerNames {
		FROM_DATE_PICKER, TO_DATE_PICKER;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		View findViewById = findViewById(R.id.from_date_text);
		findViewById.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/**
	 * Call backs from the framework to fillin the dates
	 * 
	 * @param v
	 */
	public void fromDateDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();

		newFragment.show(getFragmentManager(),
				DatePickerNames.FROM_DATE_PICKER.name());
	}

	public void toDateDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(),
				DatePickerNames.TO_DATE_PICKER.name());
	}

	// Call back when a date is selected
	@Override
	public void onDateSet(int year, int month, int day,
			DatePickerNames datePicker) {
		EditText findViewById = null;
		switch (datePicker) {
		case FROM_DATE_PICKER:
			findViewById = (EditText) findViewById(R.id.from_date_text);
			setDate(year, month, day, findViewById);
			lastFromDate.set(getCalendar(year, month, day));
			break;
		case TO_DATE_PICKER:
			findViewById = (EditText) findViewById(R.id.to_date_text);
			setDate(year, month, day, findViewById);
			lastToDate.set(getCalendar(year, month, day));
			break;
		}
		int choiceArray = R.array.choice_array;
		// Here do validation on dates to display notifications
		// Also display the options on this section properly.

	}

	private void setDate(int year, int month, int day, EditText textView) {
		DateTime date = new DateTime().withDate(year, month, day);

		Calendar cal = getCalendar(year, month, day);
		CharSequence format = DateFormat.format("MMM dd, yyyy", cal);
		textView.setText(format);
	}

	private Calendar getCalendar(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		return cal;
	}
}
