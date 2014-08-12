package blood.base;

import java.util.ArrayList;
import java.util.List;

import l2s.commons.util.Rnd;
import l2s.gameserver.data.xml.holder.ItemHolder;
import l2s.gameserver.model.ArmorSet;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.base.ClassId;
import l2s.gameserver.model.base.ClassType2;
import l2s.gameserver.model.base.Race;
import l2s.gameserver.model.items.Inventory;
import l2s.gameserver.model.items.ItemInstance;
import l2s.gameserver.model.items.PcInventory;
import l2s.gameserver.templates.item.ArmorTemplate;
import l2s.gameserver.templates.item.ArmorTemplate.ArmorType;
import l2s.gameserver.templates.item.ItemGrade;
import l2s.gameserver.templates.item.ItemTemplate;
import l2s.gameserver.templates.item.WeaponTemplate;
import l2s.gameserver.templates.item.WeaponTemplate.WeaponType;
import l2s.gameserver.utils.ItemFunctions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import blood.base.FPCArmorGrade.FPCArmorType;
import blood.base.FPCWeaponGrade.FPCWeaponType;

public class FPCItem
{
	@SuppressWarnings("unused")
	private static final Logger 		_log = LoggerFactory.getLogger(FPCItem.class);
	
	public static void putItem(int item_id)
    {
    	ItemTemplate item = ItemHolder.getInstance().getTemplate(item_id);
    	
    	if(item == null)
    		return;
    	
    	if(item.isArmor() && (item.getBodyPart() == ItemTemplate.SLOT_FULL_ARMOR || item.getBodyPart() == ItemTemplate.SLOT_CHEST))
    	{
    		ArmorTemplate _item = (ArmorTemplate) item;
    		//ArmorSet aset = ArmorSetsHolder.getInstance().getArmorSet(item_id);
    		//_holder.getArmor(_item.getGrade()).getType(_item.getItemType()).addItem(item_id);
    		FPCArmorGrade.getInstance(_item.getGrade()).getType(_item.getItemType()).addItem(item_id);
    	}
    	
    	if((item.getBodyPart() == ItemTemplate.SLOT_L_FINGER 
    			|| item.getBodyPart() == ItemTemplate.SLOT_R_FINGER 
    			|| item.getBodyPart() == ItemTemplate.SLOT_L_EAR 
    			|| item.getBodyPart() == ItemTemplate.SLOT_R_EAR 
    			|| item.getBodyPart() == ItemTemplate.SLOT_NECK
    			))
    	{
    		FPCArmorGrade.getInstance(item.getGrade()).setRing(item_id);
    	}
    	
    	if(item.isWeapon())
    	{
    		
    		WeaponTemplate _item = (WeaponTemplate) item;
    		
    		//item.getItemType() == WeaponType.
    		if(item.isMagicWeapon())
    		{
    			//_holder.getWeapon(item.getGrade()).getType("mage").addItem(_item);
    			FPCWeaponGrade.getInstance(item.getGrade()).getType("mage").addItem(item_id);
    		}
    		else
    		{
    			//_holder.getWeapon(item.getGrade()).getType(_item.getItemType()).addItem(_item);
    			FPCWeaponGrade.getInstance(item.getGrade()).getType(_item.getItemType()).addItem(item_id);
    		}
    	}
    }
	
	public static ItemGrade str2grade(String grade_str, ItemGrade defaultGrade)
	{
		for(ItemGrade grade: ItemGrade.values())
		{
			if(grade.toString().equals(grade_str))
				return grade;
		}
		
		return defaultGrade;
	}
	
	public static void semiGearUp(Player player)
	{
		if(player == null) return;
		
		reArmArmorSet(player, ItemGrade.B, 0);
		reArmWeapon(player, ItemGrade.B, 0);
		reArmRing(player, ItemGrade.B, 0);
	}
	
