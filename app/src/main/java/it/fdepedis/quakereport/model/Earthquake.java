/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package it.fdepedis.quakereport.model;

import java.io.Serializable;

/**
 * An {@link Earthquake} object contains information related to a single earthquake.
 */
public class Earthquake implements Serializable {

    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliseconds;
    private String mUrl;

    private long mUpdateInMilliseconds; //1604505522187
    private String mTZ; //null
    private String mDetail; //"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000caai&format=geojson"
    private String mFelt; //null
    private String mCDI; //null
    private double mMMI; //0
    private String mAlert; //"green"
    private String mStatus; //"reviewed"
    private double mTsunami; //0
    private double mSIG; //432
    private String mNET; //"us"
    private String mCode; //"7000caai"
    private String mIDS; //",us7000caai,"
    private String mSources; //",us,"
    private double mDMIN; //25.281
    private double mRMS; //0.64
    private double mGAP; //112
    private String mMagType; //"mww"
    private String mType; //"earthquake"
    private String mTitle; //"M 5.3 - southeast of Easter Island"
    private String mCoord0; //secondo valore per le coordinate
    private String mCoord1; //primo valore per le coordinate
    private String mDeep; //profondità

    public Earthquake(double magnitude, String location, long timeInMilliseconds, String url
                      ,long updateInMilliseconds, String tz, String detail, String felt
                      ,String cdi, double mmi, String alert, String status, double tsunami
                      ,double sig, String net, String code, String ids, String sources
                      ,double dmin, double rms, double gap, String magType, String type
                      ,String title, String coord0, String coord1, String deep) {
        this.mMagnitude = magnitude;
        this.mLocation = location;
        this.mTimeInMilliseconds = timeInMilliseconds;
        this.mUrl = url;
        this.mUpdateInMilliseconds = updateInMilliseconds;
        this.mTZ = tz;
        this.mDetail = detail;
        this.mFelt = felt;
        this.mCDI = cdi;
        this.mMMI = mmi;
        this.mAlert = alert;
        this.mStatus = status;
        this.mTsunami = tsunami;
        this.mSIG = sig;
        this.mNET = net;
        this.mCode = code;
        this.mIDS = ids;
        this.mSources = sources;
        this.mDMIN = dmin;
        this.mRMS = rms;
        this.mGAP = gap;
        this.mMagType = magType;
        this.mType = type;
        this.mTitle = title;
        this.mCoord0 = coord0;
        this.mCoord1 = coord1;
        this.mDeep = deep;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getUrl() {
        return mUrl;
    }

    public long getmUpdateInMilliseconds() {
        return mUpdateInMilliseconds;
    }

    public String getTz() {
        return mTZ;
    }

    public String getDetail() {
        return mDetail;
    }

    public String getFelt() {
        return mFelt;
    }

    public String getCdi() {
        return mCDI;
    }

    public double getMmi() {
        return mMMI;
    }

    public String getAlert() {
        return mAlert;
    }

    public String getStatus() {
        return mStatus;
    }

    public double getTsunami() {
        return mTsunami;
    }

    public double getSig() {
        return mSIG;
    }

    public String getNet() {
        return mNET;
    }

    public String getCode() {
        return mCode;
    }

    public String getIds() {
        return mIDS;
    }

    public String getSources() {
        return mSources;
    }

    public double getDmin() {
        return mDMIN;
    }

    public double getRms() {
        return mRMS;
    }

    public double getGap() {
        return mGAP;
    }

    public String getMagType() {
        return mMagType;
    }

    public String getType() {
        return mType;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getCoord0() {
        return mCoord0;
    }

    public String getCoord1() {
        return mCoord1;
    }

    public String getDeep() {
        return mDeep;
    }
}
