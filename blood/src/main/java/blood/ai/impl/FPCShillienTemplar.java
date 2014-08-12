package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCShillienTemplar extends TankerPC
{
	public FPCShillienTemplar(Player actor)
	{
		super(actor);
		
	}
	
	@Override
	protected boolean thinkBuff()
	{
		if(thinkBuff(new int[] {33, 22, 278})) // summon cubic
			return true;
		
		return super.thinkBuff();
	}

	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		SkillList.add(28);	//Aggression
		SkillList.add(18);	//Aura of Hate
		//SkillList.add(33);	//Summon Phantom Cubic
		//SkillList.add(22);	//Summon Vampiric Cubic
		//SkillList.add(278);	//Summon Viper Cubic
		SkillList.add(279);	//Lightning Strike
		SkillList.add(289);	//Life Leech
		SkillList.add(223);	//Sting
		SkillList.add(115);	//Power Break
		SkillList.add(122);	//Hex
		//SkillList.add(110);	//Ultimate Defense
		SkillList.add(402);	//Arrest
		SkillList.add(401);	//Judgment
				
		//skill 3rd
		SkillList.add(335);	//Fortitude
		SkillList.add(352);	//Shield Bash
		SkillList.add(368);	//Vengeance
		SkillList.add(351);	//Magical Mirror
		SkillList.add(342);	//Touch of Death
		SkillList.add(454);	//Symbol of Defense
		SkillList.add(789);	//Spirit of Shilen
		
		return SkillList;
	}
	

	@Override
	protected ArrayList<Integer> getDrawTargetSkill()
	{
		ArrayList<Integer>	SkillList	= new ArrayList<Integer>();
		
		SkillList.add(28); //Aggression
		SkillList.add(402); //Arrest
		
		return SkillList;
	}
}

