package newsday.zhuoxing.com.newsapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import newsday.zhuoxing.com.newsapp.R;

/**
 * Created by Administrator on 2016/9/7.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> datas;
    private Context context;
    private LayoutInflater layoutInflater;
    private View view;
    private List<Integer> height;
    private OnItemClickActionListener listent;

    public interface OnItemClickActionListener{
        void onClickActionListent(View view,int position);
        void onLongClickActionListent(View view,int position);
    }
    public OnItemClickActionListener getListent(){
        return listent;
    }
    public void setListent(OnItemClickActionListener listent){
        this.listent = listent;
    }
    public RecycleAdapter(List<String> datas, Context context) {
        this.datas = datas;
        this.layoutInflater = LayoutInflater.from(context);
        this.height = new ArrayList<>();
        for (int i=0;i<datas.size();i++){
            height.add((int)(100+Math.random()*80));
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = layoutInflater.inflate(R.layout.activity_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder,final int position) {
        ViewGroup.LayoutParams params = ((ViewHolder)holder).textView.getLayoutParams();
        params.height = height.get(position);
        params.width = height.get(position);
        ((ViewHolder)holder).textView.setLayoutParams(params);
        ((ViewHolder)holder).textView.setText(datas.get(position));
        if (listent != null){
            ((ViewHolder)holder).textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listent.onClickActionListent(holder.itemView,holder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.tv_item);
        }
    }



}
