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
		this.listAnimal.add(anim);
		if(!containSpecie(specie)) {
			Specie sp=new Specie(specie,Integer.parseInt(area),this.listAnimal);
			this.listSpecie.add(sp);
		}
		if(!containEnvironment(envir)) {
			Environment env = new Environment(envir, Integer.parseInt(area), this.listSpecie);
			this.listEnvironment.add(env);
		}
	}

	private boolean containEnvironment(String envir) {
		boolean flag=false;
		for (int i=0;i<this.listEnvironment.size();++i)
		{
			if (this.listEnvironment.get(i).getName().equals(envir))
			{
				flag=true;
				break;
			}
		}
		return flag;
	}

	private boolean containSpecie(String specie) {
		boolean flag=false;
		for (int i=0;i<this.listSpecie.size();++i)
		{
			if (this.listSpecie.get(i).getName().equals(specie))
			{
				flag=true;
				break;
			}
		}
		return flag;
	}


	public static void main (String [] args)
	{
		EnvironmentProvider ep = new EnvironmentProvider();
		ep.loadCSV();
		System.out.println(ep.listSpecie.size());
	}
}
