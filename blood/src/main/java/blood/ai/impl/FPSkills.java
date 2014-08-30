package blood.ai.impl;

public class FPSkills {
	public class HumanFigter {
		public static final int
		//======= Start Skill list of HUMAN_FIGHTER =======
		SKILL_POWER_STRIKE							= 3, // Lv.9
		SKILL_MORTAL_BLOW							= 16, // Lv.9
		SKILL_POWER_SHOT							= 56, // Lv.9
		SKILL_RELAX									= 226, // Lv.1
		//======= End Skill list of HUMAN_FIGHTER =======
		SKILL_DUMMY = 1;
	}
	
	public class Warrior extends HumanFigter{
		public static final int
		//======= Start Skill list of WARRIOR =======
		SKILL_DETECT_INSECT_WEAKNESS				= 75, // Lv.1
		SKILL_WAR_CRY								= 78, // Lv.1
		SKILL_STUN_ATTACK							= 100, // Lv.15
		SKILL_BATTLE_ROAR							= 121, // Lv.1
		SKILL_WILD_SWEEP							= 245, // Lv.15
		SKILL_POWER_SMASH							= 255, // Lv.15
		SKILL_ACCURACY								= 256, // Lv.1
		SKILL_LIONHEART								= 287, // Lv.1
		SKILL_VICIOUS_STANCE						= 312, // Lv.5
		//======= End Skill list of WARRIOR =======
		SKILL_DUMMY = 1;
	}
	
	public class Gladiator extends Warrior{
		public static final int
		//======= Start Skill list of GLADIATOR =======
		SKILL_TRIPLE_SLASH							= 1, // Lv.37
		SKILL_DOUBLE_SONIC_SLASH					= 5, // Lv.31
		SKILL_SONIC_BLASTER							= 6, // Lv.37
		SKILL_SONIC_STORM							= 7, // Lv.28
		SKILL_SONIC_FOCUS							= 8, // Lv.7
		SKILL_SONIC_BUSTER							= 9, // Lv.34
		SKILL_WAR_CRY								= 78, // Lv.2
		SKILL_DETECT_BEAST_WEAKNESS					= 80, // Lv.1
		SKILL_DETECT_ANIMAL_WEAKNESS				= 87, // Lv.1
		SKILL_DETECT_DRAGON_WEAKNESS				= 88, // Lv.1
		SKILL_DETECT_PLANT_WEAKNESS					= 104, // Lv.1
		SKILL_FATAL_STRIKE							= 190, // Lv.37
		SKILL_HAMMER_CRUSH							= 260, // Lv.37
		SKILL_TRIPLE_SONIC_SLASH					= 261, // Lv.22
		SKILL_LIONHEART								= 287, // Lv.3
		SKILL_DUELIST_SPIRIT						= 297, // Lv.2
		SKILL_VICIOUS_STANCE						= 312, // Lv.20
		SKILL_WAR_FRENZY							= 424, // Lv.3
		SKILL_SONIC_MOVE							= 451, // Lv.2
		SKILL_RUSH									= 994, // Lv.1
		//======= End Skill list of GLADIATOR =======
		SKILL_DUMMY = 1;
	}
	
	public class Warlord extends Warrior{
		public static final int
		//======= Start Skill list of WARLORD =======
		SKILL_WHIRLWIND								= 36, // Lv.37
		SKILL_THUNDER_STORM							= 48, // Lv.37
		SKILL_DETECT_BEAST_WEAKNESS					= 80, // Lv.1
		SKILL_DETECT_ANIMAL_WEAKNESS				= 87, // Lv.1
		SKILL_DETECT_DRAGON_WEAKNESS				= 88, // Lv.1
		SKILL_DETECT_PLANT_WEAKNESS					= 104, // Lv.1
		SKILL_HOWL									= 116, // Lv.14
		SKILL_BATTLE_ROAR							= 121, // Lv.6
		SKILL_THRILL_FIGHT							= 130, // Lv.2
		SKILL_REVIVAL								= 181, // Lv.1
		SKILL_PROVOKE								= 286, // Lv.3
		SKILL_LIONHEART								= 287, // Lv.3
		SKILL_VICIOUS_STANCE						= 312, // Lv.20
		SKILL_FOCUS_ATTACK							= 317, // Lv.5
		SKILL_WRATH									= 320, // Lv.10
		SKILL_FELL_SWOOP							= 421, // Lv.5
		SKILL_POLEARM_ACCURACY						= 422, // Lv.3
		SKILL_WAR_FRENZY							= 424, // Lv.3
		SKILL_SHOCK_STOMP							= 452, // Lv.5
		SKILL_POWER_CRUSH							= 920, // Lv.37
		SKILL_RUSH									= 994, // Lv.1
		//======= End Skill list of WARLORD =======
		SKILL_DUMMY = 1;
	}
	
	public class Knight extends HumanFigter {
		public static final int
		//======= Start Skill list of KNIGHT =======
		SKILL_AGGRESSION							= 28, // Lv.12
		SKILL_DIVINE_HEAL							= 45, // Lv.9
		SKILL_DRAIN_HEALTH							= 70, // Lv.13
		SKILL_MAJESTY								= 82, // Lv.1
		SKILL_SHIELD_STUN							= 92, // Lv.15
		SKILL_ULTIMATE_DEFENSE						= 110, // Lv.1
		SKILL_DEFLECT_ARROW							= 112, // Lv.2
		//======= End Skill list of KNIGHT =======
		SKILL_DUMMY = 1;
	}
	
