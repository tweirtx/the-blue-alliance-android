package com.thebluealliance.androidclient.test.datafeed;

import com.thebluealliance.androidclient.datafeed.APIv2;
import com.thebluealliance.androidclient.modules.DatafeedModule;

import org.junit.Before;

import retrofit.client.OkClient;

public abstract class AbstractAPIv2Test {

    APIv2 mApi;

    @Before
    public void setup() {
        mApi = DatafeedModule.getRestAdapter(new OkClient()).create(APIv2.class);
    }
}
