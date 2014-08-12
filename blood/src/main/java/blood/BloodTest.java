package blood;

import l2mq.L2MQ;


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
		L2MQ.getInstance();
			
	}
	
//	public static int increaseItemEnchant(int currentEnchant, int maxEnchant, int decisionChance)
//	{
//		System.out.println("start function: currentEnchant " + currentEnchant + " maxEnchant " + maxEnchant);
//		if(currentEnchant >= maxEnchant)
//		{
//			System.out.println(currentEnchant + " >=" + maxEnchant);
//			return currentEnchant;
//		}
//		
//		int enchantLevel = currentEnchant;
//	
//		//base on the currentEnchant, think that should we enchant it or not
//		//the higher of currentEnchant, the smaller the probability
//		//int probability = Math.pow(0.7, currentEnchant-3)*100;
//		
//		if(enchantLevel < 3) enchantLevel = 3;
//		
//		int probability = (decisionChance/(enchantLevel-3));
//		
//		if(Rnd.chance(probability))
//		{
//			System.out.println("Man up and do it!");
//			boolean isContinue  = true;
//			
//			for(int i=3;i<maxEnchant;i++)
//			{
//				if(isContinue)
//				{
//					if(Rnd.chance(60))
//					{
//						enchantLevel++;
//						
//						//Enchanting succeed. Let's think about whether continue enchanting or not
//						//Only 35% of the population have the gut to continue
//						probability = (decisionChance/(enchantLevel-3));
//						isContinue = (Rnd.chance(probability))?true:false;
//						System.out.println("enchantLevel " + enchantLevel + " isContinue " + isContinue);
//					}
//					else
//					{
//						System.out.println("enchant failed at level " + (enchantLevel+1));
//						isContinue = false;
//						//Enchanting failed, item is broken. Fall back to previous grade
//						enchantLevel = -1; 
//					}
//				}
//				else
//				{
//					if(enchantLevel > 0)
//					System.out.println("chicken out at level " + enchantLevel + " with probability " + probability);
//				}
//			}
//		}
//		else
//		{
//			System.out.println("chicken out with probability " + probability);
//		}
//		System.out.println("--------------------------");
//		return enchantLevel;
//	}
	
}