	public static void gearUp(Player player)
	{
		if(player == null) return;
		
		ItemGrade weapon_grade = str2grade(player.getVar("fpc_weapon_grade"), ItemGrade.B);
		ItemGrade armor_grade = str2grade(player.getVar("fpc_armor_grade"), ItemGrade.B);
		ItemGrade ring_grade = str2grade(player.getVar("fpc_ring_grade"), ItemGrade.B);
		
		int weapon_enchant = player.getVarInt("fpc_weapon_enchant", 0);
		int armor_enchant = player.getVarInt("fpc_armor_enchant", 0);
		int ring_enchant = player.getVarInt("fpc_ring_enchant", 0);
		
		/*
		// gear process
		
		switch(weapon_grade)
		{
			case B:
				//_log.info(player.getName() + " check upgrading weapon into A");
				if(player.getInventory().getAdena() > 100000) // upgrade
				{
					//_log.info("upgrade A");
					weapon_grade = Grade.A;
					weapon_enchant = 3;
					player.getInventory().reduceAdena(100000);
				}
				
				break;
				
			default:
					
		}
		
		switch(armor_grade)
		{
			case B:
				//_log.info(player.getName() + " check upgrading armor into A");
				if(player.getInventory().getAdena() > 100000) // upgrade
				{
					//_log.info("upgrade A");
					armor_grade = Grade.A;
					armor_enchant = 3;
					player.getInventory().reduceAdena(100000);
				}
				break;
				
			default:
					
		}
		
		// enchant process
		switch(weapon_grade)
		{
			case B:
				weapon_enchant = increaseItemEnchant(weapon_enchant, 10, 100);
				if(weapon_enchant <0) //item has broken after enchanting
					 weapon_enchant = 3;
				break;
			case A:
				if(player.getAdena() > 100000)
					weapon_enchant = increaseItemEnchant(weapon_enchant, 10, 200);
				else
					weapon_enchant = increaseItemEnchant(weapon_enchant, 10, 80);
				if(weapon_enchant <0) //item has broken after enchanting
				{
					//fall back to B
					weapon_grade = Grade.B;
					weapon_enchant = 3;
				}
				break;
			default:
				weapon_enchant = 3;
		}
		
		switch(armor_grade)
		{
			case B:
				armor_enchant = increaseItemEnchant(armor_enchant, 10, 100);
				if(armor_enchant <0) //item has broken after enchanting
					armor_enchant = 3;
				break;
			case A:
				if(player.getAdena() > 100000)
					armor_enchant = increaseItemEnchant(armor_enchant, 10, 200);
				else 
					armor_enchant = increaseItemEnchant(armor_enchant, 10, 80);
				if(armor_enchant <0) //item has broken after enchanting
				{
					//fall back to B
					armor_grade = Grade.B;
					armor_enchant = 3;
				}
				break;
			default:
				armor_enchant = 3;
		}
		
		switch(ring_grade)
		{
			case B:
				ring_enchant = increaseItemEnchant(ring_enchant, 10, 100);
				if(ring_enchant <0 ) ring_enchant = 3;
				break;
			case A:
				ring_enchant = increaseItemEnchant(ring_enchant, 10, 80);
				if(ring_enchant <0 ) ring_enchant = 3;
				break;
			default:
				ring_enchant = 3;
		}
		*/
		
		player.setVar("fpc_weapon_grade", weapon_grade.toString(), 0);
		player.setVar("fpc_armor_grade", armor_grade.toString(), 0);
		player.setVar("fpc_ring_grade", ring_grade.toString(), 0);
		
		player.setVar("fpc_weapon_enchant", weapon_enchant, 0);
		player.setVar("fpc_armor_enchant", armor_enchant, 0);
		player.setVar("fpc_ring_enchant", ring_enchant, 0);
		
		reArmArmorSet(player, armor_grade, weapon_enchant);
		reArmWeapon(player, weapon_grade, armor_enchant);
		reArmRing(player, ring_grade, ring_enchant);
	}
	
	public static int increaseItemEnchant(int currentEnchant, int maxEnchant, int decisionChance)
	{
		if(currentEnchant >= maxEnchant)
			return currentEnchant;
		
		int enchantLevel = currentEnchant;
	
		//base on the currentEnchant, think that should we enchant it or not
		//the higher of currentEnchant, the smaller the probability
		
		if(enchantLevel < 3) enchantLevel = 3;
		
		int probability = (decisionChance/(enchantLevel-2));
		
		if(Rnd.chance(probability))
		{
			boolean isContinue  = true;
			
			for(int i=3;i<maxEnchant;i++)
			{
				if(isContinue)
				{
					if(Rnd.chance(60))
					{
						enchantLevel++;
						
						//Enchanting succeed. Let's think about whether continue enchanting or not
						//Only 35% of the population have the gut to continue
						probability = (decisionChance/(enchantLevel-2));
						isContinue = (Rnd.chance(probability))?true:false;
					}
					else
					{
						isContinue = false;
						//Enchanting failed, item is broken. Fall back to previous grade
						enchantLevel = -1; 
					}
				}
				
			}
		}
		return enchantLevel;
	}
	
	public static void reArmWeapon(Player player, ItemGrade grade)
	{
		reArmWeapon(player, grade, 3);
	}
	
	public static void reArmWeapon(Player player, ItemGrade grade, int enchant)
	{
		ItemInstance current_weapon = player.getActiveWeaponInstance();
		
		if(current_weapon != null)
		{
			// perfect fit
			if(player.isMageClass() && current_weapon.getTemplate().isMagicWeapon())
				return;
			
			WeaponTemplate current_weapon_template = (WeaponTemplate) current_weapon.getTemplate();
	
			// perfect fit
			if(current_weapon_template.getItemType() == getWeaponType(player.getClassId()))
				return;
		}
				
		List<Integer> weaponList = getWeaponList(player.getClassId(), grade).getAll();
		int weapon_id = 0;
		
		// looking for weapon 
		for(int itemId: weaponList)
		{
			if(player.getInventory().getItemByItemId(itemId) != null)
			{
				weapon_id = itemId;
			}
		}
		
		// if weapon not found, create new id
		if(weapon_id == 0)
		{
			weapon_id = getWeaponList(player.getClassId(), grade).getRandom();
		}
		
		checkAndEquip(player, weapon_id, enchant);
		
		player.getInventory().store();
	}
	
	public static void reArmArmorSet(Player player, ItemGrade grade)
	{
		reArmArmorSet(player, grade, 3);
	}
	
	public static void reArmArmorSet(Player player, ItemGrade grade, int enchant)
	{
		// first get current chest
		ItemInstance chest = player.getInventory().getPaperdollItem(PcInventory.PAPERDOLL_CHEST);

		// find armor list match with current chest
		List<ArmorSet> armorList = getArmorList(player.getClassId(), chest != null ? chest.getTemplate().getGrade() : grade).getAll();
		
		ArmorSet fitSet = null;
		
		findbestset:
		for(ArmorSet armorSet: armorList)
		{
			// current chest is in set, check the missing part and re-equip
			if(chest != null && armorSet.containItem(Inventory.PAPERDOLL_CHEST, chest.getItemId()))
			{
				fitSet = armorSet;
				break findbestset;
			}
			
			// try to match current set with chest in inventory
			for(int chestId: armorSet.getChestIds())
			{
				if(player.getInventory().getItemByItemId(chestId) != null)
				{
					fitSet = armorSet;
					break findbestset;
				}
			}
		}
		
		if(fitSet == null)
		{
			fitSet = getArmorList(player.getClassId(), grade).getRandom();
		}
		
		for(int itemId: buildSet(fitSet))
		{
			checkAndEquip(player, itemId, enchant);
		}
		
		//agathion bracelet
		//checkAndEquip(player, 14776);
		
		player.getInventory().store();
	}
	
