package com.liang.Plane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 飞行棋--游戏关卡数据
 * @author 梁进劲
 * @date 2012-07-09
 * @declare 版权所有 &copy 梁进劲
 */
public class GameTypes {
	
	/**单元格的长度*/
	private int cellWidth = 50;
	/**单元格高度*/
	private int cellHeight = 50;
	/**每列可容纳单元格数*/
	private int rows = 0;
	/**每行可容纳单元格数*/
	private int cells = 0;
	/**居中需要对左偏移的像素数*/
	private int alignCenterLeft = 0;
	/**居中需要对上偏移的像素数*/
	private int alignCenterTop = 0;
	
	/**cellList的单元格x坐标键*/
	public final String CELL_LIST_X_KEY = "x";
	/**cellList的单元格y坐标键*/
	public final String CELL_LIST_Y_KEY = "y";
	/**
	 * 单元格数据，以数组&quot;下标&quot;为编号，记录当单元格的起始点。<br/>
	 * CELL_LIST_X_KEY :x坐标<br/>CELL_LIST_Y_KEY:y坐标
	 */
	private List<Map<String,Integer>> cellList = null;
	
	/**单元格编号，对应cellList的下标*/
	public final String GAME_DATE_ID = "id";
	/**单元格描述，自定义时不起过20字*/
	public final String GAME_DATE_DESC = "desc";
	/**前进步数*/
	public final String GAME_DATE_GO_STEP = "gostep";
	/**前进类型:go\back\pause*/
	public final String GAME_DATE_TYPE = "type";
	
	/**
	 *游戏路径数据<br/>
	 *
	 */
	public List<Map<String,String>> gameDate = null;
	
	
	
	/**
	 * 构造
	 * @param gameWidth 游戏区域宽度
	 * @param gameHeight 游戏区域高度
	 */
	public GameTypes(int gameWidth,int gameHeight){
		rows = gameHeight/cellHeight; //计算行数
		cells = gameWidth/cellWidth; //计算列数
		
		alignCenterLeft = (gameWidth - cellWidth*cells)/2; //居中需要对左偏移的像素数
		alignCenterTop = (gameHeight - cellHeight*rows)/2; //居中需要对上偏移的像素数
		
		cellList = new ArrayList<Map<String,Integer>>();
		//分配单元格
		Map<String,Integer> cell = null;
		for(int i=0 ; i<rows ; i++){
			for(int k=0 ; k<cells ; k++){
				cell = new HashMap<String, Integer>();
				
				cell.put(CELL_LIST_X_KEY, k * cellWidth + alignCenterLeft);
				cell.put(CELL_LIST_Y_KEY, i * cellHeight + alignCenterTop);
				
				cellList.add(cellList.size(), cell);
			}
		}
	}
	
	/**
	 * 获取游戏类型数据
	 */
	public void getGameByType(){
		
		gameDate
	}

	/**单元格的长度*/
	public int getCellWidth() {
		return cellWidth;
	}
	
	/**单元格高度*/
	public int getCellHeight() {
		return cellHeight;
	}
	
	/**居中需要对左偏移的像素数*/
	public int getAlignCenterLeft() {
		return alignCenterLeft;
	}
	
	/**居中需要对上偏移的像素数*/
	public int getAlignCenterTop() {
		return alignCenterTop;
	}

	/**获取所有单元格数据，以数组&quot;下标&quot;为编号，记录当单元格的起始点。*/
	public List<Map<String, Integer>> getCellList() {
		return cellList;
	}
	
}
