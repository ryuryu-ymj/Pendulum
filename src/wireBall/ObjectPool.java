package wireBall;

import java.awt.Graphics;

public class ObjectPool
{
	boolean active;

	Player player;
	Wire wire;
	Camera camera;
	static Joint joint[];
	static Ground ground[];
	static Coin coin[];
	static Heart heart[];
	static Bullet bullet[];
	Stage stage;

	/** 現在のステージ番号(1から) */
	int stageNum;

	/**
	 * そのジョイントが表示されたかどうか
	 */
	static boolean [] jointDisplay = new boolean [Stage.JOINT_MAX];
	/**
	 * そのジョイントがプレイヤーに一周されているかどうか
	 */
	static boolean[] jointLoop = new boolean[Stage.JOINT_MAX];

	/**
	 * その地面が表示されたかどうか
	 */
	static boolean [] groundDisplay = new boolean [Stage.GROUND_MAX];

	/**
	 * そのコインが取られていないかどうか
	 */
	static boolean [] coinActive = new boolean [Stage.COIN_MAX];
	/**
	 * そのコインが表示されたかどうか
	 */
	static boolean [] coinDisplay = new boolean [Stage.COIN_MAX];

	/**
	 * そのハートが取られていないかどうか
	 */
	static boolean [] heartActive = new boolean [Stage.HEART_MAX];
	/**
	 * そのハートが表示されたかどうか
	 */
	static boolean [] heartDisplay = new boolean [Stage.HEART_MAX];

	/**
	 * ワイヤーがつながっているジョイントの数字　何もつながっていないと－1
	 */
	int jointLockedNum;

	/** 画面上におけるjointの数の最大値 */
	public static final int JOINT_MAX = 10;
	/** 画面上におけるgroundの数の最大値 */
	public static final int GROUND_MAX = 20;
	/** 画面上におけるcoinの数の最大値 */
	public static final int COIN_MAX = 20;
	/** 画面上におけるheartの数の最大値 */
	public static final int HEART_MAX = 5;
	/** 画面上におけるbulletの数の最大値 */
	public static final int BULLET_MAX = 20;

	public ObjectPool()
	{
		active = true;

		player = new Player(MyPanel.WIDTH / 2, MyPanel.HEIGHT / 2, 15);
		player.active = true;
		wire = new Wire();
		camera = new Camera();
		stage = new Stage();

		stageNum = 1;

		joint = new Joint[JOINT_MAX];
		for (int i = 0; i < joint.length; i++)
		{
			joint[i] = new Joint(10, player);
		}

		ground = new Ground[GROUND_MAX];
		for (int i = 0; i < ground.length; i++)
		{
			ground[i] = new Ground();
		}

		coin = new Coin[COIN_MAX];
		for (int i = 0; i < coin.length; i++)
		{
			coin[i] = new Coin();
		}

		heart = new Heart[HEART_MAX];
		for (int i = 0; i < heart.length; i++)
		{
			heart[i] = new Heart();
		}

		bullet = new Bullet[BULLET_MAX];
		for(int i = 0; i < bullet.length; i++)
		{
			bullet[i] = new Bullet();
		}

		for (int i = 0; i < jointDisplay.length; i++)
		{
			jointDisplay[i] = false;
		}

		for (int i = 0; i < coinActive.length; i++)
		{
			coinActive[i] = true;
		}

		for (int i = 0; i < heartActive.length; i++)
		{
			heartActive[i] = true;
		}
	}

	public void drawAll(Graphics g)
	{
		cameraMove();
		playerMove();
		jointMove();
		wireMove();
		groundMove();
		coinMove();
		heartMove();
		player.draw(g);
		wire.draw(g);
		doGameObjects(g, joint);
		doGameObjects(g, ground);
		doGameObjects(g, coin);
		doGameObjects(g, heart);
		doGameObjects(g, bullet);
	}

	public void doGameObjects(Graphics g, GameObject[] objary)
	{
		for (int i = 0; i < objary.length; i++)
		{
			if (objary[i].active == true)
			{
				objary[i].move(camera.x, camera.y);
				objary[i].draw(g);
			}
		}
	}

