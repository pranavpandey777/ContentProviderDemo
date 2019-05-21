package com.example.pranav.contentproviderdemo;

import android.net.Uri;

public class Contract {


    private Contract() {

    }

    /*
    Apps send requests to the content provider using content Uniform Resource
    Identifiers or URIs. A content URI for content providers has this general form:
   scheme://authority/path-to-data/dataset-name
   */


    public static final String AUTHORITY = "com.example.pranav.contentproviderdemo.provider";
    //in Authority first we have to write the package name and then ".provider"
    public static final String CONTENT_PATH = "words";
    //It can be any name but same as the name of the string array
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + CONTENT_PATH);
    public static final int ALL_ITEMS = -2;
    public static final String WORD_ID = "id";
    // (uniquely identifies the data set to search; such as a file name or table name)
    public static final String Single = "vnd.android.cursor.item/vnd.com.example.provider.words";
    //MIME (Multipurpose Internet Mail Extensions) type for single Item.
    public static final String Multi = "vnd.android.cursor.dir/vnd.com.example.provider.words";
    //MIME (Multipurpose Internet Mail Extensions) type for multiple Item.
}
