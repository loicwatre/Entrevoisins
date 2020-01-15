package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.util.SharedPreferencesFav;

import java.util.ArrayList;

public class NeighbourDetail extends AppCompatActivity {

    private SharedPreferences sharedPrefs;

    private Integer mId;
    private Neighbour mNeighbour;
    private NeighbourApiService mApiService;

    private ArrayList<Integer> favorites = new ArrayList<>();

    //UI
    private TextView titleView;
    private FloatingActionButton mFavoriteFab;
    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbourg_detail);

        mApiService = DI.getNeighbourApiService();

        titleView = findViewById(R.id.textView_detail_title);
        mFavoriteFab = findViewById(R.id.fab_favorite);
        ImageButton backArrow = findViewById(R.id.image_details_back_arrow);

        mFavoriteFab.setOnClickListener(fabListener);
        backArrow.setOnClickListener(backListener);

        mId = getIntent().getIntExtra("ID", 0);

        //Get the corresponding user
        mNeighbour = getNeighbour(mId);

        //Initializing favorites from preferences
        sharedPrefs = this.getSharedPreferences(getString(R.string.SHARED_PREF_FAVORITES), MODE_PRIVATE);
        favorites = SharedPreferencesFav.getPreferencesToArrayList(sharedPrefs.getString(getString(R.string.shared_pref_key),null));

        //UI
        if (mNeighbour != null) {
            initUi();
        }
    }

    /**
     *
     * @param id of the neighbour clicked on
     * @return the corresponding Neighbour object by finding its name in the list
     */
    private Neighbour getNeighbour(Integer id){

        return mApiService.getSpecificNeighbour(id);
    }

    //UI
    private void initUi(){
        ImageView avatarView = findViewById(R.id.toolbarImage);
        TextView nameView = findViewById(R.id.textView_detail_name);
        TextView addressView = findViewById(R.id.textView_detail_address);
        TextView phoneView = findViewById(R.id.textView_detail_phone);
        TextView networkView = findViewById(R.id.textView_detail_network);
        TextView descriptionView = findViewById(R.id.textView_detail_description);

        if (favorites.contains(mNeighbour.getId())){
            mFavoriteFab.setImageResource(R.drawable.ic_star_yellow_24dp);
        } else {
            mFavoriteFab.setImageResource(R.drawable.ic_star_border_yellow_24dp);
        }

        titleView.setText(mNeighbour.getName());
        titleView.setTextColor(Color.WHITE);
        nameView.setText(mNeighbour.getName());
        addressView.setText("Here the personal address");
        phoneView.setText("00.00.00.00.00.00");
        Glide.with(this).load("https://image.freepik.com/free-vector/background-with-circles-abstract-design_23-2148275367.jpg").into(avatarView);
        networkView.setText("Here social network infos");
        descriptionView.setText("Personal description");
    }

    //CLICKS
    View.OnClickListener fabListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (favorites.contains(mNeighbour.getId())){
                //Already in favorites
                mFavoriteFab.setImageResource(R.drawable.ic_star_yellow_24dp);
                favorites.remove(mNeighbour.getId());
            } else {
                //Not yet in favorites
                mFavoriteFab.setImageResource(R.drawable.ic_star_border_yellow_24dp);
                favorites.add(mNeighbour.getId());
            }

            //Save IDs of favorites in preferences
            String preferences = SharedPreferencesFav.prerareDataForPreferences(favorites);
            sharedPrefs.edit().putString(getString(R.string.shared_pref_key), preferences).apply();

            Toast.makeText(NeighbourDetail.this, preferences, Toast.LENGTH_SHORT).show();
        }
    };

    View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

}

