package com.taxi.controller.command.account.client;

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
public class ClientAccountCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private ClientAccountCommand command;

    @After
    public void resetMock() {
        reset(request, response);
    }

    @Test
    public void executeShouldReturnClientAccountPage() {
        String actual = "/jsp/accountClient/clientAccount.jsp";
        String expected = command.execute(request, response);

        assertThat(actual, is(expected));
    }
}