	public class Paladin extends Knight{
		public static final int
		//======= Start Skill list of PALADIN =======
		SKILL_AURA_OF_HATE							= 18, // Lv.37
		SKILL_AGGRESSION							= 28, // Lv.49
		SKILL_REMEDY								= 44, // Lv.3
		SKILL_HOLY_STRIKE							= 49, // Lv.26
		SKILL_SACRIFICE								= 69, // Lv.25
		SKILL_IRON_WILL								= 72, // Lv.3
		SKILL_MAJESTY								= 82, // Lv.3
		SKILL_SHIELD_STUN							= 92, // Lv.52
		SKILL_SANCTUARY								= 97, // Lv.11
		SKILL_ULTIMATE_DEFENSE						= 110, // Lv.2
		SKILL_DEFLECT_ARROW							= 112, // Lv.4
		SKILL_HOLY_BLADE							= 196, // Lv.1
		SKILL_HOLY_ARMOR							= 197, // Lv.2
		SKILL_HOLY_BLESSING							= 262, // Lv.37
		SKILL_AEGIS_STANCE							= 318, // Lv.1
		SKILL_SHIELD_FORTRESS						= 322, // Lv.6
		SKILL_TRIBUNAL								= 400, // Lv.10
		SKILL_SHACKLE								= 403, // Lv.10
		SKILL_MASS_SHACKLING						= 404, // Lv.5
		SKILL_BANISH_UNDEAD							= 405, // Lv.10
		SKILL_ANGELIC_ICON							= 406, // Lv.3
		SKILL_VANGUARD								= 810, // Lv.1
		SKILL_SHIELD_DEFLECT_MAGIC					= 916, // Lv.4
		SKILL_COMBAT_AURA							= 982, // Lv.3
		SKILL_SHIELD_STRIKE							= 984, // Lv.15
		//======= End Skill list of PALADIN =======
		SKILL_DUMMY = 1;
	}
	
	public class DardAvenger extends Knight{
		public static final int
		//======= Start Skill list of DARK_AVENGER =======
		SKILL_AURA_OF_HATE							= 18, // Lv.37
		SKILL_AGGRESSION							= 28, // Lv.49
		SKILL_LIFE_SCAVENGE							= 46, // Lv.15
		SKILL_HORROR								= 65, // Lv.13
		SKILL_DRAIN_HEALTH							= 70, // Lv.53
		SKILL_IRON_WILL								= 72, // Lv.3
		SKILL_MAJESTY								= 82, // Lv.3
		SKILL_REFLECT_DAMAGE						= 86, // Lv.3
		SKILL_SHIELD_STUN							= 92, // Lv.52
		SKILL_CORPSE_PLAGUE							= 103, // Lv.4
		SKILL_ULTIMATE_DEFENSE						= 110, // Lv.2
		SKILL_DEFLECT_ARROW							= 112, // Lv.4
		SKILL_HAMSTRING								= 127, // Lv.14
		SKILL_SUMMON_DARK_PANTHER					= 283, // Lv.7
		SKILL_AEGIS_STANCE							= 318, // Lv.1
		SKILL_SHIELD_FORTRESS						= 322, // Lv.6
		SKILL_JUDGMENT								= 401, // Lv.10
		SKILL_SHACKLE								= 403, // Lv.10
		SKILL_BANISH_SERAPH							= 450, // Lv.10
		SKILL_VANGUARD								= 811, // Lv.1
		SKILL_SHIELD_DEFLECT_MAGIC					= 916, // Lv.4
		SKILL_COMBAT_AURA							= 982, // Lv.3
		SKILL_SHIELD_STRIKE							= 984, // Lv.15
		//======= End Skill list of DARK_AVENGER =======
		SKILL_DUMMY = 1;
	}
	
	public class Rogue extends HumanFigter{
		public static final int
		//======= Start Skill list of ROGUE =======
		SKILL_DASH									= 4, // Lv.1
		SKILL_MORTAL_BLOW							= 16, // Lv.24
		SKILL_UNLOCK								= 27, // Lv.5
		SKILL_POWER_SHOT							= 56, // Lv.24
		SKILL_BLEED									= 96, // Lv.2
		SKILL_RAPID_SHOT							= 99, // Lv.1
		SKILL_STUNNING_SHOT							= 101, // Lv.3
		SKILL_ULTIMATE_EVASION						= 111, // Lv.1
		SKILL_ACCURACY								= 256, // Lv.1
		SKILL_VICIOUS_STANCE						= 312, // Lv.5
		//======= End Skill list of ROGUE =======
		SKILL_DUMMY = 1;
	}
	
	public class TreasureHunter extends Rogue {
		public static final int
		//======= Start Skill list of TREASURE_HUNTER =======
		SKILL_DASH									= 4, // Lv.2
		SKILL_TRICK									= 11, // Lv.12
		SKILL_SWITCH								= 12, // Lv.14
		SKILL_UNLOCK								= 27, // Lv.14
		SKILL_BACKSTAB								= 30, // Lv.37
		SKILL_LURE									= 51, // Lv.1
		SKILL_FAKE_DEATH							= 60, // Lv.1
		SKILL_BLEED									= 96, // Lv.6
		SKILL_VEIL									= 106, // Lv.14
		SKILL_ULTIMATE_EVASION						= 111, // Lv.2
		SKILL_SILENT_MOVE							= 221, // Lv.1
		SKILL_DEADLY_BLOW							= 263, // Lv.37
		SKILL_VICIOUS_STANCE						= 312, // Lv.20
		SKILL_CRITICAL_BLOW							= 409, // Lv.10
		SKILL_STEALTH								= 411, // Lv.3
		SKILL_SAND_BOMB								= 412, // Lv.10
		SKILL_SUMMON_TREASURE_KEY					= 419, // Lv.4
		SKILL_ESCAPE_SHACKLE						= 453, // Lv.1
		SKILL_FIND_TRAP								= 623, // Lv.1
		SKILL_REMOVE_TRAP							= 624, // Lv.1
		SKILL_SHADOW_STEP							= 821, // Lv.1
		//======= End Skill list of TREASURE_HUNTER =======
		SKILL_DUMMY = 1;
	}
	
	public class Haweye extends Rogue {
		public static final int
		//======= Start Skill list of HAWKEYE =======
		SKILL_DOUBLE_SHOT							= 19, // Lv.37
		SKILL_BURST_SHOT							= 24, // Lv.31
		SKILL_RAPID_SHOT							= 99, // Lv.2
		SKILL_STUNNING_SHOT							= 101, // Lv.40
		SKILL_HAWK_EYE								= 131, // Lv.3
		SKILL_SOUL_OF_SAGITTARIUS					= 303, // Lv.4
		SKILL_VICIOUS_STANCE						= 312, // Lv.20
		SKILL_SNIPE									= 313, // Lv.8
		SKILL_QUIVER_OF_ARROW_GRADE_A				= 323, // Lv.1
		SKILL_QUIVER_OF_ARROW_GRADE_S				= 324, // Lv.1
		SKILL_SPIRIT_OF_SAGITTARIUS					= 415, // Lv.3
		SKILL_BLESSING_OF_SAGITTARIUS				= 416, // Lv.3
		SKILL_PAIN_OF_SAGITTARIUS					= 417, // Lv.5
		SKILL_DETECTION								= 933, // Lv.1
		//======= End Skill list of HAWKEYE =======
		SKILL_DUMMY = 1;
	}
	
