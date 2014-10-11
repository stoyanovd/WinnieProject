package com.example.WinnieProject.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.WinnieProject.R;
import com.example.WinnieProject.User;

import java.util.ArrayList;

/**
 * Created by Юрий on 11.10.2014.
 */
public class UserAdapter extends ArrayAdapter<User> {
    Context context;

    public UserAdapter(Context _context, ArrayList<User> value) {
        super(_context, R.layout.list_item_for_friends_in_contacts, value);
        context = _context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        User user = getItem(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item_for_friends_in_contacts, null);
        TextView textView = (TextView) view.findViewById(R.id.textViewUser);
        textView.setTextColor(Color.RED);
        textView.setTextSize(20);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewUser);
        textView.setText("ФИО:" + user.name + "\nТел.:" + user.phoneNumber + "\nYandexMoney:" + user.yandexMoneyNumber);
        return view;
    }

}
