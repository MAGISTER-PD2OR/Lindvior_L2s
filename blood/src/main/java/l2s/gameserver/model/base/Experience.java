package l2s.gameserver.model.base;

import l2s.gameserver.Config;

public class Experience
{
	public final static long LEVEL[] = { -1L, // level 0 (unreachable)
//			/* Lvl:1 */0L,
//			/* Lvl:2 */68L,
//			/* Lvl:3 */363L,
//			/* Lvl:4 */1168L,
//			/* Lvl:5 */2884L,
//			/* Lvl:6 */6038L,
//			/* Lvl:7 */11287L,
//			/* Lvl:8 */19423L,
//			/* Lvl:9 */31378L,
//			/* Lvl:10 */48229L,
//			/* Lvl:11 */71202L,
//			/* Lvl:12 */101677L,
//			/* Lvl:13 */141193L,
//			/* Lvl:14 */191454L,
//			/* Lvl:15 */254330L,
//			/* Lvl:16 */331867L,
//			/* Lvl:17 */426288L,
//			/* Lvl:18 */540000L,
//			/* Lvl:19 */675596L,
//			/* Lvl:20 */835862L,
//			/* Lvl:21 */920357L,
//			/* Lvl:22 */1015431L,
//			/* Lvl:23 */1123336L,
//			/* Lvl:24 */1246808L,
//			/* Lvl:25 */1389235L,
//			/* Lvl:26 */1554904L,
//			/* Lvl:27 */1749413L,
//			/* Lvl:28 */1980499L,
//			/* Lvl:29 */2260321L,
//			/* Lvl:30 */2634751L,
//			/* Lvl:31 */2844287L,
//			/* Lvl:32 */3093068L,
//			/* Lvl:33 */3389496L,
//			/* Lvl:34 */3744042L,
//			/* Lvl:35 */4169902L,
//			/* Lvl:36 */4683988L,
//			/* Lvl:37 */5308556L,
//			/* Lvl:38 */6074376L,
//			/* Lvl:39 */7029248L,
//			/* Lvl:40 */8342182L,
//			/* Lvl:41 */8718976L,
//			/* Lvl:42 */12842357L,
//			/* Lvl:43 */14751932L,
//			/* Lvl:44 */17009030L,
//			/* Lvl:45 */19686117L,
//			/* Lvl:46 */22875008L,
//			/* Lvl:47 */26695470L,
//			/* Lvl:48 */31312332L,
//			/* Lvl:49 */36982854L,
//			/* Lvl:50 */44659561L,
//			/* Lvl:51 */48128727L,
//			/* Lvl:52 */52277875L,
//			/* Lvl:53 */57248635L,
//			/* Lvl:54 */63216221L,
//			/* Lvl:55 */70399827L,
//			/* Lvl:56 */79078300L,
//			/* Lvl:57 */89616178L,
//			/* Lvl:58 */102514871L,
//			/* Lvl:59 */118552044L,
//			/* Lvl:60 */140517709L,
//			/* Lvl:61 */153064754L,
//			/* Lvl:62 */168231664L,
//			/* Lvl:63 */186587702L,
//			/* Lvl:64 */208840245L,
//			/* Lvl:65 */235877658L,
//			/* Lvl:66 */268833561L,
//			/* Lvl:67 */309192920L,
//			/* Lvl:68 */358998712L,
//			/* Lvl:69 */421408669L,
//			/* Lvl:70 */493177635L,
//			/* Lvl:71 */555112374L,
//			/* Lvl:72 */630494192L,
//			/* Lvl:73 */722326994L,
//			/* Lvl:74 */834354722L,
//			/* Lvl:75 */971291524L,
//			/* Lvl:76 */1139165674L,
//			/* Lvl:77 */1345884863L,
//			/* Lvl:78 */1602331019L,
//			/* Lvl:79 */1902355477L,
//			/* Lvl:80 */2288742870L,
//			/* Lvl:81 */2703488268L,
//			/* Lvl:82 */3174205601L,
//			/* Lvl:83 */3708727539L,
//			/* Lvl:84 */4316300702L,
//			/* Lvl:85 */5008025097L,
//			/* Lvl:86 */10985069426L,
//			/* Lvl:87 */19192594397L,
//			/* Lvl:88 */33533938399L,
//			/* Lvl:89 */44373087147L,
		0L, //1
		68L, //2
		363L, //3
		1168L,//4
		2884L,//5
		6038L,//6
		11287L,//7
		19423L,//8
		31378L,//9
		48229L,//10
		71202L,//11
		101677L,//12
		141193L,//13
		191454L,//14
		254330L,//15
		331867L,//16
		426288L,//17
		540000L,//18
		675596L,//19
		835862L,//20
		920357L,//21
		1015431L,//22
		1123336L,//23
		1246808L,//24
		1389235L,//25
		1554904L,//26
		1749413L,
		1980499L,
		2260321L,
		2634751L, //30
		2844287L,
		3093068L,
		3389496L,
		3744042L,
		4169902L,
		4683988L,
		5308556L,
		6074376L,
		7029248L,
		8342182L, //40
		8718976L,
		9289560L,
		9991807L,
		10856075L,
		11920512L,
		13233701L,
		14858961L,
		16882633L,
		19436426L,
		22977080L, //50
		24605660L,
		26635948L,
		29161263L,
		32298229L,
		36193556L,
		41033917L,
		47093035L,
		54711546L,
		64407353L,
		77947292L, //60
		85775204L,
		95595386L,
		107869713L,
		123174171L,
		142229446L,
		165944812L,
		195677269L,
		233072222L,
		280603594L,
		335732975L, //70
		383597045L,
		442752112L,
		516018015L,
		606913902L,
		719832095L,
		860289228L,
		1035327669L,
		1259458516L,
		1534688053L,
		1909610088L, //80
		2342785974L, // 81
		2861857696L, //82
		3478378664L, //83
		4211039578L, //84
		5078544041L, //85
		10985069426L,
		19192594397L,
		33533938399L,
		43503026615L,
			/* Lvl:90 */63751938490L,
			/* Lvl:91 */88688523458L,
			/* Lvl:92 */120224273113L,
			/* Lvl:93 */157133602347L,
			/* Lvl:94 */208513860393L,
			/* Lvl:95 */266769078393L,
			/* Lvl:96 */377839508352L,
			/* Lvl:97 */592791113370L,
			/* Lvl:98 */1016243369039L,
			/* Lvl:99 */1956916677389L,
			/* Lvl:100 */6178380725000L };

