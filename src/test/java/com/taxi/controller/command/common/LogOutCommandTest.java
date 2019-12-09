package com.taxi.controller.command.common;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LogOutCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @InjectMocks
    private LogOutCommand command;

    @After
    public void resetMock() {
        reset(request, response,session);
    }

    @Test
    public void executeShouldReturnSuccessfulLoginPage() {
        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("/taxi");
        when(request.getServletPath()).thenReturn("/user");
        String actual = "redirect#/taxi/user/homePage";
        String expected = command.execute(request, response);

        assertThat(actual, is(expected));
    }
}