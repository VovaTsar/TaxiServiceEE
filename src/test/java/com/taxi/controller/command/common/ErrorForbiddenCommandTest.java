package com.taxi.controller.command.common;

import com.taxi.controller.command.account.client.ClientAccountCommand;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.reset;

@RunWith(MockitoJUnitRunner.class)
public class ErrorForbiddenCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private ErrorForbiddenCommand command;

    @After
    public void resetMock() {
        reset(request, response);
    }

    @Test
    public void executeShouldReturnErrorForbiddenPage() {
        String actual = "/jsp/error/403.jsp";
        String expected = command.execute(request, response);

        assertThat(actual, is(expected));
    }
}
