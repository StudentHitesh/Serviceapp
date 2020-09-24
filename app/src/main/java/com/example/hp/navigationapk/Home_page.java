package com.example.hp.navigationapk;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prajapatifamily.selectimagefromgallery.editprofile;

import java.util.List;
import java.util.Locale;

import static com.example.hp.navigationapk.R.id.checkBox;
import static com.example.hp.navigationapk.R.id.checkBox2;
import static com.example.hp.navigationapk.R.id.checkBox3;
import static com.example.hp.navigationapk.R.id.checkBox4;
import static com.example.hp.navigationapk.R.id.checkBox5;
import static com.example.hp.navigationapk.R.id.drawer_layout;
import static com.example.hp.navigationapk.R.id.listview;
import static com.example.hp.navigationapk.R.id.nav_view;
public class Home_page extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,LocationListener {
    String area=null;
    LocationManager locationManager;
    final Context context = this;


    public void onLocationChanged(Location location) {

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            Toast.makeText(this, ""+ "\n"+addresses.get(0).getAddressLine(0)+", "+
                    addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2), Toast.LENGTH_SHORT).show();

            //String area1=addresses.get(0).getAddressLine(0)+","+addresses.get(0).getAddressLine(1)+","+addresses.get(0).getAddressLine(2);
            area= addresses.get(0).getSubLocality();
            area=area.toLowerCase();
            Toast.makeText(this, ""+area, Toast.LENGTH_SHORT).show();
        }catch(Exception e)
        {

        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(Home_page.this, "Please Enable GPS ", Toast.LENGTH_SHORT).show();
    }

    public  void getlocation()
    {

        //Toast.makeText(this, "getlocation()", Toast.LENGTH_SHORT).show();




        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, (LocationListener) this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }

    }

    android.app.Fragment fragment=null;
AdapterViewFlipper adapterViewFlipper;


    private static int IMG_RESULT=1;
    int flag=0;
    String ImageDecode;

    ImageView imageViewLoad;
    Intent intent;
    EditText nm,ph,eyear,ad;
    String bus_type="";

ListView lv;
String service[]={"Electrician","Plumber","Painter","Carpenter"};

    RadioButton chk1,chk2,chk3,chk4,chk5;
    Button btn;

int image[]={R.drawable.electri,R.drawable.plumb,R.drawable.painter,R.drawable.carpent};
    //int image1[]={R.drawable.electri,R.drawable.plumb,R.drawable.painter,R.drawable.carpent};
