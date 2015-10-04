package br.org.carona.caronasolidaria.controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;

import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import java.util.ArrayList;
import java.util.List;

import br.org.carona.caronasolidaria.BuildConfig;
import br.org.carona.caronasolidaria.R;
import br.org.carona.caronasolidaria.rest.model.LoginModel;


public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {
    private static final String TAG = "LoginActivity";
    // Referências da view.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mEmailLoginFormView;
    private View mSignOutButtons;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        // Inicializando o formulário de login
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        showAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.integer.login || id == EditorInfo.IME_NULL) {
                    logar();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                logar();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        mEmailLoginFormView = findViewById(R.id.email_login_form);
        mSignOutButtons = findViewById(R.id.plus_sign_out_buttons);
    }

    private void showAutoComplete() {
        getLoaderManager().initLoader(0, null, this);
    }


    /**
     * Envia o formulário de login para o servidor. Valida os campos de login.
     */
    public void logar() {

        // reseta mensagens de erro
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Retira os valores do login.
        String email = mEmailView.getText().toString();
        String senha = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Valida o campo de senha
        if (!TextUtils.isEmpty(senha) && !isPasswordValid(senha)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Valida o campo de e-mail
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);

            LoginModel loginModel= new LoginModel();
            loginModel.setNome(email);
            loginModel.setSenha(senha);

            Ion.with(this).load("POST", BuildConfig.BASE_URL+"/users/login.json")
                    .setLogging("HTTP", Log.DEBUG)
                    .setJsonPojoBody(loginModel, new TypeToken<LoginModel>() {
                    }).asJsonObject().withResponse().setCallback(new FutureCallback<Response<JsonObject>>() {
                @Override
                public void onCompleted(Exception e, Response<JsonObject> result) {
                    showProgress(false);
                    try{
//                    Código http. 200 quando o resultado for ok
                        int code = result.getHeaders().code();
                        switch (code){
                            case 200:
                                Log.d(TAG, "login iniciado");
                                startActivity(new Intent(LoginActivity.this, MapaPrincipalActivity.class));
                                finish();
                                break;
                            case 400:
                                Log.d(TAG, "erro ao logar");
                                mensagemView(getString(R.string.login_invalido));
                                break;
                            default:
                                mensagemView(getString(R.string.erro_servidor));
                        }

                    }catch(Exception e1){
                        mensagemView(getString(R.string.erro_servidor));
                    }
                }

            });
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Validar de acordo com o email da fatec
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Validar a senha de acordo com critérios pré-definidos
        return password.length() > 4;
    }

    /**
     * Mostra a barra de progresso e esconde o formulário
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
                // for very easy animations. If available, use these APIs to fade-in
                // the progress spinner.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                    int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                    mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                            show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                        }
                    });

                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                    mProgressView.animate().setDuration(shortAnimTime).alpha(
                            show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                        }
                    });
                } else {
                    // The ViewPropertyAnimator APIs are not available, so simply show
                    // and hide the relevant UI components.
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            }
        });

    }

    /**
     * Mostra uma mensagem na view
     * @param mensagem
     */
    public void mensagemView(String mensagem){
        final String mMensagem= mensagem;
        runOnUiThread(new Runnable() {
            public void run() {
                Snackbar.make(findViewById(android.R.id.content), mMensagem, Snackbar.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retorna os dados de contato do usuário.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Seleciona somente o endereço de email.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Mostra o e-mail primário caso o usuário tenha específicado.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        if(cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                emails.add(cursor.getString(ProfileQuery.ADDRESS));
                cursor.moveToNext();
            }
        }
        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }


    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


}

