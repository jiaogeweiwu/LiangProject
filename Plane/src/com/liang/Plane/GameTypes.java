package com.liang.Plane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liang.Plane.GlobleTypes.Types;
import static com.liang.Plane.GlobleTypes.CELL_LIST_X_KEY;
import static com.liang.Plane.GlobleTypes.CELL_LIST_Y_KEY;

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
	
	/**
	 * 单元格数据，以数组&quot;下标&quot;为编号，记录当单元格的起始点。<br/>
	 * CELL_LIST_X_KEY :x坐标<br/>CELL_LIST_Y_KEY:y坐标
	 */
	private List<Map<String,Integer>> cellList = null;
	
	/**游戏路径数据*/
	private List<Cell> gameDate = null;
	
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
	 * 获取游戏路径数据
	 * @return List<Cell> 路径数据
	 */
	public List<Cell> getGameByType(){
		
		//*************横向顺逆顺分配 start*********************
		gameDate = new ArrayList<Cell>();
		Cell cell = null;
		boolean isRound = true;//当前是否为顺时针排列
		for(int i=0 ; i<rows ; i++){ 
			
			if((i+1)%2 == 0){ //2、4、6、8、10......行
				if(isRound){//顺时针，则下一轮将变为逆时针，加上本行最后一个格
					cell = new Cell(i*cells+cells-1, "描述:前进", 1, Types.TYPE_FORWARD);
					gameDate.add(gameDate.size(),cell);
					isRound = false;
				}else{//逆时针，则下一轮将变为顺时针,加上本行第一个格
					cell = new Cell(i*cells, "描述:前进", 1, Types.TYPE_FORWARD);
					gameDate.add(gameDate.size(),cell);
					isRound = true;
				}
			}else{
				for(int k=0 ; k<cells ; k++){//1、3、5、7...行
					if(isRound){//顺时针排列
						cell = new Cell(i*cells + k, "描述:前进", 1,Types.TYPE_FORWARD);
						gameDate.add(gameDate.size(),cell);
					}else{//逆时针排列
						cell = new Cell(i*cells + cells - k -1, "描述:前进", 1, Types.TYPE_FORWARD);
						gameDate.add(gameDate.size(),cell);
					}
					
				}
			}
			
		}
		//*************横向顺逆顺分配 end*********************
		
		return gameDate;
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
