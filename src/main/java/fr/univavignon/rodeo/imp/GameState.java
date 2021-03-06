package fr.univavignon.rodeo.imp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univavignon.rodeo.api.*;

public class GameState implements IGameState{

	private String name;
	private int progression;

	/** **/
	private	Map<ISpecie, SpecieLevel> levels=new HashMap<ISpecie,SpecieLevel>();

	private Map<ISpecie, Integer> totalXp=new HashMap<ISpecie,Integer>();

	private List<IAnimal> animalsCaugut = new ArrayList<>();

	private int currentArea=1;
	
	private IEnvironment currentEnvironment;

	private EnvironmentProvider envProv=new EnvironmentProvider();

	/**
	 *
	 * @param name
	 * @param progression
	 */
	public GameState(String name,int progression)
	{
		this.name=name;
		progression=progression;
		currentEnvironment=envProv.getEnvironment("Savannah");

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
		this.currentArea++;

	}

	@Override
	public void catchAnimal(IAnimal animal) throws IllegalArgumentException,
			IllegalStateException {
		// TODO Auto-generated method stub

		if (animal==null){
			throw new IllegalArgumentException();
		}
		// check if animal exist in this currentarea
		boolean flag2=false;
		ISpecie tmp2=null;
		for(int i=0;i<currentEnvironment.getSpecies().size();++i)
		{
			ISpecie tmp = currentEnvironment.getSpecies().get(i);
			if (tmp.getArea()==currentArea)
			{
				for (int j=0;j<tmp.getAnimals().size();++j)
				{
					if(tmp.getAnimals().get(j).getName().equals(animal.getName()))
					{
						//System.out.println(tmp.getAnimals().get(j).getName()+"---"+animal.getName());
						flag2=true;
						tmp2=tmp;
						break;
					}
				}
				if (flag2) break;
			}
		}
		if (flag2==false) throw new IllegalStateException();


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
		if (totalXp.size()==0)
		{
			totalXp.put(tmp2,animal.getXP());
		}
		else
		{
			for (Map.Entry<ISpecie, Integer> entry : totalXp.entrySet())
			{
				if (entry.getKey().getName().equals(tmp2.getName()))
				{
					totalXp.put(entry.getKey(),entry.getValue()+animal.getXP());
				}
			}
		}
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
			Integer xp=0;
			for (Map.Entry<ISpecie, Integer> entry : totalXp.entrySet())
			{
				if (entry.getKey().getName().equals(specie.getName()))
				{
					xp=entry.getValue();
				}
			}
			if (xp >=0 && xp< 25) return SpecieLevel.NOVICE;
			if (xp >=25 && xp< 150) return SpecieLevel.WRANGLER;
			if (xp >=150 && xp< 600) return SpecieLevel.CHAMPION;
			if (xp >=600) return SpecieLevel.MASTER;
		}
		return SpecieLevel.NOVICE;
	}

	@Override
	public int getProgression() {
		// TODO Auto-generated method stub
		int caught = animalsCaugut.size();
		this.progression = (int) Math.round((caught / this.envProv.listAnimal.size())*100);
		return this.progression;
	}

}
