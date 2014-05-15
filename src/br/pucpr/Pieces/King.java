
package br.pucpr.Pieces;

import java.util.LinkedList;

import br.pucpr.Colors;
import br.pucpr.IPiece;

public class King implements IPiece
{

	private Colors pieceColor;
	private int x0, y0;
	private IPiece target;
	private boolean hitOnce = false;

	@Override
	public boolean isValid( int x, int y, LinkedList<LinkedList<IPiece>> table )
	{
		if ( ( x > 7 ) || ( x < 0 ) )
			return false;

		if ( ( y > 7 ) || ( y < 0 ) )
			return false;
		
		if((x == x0) || (y == y0))
			return false;

		if (table.get(x).get(y) != null)
			return false;
		
		return true;
	}

	@Override
	public boolean isHit( int x, int y, LinkedList<LinkedList<IPiece>> table )
	{
		
		int count = 0;
		if(x>x0)
		{
			if(y>y0){
				for(int i=x0+1,j=y0+1;i<x && j<y; i++,j++)
					if(table.get(i).get(j)!=null){
						target.setX0(i);
						target.setY0(j);
						count++;
					}
				if(count > 1)
					return false;
			}
			else {
				for(int i=x0+1,j=y-1;i<x && j> y0; i++,j--)
					if(table.get(i).get(j)!=null){
						target.setX0(i);
						target.setY0(j);
						count++;
					}
				if (count > 1)
					return false;
			}
		}
		if(x<x0)
		{
			
			if(y>y0){
				for(int i=x0-1,j=y0+1;i>x && j<y; i--,j++)
					if(table.get(i).get(j)!=null){
						target.setX0(i);
						target.setY0(j);
						count++;
					}
				if(count >1)
					return false;
			}
			else {
				for(int i=x0-1,j=y-1;i>x && j> y0; i--,j--)
					if(table.get(i).get(j)!=null){
						target.setX0(i);
						target.setY0(j);
						count++;
					}
				if (count > 1)
					return false;
			}
		}
		return true;
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

	public IPiece getTarget() {
		return target;
	}

	public void setTarget(IPiece target) {
		this.target = target;
	}
	
	public void setHitOnce(boolean hitOnce) {
		this.hitOnce = hitOnce;
	}

	@Override
	public boolean isHitOnce() {
		
		return hitOnce;
	}
	
}


//package br.pucpr.Pieces;
//
//import java.util.LinkedList;
//
//import br.pucpr.Colors;
//import br.pucpr.IPiece;
//
//public class King implements IPiece
//{
//	
//	private Colors pieceColor;
//	private int x0, y0;
//
//	@Override
//	public boolean isValid( int x, int y, LinkedList<LinkedList<IPiece>> table )
//	{
//		if ( ( x > 7 ) || ( x < 0 ) )
//			return false;
//
//		if ( ( y > 7 ) || ( y < 0 ) )
//			return false;
//
//		if ( x > x0 )
//		{
//
//			if ( y > y0 )
//			{
//				for ( int i = x0 + 1, j = y0 + 1; i < x && j < y; i++, j++ )
//					if ( table[i][j] != null )
//						return false;
//			} else
//			{
//				for ( int i = x0 + 1, j = y - 1; i < x && j > y0; i++, j-- )
//					if ( table[i][j] != null )
//						return false;
//			}
//		}
//
//		if ( x < x0 )
//		{
//
//			if ( y > y0 )
//			{
//				for ( int i = x0 - 1, j = y0 + 1; i > x && j < y; i--, j++ )
//					if ( table[i][j] != null )
//						return false;
//			} else
//			{
//				for ( int i = x0 - 1, j = y - 1; i > x && j > y0; i--, j-- )
//					if ( table[i][j] != null )
//						return false;
//			}
//		}
//		return true;
//	}
//
//	@Override
//	public boolean isHit( int x, int y, LinkedList<LinkedList<IPiece>> table )
//	{
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public Colors getColor()
//	{
//		return this.pieceColor;
//	}
//
//	@Override
//	public void setColor( Colors color )
//	{
//		this.pieceColor = color;
//
//	}
//
//	@Override
//	public int getX0()
//	{
//		return this.x0;
//	}
//
//	@Override
//	public void setX0( int x0 )
//	{
//		this.x0 = x0;
//
//	}
//
//	@Override
//	public int getY0()
//	{
//		return this.y0;
//	}
//
//	@Override
//	public void setY0( int y0 )
//	{
//		this.y0 = y0;
//
//	}
//
//	@Override
//	public boolean isHit() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//}

