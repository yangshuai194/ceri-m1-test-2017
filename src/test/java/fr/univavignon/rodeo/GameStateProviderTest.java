package fr.univavignon.rodeo;

import static org.junit.Assert.*;

import fr.univavignon.rodeo.imp.GameState;
import fr.univavignon.rodeo.imp.GameStateProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import fr.univavignon.rodeo.api.*;

@RunWith(MockitoJUnitRunner.class)
public class GameStateProviderTest {

	public static IGameState gameState=GameStateTest.getTestInstance();
	public static IGameState gameStateNull = null;

	protected static IGameStateProvider getTestInstance() throws IllegalStateException,IllegalArgumentException
	{
		IGameStateProvider gameStateProviderMock=new GameStateProvider();
		gameState = new GameState("testGameState",0);
		return gameStateProviderMock;
	}

	@Test
	public void testSave()
	{
		final IGameStateProvider gameStateProvider=getTestInstance();
		gameStateProvider.save(gameState);

	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetNull()
	{
		final IGameStateProvider gameStateProvider=getTestInstance();
		gameStateProvider.get(null);
	}

	@Test
	public void testGet()
	{
		final IGameStateProvider gameStateProvider=getTestInstance();
        assertEquals(gameStateProvider.get("player"),gameState);
	}

	
	
}
