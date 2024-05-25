package com.iesam.digitallibrary.features.digitalresource.domain;

public interface DigitalResourceRepository {

    void getDigitalResources();
    DigitalResource getDigitalResource(String isbn);
}
