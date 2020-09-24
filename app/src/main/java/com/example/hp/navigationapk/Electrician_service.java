package com.example.hp.navigationapk;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class Electrician_service extends android.app.Fragment implements ClickListener{
    SearchView sv;

    RecyclerView recyclerView;
    List<member> memberList;
    Toolbar toolbar;
    ArrayAdapter adapter;
    ListView lv;
    URL url;
    StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
    //List<String> lst;
    String nm[]=new String[5];
    String address1[]=new String[5];
    String number[]=new String[20];
    String est1[]=new String[5];
    String cont;
    int pos;

    List<String> arrayList = new ArrayList<>();

    String area;


    @SuppressLint("ValidFragment")
    public Electrician_service(String loc_area) {

        this.area = loc_area;
    }

    public Electrician_service() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(policy);

        memberList=new ArrayList<member>();

        if(area.equals("satyam nagar"))
        {
            area="amraiwadi";
        }

        if(area.equals("university area"))
        {
            area="navrangpura";
        }


        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_electrician_service,container,false);
        //sv=view.findViewById(R.id.s1);

        lv=view.findViewById(R.id.lst);
        recyclerView=view.findViewById(R.id.recycle);

        //adapter=ArrayAdapter.createFromResource(getActivity(), test,android.R.layout.simple_list_item_1);
        //adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,str);

        try{
            url=new URL("https://rajputhitesh1998.000webhostapp.com/serviceapk/Electricianxmlfile.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(url.openStream());


        NodeList nodeList = document.getElementsByTagName(area);



        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;

            String name = getDataDom(element, "name");
            nm[i]=name;

            String address = getDataDom(element, "address");
            address1[i]=address;
            String contact = getDataDom(element, "contact");

            try {
                number[i] = String.valueOf(contact);
            } catch (NumberFormatException e) {
            }

            String est = getDataDom(element, "est");
            est1[i]= String.valueOf(est);
            //  String price=getDataDom(element,"COUNTRY");
            // String year=getDataDom(element,"YEAR");
            String dt = "Name: " + name + "\n" + "Address: " + address + "\n" + "Conatact No:  " + contact + "\n" + " Established Year: " + est + "\n" + "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -" + "\n\n";
            arrayList.add(dt);
        }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*for (int i=0;i<nm.length;i++)

        Toast.makeText(getActivity().getApplicationContext(), "Name" + nm[i], Toast.LENGTH_SHORT).show();*/


        for (int i=0;i<nm.length;i++) {
            member m = new member(nm[i], address1[i],number[i],est1[i]);
            memberList.add(m);
        }

        //card_list adapter=new card_list(getActivity().getApplicationContext(),nm,address1);
        //lv.setAdapter(adapter);
        //registerForContextMenu(lv);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        card_list adapter=new card_list(memberList,getActivity().getApplicationContext());
        recyclerView.setAdapter(adapter);
        adapter.setClickListener((ClickListener) this);


        //Collections.addAll(arrayList, str);
        setHasOptionsMenu(true);
        return view;
    }

