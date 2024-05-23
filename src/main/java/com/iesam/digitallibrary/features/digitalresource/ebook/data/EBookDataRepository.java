package com.iesam.digitallibrary.features.digitalresource.ebook.data;

import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBook;
import com.iesam.digitallibrary.features.digitalresource.ebook.domain.EBookRepository;
import com.iesam.digitallibrary.features.digitalresource.ebook.data.local.EBookFileLocalDataSource;

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

    @Override
    public void updateEBook(EBook eBook) {
        eBookFileLocalDataSource.update(eBook);
    }

}
