package com.hv_07.bunker;
import com.hv_07.bunker.login.LoginContract;
import com.hv_07.bunker.login.LoginInfo;
import com.hv_07.bunker.user.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.junit.Test;

import static org.junit.Assert.*;
import java.util.Date;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
public class UserTestcases {
    private User user;

    @Before
    public void setUp(){
        user = new User("Test");
    }

    @Test
    public void testGetter(){
        assertEquals("Test",user.ID);
    }

}
