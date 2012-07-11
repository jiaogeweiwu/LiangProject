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
	 * <strong>��ȡ��Ϸ·������</strong>
	 * 2�����˲����г�ͻ�������ѭ����(��Ҫ)<br/>
	 *�㷨����<br/>
	 * 		2.1����ǰ����ָ���λ�ò������С�ǰ����(��Ϊ��ͣ������ ����ͨ)��<br/>
	 *		2.2����ǰ����ָ���λ������ǡ����ˣ����صĲ���������֮ǰָ���λ�á�<br/>			
	 *		2.3:�����ˡ�ָ���λ�ò������С����ˡ�(��Ϊ��ͨ����ͣ��ǰ��)��<br/>
	 *		2.4�������ˡ�ָ���λ������С�ǰ������ǰ���Ĳ���������֮ǰָ���λ��<br/><br/>
	 *
	 *
	 *		�㷨��<br/>
	 *			1���ȷ���·����ȫ����Ϊ��ͨ��Ϊ��䡣<br/>
	 *			2��������ͣ��10%�������ˣ�5%����ǰ���ĸ�����20%����<br/>
	 *			3��������䡰ǰ������λ���Լ�������<br/>
	 *			4��������䡰���ˡ���λ���Լ�������<br/>
	 *			5��������䡰��ͣ����λ�á�<br/>
	 *			6������·�����飬�ֱ��������ǰ�����������ˡ�������ͣ����λ�á�<br/>
	 *			7���ֱ��С���������ǰ��������ָ����һ��Ϊ��ǰ��������ı�ָ���Ϊ����ͨ����(����2.1)<br/>
	 *			8:�ֱ�Ӵ�С���������ˡ�����ָ�����һ��Ϊ��ǰ�����򡰺��ˡ���ı������ˡ�Ϊ����ͨ����<br/>
	 *
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
		
		for(int i=0 ; i<rows ; i++){ 
			
			if((i+1)%2 == 0){ //2��4��6��8��10......��
				if(isRound){//˳ʱ�룬����һ�ֽ���Ϊ��ʱ�룬���ϱ������һ����
					cell = new Cell(i*cells+cells-1, tips.get(TipDates.TYPE_NON_TIP)[nonIndex], 0, Types.TYPE_NON);
					isRound = false;
				}else{//��ʱ�룬����һ�ֽ���Ϊ˳ʱ��,���ϱ��е�һ����
					cell = new Cell(i*cells, tips.get(TipDates.TYPE_NON_TIP)[nonIndex],  0, Types.TYPE_NON);
					isRound = true;
				}
				gameDate.add(gameDate.size(),cell);
				nonIndex++;
				nonIndex = nonIndex<nonTipSize?nonIndex:0;
			}else{
				for(int k=0 ; k<cells ; k++){//1��3��5��7...��
					if(isRound)//˳ʱ������
						cell = new Cell(i*cells + k, tips.get(TipDates.TYPE_NON_TIP)[nonIndex],  0, Types.TYPE_NON);
					else//��ʱ������
						cell = new Cell(i*cells + cells - k -1, tips.get(TipDates.TYPE_NON_TIP)[nonIndex],  0, Types.TYPE_NON);
					gameDate.add(gameDate.size(),cell);
					nonIndex++;
					nonIndex = nonIndex<nonTipSize?nonIndex:0;
				}
				
			}
		}
		//*************����˳��˳���� end*********************
		
		
		if(gameDate.size()>0){
			
			int gameSize = gameDate.size();
			
			/**������ͣ�� 10%*/
			int pauseSize = (int)(gameSize * 0.1f);
			/**���� ������ 25%*/
			int backSize = (int)(gameSize * 0.2f);
			/**����ǰ���� 35%*/
			int goSize = (int)(gameSize * 0.35f);
			/**�������������� ������15%*/
			int maxDumpSteps = (int)(gameSize * 0.15f);
			
			Random randon = new Random();
			int index = 0;
			//�������<ǰ��>
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
			
			//�������<����>
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
			
			//�������<��ͣ>
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
			
			//���ÿ�ʼ��ʾ��
			cell = gameDate.get(0);
			cell.setDesc(tips.get(TipDates.TYPE_BEGIN_TIP)[0]);
			cell.setType(Types.TYPE_BEGIN);
			cell.setGostep(0);
			gameDate.remove(0);
			gameDate.add(0, cell);
			
			//���ý�����ʾ��
			cell = gameDate.get(gameDate.size()-1);
			cell.setDesc(tips.get(TipDates.TYPE_END_TIP)[0]);
			cell.setType(Types.TYPE_END);
			cell.setGostep(0);
			gameDate.remove(gameDate.size()-1);
			gameDate.add(gameDate.size(),cell);
			
			
			for(Cell c:gameDate){
				System.out.println("======"+c.getType()+":"+c.getDesc()+":"+c.getGostep()+":"+c.getId());
			}
			
			//��鲢�����ͻ
			Cell tempCell = null;
			int tempIndex = 0;
			for(int i=0 ; i<gameSize ; i++){
				//��ȡ˳���ĵ�Ԫ��
				cell = gameDate.get(i);
				if(cell.getType() == Types.TYPE_FORWARD){//�ֱ��С���������ǰ��������ָ����һ��Ϊ��ǰ��������ı�ָ���Ϊ����ͨ����(����2.1)
					tempIndex = i+cell.getGostep();
					if(tempIndex < gameSize && tempIndex>0){
						tempCell = gameDate.get(tempIndex);
						if(tempCell.getType() == Types.TYPE_FORWARD){ //��ָ��ĸ�Ϊ����ͨ��
							tempCell.setType(Types.TYPE_NON);
							tempCell.setDesc(tips.get(TipDates.TYPE_NON_TIP)[nonIndex]);
							nonIndex++;
							nonIndex = nonIndex<nonTipSize?nonIndex:0;
							
							gameDate.remove(tempIndex);
							gameDate.add(tempIndex,tempCell);
						}
					}
					
				}else if(cell.getType() == Types.TYPE_BACK){//�ֱ�Ӵ�С���������ˡ�����ָ�����һ��Ϊ��ǰ�����򡰺��ˡ���ı������ˡ�Ϊ����ͨ��
					tempIndex = i - cell.getGostep();
					if(tempIndex < gameSize && tempIndex>0){
						tempCell = gameDate.get(tempIndex);
						if(tempCell.getType() == Types.TYPE_FORWARD || tempCell.getType() == Types.TYPE_BACK){ //��ָ��ĸ�Ϊ����ͨ��
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
