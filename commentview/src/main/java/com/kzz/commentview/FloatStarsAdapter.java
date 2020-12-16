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

public class FloatStarsAdapter extends RecyclerView.Adapter<FloatStarsAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private float yellowNum = 0;
    private int grayNum = 5;
    private ArrayList<Integer> listData = new ArrayList<>();
    private int mStarsHeight = ViewGroup.LayoutParams.WRAP_CONTENT;//星星的高度
    private int mStarsWidth = ViewGroup.LayoutParams.WRAP_CONTENT;//星星的宽度
    private int mPaddingLeft = 0;//左
    private int mPaddingTop = 0;//上
    private int mPaddingRight = 0;//右
    private int mPaddingBottom = 0;//下
    private int mImageYellow = R.drawable.stars_yellow;//图片：默认是黄色星星
    private int mImageHalfYellow = R.drawable.stars_half_yellow;//图片：默认是黄色星星
    private int mImageGray = R.drawable.stars_gray;//图片：默认是黄色星星
    private StarsAdapter.OnMyItemClickListener listener;//item点击事件监听
    private float division = 0.5f;//半颗星分界点


    /**
     * 构造方法
     *
     * @param context   context
     * @param yellowNum 黄色星星数量
     * @param grayNum   灰色星星数量
     */
    public FloatStarsAdapter(Context context, float yellowNum, int grayNum) {
        this.mContext = context;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.yellowNum = yellowNum;
        this.grayNum = grayNum;
        for (int i = 0; i < yellowNum; i++) {
            if (i +1<= yellowNum)
                listData.add(2);
            else if (i +division<= yellowNum)
                listData.add(1);
        }
        if (grayNum - yellowNum > division) {
            for (int j = 0; j + division < grayNum - yellowNum; j++) {
                listData.add(0);
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
    FloatStarsAdapter(Context context) {
        this.mContext = context;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < yellowNum; i++) {
            if (i +1<= yellowNum)
                listData.add(2);
            else if (i +division<= yellowNum)
                listData.add(1);
        }
        if (grayNum - yellowNum > division) {
            for (int j = 0; j + division < grayNum - yellowNum; j++) {
                listData.add(0);
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
     * 设置半颗星的分界点
     * @param division 0-1。默认是0.5
     */
    public void setDivision(float division) {
        this.division = division;
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
    public void setOnMyItemClickListener(StarsAdapter.OnMyItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * 设置星星数量
     *
     * @param yellowNum 黄色星星数量
     * @param grayNum   灰色星星数量
     */
    public void setStarsNum(float yellowNum, int grayNum) {
        this.yellowNum = yellowNum;
        this.grayNum = grayNum;
        listData.clear();
        for (int i = 0; i < yellowNum; i++) {
            if (i +1<= yellowNum)
                listData.add(2);
            else if (i +division<= yellowNum)
                listData.add(1);
        }
        if (grayNum - yellowNum > division) {
            for (int j = 0; j + division < grayNum - yellowNum; j++) {
                listData.add(0);
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
    public void onBindViewHolder(@NonNull FloatStarsAdapter.MyViewHolder holder, final int position) {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(mStarsWidth, mStarsHeight);
        holder.imageView.setLayoutParams(layoutParams);
        holder.imageView.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
        holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.layoutVeiw.removeAllViews();
        if (listData.get(position) == 2) {
            holder.imageView.setImageResource(mImageYellow);
        } else if ((listData.get(position) == 1)) {
            holder.imageView.setImageResource(mImageHalfYellow);
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
