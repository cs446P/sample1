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

import com.google.api.client.util.Objects;
import com.google.api.services.calendar.model.Event;




class EventInfo implements Comparable<EventInfo>, Cloneable {

  static final String FIELDS = "id,summary";
  static final String FEED_FIELDS = "items(" + FIELDS + ")";

  String id;
  String summary;

  EventInfo(String id, String summary) {
    this.id = id;
    this.summary = summary;
  }

  EventInfo(Event e){
    update(e);
  }
  @Override
  public String toString() {
    return Objects.toStringHelper(EventInfo.class).add("id", id).add("summary", summary)
        .toString();
  }

  public int compareTo(EventInfo other) {
    return summary.compareTo(other.summary);
  }

  @Override
  public EventInfo clone() {
    try {
      return (EventInfo) super.clone();
    } catch (CloneNotSupportedException exception) {
      // should not happen
      throw new RuntimeException(exception);
    }
  }

  void update(Event calendar) {
    id = calendar.getId();
    summary = calendar.getSummary();
  }

}