	public static List<Integer> buildSet(ArmorSet armor_set)
	{
		List<Integer> tmp = new ArrayList<Integer>();
		
		if(armor_set.getChestIds().length > 0) tmp.add(armor_set.getChestIds()[0]);
		if(armor_set.getLegIds().length > 0) tmp.add(armor_set.getLegIds()[0]);
		if(armor_set.getHeadIds().length > 0) tmp.add(armor_set.getHeadIds()[0]);
		if(armor_set.getGlovesIds().length > 0) tmp.add(armor_set.getGlovesIds()[0]);
		if(armor_set.getFeetIds().length > 0) tmp.add(armor_set.getFeetIds()[0]);
		if(armor_set.getShieldIds().length > 0) tmp.add(armor_set.getShieldIds()[0]);
		
		return tmp;
	}
	
	public static void reArmRing(Player player, ItemGrade grade)
	{
		reArmRing(player, grade, 3);
	}
	
	public static void reArmRing(Player player, ItemGrade grade, int enchant)
	{
		List<Integer> ringList = FPCArmorGrade.getInstance(grade).getRing();
		ItemTemplate itemTemplate;
		
		for(int itemId: ringList)
		{
			itemTemplate = ItemHolder.getInstance().getTemplate(itemId);
			
			if(itemTemplate.getBodyPart() == ItemTemplate.SLOT_NECK && player.getInventory().getPaperdollItem(Inventory.PAPERDOLL_NECK) == null)
			{
				checkAndEquip(player, itemId, enchant);
			}
			
			if(itemTemplate.getBodyPart() == ItemTemplate.SLOT_L_FINGER && player.getInventory().getPaperdollItem(Inventory.PAPERDOLL_RFINGER) == null)
			{
				checkAndEquip(player, itemId, enchant);
			}
			
			if(itemTemplate.getBodyPart() == ItemTemplate.SLOT_R_FINGER && player.getInventory().getPaperdollItem(Inventory.PAPERDOLL_LFINGER) == null)
			{
				checkAndEquip(player, itemId, enchant);
			}
			
			if(itemTemplate.getBodyPart() == ItemTemplate.SLOT_L_EAR && player.getInventory().getPaperdollItem(Inventory.PAPERDOLL_LEAR) == null)
			{
				checkAndEquip(player, itemId, enchant);
			}
			
			if(itemTemplate.getBodyPart() == ItemTemplate.SLOT_R_EAR && player.getInventory().getPaperdollItem(Inventory.PAPERDOLL_REAR) == null)
			{
				checkAndEquip(player, itemId, enchant);
			}
		}
		
		player.getInventory().store();
		
	}
	
	public static ItemInstance supplyItem(Player player, int itemId)
	{
		return supplyItem(player, itemId, 1);
	}
	
	public static ItemInstance supplyItem(Player player, int itemId, long count)
	{
		if(itemId <= 0)
			return null;
		
		ItemInstance item;
		
		item = player.getInventory().getItemByItemId(itemId);
		
		if(item == null)
		{
			item = ItemFunctions.createItem(itemId);
			player.getInventory().addItem(item);
		}
		
		if(item.getCount() < count)
		{
			item.setCount(count);
		}
		
		return item;
	}
	
	public static void checkAndEquip(Player player, int itemId)
	{
		checkAndEquip(player, itemId, 0);
	}
	
	public static void checkAndEquip(Player player, int itemId, int enchantLevel)
	{
		ItemInstance item = player.getInventory().getItemByItemId(itemId);
		if(item == null)
		{
			item = ItemFunctions.createItem(itemId);
			if((item.isArmor() || item.isWeapon() || item.isAccessory()) && item.getTemplate().getGrade() == ItemGrade.B) 
				player.getInventory().addItem(item);
			else
				return;
		}
		
		if(enchantLevel > 0)
		{
			item.setEnchantLevel(enchantLevel);
		}
		
		//do not equip if that item is being for sales
		if(player.getSellList().contains(item)) return;
		
		player.getInventory().equipItem(item);
		//System.out.println(item.toString());
	}
	
	public static void addItem(Player player, int itemId, int enchantLevel, int count)
	{
		ItemInstance item;
		for(int i = 0; i < count; i++)
		{
			item = ItemFunctions.createItem(itemId);
			
			if(enchantLevel > 0)
				item.setEnchantLevel(enchantLevel);
			player.getInventory().addItem(item);
			player.getInventory().equipItem(item);
		}
	}
	
	public static List<Integer> getWeaponAndArmorAndRing(ClassId classId, ItemGrade gradeArmor, ItemGrade gradeWeapon, ItemGrade gradeRing)
	{
		List<Integer> tmp = new ArrayList<Integer>(getArmor(classId, gradeArmor));
		tmp.addAll(FPCArmorGrade.getInstance(gradeRing).getRing());
		tmp.add(getWeapon(classId, gradeWeapon));
		return tmp;
	}
	
