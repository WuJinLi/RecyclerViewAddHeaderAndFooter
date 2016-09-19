package come.example.my.recyclerviewaddheader;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by My on 2016/9/19.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> list;
    private LayoutInflater inflater;
    //设置不同头视图，底部视图，普通视图的类型
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_NORMAL = 2;

    private View headerView;
    private View footerView;

    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getHeaderView() {
        return headerView;
    }

    public void setHeaderView(View headerView) {
        this.headerView = headerView;
        //将头视图添加到第一个item的位置上
        notifyItemInserted(0);
    }

    public View getFooterView() {
        return footerView;
    }

    public void setFooterView(View footerView) {
        this.footerView = footerView;
        //将底部视图添加在最后的item上
        notifyItemInserted(getItemCount() - 1);
    }

    //根据不同的viewType返回不同的值
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headerView != null && viewType == TYPE_HEADER) {
            return new MyViewHolder(headerView);
        }
        if (footerView != null && viewType == TYPE_FOOTER) {
            return new MyViewHolder(footerView);
        }
        View normal = inflater.inflate(R.layout.item_recyclerview, parent, false);
        return new MyViewHolder(normal);
    }

    //绑定View，这里是根据返回的这个position的类型，从而进行绑定的，
    // HeaderView和FooterView, 就不同绑定了
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == TYPE_NORMAL) {
            if (holder instanceof MyViewHolder) {
                ((MyViewHolder) holder).item.setText(list.get(position-1));
            }
            return;
        } else if (getItemViewType(position) == TYPE_HEADER) {
            return;
        } else {
            return;
        }
    }

    //由于添加了头视图和底部视图，这样数据源的数量是传入集合的长度和头视图，底部视图的数量总和
    @Override
    public int getItemCount() {
        if (headerView == null && footerView == null) {
            return list.size();
        } else if (headerView == null && footerView != null) {
            return list.size() + 1;
        } else if (headerView != null && footerView == null) {
            return list.size() + 1;
        } else {
            return list.size() + 2;
        }
    }

    //根据position类型返回不同的类型，加载不同的视图
    @Override
    public int getItemViewType(int position) {
        if (headerView == null && footerView == null) {
            return TYPE_NORMAL;
        }
        if (position == 0) {
            return TYPE_HEADER;
        }
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item;

        //itemview对应的视图进行不同布局的加载
        public MyViewHolder(View itemView) {
            super(itemView);
            if (itemView == headerView) {
                return;
            }
            if (itemView == footerView) {
                return;
            }
            item = (TextView) itemView.findViewById(R.id.item);
        }
    }
}