//int image[]={R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};

    @SuppressLint("WrongViewCast")
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        lv=findViewById(listview);
        //ArrayAdapter adapter=new ArrayAdapter(Home_page.this,android.R.layout.simple_list_item_1,service);

        Custom_service adapter=new Custom_service(Home_page.this,service,image);
        lv.setAdapter(adapter);
        
        
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
                    new AlertDialog.Builder(Home_page.this).setTitle("Internet Disable").setMessage( "\n"+"Please Enable Internet").setNeutralButton("Ohk Got it !", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                        }
                    }).show();
                }
                if(connected) {

                    if(area==null)
                    {
                        new AlertDialog.Builder(Home_page.this).setTitle("Alert !").setMessage( "\n"+"App is Unable to fetch Your Location ,Please First Select Your Area.").setNeutralButton("Ohk Got it !", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
                    }

                    if (position == 0 && area!=null) {
                        fragment = new Electrician_service(area);
                        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.contect_frag, fragment).addToBackStack("E").commit();


                    }
                    if (position == 1 && area!=null) {
                        fragment = new Plumber_service(area);
                        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.contect_frag, fragment).addToBackStack("P").commit();

                    }
                    if (position == 2 && area!=null) {
                        fragment = new Painter_service(area);
                        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.contect_frag, fragment).addToBackStack("Pa").commit();

                    }
                    if (position == 3 && area!=null) {
                        fragment = new Carpenter_service(area);
                        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.contect_frag, fragment).addToBackStack("C").commit();

                    }
                }
            }
        });

        
        

        adapterViewFlipper= findViewById(R.id.AVF);

        CustomAdapterFlipper customAdapterFlipper=new CustomAdapterFlipper(getApplicationContext(),image);
        adapterViewFlipper.setAdapter(customAdapterFlipper);
        adapterViewFlipper.setFlipInterval(3000);
        adapterViewFlipper.setAutoStart(true);





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(Home_page.this);

                View view1=getLayoutInflater().inflate(R.layout.fragment_ab,null);

                final String[] rdbtn = {""};

                nm=view1.findViewById(R.id.editText8);
                ad=view1.findViewById(R.id.editText9);
                ph=view1.findViewById(R.id.editText10);
                eyear=view1.findViewById(R.id.editText11);

                btn=view1.findViewById(R.id.button5);
                chk1=view1.findViewById(checkBox3);
                chk2=view1.findViewById(checkBox2);
                chk3=view1.findViewById(checkBox);
                chk4=view1.findViewById(checkBox4);
                chk5=view1.findViewById(checkBox5);

                chk5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        LayoutInflater li = LayoutInflater.from(context);
                        View promptsView = li.inflate(R.layout.prompts, null);

                        AlertDialog.Builder alertDialogBuilder99 = new AlertDialog.Builder(context);

                        // set prompts.xml to alertdialog builder
                        alertDialogBuilder99.setView(promptsView);

                        final EditText userInput = (EditText) promptsView
                                .findViewById(R.id.editTextDialogUserInput);

                        // set dialog message
                        alertDialogBuilder99
                                .setCancelable(false)
                                .setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
                                                // get user input and set it to result
                                                // edit text
                                                bus_type=userInput.getText().toString();
                                                Toast.makeText(Home_page.this, ""+bus_type, Toast.LENGTH_SHORT).show();

                                            }
                                        })
                                .setNegativeButton("Cancel",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
                                                dialog.cancel();
                                            }
                                        });

                        // create alert dialog
                        AlertDialog alertDialog99 = alertDialogBuilder99.create();

                        // show it
                        alertDialog99.show();

                    }
                });


                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (nm.getText().toString().equals("")) {
                            nm.setError("Please Enter Name: ");
                        } else if (ad.getText().toString().equals(""))
                        {
                            ad.setError("Please Enter Address: ");
                        }
                        else if ( ph.getText().toString().equals("") || ph.getText().toString().length()<10 || ph.getText().toString().length()>11)
                        {
                            ph.setError("Please Enter Valid Phone number: ");
                        }
                        else if (eyear.getText().toString().equals(" ") || eyear.getText().toString().length()<4 || eyear.getText().toString().length()>4 || Integer.parseInt(eyear.getText().toString())>2018 || Integer.parseInt(eyear.getText().toString())<1900 )
                        {
                            eyear.setError("Please Enter Valid Establish year: ");
                        }
                        else if(!chk1.isChecked() && !chk2.isChecked() && !chk3.isChecked() && !chk4.isChecked() && !chk5.isChecked())
                        {
                            Toast.makeText(Home_page.this, "Please Select Your Business Type......", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            if(chk1.isChecked()){ rdbtn[0] = chk1.getText().toString();}
                            else if(chk2.isChecked()){ rdbtn[0] = chk2.getText().toString();}
                            else  if(chk3.isChecked()){ rdbtn[0] = chk3.getText().toString();}
                            else  if(chk4.isChecked()){ rdbtn[0] = chk4.getText().toString();}
                            else  if(chk5.isChecked()){ rdbtn[0] = bus_type;}
                            new AlertDialog.Builder(Home_page.this).setTitle("Terms And Conditions").setMessage("I am Sure about that the details filled by me is true. And if Anything is wrong in this then I am responsible for it.").setNeutralButton("I Agree", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                    new AlertDialog.Builder(Home_page.this).setTitle("Alert ! ").setMessage("You Have Selected I Agree Button , it Can Charge Your Sms Cost." + "\n" + "Would You Like To Continue Your Action").setNeutralButton("Continue", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                            String msg = "Name: " + nm.getText().toString() + "\n" + "Add:" + ad.getText().toString() + "\n" + "phno:" + ph.getText().toString() + "\n" + "Est_year:" + eyear.getText().toString()+"\n Type of Business: "+ rdbtn[0];

                                            Intent intent = new Intent(Home_page.this, Home_page.class);

                                            PendingIntent pendingIntent = PendingIntent.getActivity(Home_page.this, 0, intent, 0);

                                            SmsManager sms = SmsManager.getDefault();

                                            sms.sendTextMessage("8460692812", null, msg, pendingIntent, null);

                                            new AlertDialog.Builder(Home_page.this).setTitle("Thanks For Joining Us").setMessage("We will Contact u soon if Your Request is Accepted").setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {


//                                                Intent intent = new Intent(Home_page.this, Home_page.class);
//                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                                startActivity(intent);
                                                }
                                            }).show();


                                        }
                                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

//                                        Intent intent = new Intent(getActivity().getApplicationContext(), Home_page.class);
//                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                        startActivity(intent);

                                        }
                                    }).show();


                                }
                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

