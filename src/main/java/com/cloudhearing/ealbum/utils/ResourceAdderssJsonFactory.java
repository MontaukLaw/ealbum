package com.cloudhearing.ealbum.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloudhearing.ealbum.entity.Device;
import com.cloudhearing.ealbum.entity.ResourceAddress;
import com.cloudhearing.ealbum.entity.User;

import java.util.ArrayList;
import java.util.List;


public class ResourceAdderssJsonFactory {

    public static List<ResourceAddress> transResAddrsJsonToEntity(JSONObject obj) throws Exception {
        JSONArray jsonArray = obj.getJSONArray("resourceAddresses");
        List<ResourceAddress> resourceAddressList = new ArrayList<>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.size(); i++) {
                ResourceAddress resourceAddress = new ResourceAddress();

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.getString("id");
                if (id != null && !id.equals("")) {
                    resourceAddress.setId(id);
                }

                String ra = jsonObject.getString("resourceAddress");
                if (ra != null && !ra.equals("")) {
                    resourceAddress.setResourceAddress(ra);
                }

                Long space = jsonObject.getLong("space");
                if (space != null && space > 0) {
                    resourceAddress.setSpace(space);
                }

                //storageId
                String storageId = jsonObject.getString("storageId");
                if (storageId != null && !storageId.equals("")) {
                    resourceAddress.setStorageId(storageId);
                }

                Integer type = jsonObject.getInteger("type");
                if (type != null && type > 0) {
                    resourceAddress.setType(type);
                }

                //previewPic
                String previewPic = jsonObject.getString("previewPic");
                if (previewPic != null && !previewPic.equals("")) {
                    resourceAddress.setPreviewPic(previewPic);
                }

                JSONObject jsonObjectDevice = jsonObject.getJSONObject("device");
                if (jsonObjectDevice != null) {
                    String deviceSN = jsonObjectDevice.getString("sn");
                    if (deviceSN != null && !deviceSN.equals("")) {
                        Device device = new Device();

                        device.setSn(deviceSN);

                        resourceAddress.setDevice(device);
                    }
                }

                JSONObject jsonObjectUser = jsonObject.getJSONObject("uploader");

                if (jsonObjectUser != null) {
                    String userId = jsonObjectUser.getString("id");
                    if (userId != null && !userId.equals("")) {
                        User user = new User();

                        user.setId(userId);
                        resourceAddress.setUploader(user);
                    }
                }

                //logger.debug(jsonArray.getJSONObject(i).getString("resourceAddress"));

                resourceAddressList.add(resourceAddress);

            }

        }
        return resourceAddressList;

    }
}
