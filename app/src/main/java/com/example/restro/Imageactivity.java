package com.example.restro;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class Imageactivity extends AppCompatActivity  {

private RecyclerView mRecyclerView;
    private Imageadaptor mAdapter;

    private ProgressBar mProgressCircle;

    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;
  //  private List<Upload> mUploadslistfull;
    private Imageadaptor adaptor;
   // ArrayAdapter<Upload> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        mRecyclerView = findViewById(R.id.imagesholder);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProgressCircle = findViewById(R.id.progress_circle);

        mUploads = new ArrayList<>();

        //adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);


        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads.add(upload);
                }

                adaptor = new Imageadaptor(Imageactivity.this, mUploads);

                mRecyclerView.setAdapter(adaptor);
                mProgressCircle.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Imageactivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchfragment, menu);
        MenuItem item = menu.findItem(R.id.itemSearch);
        SearchManager searchManager= (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView= (SearchView)item.getActionView();
       searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newtext) {
                adaptor.getFilter().filter(newtext);
            return false;
            }
        });
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "loading images", Toast.LENGTH_SHORT).show();
    }

    public void moveToDashboard(){
        Intent intent = new Intent(this, dashboard.class);
          startActivity(intent);

    }



}