	public static FPCArmorType getArmorList(ClassId classId, ItemGrade grade)
	{
		ArmorType 	armorType;
		
		if (classId.getRace() == Race.KAMAEL 				// kamael
			|| classId.getType2() == ClassType2.ROGUE		// dagger and ranger
			|| classId.getType2() == ClassType2.SUMMONER
			|| classId.getId() == ClassId.BOUNTY_HUNTER.getId()
			)
		{
			armorType = ArmorType.LIGHT;
		}
		else if(classId.isMage()) // for mage
		{
			armorType = ArmorType.MAGIC;
		}
		else // and the rest use heavy
		{
			armorType = ArmorType.HEAVY;
		}
		
		return FPCArmorGrade.getInstance(grade).getType(armorType);
	}
    
    public static List<Integer> getArmor(ClassId classId, ItemGrade grade)
    {
    	return buildSet(getArmorList(classId, grade).getRandom());
    }
    
    public static WeaponType getWeaponType(ClassId classId)
    {
    	WeaponType weaponType;
    	switch(classId)
		{
		// tank
		case PALADIN:
		case DARK_AVENGER:
		case TEMPLE_KNIGHT:
		case SHILLEN_KNIGHT:
		case SWORDSINGER:
			weaponType = WeaponType.SWORD;
			break;
			
		// for dagger
		case TREASURE_HUNTER:
		case PLAIN_WALKER:
		case ABYSS_WALKER:
		case BOUNTY_HUNTER:
			weaponType = WeaponType.DAGGER;
			break;
		// ranger
		case HAWKEYE:
		case SILVER_RANGER:
		case PHANTOM_RANGER:
			weaponType = WeaponType.BOW;
			break;
		// crossbow
		case ARBALESTER:
			weaponType = WeaponType.CROSSBOW;
			break;
		// dual
		case GLADIATOR:
		case BLADEDANCER:
			weaponType = WeaponType.DUAL;
			break;
		// pole
		case WARLOCK:
			weaponType = WeaponType.POLE;
			break;
		// 2h
		case DESTROYER:
			weaponType = WeaponType.BIGSWORD;
			break;
			
		// fist
		case TYRANT:
			weaponType = WeaponType.DUALFIST;
			break;
			
		// acient
		case BERSERKER:
			weaponType = WeaponType.ANCIENTSWORD;
			break;
		// rapier
		case M_SOUL_BREAKER:
		case F_SOUL_BREAKER:
			weaponType = WeaponType.RAPIER;
			break;
			
		case WARSMITH:
			weaponType = WeaponType.BLUNT;
			break;
			
			default: 
				weaponType = WeaponType.SWORD;
		}
    	
    	return weaponType;
    }
    
    public static FPCWeaponType getWeaponList(ClassId classId, ItemGrade grade)
    {
    	
    	if(classId.isMage()) // for mage
		{
			return FPCWeaponGrade.getInstance(grade).getType("mage");
		}
		else
		{
			return FPCWeaponGrade.getInstance(grade).getType(getWeaponType(classId));
		}
    }
    
    public static Integer getWeapon(ClassId classId, ItemGrade grade)
    {
    	return getWeaponList(classId, grade).getRandom();
    }
    
    private static FPCItem _instance;
   	
   	public static FPCItem getInstance() {
        if (_instance == null) {
            _instance =
                new FPCItem();
        }
        return _instance;
    }    
    
