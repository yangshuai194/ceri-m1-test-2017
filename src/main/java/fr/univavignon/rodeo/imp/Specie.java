package fr.univavignon.rodeo.imp;
import java.util.List;

import fr.univavignon.rodeo.api.*;

/**
 * @author uapv1304003
 *
 */
public class Specie implements ISpecie{

	private String name;
	private int area;
	private List<IAnimal> animals;
	
	
	/**
	 * 
	 */
	public Specie(){}
	
	
	/**
	 * @param name
	 * @param area
	 * @param animals
	 */
	public Specie(String name,int area,List<IAnimal> animals)
	{
		this.name=name;
		this.area=area;
		this.animals=animals;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public int getArea() {
		// TODO Auto-generated method stub
		return this.area;
	}

	@Override
	public List<IAnimal> getAnimals() {
		// TODO Auto-generated method stub
		return this.animals;
	}

}
