package com.liang.Plane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	/**cellList�ĵ�Ԫ��x�����*/
	public final String CELL_LIST_X_KEY = "x";
	/**cellList�ĵ�Ԫ��y�����*/
	public final String CELL_LIST_Y_KEY = "y";
	/**
	 * ��Ԫ�����ݣ�������&quot;�±�&quot;Ϊ��ţ���¼����Ԫ�����ʼ�㡣<br/>
	 * CELL_LIST_X_KEY :x����<br/>CELL_LIST_Y_KEY:y����
	 */
	private List<Map<String,Integer>> cellList = null;
	
	/**��Ԫ���ţ���ӦcellList���±�*/
	public final String GAME_DATE_ID = "id";
	/**��Ԫ���������Զ���ʱ�����20��*/
	public final String GAME_DATE_DESC = "desc";
	/**ǰ������*/
	public final String GAME_DATE_GO_STEP = "gostep";
	/**ǰ������:go\back\pause*/
	public final String GAME_DATE_TYPE = "type";
	
	/**
	 *��Ϸ·������<br/>
	 *
	 */
	public List<Map<String,String>> gameDate = null;
	
	
	
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
	 * ��ȡ��Ϸ��������
	 */
	public void getGameByType(){
		
		gameDate
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
