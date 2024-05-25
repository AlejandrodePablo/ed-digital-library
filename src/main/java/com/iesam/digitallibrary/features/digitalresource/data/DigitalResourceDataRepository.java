package com.iesam.digitallibrary.features.digitalresource.data;

import com.iesam.digitallibrary.features.digitalresource.data.local.DigitalResourceFileLocalDataSource;
import com.iesam.digitallibrary.features.digitalresource.domain.DigitalResource;
import com.iesam.digitallibrary.features.digitalresource.domain.DigitalResourceRepository;

public class DigitalResourceDataRepository implements DigitalResourceRepository {

    public DigitalResourceFileLocalDataSource dRLocal;

    public DigitalResourceDataRepository(DigitalResourceFileLocalDataSource dRLocal) {
        this.dRLocal = dRLocal;
    }

    @Override
    public void getDigitalResources() {
        dRLocal.findAll();
    }

    @Override
    public DigitalResource getDigitalResource(String isbn) {
        return dRLocal.findById(isbn);
    }

}
