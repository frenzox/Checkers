package br.pucpr;

import java.util.LinkedList;

public class AlfaBetaPruning
{

	public int[] alfaBeta (Checkers game)
	{
		int mov[] = maxVal(game.getTable(),Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
		
		return mov;
		
	}
	
	public int[] maxVal (LinkedList<LinkedList<IPiece>> table, double alpha, double beta)
	{
		LinkedList<LinkedList<IPiece>> nextState = ( LinkedList<LinkedList<IPiece>> ) table.clone();
		double v = Double.NEGATIVE_INFINITY;
	
		for( LinkedList<IPiece> l : nextState )
		{
			for( IPiece p : l )
			{
			}
			
		}
		int max[];
		return max;
	}
	
	public boolean verifyAttack( IPiece p, LinkedList<LinkedList<IPiece>> table )
	{
		if(p.isValid( p.getX0()+2, p.getY0()+2, table ) && p.isHit(p.getX0()+2, p.getY0()+2, table ) )
			return true;
		if(p.isValid( p.getX0()+2, p.getY0()-2, table ) && p.isHit(p.getX0()+2, p.getY0()-2, table ) )
			return true;
		if(p.isValid( p.getX0()-2, p.getY0()+2, table ) && p.isHit(p.getX0()-2, p.getY0()+2, table ) )
			return true;
		if(p.isValid( p.getX0()-2, p.getY0()-2, table ) && p.isHit(p.getX0()+2, p.getY0()+2, table ) )
			return true;
		
		return false;
	}
}
