package fr.univavignon.rodeo.imp;

import fr.univavignon.rodeo.api.*;

/**
 * @author uapv1304003
 *
 */
public class Animal implements IAnimal{

	
	private int xp;
	private String name;
	private boolean isSecret; 
	private boolean isEndangered; 
	private boolean isBoss; 
	
	
	public Animal(){}
	
	/**
	 * @param xp
	 * @param name
	 * @param isSecret
	 * @param isEndangered
	 * @param isBoss
	 */
	public Animal(int xp,String name,boolean isSecret,boolean isEndangered,boolean isBoss)
	{
		this.xp=xp;
		this.name=name;
		this.isSecret=isSecret;
		this.isEndangered=isEndangered;
		this.isBoss=isBoss;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public int getXP() {
		// TODO Auto-generated method stub
		return this.xp;
	}

	@Override
	public boolean isSecret() {
		// TODO Auto-generated method stub
		return this.isSecret;
	}

	@Override
	public boolean isEndangered() {
		// TODO Auto-generated method stub
		return this.isEndangered;
	}

	@Override
	public boolean isBoss() {
		// TODO Auto-generated method stub
		return this.isBoss;
	}

}
