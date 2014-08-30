package blood;

import l2s.commons.math.random.RndSelector;
import l2s.commons.util.Rnd;
import l2s.gameserver.data.xml.holder.SkillAcquireHolder;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.SkillLearn;
import l2s.gameserver.model.base.AcquireType;
import l2s.gameserver.model.base.Experience;
import l2s.gameserver.tables.SkillTable;
import l2s.gameserver.templates.player.PlayerTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import blood.dao.FakeNameDAO;
import blood.dao.FakePlayerDAO;
import blood.data.holder.FPItemHolder;
import blood.utils.ClassFunctions;
import blood.utils.NameFunctions;

public class FPCCreator
{
	// TODO - move code here
	
	private static final Logger 		_log = LoggerFactory.getLogger(FPCCreator.class);
	
	public static void isHumanName(String name)
	{
		name.toLowerCase().contains("human");
	}
   	
   	public static void createNewChar()
	{
		int[] _class_list = {
				0, // human F
				10, // human M
				18, // elf F
				25, // elf M
				31, // delf F
				38, // delf M
				44, // orc F
				49, // orc M
				53, // dwarf
				123, // kamael Male
				124 // kamael Female
				};
		RndSelector<Integer> _randomFactor = new RndSelector<Integer>(); 
		String name = FakeNameDAO.getInstance().getName();
		if(NameFunctions.isHumanName(name))
		{
			if(!NameFunctions.isMysticName(name))
				_randomFactor.add(0, 1);
			if(!NameFunctions.isFighterName(name))
				_randomFactor.add(10, 1);
			if(NameFunctions.isMysticName(name) && NameFunctions.isFighterName(name))
			{
				_randomFactor.add(0, 1);
				_randomFactor.add(10, 1);
			}
		}
		else if(NameFunctions.isElfName(name))
		{
			if(!NameFunctions.isMysticName(name))
				_randomFactor.add(18, 1);
			if(!NameFunctions.isFighterName(name))
				_randomFactor.add(25, 1);
			if(NameFunctions.isMysticName(name) && NameFunctions.isFighterName(name))
			{
				_randomFactor.add(18, 1);
				_randomFactor.add(25, 1);
			}
		}
		else if(NameFunctions.isDarkElfName(name))
		{
			if(!NameFunctions.isMysticName(name))
				_randomFactor.add(31, 1);
			if(!NameFunctions.isFighterName(name))
				_randomFactor.add(38, 1);
			if(NameFunctions.isMysticName(name) && NameFunctions.isFighterName(name))
			{
				_randomFactor.add(31, 1);
				_randomFactor.add(38, 1);
			}
		}
		else if(NameFunctions.isOrcName(name))
		{
			if(!NameFunctions.isMysticName(name))
				_randomFactor.add(44, 1);
			if(!NameFunctions.isFighterName(name))
				_randomFactor.add(49, 1);
			if(NameFunctions.isMysticName(name) && NameFunctions.isFighterName(name))
			{
				_randomFactor.add(44, 1);
				_randomFactor.add(49, 1);
			}
		}
		else if(NameFunctions.isDwarfName(name))
		{
			_randomFactor.add(53, 1);
		}
		else if(NameFunctions.isKamaelName(name))
		{
			if(!NameFunctions.isFemaleName(name))
				_randomFactor.add(123, 1);
			if(!NameFunctions.isMaleName(name))
				_randomFactor.add(124, 1);
		}
		else if(NameFunctions.isTankerName(name))
		{
			_randomFactor.add(0, 1);
			_randomFactor.add(18, 1);
			_randomFactor.add(31, 1);
		}
		else if(NameFunctions.isWarriorName(name))
		{
			_randomFactor.add(0, 1);
			_randomFactor.add(18, 1);
			_randomFactor.add(31, 1);
			_randomFactor.add(44, 1);
			_randomFactor.add(53, 1);
			if(!NameFunctions.isFemaleName(name))
				_randomFactor.add(123, 1);
			if(!NameFunctions.isMaleName(name))
				_randomFactor.add(124, 1);
		}
		else if(NameFunctions.isDaggerName(name))
		{
			_randomFactor.add(0, 1);
			_randomFactor.add(18, 1);
			_randomFactor.add(31, 1);
			_randomFactor.add(53, 1);
		}
		else if(NameFunctions.isRangerName(name))
		{
			_randomFactor.add(0, 1);
			_randomFactor.add(18, 1);
			_randomFactor.add(31, 1);
			if(!NameFunctions.isMaleName(name))
				_randomFactor.add(124, 1);
		}
		else if(NameFunctions.isFighterName(name))
		{
			_randomFactor.add(0, 1);
			_randomFactor.add(18, 1);
			_randomFactor.add(31, 1);
			_randomFactor.add(44, 1);
			_randomFactor.add(53, 1);
			if(!NameFunctions.isFemaleName(name))
				_randomFactor.add(123, 1);
			if(!NameFunctions.isMaleName(name))
				_randomFactor.add(124, 1);
		}
		else if(NameFunctions.isNukerName(name))
		{
			_randomFactor.add(10, 1);
			_randomFactor.add(25, 1);
			_randomFactor.add(38, 1);
			if(!NameFunctions.isFemaleName(name))
				_randomFactor.add(123, 1);
			if(!NameFunctions.isMaleName(name))
				_randomFactor.add(124, 1);
		}
		else if(NameFunctions.isSummonerName(name))
		{
			_randomFactor.add(10, 1);
			_randomFactor.add(25, 1);
			_randomFactor.add(38, 1);
		}
		else if(NameFunctions.isHealerName(name))
		{
			_randomFactor.add(10, 1);
			_randomFactor.add(25, 1);
			_randomFactor.add(38, 1);
		}
		else if(NameFunctions.isBufferName(name))
		{
			_randomFactor.add(10, 1);
			_randomFactor.add(25, 1);
			_randomFactor.add(38, 1);
			_randomFactor.add(49, 1);
			_randomFactor.add(18, 1);
			_randomFactor.add(31, 1);
		}
		else if(NameFunctions.isMysticName(name))
		{
			_randomFactor.add(10, 1);
			_randomFactor.add(25, 1);
			_randomFactor.add(38, 1);
			_randomFactor.add(49, 1);
			if(!NameFunctions.isFemaleName(name))
				_randomFactor.add(123, 1);
			if(!NameFunctions.isMaleName(name))
				_randomFactor.add(124, 1);
		}
		else // add all
		{
			for(int classId: _class_list)
				_randomFactor.add(classId, 1);
		}
		
		try{
			int selectedClass = _randomFactor.select();
			createNewChar(selectedClass, name, "_fake_account");
		}catch(Exception e){
			_log.error("create char name:"+name, e);
		}
	}
    
