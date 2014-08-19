package blood;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import l2s.commons.util.Rnd;
import l2s.gameserver.Config;
import l2s.gameserver.ai.PlayerAI;
import l2s.gameserver.data.xml.holder.ItemHolder;
import l2s.gameserver.model.Party;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.Servitor;
import l2s.gameserver.model.World;
import l2s.gameserver.model.base.ClassId;
import l2s.gameserver.model.base.ClassLevel;
import l2s.gameserver.model.base.Experience;
import l2s.gameserver.model.base.InvisibleType;
//import l2s.gameserver.model.entity.events.impl.DominionSiegeEvent;
import l2s.gameserver.model.items.ItemInstance;
import l2s.gameserver.model.items.TradeItem;
import l2s.gameserver.model.pledge.Clan;
import l2s.gameserver.network.l2.s2c.PrivateStoreMsgBuy;
import l2s.gameserver.network.l2.s2c.PrivateStoreMsgSell;
import l2s.gameserver.skills.AbnormalEffect;
import l2s.gameserver.tables.ClanTable;
import l2s.gameserver.templates.item.ItemTemplate;
import l2s.gameserver.utils.ItemFunctions;
import l2s.gameserver.utils.Location;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import blood.ai.FPCDefaultAI;
import blood.base.FPCBase;
import blood.base.FPCItem;
import blood.base.FPCParty;
import blood.base.FPCPveStyle;
import blood.base.FPCRole;
import blood.base.FPCSpawnStatus;
import blood.data.holder.FarmZoneHolder;
import blood.model.FPReward;
import blood.model.FarmZone;
import blood.table.MerchantItem;


public class FPCInfo
{
	private static final Logger 		_log = LoggerFactory.getLogger(FPCInfo.class);
	// Main variables
	private Player _actor;
	private int _obj_id;
	private FPCSpawnStatus _status;
	private FPCRole	_role;
	private boolean _isMage;
	private String	_shop_status = "none";
	private ClassId _classId;
	private ItemInstance _weapon;
	private MerchantItem merchantItem;
	private int AILoopCount = 0;
	private FPCPveStyle _pveStyle = FPCPveStyle.PARTY;
	private FPCParty _party = null;
	
	private static final String[][]	spawnLoc = {
		//{"87358","-141982","-1341", "Schuttgart Town Center"},
		//{"44070","-50243","-796","Rune Town Center"},
		//{"82321","55139","-1529","Oren Town Center"},
		//{"116589","76268","-2734","Hunters Village Town Center"},
		//{"111115","219017","-3547","Heine Town Center"},
		//{"147725","-56517","-2780","Goddard Town Center"},
		//{"147705","-53066","-2731","Goddard Einhasad Temple"},
		//{"-14225","123540","-3121","Gludio Town Center"},
//		{"-83063","150791","-3120","Gludin Town Center 1"},
//		{"-81784","150840","-3120","Gludin Town Center 2"},
		//{"82698","148638","-3473","Giran Town Center"},
		//{"18748","145437","-3132","Dion Town Center"},
		{"147450","27064","-2208","Aden Town Center"}
	};

	private static FPCBase _instances = new FPCBase();
	
	public FPCInfo(int obj_id)
	{
//		_log.info("create new FPCInfo: "+obj_id);
		//_owner = owner;
		_obj_id = obj_id;
		setStatus(FPCSpawnStatus.OFFLINE);
		_instances.addInfo(this);
		
	}
	
	public FPCInfo(Player player)
	{
		int obj_id = player.getObjectId();
//		_log.info("create new FPCInfo: "+obj_id);
		//_owner = owner;
		_obj_id = obj_id;
		setStatus(FPCSpawnStatus.OFFLINE);
		_instances.addInfo(this);
		_actor = player;
	}
	
	public static FPCInfo getInstance(int obj_id)
	{
		//_log.info("obj_id " + obj_id);
		return _instances.getPlayer(obj_id) != null ? _instances.getPlayer(obj_id) : new FPCInfo(obj_id);
	}
	
	public static FPCInfo getInstance(Player player)
	{
		int obj_id = player.getObjectId();
		return _instances.getPlayer(obj_id) != null ? _instances.getPlayer(obj_id) : new FPCInfo(player);
	}
	
	public Player getActor()
	{
		return _actor;
	}
	
	public int getObjectId()
	{
		return _obj_id;
	}
	
