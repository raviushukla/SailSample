package sailfin.sailsample.sailsample;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

import sailfin.sailsample.R;
import sailfin.sailsample.adapter.DrawerAdapter;
import sailfin.sailsample.adapter.MyImagesrecyclerAdapter;
import sailfin.sailsample.general.files.ExecuteWebServerUrl;
import sailfin.sailsample.general.files.GeneralFunctions;
import sailfin.sailsample.utils.Utils;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    DrawerLayout drawerLayout;
    DrawerAdapter drawerAdapter;
    ImageView imgMenu;
    ListView listView;
    public ArrayList<String[]> list_menu_items;
    ArrayList<HashMap<String, String>> list_images;
    RecyclerView recyclerView;
    MyImagesrecyclerAdapter myImagesrecyclerAdapter;
    ProgressBar progressBar;
    GeneralFunctions generalFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generalFunctions = new GeneralFunctions(getActContext());

        imgMenu = (ImageView) findViewById(R.id.drawericon);
        imgMenu.setOnClickListener(new OnClickList());

        listView = (ListView) findViewById(R.id.menuListView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        progressBar = (ProgressBar) findViewById(R.id.loading);

        list_images = new ArrayList<>();
        myImagesrecyclerAdapter = new MyImagesrecyclerAdapter(getActContext(), list_images);

        recyclerView = (RecyclerView) findViewById(R.id.myImagesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myImagesrecyclerAdapter);

        getMenu();
        getImages();
    }

    private void getImages() {

        list_images.clear();
        myImagesrecyclerAdapter.notifyDataSetChanged();
        progressBar.setVisibility(View.VISIBLE);


        HashMap<String,String> parameters = new HashMap<>();

        parameters.put("type", "loadUserImages");
        parameters.put("iMemberId", "1");


        ExecuteWebServerUrl executeWebServerUrl = new ExecuteWebServerUrl(parameters);
        executeWebServerUrl.setDataResponseListener(new ExecuteWebServerUrl.SetDataResponse() {
            @Override
            public void setResponse(String responseString) {


                progressBar.setVisibility(View.GONE);

                if (responseString !=null && !responseString.equals(""))
                {

                    if (generalFunctions.isDataAvail("Action",responseString))
                    {
                        JSONArray jsonArray = generalFunctions.getJsonArr("message",responseString);
                        Utils.printLog("arr", "::" + jsonArray);
                        if (jsonArray != null)
                        {
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                String imgUrl = generalFunctions.getJsonValue("vImage",generalFunctions.getJsonObject(jsonArray,i).toString());
                                String userId = generalFunctions.getJsonValue("iUserId",generalFunctions.getJsonObject(jsonArray,i).toString());
                                String imageId = generalFunctions.getJsonValue("iImgId",generalFunctions.getJsonObject(jsonArray,i).toString());


                                HashMap<String,String> map_data = new HashMap<String, String>();
                                map_data.put("imgUrl",imgUrl);
                                map_data.put("userId",userId);
                                map_data.put("imageId",imageId);

                                list_images.add(map_data);

                            }

                            myImagesrecyclerAdapter.notifyDataSetChanged();

                        }
                        else
                        {

                            Toast.makeText(getActContext(),"No Data Found",Toast.LENGTH_LONG).show();

                        }

                    }


                }
                else
                {


                    Toast.makeText(getActContext(),"Error",Toast.LENGTH_LONG).show();


                }




            }
        });

        executeWebServerUrl.execute();






    }

    private void getMenu() {

        if (list_menu_items == null) {
            list_menu_items = new ArrayList();
            drawerAdapter = new DrawerAdapter(list_menu_items, getActContext());

            listView.setAdapter(drawerAdapter);
            listView.setOnItemClickListener(this);


        } else {

            list_menu_items.clear();


        }

        list_menu_items.add(new String[]{"" + R.mipmap.ic_launcher, "About Us", "" + Utils.MENU_ABOUT_US});
        list_menu_items.add(new String[]{"" + R.mipmap.ic_launcher, "My Images", "" + Utils.MENU_IMAGES});
        list_menu_items.add(new String[]{"" + R.mipmap.ic_launcher, "My Address", "" + Utils.MENU_ADDRESS});

        drawerAdapter.notifyDataSetChanged();


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        int itemId = Integer.parseInt(list_menu_items.get(position)[2]);
        switch (itemId) {
            case Utils.MENU_SIGN_OUT:
                //  generalFunc.signOut();

                break;

            case Utils.MENU_ADDRESS:

//                Intent i = new Intent(DashboardActivity.this, AddressActivity.class);
//                startActivity(i);

                break;

            case Utils.MENU_IMAGES:

//                Intent i1 = new Intent(DashboardActivity.this, MyImagesActivity.class);
//                startActivity(i1);

                break;

        }


    }


    public class OnClickList implements View.OnClickListener {


        @Override
        public void onClick(View view) {

            if (view.getId() == imgMenu.getId())

            {

                loadDrawer();

            }

        }
    }


    public void loadDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START) == true) {

            closeDrawer();
        } else {

            openDrawer();
        }


    }


    public void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);

    }

    public void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);

    }


    public Context getActContext() {
        return MainActivity.this;
    }
}
