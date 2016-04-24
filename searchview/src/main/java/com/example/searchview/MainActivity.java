package com.example.searchview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.searchview.weiget.SearchBar;

public class MainActivity extends AppCompatActivity {

    private SearchBar mSearchBar;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBar = (SearchBar) findViewById(R.id.search_bar);
        mSearchView = (SearchView) findViewById(R.id.search_view);


        mSearchBar.setSearchCallBack(new SearchBar.SearchCallBack() {
            @Override
            public void onClickSearch(View view, String query) {
                Toast.makeText(MainActivity.this, "content is " + query, Toast.LENGTH_SHORT).show();
            }
        });

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
