package com.iesam.digitallibrary.features.ebook.data;

import com.iesam.digitallibrary.features.ebook.data.local.EBookFileLocalDataSource;
import com.iesam.digitallibrary.features.ebook.domain.EBook;
import com.iesam.digitallibrary.features.ebook.domain.EBookRepository;

public class EBookDataRepository implements EBookRepository {

    EBookFileLocalDataSource eBookFileLocalDataSource = new EBookFileLocalDataSource();

    public EBookDataRepository(EBookFileLocalDataSource eBookFileLocalDataSource) {
    }

    @Override
    public void createEBook(EBook eBook) {
        eBookFileLocalDataSource.save(eBook);
    }
}
