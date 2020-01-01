package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourDetailActivity extends AppCompatActivity {

    Neighbour mNeighbour;

    @BindView(R.id.activityNeighbourDetailAvatar)
    ImageView mImageViewAvatar;
    @BindView(R.id.activityNeighbourDetailTxtTitle)
    TextView mTextViewTitle;
    @BindView(R.id.activityNeighbourDetailTxtSmallTitle1)
    TextView mTextViewSmallTitle1;
    @BindView(R.id.activityNeighbourDetailTxtHomeAddress)
    TextView mTextViewHomeAddress;
    @BindView(R.id.activityNeighbourDetailTxtPhoneNumber)
    TextView mTextViewPhoneNumber;
    @BindView(R.id.activityNeighbourDetailTxtWebAddress)
    TextView mTextViewWebAddress;
    @BindView(R.id.activityNeighbourDetailTxtDescription)
    TextView mTextViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mNeighbour = (Neighbour) intent.getSerializableExtra("KEY_NEIGHBOUR_DETAIL");

        Glide.with(this)
                .load(mNeighbour.getAvatarUrl())
                .into(mImageViewAvatar);

        mTextViewTitle.setText(mNeighbour.getName());
        mTextViewSmallTitle1.setText(mNeighbour.getName());
        mTextViewHomeAddress.setText(mNeighbour.getAddress());
        mTextViewPhoneNumber.setText(mNeighbour.getPhoneNumber());
        mTextViewWebAddress.setText(mNeighbour.getWebAddress());
        mTextViewDescription.setText(mNeighbour.getDescription());
    }


}
