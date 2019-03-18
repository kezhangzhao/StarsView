package com.kzz.starsview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.kzz.commentview.StarsView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int allStarsNum = 5;//总星星数量
        StarsView starsView = findViewById(R.id.stars_view);
        //设置星星数量
        starsView.setmStarsNum(3, allStarsNum);
        //设置单个星星的宽高
        starsView.setStarsWidthAndHeight(this, 27, 27);
        //设置单个星星的padding
        starsView.setPadding(8, 8, 8, 8);
        starsView.setOnMyItemClickListener((view, position) -> {
            starsView.setmStarsNum(position + 1, allStarsNum);//设置黄色星星和灰色星星数量
            Toast.makeText(MainActivity.this, Integer.toString(position), Toast.LENGTH_SHORT).show();
        });
    }
}