	public class HumanMage {
		public static final int
		//======= Start Skill list of HUMAN_MAGE =======
		SKILL_HEAL									= 1011, // Lv.6
		SKILL_CURE_POISON							= 1012, // Lv.1
		SKILL_BATTLE_HEAL							= 1015, // Lv.3
		SKILL_GROUP_HEAL							= 1027, // Lv.3
		SKILL_SHIELD								= 1040, // Lv.1
		SKILL_MIGHT									= 1068, // Lv.1
		SKILL_VAMPIRIC_TOUCH						= 1147, // Lv.2
		SKILL_CURSE_WEAKNESS						= 1164, // Lv.1
		SKILL_CURSE_POISON							= 1168, // Lv.1
		SKILL_WIND_STRIKE							= 1177, // Lv.5
		SKILL_ICE_BOLT								= 1184, // Lv.4
		//======= End Skill list of HUMAN_MAGE =======
		SKILL_DUMMY = 1;
	}
	
	public class Wizzard extends HumanMage {
		public static final int
		//======= Start Skill list of WIZARD =======
		SKILL_SLEEP									= 1069, // Lv.9
		SKILL_CONCENTRATION							= 1078, // Lv.2
		SKILL_SURRENDER_TO_FIRE						= 1083, // Lv.3
		SKILL_SUMMON_KAT_THE_CAT					= 1111, // Lv.4
		SKILL_SERVITOR_RECHARGE						= 1126, // Lv.6
		SKILL_SERVITOR_HEAL							= 1127, // Lv.12
		SKILL_SERVITOR_WIND_WALK					= 1144, // Lv.1
		SKILL_VAMPIRIC_TOUCH						= 1147, // Lv.6
		SKILL_CORPSE_LIFE_DRAIN						= 1151, // Lv.2
		SKILL_BODY_TO_MIND							= 1157, // Lv.1
		SKILL_SLOW									= 1160, // Lv.1
		SKILL_CURSE_WEAKNESS						= 1164, // Lv.5
		SKILL_POISONOUS_CLOUD						= 1167, // Lv.2
		SKILL_CURSE_POISON							= 1168, // Lv.3
		SKILL_AURA_BURN								= 1172, // Lv.8
		SKILL_FLAME_STRIKE							= 1181, // Lv.3
		SKILL_ICE_BOLT								= 1184, // Lv.6
		SKILL_BLAZE									= 1220, // Lv.8
		SKILL_CURSE_CHAOS							= 1222, // Lv.1
		SKILL_SUMMON_MEW_THE_CAT					= 1225, // Lv.4
		SKILL_ENERGY_BOLT							= 1274, // Lv.4
		//======= End Skill list of WIZARD =======
		SKILL_DUMMY = 1;
	}
	
	public class Sorcerer extends Wizzard {
		public static final int
		//======= Start Skill list of SORCERER =======
		SKILL_CANCELLATION							= 1056, // Lv.12
		SKILL_SLEEP									= 1069, // Lv.42
		SKILL_SLEEPING_CLOUD						= 1072, // Lv.5
		SKILL_SURRENDER_TO_WIND						= 1074, // Lv.14
		SKILL_CONCENTRATION							= 1078, // Lv.6
		SKILL_SURRENDER_TO_FIRE						= 1083, // Lv.17
		SKILL_SLOW									= 1160, // Lv.15
		SKILL_CURSE_FEAR							= 1169, // Lv.14
		SKILL_BLAZING_CIRCLE						= 1171, // Lv.19
		SKILL_PROMINENCE							= 1230, // Lv.28
		SKILL_AURA_FLARE							= 1231, // Lv.28
		SKILL_BLAZING_SKIN							= 1232, // Lv.3
		SKILL_DECAY									= 1233, // Lv.4
		SKILL_AURA_BOLT								= 1275, // Lv.14
		SKILL_SEED_OF_FIRE							= 1285, // Lv.1
		SKILL_AURA_SYMPHONY							= 1288, // Lv.1
		SKILL_INFERNO								= 1289, // Lv.1
		SKILL_ELEMENTAL_ASSAULT						= 1292, // Lv.1
		SKILL_RAIN_OF_FIRE							= 1296, // Lv.9
		SKILL_AURA_FLASH							= 1417, // Lv.5
		//======= End Skill list of SORCERER =======
		SKILL_DUMMY = 1;
	}

	public class Necromancer extends Wizzard {
		public static final int
		//======= Start Skill list of NECROMANCER =======
		SKILL_SILENCE								= 1064, // Lv.14
		SKILL_SLEEP									= 1069, // Lv.42
		SKILL_SUMMON_REANIMATED_MAN					= 1129, // Lv.7
		SKILL_DEATH_SPIKE							= 1148, // Lv.13
		SKILL_CORPSE_LIFE_DRAIN						= 1151, // Lv.16
		SKILL_SUMMON_CORRUPTED_MAN					= 1154, // Lv.6
		SKILL_CORPSE_BURST							= 1155, // Lv.15
		SKILL_FORGET								= 1156, // Lv.13
		SKILL_BODY_TO_MIND							= 1157, // Lv.5
		SKILL_CURSE_DEATH_LINK						= 1159, // Lv.22
		SKILL_CURSE_DISCORD							= 1163, // Lv.14
		SKILL_CURSE_WEAKNESS						= 1164, // Lv.19
		SKILL_POISONOUS_CLOUD						= 1167, // Lv.6
		SKILL_CURSE_POISON							= 1168, // Lv.7
		SKILL_CURSE_FEAR							= 1169, // Lv.14
		SKILL_ANCHOR								= 1170, // Lv.13
		SKILL_CURSE_CHAOS							= 1222, // Lv.15
		SKILL_VAMPIRIC_CLAW							= 1234, // Lv.28
		SKILL_TRANSFER_PAIN							= 1262, // Lv.5
		SKILL_CURSE_GLOOM							= 1263, // Lv.13
		SKILL_CURSE_DISEASE							= 1269, // Lv.9
		SKILL_MASS_SLOW								= 1298, // Lv.14
		SKILL_SUMMON_CURSED_MAN						= 1334, // Lv.7
		SKILL_MASS_FEAR								= 1381, // Lv.5
		SKILL_MASS_GLOOM							= 1382, // Lv.5
		//======= End Skill list of NECROMANCER =======
		SKILL_DUMMY = 1;
	}
	
