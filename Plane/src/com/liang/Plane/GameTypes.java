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
		TipDates tipDates = new TipDates();//提示组
		Map<String,String[]> tips = tipDates.getTip(); //获取提示组
		tipDates = null;
		
		gameDate = new ArrayList<Cell>();
		Cell cell = null;
		boolean isRound = true;//当前是否为顺时针排列
		
		/**前进提示数*/
		int goTipSize = tips.get(TipDates.TYPE_GO_TIP).length;
		/**后退提示数*/
		int backTipSize = tips.get(TipDates.TYPE_BACK_TIP).length;
		/**暂停提示数*/
		int pauseTipSize = tips.get(TipDates.TYPE_PAUSE_TIP).length;
		/**没事提示数*/
		int nonTipSize = tips.get(TipDates.TYPE_NON_TIP).length;
		//已用到提示的下标
		int goIndex = 0;
		int backIndex = 0;
		int pauseIndex = 0;
		int nonIndex = 0;
		
		Types currentType = null;
		for(int i=0 ; i<rows ; i++){ 
			
			//随机会配类型
			switch((int)((System.currentTimeMillis()+i)%10)){
				case 0://向前
					currentType = Types.TYPE_FORWARD;
					break;
				case 1://后退
					currentType = Types.TYPE_BACK;
					break;
				case 2://暂停
					currentType = Types.TYPE_PAUSE;
					break;
				default://没事
					currentType = Types.TYPE_NON;
					break;
			}
			
			if((i+1)%2 == 0){ //2、4、6、8、10......行
				if(isRound){//顺时针，则下一轮将变为逆时针，加上本行最后一个格
					cell = new Cell(i*cells+cells-1, "描述:前进", 1, currentType);
					isRound = false;
				}else{//逆时针，则下一轮将变为顺时针,加上本行第一个格
					cell = new Cell(i*cells, "描述:前进", 1, currentType);
					isRound = true;
				}
				
				cell.setGostep((int)((System.currentTimeMillis()+i)%8));
				if(currentType == Types.TYPE_FORWARD){
					cell.setDesc(tips.get(TipDates.TYPE_GO_TIP)[goIndex]);
					goIndex++;
					goIndex = goIndex<goTipSize?goIndex:0;
				}else if(currentType == Types.TYPE_BACK){
					cell.setDesc(tips.get(TipDates.TYPE_BACK_TIP)[backIndex]);
					backIndex++;
					backIndex = backIndex<backTipSize?backIndex:0;
				}else if(currentType == Types.TYPE_PAUSE){
					cell.setGostep(0);
					cell.setDesc(tips.get(TipDates.TYPE_PAUSE_TIP)[pauseIndex]);
					pauseIndex++;
					pauseIndex = pauseIndex<pauseTipSize?pauseIndex:0;
				}else{
					cell.setGostep(0);
					cell.setDesc(tips.get(TipDates.TYPE_NON_TIP)[nonIndex]);
					nonIndex++;
					nonIndex = nonIndex<nonTipSize?nonIndex:0;
				}
				gameDate.add(gameDate.size(),cell);
			}else{
				for(int k=0 ; k<cells ; k++){//1、3、5、7...行
					
					//随机会配类型
					switch((int)((System.currentTimeMillis()+i)%10)){
						case 0://向前
							currentType = Types.TYPE_FORWARD;
							break;
						case 1://后退
							currentType = Types.TYPE_BACK;
							break;
						case 2://暂停
							currentType = Types.TYPE_PAUSE;
							break;
						default://没事
							currentType = Types.TYPE_NON;
							break;
					}
					
					if(isRound)//顺时针排列
						cell = new Cell(i*cells + k, "描述:前进", 1,currentType);
					else//逆时针排列
						cell = new Cell(i*cells + cells - k -1, "描述:前进", 1, currentType);
					
					cell.setGostep((int)((System.currentTimeMillis()+i)%8));
					if(currentType == Types.TYPE_FORWARD){
						cell.setDesc(tips.get(TipDates.TYPE_GO_TIP)[goIndex]);
						goIndex++;
						goIndex = goIndex<goTipSize?goIndex:0;
					}else if(currentType == Types.TYPE_BACK){
						cell.setDesc(tips.get(TipDates.TYPE_BACK_TIP)[backIndex]);
						backIndex++;
						backIndex = backIndex<backTipSize?backIndex:0;
					}else if(currentType == Types.TYPE_PAUSE){
						cell.setGostep(0);
						cell.setDesc(tips.get(TipDates.TYPE_PAUSE_TIP)[pauseIndex]);
						pauseIndex++;
						pauseIndex = pauseIndex<pauseTipSize?pauseIndex:0;
					}else{
						cell.setGostep(0);
						cell.setDesc(tips.get(TipDates.TYPE_NON_TIP)[nonIndex]);
						nonIndex++;
						nonIndex = nonIndex<nonTipSize?nonIndex:0;
					}
					
					gameDate.add(gameDate.size(),cell);
					
					
				}
			}
			
		}
		//*************横向顺逆顺分配 end*********************
		
		//设置开始提示语
		cell = gameDate.get(0);
		cell.setDesc(tips.get(TipDates.TYPE_BEGIN_TIP)[0]);
		cell.setType(Types.TYPE_BEGIN);
		gameDate.remove(0);
		gameDate.add(0, cell);
		
		//设置结束提示语
		cell = gameDate.get(gameDate.size()-1);
		cell.setDesc(tips.get(TipDates.TYPE_END_TIP)[0]);
		cell.setType(Types.TYPE_END);
		gameDate.remove(gameDate.size()-1);
		gameDate.add(gameDate.size(),cell);
		
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
