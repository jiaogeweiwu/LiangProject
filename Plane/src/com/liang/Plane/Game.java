package com.liang.Plane;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

/**
 * ������--��ʼ��Ϸ
 * @author ������
 * @date 2012-07-08
 * @declare ��Ȩ���� &copy ������
 *
 */
public class Game extends Activity {
	/**������--��ʼ��Ϸ--view��*/
	private GameView gameView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		gameView = new GameView(this);
		this.setContentView(gameView);
		
		ApplicationDate appDate = (ApplicationDate)this.getApplicationContext();
		
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		System.out.println("��ǰ��Ļ�ܶ�Ϊ:..."+dm.densityDpi+"  xdpi:"+dm.xdpi+"  ydpi:"+dm.ydpi);
		appDate.setDpi(dm.densityDpi);
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//��ʼ��ػ���
		
	}
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//���������Ϣ
		
		
	}
	
	
}