	public class Warlock extends Wizzard {
		public static final int
		//======= Start Skill list of WARLOCK =======
		SKILL_SUMMON_STORM_CUBIC					= 10, // Lv.8
		SKILL_SUMMON_KAT_THE_CAT					= 1111, // Lv.18
		SKILL_SERVITOR_RECHARGE						= 1126, // Lv.34
		SKILL_SERVITOR_HEAL							= 1127, // Lv.45
		SKILL_SERVITOR_MAGIC_SHIELD					= 1139, // Lv.2
		SKILL_SERVITOR_PHYSICAL_SHIELD				= 1140, // Lv.3
		SKILL_SERVITOR_HASTE						= 1141, // Lv.2
		SKILL_SERVITOR_WIND_WALK					= 1144, // Lv.2
		SKILL_SUMMON_MEW_THE_CAT					= 1225, // Lv.18
		SKILL_TRANSFER_PAIN							= 1262, // Lv.5
		SKILL_SUMMON_KAI_THE_CAT					= 1276, // Lv.14
		SKILL_SUMMON_BINDING_CUBIC					= 1279, // Lv.9
		SKILL_SERVITOR_EMPOWERMENT					= 1299, // Lv.2
		SKILL_SERVITOR_CURE							= 1300, // Lv.3
		SKILL_SERVITOR_BLESSING						= 1301, // Lv.1
		SKILL_MASS_SUMMON_STORM_CUBIC				= 1328, // Lv.8
		SKILL_SUMMON_FELINE_QUEEN					= 1331, // Lv.10
		SKILL_BETRAY								= 1380, // Lv.10
		SKILL_MASS_SURRENDER_TO_FIRE				= 1383, // Lv.5
		SKILL_ARCANE_DISRUPTION						= 1386, // Lv.10
		SKILL_SUMMON_FRIEND							= 1403, // Lv.1
		SKILL_SPIRIT_SHARING						= 1547, // Lv.3
		SKILL_DIMENSION_SPIRAL						= 1558, // Lv.14
		//======= End Skill list of WARLOCK =======
		SKILL_DUMMY = 1;
	}

	public class Cleric extends HumanMage {
		public static final int
		//======= Start Skill list of CLERIC =======
		SKILL_HEAL									= 1011, // Lv.18
		SKILL_CURE_POISON							= 1012, // Lv.2
		SKILL_BATTLE_HEAL							= 1015, // Lv.15
		SKILL_RESURRECTION							= 1016, // Lv.2
		SKILL_GROUP_HEAL							= 1027, // Lv.15
		SKILL_DISRUPT_UNDEAD						= 1031, // Lv.8
		SKILL_MENTAL_SHIELD							= 1035, // Lv.1
		SKILL_SHIELD								= 1040, // Lv.2
		SKILL_HOLY_WEAPON							= 1043, // Lv.1
		SKILL_REGENERATION							= 1044, // Lv.1
		SKILL_BERSERKER_SPIRIT						= 1062, // Lv.1
		SKILL_MIGHT									= 1068, // Lv.2
		SKILL_SLEEP									= 1069, // Lv.9
		SKILL_KISS_OF_EVA							= 1073, // Lv.1
		SKILL_PEACE									= 1075, // Lv.1
		SKILL_FOCUS									= 1077, // Lv.1
		SKILL_CONCENTRATION							= 1078, // Lv.2
		SKILL_ACUMEN								= 1085, // Lv.2
		SKILL_RESIST_FIRE							= 1191, // Lv.1
		SKILL_DRYAD_ROOT							= 1201, // Lv.9
		SKILL_WIND_WALK								= 1204, // Lv.2
		//======= End Skill list of CLERIC =======
		SKILL_DUMMY = 1;
	}

	public class Bishop extends Cleric {
		public static final int
		//======= Start Skill list of BISHOP =======
		SKILL_CURE_POISON							= 1012, // Lv.3
		SKILL_RESURRECTION							= 1016, // Lv.9
		SKILL_PURIFY								= 1018, // Lv.3
		SKILL_VITALIZE								= 1020, // Lv.27
		SKILL_MIGHT_OF_HEAVEN						= 1028, // Lv.19
		SKILL_REPOSE								= 1034, // Lv.13
		SKILL_HOLD_UNDEAD							= 1042, // Lv.12
		SKILL_REQUIEM								= 1049, // Lv.14
		SKILL_SLEEP									= 1069, // Lv.42
		SKILL_PEACE									= 1075, // Lv.15
		SKILL_GREATER_HEAL							= 1217, // Lv.33
		SKILL_GREATER_BATTLE_HEAL					= 1218, // Lv.33
		SKILL_GREATER_GROUP_HEAL					= 1219, // Lv.33
		SKILL_MASS_RESURRECTION						= 1254, // Lv.6
		SKILL_RESTORE_LIFE							= 1258, // Lv.4
		SKILL_BENEDICTION							= 1271, // Lv.1
		SKILL_PRAYER								= 1307, // Lv.3
		SKILL_BODY_OF_AVATAR						= 1311, // Lv.6
		SKILL_TRANCE								= 1394, // Lv.10
		SKILL_ERASE									= 1395, // Lv.10
		SKILL_MAGICAL_BACKFIRE						= 1396, // Lv.10
		SKILL_MANA_BURN								= 1398, // Lv.10
		SKILL_MANA_STORM							= 1399, // Lv.5
		SKILL_TURN_UNDEAD							= 1400, // Lv.10
		SKILL_MAJOR_HEAL							= 1401, // Lv.11
		SKILL_MAJOR_GROUP_HEAL						= 1402, // Lv.5
		SKILL_CELESTIAL_SHIELD						= 1418, // Lv.1
		SKILL_INVOCATION							= 1430, // Lv.5
		SKILL_DIVINE_PUNISHMENT						= 1523, // Lv.13
		SKILL_SURRENDER_TO_THE_HOLY					= 1524, // Lv.42
		SKILL_DIVINE_CURSE							= 1525, // Lv.13
		SKILL_DIVINE_FLASH							= 1528, // Lv.13
		//======= End Skill list of BISHOP =======
		SKILL_DUMMY = 1;
	}
	
