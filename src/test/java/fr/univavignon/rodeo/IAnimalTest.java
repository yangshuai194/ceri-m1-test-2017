package fr.univavignon.rodeo;

import static org.junit.Assert.*;

import fr.univavignon.rodeo.imp.Animal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import fr.univavignon.rodeo.api.*;

@RunWith(MockitoJUnitRunner.class)
public class IAnimalTest {
	
	protected static IAnimal getTestInstance()
	{
		IAnimal animalMock=new Animal(50,"animal",true,true,true);
		return animalMock;
	}

	@Test
	public void testGetName(){
		final IAnimal animal=getTestInstance();
		assertEquals(animal.getName(),"animal");
	}

	@Test
	public void testGetXP()
	{
		final IAnimal animal=getTestInstance();
        assertEquals(animal.getXP(),50); 
	}
	
	@Test
	public void testIsSecret()
	{
		final IAnimal animal=getTestInstance();
        assertEquals(animal.isSecret(),true); 
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
        assertEquals(animal.isBoss(),true);
	}
	
}
