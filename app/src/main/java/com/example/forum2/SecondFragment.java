package com.example.forum2;


import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import com.example.forum2.Models.putPDF;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {

    FirebaseDatabase db;

    DatabaseReference subData, discData;

    TextView subjectLine, discriptionLine;

//    Button viewAnnounce;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_second);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }

        // below line is used to get the instance
        // of our Firebase database.
//        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get
        // reference for our database.
//        databaseReference = firebaseDatabase.getReference("Announcement");
//        databaseReference = firebaseDatabase.getReference("Data");

        // initializing our object class variable.
//        retrieveTV = findViewById(R.id.idTVRetrieveData);

//        getdata();
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_second, container, false);

//        viewAnnounce = view.findViewById(R.id.viewAnnouncements);
//        viewAnnounce.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(getActivity(), FetchFilesActivity.class);
//                startActivity(intent);
//
//            }
//        });

        // below line is used to get the instance
        // of our Firebase database.
        db = FirebaseDatabase.getInstance();

        // below line is used to get
        // reference for our database.
        subData = db.getReference("Announcement/annSubject");
        discData = db.getReference("Announcement/annDetail");

        // initializing our object class variable.
        subjectLine = view.findViewById(R.id.subjectLine);
        discriptionLine = view.findViewById(R.id.discriptionLine);

        // calling method
        // for getting data.
        getdata();

        return view;
    }


    private void getdata() {

        // calling add value event listener method
        // for getting the values from database.
        subData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.
                // below line is for getting the data from
                // snapshot of our database.
                String value = snapshot.getValue(String.class);

                // after getting the value we are setting
                // our value to our text view in below line.
                subjectLine.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                // Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });

        discData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.
                // below line is for getting the data from
                // snapshot of our database.
                String value = snapshot.getValue(String.class);

                // after getting the value we are setting
                // our value to our text view in below line.
                discriptionLine.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                // Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }


}