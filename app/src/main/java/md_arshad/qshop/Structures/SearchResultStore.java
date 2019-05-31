package md_arshad.qshop.Structures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SearchResultStore {
    private static List<String> searchResults;

    static {
        searchResults =  new ArrayList<String>();
        searchResults.add("Addidas");
        searchResults.add("Fastrack");
        searchResults.add("Men");
        searchResults.add("Women watches");
        searchResults.add("Men watches");
        searchResults.add("Women wears");
        searchResults.add("Clothes");
        searchResults.add("Kids wear");
        searchResults.add("Kids accessories");
        searchResults.add("Electronics");
        searchResults.add("Fashion");
        searchResults.add("Blankets");
        searchResults.add("Bags");
        searchResults.add("Mobiles");
        searchResults.add("Tablets");
        searchResults.add("Laptops");
        searchResults.add("Computer Accessories");
        searchResults.add("Kitchen Accessories");
        searchResults.add("Water bottles");
        searchResults.add("Routers");
    }

    public static List<String> getSearchResults(){
        return searchResults;
    }

    public static List<String> filterData(String searchString){
        List<String> searchResults =  new ArrayList<String>();
        if(searchString.isEmpty()){
            return SearchResultStore.searchResults;
        }
        if(searchString != null){
            searchString = searchString.toLowerCase();

            for(String rec :  SearchResultStore.searchResults){
                if(rec.toLowerCase().contains(searchString)){
                    searchResults.add(rec);
                }
            }
        }
        return searchResults;
    }

}
