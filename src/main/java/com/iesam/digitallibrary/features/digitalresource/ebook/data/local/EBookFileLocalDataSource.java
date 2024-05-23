package com.iesam.digitallibrary.features.digitalresource.ebook.data.local;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBook;
import com.iesam.digitallibrary.features.loan.data.local.LoanFileLocalDataSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class EBookFileLocalDataSource {

    private static EBookFileLocalDataSource instance;

    public EBookFileLocalDataSource() {

    }

    public static EBookFileLocalDataSource getInstance() {
        if (instance == null) {
            instance = new EBookFileLocalDataSource();
        }
        return instance;
    }

    private final Type typeList = new TypeToken<ArrayList<EBook>>() {
    }.getType();
    private String nameFile = "EBook.txt";
    private Gson gson = new Gson();

    public void update(EBook updatedEBook) {
        List<EBook> EBooks = findAll();
        for (int i = 0; i < EBooks.size(); i++) {
            EBook EBook = EBooks.get(i);
            if (EBook.isbn.equals(updatedEBook.isbn)) {
                EBooks.set(i, updatedEBook);
                saveToFile(EBooks);
                System.out.println("Usuario actualizado correctamente");
                return;
            }
        }
        System.out.println("el usuario con ID " + updatedEBook.isbn + "no existe");
    }

    public void save(EBook model) {
        List<EBook> models = findAll();
        models.add(model);
        saveToFile(models);
    }

    public void saveList(List<EBook> models) {
        saveToFile(models);
    }

    private void saveToFile(List<EBook> models) {
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

    public EBook findById(String id) {
        List<EBook> models = findAll();
        for (EBook model : models) {
            if (Objects.equals(model.isbn, id)) {
                return model;
            }
        }
        return null;
    }

    public List<EBook> findAll() {
        try {
            File myObj = new File(nameFile);
            if (!myObj.exists()) {
                myObj.createNewFile();
            }
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                return gson.fromJson(data, typeList);
            }
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
        List<EBook> newList = new ArrayList<>();
        List<EBook> models = findAll();
        for (EBook model : models) {
            if (!model.isbn.equals(modelId)) {
                newList.add(model);
            }
        }
        saveList(newList);
    }
}