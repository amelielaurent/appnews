package com.newsgobelins.user.appnews.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.newsgobelins.user.appnews.R;

import androidx.fragment.app.Fragment;

public class ContactFragment extends Fragment {

    private View view;
    private TextView mbuttonCall;
    private TextView mbuttonAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.contact_fragment, container, false);
        mbuttonCall = view.findViewById(R.id.contact_telephone);
        mbuttonAddress = view.findViewById(R.id.contact_address);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Fonction qui permet d'appeler
        mbuttonCall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getContext(), "APPELER", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0476542125"));
                startActivity(intent);
            }
        });

        //Fonction permettant d'afficher la carte
        mbuttonAddress.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getContext(), "VOIR LA CARTE", Toast.LENGTH_SHORT).show();
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=45.188460, 5.722344(Tabac Presse le 7/7, Grenoble)");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }
}
