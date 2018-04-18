package fr.univavignon.rodeo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import fr.univavignon.rodeo.api.*;

@RunWith(MockitoJUnitRunner.class)
public class IEnvironmentProviderTest {
	public static List<String> listAvailableEnv=Arrays.asList("Env1","Env2");
	public static IEnvironment environment;
	
	protected static IEnvironmentProvider getTestInstance()
	{
		IEnvironmentProvider environmentProviderMock=Mockito.mock(IEnvironmentProvider.class);
		Mockito.when(environmentProviderMock.getAvailableEnvironments()).thenReturn(listAvailableEnv);
		Mockito.when(environmentProviderMock.getEnvironment("Env1")).thenReturn(environment);
		return environmentProviderMock;
	}
	
	@Test
	public void testGetAvailableEnvironments()
	{
		final IEnvironmentProvider environmentProvider=getTestInstance();
        assertEquals(environmentProvider.getAvailableEnvironments(),listAvailableEnv); 
	}

	@Test
	public void testGetEnvironment()
	{
		final IEnvironmentProvider environmentProvider=getTestInstance();
        assertEquals(environmentProvider.getEnvironment("Env1"),environment); 
	}
	
}
