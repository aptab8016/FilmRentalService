package com.ControllerTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.movierentalapp.controllers.ActorController;
import com.cg.movierentalapp.enitites.Actor;
import com.cg.movierentalapp.service.IActorService;

public class ActorControllerTest {

    @Mock
    private IActorService actorService;

    @InjectMocks
    private ActorController actorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddActor() {
        Actor actor = new Actor();
        actor.setActorId((short) 1);
        actor.setFirstName("John");
        actor.setLastName("Doe");

        when(actorService.addActor(any(Actor.class))).thenReturn(actor);

        ResponseEntity<String> response = actorController.addActor(actor);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo("Record Created Successfully");
        verify(actorService, times(1)).addActor(any(Actor.class));
    }

    @Test
    public void testGetAllActors() {
        Actor actor1 = new Actor();
        actor1.setActorId((short) 1);
        actor1.setFirstName("John");
        actor1.setLastName("Doe");
        Actor actor2 = new Actor();
        actor2.setActorId((short) 2);
        actor2.setFirstName("Jane");
        actor2.setLastName("Smith");
        List<Actor> actorList = Arrays.asList(actor1, actor2);

        when(actorService.findAllActors()).thenReturn(actorList);

        ResponseEntity<List<Actor>> response = actorController.getAllActors();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(actorList);
        verify(actorService, times(1)).findAllActors();
    }

    @Test
    public void testSearchActorsByLastName() {
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

        when(actorService.findActorsByLastName(lastName)).thenReturn(actorList);

        ResponseEntity<List<Actor>> response = actorController.searchActorsByLastName(lastName);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(actorList);
        verify(actorService, times(1)).findActorsByLastName(lastName);
    }

    @Test
    public void testSearchActorsByFirstName() {
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

        when(actorService.findActorsByFirstName(firstName)).thenReturn(actorList);

        ResponseEntity<List<Actor>> response = actorController.searchActorsByFirstName(firstName);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(actorList);
        verify(actorService, times(1)).findActorsByFirstName(firstName);
    }

    @Test
    public void testUpdateActorLastName() {
        short actorId = 1;
        String newLastName = "NewLastName";
        Map<String, String> requestBody = Map.of("newLastName", newLastName);

        ResponseEntity<String> response = actorController.updateActorLastName(actorId, requestBody);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Last Name Updated Successfully");
        verify(actorService, times(1)).updateActorLastName(actorId, newLastName);
    }
}

