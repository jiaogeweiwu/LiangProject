package com.liang.Plane;

import java.util.List;
import java.util.Map;

import com.liang.Plane.GlobleTypes.Types;

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
 * ������--��ʼ��Ϸ--view��
 * @author ������
 * @date 2012-07-08
 * @declare ��Ȩ���� &copy ������
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback{
	/**SurfaceHolder*/
	private SurfaceHolder holder = null;
	/**ApplicationDate ����ʱ��������*/
	private ApplicationDate appDate = null;
	/**activity context*/
	private Context context = null;
	
	private Paint paint = null;
	
	//��������
	/**������Ϸ����߶� 08%*/
	private int aboveHeight = 0; 
	/**�������ѡ��߶� screenHeight - aboveHeight*/
	/**�������ѡ���� 60%*/
	private int belowLeftWidth = 0;
	/**�����ұ߿��� screenWidth - belowLeftWidth*/
	
	
	/**�ؿ���*/
	private GameTypes gameTypes = null;
	/**
	 * ��Ԫ�����ݣ�������&quot;�±�&quot;Ϊ��ţ���¼����Ԫ�����ʼ�㡣<br/>
	 * CELL_LIST_X_KEY :x����<br/>CELL_LIST_Y_KEY:y����
	 */
	private List<Map<String,Integer>> cellList = null;
	
	/**��Ϸ·������*/
	private List<Cell> gameDate = null;
	/**ɫ����Դ*/
	private int seResource = R.drawable.s1;
	/**�Ƿ����ڹ���ɫ��*/
	private boolean seRocking = false;
	/**�ҵ�λ��0��ʼ*/
	private int myPosition = 0;
	/**����λ��0��ʼ**/
	private int opPosition = 0;
	/**�յ��±�*/
	private int endPosition = 0;
	
	
	/**
	 * ����
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
		
	}
	
	/**��ʼ����*/
	public void drawGame(){
		Canvas canvas = holder.lockCanvas();
		
		Bitmap bitmap = null;
		Bitmap tempBitmap = null;
		
		//������ɫ
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_bg);
		tempBitmap = Bitmap.createScaledBitmap(bitmap,appDate.getScreenWidth(), appDate.getScreenHeight(),true);
		bitmap.recycle();
		canvas.drawBitmap(tempBitmap, 0, 0, paint);
		tempBitmap.recycle();
		
		//���߿�
		canvas.drawLine(0, aboveHeight, appDate.getScreenWidth(), aboveHeight, paint);
		canvas.drawLine(belowLeftWidth, aboveHeight, belowLeftWidth, appDate.getScreenHeight(), paint);
		
		//��·��
		Cell cell = null;
		for(int i=0 ; i<gameDate.size() ; i++){
			cell = gameDate.get(i);
			float x = cellList.get(cell.getId()).get(GlobleTypes.CELL_LIST_X_KEY);
			float y = cellList.get(cell.getId()).get(GlobleTypes.CELL_LIST_Y_KEY);
			
			if(i==0){//�����
				bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.start_pos);
				tempBitmap = Bitmap.createScaledBitmap(bitmap,gameTypes.getCellWidth(), gameTypes.getCellHeight(),true);
				bitmap.recycle();
				canvas.drawBitmap(tempBitmap, x, y, paint);
				tempBitmap.recycle();
			}else if(i == (gameDate.size() -1)){//���յ�
				bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.end_pos);
				tempBitmap = Bitmap.createScaledBitmap(bitmap,gameTypes.getCellWidth(), gameTypes.getCellHeight(),true);
				bitmap.recycle();
				canvas.drawBitmap(tempBitmap, x, y, paint);
				tempBitmap.recycle();
			}else{ //���м��
				
				if(cell.getType() == Types.TYPE_FORWARD){//ǰ��
					bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.go_pos);
				}else if(cell.getType() == Types.TYPE_BACK){//����
					bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.back_pos);
				}else{//��ͣ
					bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pause_pos);
				}
				tempBitmap = Bitmap.createScaledBitmap(bitmap,gameTypes.getCellWidth(), gameTypes.getCellHeight(),true);
				bitmap.recycle();
				canvas.drawBitmap(tempBitmap, x, y, paint);
				tempBitmap.recycle();
			}
			
			paint.setColor(Color.RED);
			canvas.drawText(String.valueOf(i+1), x, y+10, paint);
			
		}
		
		
		//����ɫһ
		cell = gameDate.get(myPosition);
		float x = cellList.get(cell.getId()).get(GlobleTypes.CELL_LIST_X_KEY);
		float y = cellList.get(cell.getId()).get(GlobleTypes.CELL_LIST_Y_KEY);
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.head1);
		tempBitmap = Bitmap.createScaledBitmap(bitmap,gameTypes.getCellWidth()/2, gameTypes.getCellHeight()/2,true);
		bitmap.recycle();
		canvas.drawBitmap(tempBitmap, x+gameTypes.getCellWidth()/4, y, paint);
		tempBitmap.recycle();
		
		//����ɫ��
		cell = gameDate.get(opPosition);
		x = cellList.get(cell.getId()).get(GlobleTypes.CELL_LIST_X_KEY);
		y = cellList.get(cell.getId()).get(GlobleTypes.CELL_LIST_Y_KEY);
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.head2);
		tempBitmap = Bitmap.createScaledBitmap(bitmap,gameTypes.getCellWidth()/2, gameTypes.getCellHeight()/2,true);
		bitmap.recycle();
		canvas.drawBitmap(tempBitmap, x+gameTypes.getCellWidth()/4, y+gameTypes.getCellHeight()/2, paint);
		tempBitmap.recycle();
		
		
		//��ɫ��
		bitmap = BitmapFactory.decodeResource(context.getResources(), seResource);
		tempBitmap = Bitmap.createScaledBitmap(bitmap,50,50,true);
		bitmap.recycle();
		canvas.drawBitmap(tempBitmap,
							belowLeftWidth + (appDate.getScreenWidth() - belowLeftWidth - 50)/2, 
							aboveHeight + (appDate.getScreenHeight() - aboveHeight - 50)/2, 
							paint);
		tempBitmap.recycle();
		
		//���ҵ�״̬
		cell = gameDate.get(0);
		x = cellList.get(cell.getId()).get(GlobleTypes.CELL_LIST_X_KEY);
		y = cellList.get(cell.getId()).get(GlobleTypes.CELL_LIST_Y_KEY);
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.head1);
		tempBitmap = Bitmap.createScaledBitmap(bitmap,gameTypes.getCellWidth()/4, gameTypes.getCellHeight()/4,true);
		bitmap.recycle();
		canvas.drawBitmap(tempBitmap, 5,aboveHeight+5, paint);
		canvas.drawText(":21  <==>",5+tempBitmap.getWidth() , aboveHeight+5+10, paint);
		tempBitmap.recycle();
		
		//���Ҷ���״̬
		cell = gameDate.get(0);
		x = cellList.get(cell.getId()).get(GlobleTypes.CELL_LIST_X_KEY);
		y = cellList.get(cell.getId()).get(GlobleTypes.CELL_LIST_Y_KEY);
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.head2);
		tempBitmap = Bitmap.createScaledBitmap(bitmap,gameTypes.getCellWidth()/4, gameTypes.getCellHeight()/4,true);
		bitmap.recycle();
		canvas.drawBitmap(tempBitmap, 5+tempBitmap.getWidth()+60,aboveHeight+5, paint);
		canvas.drawText(":5",5+tempBitmap.getWidth()+60+tempBitmap.getWidth() , aboveHeight+5+10, paint);
		tempBitmap.recycle();
		
		
		//����ʾ
		canvas.drawText("�����ǣ���һ��",5 , aboveHeight+5+10+20, paint);
		
		canvas.drawText("���Ǻ����ģ���һ��",5 , aboveHeight+5+10+20+20, paint);
		
		canvas.drawText("���Ǻ����ģ�������",5 , aboveHeight+5+10+20+20+20, paint);
		
		canvas.drawText("���Ǻ����ģ�������",5 , aboveHeight+5+10+20+20+20+20, paint);
		
		canvas.drawText("���Ǻ����ģ����Ĳ�",5 , aboveHeight+5+10+20+20+20+20+20, paint);
		
		holder.unlockCanvasAndPost(canvas);
		
	}
	
	/**�������߳�*/
	private class DrawThread extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			drawGame();
		}
	}
	
	/**����ɫ���߳�*/
	private class SeRock extends Thread{
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			int times = 1;
			int sleepSpec = 500;//���߼��
			int MAX_TIME = 5; //���ѭ������
			for(int i=0 ; i<MAX_TIME ; i++){
				try{
					seRocking = true;
					
					times = (int)System.currentTimeMillis()%6 + 1;
					
					System.out.println("���������.��.."+times);
					times = times>6?1:times;
					System.out.println("���������.��.."+times);
					
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
					
					if(i == (MAX_TIME-1)){
						System.out.println("�����..."+times);
					}
					
					new DrawThread().start();
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
	 * �ݵ���ķ�Χ���жϵ���¼�
	 * @param actionX x��
	 * @param actionY y��
	 */
	public void touchHandler(float actionX,float actionY){
		
		if(actionX > belowLeftWidth && actionY > aboveHeight){//�����ɫ������
			if(!seRocking)
				new SeRock().start();
			System.out.println("�����ɫ������...");
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		if(event.getAction() == MotionEvent.ACTION_UP){
			float actionX = event.getX();
			float actionY = event.getY();
			touchHandler(actionX, actionY);
			System.out.println("����:..."+actionX+":"+actionY);
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
		belowLeftWidth = (int)(width * 0.6D);
		
		gameTypes = new GameTypes(width, aboveHeight);
		cellList = gameTypes.getCellList();//��ȡ���и�����
		gameDate= gameTypes.getGameByType();
		
		endPosition = gameDate.size() - 1;
		endPosition = endPosition>0?endPosition:0;
		
		new DrawThread().start();
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
