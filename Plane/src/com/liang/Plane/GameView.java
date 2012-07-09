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
 * ������--��ʼ��Ϸ--view��
 * @author ������
 * @date 2012-07-08
 * @declare ��Ȩ���� &copy ������
 *
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback{
	/**SurfaceHolder*/
	private SurfaceHolder holder = null;
	/**ApplicationDate ����ʱ��������*/
	private ApplicationDate appDate = null;
	/**activity context*/
	private Context context = null;
	
	private Paint paint = null;
	/**������*/
//	private Utils utils = null;
	
	//��������
	/**������Ϸ����߶� 08%*/
	private int aboveHeight = 0; 
	/**�������ѡ��߶� screenHeight - aboveHeight*/
//	private int belowHeight = 0;
	/**�������ѡ���� 60%*/
	private int belowLeftWidth = 0;
	/**�����ұ߿��� screenWidth - belowLeftWidth*/
//	private int belowRightWidth = 0;
	
	
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
		
//		utils = new Utils();
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
		
		drawGame();//��ʼ����
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
