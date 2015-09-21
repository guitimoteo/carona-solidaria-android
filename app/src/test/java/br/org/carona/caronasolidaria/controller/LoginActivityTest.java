package br.org.carona.caronasolidaria.controller;

import android.os.Build;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import br.org.carona.caronasolidaria.BuildConfig;
import br.org.carona.caronasolidaria.CustomRobolectricRunner;
import br.org.carona.caronasolidaria.R;


import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
/**
 * Created by Guilherme on 16/09/15.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(CustomRobolectricRunner.class)
public class LoginActivityTest {
    private LoginActivity activity;

    // @Before => JUnit 4 annotation that specifies this method should run before each test is run
    // Useful to do setup for objects that are needed in the test
    @Before
    public void setup() {
        // Convenience method to run MainActivity through the Activity Lifecycle methods:
        // onCreate(...) => onStart() => onPostCreate(...) => onResume()
        activity = Robolectric.setupActivity(LoginActivity.class);
        ShadowLog.stream = System.out;//Permite a chamada do Log.d dentro do console.
    }

    // @Test => JUnit 4 annotation specifying this is a test to be run
    // The test simply checks that our TextView exists and has the text "Hello world!"
    @Test
    public void shouldLoginOnServer() {
        AutoCompleteTextView email = (AutoCompleteTextView) activity.findViewById(R.id.email);
        EditText password = (EditText)activity.findViewById(R.id.password);
        assertNotNull("TextView could not be found", email);
        email.setText("email@example.com");
        password.setText("password");
        activity.logar();
        Robolectric.flushBackgroundThreadScheduler(); //Espera a thread ser completada

    }
}

