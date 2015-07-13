package com.todou.favoriteanimation.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.todou.favoriteanimation.R;
import com.todou.favoriteanimation.adapter.CustomAdapter;
import com.todou.favoriteanimation.utils.ViewUtils;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.todou.favoriteanimation.utils.ViewUtils.getNumLocation;


public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.recycler_view)
    RecyclerView mRecycler;
    @InjectView(R.id.text_main_num)
    TextView textMainNum;
    @InjectView(R.id.view_main_button)
    View viewMainButton;

    private CustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        updateRecyclerView();
    }

    private void updateRecyclerView() {
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CustomAdapter(this);
        mRecycler.setAdapter(mAdapter);

        //TODO test
        ArrayList<String> datas = createTestDatas();
        mAdapter.updateData(datas);
    }

    private ArrayList<String> createTestDatas() {
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add("...data: " + i);
        }
        return datas;
    }


    public void doAnimation(float x, float y) {
        final TextView textView = new TextView(this);
        textView.setText("1");
        textView.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        textView.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ((ViewGroup) viewMainButton.getRootView()).addView(textView, params);
        final AnimatorSet flyDownAnim = new AnimatorSet();
        flyDownAnim.setDuration(800);
        int[] location = getNumLocation(textMainNum);
        ObjectAnimator transX1 = ObjectAnimator.ofFloat(textView, "translationX", x, location[0]);
        ObjectAnimator transY1 = ObjectAnimator.ofFloat(textView, "translationY", y, location[1]);
        transY1.setInterpolator(PathInterpolatorCompat.create(0.8f, 0.3f));
        ObjectAnimator rotation1 = ObjectAnimator.ofFloat(textView, "rotation", textView.getRotation(), 0);
        rotation1.setInterpolator(new AccelerateInterpolator());
        flyDownAnim.playTogether(transX1, transY1,
                ObjectAnimator.ofFloat(textView, "scaleX", 0.5f, 0.9f),
                ObjectAnimator.ofFloat(textView, "scaleY", 0.5f, 0.9f),
                rotation1
        );
        flyDownAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                textView.setVisibility(View.GONE);
                ((ViewGroup)viewMainButton.getRootView()).removeView(textView);
                startButtonAnimation();
            }
        });
        flyDownAnim.start();
    }

    private void startButtonAnimation() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(viewMainButton, "scaleY", 1.0f, 1.2f, 1.0f),
                ObjectAnimator.ofFloat(viewMainButton, "scaleX", 1.0f, 1.2f, 1.0f)
        );
        animatorSet.start();
    }


}
