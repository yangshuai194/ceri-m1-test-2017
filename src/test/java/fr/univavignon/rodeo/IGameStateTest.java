package fr.univavignon.rodeo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import fr.univavignon.rodeo.api.*;

@RunWith(MockitoJUnitRunner.class)
public class IGameStateTest {
	public static ISpecie specie=ISpecieTest.getTestInstance();
	public static IAnimal animal=IAnimalTest.getTestInstance();
	
	protected static IGameState getTestInstance() throws IllegalStateException,IllegalArgumentException
	{
		IGameState gameStateMock=Mockito.mock(IGameState.class);
		Mockito.doThrow(IllegalStateException.class).when(gameStateMock).exploreArea();
		Mockito.doThrow(IllegalArgumentException.class).when(gameStateMock).catchAnimal(null);
		Mockito.doThrow(IllegalStateException.class).when(gameStateMock).catchAnimal(animal);
		Mockito.doThrow(IllegalArgumentException.class).when(gameStateMock).getSpecieLevel(Mockito.isA(ISpecie.class));
		Mockito.when(gameStateMock.getProgression()).thenReturn(60);
		Mockito.when(gameStateMock.getSpecieLevel(Mockito.isA(ISpecie.class))).thenReturn(SpecieLevel.NOVICE);
		return gameStateMock;
	}
	
	@Test(expected=IllegalStateException.class)
	public void testExploreArea()
	{
		final IGameState gameState=getTestInstance();
		gameState.exploreArea();
        //assertEquals(gameState.exploreArea(),gameState.catchAnimal(animal)); 
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCatchAnimalNull()
	{
		final IGameState gameState=getTestInstance();
		gameState.catchAnimal(null);
        //assertEquals(gameState.getEnvironment("Env1"),environment); 
	}
	
	@Test(expected=IllegalStateException.class)
	public void testCatchAnimalNotNull()
	{
		final IGameState gameState=getTestInstance();
		gameState.catchAnimal(animal);
        //assertEquals(gameState.getEnvironment("Env1"),environment); 
	}
	
	@Test
	public void testGetSpecieLevel()
	{
		final IGameState gameState=getTestInstance();
		assertEquals(gameState.getSpecieLevel(specie),SpecieLevel.NOVICE); 
	}
	
	@Test
	public void testGetProgression()
	{
		final IGameState gameState=getTestInstance();
        assertEquals(gameState.getProgression(),60); 
	}
	
}