	public class Prophet extends Cleric {
		public static final int
		//======= Start Skill list of PROPHET =======
		SKILL_INVIGOR								= 1032, // Lv.3
		SKILL_RESIST_POISON							= 1033, // Lv.3
		SKILL_MENTAL_SHIELD							= 1035, // Lv.4
		SKILL_MAGIC_BARRIER							= 1036, // Lv.2
		SKILL_SHIELD								= 1040, // Lv.3
		SKILL_REGENERATION							= 1044, // Lv.3
		SKILL_BLESSED_BODY							= 1045, // Lv.6
		SKILL_BLESSED_SOUL							= 1048, // Lv.6
		SKILL_RETURN								= 1050, // Lv.2
		SKILL_BERSERKER_SPIRIT						= 1062, // Lv.2
		SKILL_MIGHT									= 1068, // Lv.3
		SKILL_KISS_OF_EVA							= 1073, // Lv.2
		SKILL_FOCUS									= 1077, // Lv.3
		SKILL_CONCENTRATION							= 1078, // Lv.6
		SKILL_ACUMEN								= 1085, // Lv.3
		SKILL_HASTE									= 1086, // Lv.2
		SKILL_RESIST_AQUA							= 1182, // Lv.3
		SKILL_RESIST_WIND							= 1189, // Lv.3
		SKILL_RESIST_FIRE							= 1191, // Lv.3
		SKILL_DRYAD_ROOT							= 1201, // Lv.33
		SKILL_GUIDANCE								= 1240, // Lv.3
		SKILL_DEATH_WHISPER							= 1242, // Lv.3
		SKILL_BLESS_SHIELD							= 1243, // Lv.3
		SKILL_WORD_OF_FEAR							= 1272, // Lv.13
		SKILL_GREATER_MIGHT							= 1388, // Lv.3
		SKILL_GREATER_SHIELD						= 1389, // Lv.3
		SKILL_HOLY_RESISTANCE						= 1392, // Lv.3
		SKILL_UNHOLY_RESISTANCE						= 1393, // Lv.3
		SKILL_ERASE									= 1395, // Lv.10
		SKILL_MANA_BURN								= 1398, // Lv.10
		SKILL_IMPROVED_COMBAT						= 1499, // Lv.1
		SKILL_IMPROVED_CONDITION					= 1501, // Lv.1
		SKILL_RESIST_EARTH							= 1548, // Lv.3
		//======= End Skill list of PROPHET =======
		SKILL_DUMMY = 1;
	}	

	public class ElvenFighter {
		public static final int
		//======= Start Skill list of ElvenFighter ID:18=======
		SKILL_POWER_STRIKE							= 3, // Lv.9
		SKILL_MORTAL_BLOW							= 16, // Lv.9
		SKILL_POWER_SHOT							= 56, // Lv.9
		SKILL_ELEMENTAL_HEAL						= 58, // Lv.3
		SKILL_ATTACK_AURA							= 77, // Lv.1
		SKILL_DEFENSE_AURA							= 91, // Lv.1
		//======= End Skill list of ElvenFighter ID:18=======
		SKILL_DUMMY = 1;
	};

	public class ElvenKnight extends ElvenFighter {
		public static final int
		//======= Start Skill list of ElvenKnight ID:19=======
		SKILL_CHARM									= 15, // Lv.15
		SKILL_POISON_RECOVERY						= 21, // Lv.1
		SKILL_AGGRESSION							= 28, // Lv.12
		SKILL_ELEMENTAL_HEAL						= 58, // Lv.18
		SKILL_CURE_BLEEDING							= 61, // Lv.1
		SKILL_ATTACK_AURA							= 77, // Lv.2
		SKILL_DEFENSE_AURA							= 91, // Lv.2
		SKILL_ENTANGLE								= 102, // Lv.1
		SKILL_ULTIMATE_DEFENSE						= 110, // Lv.1
		SKILL_DEFLECT_ARROW							= 112, // Lv.2
		SKILL_SPRINT								= 230, // Lv.1
		//======= End Skill list of ElvenKnight ID:19=======
		SKILL_DUMMY = 1;
	};

	public class TempleKnight extends ElvenKnight {
		public static final int
		//======= Start Skill list of TempleKnight ID:20=======
		SKILL_SUMMON_STORM_CUBIC					= 10, // Lv.8
		SKILL_CHARM								= 15, // Lv.52
		SKILL_AURA_OF_HATE							= 18, // Lv.37
		SKILL_POISON_RECOVERY						= 21, // Lv.3
		SKILL_AGGRESSION							= 28, // Lv.49
		SKILL_ELEMENTAL_HEAL						= 58, // Lv.55
		SKILL_CURE_BLEEDING						= 61, // Lv.3
		SKILL_SUMMON_LIFE_CUBIC					= 67, // Lv.7
		SKILL_ENTANGLE								= 102, // Lv.16
		SKILL_HOLY_AURA							= 107, // Lv.9
		SKILL_ULTIMATE_DEFENSE						= 110, // Lv.2
		SKILL_DEFLECT_ARROW						= 112, // Lv.4
		SKILL_SPIRIT_BARRIER						= 123, // Lv.3
		SKILL_HOLY_ARMOR							= 197, // Lv.2
		SKILL_SPRINT								= 230, // Lv.2
		SKILL_GUARD_STANCE							= 288, // Lv.4
		SKILL_SHIELD_FORTRESS						= 322, // Lv.6
		SKILL_TRIBUNAL								= 400, // Lv.10
		SKILL_ARREST								= 402, // Lv.10
		SKILL_SUMMON_ATTRACTIVE_CUBIC				= 449, // Lv.4
		SKILL_VANGUARD								= 812, // Lv.1
		SKILL_SHIELD_DEFLECT_MAGIC					= 916, // Lv.4
		SKILL_COMBAT_AURA							= 982, // Lv.3
		SKILL_SHIELD_STRIKE						= 984, // Lv.15
		//======= End Skill list of TempleKnight ID:20=======
		SKILL_DUMMY = 1;
	};

