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
import blood.table.FPCNameTable;

public class FPCCreator
{
	// TODO - move code here
	
	private static final Logger 		_log = LoggerFactory.getLogger(FPCCreator.class);
	
   	@SuppressWarnings("unused")
	private static int[] _black_ore 	= {926, 864, 864, 895, 895};
   	private static int[] _majestic_ring = {924, 862, 862, 893, 893};
   	
   	
   	public static void createNewChar()
	{
		int[] _class_list = {2,3,5,6,8,9,12,13,14,20,21,23,24,27,33,34,36,40,41,46,48,51,52,55,57,127,130,129,128}; // no ES id 28, prophet id 17, no healer id 16, 30, 43
		createNewChar(_class_list[Rnd.get(_class_list.length)], FPCNameTable.getRandomName(), "_fake_account");
	}
    
    @SuppressWarnings("unused")
	public static void createNewChar(int _classId, String _name, String _account)
	{
		Connection con = null;
		PreparedStatement statement = null;
		
		//int _classId = Integer.parseInt(wordList[1]);
		int _sex = Rnd.get(0,1);
		int _hairStyle = Rnd.get(0, _sex == 1 ? 6 : 4);
		int _hairColor = Rnd.get(0,2);
		int _face = Rnd.get(0,2);
		
		if(_classId == 128 || _classId == 127){
			_sex = 0;
		}
		
		if(_classId == 129 || _classId == 130){
			_sex = 1;
		}
		
		//String _account = wordList.length == 3 ? wordList[2] : "_mylove1412";
		
		Player newChar = Player.create(_classId, _sex, _account, _name, _hairStyle, _hairColor, _face);
		if(newChar.getRace() == Race.KAMAEL && newChar.getHairStyle() > 3)
		{
			newChar.setHairStyle(Rnd.get(2)+1);
		}
		if(newChar == null)
			return;
		
		
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			statement = con.prepareStatement("INSERT INTO fake_players(obj_id) VALUES (?)");
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
		
		_log.info("Create NewChar:"+_name+" in Account: "+_account+" Class: " + newChar.getClassId() + " Sex: " + _sex);
		
		int _obj_id = newChar.getObjectId();
		
		initNewChar(newChar);
		
		new FPCInfo(_obj_id);
		
	}
	
