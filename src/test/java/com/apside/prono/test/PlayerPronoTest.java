package com.apside.prono.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apside.prono.errors.InvalidPlayerDataException;
import com.apside.prono.errors.PlayerUnknownException;
import com.apside.prono.model.Player;
import com.apside.prono.repository.PlayerRepository;
import com.apside.prono.service.PlayerService;

@RunWith(SpringJUnit4ClassRunner.class)
public class PlayerPronoTest {
	Player p1 = new Player(1L, "Remy", "Brochet", "remy@brohetr.fr", new Date());
    Player p2 = new Player(2L, "polo", "roger", "chiniolivier@yahoo.fr", new Date());

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void canGetAllPlayers() {
        List<Player> playerEntityList = new ArrayList<>();

        playerEntityList.add(p1);
        playerEntityList.add(p2);
        when(playerRepository.findAll()).thenReturn(playerEntityList);
        List<Player> result = (List<Player>) playerService.getAllPlayers();
        assertEquals(2, result.size());
    }

    //Test getPlayerById

    @Test
    public void canGetPlayerById() {
    	
       when(playerRepository.findById(2L)).thenReturn(Optional.of(p2));
        assertNotNull(p2);
    }
    
    @Test(expected = PlayerUnknownException.class)
    public void testEntityNotFoundPlayerUnknownException() throws Exception {
    	playerService.getPlayerById(4444L) ;
    }
    
	//Test modifyPlayer
    
    @Test
    public void updatePlayer() {
    	Player p2 = playerService.updatePlayer(p1);
    	assertNotEquals(p1.getMail(), p2.getMail());
    }
    	    
    @Test
    public void savePlayer() {
    	Player playerEntity = new Player();
        playerEntity.setMail("ee");

        Player playerSave = new Player();
        playerSave.setId(1L);
        playerSave.setMail("aa");
	
        when(playerRepository.save(playerEntity)).thenReturn(playerSave);
    }
//
//    public void candeletePlayer() {
//    	
//    	playerService.deletePlayer(1L);
//
//    }
}