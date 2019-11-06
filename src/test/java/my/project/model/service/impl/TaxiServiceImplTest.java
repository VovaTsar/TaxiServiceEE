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
    private static final Taxi taxi = Taxi.builder().withId(1).build();
    private static final List<TaxiEntity> entities = Arrays.asList(
            TaxiEntity.builder().withId(1).build(),
            TaxiEntity.builder().withId(2).build());
    private static final List<Taxi> taxis = Arrays.asList(taxi,taxi);

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
        reset(taxiDao);
        reset(mapper);
    }

    @Test
    public void shouldCreateTaxi() {
        when(mapper.mapTaxiToTaxiEntity(any(Taxi.class))).thenReturn(entities.get(1));
        when(taxiDao.save(any(TaxiEntity.class))).thenReturn(true);

        assertTrue(service.createTaxi(taxi));
    }

    @Test
    public void shouldThrowInvalidEntityCreationWithNullTaxi() {
        exception.expect(InvalidEntityCreation.class);
        exception.expectMessage("TaxiEntity is not valid");

        service.createTaxi(null);
    }

    @Test
    public void shouldShowAllTaxis() {
        when(taxiDao.findAll()).thenReturn(entities);
        when(mapper.mapTaxiEntityToTaxi(any(TaxiEntity.class))).thenReturn(taxi);

        List<Taxi> actual = service.findAllTaxis();

        assertEquals(taxis, actual);
    }

    @Test
    public void shouldReturnEmptyList() {
        when(taxiDao.findAll()).thenReturn(Collections.emptyList());

        List<Taxi> actual = service.findAllTaxis();

        assertEquals(Collections.emptyList(), actual);
    }
}