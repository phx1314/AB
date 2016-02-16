//
//  {{classname}}
//
//  Created by {{creater}} on {{time}}
//  Copyright (c) {{creater}} All rights reserved.


/**
   {{mark}}
*/

package {{package}};

import java.util.ArrayList;
import java.util.List;
import com.mdx.framework.activity.TitleAct;
import android.app.Dialog;
import com.mdx.framework.utility.Helper;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class {{classname}} extends ListActivity{
    private List<TestActivity> arrayData;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayData = getData();
        ArrayAdapter<TestActivity> adapter = new ArrayAdapter<TestActivity>(this, android.R.layout.simple_list_item_1,arrayData);
        setListAdapter(adapter);
    }


    private List<TestActivity> getData() {
        List<TestActivity> data = new ArrayList<TestActivity>();
    	data.add(new TestActivity("Card Test", DebugAdaActivity.class));
    	{{FrgString}}
    	{{activitString}}
    	{{DiaString}}
    	data.add(new TestActivity("chosePhotos", com.mdx.framework.frg.multiplephoto.MultiplePhotoSelect.class, null));
    	data.add(new TestActivity("selectPhoto", null, null));
        return data;
    }

   public class TestActivity {
        public String name = "";
        
        public Class<?> activity, fragment;
        
        public Dialog dialog;
        
        public TestActivity(String name,Dialog dialog){
            this.dialog=dialog;
            this.name=name;
        }
        
        public TestActivity(String name, Class<?> activity) {
            this.name = name;
            this.activity = activity;
        }
        
        public TestActivity(String name, Class<?> fragment, Class<?> activity) {
            this.name = name;
            this.activity = activity;
            this.fragment = fragment;
        }
        
        public void StartActivity() {
			if (this.name.equals("chosePhotos")) {
				Helper.getPhotos(
						DebugActivity.this,
						new com.mdx.framework.widget.getphoto.PopUpdataPhoto.OnReceiverPhotos() {

							@Override
							public void onReceiverPhoto(ArrayList<String> list) {
								System.out.println(list);
							}
						}, 3);
				return;
			}else if(this.name.equals("selectPhoto")){
				Helper.getPhoto(
						DebugActivity.this,
						new com.mdx.framework.widget.getphoto.PopUpdataPhoto.OnReceiverPhoto() {

							@Override
							public void onReceiverPhoto(String photoPath,
									int width, int height) {
								System.out.println(photoPath);
							}
						}, 100,100,640,640);
				return;
			}
            if (fragment != null) {
				if (activity == null) {
					Helper.startActivity({{classname}}.this, fragment, TitleAct.class);
				} else {
					Helper.startActivity({{classname}}.this, fragment, activity);
				}
			} else if (dialog != null) {
				dialog.show();
			} else {
				Intent i = new Intent(DebugActivity.this, activity);
				startActivity(i);
			}
            
        }
        
        public String toString() {
            return name;
        }
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        arrayData.get(position).StartActivity();
    }
 
}
