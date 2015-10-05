package br.org.carona.caronasolidaria.controller;

import android.annotation.TargetApi;
import android.os.Build;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import br.org.carona.caronasolidaria.BuildConfig;
import br.org.carona.caronasolidaria.CustomRobolectricRunner;
import br.org.carona.caronasolidaria.R;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Guilherme on 16/09/15.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(CustomRobolectricRunner.class)
public class CadastroUsuarioActivityTest {
    private CadastroUsuarioActivity activity;

    // @Before => JUnit 4 annotation that specifies this method should run before each test is run
    // Useful to do setup for objects that are needed in the test
    @Before
    public void setup() {
        // Convenience method to run MainActivity through the Activity Lifecycle methods:
        // onCreate(...) => onStart() => onPostCreate(...) => onResume()
        activity = Robolectric.setupActivity(CadastroUsuarioActivity.class);
        ShadowLog.stream = System.out;//Permite a chamada do Log.d dentro do console.
    }

    // @Test => JUnit 4 annotation specifying this is a test to be run
    // The test simply checks that our TextView exists and has the text "Hello world!"
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Test
    public void shouldPostUserOnServer() throws InterruptedException {
        EditText nome = (EditText)activity.findViewById(R.id.activity_cadastro_usuario_nome_et);
        EditText email = (EditText) activity.findViewById(R.id.activity_cadastro_usuario_email_et);
        EditText password = (EditText)activity.findViewById(R.id.activity_cadastro_usuario_senha_et);
        EditText confirma = (EditText)activity.findViewById(R.id.activity_cadastro_usuario_senha_confirma_et);

        nome.setText("teste");
        email.setText("email@fatec.com");
        password.setText("password");
        confirma.setText("password");

        activity.onValidationSucceeded();
        Robolectric.flushBackgroundThreadScheduler(); //Espera a thread ser completada
        assertEquals(false, Robolectric.getBackgroundThreadScheduler().areAnyRunnable());
        Thread.sleep(5000);
    }
}

