package com.liang.Plane;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
/**
 * 飞行棋--开始游戏--view类
 * @author 梁进劲
 * @date 2012-07-08
 * @declare 版权所有 &copy 梁进劲
 *
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback{
	/**SurfaceHolder*/
	private SurfaceHolder holder = null;
	/**ApplicationDate 运行时共享数据*/
	private ApplicationDate appDate = null;
	/**activity context*/
	private Context context = null;
	
	private Paint paint = null;
	/**工具类*/
//	private Utils utils = null;
	
	//布局数据
	/**上面游戏界面高度 08%*/
	private int aboveHeight = 0; 
	/**下面相关选项高度 screenHeight - aboveHeight*/
//	private int belowHeight = 0;
	/**下面左边选框宽度 60%*/
	private int belowLeftWidth = 0;
	/**下面右边框宽度 screenWidth - belowLeftWidth*/
//	private int belowRightWidth = 0;
	
	
	/**
	 * 构造
	 * @param context
	 */
	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
		appDate = (ApplicationDate)context.getApplicationContext();
		
		holder = getHolder();
		holder.addCallback(this);
		this.setFocusable(true);
		
		paint = new Paint();
		paint.setColor(Color.CYAN);
		paint.setStrokeWidth(5f);
		
//		utils = new Utils();
	}
	
	/**开始绘制*/
	public void drawGame(){
		Canvas canvas = holder.lockCanvas();
		
		Bitmap bitmap = null;
		Bitmap tempBitmap = null;
		
		//画背景色
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_bg);
		tempBitmap = Bitmap.createScaledBitmap(bitmap,appDate.getScreenWidth(), appDate.getScreenHeight(),true);
		bitmap.recycle();
		canvas.drawBitmap(tempBitmap, 0, 0, paint);
		tempBitmap.recycle();
		
		//画边框
		canvas.drawLine(0, aboveHeight, appDate.getScreenWidth(), aboveHeight, paint);
		canvas.drawLine(belowLeftWidth, aboveHeight, belowLeftWidth, appDate.getScreenHeight(), paint);
		
		
		canvas.drawRect(0, 0, 50, 50, paint);
		
		holder.unlockCanvasAndPost(canvas);
		
	}
	
	
	
	

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		System.out.println("surface change ...."+width+":"+height);
		appDate.setScreenWidth(width);
		appDate.setScreenHeight(height);
		
		aboveHeight = (int)(height * 0.8D);
//		belowHeight = height - aboveHeight;
		belowLeftWidth = (int)(width * 0.6D);
//		belowRightWidth = width - belowLeftWidth;
		
		drawGame();//开始绘制
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		System.out.println("surface create .......");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		System.out.println("surface destory......");
	}
	
}
