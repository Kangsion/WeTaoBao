package com.example.ksion.wetaobao.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.bean.Discuss;
import com.example.ksion.wetaobao.bean.User;
import com.example.ksion.wetaobao.config.Contracts;
import com.example.ksion.wetaobao.contract.GoodDetailsContract;
import com.squareup.picasso.Picasso;


import java.util.List;
import java.util.zip.Inflater;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 评论的适配器
 *
 * Created by 11655 on 2016/10/14.
 */

public class DiscussXlvAdapter extends BaseAdapter {
    //定义一个数据源
    private List<Discuss> discussList;
    private Context context;
    LayoutInflater inflater;
    public DiscussXlvAdapter(List<Discuss> discussList, Context context) {
        this.discussList = discussList;
        this.context = context;
        inflater=LayoutInflater.from(context);
        Log.e("d","构造 加载");
    }

    @Override
    public int getCount() {
        return discussList.size();
    }

    @Override
    public Object getItem(int position) {
        return discussList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder = null;
        Log.e("s","convertView 加载");
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.act_details_xlv_discuss_item, null);
            viewHolder = new ViewHolder();
            //获取控件
            viewHolder.circleImageViewHead = (CircleImageView) convertView.findViewById(R.id.act_detials_xlv_item_civ_head);
            viewHolder.imgSex = (ImageView) convertView.findViewById(R.id.act_detials_xlv_item_iv_sex);
            viewHolder.tvDiscussText = (TextView) convertView.findViewById(R.id.act_detials_xlv_item_tv_discuss_text);
            viewHolder.tvUname = (TextView) convertView.findViewById(R.id.act_detials_xlv_item_tv_usename);
            viewHolder.tvDiscusTime = (TextView) convertView.findViewById(R.id.act_detials_xlv_item_tv_discuss_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置数据
        viewHolder.tvDiscussText.setText(discussList.get(position).getDiscussText());
        viewHolder.tvDiscusTime.setText(discussList.get(position).getCreatedAt());
        Log.e("time",discussList.get(position).getCreatedAt());
        //查询头像信息
        final ViewHolder finalViewHolder = viewHolder;
        //根据用户Id查询用户信息
        String sql="select * from User where phone='"+discussList.get(position).getPhone()+"'";
//        new BmobQuery<User>().doSQLQuery(context,sql, new SQLQueryListener<User>() {
//            @Override
//            public void done(BmobQueryResult<User> bmobQueryResult, BmobException e) {
//
//                    if(bmobQueryResult!=null) {
//                           User user=bmobQueryResult.getResults().get(0);
//                           Log.e("user",user.getPhone());
//                        if (user.getUserHead().getUrl() == null) {//头像为空,则显示默认头像
//                            Picasso.with(context).load(Contracts.DEFALT_HEAD_URL).into(finalViewHolder.circleImageViewHead);
//                        } else {
//                            //查询成功,设置头像
//                            Picasso.with(context).load(user.getUserHead().getUrl()).into(finalViewHolder.circleImageViewHead);
//                        }
//                        //设置用户名
//                        finalViewHolder.tvUname.setText(user.getUserName());
//                        //设置性别
//                        if (user.getSex() == "男") {
//                            finalViewHolder.imgSex.setImageResource(R.drawable.boy);
//                        } else {
//                            finalViewHolder.imgSex.setImageResource(R.drawable.grils);
//                        }
//                    }
//                }
//        });
        return convertView;
    }

    class ViewHolder {
        CircleImageView circleImageViewHead;
        ImageView imgSex;
        TextView tvUname, tvDiscussText, tvDiscusTime;
    }
}
