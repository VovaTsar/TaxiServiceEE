package my.project.model.service.impl;

import my.project.model.dao.TaxiDao;
import my.project.model.domain.Taxi;
import my.project.model.entity.TaxiEntity;
import my.project.model.exception.InvalidEntityCreation;
import my.project.model.service.mapper.TaxiMapper;
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

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaxiServiceImplTest {
    private static final Taxi TAXI = Taxi.builder().withId(1).build();
    private static final List<TaxiEntity> ENTITIES = Arrays.asList(
            TaxiEntity.builder().withId(1).build(),
            TaxiEntity.builder().withId(2).build());
    private static final List<Taxi> TAXIS = Arrays.asList(TAXI, TAXI);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private TaxiDao taxiDao;

    @Mock
    private TaxiMapper mapper;

    @InjectMocks
    private TaxiServiceImpl service;

    @After
    public void resetMock() {
        reset(taxiDao,mapper);
    }

    @Test
    public void shouldCreateTaxi() {
        when(mapper.mapTaxiToTaxiEntity(any(Taxi.class))).thenReturn(ENTITIES.get(1));
        when(taxiDao.save(any(TaxiEntity.class))).thenReturn(true);

        assertTrue(service.createTaxi(TAXI));
    }

    @Test
    public void shouldThrowInvalidEntityCreationWithNullTaxi() {
        exception.expect(InvalidEntityCreation.class);
        exception.expectMessage("TaxiEntity is not valid");

        service.createTaxi(null);
    }

    @Test
    public void shouldShowAllTaxis() {
        when(taxiDao.findAll(1,2)).thenReturn(ENTITIES);
        when(mapper.mapTaxiEntityToTaxi(any(TaxiEntity.class))).thenReturn(TAXI);

        List<Taxi> actual = service.findAll(1,2);

        assertEquals(TAXIS, actual);
    }

    @Test
    public void shouldReturnEmptyList() {
        when(taxiDao.findAll(1,2)).thenReturn(Collections.emptyList());

        List<Taxi> actual = service.findAll(1,2);

        assertEquals(Collections.emptyList(), actual);
    }
}