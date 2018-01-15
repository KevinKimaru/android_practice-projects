package info.androidhive.sqlite.model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class Tag {

    int id;
    String tag_name;

    // constructors
    public Tag() {

    }

    public Tag(String tag_name) {
        this.tag_name = tag_name;
    }

    public Tag(int id, String tag_name) {
        this.id = id;
        this.tag_name = tag_name;
    }

    // setter
    public void setId(int id) {
        this.id = id;
    }

    public void setTagName(String tag_name) {
        this.tag_name = tag_name;
    }

    // getter
    public int getId() {
        return this.id;
    }

    public String getTagName() {
        return this.tag_name;
    }

    public long createTag(Tag tag) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TAG_NAME, tag.getTagName());
        values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long tag_id = db.insert(TABLE_TAG, null, values);

        return tag_id;
    }
}