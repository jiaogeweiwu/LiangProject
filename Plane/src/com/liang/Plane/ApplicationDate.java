package com.liang.Plane;

import android.app.Application;
/**application 数据*/
public class ApplicationDate extends Application {
	/**屏幕宽度px*/
	private int screenWidth = 0;
	/**屏幕高度px*/
	private int screenHeight = 0;
	/**密度dpi*/
	private int dpi = 160;
	
	
	/**获取密度dpi*/
	public int getDpi() {
		return dpi;
	}

	/**设置密度dpi*/
	public void setDpi(int dpi) {
		this.dpi = dpi;
	}

	/**获取屏幕宽度px*/
	public int getScreenWidth() {
		return screenWidth;
	}
	
	/**设置屏幕宽度px*/
	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}
	
	/**获取屏幕高度px*/
	public int getScreenHeight() {
		return screenHeight;
	}
	
	/**设置屏幕高度px*/
	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}
	
	
}
