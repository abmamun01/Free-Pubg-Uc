package com.mamunsproject.awesome_free_pubg_uc.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.mamunsproject.awesome_free_pubg_uc.Adapter.LeaderBoardAdapter;
import com.mamunsproject.awesome_free_pubg_uc.Model.User;
import com.mamunsproject.awesome_free_pubg_uc.R;

import java.util.ArrayList;

public class LeaderBoard_Fragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_leader_board_, container, false);

        RecyclerView recyclerView=view.findViewById(R.id.recyclerviewid);

        FirebaseFirestore database=FirebaseFirestore.getInstance();







        ArrayList<User> users=new ArrayList<>();
        LeaderBoardAdapter adapter=new LeaderBoardAdapter(getActivity(),users);


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        database.collection("Users")
                .orderBy("coins", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (DocumentSnapshot snapshot: queryDocumentSnapshots){
                    User user=snapshot.toObject(User.class);
                    users.add(user);

                }
                adapter.notifyDataSetChanged();
            }
        });






        return view;
    }
}