	/**
	 * Return PenaltyModifier (can use in all cases)
	 *
	 * @param count	- how many times <percents> will be substructed
	 * @param percents - percents to substruct
	 *
	 * @author Styx
	 */

	/*
	 *  This is for fine view only ;)
	 *
	 *	public final static double penaltyModifier(int count, int percents)
	 *	{
	 *		int allPercents = 100;
	 *		int allSubstructedPercents = count * percents;
	 *		int penaltyInPercents = allPercents - allSubstructedPercents;
	 *		double penalty = penaltyInPercents / 100.0;
	 *		return penalty;
	 *	}
	 */
	public static double penaltyModifier(long count, double percents)
	{
		return Math.max(1. - count * percents / 100, 0);
	}

	/**
	 * Максимальный достижимый уровень
	 */
	public static int getMaxLevel()
	{
		return Config.ALT_MAX_LEVEL;
	}

	/**
	 * Максимальный уровень для саба
	 */
	public static int getMaxSubLevel()
	{
		return Config.ALT_MAX_SUB_LEVEL;
	}

	public static int getLevel(long thisExp)
	{
		int level = 0;
		for(int i = 0; i < LEVEL.length; i++)
		{
			long exp = LEVEL[i];
			if(thisExp >= exp)
				level = i;
		}
		return level;
	}

	public static long getExpForLevel(int lvl)
	{
		if(lvl >= Experience.LEVEL.length)
			return 0;
		return Experience.LEVEL[lvl];
	}

	public static double getExpPercent(int level, long exp)
	{
		return (exp - getExpForLevel(level)) / ((getExpForLevel(level + 1) - getExpForLevel(level)) / 100.0D) * 0.01D;
	}
}