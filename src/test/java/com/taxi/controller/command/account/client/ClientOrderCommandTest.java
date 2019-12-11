package com.taxi.controller.command.account.client;


import com.taxi.model.domain.Address;
import com.taxi.model.service.AddressService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientOrderCommandTest {

    private static final Address ADDRESS = getAddress();
    private static final List<Address> ADDRESSES = Arrays.asList(ADDRESS, ADDRESS);

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private AddressService service;

    @InjectMocks
    private ClientOrderCommand command;

    @After
    public void resetMock() {
        reset(request, response, service);
    }


    @Test
    public void executeShouldReturnClientOrderPage() {
        when(service.getAllAddress()).thenReturn(ADDRESSES);
        String expected = "/jsp/accountClient/taxiOrder.jsp";
        String actual = command.execute(request, response);

        assertThat(actual, is(expected));
        verify(request).setAttribute(anyString(), any());
    }

    private static Address getAddress() {
        return new Address(1, "Polytech", "2B");
    }
}
