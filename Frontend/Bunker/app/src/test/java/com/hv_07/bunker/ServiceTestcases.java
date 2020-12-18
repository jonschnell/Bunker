package com.hv_07.bunker;
import com.hv_07.bunker.login.LoginContract;
import com.hv_07.bunker.login.LoginInfo;
import com.hv_07.bunker.services.Service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.junit.Test;

import static org.junit.Assert.*;
import java.util.Date;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
public class ServiceTestcases {
    private Service service;
    @Before
    public void setUp(){
        service = new Service("testName","testUser","testPass","testID");
    }

    @Test
    public void TestGetters(){
        assertEquals("testName",service.getName());
        assertEquals("testUser",service.getUsername());
        assertEquals("testPass",service.getPassword());
        assertEquals("testID",service.getID());
    }
}
