package com.iesam.digitallibrary.features.digitalresource.audiobook.data.local;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iesam.digitallibrary.features.digitalresource.audiobook.domain.Audiobook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AudiobookFileLocalDataSource {

    private static AudiobookFileLocalDataSource instance;

    private String nameFile = "Audiobook.txt";

    private Gson gson = new Gson();

    private final Type typeList = new TypeToken<ArrayList<Audiobook>>() {
    }.getType();

    public void update(Audiobook updatedAudiobook) {
        List<Audiobook> Audiobooks = findAll();
        for (int i = 0; i < Audiobooks.size(); i++) {
            Audiobook Audiobook = Audiobooks.get(i);
            if (Audiobook.isbn.equals(updatedAudiobook.isbn)) {
                Audiobooks.set(i, updatedAudiobook);
                saveToFile(Audiobooks);
                System.out.println("Usuario actualizado correctamente");
                return;
            }
        }
        System.out.println("el usuario con ID " + updatedAudiobook.isbn + "no existe");
    }

    public void save(Audiobook model) {
        List<Audiobook> models = findAll();
        models.add(model);
        saveToFile(models);
    }

    public void saveList(List<Audiobook> models) {
        saveToFile(models);
    }

    private void saveToFile(List<Audiobook> models) {
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

    public Audiobook findById(String id) {
        List<Audiobook> models = findAll();
        for (Audiobook model : models) {
            if (Objects.equals(model.isbn, id)) {
                return model;
            }
        }
        return null;
    }

    public List<Audiobook> findAll() {
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
        List<Audiobook> newList = new ArrayList<>();
        List<Audiobook> models = findAll();
        for (Audiobook model : models) {
            if (!model.isbn.equals(modelId)) {
                newList.add(model);
            }
        }
        saveList(newList);
    }
}