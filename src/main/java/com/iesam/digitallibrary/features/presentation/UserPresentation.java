package com.iesam.digitallibrary.features.presentation;

import com.iesam.digitallibrary.features.data.UserDataRepository;
import com.iesam.digitallibrary.features.data.local.UserMemLocalDataSource;
import com.iesam.digitallibrary.features.domain.NewUserUseCase;
import com.iesam.digitallibrary.features.domain.User;

public class UserPresentation {

    public static void createUser(User user) {
        UserDataRepository userDataRepository = UserMemLocalDataSource.newInstance();
        NewUserUseCase newUserUseCase = new NewUserUseCase(userDataRepository);
        newUserUseCase.execute(user);
    }
}
