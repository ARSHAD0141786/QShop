package md_arshad.qshop.Activity.InnerActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

import md_arshad.qshop.Adapter.CategoryItemGridViewAdapter;
import md_arshad.qshop.Network.FetchData;
import md_arshad.qshop.R;
import md_arshad.qshop.Structures.QShopItemStructure;

public class CategoryWise extends AppCompatActivity {

    GridView categoryItemGridView;
    public static CategoryItemGridViewAdapter categoryItemGridViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_wise);

        String activityTitle = getIntent().getStringExtra("activity_name");
        int activityCode = getIntent().getIntExtra("activity_code",1);
        TextView activityTitleTextView = (TextView) findViewById(R.id.activity_title);
        if(activityTitle!=null)
        activityTitleTextView.setText(activityTitle);

        switch(activityCode){
            case 0 : categoryItemGridViewAdapter = new CategoryItemGridViewAdapter(this, FetchData.mElectronicsData);break;
            case 1 : categoryItemGridViewAdapter = new CategoryItemGridViewAdapter(this,FetchData.mFashionData);break;
            case 2 : categoryItemGridViewAdapter = new CategoryItemGridViewAdapter(this,FetchData.mHomeLivingData);break;
            case 3 : categoryItemGridViewAdapter = new CategoryItemGridViewAdapter(this,FetchData.mKidsData);break;
            default: categoryItemGridViewAdapter = new CategoryItemGridViewAdapter(this,FetchData.mFashionData);break;
        }
        categoryItemGridView = (GridView) findViewById(R.id.category_item_grid_view);

        categoryItemGridView.setAdapter(categoryItemGridViewAdapter);
    }


    public void goBack(View view){
        finish();
    }
}
