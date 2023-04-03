package br.edu.ifsp.dmos5.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.dao.UserDAO;
import br.edu.ifsp.dmos5.model.Contact;
import br.edu.ifsp.dmos5.model.User;
import br.edu.ifsp.dmos5.view.constants.Constants;

public class NewContactActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText apelido_et;
    private EditText nome_et;
    private EditText telefone_et;
    private Button save_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findById();
        setClickListener();
    }

    private void findById(){
        apelido_et = findViewById(R.id.apelido);
        nome_et = findViewById(R.id.nome);
        telefone_et = findViewById(R.id.telefone);
        save_btn = findViewById(R.id.salvarContato);
    }

    private String getStringEditText(EditText et){
        return et.getText().toString();
    }

    private void setClickListener() {
        save_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == save_btn){
            adicionarContato(UserDAO.getInstance());
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void adicionarContato(UserDAO us){
        Contact contato;
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String user = bundle.getString(Constants.USER);

            contato = new Contact(getStringEditText(apelido_et), getStringEditText(nome_et), getStringEditText(telefone_et));

            for(User u : us.getUsers()){
                if(u.getUsername().equals(user)){
                    u.addContact(contato);
                    Toast.makeText(this, R.string.contatoSalvo, Toast.LENGTH_LONG).show();
                }
            }

        }
    }

}