	public void setStatus(FPCSpawnStatus status)
	{
		//_log.info("set status function");
		if(_status == status)
			return;
		
		//_log.info(getActor()+": change status from " + _status + " to "+status);
		
		if(_status != null)
			_status.remove(this);
		
		//_log.info("BEFORE: _status " + _status + " status " + status);
		
		_status = status;
		
		//_log.info("AFTER: _status " + _status + " status " + status);
		
		if(_status != null)
			_status.add(this);
		
		switch(_status)
		{
		case OFFLINE:
			if(_role != null)
			{
				_role.remove(this);
				_role = null;
			}
			break;
		case ONLINE:
			setRole(FPCRole.IDLE);
			break;
		}
			
	}
	
	public FPCSpawnStatus getStatus()
	{
		return _status;
	}
	
	public void setRole(FPCRole role)
	{
		if(_role == role)
		{
//			_log.info(getActor()+": same old role "+role);
		}
		else
		{
//			_log.info(getActor()+": change role from " + _role + " to "+role);

			if(_role != null)
				_role.remove(this);
			
			_role = role;
			
			_role.add(this);
		}
		
		if(_role != null)
		{
			setAI(_role.getAI(getActor()));
			if(_role == FPCRole.NEXUS_EVENT)
			{
				if(canPveSolo(_actor))
	            {
		        	_pveStyle = FPCPveStyle.SOLO;
		        	_log.info(_actor + " going solo.");
	            }
	            else
	            {
	            	lookingParty();
	            	_log.info(_actor + " going to party, size:" + _party.getSize());
	            }

			}
//			_log.info("SetRole: " + getActor().getAI());
		}
	}
	
	public FPCRole getRole()
	{
		return _role;
	}
	
	public boolean isMage()
	{
		return _isMage;
	}
	
	public ClassId getClassId()
	{
		return _classId;
	}
	
	public void counterDisarm()
	{
		Player actor = getActor();
		
		if(actor == null || _weapon == null)
			return;
		
		actor.getInventory().equipItem(_weapon);
	}
	
	public void setParty(FPCParty party)
	{
		_party = party;
	}
	
	public FPCParty getParty()
	{
		return _party;
	}
	
	public void teleToNextFarmZone()
	{
		Player player = getActor();
		Location nextLoc;
		switch(_pveStyle)
		{
			case SOLO:
				nextLoc = FarmZoneHolder.getInstance().getLocation(player);
				if(nextLoc != null)
					player.teleToLocation(nextLoc);
				break;
			
			case PARTY:
				if(getParty() != null && !getParty().isFull())
					return;
				Party party = player.getParty();
				if(party == null)
					return;
				if (party.isLeader(player))
				{
					nextLoc = FarmZoneHolder.getInstance().getLocation(player);
					if(nextLoc != null)
					{
						for(Player partyMember: party.getPartyMembers())
						{
							partyMember.teleToLocation(nextLoc);
						}
					}
				}
				break;
		}
		
	}
	
//	private void registerWithNexus(Player player)
//	{
//		EventBuffer.getInstance().loadPlayer(player.getEventInfo());
//		EventManager.getInstance().onPlayerLogin(player.getEventInfo());
//	}
	
	public void setAI(FPCDefaultAI ai)
	{
		Player actor = getActor();
		
		if(actor != null)
		{
			//if(ai instanceof MarketFPC) cancelShop();
			actor.setAI(ai);
		}
	}	
	
