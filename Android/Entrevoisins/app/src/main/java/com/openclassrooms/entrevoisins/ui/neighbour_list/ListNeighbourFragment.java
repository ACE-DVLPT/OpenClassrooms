package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.ShowNeighbourDetailEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class ListNeighbourFragment extends Fragment {

    private static final String KEY_NEIGHBOUR_LIST = "KEY_NEIGHBOUR_LIST";
    public static final String KEY_NEIGHBOUR_DETAIL = "KEY_NEIGHBOUR_DETAIL";
    public static final String KEY_FAVORITE_FRAGMENT = "KEY_FAVORITE_FRAGMENT";

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;
    private MyNeighbourRecyclerViewAdapter mRecyclerViewAdapter;
    private Boolean mViewCreated = false;
    private Boolean mFavoriteFragment;

    /**
     * Create and return a new instance
     * @return @{@link ListNeighbourFragment}
     */
    public static ListNeighbourFragment newInstance(ArrayList<Neighbour> listToCall, Boolean favoriteFragment) {
        ListNeighbourFragment fragment = new ListNeighbourFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_NEIGHBOUR_LIST, listToCall);
        args.putBoolean(KEY_FAVORITE_FRAGMENT,favoriteFragment);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mNeighbours = (ArrayList<Neighbour>) getArguments().getSerializable(KEY_NEIGHBOUR_LIST);
        mFavoriteFragment = getArguments().getBoolean(KEY_FAVORITE_FRAGMENT);
        initRemoveIcon();
        initList();
        mViewCreated = true;
        return view;
    }

    private void hideDeleteIcon (){
        if (mFavoriteFragment){
         //   (MyNeighbourRecyclerViewAdapter)
        }
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {
        mRecyclerViewAdapter = new MyNeighbourRecyclerViewAdapter(mNeighbours);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    private void initRemoveIcon(){
        if (mFavoriteFragment){

        }else{
            // Do nothing
        }
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (menuVisible) {
            if (mViewCreated){
            }
            EventBus.getDefault().register(this);
            Log.e("DEBUG", "REGISTER");
        }else {
            EventBus.getDefault().unregister(this);
            Log.e("DEBUG", "UNREGISTER");
        }
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        ((ListNeighbourActivity)getActivity()).mGeneralList.remove(event.neighbour);
        ((ListNeighbourActivity)getActivity()).mFavoriteList.remove(event.neighbour);
        ((ListNeighbourActivity)getActivity()).initFragment();
       // mRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Subscribe
    public void onShowNeighbourDetail(ShowNeighbourDetailEvent event){
       Intent intent = new Intent(getActivity(),NeighbourDetailActivity.class);
       intent.putExtra(KEY_NEIGHBOUR_DETAIL,event.neighbour);
       Log.e("DEBUG","START");
       getActivity().startActivityForResult(intent,1);
    }

    public void setNeighbours() {
        mRecyclerViewAdapter.notifyDataSetChanged();
    }
}
