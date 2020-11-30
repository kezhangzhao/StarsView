package com.kzz.commentview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class FloatStarsView extends LinearLayout {
    private Context mContext;
    private RecyclerView mStarsList;//星星列表
    private FloatStarsAdapter mStarsAdapter;//星星的adapter
    private FloatStarsAdapter.OnMyItemClickListener listener;//item点击事件监听

    public FloatStarsView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public FloatStarsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_stars, this);
        mStarsList = findViewById(R.id.list_stars);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mStarsList.setLayoutManager(linearLayoutManager);
        mStarsAdapter = new FloatStarsAdapter(mContext);
        mStarsList.setAdapter(mStarsAdapter);
        mStarsList.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 设置星星的颜色
     *
     * @param pictureId 图片id
     */
    public void setStarsColor(int pictureId) {
        mStarsAdapter.setStarsColor(pictureId);
    }

    /**
     * 设置item点击事件
     *
     * @param listener OnMyItemClickListener
     */
    public void setOnMyItemClickListener(StarsAdapter.OnMyItemClickListener listener) {
        mStarsAdapter.setOnMyItemClickListener(listener);
    }


    /**
     * 设置两种状态的图片
     *
     * @param imageYellow 成功点评的黄色星星图片
     * @param imageGray   灰色星星图片
     */
    public void setImage(int imageYellow, int imageGray) {
        mStarsAdapter.setImage(imageYellow, imageGray);
        mStarsAdapter.notifyDataSetChanged();
    }

    /**
     * 设置半颗星的分界点
     *
     * @param division 0-1。默认是0.5
     */
    public void setDivision(float division) {
        mStarsAdapter.setDivision(division);
    }

    /**
     * 设置星星数量
     *
     * @param yellowNum 黄色数量
     * @param grayNum   灰色数量
     */
    public void setmStarsNum(float yellowNum, int grayNum) {
        mStarsAdapter.setStarsNum(yellowNum, grayNum);
        mStarsAdapter.notifyDataSetChanged();
    }

    /**
     * 设置单个星星的宽度和高度
     *
     * @param width  宽
     * @param height 高
     */
    public void setStarsWidthAndHeight(int width, int height) {
        mStarsAdapter.setStarsWidthAndHeight(width, height);
        mStarsAdapter.notifyDataSetChanged();
    }

    /**
     * 设置单个星星的宽度和高度(单位转换为了px)
     *
     * @param width  宽(dp)
     * @param height 高(dp)
     */
    public void setStarsWidthAndHeight(Context context, float width, float height) {
        mStarsAdapter.setStarsWidthAndHeight(dp2px(context, width), dp2px(context, height));
        mStarsAdapter.notifyDataSetChanged();
    }

    /**
     * Value of dp to value of px.
     *
     * @param dpValue The value of dp.
     * @return value of px
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 设置单个星星的padding
     *
     * @param paddingLeft   左
     * @param paddingTop    上
     * @param paddingRight  右
     * @param paddingBottom 下
     */
    public void setPadding(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        mStarsAdapter.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        mStarsAdapter.notifyDataSetChanged();
    }
}
