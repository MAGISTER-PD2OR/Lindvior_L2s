package blood;

import blood.data.holder.NamePatternHolder;


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
