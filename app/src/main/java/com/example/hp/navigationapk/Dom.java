package com.example.hp.navigationapk;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import android.os.StrictMode;
import android.provider.FontsContract;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.navigationapk.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static android.widget.Toast.LENGTH_SHORT;


/**
 * A simple {@link Fragment} subclass.
 */
public class Dom extends Fragment {
    URL url;
    ListView lv1;
    int position,flag=0;
    String area;
    TextView tv1;

    StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
    ArrayList<String>arrayList;
    public Dom()
    {

    }
    public void pos(int position,String area){ this.position=position; this.area=area;}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StrictMode.setThreadPolicy(policy);
        arrayList=new ArrayList<>();
        View view=inflater.inflate(R.layout.fragment_dom,container,false);

        lv1=(ListView)view.findViewById(R.id.lst);
        try {
            if(position==0 && area=="amraiwadi")
            {
                //url = new URL("https://theserviceapk.000webhostapp.com/theserviceapp/Electxmlfile.xml");
                url=new URL("https://theserviceapk.000webhostapp.com/theserviceapp/Electricians/eleamraiwadi.xml");
            }
            else if(position==0 && area=="maninagar")
            {
                url=new URL("https://theserviceapk.000webhostapp.com/theserviceapp/Electxmlfile.xml");
            }
            else if(position==1 && area=="maninagar")
            {
                url=new URL("https://theserviceapk.000webhostapp.com/theserviceapp/Plumbers/PlumberManinagar.xml");
            }
            else if(position==1 && area=="amraiwadi")
            {
                url=new URL("https://theserviceapk.000webhostapp.com/theserviceapp/Plumbers/PlumberAmraiwadi.xml");
            }
            else if(position==2 && area=="maninagar")
            {
                url=new URL("https://theserviceapk.000webhostapp.com/theserviceapp/Painters/PainterManinagar.xml");
            }
            else if(position==2 && area=="amraiwadi")
            {
                url=new URL("https://theserviceapk.000webhostapp.com/theserviceapp/Painters/PainterAmraiwadi.xml");
            }
            else if(position==3 && area=="amraiwadi")
            {
                url = new URL("https://theserviceapk.000webhostapp.com/theserviceapp/Carxmlfile.xml");
            }
            else if(position==3 && area=="maninagar")
            {
                url = new URL("https://theserviceapk.000webhostapp.com/theserviceapp/Carxmlfile.xml");
            }
           else
            {
                url = new URL("");

            }



                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(url.openStream());


                NodeList nodeList = document.getElementsByTagName(area);



                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    Element element = (Element) node;

                    String name = getDataDom(element, "name");

                    String address = getDataDom(element, "address");
                    String contact = getDataDom(element, "contact");
                    String est = getDataDom(element, "est");
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

        lv1.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,arrayList));

        registerForContextMenu(lv1);




        return view;


    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select action");
        MenuItem m1 = menu.add(0, 1, 0, "Call");
        MenuItem m2 = menu.add(0, 1, 0, "sms");
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    String getDataDom(Element element,String s)
    {
        NodeList nodeList=element.getElementsByTagName(s);
        Node node=nodeList.item(0);
        Element element1= (Element) node;
        String txtdata=element1.getTextContent();
        return txtdata;
    }

}
