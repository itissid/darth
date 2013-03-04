package com.confluence.mychores.utils;

import org.joda.time.Days;
import org.joda.time.Instant;
import org.joda.time.Months;
import org.joda.time.Years;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.mychores.R;

public class Utils {
	public ArrayAdapter<CharSequence> getOptions(Context activity,
			Instant start, Instant end) {
		if (Days.daysBetween(start, end).isLessThan(Days.ZERO)) {
			// Days are wrongly inputted. Error!
			return null;
		} else if (Days.daysBetween(start, end).equals(Days.ZERO)) {
			// start == end
			// Display no choices. This is a one time task.
			ArrayAdapter<CharSequence> adapter = ArrayAdapter
					.createFromResource(activity, R.array.choice_array0,
							android.R.layout.simple_spinner_dropdown_item);
			return adapter;

		} else if (Months.monthsBetween(start, end).equals(Months.ZERO)) {
			// start - end > 0 day but the "whole month" boundary is not
			// crossed. Whole month boundary means it makes sense to show the
			// choices of
			// 1) Every day 2) On Days...
			// But it does not make sense to show the choice of "Day of month"
			// An example would be:
			// Feb 14th 2013 to March 13th 2013 which would be 27 days but not a
			// thus there is no day of the month when the task can be
			// repeatable.
			ArrayAdapter<CharSequence> adapter = ArrayAdapter
					.createFromResource(activity, R.array.choice_array1,
							android.R.layout.simple_spinner_dropdown_item);
			return adapter;
		} else if (Years.yearsBetween(start, end).equals(Years.ZERO)) {
			// different months but in the same year
			ArrayAdapter<CharSequence> adapter = ArrayAdapter
					.createFromResource(activity, R.array.choice_array2,
							android.R.layout.simple_spinner_dropdown_item);
			return adapter;
		} else if (Years.yearsBetween(start, end).isGreaterThan(Years.ZERO)) {
			// Different year
			ArrayAdapter<CharSequence> adapter = ArrayAdapter
					.createFromResource(activity, R.array.choice_array3,
							android.R.layout.simple_spinner_dropdown_item);
			return adapter;
		} else {
			return null;
		}
	}
}
