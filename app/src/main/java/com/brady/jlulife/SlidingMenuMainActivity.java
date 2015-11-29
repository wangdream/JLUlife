package com.brady.jlulife;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.brady.jlulife.Fragments.ClassSyncFragment;
import com.brady.jlulife.Fragments.CourseListFragment;
import com.brady.jlulife.Fragments.LibrarySearchFragment;
import com.brady.jlulife.Fragments.MenuFragment;
import com.brady.jlulife.Fragments.SemSelectFragment;
import com.brady.jlulife.Fragments.UIMSAuthFragment;
import com.brady.jlulife.Models.UIMSModel;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;

public class SlidingMenuMainActivity extends ActionBarActivity {
    private Context mContext;
    private SlidingMenu menu;

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        UmengUpdateAgent.update(this);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_container,new CourseListFragment());
        transaction.commit();
        initSlidingMenu();
        initActionBar();
//        getSupportActionBar().hide();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar OutsideItem clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_upgrade) {
            if(UIMSModel.getInstance(this).isLoginIn()){
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.main_container, SemSelectFragment.getInstance());
                transaction.commit();
            }else {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.main_container, ClassSyncFragment.getInstance());
                transaction.commit();
                menu.showContent();
            }
            return true;
        }
        if (id == 16908332) {
            if(menu.isMenuShowing()){
                menu.showContent();
            }else {
                menu.showMenu();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void initSlidingMenu(){
        menu = new SlidingMenu(mContext);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setShadowWidth(200);
        menu.setBehindOffset(500);
        menu.setFadeDegree(0.5f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
        menu.setMenu(R.layout.menu_container);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.menu_container,new MenuFragment());
        transaction.commit();
    }
    public SlidingMenu getMenu(){
        return menu;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

    }


    public void initActionBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);

    }
    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        if(LibrarySearchFragment.getInstance().isAdded()){
            LibrarySearchFragment.getInstance().preformBack();
        }
    }*/
}
