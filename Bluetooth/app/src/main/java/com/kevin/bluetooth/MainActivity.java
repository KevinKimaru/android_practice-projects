package com.kevin.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Button on, off, visible, list;
    ImageView mImageView;
    BluetoothAdapter mBluetoothAdapter;
    ListView mListView;
    Set<BluetoothDevice> pairedDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        on = (Button) findViewById(R.id.on);
        off = (Button) findViewById(R.id.off);
        visible = (Button) findViewById(R.id.visible);
        list = (Button) findViewById(R.id.list);

        mListView = (ListView) findViewById(R.id.listView);

        mImageView = (ImageView) findViewById(R.id.image);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    }

    public void on(View view) {
        if (!mBluetoothAdapter.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(this, "Turned On", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Already On", Toast.LENGTH_SHORT).show();
        }
    }

    public void off(View view) {
        mBluetoothAdapter.disable();
        Toast.makeText(this, "Turned Off", Toast.LENGTH_SHORT).show();
    }

    public void visible(View view) {
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
        Toast.makeText(this, "Now discoverable", Toast.LENGTH_SHORT).show();
    }

    public void list(View view) {
        pairedDevices = mBluetoothAdapter.getBondedDevices();

        ArrayList arrayList = new ArrayList();
        for (BluetoothDevice bluetoothDevice: pairedDevices) {
            arrayList.add(bluetoothDevice.getName());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        mListView.setAdapter(arrayAdapter);
    }

    public void capture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 5);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 5 && resultCode == RESULT_OK) {
            super.onActivityResult(requestCode, resultCode, data);
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            mImageView.setImageBitmap(bitmap);

        }
    }
}
