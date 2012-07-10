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
		TipDates tipDates = new TipDates();//��ʾ��
		Map<String,String[]> tips = tipDates.getTip(); //��ȡ��ʾ��
		tipDates = null;
		
		gameDate = new ArrayList<Cell>();
		Cell cell = null;
		boolean isRound = true;//��ǰ�Ƿ�Ϊ˳ʱ������
		
		/**ǰ����ʾ��*/
		int goTipSize = tips.get(TipDates.TYPE_GO_TIP).length;
		/**������ʾ��*/
		int backTipSize = tips.get(TipDates.TYPE_BACK_TIP).length;
		/**��ͣ��ʾ��*/
		int pauseTipSize = tips.get(TipDates.TYPE_PAUSE_TIP).length;
		/**û����ʾ��*/
		int nonTipSize = tips.get(TipDates.TYPE_NON_TIP).length;
		//���õ���ʾ���±�
		int goIndex = 0;
		int backIndex = 0;
		int pauseIndex = 0;
		int nonIndex = 0;
		
		Types currentType = null;
		for(int i=0 ; i<rows ; i++){ 
			
			//�����������
			switch((int)((System.currentTimeMillis()+i)%10)){
				case 0://��ǰ
					currentType = Types.TYPE_FORWARD;
					break;
				case 1://����
					currentType = Types.TYPE_BACK;
					break;
				case 2://��ͣ
					currentType = Types.TYPE_PAUSE;
					break;
				default://û��
					currentType = Types.TYPE_NON;
					break;
			}
			
			if((i+1)%2 == 0){ //2��4��6��8��10......��
				if(isRound){//˳ʱ�룬����һ�ֽ���Ϊ��ʱ�룬���ϱ������һ����
					cell = new Cell(i*cells+cells-1, "����:ǰ��", 1, currentType);
					isRound = false;
				}else{//��ʱ�룬����һ�ֽ���Ϊ˳ʱ��,���ϱ��е�һ����
					cell = new Cell(i*cells, "����:ǰ��", 1, currentType);
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
				for(int k=0 ; k<cells ; k++){//1��3��5��7...��
					
					//�����������
					switch((int)((System.currentTimeMillis()+i)%10)){
						case 0://��ǰ
							currentType = Types.TYPE_FORWARD;
							break;
						case 1://����
							currentType = Types.TYPE_BACK;
							break;
						case 2://��ͣ
							currentType = Types.TYPE_PAUSE;
							break;
						default://û��
							currentType = Types.TYPE_NON;
							break;
					}
					
					if(isRound)//˳ʱ������
						cell = new Cell(i*cells + k, "����:ǰ��", 1,currentType);
					else//��ʱ������
						cell = new Cell(i*cells + cells - k -1, "����:ǰ��", 1, currentType);
					
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
		//*************����˳��˳���� end*********************
		
		//���ÿ�ʼ��ʾ��
		cell = gameDate.get(0);
		cell.setDesc(tips.get(TipDates.TYPE_BEGIN_TIP)[0]);
		cell.setType(Types.TYPE_BEGIN);
		gameDate.remove(0);
		gameDate.add(0, cell);
		
		//���ý�����ʾ��
		cell = gameDate.get(gameDate.size()-1);
		cell.setDesc(tips.get(TipDates.TYPE_END_TIP)[0]);
		cell.setType(Types.TYPE_END);
		gameDate.remove(gameDate.size()-1);
		gameDate.add(gameDate.size(),cell);
		
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
