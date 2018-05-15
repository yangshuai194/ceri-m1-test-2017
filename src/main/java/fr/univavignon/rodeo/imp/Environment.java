package fr.univavignon.rodeo.imp;
import java.util.List;

import fr.univavignon.rodeo.api.*;

public class Environment implements IEnvironment{

	private String name;
	private int area;
	private List<ISpecie> species;
	
	public Environment() {}
	
	/**
	 * @param name
	 * @param area
	 * @param species
	 */
	public Environment(String name, int area, List<ISpecie> species)
	{
		this.name=name;
		this.area=area;
		this.species=species;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public int getAreas() {
		// TODO Auto-generated method stub
		return this.area;
	}

	@Override
	public List<ISpecie> getSpecies() {
		// TODO Auto-generated method stub
		return this.species;
	}

	
}
