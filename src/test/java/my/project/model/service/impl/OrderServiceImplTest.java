package my.project.model.service.impl;

import my.project.model.dao.OrderDao;
import my.project.model.domain.Order;
import my.project.model.entity.OrderEntity;
import my.project.model.exception.InvalidEntityCreation;
import my.project.model.service.mapper.OrderMapper;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {
    private static final Order order = Order.builder().withId(1).build();
    private static final List<OrderEntity> entities = Arrays.asList(
            OrderEntity.builder().withId(1).build(),
            OrderEntity.builder().withId(2).build());


    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private OrderDao orderDao;

    @Mock
    private OrderMapper mapper;

    @InjectMocks
    private OrderServiceImpl service;

    @After
    public void resetMock() {
        reset(orderDao);
        reset(mapper);
    }

    @Test
    public void shouldCreateOrder() {
        when(mapper.mapOrderToOrderEntity(any(Order.class))).thenReturn(entities.get(1));
        when(orderDao.save(any(OrderEntity.class))).thenReturn(true);

        assertTrue(service.createOrder(order));
    }

    @Test
    public void shouldThrowInvalidEntityCreationWithNullOrder() {
        exception.expect(InvalidEntityCreation.class);
        exception.expectMessage("OrderEntity is not valid");

        service.createOrder(null);
    }




}