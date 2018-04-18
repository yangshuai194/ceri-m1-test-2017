package fr.univavignon.rodeo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import fr.univavignon.rodeo.api.*;

@RunWith(MockitoJUnitRunner.class)
public class IGameStateProviderTest {
	
	protected static IGameStateProvider getTestInstance() throws IllegalStateException,IllegalArgumentException
	{
		IGameStateProvider gameStateProviderMock=Mockito.mock(IGameStateProvider.class);
		IGameStateTest gameStateMock=Mockito.mock(IGameStateTest.class);

		Mockito.doThrow(IllegalStateException.class).when(gameStateMock).testExploreArea();

		return gameStateProviderMock;
	}
}
