package fr.univavignon.rodeo;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.univavignon.rodeo.imp.Environment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import fr.univavignon.rodeo.api.*;

@RunWith(MockitoJUnitRunner.class)
public class EnvironmentTest {
		public static List<ISpecie> listSpecie=IntStream
			.range(0,2)
			.mapToObj(i -> ISpecieTest.getTestInstance())
			.collect(Collectors.toList());

	protected static IEnvironment getTestInstance()
	{
		IEnvironment environmentMock=new Environment("Savannah",1,listSpecie);
		return environmentMock;
	}
	
	@Test
	public void testGetAreas()
	{
		final IEnvironment environment=getTestInstance();
        assertEquals(environment.getAreas(),1);
	}

	@Test
	public void testGetSpecies()
	{
		final IEnvironment environment=getTestInstance();
        assertEquals(environment.getSpecies(),listSpecie); 
	}
	
}
