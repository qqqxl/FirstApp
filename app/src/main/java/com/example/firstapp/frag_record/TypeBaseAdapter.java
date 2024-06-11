package com.example.firstapp.frag_record;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firstapp.R;
import com.example.firstapp.db.TypeBean;

import java.util.List;

public class TypeBaseAdapter extends BaseAdapter {
     Context context;
    List<TypeBean> mDatas;
    int mSelectPos =0;
    public TypeBaseAdapter(Context context, List<TypeBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recorad_gv, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.iv = convertView.findViewById(R.id.item_iv);
            viewHolder.tv = convertView.findViewById(R.id.item_tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        TypeBean typeBean = mDatas.get(position);
        viewHolder.tv.setText(typeBean.getTypename());

        if (mSelectPos == position) {
            viewHolder.iv.setImageResource(typeBean.getYesId());
        } else {
            viewHolder.iv.setImageResource(typeBean.getNotId());
        }

        return convertView;
    }

    public void setSelectPos(int SelectPos) {
        mSelectPos = SelectPos;
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        ImageView iv;
        TextView tv;
    }
}
