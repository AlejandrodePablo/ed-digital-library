package com.iesam.digitallibrary.features.digitalresource.data.local;



import com.iesam.digitallibrary.features.digitalresource.domain.DigitalResource;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DigitalResourceMemLocalDataSource {

    public static DigitalResourceMemLocalDataSource instance = null;

    private Map<String, DigitalResource> dataStore = new TreeMap<>();

    public DigitalResourceMemLocalDataSource() {
        //Private Constructor
    }

    public static DigitalResourceMemLocalDataSource getInstance() {
        if (instance == null) {
            instance = new DigitalResourceMemLocalDataSource();
        }
        return instance;
    }

    public void save(DigitalResource model) {
        dataStore.put(model.isbn, model);
    }

    public void saveList(List<DigitalResource> models) {
        for (DigitalResource DigitalResource : models) {
            save(DigitalResource);
        }
    }

    public DigitalResource findById(String id) {
        return dataStore.get(id);
    }

    public List<DigitalResource> findAll() {
        return dataStore.values().stream().toList();
    }

    public void delete(String modelId) {
        dataStore.remove(modelId);
    }

    public void update(DigitalResource updatedDigitalResource) {
        if (dataStore.containsKey(updatedDigitalResource.isbn)) {
            dataStore.put(updatedDigitalResource.isbn, updatedDigitalResource);
            System.out.println("DigitalResource updated successfully");
        } else {
            System.out.println("DigitalResource with ID" + updatedDigitalResource.isbn + " does not exist");
        }
    }

}
