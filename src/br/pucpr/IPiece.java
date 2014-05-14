package br.pucpr;

public interface IPiece
{
	public boolean isValid( int x, int y, IPiece[][] table );

	public boolean isHit( int x, int y, IPiece[][] table );

	public Colors getColor();

	public void setColor( Colors cor );

	public int getX0();

	public void setX0( int x0 );

	public int getY0();

	public void setY0( int y0 );

}
