package com.example.park.data;

import android.util.Log;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.park.controller.AppController;
import com.example.park.model.Activities;
import com.example.park.model.Data;
import com.example.park.model.EntranceFees;
import com.example.park.model.Images;
import com.example.park.model.OperatingHours;
import com.example.park.model.StandardHours;
import com.example.park.model.Topics;
import com.example.park.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    static List<Data> parkList = new ArrayList<>();

    public static void getParks(final AsyncResponse callback, String stateCode) {
        String url = Util.getParksUrl(stateCode);
        Log.d("URL", "getParks: " + url);
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, url, null, response -> {

                    try {
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Data data = new Data();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            data.setId(jsonObject.getString("id"));
                            data.setFullName(jsonObject.getString("fullName"));
                            data.setLatitude(jsonObject.getString("latitude"));
                            data.setLongitude(jsonObject.getString("longitude"));
                            data.setParkCode(jsonObject.getString("parkCode"));
                            data.setStates(jsonObject.getString("states"));

                            JSONArray imageList = jsonObject.getJSONArray("images");
                            List<Images> list = new ArrayList<>();
                            for (int j = 0; j < imageList.length(); j++) {
                                Images images = new Images();
                                images.setCredit(imageList.getJSONObject(j).getString("credit"));
                                images.setTitle(imageList.getJSONObject(j).getString("title"));
                                images.setUrl(imageList.getJSONObject(j).getString("url"));

                                list.add(images);
                            }
                            data.setImages(list);

                            data.setWeatherInfo(jsonObject.getString("weatherInfo"));
                            data.setName(jsonObject.getString("name"));
                            data.setDesignation(jsonObject.getString("designation"));

                            //Setup Activities
                            JSONArray activityArray = jsonObject.getJSONArray("activities");
                            List<Activities> activitiesList = new ArrayList<>();
                            for (int j = 0; j < activityArray.length(); j++) {
                                Activities activities = new Activities();
                                activities.setId(activityArray.getJSONObject(j).getString("id"));
                                activities.setName(activityArray.getJSONObject(j).getString("name"));

                                activitiesList.add(activities);
                            }
                            data.setActivities(activitiesList);

                            //Topics
                            JSONArray topicsArray = jsonObject.getJSONArray("topics");
                            List<Topics> topicsList = new ArrayList<>();
                            for (int j = 0; j < topicsArray.length(); j++) {
                                Topics topics = new Topics();
                                topics.setId(topicsArray.getJSONObject(j).getString("id"));
                                topics.setName(topicsArray.getJSONObject(j).getString("name"));
                                topicsList.add(topics);

                            }
                            data.setTopics(topicsList);

                            //Operating Hours
                            JSONArray opHours = jsonObject.getJSONArray("operatingHours");
                            List<OperatingHours> operatingHours = new ArrayList<>();
                            for (int j = 0; j < opHours.length(); j++) {
                                OperatingHours op = new OperatingHours();
                                op.setDescription(opHours.getJSONObject(j).getString("description"));
                                StandardHours standardHours = new StandardHours();
                                JSONObject hours = opHours.getJSONObject(j).getJSONObject("standardHours");

                                standardHours.setWednesday(hours.getString("wednesday"));
                                standardHours.setMonday(hours.getString("monday"));
                                standardHours.setThursday(hours.getString("thursday"));
                                standardHours.setSunday(hours.getString("sunday"));
                                standardHours.setTuesday(hours.getString("tuesday"));
                                standardHours.setFriday(hours.getString("friday"));
                                standardHours.setSaturday(hours.getString("saturday"));
                                op.setStandardHours(standardHours);

                                operatingHours.add(op);

                            }

                            data.setOperatingHours(operatingHours);

                            data.setDirectionsInfo(jsonObject.getString("directionsInfo"));

                            data.setDescription(jsonObject.getString("description"));


                            //Entrance fees
                            JSONArray entranceFeesArray = jsonObject.getJSONArray("entranceFees");
                            List<EntranceFees> entranceFeesList = new ArrayList<>();
                            for (int j = 0; j < entranceFeesArray.length(); j++) {
                                EntranceFees entranceFees = new EntranceFees();
                                entranceFees.setCost(entranceFeesArray.getJSONObject(j).getString("cost"));
                                entranceFees.setDescription(entranceFeesArray.getJSONObject(j).getString("description"));
                                entranceFees.setTitle(entranceFeesArray.getJSONObject(j).getString("title"));
                                entranceFeesList.add(entranceFees);

                            }
                            data.setEntranceFees(entranceFeesList);
                            data.setWeatherInfo(jsonObject.getString("weatherInfo"));


                            parkList.add(data);

                        }
                        if (null != callback) {
                            callback.processPark(parkList);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }, Throwable::printStackTrace);

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}