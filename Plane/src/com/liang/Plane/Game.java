package com.liang.Plane;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

/**
 * 飞行棋--开始游戏
 * @author 梁进劲
 * @date 2012-07-08
 * @declare 版权所有 &copy 梁进劲
 *
 */
public class Game extends Activity {
	/**飞行棋--开始游戏--view类*/
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
		System.out.println("当前屏幕密度为:..."+dm.densityDpi+"  xdpi:"+dm.xdpi+"  ydpi:"+dm.ydpi);
		appDate.setDpi(dm.densityDpi);
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//开始相关绘制
		
	}
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//保存相关信息
		
		
	}
	
	
}
