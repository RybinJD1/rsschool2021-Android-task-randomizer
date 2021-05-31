package com.rsschool.android2021;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements Sender, Receiver {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(0);
    }

    private void openFirstFragment(int previousNumber) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, FirstFragment.newInstance(previousNumber))
                .commit();
    }

    private void openSecondFragment(int min, int max) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,
                        SecondFragment.newInstance(min, max),
                        SecondFragment.class.getName()
                )
                .commit();
    }

    @Override
    public void receive(int result) {
        openFirstFragment(result);
    }

    @Override
    public void send(int min, int max) {
        openSecondFragment(min, max);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment =
                getSupportFragmentManager().findFragmentByTag(SecondFragment.class.getName());
        if (fragment == null) {
            super.onBackPressed();
        } else {
            ((SecondFragment) fragment).backButtonClick();
        }
    }

}