	public static void createNewChar(int _classId, String _name, String _account)
	{
		int _sex = Rnd.get(0,1);
		if(_classId == 123 || NameFunctions.isMaleName(_name)){
			_sex = 0;
		}
		
		if(_classId == 124 || NameFunctions.isFemaleName(_name)){
			_sex = 1;
		}
		int _hairStyle = Rnd.get(0, _sex == 1 ? 6 : 4);
		int _hairColor = Rnd.get(0,2);
		int _face = Rnd.get(0,2);
		
		Player newChar = Player.create(_classId, _sex, _account, _name, _hairStyle, _hairColor, _face);
		
		if(newChar == null)
			return;
		
		FakeNameDAO.getInstance().useName(_name);
		
		FakePlayerDAO.addFPC(newChar.getObjectId());
		
		_log.info("Create NewChar:"+_name+" in Account: "+_account+" Class: " + _classId + " Sex: " + _sex);
		
		int _obj_id = newChar.getObjectId();
		
		initNewChar(newChar);
		
		new FPCInfo(_obj_id);
	}
	
	public static void initNewChar(Player newChar)
	{
		PlayerTemplate template = newChar.getTemplate();

		newChar.getSubClassList().restore();

       	newChar.setLoc(template.getStartLocation());

		newChar.setHeading(Rnd.get(0, 90000));
		
		for(SkillLearn skill : SkillAcquireHolder.getInstance().getAvailableSkills(newChar, AcquireType.NORMAL))
			newChar.addSkill(SkillTable.getInstance().getInfo(skill.getId(), skill.getLevel()), true);

		newChar.setCurrentHpMp(newChar.getMaxHp(), newChar.getMaxMp());
		newChar.setCurrentCp(0); // retail
		newChar.setOnlineStatus(false);
		
		int newLevel = Rnd.chance(20) ? 85 : 20;
		
    	Long exp_add = Experience.LEVEL[newLevel] - newChar.getExp();
    	newChar.addExpAndSp(exp_add, 0, true);
    	
//    	ClassFunctions.upClass(newChar);
    	FPItemHolder.equip(newChar, true);

		newChar.store(false);
		newChar.getInventory().store();
		newChar.deleteMe();
		
	}
}
