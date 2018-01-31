package com.zmm.storeplay.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.zmm.storeplay.R;
import com.zmm.storeplay.di.component.AppComponent;
import com.zmm.storeplay.ui.adapter.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_page)
    ViewPager mViewPage;


    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected void init() {
        initDrawerLayout();

        initTabLayout();
    }

    private void initDrawerLayout() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.menu_app_update:
                        Toast.makeText(MainActivity.this, "menu_app_update", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.menu_message:
                        Toast.makeText(MainActivity.this, "menu_message", Toast.LENGTH_SHORT).show();

                        break;

                    case R.id.menu_app_setting:
                        Toast.makeText(MainActivity.this, "menu_app_setting", Toast.LENGTH_SHORT).show();

                        break;
                }


                return false;
            }
        });

        mToolBar.inflateMenu(R.menu.toolbar_menu);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);

    }


    private void initTabLayout() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPage.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPage);

    }

}
