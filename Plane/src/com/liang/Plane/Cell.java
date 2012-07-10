package com.liang.Plane;

import com.liang.Plane.GlobleTypes.Types;

/**
 * 飞行棋--单元格类
 * @author 梁进劲
 * @date 2012-07-09
 * @declare 版权所有 &copy 梁进劲
 */
public class Cell {
	
	/**单元格编号，对应cellList的下标*/
	private int id = -1;
	/**单元格描述，自定义时不起过20字*/
	private String desc = "";
	/**前进步数*/
	private int gostep = 0;
	
	/**类型 TYPE_BACK,TYPE_PAUSE,TYPE_FORWARD,TYPE_NON*/
	private Types type = null;
	
	/**
	 * 构造
	 * @param id 单元格编号，对应cellList的下标
	 * @param desc 单元格描述，自定义时不起过20字
	 * @param gostep 前进步数
	 * @param type 类型 TYPE_BACK,TYPE_PAUSE,TYPE_FORWARD,TYPE_NON
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