	public class Swordsinger extends ElvenKnight {
		public static final int
		//======= Start Skill list of Swordsinger ID:21=======
		SKILL_CHARM								= 15, // Lv.52
		SKILL_POISON_RECOVERY						= 21, // Lv.3
		SKILL_ELEMENTAL_HEAL						= 58, // Lv.55
		SKILL_CURE_BLEEDING						= 61, // Lv.3
		SKILL_SWORD_SYMPHONY						= 98, // Lv.5
		SKILL_ENTANGLE								= 102, // Lv.16
		SKILL_SPIRIT_BARRIER						= 123, // Lv.3
		SKILL_HOLY_BLADE							= 196, // Lv.1
		SKILL_SPRINT								= 230, // Lv.2
		SKILL_SONG_OF_EARTH						= 264, // Lv.1
		SKILL_SONG_OF_LIFE							= 265, // Lv.1
		SKILL_SONG_OF_WATER						= 266, // Lv.1
		SKILL_SONG_OF_WARDING						= 267, // Lv.1
		SKILL_SONG_OF_WIND							= 268, // Lv.1
		SKILL_SONG_OF_HUNTER						= 269, // Lv.1
		SKILL_SONG_OF_INVOCATION					= 270, // Lv.1
		SKILL_SONG_OF_VITALITY						= 304, // Lv.1
		SKILL_SONG_OF_VENGEANCE					= 305, // Lv.1
		SKILL_SONG_OF_FLAME_GUARD					= 306, // Lv.1
		SKILL_SONG_OF_STORM_GUARD					= 308, // Lv.1
		SKILL_SONG_OF_MEDITATION					= 363, // Lv.1
		SKILL_ARREST								= 402, // Lv.10
		SKILL_PSYCHO_SYMPHONY						= 407, // Lv.10
		SKILL_DEADLY_STRIKE						= 986, // Lv.15
		SKILL_BATTLE_WHISPER						= 988, // Lv.3
		SKILL_РИТМ_КРИТИЧЕСКОГО_УДАРА				= 1586, // Lv.1
		SKILL_РИТМ_МАГИИ							= 1588, // Lv.1
		SKILL_РИТМ_СРАЖЕНИЯ						= 1590, // Lv.1
		SKILL_РИТМ_БОЙЦА							= 1592, // Lv.1
		SKILL_РИТМ_МАГА							= 1599, // Lv.1
		//======= End Skill list of Swordsinger ID:21=======
		SKILL_DUMMY = 1;
	};
	public class ElvenScout extends ElvenFighter {
		public static final int
		//======= Start Skill list of ElvenScout ID:22=======
		SKILL_CHARM								= 15, // Lv.15
		SKILL_MORTAL_BLOW							= 16, // Lv.24
		SKILL_POISON_RECOVERY						= 21, // Lv.1
		SKILL_UNLOCK								= 27, // Lv.5
		SKILL_POWER_SHOT							= 56, // Lv.24
		SKILL_ELEMENTAL_HEAL						= 58, // Lv.18
		SKILL_CURE_BLEEDING						= 61, // Lv.1
		SKILL_ATTACK_AURA							= 77, // Lv.2
		SKILL_DEFENSE_AURA							= 91, // Lv.2
		SKILL_BLEED								= 96, // Lv.2
		SKILL_RAPID_SHOT							= 99, // Lv.1
		SKILL_STUNNING_SHOT						= 101, // Lv.3
		SKILL_ENTANGLE								= 102, // Lv.1
		SKILL_ULTIMATE_EVASION						= 111, // Lv.1
		SKILL_SPRINT								= 230, // Lv.1
		SKILL_ACCURACY								= 256, // Lv.1
		SKILL_VICIOUS_STANCE						= 312, // Lv.5
		//======= End Skill list of ElvenScout ID:22=======
		SKILL_DUMMY = 1;
	};

	public class PlainWalker extends ElvenScout {
		public static final int
		//======= Start Skill list of PlainWalker ID:23=======
		SKILL_SWITCH								= 12, // Lv.14
		SKILL_CHARM								= 15, // Lv.52
		SKILL_POISON_RECOVERY						= 21, // Lv.3
		SKILL_UNLOCK								= 27, // Lv.14
		SKILL_BACKSTAB								= 30, // Lv.37
		SKILL_LURE									= 51, // Lv.1
		SKILL_ELEMENTAL_HEAL						= 58, // Lv.55
		SKILL_FAKE_DEATH							= 60, // Lv.1
		SKILL_CURE_BLEEDING						= 61, // Lv.3
		SKILL_BLEED								= 96, // Lv.6
		SKILL_ENTANGLE								= 102, // Lv.16
		SKILL_ULTIMATE_EVASION						= 111, // Lv.2
		SKILL_SPIRIT_BARRIER						= 123, // Lv.3
		SKILL_SILENT_MOVE							= 221, // Lv.1
		SKILL_SPRINT								= 230, // Lv.2
		SKILL_DEADLY_BLOW							= 263, // Lv.37
		SKILL_CHAMELEON_REST						= 296, // Lv.1
		SKILL_VICIOUS_STANCE						= 312, // Lv.20
		SKILL_BLINDING_BLOW						= 321, // Lv.10
		SKILL_MORTAL_STRIKE						= 410, // Lv.3
		SKILL_SAND_BOMB							= 412, // Lv.10
		SKILL_SUMMON_TREASURE_KEY					= 419, // Lv.4
		SKILL_ESCAPE_SHACKLE						= 453, // Lv.1
		SKILL_FIND_TRAP							= 623, // Lv.1
		SKILL_REMOVE_TRAP							= 624, // Lv.1
		SKILL_SHADOW_STEP							= 821, // Lv.1
		//======= End Skill list of PlainWalker ID:23=======
		SKILL_DUMMY = 1;
	};
	public class SilverRanger extends ElvenScout {
		public static final int
		//======= Start Skill list of SilverRanger ID:24=======
		SKILL_CHARM								= 15, // Lv.52
		SKILL_DOUBLE_SHOT							= 19, // Lv.37
		SKILL_POISON_RECOVERY						= 21, // Lv.3
		SKILL_BURST_SHOT							= 24, // Lv.31
		SKILL_ELEMENTAL_HEAL						= 58, // Lv.55
		SKILL_CURE_BLEEDING						= 61, // Lv.3
		SKILL_RAPID_SHOT							= 99, // Lv.2
		SKILL_STUNNING_SHOT						= 101, // Lv.40
		SKILL_ENTANGLE								= 102, // Lv.16
		SKILL_SPIRIT_BARRIER						= 123, // Lv.3
		SKILL_SPRINT								= 230, // Lv.2
		SKILL_SOUL_OF_SAGITTARIUS					= 303, // Lv.4
		SKILL_VICIOUS_STANCE						= 312, // Lv.20
		SKILL_QUIVER_OF_ARROW_GRADE_A				= 323, // Lv.1
		SKILL_QUIVER_OF_ARROW_GRADE_S				= 324, // Lv.1
		SKILL_RAPID_FIRE							= 413, // Lv.8
		SKILL_SPIRIT_OF_SAGITTARIUS				= 415, // Lv.3
		SKILL_BLESSING_OF_SAGITTARIUS				= 416, // Lv.3
		SKILL_DETECTION							= 933, // Lv.1
		//======= End Skill list of SilverRanger ID:24=======
		SKILL_DUMMY = 1;
	};