	/** 初期化 */
	public void init()
	{
		camera.x = MyPanel.WIDTH / 2;
		camera.y = MyPanel.HEIGHT / 2;
		wire.active = false;
		player.reset();
		for (int i = 0; i < coinActive.length; i++)
		{
			coinActive[i] = true;
		}
		for (int i = 0; i < heartActive.length; i++)
		{
			heartActive[i] = true;
		}
		for (int i = 0; i < coinDisplay.length; i++)
		{
			coinDisplay[i] = false;
		}
		for (int i = 0; i < heartDisplay.length; i++)
		{
			heartDisplay[i] = false;
		}
		coin = new Coin[COIN_MAX];
		for (int i = 0; i < coin.length; i++)
		{
			coin[i] = new Coin();
		}
		heart = new Heart[HEART_MAX];
		for (int i = 0; i < heart.length; i++)
		{
			heart[i] = new Heart();
		}
		for(int i = 0; i < jointLoop.length; i++)
		{
			jointLoop[i] = false;
		}
		for (int i = 0; i < joint.length; i++)
		{
			joint[i].isPlayerLoop = false;
		}
		for (int i = 0; i < jointDisplay.length; i++)
		{
			jointDisplay[i] = false;
		}
		for (int i = 0; i < joint.length; i++)
		{
			joint[i].active = false;
		}
		for (int i = 0; i < groundDisplay.length; i++)
		{
			groundDisplay[i] = false;
		}
		for (int i = 0; i < ground.length; i++)
		{
			ground[i].active = false;
		}
		for (int i = 0; i < bullet.length; i++)
		{
			bullet[i].active = false;
		}
		for (int i = 0; i< joint.length; i++)
		{
			joint[i].isPlayerLoop = false;
		}
		for (int i = 0; i < wire.isPlayerPass.length; i++)
		{
			wire.isPlayerPass[i] = false;
		}
	}

	public static int newJoint(int x, int y, int type, int lockRadius, boolean playerLoop, int jointNum)
	{
		for (int i = 0; i < JOINT_MAX; i++)
		{
			if (!joint[i].active)
			{
				joint[i].activate(x, y, type, lockRadius, playerLoop);
				joint[i].num = jointNum;
				return i;
			}
		}
		return -1;
	}

	public void jointMove()
	{
		for (int i = 0; i < stage.jointX(stageNum).length; i++)
		{
			if (!jointDisplay[i])
			{
				if (getDisplay(stage.jointX(stageNum)[i], stage.jointY(stageNum)[i], camera.x, camera.y))
				{
					if (newJoint(stage.jointX(stageNum)[i], stage.jointY(stageNum)[i], stage.jointT(stageNum)[i], stage.jointR(stageNum)[i], jointLoop[i], i) != -1)
					{
						jointDisplay[i] = true;
					}
				}
			}
		}
	}

	public void jointLock(int mouseX, int mouseY)
	{
		f :
		for (int i = 0; i < JOINT_MAX; i++)
		{
			if (joint[i].active && player.active)
			{
				if (mouseX < joint[i].displayX + Joint.radius * 5 && mouseX > joint[i].displayX - Joint.radius * 5)
				{
					if (mouseY < joint[i].displayY + Joint.radius * 5 && mouseY > joint[i].displayY - Joint.radius * 5)
					{
						if(joint[i].lockRadius == 0 || getDistance(player, joint[i]) < joint[i].lockRadius)
						{
							jointLockedNum = i;
							wire.active = true;
							camera.active = true;
							if(joint[i].type == 1)
							{
								playerGoal();
							}
						}
						break f;
					}
				}
			}
			if (i == JOINT_MAX - 1)
			{
				jointLockedNum = -1;
				wire.active = false;
				for(int g = 0; g < wire.isPlayerPass.length; g++)
				{
					wire.isPlayerPass[g] = false;
				}
				//camera.active = false;
			}
		}
	}

	public void wirePull(boolean keySpace)
	{
		Wire.fT = 4;
	}

	public void wireMove()
	{
		if (wire.active)
		{
			wire.length = getDistance(joint[jointLockedNum], player);
			Wire.angle = getAngle(player.x - joint[jointLockedNum].x, player.y - joint[jointLockedNum].y);
			// 微調整
			Wire.fT = 0.2;
		}
		if (jointLockedNum != -1)
		{
			wire.move(joint[jointLockedNum].displayX, joint[jointLockedNum].displayY, player.displayX, player.displayY);
			if(!jointLoop[joint[jointLockedNum].num])
			{
				//playerが一周したとき
				if(wire.playerLoop())
				{
					joint[jointLockedNum].isPlayerLoop = true;
					jointLoop[joint[jointLockedNum].num] = true;
					for(int g = 0; g < wire.isPlayerPass.length; g++)
					{
						wire.isPlayerPass[g] = false;
					}
					switch(joint[jointLockedNum].type)
					{
					case 2:
						Score.addHeart(1);
					}
				}
			}
		}
		//wireがjointの幅まで短くなったらjointLock解除
		/*if(wire.length < Joint.radius)
		{
			jointLockedNum = -1;
			wire.active = false;
			for(int g = 0; g < wire.isPlayerPass.length; g++)
			{
				wire.isPlayerPass[g] = false;
			}
			System.out.println(0);
		}*/
	}

