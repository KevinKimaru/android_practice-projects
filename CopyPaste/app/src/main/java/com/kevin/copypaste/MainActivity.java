package com.kevin.copypaste;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText copyEt, pasteEt;
    ClipboardManager mClipboardManager;
    ClipData mClipData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        copyEt = (EditText) findViewById(R.id.copyEt);
        pasteEt = (EditText) findViewById(R.id.pasteEt);

        TextView textView1 = (TextView) findViewById(R.id.text1);
        TextView textView2 = (TextView) findViewById(R.id.text2);

        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Lobster-Regular.ttf");
        textView1.setTypeface(customFont);
        textView2.setTypeface(customFont);

        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

    }

    public void copy(View view) {
        String copied = copyEt.getText().toString();
        mClipData = ClipData.newPlainText("text", copied);
        mClipboardManager.setPrimaryClip(mClipData);
        Toast.makeText(this, "Text copied", Toast.LENGTH_SHORT).show();
    }

    public void paste(View view) {
        ClipData clipData = mClipboardManager.getPrimaryClip();
        ClipData.Item item = clipData.getItemAt(0);
        String pasted = item.getText().toString();
        pasteEt.setText(pasted);
        Toast.makeText(this, "Pasted", Toast.LENGTH_SHORT).show();
    }
}
