package com.example.hp.navigationapk;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {


    GMailSender sender;


    EditText Email, Password;
    TextView frgtpass;
    Button LogIn;
    String PasswordHolder, EmailHolder, tmpmail, tmpotp, newpass;
    String finalResult;
    String HttpURL = "https://theserviceapk.000webhostapp.com/RegisterApp/UserLogin.php";
    Boolean CheckEditText;
    ProgressDialog progressDialog;
    HashMap<String, String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    public static final String UserEmail = "";
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        sender = new GMailSender("0216rajesh@gmail.com", "rajeshprabhulal8726");


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.

                Builder().permitAll().build();


        StrictMode.setThreadPolicy(policy);


        Email = (EditText) findViewById(R.id.editText);
        frgtpass = (TextView) findViewById(R.id.textView5);
        Password = (EditText) findViewById(R.id.editText2);
        LogIn = (Button) findViewById(R.id.button3);

        frgtpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.emailsender, null);

                LayoutInflater li2 = LayoutInflater.from(context);
                View promptsView2 = li2.inflate(R.layout.otpsender, null);

                LayoutInflater li3 = LayoutInflater.from(context);
                View promptsView3 = li3.inflate(R.layout.newpassreceiver, null);


                final AlertDialog.Builder alertDialogBuilder99 = new AlertDialog.Builder(context);

                final AlertDialog.Builder alertDialogBuilder101 = new AlertDialog.Builder(context);

                final AlertDialog.Builder alertDialogBuilder102 = new AlertDialog.Builder(context);


                // set prompts.xml to alertdialog builder
                alertDialogBuilder99.setView(promptsView);

                alertDialogBuilder101.setView(promptsView2);

                alertDialogBuilder102.setView(promptsView3);

                final EditText userInput = (EditText) promptsView.findViewById(R.id.emailinput);

                final EditText emailInput = (EditText) promptsView2.findViewById(R.id.otpinput);

                final EditText npassInput = (EditText) promptsView3.findViewById(R.id.npassinput);

                // set dialog message
                alertDialogBuilder99.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // get user input and set it to result
                        // edit text
                        tmpmail = userInput.getText().toString();
                       // Toast.makeText(LoginActivity.this, "" + tmpmail, Toast.LENGTH_SHORT).show();


                        try {

                            new MyAsyncClass().execute();


                        } catch (Exception ex) {

//                            Toast.makeText(getApplicationContext(), ex.toString(), 100).show();

                        }

                        alertDialogBuilder101.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text
                                tmpotp = emailInput.getText().toString();
                               // Toast.makeText(LoginActivity.this, "" + tmpotp, Toast.LENGTH_SHORT).show();

                                if (tmpotp.equals("5555")) {
                                    alertDialogBuilder102.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // get user input and set it to result
                                            // edit text
                                            newpass = npassInput.getText().toString();
                                            //Toast.makeText(LoginActivity.this, "" + newpass, Toast.LENGTH_SHORT).show();

                                        }
                                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                                    // create alert dialog
                                    AlertDialog alertDialog102 = alertDialogBuilder102.create();

                                    // show it
                                    alertDialog102.show();

                                } else {
                                    Toast.makeText(LoginActivity.this, "You Have Entered Wrong OTP.", Toast.LENGTH_SHORT).show();
                                }


                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                        // create alert dialog
                        AlertDialog alertDialog101 = alertDialogBuilder101.create();

                        // show it
                        alertDialog101.show();

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                // create alert dialog
                AlertDialog alertDialog99 = alertDialogBuilder99.create();

                // show it
                alertDialog99.show();
            }
        });

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;
                } else connected = false;

                if (!connected) {
                    new AlertDialog.Builder(LoginActivity.this).setTitle("Internet Disable").setMessage("\n" + "Please Enable Internet").setNeutralButton("Ohk Got it !", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                        }
                    }).show();
                }

                if (Email.equals("") && Password.equals("")) {
                    Email.setError("plz fill name");
                    Password.setError("plz fill password");
                    /*Snackbar snackbar=Snackbar.make(ed,"please fill name",Snackbar.LENGTH_LONG);
                    snackbar.show();*/

                } else if (Email.equals("")) {
                    Email.setError("plz fill name");
                    /*Snackbar snackbar=Snackbar.make(ed,"please fill name",Snackbar.LENGTH_LONG);
                    snackbar.show();*/

                } else if (Password.equals("")) {
                    Password.setError("plz fill password");
                    /*Snackbar snackbar=Snackbar.make(ed,"please fill name",Snackbar.LENGTH_LONG);
                    snackbar.show();*/

                }


                CheckEditTextIsEmptyOrNot();

                if (connected) {

                    if (CheckEditText) {

                        UserLoginFunction(EmailHolder, PasswordHolder);

                    } else {

                        Toast.makeText(LoginActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                    }
                }

            }
        });
    }

    public void CheckEditTextIsEmptyOrNot() {

        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

        if (TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)) {
            CheckEditText = false;
        } else {

            CheckEditText = true;
        }
    }

    public void UserLoginFunction(final String email, final String password) {

        class UserLoginClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(LoginActivity.this, "Loading Data", null, true, true);

            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                if (httpResponseMsg.equalsIgnoreCase("Data Matched")) {

                    finish();

                    Intent intent = new Intent(LoginActivity.this, Home_page.class);

                    intent.putExtra(UserEmail, email);

                    startActivity(intent);

                } else {

                    Toast.makeText(LoginActivity.this, httpResponseMsg, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("email", params[0]);

                hashMap.put("password", params[1]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserLoginClass userLoginClass = new UserLoginClass();

        userLoginClass.execute(email, password);
    }

    class MyAsyncClass extends AsyncTask<Void, Void, Void> {



        ProgressDialog pDialog;



        @Override

        protected void onPreExecute() {

            super.onPreExecute();



            pDialog = new ProgressDialog(LoginActivity.this);

            pDialog.setMessage("Please wait...");

            pDialog.show();



        }



        @Override

        protected Void doInBackground(Void... mApi) {

            try {

                // Add subject, Body, your mail Id, and receiver mail Id.

                sender.sendMail("Email Verification", "5555", "0216rajesh@gmail.com", tmpmail);





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