	public void playerMove()
	{
		// 地面との衝突判定
		for (int i = 0; i < GROUND_MAX; i++)
		{
			if (ground[i].active)
			{
				// 縦
				if (ground[i].derection == 0)
				{
					if (player.x - player.radius < ground[i].x && player.x + player.radius > ground[i].x)
					{
						if (ground[i].y1 < player.displayY && ground[i].y2 > player.displayY)
						{
							if(stage.groundT(stageNum)[ground[i].num] == 0)
							{
								player.boundX(ground[i].x);
							}
							else
							{
								playerRetry(); // トゲトゲで死亡
							}
						}
					}
				}
				// 横
				if (ground[i].derection == 1)
				{
					if (player.y - player.radius < ground[i].y && player.y + player.radius > ground[i].y)
					{
						if (ground[i].x1 < player.displayX && ground[i].x2 > player.displayX)
						{
							if(stage.groundT(stageNum)[ground[i].num] == 0)
							{
								player.boundY(ground[i].y);
							}
							else
							{
								playerRetry(); // トゲトゲで死亡
							}
						}
					}
				}
			}
		}

		if (wire.active)
		{
			player.pendulum();
		}


		player.move(camera.x, camera.y);
	}

	/**
	 * playerが死んだときなどにステージの最初からリトライ
	 */
	public void playerRetry()
	{
		wire.active = false;
		player.reset();
		Score.addHeart(-1);
		active = false;
		if (Score.myHeart != 0)
		{
			MyPanel.scene = MyPanel.SCENE_STAGE;
		}
		camera.x = MyPanel.WIDTH / 2;
		camera.y = MyPanel.HEIGHT / 2;
		init();
	}

	/** playerがゴールしたとき */
	private void playerGoal()
	{
		// GameClear
		if (stageNum == Stage.STAGE_MAX)
		{
			stageNum = 1;
			MyPanel.scene = MyPanel.SCENE_GAMECLEAR;
		}
		else
		{
			stageNum++;
			active = false;
			MyPanel.scene = MyPanel.SCENE_STAGE;
			init();
		}
	}

	public static int newGround(int x, int y, int length, int derection, int type, int groundNum)
	{
		for (int i = 0; i < GROUND_MAX; i++)
		{
			if (ground[i].active == false)
			{
				ground[i].activate(x, y, length, derection, type);
				ground[i].num = groundNum;
				return i;
			}
		}
		return -1;
	} 

	public void groundMove()
	{
		for (int i = 0; i < stage.groundX(stageNum).length; i++)
		{
			if (!groundDisplay[i])
			{
				if (stage.groundD(stageNum)[i] == 0)
				{
					if (stage.groundX(stageNum)[i] > camera.x - MyPanel.WIDTH / 2 && stage.groundX(stageNum)[i] < camera.x + MyPanel.WIDTH / 2)
					{
						if (stage.groundY(stageNum)[i] > camera.y - MyPanel.HEIGHT / 2 - stage.groundL(stageNum)[i] / 2 && stage.groundY(stageNum)[i] < camera.y + MyPanel.HEIGHT / 2 + stage.groundL(stageNum)[i] / 2)
						{
							if (newGround(stage.groundX(stageNum)[i], stage.groundY(stageNum)[i], stage.groundL(stageNum)[i], 0, stage.groundT(stageNum)[i], i) != -1)
							{
								groundDisplay[i] = true;
							}
						}
					}
				}
				else
				{
					if (stage.groundY(stageNum)[i] > camera.y - MyPanel.HEIGHT / 2 && stage.groundY(stageNum)[i] < camera.y + MyPanel.HEIGHT / 2)
					{
						if (stage.groundX(stageNum)[i] > camera.x - MyPanel.WIDTH / 2 - stage.groundL(stageNum)[i] / 2 && stage.groundX(stageNum)[i] < camera.x + MyPanel.WIDTH / 2 + stage.groundL(stageNum)[i] / 2)
						{
							if (newGround(stage.groundX(stageNum)[i], stage.groundY(stageNum)[i], stage.groundL(stageNum)[i], 1, stage.groundT(stageNum)[i], i) != -1)
							{
								groundDisplay[i] = true;
							}
						}
					}
				}
			}
			//System.out.println(i + " " + groundDisplay[i]);
		}
	}

	public static void groundDisapear(int num)
	{
		groundDisplay[num] = false;
	}

	public static int newCoin(int x, int y, int coinNum)
	{
		for (int i = 0; i < COIN_MAX; i++)
		{
			if (coin[i].active == false)
			{
				coin[i].activate(x, y);
				coin[i].num = coinNum;
				return i;
			}
		}
		return -1;
	}

