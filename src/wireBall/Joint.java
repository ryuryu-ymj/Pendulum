package wireBall;

import java.awt.Color;
import java.awt.Graphics;

public class Joint extends GameObject
{
	public static int radius;
	/**
	 * jointがステージ上のどのjointを演じているのか（jointXの番号）
	 */
	int num;
	/**
	 * 0:ノーマル 1:ゴール 2:ハート入り 3:弾うち(3方向) 4:弾うち(狙撃)
	 */
	int type;
	/**
	 * jointLock有効範囲半径　0なら有効範囲は無限(指定しない)
	 */
	int lockRadius;
	/**
	 * playerが一周したかどうか
	 */
	boolean isPlayerLoop;
	/**
	 * 弾うち型jointの向く方向の角度
	 */
	int angle;
	/**
	 * 生存時間（弾を撃つタイミングに使用）
	 */
	int counter = 0;
	Player player;

	Joint(int radius, Player player)
	{
		Joint.radius = radius;
		this.player = player;
		active = false;
	}

	void move(double cameraX, double cameraY)
	{
		counter++;
		// 弾うち　3方向
		if(type == 3 && !isPlayerLoop)
		{
			angle++;
			if(counter % 80 == 0)
			{
				Bullet.FireRound(x, y, angle);
				Bullet.FireRound(x, y, angle + 120 + 10/* 微調整 */);
				Bullet.FireRound(x, y, angle + 240 + 10/* 微調整 */);
			}
		}
		// 弾うち　狙撃
		if(type == 4 && !isPlayerLoop)
		{
			angle = (int) Math.toDegrees(Math.atan2(player.y - y, player.x - x));
			if(counter % 80 == 0)
			{
				Bullet.FireRound(x, y, angle);
			}
		}

		displayX = x - cameraX + MyPanel.WIDTH / 2;
		displayY = y - cameraY + MyPanel.HEIGHT / 2;

		if (displayX < 0 || MyPanel.WIDTH < displayX)
		{
			if (displayY < 0 || MyPanel.HEIGHT < displayY)
			{
				active = false;
				ObjectPool.jointDisplay[num] = false;
			}
		}
	}

	void fireAim(int playerX, int playerY)
	{
	}

	void draw(Graphics g)
	{
		switch (type)
		{
		case 0:
			{
				g.setColor(Color.BLUE);
				g.drawOval((int)displayX - radius, (int)displayY - radius, radius * 2, radius * 2);
				break;
			}
		case 1:
			{
				g.setColor(Color.RED);
				g.drawOval((int)displayX - radius * 6 / 5, (int)displayY - radius * 6 / 5, radius * 6 / 5 * 2, radius * 6 / 5 * 2);
				g.drawOval((int)displayX - radius / 2, (int)displayY - radius / 2, radius, radius);
				break;
			}
		case 2:
			{
				Color c = new Color(0, 200, 0);
				g.setColor(c);
				g.drawOval((int)displayX - radius * 2, (int)displayY - radius * 2, radius * 4, radius * 4);
				if(!isPlayerLoop)
				{
					Heart.drawHeart(g, displayX, displayY);
				}
				break;
			}
		case 3:
			{
				g.setColor(Color.BLUE);
				g.drawOval((int)displayX - 10, (int)displayY - 10, 10 * 2, 10 * 2);
				if(!isPlayerLoop)
				{
					drawGun((int)displayX, (int)displayY, angle, g);
					drawGun((int)displayX, (int)displayY, angle + 120, g);
					drawGun((int)displayX, (int)displayY, angle + 240, g);
				}
				/*g.drawArc((int)displayX - 62, (int)displayY - 47, 57, 57, 0, -60);
				g.drawArc((int)displayX + 5, (int)displayY - 47, 57, 57, 180, 60);
				g.drawArc((int)displayX - 28, (int)displayY + 10, 57, 57, 60, 60);
				g.drawLine((int)displayX - 5, (int)displayY - 19, (int)displayX + 5, (int)displayY - 19);*/
			}
		case 4:
			{
				g.setColor(Color.BLUE);
				g.drawOval((int)displayX - 10, (int)displayY - 10, 10 * 2, 10 * 2);
				if(!isPlayerLoop)
				{
					drawGun((int)displayX, (int)displayY, angle, g);
				}
			}
		}

		//jointLock有効範囲の描写
		if(lockRadius != 0)
		{
			switch (type)
			{
			case 0:
				Color c = new Color(102, 217, 255);
				g.setColor(c);
				break;
			case 1:
				g.setColor(Color.PINK);
				break;
			}
			g.drawOval((int)displayX - lockRadius, (int)displayY - lockRadius, lockRadius * 2, lockRadius * 2);
		}
	}

	void activate(int x, int y, int type, int lockRadius, boolean isPlayerLoop)
	{
		this.x = x;
		this.y = y;
		this.type = type;
		this.lockRadius = lockRadius;
		this.isPlayerLoop = isPlayerLoop;
		active = true;
	}

	/**
	 * 銃口を描画
	 * @param x 中心座標
	 * @param y 中心座標
	 * @param angle 銃口の向く角度（３時の方向から反時計回り）
	 */
	void drawGun(int x, int y, int angle, Graphics g)
	{
		int[] xPoints = new int[4];
		int[] yPoints = new int[4];
		xPoints[0] = rotation(x - 5, y - 3, x, y, angle, true);
		yPoints[0] = rotation(x - 5, y - 3, x, y, angle, false);
		xPoints[1] = rotation(x - 5, y - 23, x, y, angle, true);
		yPoints[1] = rotation(x - 5, y - 23, x, y, angle, false);
		xPoints[2] = rotation(x + 5, y - 23, x, y, angle, true);
		yPoints[2] = rotation(x + 5, y - 23, x, y, angle, false);
		xPoints[3] = rotation(x + 5, y - 3, x, y, angle, true);
		yPoints[3] = rotation(x + 5, y - 3, x, y, angle, false);
		g.drawPolygon(xPoints, yPoints, 4);
	}

	/**
	 * 点(x, y)を(ix, iy)を中心としてangle度反時計回りに回転させた点の座標を返す
	 * @param x 回転する元の座標
	 * @param y 回転する元の座標
	 * @param ix 中心座標
	 * @param iy 中心座標
	 * @param rAngle 回転角度（３時の方向から反時計回り）
	 * @param xOrY 返す値がx座標ならtrue, y座標ならfalse
	 */
	static int rotation(int x, int y, int ix, int iy, int iAngle, boolean xOrY)
	{
		/** 辺(x, y)(ix, iy)の長さ */
		double side = Math.sqrt((x - ix) * (x - ix) + (y - iy) * (y - iy));
		double angle = Math.atan2(y - iy, x - ix);
		//System.out.println(angle);
		double rAngle = Math.toRadians(iAngle + 90);
		if(xOrY)
		{
			return (int) (ix + Math.cos(angle + rAngle) * side);
		}
		else
		{
			return (int) (iy + Math.sin(angle + rAngle) * side);
		}
	}
}
