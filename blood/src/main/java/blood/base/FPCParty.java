package blood.base;

import java.util.ArrayList;

import l2s.gameserver.model.Party;
import l2s.gameserver.model.Player;
import l2s.gameserver.utils.Location;
import blood.utils.ClassFunctions;

public class FPCParty {
	
	protected Player _leader = null;
	protected Player _tanker = null;
	protected Player _iss = null;
	protected Player _healer = null;
	protected ArrayList<Player> _dds = new ArrayList<Player>();
	
	public FPCParty(Player partyLeader){
		_leader = partyLeader;
		_leader.setParty(new Party(_leader, Party.ITEM_ORDER_SPOIL));
		partyPosition(partyLeader);
	}
	
	public int getSize()
	{
		return _leader.isInParty() ? _leader.getParty().getMemberCount() : 1; 
	}
	
	public boolean isFull(){
		return _leader.isInParty() && _leader.getParty().getMemberCount() == Party.MAX_SIZE;
	}
	
	public void partyPosition(Player player){
		if(ClassFunctions.isTanker(player))
			_tanker = player;
		else if (ClassFunctions.isIss(player))
			_iss = player;
		else if (ClassFunctions.isHealer(player))
			_healer = player;
		else if (ClassFunctions.isDamageDealer(player))
			_dds.add(player);
	}
	
	public boolean addMember(Player player){
		if(isFull())
			return false;
		
		if(_tanker != null && ClassFunctions.isTanker(player))
			return false;
		
		if(_iss != null && ClassFunctions.isIss(player))
			return false;
		
		if(_healer != null && ClassFunctions.isHealer(player))
			return false;
		
		if(_dds.size() >= 4 && ClassFunctions.isDamageDealer(player))
			return false;
		
		if(player.getLevel() > (_leader.getLevel() +5) || player.getLevel() < (_leader.getLevel() - 5))
			return false;
		
		Party party = _leader.getParty();
		
		if(party == null)
		{
//			int itemDistribution = _leader.getInteger("itemDistribution");
			_leader.setParty(party = new Party(_leader, Party.ITEM_ORDER_SPOIL));
		}
		
		boolean result = party.addPartyMember(player);
		
		if(result)
			partyPosition(player);
		
		if (isFull())
		{
			_leader = _dds.get(0);
			party.changePartyLeader(_leader);
			System.out.println("party is full, change party leader to: "+_leader);
		}
		
		return result;
	}
	
	public static Location getPartyCenterLoc(Party party)
	{
		int x = 0;
		int y = 0;
		int z = 0;
		
		int size = 0;
		
		// TODO should try to use dd only
		for(Player player: party.getPartyMembers())
		{
			if(ClassFunctions.isHealer(player)
					|| ClassFunctions.isTanker(player)
					|| ClassFunctions.isIss(player))
				continue;
			x += player.getX();
			y += player.getY();
			z += player.getZ();
			size++;
		}
		
		return new Location(x/size, y/size, z/size);
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

}
