package br.org.carona.caronasolidaria.controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import br.org.carona.caronasolidaria.BuildConfig;
import br.org.carona.caronasolidaria.R;
import br.org.carona.caronasolidaria.rest.model.UserModel;

public class CadastroUsuarioActivity extends AppCompatActivity implements Validator.ValidationListener {
    private static final String TAG = "Cadastro Usuario";
    @NotEmpty(message = "É necessário um nome")
    private EditText nomeET;

    @NotEmpty(message = "É necessário o email institucional")
    @Email
    private EditText emailET;

    @NotEmpty(message = "É necessário a senha")
    @Password
    private EditText senhaET;

    @NotEmpty(message = "É necessário a confirmação de senha")
    @ConfirmPassword
    private EditText confirmaSenhaET;

    private Validator validator;
    private View mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        nomeET = (EditText)findViewById(R.id.activity_cadastro_usuario_nome_et);
        emailET = (EditText)findViewById(R.id.activity_cadastro_usuario_email_et);
        senhaET = (EditText)findViewById(R.id.activity_cadastro_usuario_senha_et);
        confirmaSenhaET = (EditText)findViewById(R.id.activity_cadastro_usuario_senha_confirma_et);
        mProgressView = findViewById(R.id.activity_cadastro_usuario_progress);

        validator = new Validator(this);
        validator.setValidationListener(this);
    }



    @Override
    public void onValidationSucceeded() {
        showProgress(true);
        UserModel userModel = new UserModel();
        UserModel.UserEntity entity=new UserModel.UserEntity();

        entity.setEmail(emailET.getText().toString());
        entity.setNome(nomeET.getText().toString());
        entity.setPassword(senhaET.getText().toString());

        userModel.setUser(entity);
//       @see https://github.com/koush/ion
        Ion.with(this).load("POST", BuildConfig.BASE_URL+"/users/add.json")
                .setLogging("HTTP", Log.DEBUG)
                .setJsonPojoBody(userModel, new TypeToken<UserModel>() {})
                .asJsonObject().withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
            @Override
            public void onCompleted(Exception e, Response<JsonObject> result) {
                showProgress(false);
                try {
//                    Código http. 200 quando o resultado for ok
                    int code = result.getHeaders().code();
                    switch (code) {
                        case 200:
                            Log.d(TAG, "login iniciado");
                            showMessage("Usuário cadastrado com sucesso");
                            finish();
                            break;
                        case 400: //400 quando houver erro
                            Log.d(TAG, "erro ao logar");
                            showMessage("Problemas ao cadastrar o usuário");
                            break;
                        default:
                            showMessage("Não foi possível cadastrar o usuário");
                    }

                } catch (Exception e1) {
                    Log.e(TAG, "Erro ao cadastrar o usuário");
                    showMessage(getString(R.string.erro_servidor));
                }
            }

        });

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                showMessage(message);
            }
        }
    }

    public void cancelarOnclick(View view) {
        showProgress(false);
        finish();
    }

    public void cadastroOnclick(View view) {
        validator.validate();
    }

    private void showMessage(final String message){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                    int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
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
                }
            }

        });
    }
}
