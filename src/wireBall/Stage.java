package wireBall;

/**
 * ステージ情報(地面の位置だとか)の保持
 */
public class Stage
{
	/** stage数の最大値 */
	public static final int STAGE_MAX = 4;

	/** 1ステージにおけるgroundの数の最大値 */
	public static final int GROUND_MAX = 25;

	/** 1ステージにおけるjointの数の最大値 */
	public static final int JOINT_MAX = 15;

	/** 1ステージにおけるcoinの数の最大値 */
	public static final int COIN_MAX = 25;

	/** 1ステージにおけるheartの数の最大値 */
	public static final int HEART_MAX = 5;
	int [] groundX1 = {100, 700, 300, 500, 900, 1100, 1300, 1500, 1700, 1800, 1830, 1900, 1760, 1830, 1900, 1900, 2500, 2400, 2900, 3100, 3300, 2900};
	int [] groundY1 = {400, 600, 200, 0, -200, 200, 400, 600, 450, 300, 360, 330, 430, 500, 550, -200, 100, 600, 400, 200, 0, -200};
	int [] groundL1 = {400, 1200, 400, 400, 800, 400, 400, 400, 300, 200, 140, 60, 140, 140, 100, 1200, 600, 1000, 400, 400, 400, 800};
	int [] groundD1 = {0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1};
	int [] groundT1 = {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0};

	int [] groundX2 = {100, 700, 300, 500, 900, 1700, 1900, 2100, 2260, 2340, 2500, 2180, 2420, 2300, 2700, 2700, 3100, 3300, 3100, 2900};
	int [] groundY2 = {300, 100, 500, 700, 900, 900, 100, 800, 800, 800, 800, 700, 700, 900, 100, 900, 100, 300, 500, 700};
	int [] groundL2 = {400, 1200, 400, 400, 800, 800, 1200, 200, 200, 200, 200, 160, 160, 80, 400, 400, 400, 400, 400, 400};
	int [] groundD2 = {0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0};
	int [] groundT2 = {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0};

	int [] groundX3 = {100, 500, 300, 900, 900, 500, 500, 1100, 1100, 1300, 1700, 1900, 1900, 2500, 2300, 2100, 2700, 2900, 2900, 2600, 2550, 2550, 2950, 3400, 3150};
	int [] groundY3 = {300, 100, 500, 400, 800, 600, 1000, 900, 1300, 500, 900, 100, 500, 500, 900, 1300, 1300/**/, 1250, 1040/**/, 1040, 1200, 880, 480, 680, 880};
	int [] groundL3 = {400, 800, 400, 600, 200, 200, 600, 400, 1200, 800, 800, 1200, 400, 800, 400, 800, 400, 100, 320/**/, 320, 100, 100, 900, 400, 500};
	int [] groundD3 = {0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1};
	int [] groundT3 = {0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0};

