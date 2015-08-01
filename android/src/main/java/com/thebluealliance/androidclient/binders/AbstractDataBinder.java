package com.thebluealliance.androidclient.binders;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thebluealliance.androidclient.datafeed.DataConsumer;
import com.thebluealliance.androidclient.models.NoDataViewParams;

/**
 * A class that takes in input data model and updates views accordingly
 * For now, declare views as public members and set them elsewhere where the view is created
 * (like {@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}).
 * @param <T>
 */
public abstract class AbstractDataBinder<T> implements DataConsumer<T> {
    Activity mActivity;
    NoDataBinder mNoDataBinder;
    NoDataViewParams mNoDataParams;

    private boolean mIsDataBound;

    public AbstractDataBinder() {
        mIsDataBound = false;
    }

    public void setActivity(Activity activity) {
        mActivity = activity;
    }

    public void setNoDataBinder(NoDataBinder binder) {
        mNoDataBinder = binder;
    }

    public void setNoDataParams(NoDataViewParams params) {
        mNoDataParams = params;
    }

    public boolean getIsDataBound() {
        return mIsDataBound;
    }

    public void setDataBound() {
        this.mIsDataBound = true;
    }

    public void setNoDataTextView(TextView textView) {
        mNoDataBinder.setTextView(textView);
    }

    public void setNoDataImageView(ImageView imageView) {
        mNoDataBinder.setImageView(imageView);
    }
}