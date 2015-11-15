package com.brady.jlulife.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.brady.jlulife.SlidingMenuMainActivity;
import com.brady.jlulife.R;
import com.brady.jlulife.Utils.ConstValue;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * A placeholder fragment containing a simple view.
 */
public class MenuFragment extends Fragment {

//    ViewPager mViewPager;
//    RecyclerView mRecyclerView;

    public MenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final FragmentManager manager = getFragmentManager();
                ((Button) view.findViewById(R.id.main_drcom_login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction= manager.beginTransaction();
                transaction.replace(R.id.main_container, new DrcomLoginFragment());
                transaction.addToBackStack(null);
                transaction.commit();
                closeMenu();
            }
        });
        ((Button) view.findViewById(R.id.main_jwQuery)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsListFragment fragment = new NewsListFragment();
                Bundle argument = new Bundle();
                argument.putString("action", ConstValue.FUNCTION_JWCX);
                fragment.setArguments(argument);
                FragmentTransaction transaction= manager.beginTransaction();
                transaction.replace(R.id.main_container,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                closeMenu();

            }
        });
        ((Button) view.findViewById(R.id.main_xntz)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsListFragment fragment = new NewsListFragment();
                Bundle argument = new Bundle();
                argument.putString("action", ConstValue.FUNCTION_JLUNEWS);
                fragment.setArguments(argument);
                FragmentTransaction transaction= manager.beginTransaction();
                transaction.replace(R.id.main_container,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                closeMenu();
            }
        });
    }

    private void closeMenu(){
        SlidingMenu menu = ((SlidingMenuMainActivity) getActivity()).getMenu();
        if(menu.isMenuShowing()){
            menu.showContent();
        }
    }
}
