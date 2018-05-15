package fr.univavignon.rodeo.imp;
import fr.univavignon.rodeo.api.*;

public class GameStateProvider implements IGameStateProvider{

	private IGameState iGameState;
	
	@Override
	public void save(IGameState gameState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IGameState get(String name) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return iGameState;
	}

}
