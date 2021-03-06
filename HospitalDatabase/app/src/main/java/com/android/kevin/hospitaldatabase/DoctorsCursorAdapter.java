package com.android.kevin.hospitaldatabase;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.android.kevin.hospitaldatabase.data.HospitalContract;

/**
 * Created by Kevin Kimaru Chege on 4/30/2017.
 */

public class DoctorsCursorAdapter {

    public DoctorsCursorAdapter() {}


    public static final class SelectedDoctorsCursor extends CursorAdapter {
        public SelectedDoctorsCursor(Context context, Cursor c) {
            super(context, c, 0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView nameTextView = (TextView) view.findViewById(R.id.name);
            TextView summaryTextView = (TextView) view.findViewById(R.id.summary);

            int nameColumnIndex = cursor.getColumnIndex(HospitalContract.DoctorsEntry.DOCTOR_NAME);
            int specializationColumnIndex = cursor.getColumnIndex(HospitalContract.DoctorsEntry.DOCTOR_SPECIALIZATION);

            String doctorName = cursor.getString(nameColumnIndex);
            String doctorSpecialization = cursor.getString(specializationColumnIndex);

            nameTextView.setText(doctorName);
            summaryTextView.setText(doctorSpecialization);



        }
    }


    public static final class AllDoctorsCursor extends CursorAdapter {
        public AllDoctorsCursor(Context context, Cursor c) {
            super(context, c, 0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView nameTextView = (TextView) view.findViewById(R.id.name);
            TextView summaryTextView = (TextView) view.findViewById(R.id.summary);

            int nameColumnIndex = cursor.getColumnIndex(HospitalContract.DoctorsEntry.DOCTOR_NAME);
            int specializationColumnIndex = cursor.getColumnIndex(HospitalContract.DoctorsEntry.DOCTOR_SPECIALIZATION);

            String doctorName = cursor.getString(nameColumnIndex);
            String doctorSpecialization = cursor.getString(specializationColumnIndex);

            nameTextView.setText(doctorName);
            summaryTextView.setText(doctorSpecialization);



        }
    }

}
