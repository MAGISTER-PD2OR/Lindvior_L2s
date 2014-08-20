package blood;

import java.sql.Connection;
import java.sql.PreparedStatement;

import l2s.commons.dbutils.DbUtils;
import l2s.commons.math.random.RndSelector;
import l2s.commons.util.Rnd;
import l2s.gameserver.data.xml.holder.SkillAcquireHolder;
import l2s.gameserver.database.DatabaseFactory;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.SkillLearn;
import l2s.gameserver.model.base.AcquireType;
import l2s.gameserver.tables.SkillTable;
import l2s.gameserver.templates.player.PlayerTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import blood.dao.FakeNameDAO;

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
		if(FakeNameDAO.isHumanName(name))
		{
			if(!FakeNameDAO.isMysticName(name))
				_randomFactor.add(0, 1);
			if(!FakeNameDAO.isFighterName(name))
				_randomFactor.add(10, 1);
		}
		else if(FakeNameDAO.isElfName(name))
		{
			if(!FakeNameDAO.isMysticName(name))
				_randomFactor.add(18, 1);
			if(!FakeNameDAO.isFighterName(name))
				_randomFactor.add(25, 1);
		}
		else if(FakeNameDAO.isDarkElfName(name))
		{
			if(!FakeNameDAO.isMysticName(name))
				_randomFactor.add(31, 1);
			if(!FakeNameDAO.isFighterName(name))
				_randomFactor.add(38, 1);
		}
		else if(FakeNameDAO.isOrcName(name))
		{
			if(!FakeNameDAO.isMysticName(name))
				_randomFactor.add(44, 1);
			if(!FakeNameDAO.isFighterName(name))
				_randomFactor.add(49, 1);
		}
		else if(FakeNameDAO.isDwarfName(name))
		{
			_randomFactor.add(53, 1);
		}
		else if(FakeNameDAO.isKamaelName(name))
		{
			if(!FakeNameDAO.isFemaleName(name))
				_randomFactor.add(123, 1);
			if(!FakeNameDAO.isMaleName(name))
				_randomFactor.add(124, 1);
		}
		else if(FakeNameDAO.isTankerName(name))
		{
			_randomFactor.add(0, 1);
			_randomFactor.add(18, 1);
			_randomFactor.add(31, 1);
		}
		else if(FakeNameDAO.isWarriorName(name))
		{
			_randomFactor.add(0, 1);
			_randomFactor.add(18, 1);
			_randomFactor.add(31, 1);
			_randomFactor.add(44, 1);
			_randomFactor.add(53, 1);
			if(!FakeNameDAO.isFemaleName(name))
				_randomFactor.add(123, 1);
			if(!FakeNameDAO.isMaleName(name))
				_randomFactor.add(124, 1);
		}
		else if(FakeNameDAO.isDaggerName(name))
		{
			_randomFactor.add(0, 1);
			_randomFactor.add(18, 1);
			_randomFactor.add(31, 1);
			_randomFactor.add(53, 1);
		}
		else if(FakeNameDAO.isRangerName(name))
		{
			_randomFactor.add(0, 1);
			_randomFactor.add(18, 1);
			_randomFactor.add(31, 1);
			if(!FakeNameDAO.isMaleName(name))
				_randomFactor.add(124, 1);
		}
		else if(FakeNameDAO.isFighterName(name))
		{
			_randomFactor.add(0, 1);
			_randomFactor.add(18, 1);
			_randomFactor.add(31, 1);
			_randomFactor.add(44, 1);
			_randomFactor.add(53, 1);
			if(!FakeNameDAO.isFemaleName(name))
				_randomFactor.add(123, 1);
			if(!FakeNameDAO.isMaleName(name))
				_randomFactor.add(124, 1);
		}
		else if(FakeNameDAO.isNukerName(name))
		{
			_randomFactor.add(10, 1);
			_randomFactor.add(25, 1);
			_randomFactor.add(38, 1);
			if(!FakeNameDAO.isFemaleName(name))
				_randomFactor.add(123, 1);
			if(!FakeNameDAO.isMaleName(name))
				_randomFactor.add(124, 1);
		}
		else if(FakeNameDAO.isSummonerName(name))
		{
			_randomFactor.add(10, 1);
			_randomFactor.add(25, 1);
			_randomFactor.add(38, 1);
		}
		else if(FakeNameDAO.isHealerName(name))
		{
			_randomFactor.add(10, 1);
			_randomFactor.add(25, 1);
			_randomFactor.add(38, 1);
		}
		else if(FakeNameDAO.isBufferName(name))
		{
			_randomFactor.add(10, 1);
			_randomFactor.add(25, 1);
			_randomFactor.add(38, 1);
			_randomFactor.add(49, 1);
			_randomFactor.add(18, 1);
			_randomFactor.add(31, 1);
		}
		else if(FakeNameDAO.isMysticName(name))
		{
			_randomFactor.add(10, 1);
			_randomFactor.add(25, 1);
			_randomFactor.add(38, 1);
			_randomFactor.add(49, 1);
			if(!FakeNameDAO.isFemaleName(name))
				_randomFactor.add(123, 1);
			if(!FakeNameDAO.isMaleName(name))
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
		Connection con = null;
		PreparedStatement statement = null;
		
//		_log.info("createNewChar:"+_name);
		
		//int _classId = Integer.parseInt(wordList[1]);
		int _sex = Rnd.get(0,1);
		if(_classId == 123 || FakeNameDAO.isMaleName(_name)){
			_sex = 0;
		}
		
		if(_classId == 124 || FakeNameDAO.isFemaleName(_name)){
			_sex = 1;
		}
		int _hairStyle = Rnd.get(0, _sex == 1 ? 6 : 4);
		int _hairColor = Rnd.get(0,2);
		int _face = Rnd.get(0,2);
		
		//String _account = wordList.length == 3 ? wordList[2] : "_mylove1412";
		
		Player newChar = Player.create(_classId, _sex, _account, _name, _hairStyle, _hairColor, _face);
		
		if(newChar == null)
			return;
		
		FakeNameDAO.getInstance().useName(_name);
		
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			statement = con.prepareStatement("INSERT INTO fpc(obj_id) VALUES (?)");
			statement.setInt(1, newChar.getObjectId());
			statement.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(con, statement);
		}
		
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

		newChar.store(false);
		newChar.getInventory().store();
		newChar.deleteMe();
		
	}
	
	public static void rearm(Player player)
	{
		
	}
}
