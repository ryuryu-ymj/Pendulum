package wireBall;

import java.awt.*;

/**
*タイトルクラス<p>
*タイトル画面描画<p>
*ゲームオーバー画面表示
*/
public class Title
{
	Font titleFont;
	Font infoFont;

	Title()
	{
		titleFont = new Font("sansserif", Font.BOLD, 60);
		infoFont = new Font("sansserif", Font.BOLD, 30);
	}

	public void drawTitle(Graphics g)
	{
		g.setColor(Color.black);
		g.setFont(titleFont);
		g.drawString("PENDULUM",300,250);
	}

	public void drawGameover(Graphics g)
	{
		g.setColor(Color.black);
		g.setFont(infoFont);
		g.drawString("GAMEOVER",390,310);
	}

	public void drawStage(Graphics g, int stageNum)
	{
		g.setColor(Color.BLACK);
		g.setFont(infoFont);
		g.drawString("STAGE " + stageNum, 400, 320);
		Heart.drawHeart(g, 440, 340);
		g.setColor(Color.BLACK);
		g.drawString("×" + Score.myHeart, 455, 352);
	}

	/**
	 * ステージを全クリしたら表示
	 */
	public void drawGameClear(Graphics g)
	{
		g.setColor(Color.black);
		g.setFont(titleFont);
		g.drawString("GAMECLEAR",280,300);
		g.setFont(infoFont);
		g.drawString("Congratulations!",350,350);
	}
}
