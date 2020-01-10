package com.alenva.lennkentest.Util;

import com.google.android.gms.maps.model.BitmapDescriptor;

import java.util.Random;

public class Constants {
    private BitmapDescriptor[] iconColors;
    public static  int randomInt (int max, int min){

        return new Random().nextInt(max - min ) + min;
    }




}
