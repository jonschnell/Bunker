package com.hv_07.bunker;
import com.hv_07.bunker.login.LoginContract;
import com.hv_07.bunker.login.LoginInfo;
import com.hv_07.bunker.registration.RegistrationContract;
import com.hv_07.bunker.registration.RegistrationInfo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.junit.Test;

import static org.junit.Assert.*;
import java.util.Date;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
public class RegistrationInfoTestcases {
    private RegistrationInfo registration;
    @Mock
    RegistrationContract.View registrationView;

    @Before
    public void setUp(){
        registration=new RegistrationInfo("TestUser","TestAdd","TestFN","TestLN","TestEM","TestPH","TestPass",registrationView);
    }
    @Test
    public void testGetters(){
        assertEquals("TestUser",registration.getUser());
        assertEquals("TestAdd",registration.getAddress());
        assertEquals("TestFN",registration.getFirstName());
        assertEquals("TestLN",registration.getLastName());
        assertEquals("TestEM",registration.getEmail());
        assertEquals("TestPH",registration.getPhone());
        assertEquals("TestPass",registration.getPassword());
    }
}
