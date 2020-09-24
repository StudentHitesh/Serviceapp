package com.example.hp.navigationapk;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by HP on 3/9/2018.
 */

public class card_list extends RecyclerView.Adapter<card_list.Holder> {
   Context context;
    String name[];
    String add[];

    //int image[];
    List<member> list;
    ClickListener clicklistener=null;

    private static LayoutInflater inf=null;

    /*public card_list(Context applicationContext, String[] str, String[] add) {
        this.context=applicationContext;
        this.name=str;
        this.add=add;
        inf = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }*/


    public card_list(List<member> memberList, Context applicationContext) {
        this.list=memberList;
        this.context=applicationContext;
    }




    /*public card_list(Context electrician_service, String[] name, String[] add) {
        this.context = electrician_service;
        this.name = name;
        this.add = add;
        //this.image = image;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }*/

    /*public card_list(Context electrician_service, String[] str, String[] add) {
        this.context=context;
        this.name=str;
        this.add=add;
        inf = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }*/


   /* @Override
    public int getCount() {
        return add.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }*/

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row_item,parent,false);
        Holder holder1=new Holder(view);
        return holder1;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.txtname.setText("------------------------------------\n"+list.get(position).getName());
        holder.txtname2.setText("------------------------------------\n"+"Add : "+list.get(position).getAdd());
        holder.textView3.setText("------------------------------------\n"+"Mobile : "+list.get(position).getNum());
        holder.textView4.setText("------------------------------------\n"+"Established : "+list.get(position).getEst());


    }

    /*@Override
    public long getItemId(int position) {
        return 1;
    }*/

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        CardView cardView;
        TextView txtname,txtname2,textView3,textView4;


        public Holder(View itemView) {
            super(itemView);
            //cardView=itemView.findViewById(R.id.cardview);
            txtname = itemView.findViewById(R.id.name);
            txtname2 = itemView.findViewById(R.id.add);
            textView3=itemView.findViewById(R.id.num);
            textView4=itemView.findViewById(R.id.est);

            //itemView.setOnClickListener((View.OnClickListener) this);
            itemView.setOnLongClickListener(this);
        }

//        @Override
//        public void onClick(View v) {
//            if (clicklistener != null) {
//                clicklistener.itemClicked(v, getAdapterPosition());
//            }
//        }



        @Override
        public boolean onLongClick(View v) {
            if (clicklistener != null) {
                clicklistener.onLongClick(v,getAdapterPosition());
            }
            return false;
        }
    }

    public void setClickListener(ClickListener clicklistener) {
        this.clicklistener = clicklistener;
    }

   /* @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            Holder holder=new Holder();
            convertView = inf.inflate(R.layout.card_row_item, null);
             holder.txtname = convertView.findViewById(R.id.name);
             holder.txtname2 = convertView.findViewById(R.id.add);

            //ImageView imageView = convertView.findViewById(R.id.imageView7);

            holder.txtname.setText(name[position]);
            holder.txtname2.setText(add[position]);


        }
        return convertView;
    }*/
}
