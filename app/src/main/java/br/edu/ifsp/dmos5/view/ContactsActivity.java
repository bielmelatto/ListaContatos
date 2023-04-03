package br.edu.ifsp.dmos5.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.dao.UserDAO;
import br.edu.ifsp.dmos5.model.Contact;
import br.edu.ifsp.dmos5.view.adapter.ContactsAdapter;
import br.edu.ifsp.dmos5.view.constants.Constants;

public class ContactsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner contacts;
    private TextView nome;
    private TextView telefone;
    private Button novoContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findById();
        setClickListener();
        setOnItemSelectedListener();
        preencheSpinner(UserDAO.getInstance());
    }

    @Override
    protected void onResume() {
        super.onResume();
        contacts.setSelection(0);
        preencheSpinner(UserDAO.getInstance());
        setOnItemSelectedListener();
    }

    private void findById(){
        contacts = findViewById(R.id.spinnerContatos);
        nome = findViewById(R.id.nomeContato_tv);
        telefone = findViewById(R.id.telefoneContato_tv);
        novoContato = findViewById(R.id.novoContato_btn);
    }
    private void setClickListener() {
        novoContato.setOnClickListener(this);
    }

    private void setOnItemSelectedListener(){
        contacts.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == novoContato){
            saveNewContato(UserDAO.getInstance());
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveNewContato(UserDAO uDAO){
        Bundle bundle = getIntent().getExtras();
        String user = bundle.getString("user");

        Bundle bundle2 = new Bundle();
        bundle2.putString("user", user);

        Intent intent = new Intent(this, NewContactActivity.class);
        intent.putExtras(bundle2);
        startActivity(intent);
    }

    private void preencheSpinner(UserDAO user){
        Bundle bundle = getIntent().getExtras();

        ContactsAdapter adapter = new ContactsAdapter(this, android.R.layout.simple_spinner_item,  user.findByUsername(bundle.getString(Constants.USER)).getContacts());
        contacts.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Contact contact = (Contact) contacts.getItemAtPosition(position);

        if(contact != null){
            exibeContato(contact);
        } else{
            nome.setText("");
            telefone.setText("");
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        nome.setVisibility(View.GONE);
        telefone.setVisibility(View.GONE);
    }

    private void exibeContato (Contact contato){
        nome.setVisibility(View.VISIBLE);
        telefone.setVisibility(View.VISIBLE);
        nome.setText(contato.getNome());
        telefone.setText(contato.getTelefone());
    }
}
