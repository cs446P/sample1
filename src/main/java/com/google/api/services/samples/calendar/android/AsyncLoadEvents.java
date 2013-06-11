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

import com.google.api.services.calendar.model.Events;

import java.io.IOException;



class AsyncLoadEvents extends EventAsyncTask{

  
  String id;
  
  AsyncLoadEvents(CalendarSampleActivity activity,String id) {
    super(activity);
    this.id = id;
  }

  @Override
  protected void doInBackground() throws IOException {
    Events feed = client.events().list(id).execute();
    model_event.reset(feed.getItems());
  }
  static void run(CalendarSampleActivity calendarSample,String id) {
    new AsyncLoadEvents(calendarSample,id).execute();
  }
  
}
