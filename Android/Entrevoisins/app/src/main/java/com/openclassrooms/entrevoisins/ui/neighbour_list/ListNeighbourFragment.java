package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;


    /**
     * Create and return a new instance
     * @return @{@link ListNeighbourFragment}
     */
    public static ListNeighbourFragment newInstance(ArrayList<Neighbour> listToCall) {
        ListNeighbourFragment fragment = new ListNeighbourFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_NEIGHBOUR_LIST, listToCall);
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
        initList();
        return view;
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours));
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        mApiService.deleteNeighbour(event.neighbour);
        mNeighbours.remove(event.neighbour);
        initList();
    }

    @Subscribe
    public void onShowNeighbourDetail(ShowNeighbourDetailEvent event){
       Intent intent = new Intent(getActivity(),NeighbourDetailActivity.class);
       intent.putExtra(KEY_NEIGHBOUR_DETAIL,event.neighbour);
       getActivity().startActivity(intent);
    }
}
