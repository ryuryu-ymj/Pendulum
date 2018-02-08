package wireBall;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * スコアと獲得コイン数の管理および表示
 */
public class Score
{
	static int myCoin;
	static int myHeart;
	Font scoreFont;
	/**
	 * 獲得コイン数の上限 <p>
	 * この数になるとハートが一個増える
	 */
	static int coinMax;

	Score()
	{
		scoreFont = new Font("sansserif", Font.BOLD, 25);
		coinMax = 10;
		myCoin = 0;
		myHeart = 3;
	}

	public void drawScore(Graphics g)
	{
		Coin.drawCoin(g, 20, 22, 12);
		Heart.drawHeart(g, 90, 20);
		g.setColor(Color.black);
		g.setFont(scoreFont);
		g.drawString("×"+myCoin, 40, 30);
		g.drawString("×"+myHeart, 110, 30);
	}

	public static void addCoin(int gain)
	{
		myCoin += gain;
		if (myCoin >= coinMax)
		{
			myCoin = myCoin - coinMax;
			myHeart += 1;
		}
	}

	public static void addHeart(int gain)
	{
		myHeart += gain;
	}

	public static void initScore()
	{
		coinMax = 10;
		myCoin = 0;
		myHeart = 3;
	}
}