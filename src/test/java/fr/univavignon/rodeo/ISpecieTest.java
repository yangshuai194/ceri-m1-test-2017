package fr.univavignon.rodeo;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import fr.univavignon.rodeo.api.*;

@RunWith(MockitoJUnitRunner.class)
public class ISpecieTest {

	public static List<IAnimal> listAnimal=IntStream
			.range(0,2)
			.mapToObj(i -> IAnimalTest.getTestInstance())
			.collect(Collectors.toList());

	protected static ISpecie getTestInstance()
	{
		ISpecie specieMock=Mockito.mock(ISpecie.class);
		Mockito.when(specieMock.getArea()).thenReturn(50);
		Mockito.when(specieMock.getAnimals()).thenReturn(listAnimal);
		return specieMock;
	}

	@Test
	public void testGetArea()
	{
		final ISpecie specie=getTestInstance();
		assertEquals(specie.getArea(),50);
	}

	@Test
	public void testGetAnimals()
	{
		final ISpecie specie=getTestInstance();
		assertEquals(specie.getAnimals(),listAnimal);
	}

}