package com.iesam.digitallibrary.features.ebook.data;

import com.iesam.digitallibrary.features.ebook.data.local.EBookFileLocalDataSource;
import com.iesam.digitallibrary.features.ebook.domain.EBook;
import com.iesam.digitallibrary.features.ebook.domain.EBookRepository;

import java.util.List;

public class EBookDataRepository implements EBookRepository {

    EBookFileLocalDataSource eBookFileLocalDataSource = new EBookFileLocalDataSource();

    public EBookDataRepository(EBookFileLocalDataSource eBookFileLocalDataSource) {
    }

    @Override
    public void createEBook(EBook eBook) {
        eBookFileLocalDataSource.save(eBook);
    }

    @Override
    public void deleteEBook(String isbn) {
        eBookFileLocalDataSource.delete(isbn);
    }

    @Override
    public EBook getEBook(String isbn) {
        return eBookFileLocalDataSource.findById(isbn);
    }

    @Override
    public List<EBook> getEBooks() {
        return eBookFileLocalDataSource.findAll();
    }

}
