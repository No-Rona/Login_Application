package com.tinyowl.rohan.login_application;

import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

/**
 * Created by rohan on 27/07/15.
 */
public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity>{


    public MainActivityTests(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    /*
        Test cases for the Main Activity for the login-application.
     */

    @Test
    public void test_1_CheckActivityExists() {
        MainActivity mainActivity = getActivity();
        assertNotNull(mainActivity);

    }

    @Test
    public void test_2_CheckTextBoxStringValuesNotNull() {
        
    }

}
