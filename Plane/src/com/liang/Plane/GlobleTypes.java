package com.liang.Plane;

/**
 * 飞行棋--全局数据
 * @author 梁进劲
 * @date 2012-07-09
 * @declare 版权所有 &copy 梁进劲
 */
public class GlobleTypes {
	/**<strong>单元格类型:</strong>
	 *  TYPE_BEGIN:开始位置<br/>
	 *  TYPE_END:结束位置<br/>
	 *  TYPE_BACK:退格<br/>
	 *  TYPE_PAUSE:暂停一次<br/>
	 *  TYPE_FORWARD:前进<br/>
	 */
	public static enum Types{TYPE_BEGIN,TYPE_END,TYPE_BACK,TYPE_PAUSE,TYPE_FORWARD};
	
	/** GameTypes.cellList的单元格x坐标键*/
	public static final String CELL_LIST_X_KEY = "x";
	/**GameTypes.cellList的单元格y坐标键*/
	public static final String CELL_LIST_Y_KEY = "y";
}
