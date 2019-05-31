package md_arshad.qshop.Activity;

import android.app.SearchManager;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import md_arshad.qshop.Adapter.CustomSearchAdapter;
import md_arshad.qshop.R;
import md_arshad.qshop.Structures.SearchResultStore;

public class CustomSearchActivity extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_search);
        ImageButton backBtn = (ImageButton) findViewById(R.id.back_button);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final EditText searchText = (EditText) findViewById(R.id.search_text);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override public void onItemClick(AdapterView<?> parent, View view,
                                              int position, long id) {
                //execution come here when an item is clicked from
                //the search results displayed after search form is submitted
                //you can continue from here with user clicked search item
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                listView.setVisibility(View.INVISIBLE);
                searchText.setText(""+text.getText().toString());
//                Toast.makeText(CustomSearchActivity.this,
//                        "Searching for "+((TextView)view).getText(),
//                        Toast.LENGTH_SHORT).show();
            }
        });

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable e) {
                String textFromEditView = e.toString();
                listView.setVisibility(View.VISIBLE);
//                listView.setAdapter(new CustomSearchAdapter(getApplicationContext(),android.R.layout.simple_list_item_2,SearchResultStore.getSearchResults()));
                listView.setAdapter(new CustomSearchAdapter(getApplicationContext(),android.R.layout.simple_list_item_2,SearchResultStore.filterData(textFromEditView)));
//                Toast.makeText(getApplicationContext(),textFromEditView,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //nothing needed here...
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //nothing needed here...
            }
        });
        final ImageButton searchBtn = (ImageButton) findViewById(R.id.search_button);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //// search the content;
                listView.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),"Searching for "+searchText.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });

        // search
        handleSearch();
    }

    private void handleSearch() {
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String searchQuery = intent.getStringExtra(SearchManager.QUERY);

            CustomSearchAdapter adapter = new CustomSearchAdapter(this,
                    android.R.layout.simple_dropdown_item_1line,
                    SearchResultStore.filterData(searchQuery));
            listView.setAdapter(adapter);

        }else if(Intent.ACTION_VIEW.equals(intent.getAction())) {
            String selectedSuggestionRowId =  intent.getDataString();
            //execution comes here when an item is selected from search suggestions
            //you can continue from here with user selected search item
            Toast.makeText(this, "selected search suggestion "+selectedSuggestionRowId,
                    Toast.LENGTH_SHORT).show();
        }
    }
}