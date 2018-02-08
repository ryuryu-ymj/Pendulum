package wireBall;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject
{
	double speedX, speedY;
	/**
	 * 半径
	 */
	int radius;
	double forceAngle;
	final int speedMax = 11;

	Player(int x, int y, int radius)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
		speedX = 0;
		speedY = 0;
		forceAngle = 0;
	}

	void move(double cameraX, double cameraY)
	{
		// 重力
		speedY += 0.1;

		// 速度の出過ぎを防止
		if(speedX > speedMax)
		{
			speedX = speedMax;
		}
		if(speedY > speedMax)
		{
			speedY = speedMax;
		}

		x = x + speedX;
		y = y + speedY;

		displayX = x - cameraX + MyPanel.WIDTH / 2;
		displayY = y - cameraY + MyPanel.HEIGHT / 2;

		speedX = slowDown(speedX);
		speedY = slowDown(speedY);
	}

	void draw(Graphics g)
	{
		if (active)
		{
			g.setColor(Color.ORANGE);
			g.drawOval((int)displayX - radius, (int)displayY - radius, radius * 2, radius * 2);
		}
	}

	/**
	 * 振り子運動
	 */
	void pendulum()
	{
		forceAngle = ObjectPool.getAngle(speedX, speedY);
		double f = ObjectPool.getDistance2(speedX, speedY) * Math.sin(forceAngle - Wire.angle);
		speedX = -f * Math.sin(Wire.angle);
		speedY = f * Math.cos(Wire.angle);

		// 張力
		speedX -= Math.cos(Wire.angle) * Wire.fT;
		speedY -= Math.sin(Wire.angle) * Wire.fT;
	}

	/**
	 * x座標の跳ね返り
	 */
	void boundX(double groundX)
	{
		speedX = -speedX;
		// 線の左
		if (x < groundX)
		{
			x = groundX - radius;
		}
		// 線の右
		else
		{
			x = groundX + radius;
		}
	}

	/**
	 * y座標の跳ね返り
	 */
	void boundY(double groundY)
	{
		speedY = -speedY;
		// 線の上
		if (y < groundY)
		{
			y = groundY - radius;
		}
		// 線の下
		else
		{
			y = groundY + radius;
		}
	}

	void reset()
	{
		x = MyPanel.WIDTH / 2; 
		y = MyPanel.HEIGHT / 2;
		speedX = 0;
		speedY = 0;
		forceAngle = 0;
	}

	double slowDown(double a)
	{
		if (a > 0)
		{
			a -= 0.01;
		}
		else if(a < 0)
		{
			a += 0.01;
		}
		return a;
	}
}
