package br.pucpr.Pieces;

import br.pucpr.Colors;
import br.pucpr.IPiece;

public class King implements IPiece
{
	
	private Colors pieceColor;
	private int x0, y0;

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
		return this.pieceColor;
	}

	@Override
	public void setColor( Colors color )
	{
		this.pieceColor = color;

	}

	@Override
	public int getX0()
	{
		return this.x0;
	}

	@Override
	public void setX0( int x0 )
	{
		this.x0 = x0;

	}

	@Override
	public int getY0()
	{
		return this.y0;
	}

	@Override
	public void setY0( int y0 )
	{
		this.y0 = y0;

	}
}
