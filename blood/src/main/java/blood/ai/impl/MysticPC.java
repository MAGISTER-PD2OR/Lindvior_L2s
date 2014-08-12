package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;
import blood.ai.EventFPC;

public class MysticPC extends EventFPC
{
	public MysticPC(Player actor)
	{
		super(actor);
		
	}
	
	@Override
	protected void makeNpcBuffs()
	{
		npcBuff( 15650, 1 ); // Wizard Harmony
		super.makeNpcBuffs();
	}

	@Override
	protected boolean createNewTask()
	{
		return defaultFightTask();
	}

	@Override
	public int getRatePHYS()
	{
		return _damSkills.length == 0 ? 25 : 0;
	}

	@Override
	public int getRateDOT()
	{
		return 25;
	}

	@Override
	public int getRateDEBUFF()
	{
		return 20;
	}

	@Override
	public int getRateDAM()
	{
		return 100;
	}

	@Override
	public int getRateSTUN()
	{
		return 10;
	}

	@Override
	public int getRateBUFF()
	{
		return 10;
	}

	@Override
	public int getRateHEAL()
	{
		return 0;
	}
	
	@Override
	public List<Integer> getEventBuffList()
	{
		List<Integer> buffs = new ArrayList<Integer>();
//		buffs.add(275); 		//Dances - Dance of Fury
//		buffs.add(309); 		//Dances - Dance of Earth Guard
//		buffs.add(307); 		//Dances - Dance of Aqua Guard
		buffs.add(365); 		//Dances - Siren's Dance
//		buffs.add(277); 		//Dances - Dance of Light
		buffs.add(276); 		//Dances - Dance of concentration
//		buffs.add(310); 		//Dances - Dance of the Vampire
//		buffs.add(274); 		//Dances - Dance of Fire
		buffs.add(273); 		//Dances - Dance of the Mystic
//		buffs.add(272); 		//Dances - Dance of Inspiration
//		buffs.add(271); 		//Dances - Dance of the Warrior
//		buffs.add(530); 		//Dances - Dance of Alignment
//		buffs.add(1182); 		//Elder - Resist Aqua
//		buffs.add(1354); 		//Elder - Arcane Protection
		buffs.add(1087); 		//Elder - Agility
//		buffs.add(1353); 		//Elder - Divine Protection
//		buffs.add(1352); 		//Elder - Elemental Protection
		buffs.add(1259); 		//Elder - Resist Shock
//		buffs.add(1073); 		//Elder - Kiss of Eva
//		buffs.add(1304); 		//Elder - Advanced Block
		buffs.add(1355); 		//Elder - Prophecy of Water
//		buffs.add(1460); 		//Elder - Mana Gain
		buffs.add(1397); 		//Elder - Clarity
//		buffs.add(1393); 		//Elder - Unholy Resistance
//		buffs.add(1044); 		//Prophet - Regeneration
		buffs.add(1045); 		//Prophet - Blessed Body
		buffs.add(1048); 		//Prophet - Blessed Soul
		buffs.add(1062); 		//Prophet - Berserker Spirit
//		buffs.add(1068); 		//Prophet - Might
		buffs.add(1085); 		//Prophet - Acumen
//		buffs.add(1086); 		//Prophet - Haste
//		buffs.add(1191); 		//Prophet - Resist Fire
		buffs.add(1204); 		//Prophet - Wind Walk
//		buffs.add(1043); 		//Prophet - Holy Weapon
		buffs.add(1040); 		//Prophet - Shield
		buffs.add(1036); 		//Prophet - Magic Barrier
//		buffs.add(1356); 		//Prophet - Prophecy of Fire
//		buffs.add(1035); 		//Prophet - Mental Shield
//		buffs.add(1392); 		//Prophet - Holy Resistance
//		buffs.add(1240); 		//Prophet - Guidance
//		buffs.add(1243); 		//Prophet - Bless Shield
//		buffs.add(1033); 		//Prophet - Resist Poison
//		buffs.add(1032); 		//Prophet - Invigor
		buffs.add(1303); 		//ShillenElder - Wild Magic
//		buffs.add(1357); 		//ShillenElder - Prophecy of Wind
//		buffs.add(1189); 		//ShillenElder - Resist Wind
//		buffs.add(1388); 		//ShillenElder - Greater Might
//		buffs.add(1268); 		//ShillenElder - Vampiric Rage
		buffs.add(1389); 		//ShillenElder - Greater Shield
		buffs.add(1059); 		//ShillenElder - Empower
//		buffs.add(1077); 		//ShillenElder - Focus
//		buffs.add(1242); 		//ShillenElder - Death Whisper
		buffs.add(1078); 		//ShillenElder - Concentration
//		buffs.add(264); 		//Songs - Song of Earth
//		buffs.add(308); 		//Songs - Song of Storm Guard
//		buffs.add(306); 		//Songs - Song of Flame Guard
//		buffs.add(305); 		//Songs - Song of Vengeance
		buffs.add(304); 		//Songs - Song of Vitality
//		buffs.add(270); 		//Songs - Song of Invocation
//		buffs.add(269); 		//Songs - Song of Hunter
		buffs.add(268); 		//Songs - Song of Wind
		buffs.add(267); 		//Songs - Song of Warding
//		buffs.add(266); 		//Songs - Song of Water
		buffs.add(349); 		//Songs - Song of Renewal
		buffs.add(363); 		//Songs - Song of Meditation
//		buffs.add(364); 		//Songs - Song of Champion
//		buffs.add(529); 		//Songs - Song of Elemental
//		buffs.add(4702); 		//Summon - Blessing of Seraphim
//		buffs.add(4700); 		//Summon - Gift of Queen
//		buffs.add(4699); 		//Summon - Blessing of Queen
		buffs.add(4703); 		//Summon - Gift of Seraphim
//		buffs.add(1007); 		//WarCryer - Chant of Battle
		buffs.add(1413); 		//WarCryer - Magnus Chant
//		buffs.add(1009); 		//WarCryer - Chant of Shielding
//		buffs.add(1006); 		//WarCryer - Chant of Fire
//		buffs.add(1284); 		//WarCryer - Chant of Revenge
//		buffs.add(1391); 		//WarCryer - Earth Chant
//		buffs.add(1002); 		//WarCryer - Flame Chant
//		buffs.add(1363); 		//WarCryer - Chant of Victory
//		buffs.add(1362); 		//WarCryer - Chant of Spirit
//		buffs.add(1308); 		//WarCryer - Chant of Predator
//		buffs.add(1309); 		//WarCryer - Chant of Eagle
//		buffs.add(1310); 		//WarCryer - Chant of Vampire
//		buffs.add(1390); 		//WarCryer - War Chant
		return buffs;
	}
}