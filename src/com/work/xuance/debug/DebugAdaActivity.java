//
//  DebugAdaActivity
//
//  Created by Administrator on 2015-10-02 21:04:22
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.debug;

import android.app.ListActivity;
import android.os.Bundle;


public class DebugAdaActivity extends  ListActivity {
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DebugAda debugdad=new DebugAda(this);
        setListAdapter(debugdad);
    }
    
}

