package com.liang.Plane;

/**
 * ������--������
 * @author ������
 * @date 2012-07-09
 * @declare ��Ȩ���� &copy ������
 *
 */
public class Utils {
	
	/**
	 * �ݵ�ǰ�ܶ���Ӧת��px�����ڲ�ͬ�ܶȵ�����£�������ͬ�ĳ��ȶ�Ӧ������
	 * @param dpi ��Ļ�ܶ�
	 * @param px ��dpiΪ160����µ����ش�С
	 * @return ��ǰ�ܶ��¶�Ӧ������
	 */
	public int getPixFromDpi(int dpi,int px){
		return px*(dpi/160);
	}
	
}
