package com.hv_07.bunker;
import com.hv_07.bunker.login.LoginContract;
import com.hv_07.bunker.login.LoginInfo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.junit.Test;

import static org.junit.Assert.*;
import java.util.Date;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class LoginTestcases {
    @Mock
    private LoginContract.View loginView;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void TestConstructor(){
        LoginInfo login = new LoginInfo("Test","Test",loginView);
        assertEquals("Test",login.getUsername());
        assertEquals("Test",login.getPassword());
    }

    @Test
    public void TestGettersAndSetters(){
        LoginInfo login = new LoginInfo("Test","Test",loginView);
        login.setUsername("Test1");
        login.setPassword("Test1");
        assertEquals("Test1",login.getUsername());
        assertEquals("Test1",login.getPassword());
    }
    @Test
    public void TestResponseChecker(){
        LoginInfo login = new LoginInfo("Test","Test",loginView);
        assertTrue(login.responseChecker("1"));
        assertFalse(login.responseChecker("0"));
    }
}
