package fr.univavignon.rodeo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.univavignon.rodeo.imp.Animal;
import fr.univavignon.rodeo.imp.Specie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import fr.univavignon.rodeo.api.*;

@RunWith(MockitoJUnitRunner.class)
public class SpecieTest {
	
	public static List<IAnimal> listAnimal;

	protected static ISpecie getTestInstance()
	{
		listAnimal=Arrays.asList(
				new Animal(1,"Forest Buffalo", false, false, false),
				new Animal(3,"Diabuffalo", true, false, false),
				new Animal(30,"Buff the Magic Dragon", false, true, false)
		);

		ISpecie specieMock=new Specie("Buffalo", 1, listAnimal);
		return specieMock;
	}

	@Test
	public void testGetName()
	{
		final ISpecie specie=getTestInstance();
		assertEquals(specie.getName(),"Buffalo");
	}

	@Test
	public void testGetArea()
	{
		final ISpecie specie=getTestInstance();
        assertEquals(specie.getArea(),1);
	}

	@Test
	public void testGetAnimals()
	{
		final ISpecie specie=getTestInstance();
        assertEquals(specie.getAnimals(),listAnimal); 
	}
	
}