	public class ElvenMage {
		public static final int
		//======= Start Skill list of ElvenMage ID:25=======
		SKILL_HEAL									= 1011, // Lv.6
		SKILL_CURE_POISON							= 1012, // Lv.1
		SKILL_BATTLE_HEAL							= 1015, // Lv.3
		SKILL_GROUP_HEAL							= 1027, // Lv.3
		SKILL_SHIELD								= 1040, // Lv.1
		SKILL_MIGHT								= 1068, // Lv.1
		SKILL_CURSE_WEAKNESS						= 1164, // Lv.1
		SKILL_WIND_STRIKE							= 1177, // Lv.5
		SKILL_ICE_BOLT								= 1184, // Lv.4
		SKILL_WIND_SHACKLE							= 1206, // Lv.1
		//======= End Skill list of ElvenMage ID:25=======
		SKILL_DUMMY = 1;
	};
	public class ElvenWizard extends ElvenMage {
		public static final int
		//======= Start Skill list of ElvenWizard ID:26=======
		SKILL_SLEEP								= 1069, // Lv.9
		SKILL_CONCENTRATION						= 1078, // Lv.2
		SKILL_SERVITOR_RECHARGE					= 1126, // Lv.6
		SKILL_SERVITOR_HEAL						= 1127, // Lv.12
		SKILL_BRIGHT_SERVITOR						= 1145, // Lv.1
		SKILL_CURSE_WEAKNESS						= 1164, // Lv.5
		SKILL_AURA_BURN							= 1172, // Lv.8
		SKILL_AQUA_SWIRL							= 1175, // Lv.8
		SKILL_FLAME_STRIKE							= 1181, // Lv.3
		SKILL_RESIST_AQUA							= 1182, // Lv.1
		SKILL_ICE_BOLT								= 1184, // Lv.6
		SKILL_WIND_SHACKLE							= 1206, // Lv.5
		SKILL_SURRENDER_TO_EARTH					= 1223, // Lv.1
		SKILL_SUMMON_BOXER_THE_UNICORN				= 1226, // Lv.4
		SKILL_SUMMON_MIRAGE_THE_UNICORN			= 1227, // Lv.4
		SKILL_SOLAR_SPARK							= 1264, // Lv.3
		SKILL_ENERGY_BOLT							= 1274, // Lv.4
		//======= End Skill list of ElvenWizard ID:26=======
		SKILL_DUMMY = 1;
	};
	public class Spellsinger extends ElvenWizard {
		public static final int
		//======= Start Skill list of Spellsinger ID:27=======
		SKILL_MANA_REGENERATION					= 1047, // Lv.4
		SKILL_CANCELLATION							= 1056, // Lv.12
		SKILL_SLEEP								= 1069, // Lv.42
		SKILL_SURRENDER_TO_WATER					= 1071, // Lv.14
		SKILL_SLEEPING_CLOUD						= 1072, // Lv.5
		SKILL_CURSE_WEAKNESS						= 1164, // Lv.19
		SKILL_CURSE_FEAR							= 1169, // Lv.14
		SKILL_FROST_WALL							= 1174, // Lv.22
		SKILL_RESIST_AQUA							= 1182, // Lv.3
		SKILL_FREEZING_SHACKLE						= 1183, // Lv.4
		SKILL_SURRENDER_TO_EARTH					= 1223, // Lv.15
		SKILL_AURA_FLARE							= 1231, // Lv.28
		SKILL_HYDRO_BLAST							= 1235, // Lv.28
		SKILL_FROST_BOLT							= 1236, // Lv.19
		SKILL_ICE_DAGGER							= 1237, // Lv.17
		SKILL_FREEZING_SKIN						= 1238, // Lv.3
		SKILL_SOLAR_FLARE							= 1265, // Lv.14
		SKILL_AURA_BOLT							= 1275, // Lv.14
		SKILL_SEED_OF_WATER						= 1286, // Lv.1
		SKILL_AURA_SYMPHONY						= 1288, // Lv.1
		SKILL_BLIZZARD								= 1290, // Lv.1
		SKILL_ELEMENTAL_SYMPHONY					= 1293, // Lv.1
		SKILL_AQUA_SPLASH							= 1295, // Lv.9
		SKILL_AURA_FLASH							= 1417, // Lv.5
		//======= End Skill list of Spellsinger ID:27=======
		SKILL_DUMMY = 1;
	};
	public class ElementalSummoner extends ElvenWizard {
		public static final int
		//======= Start Skill list of ElementalSummoner ID:28=======
		SKILL_SUMMON_LIFE_CUBIC					= 67, // Lv.7
		SKILL_SERVITOR_RECHARGE					= 1126, // Lv.34
		SKILL_SERVITOR_HEAL						= 1127, // Lv.45
		SKILL_SERVITOR_MAGIC_SHIELD				= 1139, // Lv.2
		SKILL_SERVITOR_PHYSICAL_SHIELD				= 1140, // Lv.3
		SKILL_SERVITOR_HASTE						= 1141, // Lv.2
		SKILL_BRIGHT_SERVITOR						= 1145, // Lv.3
		SKILL_WIND_SHACKLE							= 1206, // Lv.19
		SKILL_SUMMON_BOXER_THE_UNICORN				= 1226, // Lv.18
		SKILL_SUMMON_MIRAGE_THE_UNICORN			= 1227, // Lv.18
		SKILL_TRANSFER_PAIN						= 1262, // Lv.5
		SKILL_SUMMON_MERROW_THE_UNICORN			= 1277, // Lv.14
		SKILL_SUMMON_AQUA_CUBIC					= 1280, // Lv.9
		SKILL_SERVITOR_EMPOWERMENT					= 1299, // Lv.2
		SKILL_SERVITOR_CURE						= 1300, // Lv.3
		SKILL_SERVITOR_BLESSING					= 1301, // Lv.1
		SKILL_MASS_SUMMON_AQUA_CUBIC				= 1329, // Lv.9
		SKILL_SUMMON_UNICORN_SERAPHIM				= 1332, // Lv.10
		SKILL_BETRAY								= 1380, // Lv.10
		SKILL_MASS_SURRENDER_TO_WATER				= 1384, // Lv.5
		SKILL_SUMMON_FRIEND						= 1403, // Lv.1
		SKILL_SPIRIT_SHARING						= 1547, // Lv.3
		SKILL_DIMENSION_SPIRAL						= 1558, // Lv.14
		//======= End Skill list of ElementalSummoner ID:28=======
		SKILL_DUMMY = 1;
	};
	public class Oracle extends ElvenMage {
		public static final int
		//======= Start Skill list of Oracle ID:29=======
		SKILL_HEAL									= 1011, // Lv.18
		SKILL_CURE_POISON							= 1012, // Lv.2
		SKILL_RECHARGE								= 1013, // Lv.4
		SKILL_BATTLE_HEAL							= 1015, // Lv.15
		SKILL_RESURRECTION							= 1016, // Lv.2
		SKILL_GROUP_HEAL							= 1027, // Lv.15
		SKILL_DISRUPT_UNDEAD						= 1031, // Lv.8
		SKILL_RESIST_POISON						= 1033, // Lv.1
		SKILL_MENTAL_SHIELD						= 1035, // Lv.1
		SKILL_SHIELD								= 1040, // Lv.2
		SKILL_HOLY_WEAPON							= 1043, // Lv.1
		SKILL_REGENERATION							= 1044, // Lv.1
		SKILL_MIGHT								= 1068, // Lv.2
		SKILL_SLEEP								= 1069, // Lv.9
		SKILL_KISS_OF_EVA							= 1073, // Lv.1
		SKILL_CONCENTRATION						= 1078, // Lv.2
		SKILL_AGILITY								= 1087, // Lv.1
		SKILL_DRYAD_ROOT							= 1201, // Lv.9
		SKILL_WIND_WALK							= 1204, // Lv.2
		SKILL_WIND_SHACKLE							= 1206, // Lv.5
		SKILL_DECREASE_WEIGHT						= 1257, // Lv.1
		//======= End Skill list of Oracle ID:29=======
		SKILL_DUMMY = 1;
	};
	public class Elder extends Oracle {
		public static final int
		//======= Start Skill list of Elder ID:30=======
		SKILL_CURE_POISON							= 1012, // Lv.3
		SKILL_RECHARGE								= 1013, // Lv.32
		SKILL_RESURRECTION							= 1016, // Lv.7
		SKILL_VITALIZE								= 1020, // Lv.27
		SKILL_MIGHT_OF_HEAVEN						= 1028, // Lv.19
		SKILL_RESIST_POISON							= 1033, // Lv.3
		SKILL_MENTAL_SHIELD							= 1035, // Lv.4
		SKILL_SHIELD								= 1040, // Lv.3
		SKILL_REGENERATION							= 1044, // Lv.3
		SKILL_RETURN								= 1050, // Lv.2
		SKILL_MIGHT									= 1068, // Lv.3
		SKILL_SLEEP									= 1069, // Lv.42
		SKILL_KISS_OF_EVA							= 1073, // Lv.2
		SKILL_CONCENTRATION							= 1078, // Lv.6
		SKILL_AGILITY								= 1087, // Lv.3
		SKILL_WIND_SHACKLE							= 1206, // Lv.19
		SKILL_GREATER_HEAL							= 1217, // Lv.33
		SKILL_GREATER_GROUP_HEAL					= 1219, // Lv.29
		SKILL_BLESS_SHIELD							= 1243, // Lv.6
		SKILL_PARTY_RECALL							= 1255, // Lv.2
		SKILL_DECREASE_WEIGHT						= 1257, // Lv.3
		SKILL_RESIST_SHOCK							= 1259, // Lv.4
		SKILL_SERENADE_OF_EVA						= 1273, // Lv.13
		SKILL_WILD_MAGIC							= 1303, // Lv.2
		SKILL_ADVANCED_BLOCK						= 1304, // Lv.3
		SKILL_UNHOLY_RESISTANCE						= 1393, // Lv.3
		SKILL_TRANCE								= 1394, // Lv.10
		SKILL_ERASE									= 1395, // Lv.10
		SKILL_CLARITY								= 1397, // Lv.3
		SKILL_MANA_BURN								= 1398, // Lv.10
		SKILL_TURN_UNDEAD							= 1400, // Lv.10
		SKILL_MAJOR_HEAL							= 1401, // Lv.11
		SKILL_INVOCATION							= 1430, // Lv.5
		SKILL_IMPROVED_SHIELD_DEFENSE				= 1503, // Lv.1
		SKILL_IMPROVED_MOVEMENT						= 1504, // Lv.1
		SKILL_DIVINE_PUNISHMENT						= 1523, // Lv.13
		SKILL_SURRENDER_TO_THE_HOLY					= 1524, // Lv.42
		SKILL_DIVINE_CURSE							= 1525, // Lv.13
		SKILL_DIVINE_FLASH							= 1528, // Lv.13
		//======= End Skill list of Elder ID:30=======
		SKILL_DUMMY = 1;
	};
	
}
