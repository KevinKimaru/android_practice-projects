package com.kevin.toyapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Recycler extends AppCompatActivity implements GreenAdapter.ListItemClickListener {

    private static final int NUM_LIST_ITEMS = 100;
    private GreenAdapter mAdapter;
    private RecyclerView mNumbersList;
    private Toast mToast;

    private GridLayoutManager mGridLayoutManager;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        mNumbersList = (RecyclerView) findViewById(R.id.rv_numbers);
        layoutManager = new LinearLayoutManager(this);
        mGridLayoutManager = new GridLayoutManager(this, 2);

        mNumbersList.setLayoutManager(layoutManager);
        mNumbersList.setHasFixedSize(false);
        mAdapter = new GreenAdapter(NUM_LIST_ITEMS, this);
        mNumbersList.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Toast.makeText(Recycler.this, "Don't get me wrong I'll be back @ " + position, Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(mNumbersList);

    }

    private void changeLayout() {
        if (mNumbersList.getLayoutManager().equals(layoutManager)) {
            mNumbersList.setLayoutManager(mGridLayoutManager);
        } else {
            mNumbersList.setLayoutManager(layoutManager);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recycler_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            mAdapter = new GreenAdapter(NUM_LIST_ITEMS, this);
            mNumbersList.setAdapter(mAdapter);
            return true;
        } else if (item.getItemId() == R.id.action_change_layout) {
            changeLayout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        if (mToast != null) {
            mToast.cancel();
        }

        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
        mToast.show();
    }
}
