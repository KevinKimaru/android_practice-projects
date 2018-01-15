package kevin.com.udacitysqlite;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import kevin.com.udacitysqlite.data.PetContract.PetEntry;
import kevin.com.udacitysqlite.data.PetDbHelper;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private PetDbHelper mDbHelper;
    private static final int PET_LOADER = 0;

    PetCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }

        });

        //mDbHelper = new PetDbHelper(this);
        //displayDatabaseInfo();

        //Find the listView which will be populated with the pet data\
        ListView petListView = (ListView) findViewById(R.id.list);
        //View and set empty view on the listView so that it only shows when the list has 0 items
        View emptyView = findViewById(R.id.empty_view);
        petListView.setEmptyView(emptyView);

        mCursorAdapter = new PetCursorAdapter(this, null);
        petListView.setAdapter(mCursorAdapter);

        petListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Create new pet to go to editor activity
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);

                //Form the current uri that represents the specific pet that was clicked on by
                //appending the id (pased as input to this method) onto the (@link PetEntry#CONTENT_URI)
                //for example the uri would be  "content:// com.android.pets/pets/2" if the pet with id 2 was clicked

                Uri currentPetUri = ContentUris.withAppendedId(PetEntry.CONTENT_URI, id);

                //set the uri on the data field of the intent
                intent.setData(currentPetUri);

                //launch the (@link editor activity to display the data for the current pet)
                startActivity(intent);
            }
        });

        //Kick off the loader
        getSupportLoaderManager().initLoader(PET_LOADER, null, this);

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        //displayDatabaseInfo();
//    }

    /**
     * Temporary helper method to to display information in the onscreen Textview
     * about the state of the pets database
     */

//    private void displayDatabaseInfo() {
////        To access our database , we instantiate our subclass  of SQLiteOpenHelper
//// and pass the context, which is the current activity
//
////        PetDbHelper mDbHelper = new PetDbHelper(this);
//
//        //Create and /or open database to read from it
//        SQLiteDatabase db = mDbHelper.getReadableDatabase();
//        //Perform this raw SQL query "SELECT*FROM pets" to get a cursor that contains all
//        // rows from the pets table
//        //Cursor cursor = db.rawQuery("SELECT * FROM " + PetEntry.TABLE_NAME, null);
//        String[] projection = {
//                PetEntry._ID,
//                PetEntry.COLUMN_PET_NAME,
//                PetEntry.COLUMN_PET_BREED,
//                PetEntry.COLUMN_PET_GENDER,
//                PetEntry.COLUMN_PET_WEIGHT,
//        };
//
////        Cursor cursor = db.query(
////                PetEntry.TABLE_NAME,
////                projection,
////                null,
////                null,
////                null,
////                null,
////                null
////        );
//        Cursor cursor = getContentResolver().query(
//                PetEntry.CONTENT_URI,  //The content uri of the words table
//                projection,  //The columns to return for each row
//                null,   //selection criteria
//                null,  //selection criteria
//                null);  //The sort order for the returned rows
//
//        ListView petlistView = (ListView) findViewById(R.id.list);
//
//        PetCursorAdapter cursorAdapter = new PetCursorAdapter(this, cursor);
//
//        petlistView.setAdapter(cursorAdapter);
//
//
////        TextView displayView = (TextView) findViewById(R.id.text_view_pet);
//
////        try {
////
////            displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
////            displayView.append(PetEntry._ID + " - " +
////                    PetEntry.COLUMN_PET_NAME + " - " +
////                    PetEntry.COLUMN_PET_BREED + " - " +
////                    PetEntry.COLUMN_PET_GENDER + " - " +
////                    PetEntry.COLUMN_PET_WEIGHT + "\n");
////
////            //figure out the index of each column
////            int idColumnIndex = cursor.getColumnIndex(PetEntry._ID);
////            int nameColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_NAME);
////            int breedColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_BREED);
////            int genderColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_GENDER);
////            int weightColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_WEIGHT);
////
////            //iterate through all the returned rows in the cursor
////            while (cursor.moveToNext()) {
////                //use that index to extraxt the string or int value of the word at the current row
////                //the cursor is in
////                int currentID = cursor.getInt(idColumnIndex);
////                String currentName = cursor.getString(nameColumnIndex);
////                String currentBreed = cursor.getString(breedColumnIndex);
////                int currentGender = cursor.getInt(genderColumnIndex);
////                int currentWeight = cursor.getInt(weightColumnIndex);
////
////                //display the values of each column of the current row  in the cursor in the text
////                // view
////                displayView.append("\n" + currentID + " - " +
////                        currentName + " - " +
////                        currentBreed + " - " +
////                        currentGender + " - " +
////                        currentWeight);
////            }
////        } finally {
////            //Always close the cursor when you are done reading from it. this releases all
////            // its resources and makes it invalid
////            cursor.close();
////        }
//    }

    private void insertPet() {
        //Gets the data respiritory in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(PetEntry.COLUMN_PET_NAME, "Toto");
        values.put(PetEntry.COLUMN_PET_BREED, "Terrier");
        values.put(PetEntry.COLUMN_PET_GENDER, PetEntry.GENDER_MALE);
        values.put(PetEntry.COLUMN_PET_WEIGHT, 7);

        long newRowId = db.insert(PetEntry.TABLE_NAME, null, values);

        Log.v("CatalogActivity", "New Row Id" + newRowId);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPet();
                //displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //Define a projection that specifies the columns from the table we care about
        String[] projection = {
                PetEntry._ID,
                PetEntry.COLUMN_PET_NAME,
                PetEntry.COLUMN_PET_BREED
        };

        //This loader will execute the contentprovider's query method on a background thread
        return new CursorLoader(this,  //Parent activity context
        PetEntry.CONTENT_URI, //Provider content uri to query
                projection,  //Columns to include in the resulting Cursor
                null,   //No selection clause
                null,   //No selectiom args
                null);      //Default sort order

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //update petCursor adapter  with this new cursor containing up[dated pet data
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        //callback when data needs to be deleted
        mCursorAdapter.swapCursor(null);

    }
}
