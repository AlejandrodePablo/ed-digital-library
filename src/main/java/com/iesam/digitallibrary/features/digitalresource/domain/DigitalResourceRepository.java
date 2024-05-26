package com.iesam.digitallibrary.features.digitalresource.domain;

import java.util.List;

public interface DigitalResourceRepository {

    List<DigitalResource> getDigitalResources();

    DigitalResource getDigitalResource(String isbn);
}
