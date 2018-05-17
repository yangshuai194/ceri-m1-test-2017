package fr.univavignon.rodeo;

import static org.junit.Assert.*;

import fr.univavignon.rodeo.imp.Animal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import fr.univavignon.rodeo.api.*;

@RunWith(MockitoJUnitRunner.class)
public class AnimalTest {
	
	protected static IAnimal getTestInstance()
	{
		IAnimal animalMock=new Animal( 30,"Buff the Magic Dragon",
				false, true, false);
		return animalMock;
	}

	@Test
	public void testGetName(){
		final IAnimal animal=getTestInstance();
		assertEquals(animal.getName(),"Buff the Magic Dragon");
	}

	@Test
	public void testGetXP()
	{
		final IAnimal animal=getTestInstance();
        assertEquals(animal.getXP(),30);
	}
	
	@Test
	public void testIsSecret()
	{
		final IAnimal animal=getTestInstance();
        assertEquals(animal.isSecret(),false);
	}
	
	@Test
	public void testIsEndangered()
	{
		final IAnimal animal=getTestInstance();
        assertEquals(animal.isEndangered(),true);
	}
	
	@Test
	public void testIsBoss()
	{
		final IAnimal animal=getTestInstance();
        assertEquals(animal.isBoss(),false);
	}
	
}
