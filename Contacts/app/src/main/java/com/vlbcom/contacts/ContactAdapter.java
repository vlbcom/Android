package com.vlbcom.contacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ContactAdapter extends BaseAdapter {
    private List <Contact> contacts;
    private Context context;


    public ContactAdapter(@NonNull Context context) {
        this.context = context;
        this.contacts=new ArrayList<>();
           }



    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }



    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.contact_row, parent, false);
        }
        Contact contact = contacts.get(position);
        TextView nameTxt = convertView.findViewById(R.id.name_txt);
        TextView emailTxt = convertView.findViewById(R.id.email_txt);

        nameTxt.setText(contact.getName());
        emailTxt.setText(contact.getEmail());


        return convertView;
    }

    public void addContact(Contact contact){
        contacts.add(0,contact);
        notifyDataSetChanged();
    }
}
