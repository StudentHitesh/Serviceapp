package com.example.hp.navigationapk;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class RegisterActivity1 extends AppCompatActivity {
    GMailSender sender;
    Button register, log_in;
    EditText First_Name, Last_Name, Email, Password ;
    String F_Name_Holder, L_Name_Holder, EmailHolder, PasswordHolder,tmpotp;
    String finalResult ;
    String HttpURL = "https://theserviceapk.000webhostapp.com/RegisterApp/UserRegistration.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        sender = new GMailSender("0216rajesh@gmail.com", "rajeshprabhulal8726");


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.

                Builder().permitAll().build();


        StrictMode.setThreadPolicy(policy);

        First_Name = (EditText)findViewById(R.id.editText7);
        Last_Name = (EditText)findViewById(R.id.editText3);
        Email = (EditText)findViewById(R.id.editText4);
        Password = (EditText)findViewById(R.id.editText5);

        register = (Button)findViewById(R.id.button4);
        log_in=(Button)findViewById(R.id.button7);

        //Adding Click Listener on button.
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;
                }
                else
                    connected = false;

                if(!connected)
                {
                    new AlertDialog.Builder(RegisterActivity1.this).setTitle("Internet Disable").setMessage( "\n"+"Please Enable Internet").setNeutralButton("Ohk Got it !", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                        }
                    }).show();
                }

                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();
                if(connected) {
                    if (First_Name.getText().toString().equals("")) {
                        First_Name.setError("Please Enter First name");

                    } else if (Last_Name.getText().toString().equals("")) {
                        Last_Name.setError("Please Enter Last name");

                    } else if (!Email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                        Email.setError("Please Insert Valid Email Adddress");
                    } else if (Password.getText().toString().equals("") || Password.getText().length() < 6) {
                        Password.setError("Password must have atleast 6 character");
                    }
                 else {

                        LayoutInflater li201 = LayoutInflater.from(context);
                        View promptsView2 = li201.inflate(R.layout.otpsender, null);


                        final AlertDialog.Builder alertDialogBuilder1011 = new AlertDialog.Builder(context);

                        alertDialogBuilder1011.setView(promptsView2);


                        final EditText emailInput = (EditText) promptsView2.findViewById(R.id.otpinput);

                        try {

                            new MyAsyncClass().execute();


                        } catch (Exception ex) {

//                            Toast.makeText(getApplicationContext(), ex.toString(), 100).show();

                        }

                        alertDialogBuilder1011.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text
                                tmpotp = emailInput.getText().toString();
                                //Toast.makeText(RegisterActivity1.this, "" + tmpotp, Toast.LENGTH_SHORT).show();

                                if(tmpotp.equals("9999")) {
                                    UserRegisterFunction(F_Name_Holder, L_Name_Holder, EmailHolder, PasswordHolder);
                                }
                                 else {
                                    Toast.makeText(RegisterActivity1.this, "You Have Entered Wrong OTP.", Toast.LENGTH_SHORT).show();
                                }


                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertDialog1022 = alertDialogBuilder1011.create();

                        // show it
                        alertDialog1022.show();
                    }
                }
            }
        });

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RegisterActivity1.this,LoginActivity.class);
                startActivity(intent);

            }
        });

    }

    public void CheckEditTextIsEmptyOrNot(){

        F_Name_Holder = First_Name.getText().toString();
        L_Name_Holder = Last_Name.getText().toString();
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();


        if(TextUtils.isEmpty(F_Name_Holder) || TextUtils.isEmpty(L_Name_Holder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
        {

            CheckEditText = false;

        }
        else {

            CheckEditText = true ;
        }

    }

    public void UserRegisterFunction(final String F_Name, final String L_Name, final String email, final String password){

        class UserRegisterFunctionClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(RegisterActivity1.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(RegisterActivity1.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("f_name",params[0]);

                hashMap.put("L_name",params[1]);

                hashMap.put("email",params[2]);

                hashMap.put("password",params[3]);


                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

        userRegisterFunctionClass.execute(F_Name,L_Name,email,password);
    }

    class MyAsyncClass extends AsyncTask<Void, Void, Void> {



        ProgressDialog pDialog;



        @Override

        protected void onPreExecute() {

            super.onPreExecute();



            pDialog = new ProgressDialog(RegisterActivity1.this);

            pDialog.setMessage("Please wait...");

            pDialog.show();



        }



        @Override

        protected Void doInBackground(Void... mApi) {

            try {

                // Add subject, Body, your mail Id, and receiver mail Id.

                sender.sendMail("Email Verification","9999", "0216rajesh@gmail.com", Email.getText().toString());





            }



            catch (Exception ex) {



            }

            return null;

        }



        @Override

        protected void onPostExecute(Void result) {

            super.onPostExecute(result);

            pDialog.cancel();

            Toast.makeText(getApplicationContext(), "Email send", 100).show();

        }

    }


}