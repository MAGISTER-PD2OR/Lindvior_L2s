package blood.base;

import java.util.ArrayList;

import l2s.gameserver.model.Party;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.base.ClassLevel;

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
	
	public boolean isFull(){
		return _leader.getParty() != null && _leader.getParty().getMemberCount() == Party.MAX_SIZE;
	}
	
	public void partyPosition(Player player){
		if(isTanker(player))
			_tanker = player;
		else if (isIss(player))
			_iss = player;
		else if (isHealer(player))
			_healer = player;
		else if (isDamageDealer(player))
			_dds.add(player);
	}
	
	public boolean isTanker(Player player){
		if(player == null)
			return false;
		
		switch(player.getClassId())
		{
			case SIGEL_PHOENIX_KNIGHT:
			case SIGEL_HELL_KNIGHT:
			case SIGEL_EVAS_TEMPLAR:
			case SIGEL_SHILLIEN_TEMPLAR:
			case SIGEL_KNIGHT:
				return true;
			
			default:
				return false;
		}
	}
	
	public boolean isIss(Player player){
		if(player == null)
			return false;
		
		switch(player.getClassId())
		{
			case ISS_HIEROPHANT:
			case ISS_SWORD_MUSE:
			case ISS_SPECTRAL_DANCER:
			case ISS_DOOMCRYER:
			case ISS_DOMINATOR:
			case ISS_ENCHANTER:
				return true;
			
			default:
				return false;
		}
	}
	
	public boolean isHealer(Player player){
		if(player == null)
			return false;
		
		switch(player.getClassId())
		{
			case AEORE_CARDINAL:
			case AEORE_EVAS_SAINT:
			case AEORE_SHILLIEN_SAINT:
				return true;
			
			default:
				return false;
		}
	}
	
	public boolean isDamageDealer(Player player){
		if(player == null)
			return false;
		
		return player.getClassId().isOfLevel(ClassLevel.AWAKED) && !isTanker(player) && !isIss(player) && !isHealer(player);
	}
	
	public boolean addMember(Player player){
		if(isFull())
			return false;
		
		if(_tanker != null && isTanker(player))
			return false;
		
		if(_iss != null && isIss(player))
			return false;
		
		if(_healer != null && isHealer(player))
			return false;
		
		if(_dds.size() >= 4 && isDamageDealer(player))
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
		
		return result;
	}

}
