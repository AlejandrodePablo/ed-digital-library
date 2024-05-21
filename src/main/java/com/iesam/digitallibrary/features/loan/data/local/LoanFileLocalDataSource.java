package com.iesam.digitallibrary.features.loan.data.local;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iesam.digitallibrary.features.loan.domain.Loan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class LoanFileLocalDataSource {

    private static LoanFileLocalDataSource instance;

    public LoanFileLocalDataSource() {

    }

    public static LoanFileLocalDataSource getInstance() {
        if (instance == null) {
            instance = new LoanFileLocalDataSource();
        }
        return instance;
    }

    private final Type typeList = new TypeToken<ArrayList<Loan>>() {
    }.getType();
    private String nameFile = "Loan.txt";
    private Gson gson = new Gson();
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public void update(Loan updatedLoan) {
        List<Loan> loans = findAll();
        for (int i = 0; i < loans.size(); i++) {
            Loan loan = loans.get(i);
            if (loan.loanId.equals(updatedLoan.loanId)) {
                loans.set(i, updatedLoan);
                saveToFile(loans);
                System.out.println("Préstamo actualizado correctamente");
                return;
            }
        }
        System.out.println("El préstamo con ID " + updatedLoan.loanId + " no existe");
    }

    public void save(Loan model) {
        List<Loan> models = findAll();
        models.add(model);
        saveToFile(models);
    }

    public void saveList(List<Loan> models) {
        saveToFile(models);
    }

    private void saveToFile(List<Loan> models) {
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

    public Loan findById(String id) {
        List<Loan> models = findAll();
        for (Loan model : models) {
            if (Objects.equals(model.loanId, id)) {
                return model;
            }
        }
        return null;
    }

    public List<Loan> findAll() {
        try {
            File myObj = new File(nameFile);
            if (!myObj.exists()) {
                myObj.createNewFile();
            }
            Scanner myReader = new Scanner(myObj);
            List<Loan> loans = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (!data.isEmpty()) {
                    loans = gson.fromJson(data, typeList);
                }
            }
            myReader.close();
            return loans;
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
        List<Loan> newList = new ArrayList<>();
        List<Loan> models = findAll();
        for (Loan model : models) {
            if (!model.loanId.equals(modelId)) {
                newList.add(model);
            }
        }
        saveList(newList);
    }
}
