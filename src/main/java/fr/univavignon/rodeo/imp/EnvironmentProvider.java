package fr.univavignon.rodeo.imp;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import fr.univavignon.rodeo.api.*;

public class EnvironmentProvider implements IEnvironmentProvider {

	public static final String FILE_PATH = "src/main/resources/animal-list.csv";
	private List<String> availableEnvironments;
	private IEnvironment iEnvrionment;
	
	public EnvironmentProvider() {}
	
	/**
	 * @param environments
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
		return this.availableEnvironments;
	}

	@Override
	public IEnvironment getEnvironment(String name)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		// https://docs.google.com/spreadsheets/d/1Pt_bnx2vTLi7NSl_R73WHqwIRhOZF7lkaufwv_4jTJo/edit#gid=0
		try {
			// read animal-list file to load environments, species, animal, isDanger, isSecret, isBoss
			// but this file dosen't contain animals' xp and the area 
			// which means that we need to parse the site to collect these data
			final List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
			for(int i = 0; i < lines.size();i++)
			{
				List<String> contents = Arrays.asList(lines.get(i).split(","));
				String envir="";
				//System.out.println(contents);

				if(contents.size()==1) // it's a environment
				{
					envir=contents.get(0);
					//System.out.println(envir);
				}
				else // specie,animal, isDanger, isSecret, isBoss
				{
					List<Animal> animals;
					String specie = contents.get(0);
					boolean secret,boss,enddanger;
					//System.out.println(contents.size());
					/*for(int j=1;i<7;++j) // animals
					{
						System.out.println();

					}*/
					/*for(int j= 11;j<13;++i) // isDanger, isSecret, isBoss
					{
						if(contents.get(j)!="-")
						{
							
						}
					}*/
					
				}
				//System.out.println(lines.get(i));
			}
		}
		catch (final IOException e) {
			throw new IllegalStateException(e);
		}
    	return null;
	}
	
	public static void main (String [] args)
	{
		EnvironmentProvider ep = new EnvironmentProvider();
		ep.getEnvironment("hello");
		
	}
}
