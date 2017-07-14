package com.slkk.test_recyclerviewandcardview;

import android.content.Context;
import android.media.Image;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * Created by dell on 2017/7/14.
 */

public class NewListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int NORMAL_ITEM = 0;
    private static final int TIME_ITEM = 1;
    private Context mContext;
    private List<NewListEntry> mDataList;
    private LayoutInflater layoutInflater;

    public NewListAdapter(Context context, List<NewListEntry> dataList) {
        mContext = context;
        mDataList = dataList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == NORMAL_ITEM) {
            return new NormalViewHolder(layoutInflater.inflate(R.layout.fragment_base_swipe_list, parent, false));
        } else {
            return new GroupViewHolder(layoutInflater.inflate(R.layout.fragment_base_swipe_group_item, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        NewListEntry newListEntry = mDataList.get(position);
        if (holder instanceof GroupViewHolder) {
            GroupViewHolder gholder = (GroupViewHolder) holder;
            gholder.time_texgt.setText("SHIJING");
            gholder.text_title.setText("gTitle");
            gholder.news_image.setImageResource(R.mipmap.ic_launcher);

        } else {
            NormalViewHolder gholder = (NormalViewHolder) holder;

            gholder.text_title.setText("gTitle");
            gholder.news_image.setImageResource(R.mipmap.ic_launcher);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    private void bindGroupData(RecyclerView.ViewHolder holder) {

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder {
        TextView text_title;
        ImageView news_image;
        LinearLayout container;

        public NormalViewHolder(View itemView) {
            super(itemView);
            text_title = (TextView) itemView.findViewById(R.id.base_swipe_item_title);
            news_image = (ImageView) itemView.findViewById(R.id.base_swipe_item_icon);
            container = (LinearLayout) itemView.findViewById(R.id.base_swipe_item_container);
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(), "新闻详情", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public class GroupViewHolder extends NormalViewHolder {

        TextView time_texgt;

        public GroupViewHolder(View itemView) {
            super(itemView);
            time_texgt = (TextView) itemView.findViewById(R.id.base_swipe_group_item_time);

        }
    }
}
