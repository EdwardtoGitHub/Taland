package com.dragon.navigation.Control;

import android.location.Location;

import com.dragon.navigation.Model.SearchpoiEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EdwardPC on 2016/10/13.
 */
public class Data {
    public static float[] ProjectionMatrix={
            0.0f , -1.69032f ,  0.0f  , 0.0f,
           -3.0050135f ,  0.0f ,  0.0f  , 0.0f,
            0.0f ,  -0.0015625f  , 1.004008f ,  1.0f,
           0.0f ,  0.0f  , -20.040081f ,  0.0f
        };
   public static float[] modelViewMatrix=  {
           0, -1, 0, 0,
            -1, 0, 0, 0,
            0, 0, -1.0f, 0,
            0, 0, 4000, 1};
    public static int degree;
    public static boolean getfirstdegree=false;
    public static float firstdegree;
    public static float predegree;
    public static boolean modelDrawed=false;


    public static float movedistance;
    public static int alreadymove=0;

    public static Location locationdes;
    public static Location locationhere;
    public static float bearing;

    public static float currentAzimuth=0;

   public static volatile List<SearchpoiEntity> AroundpoiList = new ArrayList<>();
    public static int poinum=0;
    public static List<SearchpoiEntity> SearchpoiList = new ArrayList<>();

    public static volatile int SelectArroundId=-1;//默认不被选择
    public static boolean IsSelectArround=false;
}