	int [] groundX4 = {800, 200, 600, 400, 0, -400, -800, -600, -800, -1000, -1200, -1400, -1600, -1400, -1200, -1400, -1600, -1800, -2000, -1600};
	int [] groundY4 = {300, 100, 500, 700, 900, 700, 100, 500, 700, 900, 800, 700, 600, 500, 400, 300, 400, 500, 300, 100};
	int [] groundL4 = {400, 1200, 400, 400, 800, 400, 800, 400, 400, 400, 200, 400, 200, 400, 200, 400, 200, 400, 400, 800};
	int [] groundD4 = {0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1};
	int [] groundT4 = {0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

	/** [ステージ番号 - 1][ground番号] */
	int [][] groundX = new int [STAGE_MAX][];

	/** [ステージ番号 - 1][ground番号] */
	int [][] groundY = new int [STAGE_MAX][];

	/** 地面の長さ　[ステージ番号 - 1][ground番号] */
	int [][] groundL = new int [STAGE_MAX][];

	/**
	 * 0:縦　1:横　[ステージ番号 - 1][ground番号]
	 */
	int [][] groundD = new int [STAGE_MAX][];

	/**
	 * 0:ノーマル 1:トゲトゲ　[ステージ番号 - 1][ground番号]
	 */
	int [][] groundT = new int [STAGE_MAX][];


	int [] jointX1 = {300, 600, 900, 1500, 1200, 1900, 2450, 2500, 3100};
	int [] jointY1 = {400, 300, 200, 50, 0, 0, 0, 410, 0};
	int [] jointT1 = {0, 0, 0, 0, 0, 0, 0, 0, 1};
	int [] jointR1 = {0, 0, 0, 0, 0, 0, 0, 0, 180};

	int [] jointX2 = {300, 700, 1100, 1500, 1900, 2500, 2700, 3100};
	int [] jointY2 = {300, 400, 400, 700, 400, 300, 450, 300};
	int [] jointT2 = {0, 0, 0, 0, 0, 0, 0, 1};
	int [] jointR2 = {0, 0, 0, 200, 0, 0, 0, 180};

	int [] jointX3 = {300, 700, 700, 1000, 1300, 1500, 2300, 2100, 2100, 2400, 2550, 2950, 3200};
	int [] jointY3 = {300, 300, 1100, 1100, 900, 300, 300, 500, 900, 1100, 820, 820, 700};
	int [] jointT3 = {0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 1};
	int [] jointR3 = {0, 180, 0, 0, 0, 180, 0, 0, 0, 0, 0, 0, 180};

	int [] jointX4 = {600, 200, 200, -200, -200, -1000, -1000, -1220, -1220, -1580, -1800};
	int [] jointY4 = {300, 300, 700, 300, 700, 300, 700, 520, 680, 600, 300};
	int [] jointT4 = {0, 3, 3, 3, 3, 4, 4, 4, 4, 4, 1};
	int [] jointR4 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 180};

	/** [ステージ番号 - 1][joint番号] */
	int [][] jointX = new int [STAGE_MAX][];

	/** [ステージ番号 - 1][joint番号] */
	int [][] jointY = new int [STAGE_MAX][];

	/** 0:ノーマル 1:ゴール　[ステージ番号 - 1][joint番号] */
	int [][] jointT = new int [STAGE_MAX][];

	/** jointLock有効範囲半径　0なら無限(指定しない)　*/
	int [][] jointR = new int [STAGE_MAX][];


	int [] coinX1 = {450, 900, 900, 750, 795, 1005, 795, 1500, 1500, 1650, 1350, 1605, 1605, 1395, 1395, 1865, 1795, 1865, 1795};
	int [] coinY1 = {450, 350, 50, 200, 305, 305, 95, 200, -100, 50, 50, 155, -55, 155, -55, 395, 395, 465, 465};

	int [] coinX2 = {700, 600, 800, 1100, 1000, 1200, 900, 1300, 1500, 1500, 1350, 1650, 1395, 1395, 1605, 1605};
	int [] coinY2 = {550, 500, 500, 550, 500, 500, 480, 480, 550, 850, 700, 700, 595, 805, 595, 805};

	int [] coinX3 = {700, 850, 550, 1300, 1500, 1441, 1900, 2300, 2241, 2100, 1969, 1900, 1969, 2100};
	int [] coinY3 = {700, 600, 800, 1120, 900, 1041, 480, 500, 641, 700, 759, 900, 1041, 1100};

	int [] coinX4 = {200, 200, 20, 380, 200, 200, 20, 380, -200, -200, -20, -380, -200, -200, -20, -380, -1000, -1000, -820, -1180, -1000, -1000, -820, -1180};
	int [] coinY4 = {120, 480, 300, 300, 520, 880, 700, 700, 120, 480, 300, 300, 520, 880, 700, 700, 120, 480, 300, 300, 520, 880, 700, 700};

	/** [ステージ番号 - 1][coin番号] */
	int [][] coinX = new int [STAGE_MAX][];

	/** [ステージ番号 - 1][coin番号] */
	int [][] coinY = new int [STAGE_MAX][];


