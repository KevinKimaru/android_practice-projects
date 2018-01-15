package com.example.android.androidyoutube;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQlite1 extends AppCompatActivity implements View.OnClickListener {

    Button sqlUpdate, sqlView, sqlModify, sqlGetInfo, sqlDelete;
    EditText sqlName, sqlHotness, sqlRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite1);

        sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
        sqlView = (Button) findViewById(R.id.bSQLOpenView);
        sqlName = (EditText) findViewById(R.id.etSQLname);
        sqlHotness = (EditText) findViewById(R.id.etSQLHotness);

        sqlRow = (EditText) findViewById(R.id.etSQLRowInfo);
        sqlModify = (Button) findViewById(R.id.bSQLModify);
        sqlGetInfo = (Button) findViewById(R.id.bSQLGetInfo);
        sqlDelete = (Button) findViewById(R.id.bSQLdelete);

        sqlModify.setOnClickListener(this);
        sqlGetInfo.setOnClickListener(this);
        sqlDelete.setOnClickListener(this);


        sqlUpdate.setOnClickListener(this);
        sqlView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSQLUpdate:
                boolean didItWork = true;
                try {
                    String name = sqlName.getText().toString().trim();
                    String hotness = sqlHotness.getText().toString().trim();

                    HotOrNot entry = new HotOrNot(SQlite1.this);
                    entry.open();
                    entry.createEntry(name, hotness);
                    entry.close();
                } catch (Exception e) {
                    didItWork = false;
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Oops!");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                } finally {
                    if (didItWork) {
                        Dialog d = new Dialog(this);
                        d.setTitle("Hello!");
                        TextView tv = new TextView(this);
                        tv.setText("Success");
                        d.setContentView(tv);
                        d.show();
                    }
                }

                break;
            case R.id.bSQLOpenView:
                Intent intent = new Intent(SQlite1.this, SQLView.class);
                startActivity(intent);
                break;

            case R.id.bSQLGetInfo:
                try {
                    String s = sqlRow.getText().toString().trim();
                    long l = Long.parseLong(s);
                    HotOrNot h = new HotOrNot(this);
                    h.open();
                    String returnedName = h.getName(l);
                    String returnedHotness = h.getHotness(l);
                    h.close();

                    sqlName.setText(returnedName);
                    sqlHotness.setText(returnedHotness);
                } catch (Exception e) {
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Oops!");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }
                break;

            case R.id.bSQLModify:
                try {
                    String name = sqlName.getText().toString().trim();
                    String hotness = sqlHotness.getText().toString().trim();
                    String sRow = sqlRow.getText().toString().trim();
                    long lRow = Long.parseLong(sRow);

                    HotOrNot ex = new HotOrNot(this);
                    ex.open();
                    ex.updateEntry(lRow, name, hotness);
                    ex.close();
                } catch (Exception e) {
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Oops!");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }
                break;

            case R.id.bSQLdelete:
                try {
                    String sD = sqlRow.getText().toString().trim();
                    long lD = Long.parseLong(sD);
                    HotOrNot hD = new HotOrNot(this);
                    hD.open();
                    hD.deleteEntry(lD);
                    hD.close();
                } catch (Exception e) {
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Oops!");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }
                break;
        }
    }
}
