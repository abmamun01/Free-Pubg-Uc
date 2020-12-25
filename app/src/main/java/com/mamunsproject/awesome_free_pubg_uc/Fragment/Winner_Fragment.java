package com.mamunsproject.awesome_free_pubg_uc.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.L;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.mamunsproject.awesome_free_pubg_uc.Adapter.winnerAdapter;
import com.mamunsproject.awesome_free_pubg_uc.Model.winnerModel;
import com.mamunsproject.awesome_free_pubg_uc.R;

import java.util.ArrayList;
import java.util.List;

public class Winner_Fragment extends Fragment {

    FirebaseFirestore database;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_winner_, container, false);

        recyclerView=view.findViewById(R.id.recyclerviewidforwinner);
        database=FirebaseFirestore.getInstance();

       final List<winnerModel> list=new ArrayList<>();
        final winnerAdapter adapter=new winnerAdapter(getActivity(),list);


       database.collection("Winner")
               .addSnapshotListener(new EventListener<QuerySnapshot>() {
                   @Override
                   public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                       //ager data delete kore notun data add korbe
                       list.clear();
                       for (DocumentSnapshot snapshot:value.getDocuments()  ){

                           winnerModel model= snapshot.toObject(winnerModel.class);
                           model.setCategoryId(snapshot.getId());
                           list.add(model);

                       }
                       adapter.notifyDataSetChanged();
                   }
               });



        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);



        return view;

    }
}