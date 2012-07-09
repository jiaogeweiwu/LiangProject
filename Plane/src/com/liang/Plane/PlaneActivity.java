package com.liang.Plane;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 飞行棋
 * @author 梁进劲
 * @date 2012-07-08
 * @declare 版权所有 &copy 梁进劲
 *
 */
public class PlaneActivity extends Activity implements OnClickListener{
	/**View 快速开始*/
	private View vQuickStart = null;
	/**View 继续游戏*/
	private View vGoon = null;
	/**View 选择关卡*/
	private View vSelect = null;
	/**View 自创游戏*/
	private View vDefine = null;
	/**View 设置*/
	private View vSetting = null;
	/**View 关于*/
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
			case R.id.main_quick_start://快速开始
				PlaneActivity.this.startActivity(new Intent(PlaneActivity.this,Game.class));
				PlaneActivity.this.finish();
				break;
			case R.id.main_goon://继续游戏
				
				break;
			case R.id.main_select://选择关卡
				
				break;
			case R.id.main_define://自创关卡
				
				break;
			case R.id.main_setting://设置
				
				break;
			case R.id.main_about://关于
				
				break;
		}
	}
    
    
}