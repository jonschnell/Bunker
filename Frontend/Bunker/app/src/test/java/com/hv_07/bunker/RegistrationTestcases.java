package com.hv_07.bunker;
import com.hv_07.bunker.login.LoginContract;
import com.hv_07.bunker.login.LoginInfo;
import com.hv_07.bunker.registration.RegistrationContract;
import com.hv_07.bunker.registration.RegistrationPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.junit.Test;

import static org.junit.Assert.*;
import java.util.Date;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
public class RegistrationTestcases {
    private RegistrationContract.Presenter presenter;
    @Mock
    RegistrationContract.View registration;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        presenter = new RegistrationPresenter(registration);
    }

    @Test
    public void TestLogic(){
        assertFalse(presenter.isNotEmpty(""));
        assertTrue(presenter.isNotEmpty("NotEmpty"));

        assertTrue(presenter.isEqual("Test","Test"));
        assertFalse(presenter.isEqual("Test1","Test2"));

        assertTrue(presenter.isLong("TestPass"));
        assertFalse(presenter.isLong("Test"));

    }
}
