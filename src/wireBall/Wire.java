package wireBall;

import java.awt.Color;
import java.awt.Graphics;

public class Wire extends GameObject
{
	double x2, y2;
	double displayX2, displayY2;
	double length;
	static double angle;
	/**
	 * 張力
	 */
	static double fT;
	/**
	 * playerが1周回ったかどうかを判定するためのフラグ<p>
	 * playerが(配列番号×60)度の角度を通ったらtrue
	 */
	boolean[] isPlayerPass = new boolean[6];

	Wire()
	{
		active = false;
	}

	void move(double cameraX, double cameraY)
	{
	}

	void move(double x, double y, double x2, double y2)
	{
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
		//System.out.println(angle);
	}

	void draw(Graphics g)
	{
		if (active)
		{
			Color c = new Color(0, 240, 240);
			g.setColor(c);
			g.drawLine((int)x, (int)y, (int)x2, (int)y2);
		}
	}

	/**
	 * playerが一周したかの判定
	 */
	boolean playerLoop()
	{
		// playerがその角度を通ったかどうかを判定
		for(int i = 0; i < isPlayerPass.length; i++)
		{
			if(Wire.angle > (i - 3) * Math.PI / 3 && Wire.angle < (i - 2) * Math.PI / 3)
			{
				isPlayerPass[i] = true;
			}
			//System.out.println(i + " " + isPlayerPass[i]);
		}

		// playerが一周したかの判定
		a:
		for(int i = 0; i < isPlayerPass.length; i++)
		{
			if(isPlayerPass[i] == false)
			{
				break a;
			}
			if(i == isPlayerPass.length - 1)
			{
				//System.out.println("a");
				return true;
			}
		}
		return false;
	}
}
