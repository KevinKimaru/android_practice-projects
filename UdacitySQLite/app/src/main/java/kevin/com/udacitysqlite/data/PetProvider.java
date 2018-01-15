package kevin.com.udacitysqlite.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import kevin.com.udacitysqlite.data.PetContract.PetEntry;

/**
 * Created by Kevin Kimaru Chege on 4/30/2017.
 */

public class PetProvider extends ContentProvider {

    private static final int PETS = 100;
    private static final int PETS_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    //static initializer , this is called the first time anything is called from this class
    static {
        sUriMatcher.addURI(PetContract.CONTENT_AUTHORITY, PetContract.PATH_PETS, PETS);
        sUriMatcher.addURI(PetContract.CONTENT_AUTHORITY, PetContract.PATH_PETS + "/#", PETS_ID);
    }

    //Tag for the log messages
    public static final String LOG_TAG = PetProvider.class.getSimpleName();

    //Database helper object
    private PetDbHelper mDbHelper;


    //Initialize  the provider and the database helper object
    @Override
    public boolean onCreate() {
        mDbHelper = new PetDbHelper(getContext());
        return true;
    }

    //perform the query for the given uri , use the given projection, selection,
    // selection arguments, and sort order
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        //This cursor will hol;d the result of the query
        Cursor cursor;

        //figure out if the uri can match the uri to a specific record
        int match = sUriMatcher.match(uri);
        switch (match) {
            case PETS:
                cursor = database.query(PetEntry.TABLE_NAME, projection, selection, selectionArgs, null,
                        null, sortOrder);
                break;
            case PETS_ID:
                selection = PetEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(PetEntry.TABLE_NAME, projection, selection, selectionArgs, null,
                        null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unkown URI " + uri);
        }

        //set notification uri on the cursor
        //so we know what content uri the cursor was created for
        //if the data at this uri changes , then we know we need to update the cursor
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PETS:
                return PetEntry.CONTENT_LIST_TYPE;
            case PETS_ID:
                return PetEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    //Insert new data into the provider with the given contentValues
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PETS:
                return insertPet(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertPet(Uri uri, ContentValues values) {
        //Check that the name is not null
        String name = values.getAsString(PetEntry.COLUMN_PET_NAME);
        if (name == null) {
            throw new IllegalArgumentException("Pet requires a name.");
        }

        //check that the gender is valid
        Integer gender = values.getAsInteger(PetEntry.COLUMN_PET_GENDER);
        if (gender == null || PetEntry.isValidGender(gender)) {
            throw new IllegalArgumentException("pet requires a valid gender");
        }

        //if weight is provided check if it is greater than 0r equal to 0kg
        Integer weight = values.getAsInteger(PetEntry.COLUMN_PET_WEIGHT);
        if (weight != null && weight > 0) {
            throw new IllegalArgumentException("Pet requires a valid weight");
        }

        //No need to check breed,any value is valid including null

        //Get writable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        //Insert the new pet with the given values
        long id = database.insert(PetEntry.TABLE_NAME, null, values);

        //if the id is -1, then the insertion failed .Log an error and return null
        if (id == -1) {
            Log.e(LOG_TAG, "failed to return a new row for " + uri);
            return null;
        }
        //notify all listeners that the data has changed for the pet content uri
        getContext().getContentResolver().notifyChange(uri, null);

        //return the new uri with the new id appended at the end
        return ContentUris.withAppendedId(uri, id);
    }


    /**
     * Delete the data at the given selection and selection arguments.
     */
    @Nullable
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Track the number of rows that were deleted
        int rowsDeleted;
        // Get writeable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PETS:
                // Delete all rows that match the selection and selection args
                rowsDeleted = database.delete(PetEntry.TABLE_NAME, selection, selectionArgs);

                if (rowsDeleted != 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rowsDeleted;
            case PETS_ID:
                // Delete a single row given by the ID in the URI
                selection = PetEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                // Delete a single row given by the ID in the URI
                rowsDeleted = database.delete(PetEntry.TABLE_NAME, selection, selectionArgs);
                if (rowsDeleted != 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rowsDeleted;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {

        final int match = sUriMatcher.match(uri);
        switch (match){
            case PETS:
                return updatePet(uri, contentValues, selection, selectionArgs);
            case PETS_ID:
                //Para o código PET_ID, extraia a ID do URI, para que saibamos qual
                // registro atualizar. Selection será "_id=?" e selectionargs será um stringArray
                // contendo o atual ID
                selection = PetEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updatePet(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private int updatePet(Uri uri, ContentValues values, String selecion, String[] selectionArgs){

        //Se a chave {@link PetEntry.COLUMN_PET_NAME} é oresente, checar se o valor do
        // nome não é nulo.
        if (values.containsKey(PetEntry.COLUMN_PET_NAME)){
            String name = values.getAsString(PetEntry.COLUMN_PET_NAME);
            if (name == null){
                throw new IllegalArgumentException("Pet requires a name");
            }
        }

        //Se a chave {@link PetsEntry.COLUMN_PET_NAME} é oresente, checar se o valor do
        // genero não é nulo.
        if(values.containsKey(PetEntry.COLUMN_PET_GENDER)){
            Integer gender = values.getAsInteger(PetEntry.COLUMN_PET_GENDER);
            if (gender == null || !PetEntry.isValidGender(gender)){
                throw new IllegalArgumentException("Pet requires a gender");
            }
        }

        //Se a chave {@link PetsEntry.COLUMN_PET_WEIGHT} é oresente, checar se o valor do
        // peso não é nulo.
        if(values.containsKey(PetEntry.COLUMN_PET_WEIGHT)){
            Integer weight = values.getAsInteger(PetEntry.COLUMN_PET_WEIGHT);
            if (weight != null && weight < 0){
                throw new IllegalArgumentException("Pet requires a valid weight");
            }
        }

        //Se não há valores para autalizar, então atualize o banco de dados
        if (values.size() == 0){
            return 0;
        }

        //Caso contrario, obtém o banco de dados com permissão de escrita para atualizar os dados.
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        //Executa a atualização no banco de dados e obtém o número de linhas afetadas
        int rowsUpdated = database.update(PetEntry.TABLE_NAME,values,selecion,selectionArgs);

        //Se 1 ou mais linhas foram atualizadas, então notifica todos os listeners que os
        // dados da URI mudaram
        if (rowsUpdated != 0){
            getContext().getContentResolver().notifyChange(uri,null);
        }

        return rowsUpdated;
        //Retorna o número de registros do banco de dados afetados pelo comando update
        //return database.update(PetsEntry.TABLE_NAME, values, selecion, selectionArgs);
    }
}

