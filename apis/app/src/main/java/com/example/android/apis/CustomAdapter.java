package com.example.android.apis;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class CustomAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<User> data;//modify here
    public CustomAdapter(Context context, ArrayList<User> data) //modify here
    {
        this.mContext = context;
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();// # of items in your arraylist
    }
    @Override
    public Object getItem(int position) {
        return data.get(position);// get the actual movie
    }
    @Override
    public long getItemId(int id) {
        return id;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_layout, parent,false);//modify here
            viewHolder = new ViewHolder();
            //modify this block of code
            viewHolder.tvNames = (TextView) convertView.findViewById(R.id.tvNames);
            viewHolder.tvEmail = (TextView) convertView.findViewById(R.id.tvEmail);
            viewHolder.tvAge = (TextView) convertView.findViewById(R.id.tvAge);
            viewHolder.dotted = (ImageView) convertView.findViewById(R.id.menuImg);
            //viewHolder.imageViewDish=(ImageView) convertView.findViewById(R.id.imageViewDish);
            //Up to here
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        User x = data.get(position);
        viewHolder.tvNames.setText(x.getNames());
        viewHolder.tvEmail.setText(x.getEmail());
        viewHolder.tvAge.setText(x.getAge() + "");

        viewHolder.dotted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //popup menu
                PopupMenu menu = new PopupMenu(mContext, v);
                menu.getMenuInflater().inflate(R.menu.item_menu, menu.getMenu());

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getTitle().equals("Delete")) {
                            //delete
                            delete(position);
                        } else if (item.getTitle().equals("Edit")) {
                            //edit
                            edit(position);
                        }
                        return true;
                    }
                });
                menu.show();
            }
        });
        //MODIFY THIS BLOCK OF CODE
        //Dish b = data.get(position);//modify here
        //viewHolder.textViewTitle.setText(b.getTitle());//modify here
        //viewHolder.imageViewDish.setImageResource(b.getImage());
        return convertView;

    }

    private void edit(int position) {
        User u = data.get(position);
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_layout, null);

        AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
        alertDialog.setTitle("Edit User");
        alertDialog.setCancelable(false);

        final int id = u.getId();

        final EditText edtNames = (EditText) view.findViewById(R.id.names_input);
        edtNames.setText(u.getNames());
        final EditText edtEmail = (EditText) view.findViewById(R.id.email_input);
        edtEmail.setText(u.getEmail());
        final EditText edtAge = (EditText) view.findViewById(R.id.age_input);
        edtAge.setText(u.getAge() + "");

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String names = edtNames.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String age = edtAge.getText().toString().trim();

                if (names.isEmpty() || email.isEmpty() || age.isEmpty()) {
                    Toast.makeText(mContext, "Fill in all values", Toast.LENGTH_SHORT).show();
                } else {
                    update(names, email, age, id);
                }
            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.setView(view);
        alertDialog.show();
    }

    private void update(String names, String email, String age, int id) {
        String url = "http://kevinkimaru.pythonanywhere.com/update/" + id;
        RequestParams params = new RequestParams();
        params.put("names", names);
        params.put("email", email);
        params.put("age", Integer.parseInt(age));

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                Toast.makeText(mContext, "Failed to update. Try again", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int i, Header[] headers, String s) {
                Toast.makeText(mContext, "Server said " + s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void delete(final int position) {
        User x = data.get(position);
        int id = x.getId();
        String url = "http://kevinkimaru.pythonanywhere.com/delete/" + id;
        AsyncHttpClient client = new AsyncHttpClient();
        client.delete(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                Toast.makeText(mContext, "Server is down", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int i, Header[] headers, String s) {
                Toast.makeText(mContext, "" + s, Toast.LENGTH_SHORT).show();
                Log.d("RESPONSE", "SERVER SAID " + s);
                if (s.contains("Succ")) {
                    data.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
    }

    static class ViewHolder {
        TextView tvNames;
        TextView tvEmail;
        TextView tvAge;
        ImageView dotted;
        //MODIFY THIS BLOCK OF CODE
        //TextView textViewTitle;
        //ImageView imageViewDish;
    }

}