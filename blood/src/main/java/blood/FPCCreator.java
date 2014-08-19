package blood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import l2s.commons.dbutils.DbUtils;
import l2s.commons.util.Rnd;
import l2s.gameserver.Config;
import l2s.gameserver.data.xml.holder.SkillAcquireHolder;
import l2s.gameserver.database.DatabaseFactory;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.SkillLearn;
import l2s.gameserver.model.actor.instances.player.ShortCut;
import l2s.gameserver.model.base.AcquireType;
import l2s.gameserver.model.base.ClassId;
import l2s.gameserver.model.base.Race;
import l2s.gameserver.model.items.ItemInstance;
import l2s.gameserver.tables.SkillTable;
import l2s.gameserver.templates.item.ItemGrade;
import l2s.gameserver.templates.item.ItemTemplate;
import l2s.gameserver.templates.player.PlayerTemplate;
import l2s.gameserver.utils.ItemFunctions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import blood.base.FPCItem;
import blood.dao.FakeNameDAO;
import blood.table.FPCNameTable;

public class FPCCreator
{
	// TODO - move code here
	
	private static final Logger 		_log = LoggerFactory.getLogger(FPCCreator.class);
   	
   	public static void createNewChar()
	{
		int[] _class_list = {0,10,18,25,31,38,44,49,53,123,124};
		createNewChar(_class_list[Rnd.get(_class_list.length)], FakeNameDAO.getInstance().getName(), "_fake_account");
	}
    
	public static void createNewChar(int _classId, String _name, String _account)
	{
		Connection con = null;
		PreparedStatement statement = null;
		
//		_log.info("createNewChar:"+_name);
		
		//int _classId = Integer.parseInt(wordList[1]);
		int _sex = Rnd.get(0,1);
		if(_classId == 123){
			_sex = 0;
		}
		
		if(_classId == 124){
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
