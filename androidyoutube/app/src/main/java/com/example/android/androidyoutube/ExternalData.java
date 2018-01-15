package com.example.android.androidyoutube;

import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ExternalData extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private TextView canRead, canWrite;
    private String state;
    Boolean canW, canR;
    Spinner spinner;
    String[] paths = {"Music", "Pictures", "Downloads"};
    File path = null;
    File file = null;
    EditText saveFile;
    Button confirm, save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_data);

        canRead = (TextView) findViewById(R.id.tvCanRead);
        canWrite = (TextView) findViewById(R.id.tvCanWrite);
        saveFile = (EditText) findViewById(R.id.etSaveAs);
        confirm = (Button) findViewById(R.id.bConfirmSaveAs);
        save = (Button) findViewById(R.id.bSaveFile);
        saveFile = (EditText) findViewById(R.id.etSaveAs);
        spinner = (Spinner) findViewById(R.id.spin);

        confirm.setOnClickListener(this);
        save.setOnClickListener(this);

        checkState();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, paths);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    private void checkState() {
        state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            //read and write
            canRead.setText("True");
            canWrite.setText("True");
            canR = canW = true;
        } else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            canRead.setText("True");
            canWrite.setText("False");
            canR = true;
            canW = false;
        } else {
            canRead.setText("False");
            canWrite.setText("False");
            canR = canW = false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int pos = spinner.getSelectedItemPosition();
        switch (pos) {
            case 0:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                break;
            case 1:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                break;
            case 2:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSaveFile:
                String f = saveFile.getText().toString();
                file = new File(path, f + ".png");

                checkState();
                if (canW == canR == true) {
                    path.mkdirs();
                    try {
                        InputStream is = getResources().openRawResource(R.drawable.plushighlight);
                        OutputStream os = new FileOutputStream(file);
                        byte[] data = new byte[is.available()];
                        is.read(data);
                        os.write(data);
                        is.close();
                        os.close();

                        Toast.makeText(ExternalData.this, "file has been Saved", Toast.LENGTH_SHORT).show();

                        //update files for user to use
                        MediaScannerConnection.scanFile(ExternalData.this,
                                new String[]{file.toString()},
                                null,
                                new MediaScannerConnection.OnScanCompletedListener() {
                                    @Override
                                    public void onScanCompleted(String path, Uri uri) {
                                        Toast.makeText(ExternalData.this, "Scan complete", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                break;
            case R.id.bConfirmSaveAs:
                save.setVisibility(View.VISIBLE);
                break;
        }
    }
}