	int [] heartX1 = {2450};
	int [] heartY1 = {-160};

	int [] heartX2 = {2300, 2850};
	int [] heartY2 = {850, 150};

	int [] heartX3 = {};
	int [] heartY3 = {};

	int [] heartX4 = {-1400};
	int [] heartY4 = {600};

	/** [ステージ番号 - 1][coin番号] */
	int [][] heartX = new int [STAGE_MAX][];

	/** [ステージ番号 - 1][coin番号] */
	int [][] heartY = new int [STAGE_MAX][];

	Stage()
	{
		groundX[0] = groundX1;
		groundX[1] = groundX2;
		groundX[2] = groundX3;
		groundX[3] = groundX4;

		groundY[0] = groundY1;
		groundY[1] = groundY2;
		groundY[2] = groundY3;
		groundY[3] = groundY4;

		groundL[0] = groundL1;
		groundL[1] = groundL2;
		groundL[2] = groundL3;
		groundL[3] = groundL4;

		groundD[0] = groundD1;
		groundD[1] = groundD2;
		groundD[2] = groundD3;
		groundD[3] = groundD4;

		groundT[0] = groundT1;
		groundT[1] = groundT2;
		groundT[2] = groundT3;
		groundT[3] = groundT4;


		jointX[0] = jointX1;
		jointX[1] = jointX2;
		jointX[2] = jointX3;
		jointX[3] = jointX4;

		jointY[0] = jointY1;
		jointY[1] = jointY2;
		jointY[2] = jointY3;
		jointY[3] = jointY4;

		jointT[0] = jointT1;
		jointT[1] = jointT2;
		jointT[2] = jointT3;
		jointT[3] = jointT4;

		jointR[0] = jointR1;
		jointR[1] = jointR2;
		jointR[2] = jointR3;
		jointR[3] = jointR4;


		coinX[0] = coinX1;
		coinX[1] = coinX2;
		coinX[2] = coinX3;
		coinX[3] = coinX4;

		coinY[0] = coinY1;
		coinY[1] = coinY2;
		coinY[2] = coinY3;
		coinY[3] = coinY4;


		heartX[0] = heartX1;
		heartX[1] = heartX2;
		heartX[2] = heartX3;
		heartX[3] = heartX4;

		heartY[0] = heartY1;
		heartY[1] = heartY2;
		heartY[2] = heartY3;
		heartY[3] = heartY4;
	}

	/** groundXの出力 **/
	int[] groundX(int stageNum)
	{
		return groundX[stageNum - 1];
	}

	/** groundYの出力 **/
	int[] groundY(int stageNum)
	{
		return groundY[stageNum - 1];
	}

	/** groundLの出力 **/
	int[] groundL(int stageNum)
	{
		return groundL[stageNum - 1];
	}

	/** groundDの出力 **/
	int[] groundD(int stageNum)
	{
		return groundD[stageNum - 1];
	}

	/** groundTの出力 **/
	int[] groundT(int stageNum)
	{
		return groundT[stageNum - 1];
	}

	/** jointXの出力 */
	int[] jointX(int stageNum)
	{
		return jointX[stageNum - 1];
	}

	/** jointYの出力 */
	int[] jointY(int stageNum)
	{
		return jointY[stageNum - 1];
	}

	/** jointTの出力 */
	int[] jointT(int stageNum)
	{
		return jointT[stageNum - 1];
	}

	/** jointRの出力 */
	int[] jointR(int stageNum)
	{
		return jointR[stageNum - 1];
	}

	/** coinXの出力 */
	int[] coinX(int stageNum)
	{
		return coinX[stageNum - 1];
	}

	/** coinYの出力 */
	int[] coinY(int stageNum)
	{
		return coinY[stageNum - 1];
	}

	/** heartXの出力 */
	int[] heartX(int stageNum)
	{
		return heartX[stageNum - 1];
	}

	/** heartYの出力 */
	int[] heartY(int stageNum)
	{
		return heartY[stageNum - 1];
	}
}
