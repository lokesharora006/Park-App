package com.example.park.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Util {

    public static final String PARKS_URL = "https://developer.nps.gov/api/v1/parks?parkCode1=acad&api_key=GRdc8ChLdowEF8kiWbHXgEKVS9hgtoBsIEvNGjHG";


    public static void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }

    public static String getParksUrl(String stateCode) {
        return "https://developer.nps.gov/api/v1/parks?stateCode=" + stateCode + "&api_key=sptqFvukvLzopBhlCAWusyNUI5wPgv28a3Gip6xp";
    }

}
