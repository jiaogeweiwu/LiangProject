package com.liang.Plane;

import com.liang.Plane.GlobleTypes.Types;

/**
 * ������--��Ԫ����
 * @author ������
 * @date 2012-07-09
 * @declare ��Ȩ���� &copy ������
 */
public class Cell {
	
	/**��Ԫ���ţ���ӦcellList���±�*/
	private int id = -1;
	/**��Ԫ���������Զ���ʱ�����20��*/
	private String desc = "";
	/**ǰ������*/
	private int gostep = 0;
	
	/**���� TYPE_BACK,TYPE_PAUSE,TYPE_FORWARD,TYPE_NON*/
	private Types type = null;
	
	/**
	 * ����
	 * @param id ��Ԫ���ţ���ӦcellList���±�
	 * @param desc ��Ԫ���������Զ���ʱ�����20��
	 * @param gostep ǰ������
	 * @param type ���� TYPE_BACK,TYPE_PAUSE,TYPE_FORWARD,TYPE_NON
	 */
	public Cell(int id, String desc,int gostep,Types type){
		this.setId(id);
		this.setDesc(desc);
		this.setGostep(gostep);
		this.setType(type);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getGostep() {
		return gostep;
	}

	public void setGostep(int gostep) {
		this.gostep = gostep;
	}

	public Types getType() {
		return type;
	}

	public void setType(Types type) {
		this.type = type;
	}

	
}
