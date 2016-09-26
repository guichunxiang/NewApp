package newsday.zhuoxing.com.newsapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import newsday.zhuoxing.com.newsapp.R;
import newsday.zhuoxing.com.newsapp.entity.News;

/**
 * Created by lenovo on 2016/8/9.
 */
public class LikeListAdapter extends RecyclerView.Adapter<LikeListAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<News> likeList;

    public LikeListAdapter(Context context, ArrayList<News> likeList) {
        this.context = context;
        this.likeList = likeList;
    }

    View view;
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView summary,title;
        ImageView icon;
        public MyViewHolder(View itemView) {
            super(itemView);
            summary= (TextView) itemView.findViewById(R.id.summary_item);
            title= (TextView) itemView.findViewById(R.id.title_item);
            icon= (ImageView) itemView.findViewById(R.id.imageView_item);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.title.setText(likeList.get(position).getTitle());
        holder.summary.setText(likeList.get(position).getSummary());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onItemClick(position);
                }
            }
        });
    }
    public interface onItemClickListener{
        void onItemClick(int position);
    }
    private onItemClickListener listener;
    public void setOnItemClickListener(onItemClickListener listener){
        this.listener=listener;
    }

    @Override
    public int getItemCount() {
        return likeList.size();
    }
}
