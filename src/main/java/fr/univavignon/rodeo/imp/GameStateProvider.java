package fr.univavignon.rodeo.imp;
import fr.univavignon.rodeo.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GameStateProvider implements IGameStateProvider{

	private String SAVE_PATH="src/main/resources/";
	@Override
	public void save(IGameState gameState) {
		// TODO Auto-generated method stub
		File file = new File(SAVE_PATH+gameState.getName()+"_SAVE.txt");
		try
		{
			if (file.exists())
			{
				//delete if exists
				file.delete();
			}
			file.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(file,true));
			out.write(gameState.getName());
			out.newLine();
			out.write(gameState.getProgression()+"\n");
			out.close();
		}catch (IOException fe)
		{
			fe.printStackTrace();
		}

	}

	@Override
	public IGameState get(String name) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (name==null) throw new IllegalArgumentException();
		GameState gameState = null;

		try{
			final List<String> lines = Files.readAllLines(Paths.get(SAVE_PATH+name+"_SAVE.txt"));
			gameState=new GameState(lines.get(0),Integer.parseInt(lines.get(1)));
		}catch (IOException e)
		{
			e.printStackTrace();
		}
		return gameState;
	}

}
