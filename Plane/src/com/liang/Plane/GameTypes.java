package com.liang.Plane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
	 * <strong>获取游戏路径数据</strong>
	 * 2：进退不能有冲突，造成死循环。(重要)<br/>
	 *算法规则：<br/>
	 * 		2.1：“前进”指向的位置不能再有“前进”(可为暂停、后退 、普通)。<br/>
	 *		2.2：“前进”指向的位置如果是“后退，返回的步数不能是之前指向的位置。<br/>			
	 *		2.3:“后退”指向的位置不能再有“后退”(可为普通、暂停、前进)。<br/>
	 *		2.4：“后退”指向的位置如果有“前进”，前进的步数不能是之前指向的位置<br/><br/>
	 *
	 *
	 *		算法：<br/>
	 *			1：先分配路径，全部设为普通作为填充。<br/>
	 *			2：计算暂停（10%）、后退（5%）、前进的个数（20%）。<br/>
	 *			3：随机分配“前进”的位置以及步数。<br/>
	 *			4：随机分配“后退”的位置以及步数。<br/>
	 *			5：随机分配“暂停”的位置。<br/>
	 *			6：遍历路径数组，分别检索出“前进”、“后退”、“暂停”的位置。<br/>
	 *			7：分别从小到大遍历“前进”，若指向下一个为“前进”，则改被指向的为“普通”。(规则2.1)<br/>
	 *			8:分别从大到小遍历“后退”，若指向的下一个为“前进”或“后退”则改本“后退”为“普通”。<br/>
	 *
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
		
		for(int i=0 ; i<rows ; i++){ 
			
			if((i+1)%2 == 0){ //2、4、6、8、10......行
				if(isRound){//顺时针，则下一轮将变为逆时针，加上本行最后一个格
					cell = new Cell(i*cells+cells-1, tips.get(TipDates.TYPE_NON_TIP)[nonIndex], 0, Types.TYPE_NON);
					isRound = false;
				}else{//逆时针，则下一轮将变为顺时针,加上本行第一个格
					cell = new Cell(i*cells, tips.get(TipDates.TYPE_NON_TIP)[nonIndex],  0, Types.TYPE_NON);
					isRound = true;
				}
				gameDate.add(gameDate.size(),cell);
				nonIndex++;
				nonIndex = nonIndex<nonTipSize?nonIndex:0;
			}else{
				for(int k=0 ; k<cells ; k++){//1、3、5、7...行
					if(isRound)//顺时针排列
						cell = new Cell(i*cells + k, tips.get(TipDates.TYPE_NON_TIP)[nonIndex],  0, Types.TYPE_NON);
					else//逆时针排列
						cell = new Cell(i*cells + cells - k -1, tips.get(TipDates.TYPE_NON_TIP)[nonIndex],  0, Types.TYPE_NON);
					gameDate.add(gameDate.size(),cell);
					nonIndex++;
					nonIndex = nonIndex<nonTipSize?nonIndex:0;
				}
				
			}
		}
		//*************横向顺逆顺分配 end*********************
		
		
		if(gameDate.size()>0){
			
			int gameSize = gameDate.size();
			
			/**计算暂停数 10%*/
			int pauseSize = (int)(gameSize * 0.1f);
			/**计算 后退数 25%*/
			int backSize = (int)(gameSize * 0.2f);
			/**计算前进数 35%*/
			int goSize = (int)(gameSize * 0.35f);
			/**随机跳步的最大间隔 总数的15%*/
			int maxDumpSteps = (int)(gameSize * 0.15f);
			
			Random randon = new Random();
			int index = 0;
			//随机分配<前进>
			for(int i=0 ; i<goSize ; i++){
				index = Math.abs(randon.nextInt()%gameSize);
				cell = gameDate.get(index);
				gameDate.remove(index);
				cell.setDesc(tips.get(TipDates.TYPE_GO_TIP)[goIndex]);
				cell.setGostep(Math.abs(randon.nextInt()%maxDumpSteps+1));
				cell.setType(Types.TYPE_FORWARD);
				goIndex++;
				goIndex = goIndex<goTipSize?goIndex:0;
				gameDate.add(index, cell);
			}
			
			//随机分配<后退>
			for(int i=0 ; i<backSize ; i++){
//				index = (int)((randon.nextInt(10+i)+System.currentTimeMillis())%maxDumpSteps);
				index = Math.abs(randon.nextInt()%gameSize);
				cell = gameDate.get(index);
				gameDate.remove(index);
				cell.setDesc(tips.get(TipDates.TYPE_BACK_TIP)[backIndex]);
				cell.setGostep(Math.abs(randon.nextInt()%maxDumpSteps+1));
				cell.setType(Types.TYPE_BACK);
				backIndex++;
				backIndex = backIndex<backTipSize?backIndex:0;
				gameDate.add(index, cell);
			}
			
			//随机分配<暂停>
			for(int i=0 ; i<pauseSize ; i++){
//				index = (int)((randon.nextInt(10+i)+System.currentTimeMillis())%maxDumpSteps);
				index = Math.abs(randon.nextInt()%gameSize);
				cell = gameDate.get(index);
				gameDate.remove(index);
				cell.setDesc(tips.get(TipDates.TYPE_PAUSE_TIP)[pauseIndex]);
				cell.setType(Types.TYPE_PAUSE);
				pauseIndex++;
				pauseIndex = pauseIndex<pauseTipSize?pauseIndex:0;
				gameDate.add(index, cell);
			}
			
			//设置开始提示语
			cell = gameDate.get(0);
			cell.setDesc(tips.get(TipDates.TYPE_BEGIN_TIP)[0]);
			cell.setType(Types.TYPE_BEGIN);
			cell.setGostep(0);
			gameDate.remove(0);
			gameDate.add(0, cell);
			
			//设置结束提示语
			cell = gameDate.get(gameDate.size()-1);
			cell.setDesc(tips.get(TipDates.TYPE_END_TIP)[0]);
			cell.setType(Types.TYPE_END);
			cell.setGostep(0);
			gameDate.remove(gameDate.size()-1);
			gameDate.add(gameDate.size(),cell);
			
			
			for(Cell c:gameDate){
				System.out.println("======"+c.getType()+":"+c.getDesc()+":"+c.getGostep()+":"+c.getId());
			}
			
			//检查并解决冲突
			Cell tempCell = null;
			int tempIndex = 0;
			for(int i=0 ; i<gameSize ; i++){
				//获取顺数的单元格
				cell = gameDate.get(i);
				if(cell.getType() == Types.TYPE_FORWARD){//分别从小到大遍历“前进”，若指向下一个为“前进”，则改被指向的为“普通”。(规则2.1)
					tempIndex = i+cell.getGostep();
					if(tempIndex < gameSize && tempIndex>0){
						tempCell = gameDate.get(tempIndex);
						if(tempCell.getType() == Types.TYPE_FORWARD){ //被指向的改为“普通”
							tempCell.setType(Types.TYPE_NON);
							tempCell.setDesc(tips.get(TipDates.TYPE_NON_TIP)[nonIndex]);
							nonIndex++;
							nonIndex = nonIndex<nonTipSize?nonIndex:0;
							
							gameDate.remove(tempIndex);
							gameDate.add(tempIndex,tempCell);
						}
					}
					
				}else if(cell.getType() == Types.TYPE_BACK){//分别从大到小遍历“后退”，若指向的下一个为“前进”或“后退”则改本“后退”为“普通”
					tempIndex = i - cell.getGostep();
					if(tempIndex < gameSize && tempIndex>0){
						tempCell = gameDate.get(tempIndex);
						if(tempCell.getType() == Types.TYPE_FORWARD || tempCell.getType() == Types.TYPE_BACK){ //被指向的改为“普通”
							cell.setType(Types.TYPE_NON);
							cell.setDesc(tips.get(TipDates.TYPE_NON_TIP)[nonIndex]);
							nonIndex++;
							nonIndex = nonIndex<nonTipSize?nonIndex:0;
							
							gameDate.remove(i);
							gameDate.add(i,cell);
						}
					}
				}
				
			}
		}
		
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
