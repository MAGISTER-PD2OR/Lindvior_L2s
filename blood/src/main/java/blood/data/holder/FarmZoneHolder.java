package blood.data.holder;

import java.util.ArrayList;
import java.util.List;

import l2s.commons.data.xml.AbstractHolder;
import l2s.commons.util.Rnd;
import l2s.gameserver.model.Party;
import l2s.gameserver.model.Player;
import l2s.gameserver.utils.Location;
import blood.model.FarmZone;

public final class FarmZoneHolder  extends AbstractHolder{
	
	/**
	 * Field _instance.
	 */
	private static final FarmZoneHolder _instance = new FarmZoneHolder();
	
	/**
	 * Field _bonusList.
	 */
	private final List<FarmZone> _lists = new ArrayList<FarmZone>();
	
	/**
	 * Method getInstance.
	 * @return LevelBonusHolder
	 */
	public static FarmZoneHolder getInstance()
	{
		return _instance;
	}
	
	public void addZone(FarmZone zone){
		_lists.add(zone);
	}
	
	public FarmZone getZones(Player player){
		List<FarmZone> tmp = new ArrayList<FarmZone>();
		for (FarmZone zone: _lists){
			if (zone.isValid(player))
			{
				tmp.add(zone);
			}
		}
		
		if (tmp.size() > 0)
			return tmp.get(Rnd.get(tmp.size()));
		
		return null;
	}
	
	public Location getPartyLocation(int level) 
	{
		// TODO Auto-generated method stub
		List<FarmZone> tmp = new ArrayList<FarmZone>();
		for (FarmZone zone: _lists){
//			_log.info("FarmZone try zone:"+zone);
			if (zone.checkLevel(level) && zone.isParty())
			{
//				_log.info("FarmZone valid zone:"+zone);
				tmp.add(zone);
			}
		}
		
		if (tmp.size() > 0)
			return tmp.get(Rnd.get(tmp.size())).getRndLocation();
		
		return null;
	}
	
	public Location getLocation(Player player)
	{
		FarmZone validZone = getZones(player);
		
		if(validZone == null)
			return null;
		
		return validZone.getRndLocation();
	}
	
	public Location getLocation(Party party)
	{
		return getLocation(party.getPartyLeader());
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return _lists.size();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		_lists.clear();
	}

	

}