    private FPCItem() 
    {
    	/*
    	 * B-grade black ore ring
    	 */
       	putItem(926);
       	putItem(864);
       	//putItem(864);
       	putItem(895);
       	//putItem(895);
       	
       	/*
    	 * A-grade majestic ring
    	 */
       	
       	putItem(871);
       	putItem(902);
       	putItem(933);
       	
       	/* Phoenix set
       	putItem(924);
       	putItem(862);
       	//putItem(862);
       	putItem(893);
       	//putItem(893);
       	 */
    	
    	/*
    	 * B-grade armor set
    	 */
    	
    	putItem(357); 		//Zubei's Breastplate - heavy
		putItem(358); 		//Blue Wolf Breastplate - heavy
		putItem(2376); 		//Avadon Breastplate - heavy
		putItem(2381); 		//Doom Plate Armor - heavy
		putItem(2392); 		//Leather Armor of Doom - light
		putItem(2391); 		//Blue Wolf Leather Armor - light
		putItem(2390); 		//Avadon Leather Armor - light
		putItem(2384); 		//Zubei's Leather Shirt - light
		putItem(2397); 		//Tunic of Zubei - magic
		putItem(2398); 		//Blue Wolf Tunic - magic
		putItem(2399); 		//Tunic of Doom - magic
		putItem(2406); 		//Avadon Robe - magic
		
		/*
		 * A-grade armor set
		 */
		
		putItem(365); 		//Dark Crystal Breastplate - heavy
		//putItem(374); 		//Armor of Nightmare - heavy
		putItem(2382); 		//Tallum Plate Armor - heavy
		//putItem(2383); 		//Majestic Plate Armor - heavy
		//putItem(2395); 		//Majestic Leather Armor - light
		//putItem(2394); 		//Nightmarish Leather Armor - light
		putItem(2393); 		//Tallum Leather Armor - light
		putItem(2385); 		//Dark Crystal Leather Armor - light
		putItem(2400); 		//Tallum Tunic - magic
		putItem(2407); 		//Dark Crystal Robe - magic
		//putItem(2408); 		//Nightmare Robe - magic
		//putItem(2409); 		//Majestic Robe - magic
		
		/*
		 * S-grade armor set
		 */
		
		putItem(6373); 		//Imperial Crusader Breastplate - heavy
		putItem(6379); 		//Draconic Leather Armor - light
		putItem(6383); 		//Major Arcana Robe - magic
		
		/*
		 * Top B-grade weapon
		 */
		
		//putItem(9323); 		//ancient - Dismantler [Critical Bleed] cry: 1346
		//putItem(9321); 		//ancient - Dismantler [Critical Drain] cry: 1346
		putItem(9322); 		//ancient - Dismantler [Health] cry: 1346
		putItem(8133); 		//bigblunt - Star Buster [Haste] cry: 1346
		putItem(8132); 		//bigblunt - Star Buster [Health] cry: 1346
		putItem(8134); 		//bigblunt - Star Buster [Rsk. Focus] cry: 1346
		//putItem(8107); 		//bigsword - Guardian Sword [Critical Bleed] cry: 1346
		//putItem(8105); 		//bigsword - Guardian Sword [Critical Drain] cry: 1346
		putItem(8106); 		//bigsword - Guardian Sword [Health] cry: 1346
		putItem(4755); 		//blunt - Art of Battle Axe [Haste] cry: 1346
		putItem(4753); 		//blunt - Art of Battle Axe [Health] cry: 1346
		putItem(4754); 		//blunt - Art of Battle Axe [Rsk. Focus] cry: 1346
		//putItem(4750); 		//blunt - Deadman's Glory [Anger] cry: 1346
		putItem(4752); 		//blunt - Deadman's Glory [Haste] cry: 1346
		putItem(4751); 		//blunt - Deadman's Glory [Health] cry: 1346
		//putItem(8146); 		//blunt - Kaim Vanul's Bones [Conversion] cry: 1346
		//putItem(8145); 		//blunt - Kaim Vanul's Bones [Magic Silence] cry: 1346
		//putItem(8144); 		//blunt - Kaim Vanul's Bones [Mana Up] cry: 1346
		//putItem(4830); 		//bow - Bow of Peril [Cheap Shot] cry: 1346
		//putItem(4828); 		//bow - Bow of Peril [Guidance] cry: 1346
		putItem(4829); 		//bow - Bow of Peril [Quick Recovery] cry: 1346
		//putItem(9327); 		//crossbow - Hell Hound [Cheap Shot] cry: 1346
		//putItem(9325); 		//crossbow - Hell Hound [Guidance] cry: 1346
		putItem(9326); 		//crossbow - Hell Hound [Quick Recovery] cry: 1346
		//putItem(4780); 		//dagger - Demon Dagger [Critical Bleed] cry: 1346
		putItem(6359); 		//dagger - Demon Dagger [Critical Damage] cry: 1346
		//putItem(4781); 		//dagger - Demon Dagger [Critical Poison] cry: 1346
		putItem(2626); 		//dual - Samurai Long Sword*Samurai Long Sword [] cry: 1346
		putItem(4804); 		//dualfist - Bellion Cestus [Critical Drain] cry: 1346
		//putItem(4805); 		//dualfist - Bellion Cestus [Critical Poison] cry: 1346
		putItem(4806); 		//dualfist - Bellion Cestus [Rsk. Haste] cry: 1346
		putItem(4858); 		//pole - Lance [Anger] cry: 1346
		putItem(4859); 		//pole - Lance [Critical Stun] cry: 1346
		putItem(4860); 		//pole - Lance [Towering Blow] cry: 1346
		putItem(9318); 		//rapier - Colichemarde [Critical Damage] cry: 1346
		putItem(9317); 		//rapier - Colichemarde [Focus] cry: 1346
		putItem(9319); 		//rapier - Colichemarde [Haste] cry: 1346
		putItem(4718); 		//sword - Sword of Damascus [Critical Damage] cry: 1346
		putItem(4717); 		//sword - Sword of Damascus [Focus] cry: 1346
		putItem(4719); 		//sword - Sword of Damascus [Haste] cry: 1346
		putItem(8117); 		//sword - Wizard's Tear [Acumen] cry: 1346
		//putItem(8119); 		//sword - Wizard's Tear [Conversion] cry: 1346
		//putItem(8118); 		//sword - Wizard's Tear [Magic Power] cry: 1346

		
		/*
		 * Low A-grade weapon
		 */
		
		putItem(9334); 		//ancient - Divine Pain [Critical Damage] cry: 1128
		putItem(9335); 		//ancient - Divine Pain [Focus] cry: 1128
		putItem(9333); 		//ancient - Divine Pain [Haste] cry: 1128
		putItem(5598); 		//bigblunt - Dasparion's Staff [Acumen] cry: 1128
//		putItem(5597); 		//bigblunt - Dasparion's Staff [Conversion] cry: 1128
//		putItem(5596); 		//bigblunt - Dasparion's Staff [Mana Up] cry: 1128
//		putItem(8128); 		//bigblunt - Destroyer Hammer [Critical Drain] cry: 1128
		putItem(8127); 		//bigblunt - Destroyer Hammer [Haste] cry: 1128
		putItem(8126); 		//bigblunt - Destroyer Hammer [Health] cry: 1128
		putItem(8109); 		//bigsword - Infernal Master [Critical Damage] cry: 1128
		putItem(8110); 		//bigsword - Infernal Master [Focus] cry: 1128
		putItem(8108); 		//bigsword - Infernal Master [Haste] cry: 1128
//		putItem(5600); 		//blunt - Meteor Shower [Critical Bleed] cry: 1128
//		putItem(4757); 		//blunt - Meteor Shower [Focus] cry: 1128
//		putItem(5599); 		//blunt - Meteor Shower [Focus] cry: 1128
		putItem(4756); 		//blunt - Meteor Shower [Health] cry: 1128
//		putItem(4758); 		//blunt - Meteor Shower [P.Focus] cry: 1128
//		putItem(5601); 		//blunt - Meteor Shower [Rsk. Haste] cry: 1128
		putItem(8149); 		//blunt - Spiritual Eye [Acumen] cry: 1128
//		putItem(8148); 		//blunt - Spiritual Eye [Magic Poison] cry: 1128
//		putItem(8147); 		//blunt - Spiritual Eye [Mana Up] cry: 1128
		putItem(5609); 		//bow - Carnage Bow [Critical Bleed] cry: 1128
//		putItem(4831); 		//bow - Carnage Bow [Critical Bleed] cry: 1128
//		putItem(5608); 		//bow - Carnage Bow [Light] cry: 1128
//		putItem(4832); 		//bow - Carnage Bow [Mana Up] cry: 1128
//		putItem(5610); 		//bow - Carnage Bow [Mana Up] cry: 1128
//		putItem(4833); 		//bow - Carnage Bow [Quick Recovery] cry: 1128
		putItem(9338); 		//crossbow - Doomchanter [Critical Bleed] cry: 1128
//		putItem(9337); 		//crossbow - Doomchanter [Light] cry: 1128
//		putItem(9339); 		//crossbow - Doomchanter [Mana Up] cry: 1128
		putItem(5615); 		//dagger - Bloody Orchid [Back Blow] cry: 1128
//		putItem(4785); 		//dagger - Bloody Orchid [Back Blow] cry: 1128
//		putItem(5616); 		//dagger - Bloody Orchid [Critical Bleed] cry: 1128
//		putItem(4783); 		//dagger - Bloody Orchid [Evasion] cry: 1128
//		putItem(4784); 		//dagger - Bloody Orchid [Focus] cry: 1128
		putItem(5614); 		//dagger - Bloody Orchid [Focus] cry: 1128
//		putItem(5704); 		//dual - Keshanberk*Keshanberk [] cry: 1128
		putItem(5233); 		//dual - Keshanberk*Keshanberk [] cry: 1128
//		putItem(5622); 		//dualfist - Blood Tornado [Anger] cry: 1128
//		putItem(4807); 		//dualfist - Blood Tornado [Critical Drain] cry: 1128
		putItem(5621); 		//dualfist - Blood Tornado [Focus] cry: 1128
		putItem(5620); 		//dualfist - Blood Tornado [Haste] cry: 1128
//		putItem(4809); 		//dualfist - Blood Tornado [Haste] cry: 1128
//		putItem(4808); 		//dualfist - Blood Tornado [Rsk. Evasion] cry: 1128
//		putItem(4861); 		//pole - Halberd [Critical Stun] cry: 1128
		putItem(5627); 		//pole - Halberd [Critical Stun] cry: 1128
		putItem(5626); 		//pole - Halberd [Haste] cry: 1128
//		putItem(4862); 		//pole - Halberd [Towering Blow] cry: 1128
//		putItem(4863); 		//pole - Halberd [Wide Blow] cry: 1128
		putItem(5628); 		//pole - Halberd [Wide Blow] cry: 1128
		putItem(9331); 		//rapier - White Lightning [Anger] cry: 1128
//		putItem(9329); 		//rapier - White Lightning [Critical Poison] cry: 1128
		putItem(9330); 		//rapier - White Lightning [Haste] cry: 1128
		putItem(5640); 		//sword - Elemental Sword [Empower] cry: 1128
		putItem(5639); 		//sword - Elemental Sword [Magic Paralyze] cry: 1128
		putItem(5638); 		//sword - Elemental Sword [Magic Power] cry: 1128
		putItem(5637); 		//sword - Tallum Blade [Anger] cry: 1128
//		putItem(5635); 		//sword - Tallum Blade [Critical Poison] cry: 1128
		putItem(5636); 		//sword - Tallum Blade [Haste] cry: 1128
//		putItem(4720); 		//sword - Tallum Blade [Health] cry: 1128
//		putItem(4721); 		//sword - Tallum Blade [Rsk. Evasion] cry: 1128
//		putItem(4722); 		//sword - Tallum Blade [Rsk. Haste] cry: 1128
		
		/*
		 * Mid A-Grade Weapon
		 */
		
		/*
		putItem(5705); 		//dual - Keshanberk*Damascus [] cry: 1485
		putItem(9346); 		//ancient - Undertaker [Critical Bleed] cry: 1659
		putItem(9347); 		//ancient - Undertaker [Critical Drain] cry: 1659
		putItem(9345); 		//ancient - Undertaker [Health] cry: 1659
		putItem(5607); 		//bigblunt - Branch of the Mother Tree [Acumen] cry: 1659
		putItem(5605); 		//bigblunt - Branch of the Mother Tree [Conversion] cry: 1659
		putItem(5606); 		//bigblunt - Branch of the Mother Tree [Magic Damage] cry: 1659
		putItem(8136); 		//bigblunt - Doom Crusher [Anger] cry: 1659
		putItem(8135); 		//bigblunt - Doom Crusher [Health] cry: 1659
		putItem(8137); 		//bigblunt - Doom Crusher [Rsk. Haste] cry: 1659
		putItem(5645); 		//bigsword - Dragon Slayer [Critical Bleed] cry: 1659
		putItem(5646); 		//bigsword - Dragon Slayer [Critical Drain] cry: 1659
		putItem(5644); 		//bigsword - Dragon Slayer [Health] cry: 1659
		putItem(5603); 		//blunt - Elysian [Anger] cry: 1659
		putItem(5604); 		//blunt - Elysian [Critical Drain] cry: 1659
		putItem(5602); 		//blunt - Elysian [Health] cry: 1659
		putItem(8150); 		//blunt - Flaming Dragon Skull [Acumen] cry: 1659
		putItem(8151); 		//blunt - Flaming Dragon Skull [Magic Power] cry: 1659
		putItem(8152); 		//blunt - Flaming Dragon Skull [Magic Silence] cry: 1659
		putItem(5611); 		//bow - Soul Bow [Cheap Shot] cry: 1659
		putItem(5613); 		//bow - Soul Bow [Critical Poison] cry: 1659
		putItem(5612); 		//bow - Soul Bow [Quick Recovery] cry: 1659
		putItem(9349); 		//crossbow - Reaper [Cheap Shot] cry: 1659
		putItem(9351); 		//crossbow - Reaper [Critical Poison] cry: 1659
		putItem(9350); 		//crossbow - Reaper [Quick Recovery] cry: 1659
		putItem(5618); 		//dagger - Soul Separator [Critical Damage] cry: 1659
		putItem(5617); 		//dagger - Soul Separator [Guidance] cry: 1659
		putItem(5619); 		//dagger - Soul Separator [Rsk. Haste] cry: 1659
		putItem(5706); 		//dual - Damascus*Damascus [] cry: 1659
		putItem(5624); 		//dualfist - Dragon Grinder [Guidance] cry: 1659
		putItem(5625); 		//dualfist - Dragon Grinder [Health] cry: 1659
		putItem(5623); 		//dualfist - Dragon Grinder [Rsk. Evasion] cry: 1659
		putItem(5632); 		//pole - Tallum Glaive [Guidance] cry: 1659
		putItem(5633); 		//pole - Tallum Glaive [Health] cry: 1659
		putItem(5634); 		//pole - Tallum Glaive [Wide Blow] cry: 1659
		putItem(9341); 		//rapier - Lacerator [Critical Damage] cry: 1659
		putItem(9342); 		//rapier - Lacerator [Health] cry: 1659
		putItem(9343); 		//rapier - Lacerator [Rsk. Focus] cry: 1659
		putItem(5647); 		//sword - Dark Legion's Edge [Critical Damage] cry: 1659
		putItem(5648); 		//sword - Dark Legion's Edge [Health] cry: 1659
		putItem(5649); 		//sword - Dark Legion's Edge [Rsk. Focus] cry: 1659
		putItem(5643); 		//sword - Sword of Miracles [Acumen] cry: 1659
		putItem(5641); 		//sword - Sword of Miracles [Magic Power] cry: 1659
		putItem(5642); 		//sword - Sword of Miracles [Magic Silence] cry: 1659
		*/
		
		/*
		 * Top A-Grade Item
		 */
		
		/*
		
		putItem(9357); 		//ancient - Durendal [Focus] cry: 2157
		putItem(9358); 		//ancient - Durendal [Haste] cry: 2157
		putItem(9359); 		//ancient - Durendal [Health] cry: 2157
		putItem(8799); 		//bigblunt - Behemoth's Tuning Fork [Anger] cry: 2157
		putItem(8797); 		//bigblunt - Behemoth's Tuning Fork [Focus] cry: 2157
		putItem(8798); 		//bigblunt - Behemoth's Tuning Fork [Health] cry: 2157
		putItem(8819); 		//bigblunt - Daimon Crystal [Acumen] cry: 2157
		putItem(8820); 		//bigblunt - Daimon Crystal [Magic Mental Shield] cry: 2157
		putItem(8818); 		//bigblunt - Daimon Crystal [Mana Up] cry: 2157
		putItem(8791); 		//bigsword - Sword of Ipos [Focus] cry: 2157
		putItem(8792); 		//bigsword - Sword of Ipos [Haste] cry: 2157
		putItem(8793); 		//bigsword - Sword of Ipos [Health] cry: 2157
		putItem(8796); 		//blunt - Barakiel's Axe [Focus] cry: 2157
		putItem(8795); 		//blunt - Barakiel's Axe [Haste] cry: 2157
		putItem(8794); 		//blunt - Barakiel's Axe [Health] cry: 2157
		putItem(8815); 		//blunt - Cabrio's Hand [Conversion] cry: 2157
		putItem(8817); 		//blunt - Cabrio's Hand [Magic Silence] cry: 2157
		putItem(8816); 		//blunt - Cabrio's Hand [Mana Up] cry: 2157
		putItem(8806); 		//bow - Shyeed's Bow [Cheap Shot] cry: 2157
		putItem(8807); 		//bow - Shyeed's Bow [Focus] cry: 2157
		putItem(8808); 		//bow - Shyeed's Bow [Quick Recovery] cry: 2157
		putItem(9361); 		//crossbow - Screaming Vengeance [Cheap Shot] cry: 2157
		putItem(9362); 		//crossbow - Screaming Vengeance [Focus] cry: 2157
		putItem(9363); 		//crossbow - Screaming Vengeance [Quick Recovery] cry: 2157
		putItem(8802); 		//dagger - Naga Storm [Back Blow] cry: 2157
		putItem(8801); 		//dagger - Naga Storm [Critical Damage] cry: 2157
		putItem(8800); 		//dagger - Naga Storm [Focus] cry: 2157
		putItem(8938); 		//dual - Damascus * Tallum Blade [] cry: 2157
		putItem(8811); 		//dualfist - Sobekk's Hurricane [Critical Drain] cry: 2157
		putItem(8810); 		//dualfist - Sobekk's Hurricane [Haste] cry: 2157
		putItem(8809); 		//dualfist - Sobekk's Hurricane [Rsk. Haste] cry: 2157
		putItem(8803); 		//pole - Tiphon's Spear [Critical Stun] cry: 2157
		putItem(8804); 		//pole - Tiphon's Spear [Towering Blow] cry: 2157
		putItem(8805); 		//pole - Tiphon's Spear [Wild Blow] cry: 2157
		putItem(9355); 		//rapier - Eclair Bijou [Critical Poison] cry: 2157
		putItem(9353); 		//rapier - Eclair Bijou [Haste] cry: 2157
		putItem(9354); 		//rapier - Eclair Bijou [Health] cry: 2157
		putItem(8790); 		//sword - Sirra's Blade [Critical Poison] cry: 2157
		putItem(8788); 		//sword - Sirra's Blade [Haste] cry: 2157
		putItem(8789); 		//sword - Sirra's Blade [Health] cry: 2157
		putItem(8814); 		//sword - Themis' Tongue [Magic Focus] cry: 2157
		putItem(8813); 		//sword - Themis' Tongue [Magic Mental Shield] cry: 2157
		putItem(8812); 		//sword - Themis' Tongue [Mana Up] cry: 2157
		
		*/
		
		/*
		 * S-Grade Item
		 */
		
		putItem(9371); 		//ancient - Gram [Focus] cry: 2052
		putItem(9369); 		//ancient - Gram [Haste] cry: 2052
		putItem(9370); 		//ancient - Gram [Health] cry: 2052
		putItem(6597); 		//bigblunt - Dragon Hunter Axe [Health] cry: 2052
		putItem(6598); 		//bigblunt - Dragon Hunter Axe [HP Drain] cry: 2052
		putItem(6596); 		//bigblunt - Dragon Hunter Axe [HP Regeneration] cry: 2052
		putItem(6587); 		//bigblunt - Imperial Staff [Empower] cry: 2052
		putItem(6589); 		//bigblunt - Imperial Staff [Magic Hold] cry: 2052
		putItem(6588); 		//bigblunt - Imperial Staff [MP Regeneration] cry: 2052
		putItem(6607); 		//bigsword - Heavens Divider [Focus] cry: 2052
		putItem(6605); 		//bigsword - Heavens Divider [Haste] cry: 2052
		putItem(6606); 		//bigsword - Heavens Divider [Health] cry: 2052
		putItem(6608); 		//blunt - Arcana Mace [Acumen] cry: 2052
		putItem(6610); 		//blunt - Arcana Mace [Mana Up] cry: 2052
		putItem(6609); 		//blunt - Arcana Mace [MP Regeneration] cry: 2052
		putItem(6585); 		//blunt - Basalt Battlehammer [Health] cry: 2052
		putItem(6584); 		//blunt - Basalt Battlehammer [HP Drain] cry: 2052
		putItem(6586); 		//blunt - Basalt Battlehammer [HP Regeneration] cry: 2052
		putItem(7576); 		//bow - Draconic Bow [Cheap Shot] cry: 2052
		putItem(7578); 		//bow - Draconic Bow [Critical Slow] cry: 2052
		putItem(7577); 		//bow - Draconic Bow [Focus] cry: 2052
		putItem(9373); 		//crossbow - Sarnga [Cheap Shot] cry: 2052
		putItem(9375); 		//crossbow - Sarnga [Critical Slow] cry: 2052
		putItem(9374); 		//crossbow - Sarnga [Focus] cry: 2052
		putItem(6590); 		//dagger - Angel Slayer [Critical Damage] cry: 2052
		putItem(6592); 		//dagger - Angel Slayer [Haste] cry: 2052
		putItem(6591); 		//dagger - Angel Slayer [HP Drain] cry: 2052
		putItem(6580); 		//dual - Tallum Blade*Dark Legion's Edge [] cry: 2052
		putItem(6604); 		//dualfist - Demon Splinter [Critical Stun] cry: 2052
		putItem(6602); 		//dualfist - Demon Splinter [Focus] cry: 2052
		putItem(6603); 		//dualfist - Demon Splinter [Health] cry: 2052
		putItem(6600); 		//pole - Saint Spear [Guidance] cry: 2052
		putItem(6601); 		//pole - Saint Spear [Haste] cry: 2052
		putItem(6599); 		//pole - Saint Spear [Health] cry: 2052
		putItem(9367); 		//rapier - Laevateinn [Focus] cry: 2052
		putItem(9365); 		//rapier - Laevateinn [Haste] cry: 2052
		putItem(9366); 		//rapier - Laevateinn [Health] cry: 2052
		putItem(6583); 		//sword - Forgotten Blade [Focus] cry: 2052
		putItem(6581); 		//sword - Forgotten Blade [Haste] cry: 2052
		putItem(6582); 		//sword - Forgotten Blade [Health] cry: 2052
		
		putItem(6593); 		//bow - Shining Bow [Cheap Shot] cry: 2440
		putItem(6595); 		//bow - Shining Bow [Critical Slow] cry: 2440
		putItem(6594); 		//bow - Shining Bow [Focus] cry: 2440
    }
    
    
    
    
}
