package sailfin.sailsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import sailfin.sailsample.R;
import sailfin.sailsample.general.files.GeneralFunctions;

/**
 * Created by Ravi on 24-01-2017.
 */

public class MyImagesrecyclerAdapter extends RecyclerView.Adapter<MyImagesrecyclerAdapter.ViewHolder> {

    ArrayList<HashMap<String, String>> list_item;
    Context mContext;
    GeneralFunctions generalFunctions;

    public MyImagesrecyclerAdapter(Context context, ArrayList<HashMap<String,String>> list_item)
    {

        this.mContext = context;
        this.list_item = list_item;
        generalFunctions = new GeneralFunctions(mContext);



    }



    @Override
    public MyImagesrecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_images, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(MyImagesrecyclerAdapter.ViewHolder holder, int position) {

        if (holder instanceof MyImagesrecyclerAdapter.ViewHolder) {
            final HashMap<String, String> item = list_item.get(position);
//            final String userId = item.get("UserId");
//            final String imageId = item.get("ImgId");

            Picasso.with(mContext)
                    .load(item.get("imgUrl"))
                    .into(holder.myImage);

        }


    }

    @Override
    public int getItemCount() {
        return list_item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        public ImageView myImage;


        public ViewHolder(View itemView) {
            super(itemView);

            myImage = (ImageView) itemView.findViewById(R.id.my_image);

        }
    }


}
