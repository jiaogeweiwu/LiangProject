package com.liang.Plane;

/**
 * 飞行棋--工具类
 * @author 梁进劲
 * @date 2012-07-09
 * @declare 版权所有 &copy 梁进劲
 *
 */
public class Utils {
	
	/**
	 * 据当前密度相应转换px。即在不同密度的情况下，物理相同的长度对应的像素
	 * @param dpi 屏幕密度
	 * @param px 在dpi为160情况下的像素大小
	 * @return 当前密度下对应的像素
	 */
	public int getPixFromDpi(int dpi,int px){
		return px*(dpi/160);
	}
	
}
