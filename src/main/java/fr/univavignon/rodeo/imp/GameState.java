package fr.univavignon.rodeo.imp;
import java.util.List;
import java.util.Map;

import fr.univavignon.rodeo.api.*;

public class GameState implements IGameState{

	private String name;
	private int progression;

	/** **/
	private	Map<ISpecie, SpecieLevel> levels;

	private Map<Integer, ISpecie> totalXp;

	private List<IAnimal> animalsCaugut;

	private int currentArea;
	
	private IEnvironment currentEnvironment;

	private EnvironmentProvider envProv;

	public GameState() {}
	
	/**
	 * @param name
	 */
	public GameState(String name)
	{
		this.name=name;
		currentArea=1;
		envProv=new EnvironmentProvider();
		currentEnvironment=envProv.getEnvironment("Savannah");
		progression=0;
	}
	
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public void exploreArea() throws IllegalStateException {
		// TODO Auto-generated method stub
		// check current area+1
		if (currentArea+1>currentEnvironment.getAreas())
		{
			throw new IllegalStateException();
		}
		else
		{
			this.currentArea++;
		}
	}

	@Override
	public void catchAnimal(IAnimal animal) throws IllegalArgumentException,
			IllegalStateException {
		// TODO Auto-generated method stub
		if (animal==null){
			throw new IllegalArgumentException();
		}
		// check if animal exist in this currentarea
		for(int i=0;i<currentEnvironment.getSpecies().size();++i)
		{
			boolean flag=false;
			ISpecie tmp = currentEnvironment.getSpecies().get(i);
			if (tmp.getArea()==currentArea)
			{
				for (int j=0;j<tmp.getAnimals().size();++j)
				{
					if(tmp.getAnimals().get(j).getName().equals(animal.getName()))
					{
						flag=true;
						break;
					}
				}
				if (!flag) throw new IllegalStateException();
			}
		}
		boolean flag=false;
		for(int i =0;i<animalsCaugut.size();++i)
		{
			if(animalsCaugut.get(i).getName().equals(animal.getName()))
			{
				flag=true;
				break;
			}
		}
		if (!flag) animalsCaugut.add(animal);
	}

	@Override
	public SpecieLevel getSpecieLevel(ISpecie specie)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (specie==null)
		{
			throw new IllegalArgumentException();
		}
		else{
			for (Map.Entry<ISpecie, SpecieLevel> entry : levels.entrySet())
			{
				if (entry.getKey().getName().equals(specie.getName()))
				{
					return entry.getValue();
				}
			}
		}
		throw new IllegalArgumentException();
	}

	@Override
	public int getProgression() {
		// TODO Auto-generated method stub
		double animal = (double) (animalsCaugut.size()/envProv.listAnimal.size());
		int currentSpecieLvl=0;
		for ( Integer key : totalXp.keySet() ) {
			currentSpecieLvl+=key;
		}
		double specielvl = (double) (currentSpecieLvl/envProv.listSpecie.size()*600);
		int avg = (int) ((animal+specielvl)/2)*100;
		return this.progression;
	}

}
