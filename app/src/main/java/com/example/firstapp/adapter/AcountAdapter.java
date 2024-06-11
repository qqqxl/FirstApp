package com.example.firstapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firstapp.R;
import com.example.firstapp.db.AcountBean;

import java.util.Calendar;
import java.util.List;

public class AcountAdapter extends BaseAdapter {
    Context context;
    List<AcountBean>mDatas;
    LayoutInflater inflater;
    int year,month,day;

    public AcountAdapter(Context context, List<AcountBean> mData) {
        this.context = context;
        this.mDatas = mData;
        inflater=LayoutInflater.from(context);
        Calendar calendar = Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH)+1;
        day=calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.item_mainlv,parent,false);
             holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
             holder =(ViewHolder) convertView.getTag();
        }
        AcountBean bean = mDatas.get(position);
        holder.itemIv.setImageResource(bean.getYesId());
        holder.itemTv.setText(bean.getTypename());
        holder.beizhuItem.setText(bean.getBeizhu());
        holder.moneyItem.setText(String.valueOf(bean.getMoney()));
        if (bean.getYear()==year&&bean.getMonth()==month&&bean.getDay()==day){

            holder.timeItem.setText("今日");
        }else {
            holder.timeItem.setText(bean.getTime());
        }

        return convertView;
    }
    class  ViewHolder{
        ImageView itemIv;
        TextView itemTv, beizhuItem, timeItem, moneyItem;
        public ViewHolder(View btn){
            itemIv =btn.findViewById(R.id.main_lv);
            itemTv =btn.findViewById(R.id.item_main_title);
            beizhuItem =btn.findViewById(R.id.item_main_beizhu);
            moneyItem =btn.findViewById(R.id.item_money);
            timeItem =btn.findViewById(R.id.item_time);
        }
    }
}
