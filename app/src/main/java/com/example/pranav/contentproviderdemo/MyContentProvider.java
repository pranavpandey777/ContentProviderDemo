package com.example.pranav.contentproviderdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import static java.lang.Integer.parseInt;

public class MyContentProvider extends ContentProvider {


    String[] mData;
    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);


    @Override
    public boolean onCreate() {
        init();
        Context context = getContext();
        mData = context.getResources().getStringArray(R.array.words);
        return true;
    }

    private void init() {


        uriMatcher.addURI(Contract.AUTHORITY, Contract.CONTENT_PATH + "/#", 1);
//return single word
        uriMatcher.addURI(Contract.AUTHORITY, Contract.CONTENT_PATH, 0);
//return all words

    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int id=-1;

        switch (uriMatcher.match(uri)) {
            case 0:

                id = Contract.ALL_ITEMS;

                if (selection != null) {
                    id = parseInt(selectionArgs[0]);
                }
                break;
            case 1:

                id = parseInt(uri.getLastPathSegment());

                break;
            case UriMatcher.NO_MATCH:


                id = -1;
                break;
            default:

                id = -1;
        }
        return populateCursor(id);
    }

    private Cursor populateCursor(int id) {

        MatrixCursor cursor = new MatrixCursor(new String[]{Contract.CONTENT_PATH});
        if (id == Contract.ALL_ITEMS) {
            for (int i = 0; i < mData.length; i++) {
                String word = mData[i];
                cursor.addRow(new Object[]{word});
            }
        } else if (id >= 0) {
            String word = mData[id];
            cursor.addRow(new Object[]{word});
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
     /*  switch (uriMatcher.match(uri)) {
            case 0:
                return Contract.Multi;
            case 1:
                return Contract.Single;
            default:
*/
                return null;
        }

   // }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