	public static void initNewChar(Player newChar)
	{
		PlayerTemplate template = newChar.getTemplate();

		newChar.getSubClassList().restore();

//		if(Config.STARTING_ADENA > 0)
//			newChar.addAdena(Config.STARTING_ADENA);
//		
//		if(Config.STARTING_LEVEL != 0)
//			newChar.addExpAndSp(Experience.LEVEL[Config.STARTING_LEVEL] - newChar.getExp(), 0, 0, 0, false, false);

//        if (Config.SPAWN_CHAR)
//            newChar.teleToLocation(Config.SPAWN_X + Rnd.get(-750, 750), Config.SPAWN_Y + Rnd.get(-750, 750), Config.SPAWN_Z);
//           else
           	newChar.setLoc(template.getStartLocation());

		if(Config.CHAR_TITLE)
			newChar.setTitle(Config.ADD_CHAR_TITLE);
		else
			newChar.setTitle("");

//		if(Config.SERVICES_RATE_TYPE != Bonus.NO_BONUS && Config.SERVICES_RATE_CREATE_PA != 0 && newChar.getBonus() == null)
//		{
//			newChar.getBonus().setBonusExpire((int)(System.currentTimeMillis() / 1000L * (60 * 60 * 24 *  Config.SERVICES_RATE_CREATE_PA)));
//			newChar.stopBonusTask();
//			newChar.startBonusTask();
//		}
		
		newChar.setHeading(Rnd.get(0, 90000));
		
		/*
		for(CreateItem i : template.getItems())
		{
			ItemInstance item = ItemFunctions.createItem(i.getItemId());
			newChar.getInventory().addItem(item);

			if(i.getShortcut() - 1 > -1) // tutorial book
				newChar.registerShortCut(new ShortCut(Math.min(i.getShortcut() - 1, 11), 0, ShortCut.TYPE_ITEM, item.getObjectId(), -1, 1));

			if(i.isEquipable() && item.isEquipable() && (newChar.getActiveWeaponItem() == null || item.getTemplate().getType2() != ItemTemplate.TYPE2_WEAPON))
				newChar.getInventory().equipItem(item);
		}
		*/
		
		

		ClassId nclassId = newChar.getClassId();
		
		// black ore
		for(int i: _majestic_ring){
			ItemInstance item = ItemFunctions.createItem(i);
			newChar.getInventory().addItem(item);
			if(item.isEquipable() && (newChar.getActiveWeaponInstance() == null || item.getTemplate().getType2() != ItemTemplate.TYPE2_WEAPON))
				newChar.getInventory().equipItem(item);
		}
		
		// armor and weapon
		List<Integer> doll_set = FPCItem.getWeaponAndArmorAndRing(nclassId, ItemGrade.B, ItemGrade.B, ItemGrade.A);
		
		for(int i: doll_set)
		{
			ItemInstance item = ItemFunctions.createItem(i);
			newChar.getInventory().addItem(item);
			if(item.isEquipable() && (newChar.getActiveWeaponInstance() == null || item.getTemplate().getType2() != ItemTemplate.TYPE2_WEAPON))
				newChar.getInventory().equipItem(item);
		}
		
		// bonus item
		ItemInstance item ;
		
		// Adventurer's Scroll of Escape
		item = ItemFunctions.createItem(10650);
		item.setCount(5);
		newChar.getInventory().addItem(item);

		// Scroll of Escape: Kamael Village
		item = ItemFunctions.createItem(9716);
		item.setCount(10);
		newChar.getInventory().addItem(item);

		for(SkillLearn skill : SkillAcquireHolder.getInstance().getAvailableSkills(newChar, AcquireType.NORMAL))
			newChar.addSkill(SkillTable.getInstance().getInfo(skill.getId(), skill.getLevel()), true);

		if(newChar.getSkillLevel(1001) > 0) // Soul Cry
			newChar.registerShortCut(new ShortCut(1, 0, ShortCut.TYPE_SKILL, 1001, 1, 1));
		if(newChar.getSkillLevel(1177) > 0) // Wind Strike
			newChar.registerShortCut(new ShortCut(1, 0, ShortCut.TYPE_SKILL, 1177, 1, 1));
		if(newChar.getSkillLevel(1216) > 0) // Self Heal
			newChar.registerShortCut(new ShortCut(2, 0, ShortCut.TYPE_SKILL, 1216, 1, 1));

		// add attack, take, sit shortcut
		newChar.registerShortCut(new ShortCut(0, 0, ShortCut.TYPE_ACTION, 2, -1, 1));
		newChar.registerShortCut(new ShortCut(3, 0, ShortCut.TYPE_ACTION, 5, -1, 1));
		newChar.registerShortCut(new ShortCut(10, 0, ShortCut.TYPE_ACTION, 0, -1, 1));
		// понял как на панельке отобразить. нц софт 10-11 панели сделали(by VISTALL)
		// fly transform
		newChar.registerShortCut(new ShortCut(0, ShortCut.PAGE_FLY_TRANSFORM, ShortCut.TYPE_SKILL, 911, 1, 1));
		newChar.registerShortCut(new ShortCut(3, ShortCut.PAGE_FLY_TRANSFORM, ShortCut.TYPE_SKILL, 884, 1, 1));
		newChar.registerShortCut(new ShortCut(4, ShortCut.PAGE_FLY_TRANSFORM, ShortCut.TYPE_SKILL, 885, 1, 1));
		// air ship
		newChar.registerShortCut(new ShortCut(0, ShortCut.PAGE_AIRSHIP, ShortCut.TYPE_ACTION, 70, 0, 1));

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
