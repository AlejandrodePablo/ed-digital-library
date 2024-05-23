package com.iesam.digitallibrary.features.digitalresource.data;

import com.iesam.digitallibrary.features.digitalresource.data.local.DigitalResourceFileLocalDataSource;
import com.iesam.digitallibrary.features.digitalresource.domain.DigitalResource;
import com.iesam.digitallibrary.features.digitalresource.domain.DigitalResourceRepository;

public class DigitalResourceDataRepository implements DigitalResourceRepository {

    DigitalResourceFileLocalDataSource dRLocal = new DigitalResourceFileLocalDataSource();

    @Override
    public void createDigitalResource(DigitalResource digitalResource) {
        dRLocal.save(digitalResource);
    }
}
