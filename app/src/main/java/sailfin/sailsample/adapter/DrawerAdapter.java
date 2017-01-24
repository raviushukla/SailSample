package sailfin.sailsample.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import sailfin.sailsample.R;

/**
 * Created by Ravi on 24-01-2017.
 */

public class DrawerAdapter extends BaseAdapter {

    ArrayList<String[]> list_item;
    Context mContext;


    public DrawerAdapter(ArrayList<String[]> list_item,Context mContext)
    {
        this.list_item = list_item;
        this.mContext = mContext;




    }

    @Override
    public int getCount() {
        return list_item.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_menu,null);



        }


        TextView tv= (TextView) view.findViewById(R.id.menuTitleTxt);
        tv.setText(list_item.get(i)[1]);

        ImageView menuIcon = (ImageView) view.findViewById(R.id.menuIcon);
        menuIcon.setImageResource(Integer.parseInt(list_item.get(i)[0]));




        return view;
    }
}
