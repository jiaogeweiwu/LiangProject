package com.liang.Plane;

import android.app.Application;
/**application ����*/
public class ApplicationDate extends Application {
	/**��Ļ���px*/
	private int screenWidth = 0;
	/**��Ļ�߶�px*/
	private int screenHeight = 0;
	/**�ܶ�dpi*/
	private int dpi = 160;
	
	
	/**��ȡ�ܶ�dpi*/
	public int getDpi() {
		return dpi;
	}

	/**�����ܶ�dpi*/
	public void setDpi(int dpi) {
		this.dpi = dpi;
	}

	/**��ȡ��Ļ���px*/
	public int getScreenWidth() {
		return screenWidth;
	}
	
	/**������Ļ���px*/
	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}
	
	/**��ȡ��Ļ�߶�px*/
	public int getScreenHeight() {
		return screenHeight;
	}
	
	/**������Ļ�߶�px*/
	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}
	
	
}
