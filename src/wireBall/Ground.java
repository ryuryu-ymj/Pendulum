package wireBall;

import java.awt.Color;
import java.awt.Graphics;

public class Ground extends GameObject
{
	/**
	 * 線の長さ
	 */
	int length;
	/**
	 * 0:縦　1:横
	 */
	int derection;
	/**
	 * 表示座標
	 */
	int x1, y1, x2, y2;
	/**
	 * groundがステージ上のどの地面を演じているのか（groundXの番号）
	 */
	int num;
	/**
	 * 0:ノーマル 1:トゲトゲ
	 */
	int type;
	/**
	 * トゲトゲの幅
	 */
	int side = 20;

	/**
	 * @param x 　線の中心のx座標
	 * @param y 　線の中心のy座標
	 * @param derection 　0:縦　1:横
	 */
	Ground ()
	{
		active = false;
	}

	void move(double cameraX, double cameraY)
	{
		displayX = x - cameraX + MyPanel.WIDTH / 2;
		displayY = y - cameraY + MyPanel.HEIGHT / 2;

		f:
		if (derection == 0)
		{
			if (displayX > 0 && displayX < MyPanel.WIDTH) 
			{
				if (displayY >  - length / 2 && displayY < MyPanel.HEIGHT + length / 2)
				{
					break f;
				}
			}
			active = false;
			ObjectPool.groundDisapear(num);
		}
		g:
		if (derection == 1)
		{
			if (displayY > 0 && displayY < MyPanel.HEIGHT)
			{
				if (displayX >  - length / 2 && displayX < MyPanel.WIDTH + length / 2)
				{
					break g;
				}
			}
			active = false;
			ObjectPool.groundDisapear(num);
		}
		//System.out.println(num);
	}

	void draw(Graphics g)
	{
		if (active)
		{
			x1 = (int)displayX - derection * length / 2;
			x2 = (int)displayX + derection * length / 2;
			y1 = (int)displayY - (1 - derection) * length / 2;
			y2 = (int)displayY + (1 - derection) * length / 2;

			switch (type)
			{
			case 0:
				{
					g.setColor(Color.GREEN);
					g.drawLine(x1, y1, x2, y2);
					break;
				}
			case 1:
				{
					// トゲトゲ
					g.setColor(Color.GRAY);
					int [] xPoints = new int [4];
					int [] yPoints = new int [4];
					xPoints [0] = x1;
					yPoints [0] = y1;
					for (int i = 0; i < length / side / 2; i++)
					{
						xPoints [1] = xPoints [0] + side - side / 2 * derection;
						yPoints [1] = yPoints [0] + side / 2 - side / 2 * 3 * derection;
						xPoints [2] = xPoints [0] - side + side / 2 * 5 * derection;
						yPoints [2] = yPoints [0] + side / 2 * 3 - side / 2 * derection;
						xPoints [3] = xPoints [0] + side * 2 * derection;
						yPoints [3] = yPoints [0] + side * 2 * (1 - derection);
						g.drawPolyline(xPoints, yPoints, 4);
						xPoints [0] = xPoints [0] + side * 2 * derection;
						yPoints [0] = yPoints [0] + side * 2 * (1 - derection);
					}
					break;
				}
			}
		}
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param length
	 * @param derection 0:縦　1:横
	 * @param type 0:ノーマル 1:トゲトゲ
	 */
	void activate(int x, int y, int length, int derection, int type)
	{
		this.x = x;
		this.y = y;
		this.length = length;
		this.derection = derection;
		this.type = type;
		active = true;
	}
}
