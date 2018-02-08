package wireBall;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

/**
 * メインの処理や描画を行うクラス.
 */
public class MyPanel extends Canvas implements Runnable
{
	ObjectPool objectPool;
	KeyInput key;
	MouseInput mouse;
	Image imgBuf;
	Graphics gBuf;
	Random random;
	Title title;
	Score score;

	/** 画面の横幅を表す定数 */
	public static final int WIDTH = 960; //640 960
	/** 画面の高さを表す定数 */
	public static final int HEIGHT = 720; //480 720

	/**
	 * シーン管理変数<p>
	 * 0:タイトル画面 1:ステージの表示画面 2:ゲームのメイン画面
	 */
	public static int scene;
	static final int SCENE_TITLE = 0;
	static final int SCENE_STAGE = 1;
	static final int SCENE_GAMEMAIN = 2;
	static final int SCENE_GAMECLEAR = 3;

	public boolean gameover;
	int counter;

	/**
	 * コンストラクタ
	 */
	MyPanel()
	{
		objectPool = new ObjectPool();

		//キーリスナ実装
		key = new KeyInput();
		addKeyListener(key);
		setFocusable(true);

		// マウス入力を読み取れるようにする.
		mouse = new MouseInput();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);

		random = new Random();
		title = new Title();
		score = new Score();
	}

	/**
	 * 初期化処理<p>
	 * アプリケーションの開始時、またはリスタート時に呼ばれ、<br>
	 * ゲーム内で使われる変数の初期化を行う。
	 */
	public void init()
	{
		//シーンはタイトル画面
		scene = SCENE_TITLE;
		objectPool.active = true;
		gameover = false;
		//スコアの初期化
		Score.initScore();
		objectPool.init();
	}

	/**
	 * 外部からスレッドを初期化する。
	 */
	public void initThread()
	{
		Thread thread = new Thread(this);
		thread.start();
	}

	/**
	 * 描画処理<p>
	 * repaint()の際に呼ばれて、
	 * オフスクリーンバッファから画像をコピーし表示する。
	 * @param g 描画先グラフィックハンドル
	 */
	public void paint(Graphics g)
	{
		//オフスクリーンバッファの内容を自分にコピー
		g.drawImage(imgBuf, 0, 0, this);
	}

	/**
	 * メインループ
	 */
	public void run()
	{
		//オフスクリーンバッファ作成
		imgBuf = createImage(MyPanel.WIDTH, MyPanel.HEIGHT);
		gBuf = imgBuf.getGraphics();

		for(counter = 0; ; counter++)
		{
			//バッファをクリア
			gBuf.setColor(Color.white);
			gBuf.fillRect(0, 0, MyPanel.WIDTH, MyPanel.HEIGHT);

			//シーン遷移用の変数で分岐
			switch (scene)
			{
				//タイトル画面
				case SCENE_TITLE:
					title.drawTitle(gBuf);

					//マウスが押された
					if (mouse.isMousePushed)
					{
						mouse.isMousePushed= false;
						//ステージ画面に行く
						scene = SCENE_STAGE;
					}
					break;

				//ゲームのメイン画面
				case SCENE_GAMEMAIN:
					gameMain();
					break;

				case SCENE_STAGE:
					title.drawStage(gBuf, objectPool.stageNum);

					//マウスが押された
					if (mouse.isMousePushed)
					{
						mouse.isMousePushed= false;
						//メイン画面に行く
						scene = SCENE_GAMEMAIN;
						objectPool.active = true;
					}
					break;

				case SCENE_GAMECLEAR:
					title.drawGameClear(gBuf);

					//マウスが押された
					if (mouse.isMousePushed)
					{
						mouse.isMousePushed= false;
						init();
						objectPool.active = false;
						//タイトル画面に行く
						scene = SCENE_TITLE;
					}
					break;
			}

			//再描画を要求
			repaint();

			try
			{
				Thread.sleep(20);				//ループのウェイト
			}
			catch(InterruptedException e)
			{}
		}
	}

	/**
	 * 画面をいちいちクリアしないようにするため、
	 * updateメソッドをオーバーライドする。
	 * @param g 更新先グラフィックハンドル
	 */
	public void update(Graphics g)
	{
		paint(g);
	}

	/**
	 * ゲーム画面のメイン処理
	 */
	void gameMain()
	{
		if (objectPool.active)
		{
			if (mouse.isMousePushed)
			{
				objectPool.jointLock(mouse.mouseX, mouse.mouseY);
				mouse.isMousePushed = false;
			}
			if (key.keySpace)
			{
				objectPool.wirePull(key.keySpace);
			}

			//衝突の判定
			objectPool.getColision();

			//ゲームオブジェクトの一括描画処理
			objectPool.drawAll(gBuf);
			//スコア描画
			score.drawScore(gBuf);
		}

		//ゲームオーバーか？
		if (Score.myHeart == 0)
		{
			//ゲームオーバー文字を表示
			title.drawGameover(gBuf);
			objectPool.active = false;
			if (mouse.isMousePushed)
			{
				mouse.isMousePushed = false;
				//ゲームを再初期化
				init();
			}
		}
	}
}
