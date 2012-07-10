package com.liang.Plane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ������--��Ԫ��������ʾ��
 * @author ������
 * @date 2012-07-10
 * @declare ��Ȩ���� &copy ������
 */
public class TipDates {
	
	/**tips key��ǰ��*/
	public static String TYPE_GO_TIP = "go";
	/**tips key������*/
	public static String TYPE_BACK_TIP = "back";
	/**tips key����ͣ*/
	public static String TYPE_PAUSE_TIP = "pause";
	/**tips key,��ʼ*/
	public static String TYPE_BEGIN_TIP = "begin";
	/**tips key,ʤ��*/
	public static String TYPE_END_TIP = "end";
	
	/**
	 * ��Ϸ��ʾ����
	 */
	private List<Map<String,String[]>> tips = null;
	
	public TipDates(){
		tips = new ArrayList<Map<String,String[]>>();
		
		//�������
		Map<String,String[]> tempMap = null;
		
		//**********׷Ů�� start**********
		tempMap = new HashMap<String, String[]>();
		//��ʼ��ʾ��
		String[] beginTips = {"����˧����С����ͬʱ׷һ��Ư����Ů����"};
		tempMap.put(TYPE_BEGIN_TIP, beginTips);
		
		//������ʾ��
		String[] endTips = {"����Ŷ����С��׷����"}; 
		tempMap.put(TYPE_END_TIP, endTips);
		
		//ǰ����ʾ��
		String[] goTips = {
							"һ�����飬ǰ��",
							"����һ�仨��ǰ��",
							"�Ը������ͣ�ǰ��",
							"����˧����ǰ��",
							"����������ǰ��",
							"����˧���ķ��ͣ�ǰ��"
							};
		tempMap.put(TYPE_GO_TIP, goTips);
		
		//��ͣ��ʾ��
		String[] pauseTips = {
								"������Ǯ��û����ͣ",
								"���ͺ���������ͣ",
							};
		tempMap.put(TYPE_PAUSE_TIP, pauseTips);
		
		//������ʾ��
		String[] backTips = {
								"����ǽ��,����",
								"��������,����"
							};
		tempMap.put(TYPE_BACK_TIP, backTips);
		
		//**********׷Ů�� end**********
		
		tips.add(tempMap);
		
	}
	
	/**
	 * ��ȡ��ʾ��  <br/>
	 * TYPE_GO_TIP <br/> 
	 * TYPE_BACK_TIP<br/>   
	 * TYPE_PAUSE_TIP<br/>   
	 * TYPE_BEGIN_TIP<br/>  
	 * TYPE_END_TIP
	 * @return ��ʾ��
	 */
	public Map<String,String[]> getTip(){
		return tips.get(0);
	}
	
}
