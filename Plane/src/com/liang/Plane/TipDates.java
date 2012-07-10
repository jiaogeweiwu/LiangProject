package com.liang.Plane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 飞行棋--单元格数据提示类
 * @author 梁进劲
 * @date 2012-07-10
 * @declare 版权所有 &copy 梁进劲
 */
public class TipDates {
	
	/**tips key，前进*/
	public static String TYPE_GO_TIP = "go";
	/**tips key，后退*/
	public static String TYPE_BACK_TIP = "back";
	/**tips key，暂停*/
	public static String TYPE_PAUSE_TIP = "pause";
	/**tips key,开始*/
	public static String TYPE_BEGIN_TIP = "begin";
	/**tips key,胜利*/
	public static String TYPE_END_TIP = "end";
	
	/**
	 * 游戏提示数据
	 */
	private List<Map<String,String[]>> tips = null;
	
	public TipDates(){
		tips = new ArrayList<Map<String,String[]>>();
		
		//填充数据
		Map<String,String[]> tempMap = null;
		
		//**********追女孩 start**********
		tempMap = new HashMap<String, String[]>();
		//开始提示组
		String[] beginTips = {"两个帅气的小伙子同时追一个漂亮的女孩子"};
		tempMap.put(TYPE_BEGIN_TIP, beginTips);
		
		//结束提示组
		String[] endTips = {"不错哦，你小子追到了"}; 
		tempMap.put(TYPE_END_TIP, endTips);
		
		//前进提示组
		String[] goTips = {
							"一见钟情，前进",
							"送她一朵花，前进",
							"吃个烛光晚餐，前进",
							"打扮很帅气，前进",
							"开着名车，前进",
							"换个帅气的发型，前进"
							};
		tempMap.put(TYPE_GO_TIP, goTips);
		
		//暂停提示组
		String[] pauseTips = {
								"人又老钱又没，暂停",
								"发型很老土，暂停",
							};
		tempMap.put(TYPE_PAUSE_TIP, pauseTips);
		
		//后退提示组
		String[] backTips = {
								"被挖墙角,后退",
								"和她吵架,后退"
							};
		tempMap.put(TYPE_BACK_TIP, backTips);
		
		//**********追女孩 end**********
		
		tips.add(tempMap);
		
	}
	
	/**
	 * 获取提示组  <br/>
	 * TYPE_GO_TIP <br/> 
	 * TYPE_BACK_TIP<br/>   
	 * TYPE_PAUSE_TIP<br/>   
	 * TYPE_BEGIN_TIP<br/>  
	 * TYPE_END_TIP
	 * @return 提示组
	 */
	public Map<String,String[]> getTip(){
		return tips.get(0);
	}
	
}