	public void coinMove()
	{
		for (int i = 0; i < stage.coinX(stageNum).length; i++)
		{
			if (coinActive[i] && !coinDisplay[i])
			{
				if (getDisplay(stage.coinX(stageNum)[i], stage.coinY(stageNum)[i], camera.x, camera.y))
				{
					newCoin(stage.coinX(stageNum)[i], stage.coinY(stageNum)[i], i);
					coinDisplay[i] = true;
				}
			}
		}
	}

	public static int newHeart(int x, int y, int heartNum)
	{
		for (int i = 0; i < HEART_MAX; i++)
		{
			if (heart[i].active == false)
			{
				heart[i].activate(x, y);
				heart[i].num = heartNum;
				return i;
			}
		}
		return -1;
	}

	public void heartMove()
	{
		for (int i = 0; i < stage.heartX(stageNum).length; i++)
		{
			if (heartActive[i] && !heartDisplay[i])
			{
				if (getDisplay(stage.heartX(stageNum)[i], stage.heartY(stageNum)[i], camera.x, camera.y))
				{
					newHeart(stage.heartX(stageNum)[i], stage.heartY(stageNum)[i], i);
					heartDisplay[i] = true;
				}
			}
		}
	}

	/**
	 * 弾の生成・初期化（実際は配列のインスタンスを使い回す）
	 * @param ix 生成先x座標
	 * @param iy 生成先y座標
	 * @param idirection 動かす方向
	 * @param ispeed 動かす速度
	 * @return 弾のID（空きが無ければ-1）
     */
	public static int newBullet(double ix, double iy, double idirection, double ispeed)
	{
		for (int i = 0; i < BULLET_MAX; i++)
		{
			if ((bullet[i].active) == false)
			{
				bullet[i].activate(ix, iy, idirection, ispeed);
				return i;
			}
		}
		return -1;		//見つからなかった
	}

	public void cameraMove()
	{
		camera.move(player.x, player.y);
	}

	/**
	 * 衝突判定
	 */
	public void getColision()
	{
		// プレイヤーとコインの衝突
		for (int i = 0; i < COIN_MAX; i++)
		{
			if (coin[i].active)
			{
				if (getDistance(player, coin[i]) < player.radius + Coin.radius)
				{
					coin[i].active = false;
					coinActive[coin[i].num] = false;
					Score.addCoin(1);
					//System.out.println("a");
				}
			}
		}

		// プレイヤーとハートの衝突
		for (int i = 0; i < HEART_MAX; i++)
		{
			if (heart[i].active)
			{
				if (getDistance(player, heart[i]) < player.radius + Heart.radius)
				{
					heart[i].active = false;
					heartActive[heart[i].num] = false;
					Score.addHeart(1);
				}
			}
		}

		// プレイヤーと弾の衝突
		for (int i = 0; i < BULLET_MAX; i++)
		{
			if (bullet[i].active)
			{
				if (getDistance(player, bullet[i]) < player.radius + Bullet.radius)
				{
					bullet[i].active = false;
					playerRetry();
				}
			}
		}
	}

	/**
	 * ２点間の距離を返す
	 * @param ga ゲームオブジェクト
	 * @param gb 比較先ゲームオブジェクト
	 * @return 距離
	 */
	public double getDistance(GameObject ga, GameObject gb)
	{
		//三平方の定理
		double Xdiff = Math.abs(ga.x - gb.x);
		double Ydiff = Math.abs(ga.y - gb.y);
		return Math.sqrt(Math.pow(Xdiff,2) + Math.pow(Ydiff,2));
	}

	public static double getDistance2(double x, double y)
	{
		//三平方の定理
		double Xdiff = Math.abs(x);
		double Ydiff = Math.abs(y);
		return Math.sqrt(Math.pow(Xdiff,2) + Math.pow(Ydiff,2));
	}

	/**
	 * ２点間を結ぶ直線の角度を返す
	 * @param x
	 * @param y
	 * @return X軸から下への角度（ラジアン）
	 */
	public static double getAngle(double x, double y)
	{
		return Math.atan2(y, x);
	}

	/**
	 * オブジェクトが画面上に存在するかどうかを返す
	 * @param x
	 * @param y
	 * @param cameraX 画面の中心座標
	 * @param cameraY 画面の中心座標
	 * @return 	(x, y)が画面上に存在するかどうか
	 */
	public static boolean getDisplay(double x, double y, double cameraX, double cameraY)
	{
		if (x > cameraX - MyPanel.WIDTH / 2 && x < cameraX + MyPanel.WIDTH / 2)
		{
			if (y > cameraY - MyPanel.HEIGHT / 2 && y < cameraY + MyPanel.HEIGHT / 2)
			{
				return true;
			}
		}
		return false;
	}
}
