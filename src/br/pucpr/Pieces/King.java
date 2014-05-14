package br.pucpr.Pieces;

import br.pucpr.Colors;
import br.pucpr.IPiece;

public class King implements IPiece
{
	private Colors pieceColor;
	private int x, y;
	
	
	@Override
	public boolean isValid( int x, int y, IPiece[][] table )
	{
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isHit( int x, int y, IPiece[][] table )
	{
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Colors getColor()
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setColor( Colors cor )
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getX0()
	{
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setX0( int x0 )
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getY0()
	{
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setY0( int y0 )
	{
		// TODO Auto-generated method stub
		
	}
}
