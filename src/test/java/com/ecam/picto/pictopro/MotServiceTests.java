package com.ecam.picto.pictopro;

import com.ecam.picto.pictopro.entity.Mot;
import com.ecam.picto.pictopro.repository.MotRepository;
import com.ecam.picto.pictopro.service.MotServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MotServiceTests {
    @InjectMocks
    MotServiceImpl motService;
    @Mock
    MotRepository motRepository;
    @Test
    public void testAjouterUnMot() {
        Mot mot = new Mot();
        motService.ajouterUnMot(mot);
        verify(motRepository, times(1)).save(mot);
    }
    @Test
    public void testFindAll() {
        motService.findAll();
        verify(motRepository, times(1)).findAll();
    }
    @Test
    public void testFindById() {
        Optional<Mot> mot = Optional.of(new Mot());
        when(motRepository.findById(1)).thenReturn(mot);
        Mot result = motService.findById(1);
        assertEquals(mot.get(), result);
    }
    @Test
    public void testModifierUnMot() {
        Mot ancienMot = new Mot();
        ancienMot.setId(1);
        Mot nouveauMot = new Mot();
        nouveauMot.setId(1);
        when(motRepository.findById(1)).thenReturn(Optional.of(ancienMot));
        when(motRepository.save(any(Mot.class))).thenReturn(nouveauMot);
        Mot result = motService.modifierUnMot(ancienMot, nouveauMot);
        assertEquals(nouveauMot, result);
    }
}
