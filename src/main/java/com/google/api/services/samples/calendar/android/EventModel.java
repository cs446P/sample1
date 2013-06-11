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

import com.google.api.services.calendar.model.Event;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



class EventModel {

  
  //List<EventInfo> ret = new ArrayList<EventInfo>();
  Map<String, EventInfo> calendars = new HashMap<String, EventInfo>();

  int size() {
    synchronized (calendars) {
      return calendars.size();
    }
  }

  void remove(String id) {
    synchronized (calendars) {
      calendars.remove(id);
    }
  }

  EventInfo get(String id) {
    synchronized (calendars) {
      return calendars.get(id);
    }
  }

  void add(Event calendarToAdd) {
    synchronized (calendars) {
      //EventInfo found = get(calendarToAdd.getId());
      //if (found == null) {
        calendars.put(calendarToAdd.getId(), new EventInfo(calendarToAdd));
      //} else {
      //  found.update(calendarToAdd);
     // }
    }
  }


  void reset(List<Event> eventsToAdd) {
    synchronized (calendars) {
      calendars.clear();
      for (Event eventToAdd : eventsToAdd) {
        Log.d("Info","added " + eventToAdd.getSummary());
        Log.d("Info","added " + eventToAdd.getId());
        add(eventToAdd);
       // EventInfo e = new EventInfo(eventToAdd);
       // ret.add(e);
      }
    }
  }

  

  public ArrayList<String> toSortedArray() {
    synchronized (calendars) {
      ArrayList<String> result = new ArrayList<String>();
      for (EventInfo calendar : calendars.values()) {
        result.add(calendar.clone().summary);
        Log.d("info","adding");
      }
      Collections.sort(result);
      Log.d("info","size is "+ result.size());
      Log.d("info","map size is "+ calendars.size());
      return result;
    }
  }
   
}
