package come.example.my.recyclerviewaddheader;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView_main;
    private MyAdapter myAdapter;
    private List<String> list = new ArrayList<>();
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView_main = (RecyclerView) findViewById(R.id.recyclerView_main);
        //初始化数据源
        list = getData();
        //初始化适配器
        myAdapter = new MyAdapter(context, list);
        //初始化布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        //设置布局管理器
        recyclerView_main.setLayoutManager(linearLayoutManager);
        recyclerView_main.setHasFixedSize(true);
        //设置适配器
        recyclerView_main.setAdapter(myAdapter);


        setHeaderView(recyclerView_main);
        setFooterView(recyclerView_main);
    }


    //调用添加头视图的方法
    public void setHeaderView(RecyclerView recyclerView) {
        View headView = getLayoutInflater().inflate(R.layout.headview, null);
        myAdapter.setHeaderView(headView);
    }

    //自定义加载底部视图的方法
    public void setFooterView(RecyclerView recyclerView) {
        View footerView = getLayoutInflater().inflate(R.layout.footview, null);
        myAdapter.setFooterView(footerView);
    }


    //初始化数据源的方法
    public List<String> getData() {
        List<String> listData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            listData.add("Data" + i);
        }
        return listData;
    }
}
