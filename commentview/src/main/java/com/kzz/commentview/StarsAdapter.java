package com.kzz.commentview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * 评价星星的自定义view
 * Created by kezhangzhao on 2018/2/12.
 */

public class StarsAdapter extends RecyclerView.Adapter<StarsAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private int yellowNum = 0;
    private int grayNum = 5;
    private ArrayList<Boolean> listData = new ArrayList<>();
    private int mStarsHeight = 0;//星星的高度
    private int mStarsWidth = 0;//星星的宽度
    private int mPaddingLeft = 0;//左
    private int mPaddingTop = 0;//上
    private int mPaddingRight = 0;//右
    private int mPaddingBottom = 0;//下
    private int mImageYellow = R.drawable.stars_yellow;//图片：默认是黄色星星
    private int mImageGray = R.drawable.stars_gray;//图片：默认是黄色星星
    private OnMyItemClickListener listener;//item点击事件监听


    /**
     * 构造方法
     *
     * @param context   context
     * @param yellowNum 黄色星星数量
     * @param grayNum   灰色星星数量
     */
    public StarsAdapter(Context context, int yellowNum, int grayNum) {
        this.mContext = context;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.yellowNum = yellowNum;
        this.grayNum = grayNum;
        for (int i = 0; i < yellowNum; i++) {
            listData.add(true);
        }
        if (grayNum - yellowNum > 0) {
            for (int j = 0; j < grayNum - yellowNum; j++) {
                listData.add(false);
            }
        }

    }

    /**
     * 构造方法
     * 使用默认星星数量，灰色5个，黄色0个。
     * 可以通过方法重新进行设置星星数量
     *
     * @param context context
     */
    StarsAdapter(Context context) {
        this.mContext = context;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < yellowNum; i++) {
            listData.add(true);
        }
        if (grayNum - yellowNum > 0) {
            for (int j = 0; j < grayNum - yellowNum; j++) {
                listData.add(false);
            }
        }
    }

    /**
     * 设置星星的颜色
     *
     * @param pictureId 图片id
     */
    public void setStarsColor(int pictureId) {
        this.mImageYellow = pictureId;
    }

    /**
     * 设置图片
     *
     * @param imageYellow 成功点评的黄色星星图片
     * @param imageGray   灰色星星图片
     */
    public void setImage(int imageYellow, int imageGray) {
        this.mImageYellow = imageYellow;
        this.mImageGray = imageGray;
    }

    /**
     * item点击事件监听
     */
    public interface OnMyItemClickListener {
        void myClick(View view, int position);
    }

    /**
     * 设置item监听
     *
     * @param listener OnMyItemClickListener
     */
    public void setOnMyItemClickListener(OnMyItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * 设置星星数量
     *
     * @param yellowNum 黄色星星数量
     * @param grayNum   灰色星星数量
     */
    public void setStarsNum(int yellowNum, int grayNum) {
        this.yellowNum = yellowNum;
        this.grayNum = grayNum;
        listData.clear();
        for (int i = 0; i < yellowNum; i++) {
            listData.add(true);
        }
        if (grayNum - yellowNum > 0) {
            for (int j = 0; j < grayNum - yellowNum; j++) {
                listData.add(false);
            }
        }
    }

    /**
     * 设置单个星星图片的宽高
     *
     * @param starsWidth  Width
     * @param starsHeight Height
     */
    public void setStarsWidthAndHeight(int starsWidth, int starsHeight) {
        this.mStarsWidth = starsWidth;
        this.mStarsHeight = starsHeight;
    }


    /**
     * 设置单个星星的padding
     *
     * @param paddingLeft   左边距
     * @param paddingTop    上边距
     * @param paddingRight  右边距
     * @param paddingBottom 底边距
     */
    public void setPadding(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        this.mPaddingLeft = paddingLeft;
        this.mPaddingTop = paddingTop;
        this.mPaddingRight = paddingRight;
        this.mPaddingBottom = paddingBottom;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.stars_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(mStarsWidth, mStarsHeight);
        holder.imageView.setLayoutParams(layoutParams);
        holder.imageView.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
        holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.layoutVeiw.removeAllViews();
        if (listData.get(position)) {
            holder.imageView.setImageResource(mImageYellow);
        } else {
            holder.imageView.setImageResource(mImageGray);
        }
        holder.layoutVeiw.addView(holder.imageView);
        if (listener != null) {//设置监听
            holder.layoutVeiw.setOnClickListener(view -> listener.myClick(view, position));
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    /**
     * adapter的ViewHolder
     */
    class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout layoutVeiw;
        ImageView imageView;

        MyViewHolder(View itemView) {
            super(itemView);
            layoutVeiw = itemView.findViewById(R.id.layout_view);
            imageView = new ImageView(mContext);
        }
    }
}
