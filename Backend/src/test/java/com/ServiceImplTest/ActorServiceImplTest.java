package com.ServiceImplTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.movierentalapp.enitites.Actor;
import com.cg.movierentalapp.repositories.ActorRepository;
import com.cg.movierentalapp.service.ActorServiceImpl;


public class ActorServiceImplTest {

    @Mock
    private ActorRepository actorRepository;

    @InjectMocks
    private ActorServiceImpl actorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllActors() {
        Actor actor1 = new Actor();
        actor1.setActorId((short) 1);
        actor1.setFirstName("John");
        actor1.setLastName("Doe");
        Actor actor2 = new Actor();
        actor2.setActorId((short) 2);
        actor2.setFirstName("Jane");
        actor2.setLastName("Smith");
        List<Actor> actorList = Arrays.asList(actor1, actor2);
        
        when(actorRepository.findAll()).thenReturn(actorList);
        
        List<Actor> result = actorService.findAllActors();
        
        assertThat(result).isEqualTo(actorList);
        verify(actorRepository, times(1)).findAll();
    }

    @Test
    public void testAddActor() {
        Actor actor = new Actor();
        actor.setActorId((short) 1);
        actor.setFirstName("John");
        actor.setLastName("Doe");
        
        when(actorRepository.save(any(Actor.class))).thenReturn(actor);
        
        Actor result = actorService.addActor(actor);
        
        assertThat(result).isEqualTo(actor);
        verify(actorRepository, times(1)).save(any(Actor.class));
    }

    @Test
    public void testFindActorsByLastName() {
        String lastName = "Doe";
        Actor actor1 = new Actor();
        actor1.setActorId((short) 1);
        actor1.setFirstName("John");
        actor1.setLastName("Doe");
        Actor actor2 = new Actor();
        actor2.setActorId((short) 2);
        actor2.setFirstName("Jane");
        actor2.setLastName("Doe");
        List<Actor> actorList = Arrays.asList(actor1, actor2);
        
        when(actorRepository.findByLastName(lastName)).thenReturn(actorList);
        
        List<Actor> result = actorService.findActorsByLastName(lastName);
        
        assertThat(result).isEqualTo(actorList);
        verify(actorRepository, times(1)).findByLastName(lastName);
    }

    @Test
    public void testFindActorsByFirstName() {
        String firstName = "John";
        Actor actor1 = new Actor();
        actor1.setActorId((short) 1);
        actor1.setFirstName("John");
        actor1.setLastName("Doe");
        Actor actor2 = new Actor();
        actor2.setActorId((short) 2);
        actor2.setFirstName("John");
        actor2.setLastName("Smith");
        List<Actor> actorList = Arrays.asList(actor1, actor2);
        
        when(actorRepository.findByFirstName(firstName)).thenReturn(actorList);
        
        List<Actor> result = actorService.findActorsByFirstName(firstName);
        
        assertThat(result).isEqualTo(actorList);
        verify(actorRepository, times(1)).findByFirstName(firstName);
    }

    @Test
    public void testUpdateActorLastName() {
        short actorId = 1;
        String newLastName = "Smith";
        Actor actor = new Actor();
        actor.setActorId(actorId);
        actor.setFirstName("John");
        actor.setLastName("Doe");
        
        when(actorRepository.findById(actorId)).thenReturn(Optional.of(actor));
        when(actorRepository.save(any(Actor.class))).thenReturn(actor);
        
        Actor result = actorService.updateActorLastName(actorId, newLastName);
        
        assertThat(result).isEqualTo(actor);
        assertThat(result.getLastName()).isEqualTo(newLastName);
        verify(actorRepository, times(1)).findById(actorId);
        verify(actorRepository, times(1)).save(any(Actor.class));
    }
}
