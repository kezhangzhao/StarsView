# 评价星星自定义view
# 依赖方式
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  	dependencies {
	        implementation 'com.github.kezhangzhao:StarsView:1.0.1'
	}
 # 1、在XML布局文件添加：
	    <com.kzz.commentview.StarsView
        android:id="@+id/stars_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"/>
# 2、使用方法：	  
        int allStarsNum = 5;//总星星数量
        StarsView starsView = findViewById(R.id.stars_view);
        //设置星星数量
        starsView.setmStarsNum(3, allStarsNum);
        //设置单个星星的宽高
        starsView.setStarsWidthAndHeight(this, 27, 27);
        //设置单个星星的padding
        starsView.setPadding(8, 8, 8, 8);
        //监听方法
        starsView.setOnMyItemClickListener((view, position) -> {
            starsView.setmStarsNum(position + 1, allStarsNum);//设置黄色星星和灰色星星数量
            Toast.makeText(MainActivity.this, Integer.toString(position), Toast.LENGTH_SHORT).show();
        });
