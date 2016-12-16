package com.example.projectflower;

import android.app.Dialog;
import android.app.Notification;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class Flower extends AppCompatActivity {
    SQLiteDatabase mDb;
    Database mHelper;
    Cursor mCursor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower);
        mHelper = new Database(this);
        mDb = mHelper.getWritableDatabase();

        mCursor = mDb.rawQuery("SELECT * FROM "
                + Database.TABLE_NAME, null);

        ArrayList<String> dirArray = new ArrayList<String>();

        mCursor.moveToFirst();
        while ( !mCursor.isAfterLast() ){
            dirArray.add(mCursor.getString(mCursor.getColumnIndex
                    (Database.COL_THNAME)));
            mCursor.moveToNext();
        }

        ListView listView1 = (ListView) findViewById(R.id.listView1);
        listView1.setAdapter(new ArrayAdapter(this
                , android.R.layout.simple_list_item_1, dirArray));
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1
                    , int arg2, long arg3) {
                mCursor.moveToPosition(arg2);
                Log.i("position", "" + arg2);

                myDialog();
            }
        });


    }

    private void myDialog() {

        final Dialog dialog = new Dialog(Flower.this);
        dialog.requestWindowFeature
                (dialog.getWindow().FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_data);


        TextView textTHName =
                (TextView) dialog.findViewById(R.id.textTHName);
        textTHName.setText("ชื่อ [TH] : "
                + mCursor.getString(mCursor.getColumnIndex
                (Database.COL_THNAME)));

        TextView textENName =
                (TextView) dialog.findViewById(R.id.textENName);
        textENName.setText("ชื่อ [EN] : "
                + mCursor.getString(mCursor.getColumnIndex
                (Database.COL_ENNAME)));

        TextView textMean =
                (TextView) dialog.findViewById(R.id.textMean);
        textMean.setText("ความหมาย [Mean] :  "
                + mCursor.getString(mCursor.getColumnIndex
                (Database.COL_MEAN)));

        String imageFileName = mCursor.getString(mCursor.getColumnIndex(Database.COL_IMAGE));
        Drawable drawable = getDrawableFromAssets(imageFileName);

        ImageView imageView = (ImageView) dialog.findViewById(R.id.dialog_image_view);
        imageView.setImageDrawable(drawable);

        Button buttonOK = (Button) dialog.findViewById(R.id.buttonOK);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        dialog.show();

    }

    public void onPause() {
        super.onPause();
        mHelper.close();
        mDb.close();
    }
    public Drawable getDrawableFromAssets(String pictureFilename) {
        AssetManager am = getAssets();

        try {
            // รูปภาพที่เราเตรียมไว้เองในโฟลเดอร์ assets
            InputStream stream = am.open(pictureFilename);
            Drawable drawable = Drawable.createFromStream(stream, null);
            return drawable;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}