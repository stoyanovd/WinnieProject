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

        final User user = getItem(position);
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item_for_friends_in_contacts, null);
        TextView textView = (TextView) view.findViewById(R.id.textViewUser);
        textView.setTextColor(Color.RED);
        textView.setTextSize(20);
        textView.setText(user.name);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewUser);
        imageView.setImageResource(context.getResources().getIdentifier("p" + user.pictureId, "drawable", context.getPackageName()));

        ImageView imageView1 = (ImageView) view.findViewById(R.id.imageViewForVK);
        imageView1.setImageResource(context.getResources().getIdentifier("vk", "drawable", context.getPackageName()));
        if (user.vkId.equals(user.noFilled)) imageView1.setVisibility(View.INVISIBLE);

        ImageView imageView2 = (ImageView) view.findViewById(R.id.imageViewForContact);
        imageView2.setImageResource(context.getResources().getIdentifier("contact", "drawable", context.getPackageName()));
        if (user.phoneNumber.equals(user.noFilled)) imageView2.setVisibility(View.INVISIBLE);

        ImageView imageView3 = (ImageView) view.findViewById(R.id.imageViewForYandex);
        imageView3.setImageResource(context.getResources().getIdentifier("yandex", "drawable", context.getPackageName()));
        if (user.phoneNumber.equals(user.noFilled)) imageView3.setVisibility(View.INVISIBLE);


        return view;
    }

}
