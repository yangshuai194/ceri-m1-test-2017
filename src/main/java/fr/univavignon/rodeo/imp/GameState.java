package fr.univavignon.rodeo.imp;
import java.util.Map;

import fr.univavignon.rodeo.api.*;

public class GameState implements IGameState{

	private String name;
	private int progression;

	/** **/
	private	Map<ISpecie, SpecieLevel> levels;

	private Map<Integer, ISpecie> totalXp;
	
	private int currentArea;
	
	private Environment currentEnvironment;
	
	public GameState() {}
	
	/**
	 * @param name
	 * @param progression
	 */
	public GameState(String name, int progression)
	{
		this.name=name;
		this.progression=progression;
	}
	
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public void exploreArea() throws IllegalStateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void catchAnimal(IAnimal animal) throws IllegalArgumentException,
			IllegalStateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SpecieLevel getSpecieLevel(ISpecie specie)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getProgression() {
		// TODO Auto-generated method stub
		return this.progression;
	}

}
