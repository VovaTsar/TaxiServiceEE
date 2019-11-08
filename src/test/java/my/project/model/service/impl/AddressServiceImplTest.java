package my.project.model.service.impl;

import my.project.model.dao.AddressDao;
import my.project.model.domain.Address;
import my.project.model.entity.AddressEntity;
import my.project.model.exception.EntityCreationRuntimeException;
import my.project.model.service.mapper.AddressMapper;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceImplTest {
    private static final Address ADDRESS = Address.builder().withId(1).build();
    private static final List<AddressEntity> ENTITIES = Arrays.asList(
            AddressEntity.builder().withId(1).build(),
            AddressEntity.builder().withId(2).build());
    private static final List<Address> ADDRESSES = Arrays.asList(ADDRESS, ADDRESS);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private AddressDao addressDao;

    @Mock
    private AddressMapper mapper;

    @InjectMocks
    private AddressServiceImpl service;

    @After
    public void resetMock() {
        reset(addressDao,mapper);
    }

    @Test
    public void shouldCreateAddress() {
        when(mapper.mapAddressToAddressEntity(any(Address.class))).thenReturn(ENTITIES.get(1));
        when(addressDao.save(any(AddressEntity.class))).thenReturn(true);

        assertTrue(service.createAddress(ADDRESS));
    }

    @Test
    public void shouldThrowInvalidEntityCreationWithNullAddress() {
        exception.expect(EntityCreationRuntimeException.class);
        exception.expectMessage("AddressEntity is not valid");

        service.createAddress(null);
    }

    @Test
    public void shouldShowAllAddresses() {
        when(addressDao.findAll(1,2)).thenReturn(ENTITIES);
        when(mapper.mapAddressEntityToAddress(any(AddressEntity.class))).thenReturn(ADDRESS);

        List<Address> actual = service.findAll(1,2);

        assertEquals(ADDRESSES, actual);
    }

    @Test
    public void shouldReturnEmptyList() {
        when(addressDao.findAll(1,2)).thenReturn(Collections.emptyList());

        List<Address> actual = service.findAll(1,2);

        assertEquals(Collections.emptyList(), actual);
    }

}