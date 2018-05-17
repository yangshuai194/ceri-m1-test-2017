package fr.univavignon.rodeo.imp;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import fr.univavignon.rodeo.api.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class EnvironmentProvider implements IEnvironmentProvider {

	public static final String FILE_PATH = "src/main/resources/ListAnimals.csv";
	private List<String> availableEnvironments;
	public List<IEnvironment> listEnvironment;
	public List<ISpecie> listSpecie;
	public List<IAnimal> listAnimal;
	public List<String> envArea;
	private IEnvironment iEnvrionment;
	
	public EnvironmentProvider() {
		this.listSpecie=new ArrayList<ISpecie>();
		this.listAnimal=new ArrayList<IAnimal>();
		this.listEnvironment=new ArrayList<IEnvironment>();
		this.availableEnvironments=new ArrayList<String>();
		this.envArea=new ArrayList<String>();
		loadCSV();
	}

	private void loadCSV()
	{
		try {
			final Reader reader = new InputStreamReader(new FileInputStream(FILE_PATH), "UTF-8");
			final CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
			// read animal-list file to load environments, species, animal, isDanger, isSecret, isBoss, xp
			for (final CSVRecord record : parser) {
				/* get animals */
				final String animal = record.get("Animal");

				/* get species */
				final String specie = record.get("Species");

				/* get class */
				final String className = record.get("Class");
				//System.out.println(className);

				/* get environments */
				final String unlocked = record.get("Unlocked");
				//System.out.println(unlocked);
				final String envir=unlocked.split(" ")[0];
				final String area=unlocked.split(" ")[1];
				if (!envArea.contains(unlocked))
				{
					envArea.add(unlocked);
				}
				/* get XP */
				final String exp = record.get("XP Given");

				addData(animal,specie,className,envir,area,exp);
			}
			parser.close();
			reader.close();
		}
		catch (final IOException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * @param availableEnvironments
	 * @param iEnvironment
	 */
	public EnvironmentProvider(List<String> availableEnvironments, IEnvironment iEnvironment)
	{
		this.availableEnvironments=availableEnvironments;
		this.iEnvrionment=iEnvrionment;
	}
	
	@Override
	public List<String> getAvailableEnvironments() {
		// TODO Auto-generated method stub
		for(int i=0;i<this.listEnvironment.size();++i)
		{
			this.availableEnvironments.add(this.listEnvironment.get(i).getName());
		}
		return this.availableEnvironments;
	}

	@Override
	public IEnvironment getEnvironment(String name)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(name==null) throw new IllegalArgumentException();
		for (int i = 0;i<this.listEnvironment.size();i++)
		{
			if (this.listEnvironment.get(i).getName().equals(name))
			{
				return this.listEnvironment.get(i);
			}
		}
		return null;
	}


	private void addData(String animal, String specie, String className, String envir, String area, String exp) {
		Animal anim=new Animal(Integer.parseInt(exp),animal,className.equals("Secret"),
								className.equals("Endangered"),className.equals("Boss"));

		/*this.listAnimal.add(anim);
		if(!containSpecie(specie)) {
			Specie sp=new Specie(specie,Integer.parseInt(area),this.listAnimal);
			this.listSpecie.add(sp);
		}
		*/
		// check if environment exist already
			// check if list specie contains specie
		//System.out.println(containEnvironment(envir));
		if(containEnvironment(envir)==-1) {
			Environment env = new Environment(envir, Integer.parseInt(area), new ArrayList<ISpecie>());
			this.listEnvironment.add(env);
		}
		else
		{
			//System.out.println(containEnvironment(envir));
			List<ISpecie> ls=this.listEnvironment.get(containEnvironment(envir)).getSpecies();
			if(containSpecie(specie)==-1) {
				Specie sp=new Specie(specie,Integer.parseInt(area),new ArrayList<IAnimal>());
				this.listEnvironment.get(containEnvironment(envir)).getSpecies().add(sp);
				//System.out.println(this.listEnvironment.get(containEnvironment(envir)).getName()+"  "+this.listEnvironment.get(containEnvironment(envir)).getSpecies().size());
			}
			else
			{
				//System.out.println(/*ls.get(containSpecie(specie)).getName()+*/ls.size()+" -> "+containSpecie(specie));
				List<IAnimal> la = this.listEnvironment.get(containEnvironment(envir)).getSpecies().get(containSpecie(specie)).getAnimals();
				//this.listEnvironment.get(containEnvironment(envir)).getSpecies().get(containSpecie(specie)).getAnimals().add(anim);
				la.add(anim);
				//System.out.println(this.listEnvironment.get(containEnvironment(envir)).getSpecies().get(containSpecie(specie)).getAnimals().size());
			}
		}
	}

	private int containEnvironment(String envir) {
		int flag=-1;
		for (int i=0;i<this.listEnvironment.size();++i)
		{
			if (this.listEnvironment.get(i).getName().equals(envir))
			{
				flag=i;
				break;
			}
		}
		return flag;
	}

	private int containSpecie(String specie) {
		int flag=-1;
		for (int i=0;i<this.listEnvironment.size();++i)
		{
			for (int j =0;j<this.listEnvironment.get(i).getSpecies().size();++j){
				if (this.listEnvironment.get(i).getSpecies().get(j).getName().equals(specie))
				{
					flag=j;
					break;
				}
			}
		}
		return flag;
	}
	public static void main(String [] args)
	{
		EnvironmentProvider e =new EnvironmentProvider();
		for (int i =0;i<e.getEnvironment("Savannah").getSpecies().size();++i)
		{
			for (int j=0;j<e.getEnvironment("Savannah").getSpecies().get(i).getAnimals().size();++j)
			{
				System.out.println(e.getEnvironment("Savannah").getSpecies().get(i).getAnimals().get(j).getName());
			}
		}
	}
}
