package br.edu.ifsp.dmos5.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.dao.UserDAO;
import br.edu.ifsp.dmos5.view.constants.Constants;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usuarioEt;
    private EditText senhaEt;
    private Button loginBtn;
    private Button novoUsuarioBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFindViewById();
        onClickListener();
        UserDAO.getInstance();
    }

    @Override
    public void onClick(View view) {
        if(view == loginBtn){
            openContactsActivity();
        } else if(view == novoUsuarioBtn){
            openNewUserActivity();
        }
    }

    private void onClickListener() {
        loginBtn.setOnClickListener(this);
        novoUsuarioBtn.setOnClickListener(this);
    }

    private void setFindViewById() {
        usuarioEt = findViewById(R.id.username_et);
        senhaEt = findViewById(R.id.senha_et);
        loginBtn = findViewById(R.id.login_btn);
        novoUsuarioBtn = findViewById(R.id.novoUsuario_btn);
    }

    private void openContactsActivity() {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.USER,getValueEditText(usuarioEt));
        bundle.putString(Constants.PASSWORD,getValueEditText(senhaEt));

        Intent intent = new Intent(this, ContactsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void openNewUserActivity() {
        Intent intent = new Intent(this, NewUserActivity.class);
        startActivity(intent);
    }

    private String getValueEditText(EditText et){
        return et.getText().toString();
    }

}