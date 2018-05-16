package fr.univavignon.rodeo.imp;
import fr.univavignon.rodeo.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GameStateProvider implements IGameStateProvider{

	private String SAVE_PATH="src/main/resources/_SAVE.txt";
	@Override
	public void save(IGameState gameState) {
		// TODO Auto-generated method stub
		File file = new File(gameState.getName()+SAVE_PATH);
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
		GameState gameState=null;
		try{
			final List<String> lines = Files.readAllLines(Paths.get(name+SAVE_PATH));
			gameState=new GameState(lines.get(0),Integer.parseInt(lines.get(1)));
		}catch (IOException e)
		{
			e.printStackTrace();
		}
		return gameState;
	}

}
