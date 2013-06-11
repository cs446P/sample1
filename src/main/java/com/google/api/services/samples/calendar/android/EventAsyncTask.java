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

import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.io.IOException;



abstract class EventAsyncTask extends AsyncTask<Void, Void, Boolean> {

  final CalendarSampleActivity activity;
  final EventModel model_event;
  final com.google.api.services.calendar.Calendar client;
  private final View progressBar; 

  EventAsyncTask(CalendarSampleActivity activity) {
    this.activity = activity;
    this.model_event = activity.model_event;
    client = activity.client;
    progressBar = activity.findViewById(R.id.title_refresh_progress);
  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    activity.numAsyncTasks++;
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override
  protected final Boolean doInBackground(Void... ignored) {
    try {
      doInBackground();
      return true;
    } catch (final GooglePlayServicesAvailabilityIOException availabilityException) {
      activity.showGooglePlayServicesAvailabilityErrorDialog(
          availabilityException.getConnectionStatusCode());
    } catch (UserRecoverableAuthIOException userRecoverableException) {
      activity.startActivityForResult(
          userRecoverableException.getIntent(), CalendarSampleActivity.REQUEST_AUTHORIZATION);
    } catch (IOException e) {
      Utils.logAndShow(activity, CalendarSampleActivity.TAG, e);
    }
    return false;
  }

  @Override
  protected final void onPostExecute(Boolean success) {
    super.onPostExecute(success);
    if (0 == --activity.numAsyncTasks) {
      progressBar.setVisibility(View.GONE);
    }
    if (success) { 
      activity.startShowEvents();
      Log.d("info","events loaded!");
    }
  }

  abstract protected void doInBackground() throws IOException;
}
