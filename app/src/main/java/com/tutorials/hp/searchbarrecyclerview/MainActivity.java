package com.tutorials.hp.searchbarrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import com.mancj.materialsearchbar.MaterialSearchBar;
import java.util.ArrayList;
import java.util.HashMap;

/*
- Our MainActivity class.
- We reference our RecyclerView and  MaterialSearchBar here.
- We define data source and pass it to our MyAdapter instance.
- We add TextChangelistener to our material searchbar
 */
public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    MyAdapter adapter;
    ArrayList<HashMap<String,String>> hashMapArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //RFERENCE VIEWS
        rv= (RecyclerView) findViewById(R.id.mRecyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));

        MaterialSearchBar searchBar = (MaterialSearchBar) findViewById(R.id.searchBar);
        searchBar.setHint("Search..");
        searchBar.setSpeechMode(true);
        char c='a';
        int i=0;
        while (c<'z'){
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("character", String.valueOf(c));
            hashMap.put("number", String.valueOf(i));
            hashMapArrayList.add(hashMap);
            c++;
            i++;
        }
        //ADAPTER
        adapter=new MyAdapter(hashMapArrayList);
        rv.setAdapter(adapter);

        //SEARCHBAR TEXT CHANGE LISTENER
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //SEARCH FILTER
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
