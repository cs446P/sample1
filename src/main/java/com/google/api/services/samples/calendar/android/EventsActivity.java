/*
 * Copyright (c) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.api.services.samples.calendar.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class EventsActivity extends Activity{

  ArrayAdapter<String> adapter;
  ListView tv;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.showevents);
    
    //Log.d("info", "new view");
    ArrayList<String> result = getIntent().getExtras().getStringArrayList("info");
    
    String[] it = result.toArray(new String[0]);
    tv = (ListView) findViewById(R.id.event_list);
    
    
    adapter = new ArrayAdapter<String>(
        this, android.R.layout.simple_list_item_1, it) {

        @Override
      public View getView(int position, View convertView, ViewGroup parent) {
        // by default it uses toString; override to use summary instead
        TextView view = (TextView) super.getView(position, convertView, parent);
        String calendarInfo = getItem(position);
        view.setText(calendarInfo);
        return view;
      }
    };
    tv.setAdapter(adapter);
    
    
  }

}
