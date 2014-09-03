package blood;

import java.util.HashSet;

import l2s.commons.math.random.RndSelector;
import l2s.commons.util.Rnd;
import l2s.gameserver.data.xml.holder.SkillAcquireHolder;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.SkillLearn;
import l2s.gameserver.model.base.AcquireType;
import l2s.gameserver.model.base.ClassId;
import l2s.gameserver.model.base.ClassLevel;
import l2s.gameserver.model.base.Experience;
import l2s.gameserver.model.base.Race;
import l2s.gameserver.model.base.Sex;
import l2s.gameserver.tables.SkillTable;
import l2s.gameserver.templates.player.PlayerTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import blood.dao.FakeNameDAO;
import blood.dao.FakePlayerDAO;
import blood.data.holder.NamePatternHolder;

public class FPCCreator
{
	// TODO - move code here
	
	private static final Logger 		_log = LoggerFactory.getLogger(FPCCreator.class);
	
	public static void createNewChar()
	{
		RndSelector<Integer> _randomFactor = new RndSelector<Integer>();
		HashSet<ClassId> validClass = new HashSet<ClassId>();
		String name = FakeNameDAO.getInstance().getName();
		
		Race meaningRace = NamePatternHolder.getRaceByName(name);
		Sex meaningSex = NamePatternHolder.getSexByName(name);
		
		if(validClass.size() == 0)
			for(ClassId classid: ClassId.VALUES)
			{
				if(meaningRace != null && !classid.isOfRace(meaningRace))
					continue;
				
				if(NamePatternHolder.checkName(name, classid) || NamePatternHolder.checkName(name, classid.getType2()) || NamePatternHolder.checkName(name, classid.getType()))
					validClass.add(classid.getFirstParent(meaningSex == null ? 0 : meaningSex.ordinal()));
			}
		
		if(validClass.size() == 0)
			for(ClassId classid: ClassId.VALUES)
			{
				if(meaningRace != null && !classid.isOfRace(meaningRace))
					continue;
				
				if(!classid.isOfLevel(ClassLevel.NONE))
					continue;
				
				validClass.add(classid.getFirstParent(meaningSex == null ? 0 : meaningSex.ordinal()));
			}
		
		if(validClass.size() == 0){
			_log.info("cant find any class with that name:"+name);
			return;
		}
		
		for(ClassId classid: validClass)
			_randomFactor.add(classid.getId(), 1);
		
		try{
			int selectedClass = _randomFactor.select();
			createNewChar(selectedClass, name, "_fake_account");
		}catch(Exception e){
			_log.error("create char name:"+name, e);
		}
	}
    
	public static void createNewChar(int _classId, String _name, String _account)
	{
		Sex sex = NamePatternHolder.getSexByName(_name);
		
		if(_classId == 123)
			sex = Sex.MALE;
		
		if(_classId == 124)
			sex = Sex.FEMALE;
		
		int _sex = sex != null ? sex.ordinal() : Rnd.get(0,1);
		
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
//    	FPItemHolder.equip(newChar, true);

		newChar.store(false);
		newChar.getInventory().store();
		newChar.deleteMe();
		
	}
}
