package com.example.mad_project;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ContactUs extends Fragment implements View.OnClickListener {

    Button email, call, sms;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.activity_contact_us, null);
        email = (Button) getView().findViewById(R.id.sendemail);
        call = (Button) getView().findViewById(R.id.sendcall);
        sms = (Button) getView().findViewById(R.id.sendtxt);

        getActivity().findViewById(R.id.sendemail).setOnClickListener(this);
        getActivity().findViewById(R.id.sendcall).setOnClickListener(this);
        getActivity().findViewById(R.id.sendtxt).setOnClickListener(this);

        return view;


    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.sendemail){
            String uriText =
                    "mailto:19-10632@formanite.fccollege.edu.pk" +
                            "?subject=" + Uri.encode("Whisker's Shelter Query");

            Uri uri = Uri.parse(uriText);

            Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
            sendIntent.setData(uri);
            if (sendIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(Intent.createChooser(sendIntent, "Send email"));
            }
        }
        else if(v.getId() == R.id.sendcall){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:03334437959"));
            startActivity(intent);

        }
        else if(v.getId() == R.id.sendtxt){
            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address", "03334437959");
            smsIntent.putExtra("sms_body"," ");
            startActivity(smsIntent);

        }
        else if(v.getId() == R.id.gomaps){
            Intent intent = new Intent(getActivity(), MapsActivity.class);
            startActivity(intent);
        }

    }

}
