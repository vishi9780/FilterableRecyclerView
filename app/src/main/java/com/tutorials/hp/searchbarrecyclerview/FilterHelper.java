package com.tutorials.hp.searchbarrecyclerview;

import android.widget.Filter;

import java.util.ArrayList;
import java.util.HashMap;

public class FilterHelper extends Filter {
    static ArrayList<HashMap<String,String>> currentList;
    static MyAdapter adapter;

    public static FilterHelper newInstance(ArrayList<HashMap<String,String>> currentList, MyAdapter adapter) {
        FilterHelper.adapter=adapter;
        FilterHelper.currentList=currentList;
        return new FilterHelper();
    }

    /*
    - Perform actual filtering.
     */
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults filterResults=new FilterResults();

        if(constraint != null && constraint.length()>0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();

            //HOLD FILTERS WE FIND
            ArrayList<HashMap<String,String>> hashMapArrayList=new ArrayList<>();

            String galaxy;

            //ITERATE CURRENT LIST
            for (int i=0;i<currentList.size();i++)
            {
                galaxy= currentList.get(i).get("character");

                //SEARCH
                if(galaxy.toUpperCase().contains(constraint))
                {
                    //ADD IF FOUND
                    HashMap<String,String> hashMap=new HashMap<>();
                    hashMap.put("character",galaxy);
                    hashMapArrayList.add(hashMap);
                }
            }

            //SET RESULTS TO FILTER LIST
            filterResults.count=hashMapArrayList.size();
            filterResults.values=hashMapArrayList;
        }else
        {
            //NO ITEM FOUND.LIST REMAINS INTACT
            filterResults.count=currentList.size();
            filterResults.values=currentList;
        }

        //RETURN RESULTS
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

        adapter.setGalaxies((ArrayList<HashMap<String, String>>) filterResults.values);
        adapter.notifyDataSetChanged();
    }
}
