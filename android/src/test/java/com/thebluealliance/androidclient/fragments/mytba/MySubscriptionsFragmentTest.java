package com.thebluealliance.androidclient.fragments.mytba;

import com.thebluealliance.androidclient.IntegrationRobolectricRunner;
import com.thebluealliance.androidclient.fragments.framework.FragmentTestDriver;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(IntegrationRobolectricRunner.class)
public class MySubscriptionsFragmentTest {

    MySubscriptionsFragment mFragment;

    @Before
    public void setUp() {
        mFragment = MySubscriptionsFragment.newInstance();
    }

    @Test
    public void testLifecycle() {
        FragmentTestDriver.testLifecycle(mFragment);
    }
}