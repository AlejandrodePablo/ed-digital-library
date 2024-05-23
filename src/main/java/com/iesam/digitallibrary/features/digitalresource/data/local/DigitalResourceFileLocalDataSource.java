package com.iesam.digitallibrary.features.digitalresource.data.local;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iesam.digitallibrary.features.digitalresource.domain.DigitalResource;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class DigitalResourceFileLocalDataSource {

    private static DigitalResourceFileLocalDataSource instance;

    private String nameFile = "DigitalResource.txt";

    private Gson gson = new Gson();

    private final Type typeList = new TypeToken<ArrayList<DigitalResource>>() {
    }.getType();

    public void update(DigitalResource updatedDigitalResource) {
        List<DigitalResource> DigitalResources = findAll();
        for (int i = 0; i < DigitalResources.size(); i++) {
            DigitalResource DigitalResource = DigitalResources.get(i);
            if (DigitalResource.id.equals(updatedDigitalResource.id)) {
                DigitalResources.set(i, updatedDigitalResource);
                saveToFile(DigitalResources);
                System.out.println("Usuario actualizado correctamente");
                return;
            }
        }
        System.out.println("el usuario con ID " + updatedDigitalResource.id + "no existe");
    }

    public void save(DigitalResource model) {
        List<DigitalResource> models = findAll();
        models.add(model);
        saveToFile(models);
    }

    public void saveList(List<DigitalResource> models) {
        saveToFile(models);
    }

    private void saveToFile(List<DigitalResource> models) {
        try {
            FileWriter myWriter = new FileWriter(nameFile);
            myWriter.write(gson.toJson(models));
            myWriter.close();
            System.out.println("Datos guardados correctamente");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al guardar la información.");
            e.printStackTrace();
        }
    }

    public DigitalResource findById(String id) {
        List<DigitalResource> models = findAll();
        for (DigitalResource model : models) {
            if (Objects.equals(model.id, id)) {
                return model;
            }
        }
        return null;
    }

    public List<DigitalResource> findAll() {
        try {
            File myObj = new File(nameFile);
            if (!myObj.exists()) {
                myObj.createNewFile();
            }
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                myReader.close();
                return gson.fromJson(data, typeList);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ha ocurrido un error al obtener el listado.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al crear el fichero.");
            throw new RuntimeException(e);
        }
        return new ArrayList<>();
    }

    public void delete(String modelId) {
        List<DigitalResource> newList = new ArrayList<>();
        List<DigitalResource> models = findAll();
        for (DigitalResource model : models) {
            if (!model.id.equals(modelId)) {
                newList.add(model);
            }
        }
        saveList(newList);
    }
}