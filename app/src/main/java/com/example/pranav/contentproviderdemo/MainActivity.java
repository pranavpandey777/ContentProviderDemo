package com.example.pranav.contentproviderdemo;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
TextView area;
Button single,multi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        area=findViewById(R.id.area);
        single=findViewById(R.id.single);
        multi=findViewById(R.id. multi);

        single.setOnClickListener(this);
        multi.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        String queryUri = Contract.CONTENT_URI.toString();


        String[] projection = new String[]{Contract.CONTENT_PATH};


        String selectionClause;

        String selectionArgs[];

        String sortOrder = null;


        switch (view.getId()) {
            case R.id.multi:
                area.setText("");
                selectionClause = null;
                selectionArgs = null;
                break;
            case R.id.single:
                area.setText("");
                selectionClause = Contract.WORD_ID + " = ?";
                selectionArgs = new String[]{"0"};
                break;
            default:
                selectionClause = null;
                selectionArgs = null;
        }


        Cursor cursor = getContentResolver().query(Uri.parse(queryUri), projection, selectionClause, selectionArgs, sortOrder);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int index = cursor.getColumnIndex(projection[0]);
                String word = cursor.getString(index);
                area.append(word + "\n");
            }
        } else {

            area.append("No data" + "\n");


        }
    }

}
