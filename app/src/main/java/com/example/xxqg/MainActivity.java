package com.example.xxqg;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<ListItem> itemList = new ArrayList<>();

        itemList.add(new ListItem("融媒建设|中山广播电视台香山文化频道启播一周年","01:54","https://www.xuexi.cn/local/normalTemplate.html?itemId=4474540657282928024","https://region-guangdong-resource.xuexi.cn/video/1006/p/88e22359fe3f3e20461a2fd825f5b6c7-fa524812a37949e7b218729a5064b6ee-2.mp4"));
        itemList.add(new ListItem("广东：4K超高清频道呼号正式发布","00:31","https://www.xuexi.cn/lgpage/detail/index.html?id=18426252183650326229&amp;item_id=18426252183650326229","https://boot-video.xuexi.cn/video/1006/p/b7223ba15c29beca2276cc159ec5673a-f8f4f80a3f114927aaffe0369bf06fe7-2.mp4"));
        itemList.add(new ListItem("央广“云听中国”贵州频道上线 赋能多彩文化品牌高质量传播","00:24","https://www.xuexi.cn/local/normalTemplate.html?itemId=2298383577111983917","https://region-guizhou-resource.xuexi.cn/video/1006/p/e543948ebc1df65cb6e72c667b86f16c-c9a570e331bf4117b2f048496de67b39-2.mp4"));
        itemList.add(new ListItem("电影频道记者观影vlog：感谢电影 让这个春节年味更足","03:41","https://www.xuexi.cn/lgpage/detail/index.html?id=8914180914065475121&amp;item_id=8914180914065475121","https://boot-video.xuexi.cn/video/1006/p/08399ab1536f13fed12c083286d38147-9cf80ba1dba640d89066333f6b235eb8-2.mp4"));
        itemList.add(new ListItem("2022电影频道传媒荣誉之夜巡礼：最受传媒关注入围女主角","06:46","https://www.xuexi.cn/lgpage/detail/index.html?id=6668136883674377495&amp;item_id=6668136883674377495","https://boot-video.xuexi.cn/video/1006/p/1028d6f6b251390fe97c779682d7be8c-be31864e79ab43a6a2a94b678a6ebeab-2.mp4"));
        itemList.add(new ListItem("主播教你用强国：如何用好老年频道","02:17","https://www.xuexi.cn/lgpage/detail/index.html?id=13740840463021445196&amp;item_id=13740840463021445196","https://boot-video.xuexi.cn/video/1006/p/c38172c3656bc9671de104fc2488e6cc-a1c2e8ba8cb14c7baea0499d0bda0744-2.mp4"));

        CustomListAdapter adapter = new CustomListAdapter(this, itemList);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}