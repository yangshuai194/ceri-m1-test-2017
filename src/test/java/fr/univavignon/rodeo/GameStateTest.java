package fr.univavignon.rodeo;

import static org.junit.Assert.*;

import fr.univavignon.rodeo.imp.Animal;
import fr.univavignon.rodeo.imp.GameState;
import fr.univavignon.rodeo.imp.Specie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import fr.univavignon.rodeo.api.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GameStateTest {
	public static IAnimal animal=AnimalTest.getTestInstance();
	public static ISpecie specie=SpecieTest.getTestInstance();
	protected static IGameState getTestInstance() throws IllegalStateException,IllegalArgumentException
	{
		IGameState gameStateMock=new GameState("testGameState",0);
		return gameStateMock;
	}

	@Test(expected=IllegalStateException.class)
	public void testExploreArea()
	{
		final IGameState gameState=getTestInstance();
		gameState.exploreArea();
		gameState.exploreArea();
		gameState.exploreArea();
		gameState.exploreArea();
		gameState.exploreArea();
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCatchAnimalNull()
	{
		final IGameState gameState=getTestInstance();
		gameState.catchAnimal(null);
	}

	@Test(expected=IllegalStateException.class)
	public void testCatchAnimalNotNull()
	{
		final IGameState gameState=getTestInstance();
		IAnimal anim=new Animal(3,"bu",true,true,true);
		gameState.catchAnimal(anim);
	}

	@Test
	public void testGetSpecieLevel()
	{
		final IGameState gameState=getTestInstance();
		List l = Arrays.asList(new Animal(1,"Forest Buffalo", false, false, false));
		Specie tmp = new Specie("Buffalo",1,l);
		assertEquals(gameState.getSpecieLevel(tmp),SpecieLevel.NOVICE);
		IAnimal animal = new Animal( 30,"Buff the Magic Dragon", false, true, false);
		gameState.catchAnimal(animal);
		//System.out.println(gameState.getSpecieLevel(tmp));
		assertEquals(gameState.getSpecieLevel(specie),SpecieLevel.WRANGLER);
		for(int i=0;i<5;i++)
		{
			gameState.catchAnimal(animal);
		}
		assertEquals(gameState.getSpecieLevel(specie),SpecieLevel.CHAMPION);
		for(int i=0;i<20;i++)
		{
			gameState.catchAnimal(animal);
		}
		assertEquals(gameState.getSpecieLevel(specie),SpecieLevel.MASTER);
	}



	@Test
	public void testGetProgression()
	{
		final IGameState gameState=getTestInstance();
        assertEquals(gameState.getProgression(),0);
	}

}
