package com.example.idenuncia.idenuncia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.idenuncia.idenuncia.services.WebTaskCadastroUsuario;


public class CadastroFragment extends Fragment {
    private WebTaskCadastroUsuario mCadTask = null;
    private EditText mNomeView;
    private EditText mUsernameView;
    private EditText mEmailView;
    private EditText mPasswordView;

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cadastro, container, false);

        mNomeView = (EditText) rootView.findViewById(R.id.nome);
        mUsernameView = (EditText) rootView.findViewById(R.id.username);
        mEmailView = (EditText) rootView.findViewById(R.id.email);
        mPasswordView = (EditText) rootView.findViewById(R.id.senha);

        rootView.findViewById(R.id.btn_cadastrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptCad(getView());
            }
        });

        return rootView;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    public void attemptCad(View v) {
        String nome = mNomeView.getText().toString();
        String username = mUsernameView.getText().toString();
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mCadTask = new WebTaskCadastroUsuario(getContext(), nome, username, password, email);
            mCadTask.execute();
        }
    }
}