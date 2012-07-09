package com.liang.Plane;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 飞行棋--开始游戏--view类
 * @author 梁进劲
 * @date 2012-07-08
 * @declare 版权所有 &copy 梁进劲
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
	
	
	/**关卡类*/
	private GameTypes gameTypes = null;
	/**
	 * 单元格数据，以数组&quot;下标&quot;为编号，记录当单元格的起始点。<br/>
	 * CELL_LIST_X_KEY :x坐标<br/>CELL_LIST_Y_KEY:y坐标
	 */
	private List<Map<String,Integer>> cellList = null;
	
	/**游戏路径数据*/
	private List<Cell> gameDate = null;
	/**色子资源*/
	private int seResource = R.drawable.s1;
	/**是否正在滚动色子*/
	private boolean seRocking = false;
	
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
		
		//画路径
		Cell cell = null;
		for(int i=0 ; i<gameDate.size() ; i++){
			cell = gameDate.get(i);
			float x = cellList.get(cell.getId()).get(GlobleTypes.CELL_LIST_X_KEY);
			float y = cellList.get(cell.getId()).get(GlobleTypes.CELL_LIST_Y_KEY);
			
			if(i==0){//画起点
				bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.start_pos);
				tempBitmap = Bitmap.createScaledBitmap(bitmap,gameTypes.getCellWidth(), gameTypes.getCellHeight(),true);
				bitmap.recycle();
				canvas.drawBitmap(tempBitmap, x, y, paint);
				tempBitmap.recycle();
			}else if(i == (gameDate.size() -1)){//画终点
				bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.end_pos);
				tempBitmap = Bitmap.createScaledBitmap(bitmap,gameTypes.getCellWidth(), gameTypes.getCellHeight(),true);
				bitmap.recycle();
				canvas.drawBitmap(tempBitmap, x, y, paint);
				tempBitmap.recycle();
			}else{ //画中间点
				
				if(i%2 == 0){
					bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.go_pos);
				}else{
					bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.back_pos);
				}
				tempBitmap = Bitmap.createScaledBitmap(bitmap,gameTypes.getCellWidth(), gameTypes.getCellHeight(),true);
				bitmap.recycle();
				canvas.drawBitmap(tempBitmap, x, y, paint);
				tempBitmap.recycle();
			}
			
			paint.setColor(Color.RED);
			canvas.drawText(String.valueOf(i+1), x, y+10, paint);
			
		}
		
		//画角色一
		cell = gameDate.get(0);
		float x = cellList.get(cell.getId()).get(GlobleTypes.CELL_LIST_X_KEY);
		float y = cellList.get(cell.getId()).get(GlobleTypes.CELL_LIST_Y_KEY);
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.head1);
		tempBitmap = Bitmap.createScaledBitmap(bitmap,gameTypes.getCellWidth()/2, gameTypes.getCellHeight()/2,true);
		bitmap.recycle();
		canvas.drawBitmap(tempBitmap, x+gameTypes.getCellWidth()/4, y, paint);
		tempBitmap.recycle();
		
		//画角色二
		cell = gameDate.get(0);
		x = cellList.get(cell.getId()).get(GlobleTypes.CELL_LIST_X_KEY);
		y = cellList.get(cell.getId()).get(GlobleTypes.CELL_LIST_Y_KEY);
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.head2);
		tempBitmap = Bitmap.createScaledBitmap(bitmap,gameTypes.getCellWidth()/2, gameTypes.getCellHeight()/2,true);
		bitmap.recycle();
		canvas.drawBitmap(tempBitmap, x+gameTypes.getCellWidth()/4, y+gameTypes.getCellHeight()/2, paint);
		tempBitmap.recycle();
		
		
		//画色子
		bitmap = BitmapFactory.decodeResource(context.getResources(), seResource);
		tempBitmap = Bitmap.createScaledBitmap(bitmap,50,50,true);
		bitmap.recycle();
		canvas.drawBitmap(tempBitmap,
							belowLeftWidth + (appDate.getScreenWidth() - belowLeftWidth - 50)/2, 
							aboveHeight + (appDate.getScreenHeight() - aboveHeight - 50)/2, 
							paint);
		tempBitmap.recycle();
		
		//画我的状态
		cell = gameDate.get(0);
		x = cellList.get(cell.getId()).get(GlobleTypes.CELL_LIST_X_KEY);
		y = cellList.get(cell.getId()).get(GlobleTypes.CELL_LIST_Y_KEY);
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.head1);
		tempBitmap = Bitmap.createScaledBitmap(bitmap,gameTypes.getCellWidth()/4, gameTypes.getCellHeight()/4,true);
		bitmap.recycle();
		canvas.drawBitmap(tempBitmap, 5,aboveHeight+5, paint);
		canvas.drawText(":21  <==>",5+tempBitmap.getWidth() , aboveHeight+5+10, paint);
		tempBitmap.recycle();
		
		//画我对手状态
		cell = gameDate.get(0);
		x = cellList.get(cell.getId()).get(GlobleTypes.CELL_LIST_X_KEY);
		y = cellList.get(cell.getId()).get(GlobleTypes.CELL_LIST_Y_KEY);
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.head2);
		tempBitmap = Bitmap.createScaledBitmap(bitmap,gameTypes.getCellWidth()/4, gameTypes.getCellHeight()/4,true);
		bitmap.recycle();
		canvas.drawBitmap(tempBitmap, 5+tempBitmap.getWidth()+60,aboveHeight+5, paint);
		canvas.drawText(":5",5+tempBitmap.getWidth()+60+tempBitmap.getWidth() , aboveHeight+5+10, paint);
		tempBitmap.recycle();
		
		
		//画提示
		canvas.drawText("遇到狼，退一步",5 , aboveHeight+5+10+20, paint);
		
		canvas.drawText("你是好样的，进一步",5 , aboveHeight+5+10+20+20, paint);
		
		canvas.drawText("你是好样的，进两步",5 , aboveHeight+5+10+20+20+20, paint);
		
		canvas.drawText("你是好样的，进三步",5 , aboveHeight+5+10+20+20+20+20, paint);
		
		canvas.drawText("你是好样的，进四步",5 , aboveHeight+5+10+20+20+20+20+20, paint);
		
		holder.unlockCanvasAndPost(canvas);
		
	}
	
	/**滚动色子线程*/
	private class SeRock extends Thread{
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			int times = 1;
			int sleepSpec = 10;//休眠间隔
			for(int i=0 ; i<10 ; i++){
				try{
					seRocking = true;
					if(times == 1){
						seResource = R.drawable.s1;
					}else if(times == 2){
						seResource = R.drawable.s2;
					}else if(times == 3){
						seResource = R.drawable.s3;
					}else if(times == 4){
						seResource = R.drawable.s4;
					}else if(times == 5){
						seResource = R.drawable.s5;
					}else if(times == 6){
						seResource = R.drawable.s6;
					}
					
					times++;
					times = times>6?1:times;
					drawGame();//开始绘制
					sleep(sleepSpec);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				seRocking = false;
			}
		}
		
	}
	
	/**
	 * 据点击的范围，判断点击事件
	 * @param actionX x点
	 * @param actionY y点
	 */
	public void touchHandler(float actionX,float actionY){
		
		if(actionX > belowLeftWidth && actionY > aboveHeight){//点击了色子区域
			if(!seRocking)
				new SeRock().start();
			System.out.println("点击了色子区域...");
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		if(event.getAction() == MotionEvent.ACTION_UP){
			float actionX = event.getX();
			float actionY = event.getY();
			touchHandler(actionX, actionY);
			System.out.println("点中:..."+actionX+":"+actionY);
		}
		
		return true;
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
		
		gameTypes = new GameTypes(width, aboveHeight);
		cellList = gameTypes.getCellList();//获取所有格数据
		gameDate= gameTypes.getGameByType();
		
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