//    public void itemClicked(View view, int position) {
//        Toast.makeText(getActivity().getApplicationContext(), "Clicked " + position, Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void itemClicked(View view, int position) {
        Toast.makeText(getActivity().getApplicationContext(), "Clicked " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onLongClick(View view, int position) {
        registerForContextMenu(recyclerView);
        Toast.makeText(getActivity().getApplicationContext(), "Clicked " + position, Toast.LENGTH_SHORT).show();
        if (position==0)
        {
            pos=0;
            cont=number[0];

        }
        if (position==1)
        {
            pos=1;
            cont=number[1];

        }
        if (position==2)
        {
            pos=3;
            cont=number[2];
        }
        if (position==3)
        {
            pos=4;
            cont=number[3];
        }
        if (position==4)
        {
            pos=5;
            cont=number[4];
        }
        return 1;

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select action");
        //MenuItem m1 = menu.add(0, 1, 0, "View");
        MenuItem m2 = menu.add(0, 1, 0, "Call");
        MenuItem m3 = menu.add(0, 1, 0, "sms");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public boolean onContextItemSelected(MenuItem item){


        String tmpnum="8460692812";

        String num="";

        num=String.valueOf(cont);

        cont=num.substring(0,10);

        if(num.charAt(0)=='0' && num.charAt(1)=='7' && num.charAt(2)=='9')
        {
            cont=num.substring(0,11);
        }
        if (item.getTitle()=="View")
        {
//            first_frag f1=new first_frag();
//            f1.dis(arrayList,pos);
//            android.app.FragmentTransaction ft=getFragmentManager().beginTransaction();
//           ft.replace(R.id.contect_frag,f1).addToBackStack("vi").commit();

        }
        if(item.getTitle()=="Call"){
            Intent intent=new Intent(Intent.ACTION_CALL);

            intent.setData(Uri.parse("tel:"+cont.toString()));

            startActivity(intent);

            Toast.makeText(getActivity(),"Calling"+cont, Toast.LENGTH_SHORT).show();
        }
        else if(item.getTitle()=="sms"){

            SMS_frag sms_frag=new SMS_frag();

            sms_frag.Mobnum(cont);
            android.app.FragmentTransaction ft=getFragmentManager().beginTransaction();
            ft.replace(R.id.contect_frag,sms_frag).addToBackStack("sms").commit();
            //Toast.makeText(getActivity(),"SMS"+cont, Toast.LENGTH_SHORT).show();

        }else{
            return false;
        }
        return true;
    }

    @SuppressLint("ResourceType")



    /*public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_page,menu);

        /*MenuItem searchmenu=menu.findItem(R.id.app_bar_search);
        final SearchView searchView= (SearchView) MenuItemCompat.getActionView(searchmenu);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //searchView.clearFocus();
                if(arrayList.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"No Match Found", Toast.LENGTH_SHORT).show();

                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
        //return super.onCreateOptionsMenu(menu, inflater);

    }*/

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.home_page, menu);
        SubMenu sm=menu.addSubMenu(0,1,1,"Search According to Area");

        sm.add(0,11,Menu.NONE,"Ambawadi");

        sm.add(0,12,Menu.NONE,"Amraiwadi");

        sm.add(0,13,Menu.NONE,"Ashramroad");

        sm.add(0,14,Menu.NONE,"Bapunagar");

        sm.add(0,15,Menu.NONE,"Behrampura");
        sm.add(0,16,Menu.NONE,"Bodakdev");
        sm.add(0,17,Menu.NONE,"Bopal");
        sm.add(0,18,Menu.NONE,"Chandkheda");
        sm.add(0,19,Menu.NONE,"Civil_hospital");
        sm.add(0,20,Menu.NONE,"CTM");
        sm.add(0,21,Menu.NONE,"Ellishbridge");
        sm.add(0,22,Menu.NONE,"Gandhi_road");
        sm.add(0,23,Menu.NONE,"Ghatlodia");
        sm.add(0,24,Menu.NONE,"Geeta_mandir");
        sm.add(0,25,Menu.NONE,"Isanpur");
        sm.add(0,26,Menu.NONE,"Jivraj_park");
        sm.add(0,27,Menu.NONE,"Kalupur");
        sm.add(0,28,Menu.NONE,"Hatkeshwar");
        sm.add(0,29,Menu.NONE,"Meghaninagar");
        sm.add(0,30,Menu.NONE,"Navrangpura");
        sm.add(0,31,Menu.NONE,"Paldi");
        sm.add(0,32,Menu.NONE,"Raipur");
        sm.add(0,33,Menu.NONE,"Vastral");
        sm.add(0,34,Menu.NONE,"Maninagar");

        return ;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        FragmentManager manager=getActivity().getFragmentManager();
        FragmentTransaction ft=manager.beginTransaction();
        Fragment newFragment=this;
        int flag=0;
        if(id==11)
        {
            area="ambawadi";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected Ambawadi as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        }
        else if(id==12)
        {
            area="amraiwadi";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected Amraiwadi as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        }
        else if(id==13)
        {
            area="ashramroad";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected Ashram_road as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        }
        else if(id==14)
        {
            area="bapunagar";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected Bapunagar as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        }
        else if(id==15)
        {

            area="behrampura";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected Behrampura as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        } else if(id==16)
        {

            area="bodakdev";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected BodakDev as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        } else if(id==17)
        {

            area="bopal";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected Bopal as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        } else if(id==18)
        {

            area="chandkheda";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected Chandkheda as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        } else if(id==19)
        {

            area="civil_hospital";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected Civil_hospital as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        } else if(id==20)
        {

            area="ctm";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected CTM as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        } else if(id==21)
        {

            area="ellishbridge";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected Ellishbridge as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        } else if(id==22)
        {

            area="gandhi_road";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected Gandhi_road as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        } else if(id==23)
        {
            area="ghatlodia";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected Ghatlodia as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        } else if(id==24)
        {
            area="geeta_mandir";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected Geeta_mandir as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        } else if(id==25)
        {
            area="isanpur";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected Isanpur as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        } else if(id==26)
        {
            area="jivraj_park";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected Jivraj_park as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        } else if(id==27)
        {
            area="kalupur";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected Kalupur as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        } else if(id==28)
        {
            area="hatkeshwar";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected Hatkeshwar as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        } else if(id==29)
        {
            area="meghaninagar";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected Meghaninagar as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        } else if(id==30)
        {
            area="navrangpura";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected Navrangpura as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        } else if(id==31)
        {
            area="paldi";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected Paldi as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        } else if(id==32)
        {
            area="raipur";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected Raipur as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        } else if(id==33)
        {
            area="vastral";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected Vastral as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        }
        else if(id==34)
        {
            area="maninagar";
            Toast.makeText(getActivity().getApplicationContext(), "You Have Selected Maninagar as Your area", Toast.LENGTH_SHORT).show();
            flag=1;
        }

        if (flag==1){
            this.onDestroy();
            ft.remove(this);
            ft.replace(R.id.contect_frag,newFragment).commit();
        }

        return super.onOptionsItemSelected(item);
    }





   /* @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //ArrayAdapter adapter=ArrayAdapter.createFromResource(getActivity(), test,android.R.layout.simple_list_item_1);
        lv.setAdapter(adapter);
    }*/
   String getDataDom(Element element,String s)
   {
       NodeList nodeList=element.getElementsByTagName(s);
       Node node=nodeList.item(0);
       Element element1= (Element) node;
       String txtdata=element1.getTextContent();
       return txtdata;
   }



//    <item
//    android:id="@+id/app_bar_search"
//    android:icon="@drawable/search"
//    android:title="Search"
//    app:showAsAction="ifRoom|withText"
//    app:actionViewClass="android.widget.SearchView"/>
}