//                                Intent intent = new Intent(getActivity().getApplicationContext(), Home_page.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                startActivity(intent);

                                }
                            }).show();
                        }
                    }
                });

                builder.setView(view1);

                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(nav_view);
        TextView username= navigationView.getHeaderView(0).findViewById(R.id.txtuser);
        imageViewLoad=navigationView.findViewById(R.id.imageload);
        Intent intent=getIntent();
        String s=intent.getStringExtra("USER");
        username.setText(s);
        navigationView.setNavigationItemSelectedListener(this);

        getlocation();
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*@Override                                                       //option menu

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        SubMenu sm=menu.addSubMenu(0,1,1,"Search According to Area");

        sm.add(0,11,Menu.NONE,"Amraiwadi");

        sm.add(0,12,Menu.NONE,"Ashramroad");

        sm.add(0,13,Menu.NONE,"Maninagar");

        sm.add(0,14,Menu.NONE,"Navrangpura");

        sm.add(0,15,Menu.NONE,"Vastral");





        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if(id==11)
        {
            area="amraiwadi";
            Toast.makeText(this, "You Have Selected Amraiwadi as Your area", Toast.LENGTH_SHORT).show();
        }
        else if(id==12)
        {
            Toast.makeText(this, "You Have Selected AshramRoad as Your area", Toast.LENGTH_SHORT).show();
            area="ashramroad";
        }
        else if(id==13)
        {
            Toast.makeText(this, "You Have Selected Maninagar as Your area", Toast.LENGTH_SHORT).show();
            area="maninagar";
        }
        else if(id==14)
        {
            Toast.makeText(this, "You Have Selected Navrangpura as Your area", Toast.LENGTH_SHORT).show();
            area="navrangpura";
        }
        else if(id==15)
        {
            Toast.makeText(this, "You Have Selected Vastral as Your area", Toast.LENGTH_SHORT).show();
            area="vastral";
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("ResourceType")
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();




        switch (item.getItemId()){
            case R.id.home:
                fragment= new Fragment();
                flag=0;
                break;
            case R.id.viewprofile:
                Toast.makeText(this, "Edit profile", Toast.LENGTH_SHORT).show();

                Intent intent9=new Intent(Home_page.this,editprofile.class);
                startActivity(intent9);
                flag=4;
                break;

            case R.id.feedback:

                new AlertDialog.Builder(Home_page.this).setTitle("Go to PlayStrore").setMessage("Please Give us Your Feedback in the playstore And Press Go Button !").setNeutralButton("Go", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Home_page.this, "Thanks For Your Feedback", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

                flag=4;
                break;
            case R.id.rate_us:
                flag=1;
                //fragment=new RatingBar_frag();
                //Toast.makeText(this, "Rate us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.contact_us:
                new AlertDialog.Builder(this).setTitle("Contact us").setMessage("You can contact us on..."+ "\n\n"+"   1)0216rajesh@gmail.com"+"\n"+"     2)rajputhitesh1998@gmail.com").setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
                flag=4;
                break;
            case R.id.about_us:
                new AlertDialog.Builder(this).setTitle("About us").setMessage("The Service App basically provides four types of services Which are Electrician,Painter,Plumber,Carpenter by selecting your area or your nearby area."+"\n\n   You also can call or sms by long pressing on service provider."+"\n\n And if you want add your business in this application then you can add your business by just providing some information at free of cost.").setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
                flag=4;
                break;
            case R.id.logout:
                Intent intent = new Intent(this, First_page.class);
                startActivity(intent);
                flag=4;
                break;

        }



        if (flag==0)
        {
        FragmentTransaction ft=getFragmentManager().beginTransaction();
//            this.onDestroy();
//            ft.remove(fragment);
        ft.replace(R.id.contect_frag,fragment).commit();

        }
        if (flag==1)
        {
            new AlertDialog.Builder(Home_page.this).setTitle("Rate this App").setMessage("If you like this App , then please rate in the playstore").setNeutralButton("Rate", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(Home_page.this, "Thanks To Rate the App", Toast.LENGTH_SHORT).show();
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
        if (requestCode==IMG_RESULT && resultCode==RESULT_OK && null!=data)
        {
            Uri URI=data.getData();
            String[] FILE={MediaStore.Images.Media.DATA};

            Cursor cursor=getContentResolver().query(URI,FILE,null,null,null);
            cursor.moveToFirst();

            int columnIndex=cursor.getColumnIndex(FILE[0]);
            ImageDecode=cursor.getString(columnIndex);
            cursor.close();

            imageViewLoad.setImageBitmap(BitmapFactory.decodeFile(ImageDecode));
            imageViewLoad.setImageResource(Integer.parseInt(ImageDecode));
        }}catch (Exception e) {

            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
    }

   /* class Fliperadapter extends BaseAdapter {
        Context ctx;
        int img[];
        LayoutInflater inflater;

        public Fliperadapter(Context ctx, int[] img) {
            this.ctx = ctx;
            this.img = img;
            inflater=LayoutInflater.from(ctx);
        }

        @Override
        public int getCount() {
            return img.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView=inflater.inflate(R.layout.flipper_item,null);
            ImageView txtimage=convertView.findViewById(imageView2);
            txtimage.setImageResource(img[position]);
            return convertView;
        }
    }*/
}
