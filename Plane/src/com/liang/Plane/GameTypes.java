package com.liang.Plane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liang.Plane.GlobleTypes.Types;
import static com.liang.Plane.GlobleTypes.CELL_LIST_X_KEY;
import static com.liang.Plane.GlobleTypes.CELL_LIST_Y_KEY;

/**
 * ������--��Ϸ�ؿ�����
 * @author ������
 * @date 2012-07-09
 * @declare ��Ȩ���� &copy ������
 */
public class GameTypes {
	
	/**��Ԫ��ĳ���*/
	private int cellWidth = 50;
	/**��Ԫ��߶�*/
	private int cellHeight = 50;
	/**ÿ�п����ɵ�Ԫ����*/
	private int rows = 0;
	/**ÿ�п����ɵ�Ԫ����*/
	private int cells = 0;
	/**������Ҫ����ƫ�Ƶ�������*/
	private int alignCenterLeft = 0;
	/**������Ҫ����ƫ�Ƶ�������*/
	private int alignCenterTop = 0;
	
	/**
	 * ��Ԫ�����ݣ�������&quot;�±�&quot;Ϊ��ţ���¼����Ԫ�����ʼ�㡣<br/>
	 * CELL_LIST_X_KEY :x����<br/>CELL_LIST_Y_KEY:y����
	 */
	private List<Map<String,Integer>> cellList = null;
	
	/**��Ϸ·������*/
	private List<Cell> gameDate = null;
	
	/**
	 * ����
	 * @param gameWidth ��Ϸ������
	 * @param gameHeight ��Ϸ����߶�
	 */
	public GameTypes(int gameWidth,int gameHeight){
		rows = gameHeight/cellHeight; //��������
		cells = gameWidth/cellWidth; //��������
		
		alignCenterLeft = (gameWidth - cellWidth*cells)/2; //������Ҫ����ƫ�Ƶ�������
		alignCenterTop = (gameHeight - cellHeight*rows)/2; //������Ҫ����ƫ�Ƶ�������
		
		cellList = new ArrayList<Map<String,Integer>>();
		//���䵥Ԫ��
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
	 * ��ȡ��Ϸ·������
	 * @return List<Cell> ·������
	 */
	public List<Cell> getGameByType(){
		
		//*************����˳��˳���� start*********************
		gameDate = new ArrayList<Cell>();
		Cell cell = null;
		boolean isRound = true;//��ǰ�Ƿ�Ϊ˳ʱ������
		for(int i=0 ; i<rows ; i++){ 
			
			if((i+1)%2 == 0){ //2��4��6��8��10......��
				if(isRound){//˳ʱ�룬����һ�ֽ���Ϊ��ʱ�룬���ϱ������һ����
					cell = new Cell(i*cells+cells-1, "����:ǰ��", 1, Types.TYPE_FORWARD);
					gameDate.add(gameDate.size(),cell);
					isRound = false;
				}else{//��ʱ�룬����һ�ֽ���Ϊ˳ʱ��,���ϱ��е�һ����
					cell = new Cell(i*cells, "����:ǰ��", 1, Types.TYPE_FORWARD);
					gameDate.add(gameDate.size(),cell);
					isRound = true;
				}
			}else{
				for(int k=0 ; k<cells ; k++){//1��3��5��7...��
					if(isRound){//˳ʱ������
						cell = new Cell(i*cells + k, "����:ǰ��", 1,Types.TYPE_FORWARD);
						gameDate.add(gameDate.size(),cell);
					}else{//��ʱ������
						cell = new Cell(i*cells + cells - k -1, "����:ǰ��", 1, Types.TYPE_FORWARD);
						gameDate.add(gameDate.size(),cell);
					}
					
				}
			}
			
		}
		//*************����˳��˳���� end*********************
		
		return gameDate;
	}

	/**��Ԫ��ĳ���*/
	public int getCellWidth() {
		return cellWidth;
	}
	
	/**��Ԫ��߶�*/
	public int getCellHeight() {
		return cellHeight;
	}
	
	/**������Ҫ����ƫ�Ƶ�������*/
	public int getAlignCenterLeft() {
		return alignCenterLeft;
	}
	
	/**������Ҫ����ƫ�Ƶ�������*/
	public int getAlignCenterTop() {
		return alignCenterTop;
	}

	/**��ȡ���е�Ԫ�����ݣ�������&quot;�±�&quot;Ϊ��ţ���¼����Ԫ�����ʼ�㡣*/
	public List<Map<String, Integer>> getCellList() {
		return cellList;
	}
	
	
}
