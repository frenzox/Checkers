package br.pucpr;

import java.util.LinkedList;

public interface IPiece
{
	public boolean isValid( int x, int y, LinkedList<LinkedList<IPiece>> table );

	public boolean isHit( int x, int y, LinkedList<LinkedList<IPiece>> table );

	public Player getPlayer();

	public void setPlayer( Player player );

	public int getX0();

	public void setX0( int x0 );

	public int getY0();

	public void setY0( int y0 );

	public int getTargetx();

	public int getTargety();

	public void setTargetx( int targetx );

	public void setTargety( int targety );

	public boolean isKing();

}
