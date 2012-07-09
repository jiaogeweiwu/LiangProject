package com.liang.Plane;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * ������
 * @author ������
 * @date 2012-07-08
 * @declare ��Ȩ���� &copy ������
 *
 */
public class PlaneActivity extends Activity implements OnClickListener{
	/**View ���ٿ�ʼ*/
	private View vQuickStart = null;
	/**View ������Ϸ*/
	private View vGoon = null;
	/**View ѡ��ؿ�*/
	private View vSelect = null;
	/**View �Դ���Ϸ*/
	private View vDefine = null;
	/**View ����*/
	private View vSetting = null;
	/**View ����*/
	private View vAbout = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        vQuickStart = this.findViewById(R.id.main_quick_start);
        vQuickStart.setOnClickListener(this);
        
        vGoon = this.findViewById(R.id.main_goon);
        vGoon.setOnClickListener(this);
        
        vSelect = this.findViewById(R.id.main_select);
        vSelect.setOnClickListener(this);
        
        vDefine = this.findViewById(R.id.main_define);
        vDefine.setOnClickListener(this);
        
        vSetting = this.findViewById(R.id.main_setting);
        vSetting.setOnClickListener(this);
        
        vAbout = this.findViewById(R.id.main_about);
        vAbout.setOnClickListener(this);
        
        //*************test start************
        PlaneActivity.this.startActivity(new Intent(PlaneActivity.this,Game.class));
        //*************test end**************
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.main_quick_start://���ٿ�ʼ
				PlaneActivity.this.startActivity(new Intent(PlaneActivity.this,Game.class));
				PlaneActivity.this.finish();
				break;
			case R.id.main_goon://������Ϸ
				
				break;
			case R.id.main_select://ѡ��ؿ�
				
				break;
			case R.id.main_define://�Դ��ؿ�
				
				break;
			case R.id.main_setting://����
				
				break;
			case R.id.main_about://����
				
				break;
		}
	}
    
    
}