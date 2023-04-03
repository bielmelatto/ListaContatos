package br.edu.ifsp.dmos5.view.adapter;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import br.edu.ifsp.dmos5.model.Contact;

public class ContactsAdapter extends ArrayAdapter<Contact> {

    public ContactsAdapter(Context context, int resource, List<Contact> values) {
        super(context, resource, values);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView textView = new TextView(getContext());
        textView.setTextColor(Color.BLACK);
        if (getItem(position) == null){
            textView.setText("");
        } else{
            textView.setText(getItem(position).getApelido());
        }

        return textView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView textView = new TextView(getContext());
        textView.setTextColor(Color.BLACK);
        if (getItem(position)==null){
            textView.setText("");
        } else{
            textView.setText(String.format("%s%n%s", getItem(position).getApelido(), getItem(position).getNome()));
        }

        return textView;
    }


}