	@SuppressWarnings("unchecked")
	public void setAI(String ai)
	{
		Player actor = getActor();
		
		if(actor == null)
			return;
		
		Class<FPCDefaultAI> classAI = null;
		try {
		classAI = (Class<FPCDefaultAI>) Class.forName("blood.ai." + ai);
		}catch(Exception e){
			
		}
		
		if(classAI == null)
			_log.error("Not found ai class for ai: " + ai + ". FakePlayer: " + actor);
		else
		{
			Constructor<FPCDefaultAI> constructorAI = (Constructor<FPCDefaultAI>)classAI.getConstructors()[0];
			try
			{
				setAI(constructorAI.newInstance(actor));
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public FPCDefaultAI getAI()
	{
		Player actor	= getActor();
		
		PlayerAI ai = actor.getAI();
		
		return (FPCDefaultAI) ai;
	}
	
	public static boolean canPveSolo(Player player)
	{
		if(player == null)
			return false;
		
		switch(player.getClassId())
		{
			case FEOH_ARCHMAGE:
			case FEOH_MYSTIC_MUSE:
			case FEOH_SOUL_HOUND:
			case FEOH_SOULTAKER:
			case FEOH_STORM_SCREAMER:
			case FEOH_WIZARD:
			case WYNN_ARCANA_LORD:
			case WYNN_ELEMENTAL_MASTER:
			case WYNN_SPECTRAL_MASTER:
			case WYNN_SUMMONER:
			case ISS_DOMINATOR:
			case ISS_DOOMCRYER:
			case ISS_ENCHANTER:
			case ISS_HIEROPHANT:
			case ISS_SPECTRAL_DANCER:
			case ISS_SWORD_MUSE:
				return player.getLevel() < 91 && Rnd.chance(30);
				
			default:
				return false;
		}
	}
	
	public static void upClass(Player player)
	{
		if (player == null)
			return;
		
		ArrayList<ClassId> classList = new ArrayList<ClassId>();
		
		if (player.getLevel() >= 20 && player.getClassId().isOfLevel(ClassLevel.NONE))
		{
			classList = new ArrayList<ClassId>();
			for(ClassId classId: ClassId.VALUES)
			{
				if(classId.childOf(player.getClassId()) && classId.isOfLevel(ClassLevel.FIRST))
				{
					classList.add(classId);
				}
			}
			
			if(classList.size() > 0)
				player.setClassId(classList.get(Rnd.get(classList.size())).getId(), true);
		}
		
		if (player.getLevel() >= 40 && player.getClassId().isOfLevel(ClassLevel.FIRST))
		{
			classList = new ArrayList<ClassId>();
			for(ClassId classId: ClassId.VALUES)
			{
				if(classId.childOf(player.getClassId()) && classId.isOfLevel(ClassLevel.SECOND))
				{
					classList.add(classId);
				}
			}
			
			if(classList.size() > 0)
				player.setClassId(classList.get(Rnd.get(classList.size())).getId(), true);
		}
		
		if (player.getLevel() >= 76 && player.getClassId().isOfLevel(ClassLevel.SECOND))
		{
			classList = new ArrayList<ClassId>();
			for(ClassId classId: ClassId.VALUES)
			{
				if(classId.childOf(player.getClassId()) && classId.isOfLevel(ClassLevel.THIRD))
				{
					classList.add(classId);
				}
			}
			
			if(classList.size() > 0)
				player.setClassId(classList.get(Rnd.get(classList.size())).getId(), true);
		}
		
		if (player.getLevel() >= 85 && player.getClassId().isOfLevel(ClassLevel.THIRD))
		{
			classList = new ArrayList<ClassId>();
			for(ClassId classId: ClassId.VALUES)
			{
				if(classId.getId() > 138 && classId.getId() < 147)
					continue;
				
				if(classId.childOf(player.getClassId()) && classId.isOfLevel(ClassLevel.AWAKED))
				{
					classList.add(classId);
				}
			}
			
			if(classList.size() > 0)
				player.setClassId(classList.get(Rnd.get(classList.size())).getId(), true);
		}
		
		// give all skills
		player.rewardSkills(true, true, true, false);
	}
	
	public void lookingParty()
	{
		FPCPartyManager.getInstance().getParty(this);
	}
		
	public boolean uyThac()
	{
		
		Player actor = World.getPlayer(getObjectId());
		_log.info("uyThac: start "+actor);
		try{
			if (actor.isLogoutStarted() || actor.isInOfflineMode()) 
			{
				_log.info("uyThac: failed at stage 1");
				return false; 
			}
			
			// GM dont need uythac
			if(actor.isGM() && !Config.EVERYBODY_HAS_ADMIN_RIGHTS)
			{
				_log.info("uyThac: failed GM dont need that");
				return false;
			}
			
			/* FIXME */
			
//			if(actor.getClassId().getLevel() < 3)
//			{
//				_log.info("uyThac: failed doesnt change class");
//				return false;
//			}
			
			// remove invisible effect
			if(actor.isInvisible())
			{
				actor.setInvisibleType(InvisibleType.NONE);
				actor.broadcastCharInfo();
				actor.stopAbnormalEffect(AbnormalEffect.STEALTH);
				if (actor.getServitors().length > 0)
				{
					for (Servitor sum: actor.getServitors())
						sum.broadcastCharInfo();
				}
			}
			
			// set uythac status
//			actor.setUyThac(); FIXME
//			actor.setFakePlayer(); FIXME
			
			// gear up
			FPCItem.semiGearUp(actor);
			
			//randomTown(actor);
			
			_actor = actor;
			_isMage = actor.isMageClass();
	        _classId = actor.getClassId();
	        
	        
			
			actor.broadcastCharInfo();
			actor.broadcastStatusUpdate();
			
			setStatus(FPCSpawnStatus.ONLINE);
			_weapon = actor.getActiveWeaponInstance();
			setRole(FPCRole.NEXUS_EVENT);
			
			return true;
		}catch(Exception e)
		{
			_log.error("player uythac failed" + getObjectId(), e);
			return false;
		}
		
	}
	
	public void spawn()
	{
		//_log.info("spawn function");
		Player player = null;
    	try{
    		player = Player.restore(getObjectId());
            player.setFakePlayer();
            player.spawnMe();
    		player.setRunning();
    		player.setHeading(Rnd.get(0, 9000));
//    		registerWithNexus(player);
            player.setOnlineStatus(true);
            player.restoreExp();
            //player.broadcastCharInfo();
            //_log.info("spawn " + player.getName());
            randomTown(player);
//            if(Rnd.chance(20))
//            	addClan(player);
//            if(Rnd.chance(30) && player.getTitle().isEmpty() && player.getClan() != null)
//            	setClanTitle(player, FPCNameTable.getRandomTitle());
            // move to spawn zone
            //player.setXYZ(Config.SPAWN_X + Rnd.get(-700, 700), Config.SPAWN_Y + Rnd.get(-700, 700), Config.SPAWN_Z);
            
            _isMage = player.isMageClass();
            _classId = player.getClassId();
            
            if(player.getLevel() < 85)
            {
            	Long exp_add = Experience.LEVEL[85] - player.getExp();
    			player.addExpAndSp(exp_add, 0, true);
            }
            
            upClass(player);
            
            FPReward.getInstance().giveReward(player);
            
            player.broadcastCharInfo();
            
            _actor = player;
            
            
//            cancelShop();
            
            setStatus(FPCSpawnStatus.ONLINE);
            
            _weapon = player.getActiveWeaponInstance();
            
    	}catch (Exception e) {
            _log.error("Fake Players Engine: Error loading player: " + player, e);
            if (player != null) {
                player.deleteMe();
            }
        }
	}
	
	public void cancelShop()
	{
		//if(_actor.getPrivateStoreType() == Player.STORE_PRIVATE_NONE)
		//	return;
		
		List<TradeItem> list = new CopyOnWriteArrayList<TradeItem>();
		list.clear();
        _actor.setPrivateStoreType(Player.STORE_PRIVATE_NONE);
        _actor.standUp();
        _actor.setSellList(false, list);
        _log.info("cancel shop, list " + list.size());
        _actor.setBuyList(list);
		
	}
	
	public void kick()
	{
		Player actor = getActor();
		if(actor == null)
			return;
		
		setStatus(FPCSpawnStatus.OFFLINE);
		
		_log.info(actor +": kicked");
		actor.kick();
	}
	
	public boolean isInEvent()
	{
//		return NexusEvents.isInEvent(getActor());
		return false;
		
	}
	
	public void randomTown(Player player)
	{
		String[] randomLoc = spawnLoc[Rnd.get(spawnLoc.length)];
		
		Location baseLoc = Location.findPointToStay(
												        Integer.parseInt(randomLoc[0]), 
												        Integer.parseInt(randomLoc[1]), 
												        Integer.parseInt(randomLoc[2]),
														100, 850, player.getGeoIndex());
		player.setLoc(baseLoc);
	}
	
	public void setSellShop(MerchantItem item)
	{
		//_log.info("player " + _actor + " item: " + item.getItemID() + " price: " + item.getPrice());
		if(item.getPrice() <= 0) return;
		
		//save it for later references
		setMerchantItem(item);
		
		//check amount of item if available, if not, generate more
		ItemInstance sellItem = checkItemAvailable(item.getItemID(), item.getItemAmount());
		
		if(item.getShopTitle().equalsIgnoreCase(""))
			item.setShopTitle(generateShopTitle(sellItem.getTemplate().getName(), item.getPrice()));
		
		TradeItem tradeItem = new TradeItem(sellItem);
		List<TradeItem> list = new CopyOnWriteArrayList<TradeItem>();	
		
		tradeItem.setItemId(item.getItemID());
		tradeItem.setCount(item.getItemAmount());
		tradeItem.setOwnersPrice(item.getPrice());
		
		list.add(tradeItem);
    	
		if(!list.isEmpty())
		{
			
			_actor.setSellList(false, list);
			_actor.setSellStoreName(item.getShopTitle());
			_actor.saveTradeList();
			_actor.setPrivateStoreType(Player.STORE_PRIVATE_SELL);
			_actor.broadcastPacket(new PrivateStoreMsgSell(_actor));
			_actor.sitDown(null);
			_actor.broadcastCharInfo();
			
			//set owner for the MerchantItem, and write into db, table fpc_merchant
			item.setOwner(_obj_id);
			
		}
		//set the current character as shop, so stop asking it to do anything else
		setShopStatus(item.getStatus());
		
	}
	
	public void setBuyShop(MerchantItem item)
	{
		//save it for later references
		setMerchantItem(item);
		
		//add adena for buying
		_actor.addAdena(item.getPrice()*item.getItemAmount());
		
		TradeItem tradeItem = new TradeItem();
		List<TradeItem> list = new CopyOnWriteArrayList<TradeItem>();	
		
		
		tradeItem.setItemId(item.getItemID());
		tradeItem.setCount(item.getItemAmount());
		tradeItem.setOwnersPrice(item.getPrice());
		
		list.add(tradeItem);
		
		if(!list.isEmpty())
		{
			
			_actor.setBuyList(list);
			_actor.setBuyStoreName(item.getShopTitle());
			_actor.saveTradeList();
			_actor.setPrivateStoreType(Player.STORE_PRIVATE_BUY);
			_actor.broadcastPacket(new PrivateStoreMsgBuy(_actor));
			_actor.sitDown(null);
			_actor.broadcastCharInfo();
			
			//set owner for the MerchantItem, and write into db, table fpc_merchant
			item.setOwner(_obj_id);
			
		}
		//set the current character as shop, so stop asking it to do anything else
		setShopStatus(item.getStatus());
		
	}
	
	
	private String generateShopTitle(String name, long price)
	{
		int maxTitleLength = 29;
		
		String shopName;
		
		if(Rnd.chance(50))
			shopName = "Cheap ";
		else if(Rnd.chance(50))
			shopName = "Best ";
		else 
			shopName = "";
	
		String itemName = FPCItem.shortenItemName(name);
		
		String itemPrice = (Rnd.chance(50))? FPCItem.shortenItemPrice(price):"";
		
		shopName = shopName.concat(itemName);
		shopName = shopName.concat(" ");
		
		if(shopName.length() + itemPrice.length() <= maxTitleLength)
			shopName = shopName.concat(itemPrice);
		
		return shopName;
	}

	private ItemInstance checkInventory(int item_id)
	{
		return _actor.getInventory().getItemByItemId(item_id);
	}
	
	private ItemInstance checkItemAvailable(int item_id, long amount)
	{
		ItemInstance item = checkInventory(item_id);
		
		if(item == null)
		{
			//generate item
			item = ItemFunctions.createItem(item_id);
			item.setCount(amount);
			_actor.getInventory().addItem(item);
		}
		else
		{
			//if the current item amount is not enough compare to the requirement
			//only apply for stackable items
			ItemTemplate itemTemplate = ItemHolder.getInstance().getTemplate(item_id);
			if(item.getCount() < amount && itemTemplate.isStackable())
			{
				//generate some more items
				item.setCount(amount - item.getCount());
			}
		}			
		
		return item;
	}
		
	public MerchantItem getMerchantItem()
	{
		if(merchantItem == null)
		{
			//try to get from the Character Variables
			String rs = _actor.getVar("merchant_item");
			if(rs != null && !rs.isEmpty())
			{
				String[] choppedString = rs.split(";");
				merchantItem = new MerchantItem(Integer.parseInt(choppedString[0]),
												Integer.parseInt(choppedString[1]),
												Integer.parseInt(choppedString[2]),
												Integer.parseInt(choppedString[3]),
												choppedString[4],
												Integer.parseInt(choppedString[5]),
												choppedString[6],
												Long.parseLong(choppedString[7]));
			}
		}
		return merchantItem;
	}

	public void setMerchantItem(MerchantItem merchantItem)
	{
		if(merchantItem == null) return;
		
		if(merchantItem.getID() != -1)
			_actor.setVar("merchant_item", merchantItem.toString(), -1 );
	
		//_log.info("set Var: " + merchantItem.toString());
		this.merchantItem = merchantItem;
		
	}

	public String getShopStatus()
	{
		return _shop_status;
	}

	public void setShopStatus(String shop_status)
	{
		
		this._shop_status = shop_status;
	}
	
	public int getAILoopCount()
	{
		return AILoopCount;
	}

	public void increaseAILoopCount()
	{
		AILoopCount++;
	}
}
