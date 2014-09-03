package blood;

import l2s.gameserver.model.base.ClassId;
import l2s.gameserver.model.base.ClassType;
import l2s.gameserver.model.base.ClassType2;
import l2s.gameserver.model.base.Race;
import l2s.gameserver.model.base.Sex;
import blood.data.holder.NamePatternHolder;
import blood.utils.PIDHelper;


public class BloodTest
{
	
	public static BloodTest _instance;
	
	private BloodTest()
	{
		//BloodFakePlayers.getInstance();
		
	}
	
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		System.out.println(NamePatternHolder.getStartClass("orcsomething"));
	}
	
}
