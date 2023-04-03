package br.edu.ifsp.dmos5.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.criptografia.MD5;
import br.edu.ifsp.dmos5.dao.UserDAO;
import br.edu.ifsp.dmos5.model.User;

public class NewUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usuario_et;
    private EditText senha_et;
    private EditText confSenha_et;
    private Button salvar_btn;
    private UserDAO db = UserDAO.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setFindViewById();
        onClickListener();
    }

    private void onClickListener() {
        salvar_btn.setOnClickListener(this);
    }

    private void setFindViewById() {
        usuario_et = findViewById(R.id.username);
        senha_et = findViewById(R.id.senha);
        confSenha_et = findViewById(R.id.confirmacaoSenha);
        salvar_btn = findViewById(R.id.salvarUser);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view == salvar_btn){
            salvarUsuario();
        }
    }

    private void salvarUsuario() {
        List<User> users = db.getUsers();
        String usuario = getValueEditText(usuario_et);
        String senha = getValueEditText(senha_et);
        String confSenha = getValueEditText(confSenha_et);

        if(!senha.equals(confSenha)){
            Toast.makeText(this, R.string.senhas_diferentes, Toast.LENGTH_SHORT).show();
            return;
        }

        if(!users.isEmpty()){

            for(User l : users){
                if(l.getUsername().equals(usuario)){
                    Toast.makeText(this, R.string.usuario_existente, Toast.LENGTH_SHORT).show();
                    return;
                }
            }

        }

        db.addUser(new User(usuario, MD5.criptografar(senha)));
        Toast.makeText(this, R.string.usuario_cadastrado, Toast.LENGTH_LONG).show();
        finish();
    }

    private String getValueEditText(EditText et){
        return et.getText().toString();
    }
}
