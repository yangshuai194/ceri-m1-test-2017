package fr.univavignon.rodeo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import fr.univavignon.rodeo.imp.Environment;
import fr.univavignon.rodeo.imp.EnvironmentProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import fr.univavignon.rodeo.api.*;

@RunWith(MockitoJUnitRunner.class)
public class EnvironmentProviderTest {
	public static List<String> listAvailableEnv=Arrays.asList("Savannah", "Jungle", "Mountains", "Outback", "Tundra",
																"Jurassic", "Olympus", "Garden");
	public static IEnvironment environment;
	
	protected static IEnvironmentProvider getTestInstance()
	{
		IEnvironmentProvider environmentProviderMock=new EnvironmentProvider();
		environment=((EnvironmentProvider) environmentProviderMock).listEnvironment.get(1);
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
        assertEquals(environmentProvider.getEnvironment("Jungle"),environment);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetEnvironmentNull()
	{
		final IEnvironmentProvider environmentProvider=getTestInstance();
		environmentProvider.getEnvironment(null);
	}
	
}
