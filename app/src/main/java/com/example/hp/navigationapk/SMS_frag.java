package com.example.hp.navigationapk;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SMS_frag extends android.app.Fragment {
  EditText ed1,ed2,ed3;
  Button btn;
  String num;


    public SMS_frag() {
        // Required empty public constructor
    }

    public void Mobnum(String num)
    {
        this.num=num;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_sms_frag,container,false);

            ed1=view.findViewById(R.id.editText12);


                ed2=view.findViewById(R.id.editText13);

        ed3=view.findViewById(R.id.editText14);

            btn=view.findViewById(R.id.button6);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    String msg="Name: "+ed3.getText().toString()+"\nAddress: "+ed2.getText().toString()+"\n Request: "+ ed1.getText().toString();

                    Intent intent=new Intent(getActivity(),SMS_frag.class);

                    PendingIntent pendingIntent=PendingIntent.getActivity(getActivity(),0,intent,0);

                    SmsManager sms=SmsManager.getDefault();

                    sms.sendTextMessage("8460692812",null,msg,pendingIntent,null);

                    Toast.makeText(getActivity(), "Message Sent.. "+num, Toast.LENGTH_SHORT).show();

                    Intent intent2 = new Intent(getActivity().getApplicationContext(), Home_page.class);
                    intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent2);
                }
            });

        return view;
    }


}
