package br.pucpr;

import java.util.LinkedList;

public interface IPiece
{
	public boolean isValid( int x, int y, LinkedList<LinkedList<IPiece>> table );

	public boolean isHit( int x, int y, LinkedList<LinkedList<IPiece>> table );

	public Colors getColor();

	public void setColor( Colors cor );

	public int getX0();

	public void setX0( int x0 );

	public int getY0();

	public void setY0( int y0 );

	public boolean isHit();

}
