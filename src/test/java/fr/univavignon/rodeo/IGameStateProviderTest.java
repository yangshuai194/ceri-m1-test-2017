package fr.univavignon.rodeo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import fr.univavignon.rodeo.api.*;

@RunWith(MockitoJUnitRunner.class)
public class IGameStateProviderTest {
	
	public static IGameState gameState = IGameStateTest.getTestInstance();

	protected static IGameStateProvider getTestInstance() throws IllegalStateException,IllegalArgumentException
	{
		IGameStateProvider gameStateProviderMock=Mockito.mock(IGameStateProvider.class);
		Mockito.doThrow(IllegalArgumentException.class).when(gameStateProviderMock).get(null);
		Mockito.when(gameStateProviderMock.get("gm")).thenReturn(gameState);
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
        assertEquals(gameStateProvider.get("gm"),gameState); 
	}

	
	
}
