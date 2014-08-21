package blood.base;

import java.util.ArrayList;

import l2s.gameserver.ThreadPoolManager;
import l2s.gameserver.model.Party;
import l2s.gameserver.model.Player;
import l2s.gameserver.utils.Location;
import blood.FPCInfo;
import blood.FPCPartyManager;
import blood.data.holder.FarmZoneHolder;
import blood.utils.ClassFunctions;

public class FPCParty {
	
	public static enum PartyIntention {
		IDLE,
		ACTIVE,
		MOVING,
		ATTACK
	}
	
	public static int PARTY_LEVEL_PADDING = 5;
	public static int AWAKENED_LEVEL = 85;
	protected static long RUNNER_INTERVAL = 3000L;
	
	protected Player _leader = null;
	protected Player _tanker = null;
	protected Player _iss = null;
	protected Player _healer = null;
	protected ArrayList<Player> _dds = new ArrayList<Player>();
	protected int _averageLevel = 0;
	protected Party _party = null;
	protected Location _beginLoc = null;
	protected Location _centerLoc = null;
	protected Location _nextLoc = null;
	protected PartyIntention _intention = null;
	
	
	public FPCParty(Player partyLeader){
		_leader = partyLeader;
		_averageLevel = _leader.getLevel();
		setIntention(PartyIntention.IDLE);
		ThreadPoolManager.getInstance().scheduleAtFixedRate(new HeartBeat(), RUNNER_INTERVAL, RUNNER_INTERVAL);
	}
	
	public void setLeader(Player player)
	{
		_leader = player;
		getParty().changePartyLeader(_leader);
		System.out.println("party is full, change party leader to: "+_leader);
	}
	
	public boolean isFull()
	{
		return getParty().getMemberCount() == Party.MAX_SIZE;
	}
	
	public Party getParty()
	{
		if(_party == null)
		{
			_party = new Party(_leader, Party.ITEM_ORDER_SPOIL);
			_leader.setParty(_party);
			if(ClassFunctions.isTanker(_leader))
				_tanker = _leader;
			else if (ClassFunctions.isIss(_leader))
				_iss = _leader;
			else if (ClassFunctions.isHealer(_leader))
				_healer = _leader;
			else if (ClassFunctions.isDamageDealer(_leader))
				_dds.add(_leader);
		}
		
		return _party;
	}
	
	public boolean isValidLevel(int level)
	{
		int minLvl = _averageLevel >= AWAKENED_LEVEL ? Math.min(85, _averageLevel - PARTY_LEVEL_PADDING) : _averageLevel - PARTY_LEVEL_PADDING;
		int maxLvl = _averageLevel + PARTY_LEVEL_PADDING;
		return minLvl <= level && level <= maxLvl; 
	}
	
	public boolean isTankerAvailable(Player player)
	{
		return _tanker == null && ClassFunctions.isTanker(player);
	}
	
	public boolean isHealerAvailable(Player player)
	{
		return _healer == null && ClassFunctions.isHealer(player) ;
	}
	
	public boolean isSupportAvailable(Player player)
	{
		return _iss == null && ClassFunctions.isIss(player);
	}
	
	public boolean isDamageDealerAvaialble(Player player)
	{
		return _dds.size() < 4 && ClassFunctions.isDamageDealer(player);
	}
	
	public boolean addMember(Player player){
		if(isFull())
			return false;
		
		// check level
		if(!isValidLevel(player.getLevel()))
		{
			System.out.println("not valid level "+player);
			return false;
		}
		
		if(isDamageDealerAvaialble(player) && getParty().addPartyMember(player))
			_dds.add(player);
		else if(isTankerAvailable(player) && getParty().addPartyMember(player))
			_tanker = player;
		else if(isHealerAvailable(player) && getParty().addPartyMember(player))
			_healer = player;
		else if(isSupportAvailable(player) && getParty().addPartyMember(player))
			_iss = player;
		else
		{
			System.out.println("not valid class "+player);
			return false;
		}
		
		// join party in middle of active
		switch(_intention){
		case ATTACK:
		case ACTIVE:
		case MOVING:
			player.teleToLocation(_leader.getLoc());
			break;
		default:
			break;
		}
		
		return true;
	}
	
	public PartyIntention getIntention() {
		return _intention;
	}

	public void setIntention(PartyIntention intention) {
		_intention = intention;
	}
	
	public Location getBeginLoc()
	{
		return _beginLoc;
	}
	
	public void setBeginLoc()
	{
		_beginLoc = FarmZoneHolder.getInstance().getPartyLocation(_averageLevel);
	}
	
	public Location getCenterLoc()
	{
		return _centerLoc;
	}
	
	public void updateCenterLoc()
	{
		int x = 0;
		int y = 0;
		int z = 0;
		
		int size = 0;
		
		// TODO should try to use dd only
		for(Player player: _dds)
		{
			x += player.getX();
			y += player.getY();
			z += player.getZ();
			size++;
		}
		
		if(size > 0)
			_centerLoc = new Location(x/size, y/size, z/size);		
	}
	
	public void tryReopen()
	{
		if (!isFull())
			FPCPartyManager.getInstance().reopenParty(this);
	}
	
	public void teleportPartyMember(Location loc)
	{
		for(Player player: getParty().getPartyMembers())
		{
			player.teleToLocation(loc);
			FPCInfo.fullRestore(player);
		}
	}
	
	public void thinkIdle()
	{
		if (!isFull())
			return;
		
		setBeginLoc();
		// TODO should move
		teleportPartyMember(getBeginLoc());
		// change leader to dd
		setLeader(_dds.get(0));
		// change party intension
		setIntention(PartyIntention.ACTIVE);
	}
	
	public void thinkActive()
	{
		tryReopen();
	}
	
	public void thinkMoving()
	{
		tryReopen();
		if(_nextLoc == null)
		{
			setIntention(PartyIntention.ACTIVE);
		}
	}
	
	public void thinkActtack()
	{
		tryReopen();
		updateCenterLoc();
	}
	
	public void onEvtThing()
	{
		System.out.println("party heartbeat:"+_leader);
		switch (getIntention()) {
		case IDLE:
			thinkIdle();
			break;
			
		case ACTIVE:
			thinkActive();
			break;
			
		case MOVING:
			thinkMoving();
			break;
			
		case ATTACK:
			thinkActtack();
			break;
		}
	}

	public void debug() {
		// TODO Auto-generated method stub
		System.out.println("=====Party DEBUG=====");
		System.out.println("leader:"+_leader);
		System.out.println("tanker:"+_tanker);
		System.out.println("healer:"+_healer);
		System.out.println("iss:"+_iss);
		for(Player dd: _dds)
		{
			System.out.println("dd:"+dd);
		}
		System.out.println("====================");
		
	}
	
	public class HeartBeat implements Runnable
	{
		@SuppressWarnings("synthetic-access")
		@Override
		public void run()
		{
			try
			{
				onEvtThing();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
