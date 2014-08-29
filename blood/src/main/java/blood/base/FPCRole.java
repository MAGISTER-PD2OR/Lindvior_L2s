package blood.base;

import l2s.commons.util.Rnd;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.base.ClassLevel;
import blood.BloodConfig;
import blood.FPCInfo;
import blood.ai.FPCDefaultAI;
import blood.ai.IdleFPC;
import blood.ai.MarketFPC;
import blood.ai.impl.FPCAdventurer;
import blood.ai.impl.FPCAeore;
import blood.ai.impl.FPCArcarnaLord;
import blood.ai.impl.FPCArchmage;
import blood.ai.impl.FPCBishop;
import blood.ai.impl.FPCDominator;
import blood.ai.impl.FPCDoomBringer;
import blood.ai.impl.FPCDoomcryer;
import blood.ai.impl.FPCDreadnought;
import blood.ai.impl.FPCDuelist;
import blood.ai.impl.FPCEvaSaint;
import blood.ai.impl.FPCEvaTemplar;
import blood.ai.impl.FPCFeoh;
import blood.ai.impl.FPCFortuneSeeker;
import blood.ai.impl.FPCGhostHunter;
import blood.ai.impl.FPCGhostSentinel;
import blood.ai.impl.FPCGrandKhauatari;
import blood.ai.impl.FPCHellKnight;
import blood.ai.impl.FPCIss;
import blood.ai.impl.FPCJudicator;
import blood.ai.impl.FPCMaestro;
import blood.ai.impl.FPCMaleSoulhound;
import blood.ai.impl.FPCMoonlightSentinel;
import blood.ai.impl.FPCMysticMuse;
import blood.ai.impl.FPCOthell;
import blood.ai.impl.FPCPhoenixKnight;
import blood.ai.impl.FPCSagittarius;
import blood.ai.impl.FPCShilenElder;
import blood.ai.impl.FPCShillienTemplar;
import blood.ai.impl.FPCSigel;
import blood.ai.impl.FPCSoultaker;
import blood.ai.impl.FPCSpectraDancer;
import blood.ai.impl.FPCSpectraMaster;
import blood.ai.impl.FPCStormScreamer;
import blood.ai.impl.FPCSwordMuse;
import blood.ai.impl.FPCTitan;
import blood.ai.impl.FPCTrickster;
import blood.ai.impl.FPCTyrr;
import blood.ai.impl.FPCWindRider;
import blood.ai.impl.FPCWizzard;
import blood.ai.impl.FPCWynn;
import blood.ai.impl.FPCYul;
import blood.ai.impl.LowLevelFarming;
import blood.ai.impl.MysticPC;

public enum FPCRole {
	IDLE("idle", BloodConfig.FPC_IDLE),
	NEXUS_EVENT("nexus", BloodConfig.FPC_NEXUS),
	MARKET("market", BloodConfig.FPC_MARKET);
	
	private String _name;
	private FPCBase _players = new FPCBase();
	private int _quota = 0;
	private int _quota_base = 0;
	private int _quota_adjust_rate	= 15; //percent
	
	private FPCRole(String name, int quota)
	{
		_name = name;
		setQuota(quota, true);
	}
	
	public void quotaPadding()
	{
		int	_adjust_ccu	= _quota_base*_quota_adjust_rate/100;
		
		if(_adjust_ccu > 0)
			_adjust_ccu		=  Rnd.get(_adjust_ccu*2)-_adjust_ccu;
		
		_quota			= _quota_base + _adjust_ccu;
		
		if(_quota < 0) _quota = 0;
	}
	
	public void setQuota(int quota)
	{
		setQuota(quota, false);
	}
	
	public void setQuota(int quota, boolean is_init)
	{
		_quota_base = quota;
		quotaPadding();
		
		if(!is_init)
			FPCSpawnStatus.setPopulation(getExpectCCU()*FPCSpawnStatus.getOnlineRatio());
	}
	
	public int getQuota()
	{
		return _quota;
	}
	
	public int getSize()
	{
		return _players.getPs().size();
	}
	
	public int getPadding()
	{
		return _quota - getSize();
	}
	
	public FPCInfo getRandom()
	{
		return _players.getRandom();
	}
	
	public FPCBase getAll()
	{
		return _players;
	}
	
	public FPCInfo getPlayer(int obj_id)
	{
		return _players.getPlayer(obj_id);
	}
	
	public FPCInfo getPlayer(Player player)
	{
		return _players.getPlayer(player);
	}
	
	public void add(FPCInfo player)
	{
		_players.addInfo(player);
	}
	
	public void remove(FPCInfo player)
	{
		_players.deleteInfo(player);
	}
	
	public static int getExpectCCU()
	{
		int tmp = 0;
		for(FPCRole role: FPCRole.values())
		{
			tmp += role.getQuota();
		}
		return tmp;
	}
	
	public static int getCCU()
	{
		return IDLE.getSize() + NEXUS_EVENT.getSize() + MARKET.getSize();
	}
	
	public static int getTotalPadding()
	{
		return getExpectCCU() - getCCU();
	}
	
	public FPCDefaultAI getAI(Player player)
	{
		if(player == null)
			return null;
		
		switch(this)
		{
			case IDLE:
				return new IdleFPC(player);
			
			case MARKET:
				return new MarketFPC(player);
				
			case NEXUS_EVENT:
				return getAggresiveAI(player);
				//return new EventFPC(player);
			
			default:
				return new IdleFPC(player);
		}
	}
	
	private FPCDefaultAI getAggresiveAI(Player player)
	{
		if(player.getClassId().isOfLevel(ClassLevel.AWAKED))
		{
			switch (player.getClassId()) {
			case FEOH_ARCHMAGE:
			case FEOH_SOULTAKER:
			case FEOH_MYSTIC_MUSE:
			case FEOH_STORM_SCREAMER:
			case FEOH_SOUL_HOUND:
			case FEOH_WIZARD:
				return new FPCFeoh(player);
				
			case WYNN_ARCANA_LORD:
			case WYNN_ELEMENTAL_MASTER:
			case WYNN_SPECTRAL_MASTER:
			case WYNN_SUMMONER:
				return new FPCWynn(player);
				
			case ISS_HIEROPHANT:
			case ISS_DOMINATOR:
			case ISS_DOOMCRYER:
			case ISS_ENCHANTER:
			case ISS_SPECTRAL_DANCER:
			case ISS_SWORD_MUSE:
				return new FPCIss(player);
				
			case AEORE_CARDINAL:
			case AEORE_EVAS_SAINT:
			case AEORE_SHILLIEN_SAINT:
				return new FPCAeore(player);
				
			case SIGEL_PHOENIX_KNIGHT:
			case SIGEL_HELL_KNIGHT:
			case SIGEL_EVAS_TEMPLAR:
			case SIGEL_SHILLIEN_TEMPLAR:
			case SIGEL_KNIGHT:
				return new FPCSigel(player);
				
			case OTHELL_ADVENTURER:
			case OTHELL_WIND_RIDER:
			case OTHELL_GHOST_HUNTER:
			case OTHELL_FORTUNE_SEEKER:
			case OTHELL_ROGUE:
				return new FPCOthell(player);
				
			case YR_SAGITTARIUS:
			case YR_MOONLIGHT_SENTINEL:
			case YR_GHOST_SENTINEL:
			case YR_TRICKSTER:
			case YR_ARCHER:
				return new FPCYul(player);
				
			case TYR_DUELIST:
			case TYR_DREADNOUGHT:
			case TYR_TITAN:
			case TYR_GRAND_KHAVATARI:
			case TYR_MAESTRO:
			case TYR_DOOMBRINGER:
			case TYR_WARRIOR:
				return new FPCTyrr(player);
				
			default:
				break;
			}
		}
		
		if(player.isMageClass())
        {
        	switch(player.getClassId())
        	{
        		//Healer
        		case CARDINAL:
	        	case BISHOP:
	        		return new FPCBishop(player);
	        	case EVAS_SAINT:
	        	case ELDER:
	        		return new FPCEvaSaint(player);
	        	case SHILLIEN_SAINT:
	        	case SHILLEN_ELDER:
	        		return new FPCShilenElder(player);
	        	//Summoner
	        	case NECROMANCER:
	        	case SOULTAKER:
	        		return new FPCSoultaker(player);
	        	case WARLOCK:
	        	case ARCANA_LORD:
	        		return new FPCArcarnaLord(player);
	        	case PHANTOM_SUMMONER:
	        	case SPECTRAL_MASTER:
	        		return new FPCSpectraMaster(player);
	        	//Nuker
	        	case SORCERER:
	        	case ARCHMAGE:
	        		return new FPCArchmage(player);
	        	case SPELLSINGER:
	        	case MYSTIC_MUSE:
	        		return new FPCMysticMuse(player);
	        	case SPELLHOWLER:
	        	case STORM_SCREAMER:
	        		return new FPCStormScreamer(player);
	        	//Orcish Mystic
	        	case WARCRYER:
	        	case DOOMCRYER:
	        		return new FPCDoomcryer(player);
	        	case OVERLORD:
	        	case DOMINATOR:
	        		return new FPCDominator(player);
	        	case WIZARD:
	        	case ELVEN_WIZARD:
	        	case DARK_WIZARD:
	        		return new FPCWizzard(player);
        		default:
        			return new MysticPC(player);
        	}
        	
        }
        else
        {
        	switch(player.getClassId())
        	{
        		//Ranger
	        	case HAWKEYE:
	        	case SAGITTARIUS:
	        		return new FPCSagittarius(player);
	        	case SILVER_RANGER:
	        	case MOONLIGHT_SENTINEL:
	        		return new FPCMoonlightSentinel(player);
	        	case PHANTOM_RANGER:
	        	case GHOST_SENTINEL:
	        		return new FPCGhostSentinel(player);
	        	case TRICKSTER:
	        	case ARBALESTER:
	    			return new FPCTrickster(player);
	    		//Dagger
	        	case ABYSS_WALKER:
	        	case GHOST_HUNTER:
	        		return new FPCGhostHunter(player);
	        	case TREASURE_HUNTER:
	        	case ADVENTURER:
	        		return new FPCAdventurer(player);
	        	case PLAIN_WALKER:
	        	case WIND_RIDER:
	        		return new FPCWindRider(player);
	        	//Tanker
	        	case PALADIN:
	        	case PHOENIX_KNIGHT:
	        		return new FPCPhoenixKnight(player);
	        	case DARK_AVENGER:
	        	case HELL_KNIGHT:
	        		return new FPCHellKnight(player);
	        	case TEMPLE_KNIGHT:
	        	case EVAS_TEMPLAR:
	        		return new FPCEvaTemplar(player);
	        	case SHILLEN_KNIGHT:
	        	case SHILLIEN_TEMPLAR:
	        		return new FPCShillienTemplar(player);
	        	//Warrior
	        	case DREADNOUGHT:
	        	case WARLORD:
	        		return new FPCDreadnought(player);
	        	case GLADIATOR:
	        	case DUELIST:
	        		return new FPCDuelist(player);
	        	//Orcish Warrior
	        	case TITAN:
	        	case DESTROYER:
	        		return new FPCTitan(player);
	        	case TYRANT:
	        	case GRAND_KHAVATARI:
	        		return new FPCGrandKhauatari(player);
	        	//Dwarven Warrior
	        	case BOUNTY_HUNTER:
	        	case FORTUNE_SEEKER:
	        		return new FPCFortuneSeeker(player);
	        	case MAESTRO:
	        	case WARSMITH:
	        		return new FPCMaestro(player);
	        	//Orchestra
	        	case SWORDSINGER:
	        	case SWORD_MUSE:
	        		return new FPCSwordMuse(player);
	        	case BLADEDANCER:
	        	case SPECTRAL_DANCER:
	        		return new FPCSpectraDancer(player);
	        	//Kamael Warrior
	        	case DOOMBRINGER:
	        	case BERSERKER:
	        		return new FPCDoomBringer(player);
	        	case M_SOUL_BREAKER:
	        	case M_SOUL_HOUND:
	        	case F_SOUL_BREAKER:
	        	case F_SOUL_HOUND:
	        		return new FPCMaleSoulhound(player);
	        	case INSPECTOR:
	        	case JUDICATOR:
	        		return new FPCJudicator(player);
	    		default:
//	            	return new FighterPC(player);
	    			return new LowLevelFarming(player);
        	}
        }
		
	}
	

	public static FPCDefaultAI getAggresiveAI(Player player, String className)
	{
		player.sendMessage("Set character to AI: " + className);
//		FPCInfo.autoshot(player);
		switch(className.toLowerCase())
    	{
			// 4th
			case "iss":
				return new FPCIss(player);
			case "yul":
				return new FPCYul(player);
			case "sigel":
				return new FPCSigel(player);
			case "tyr":
				return new FPCTyrr(player);
			case "othell":
				return new FPCOthell(player);
			case "feoh":
				return new FPCFeoh(player);
			case "wynn":
				return new FPCWynn(player);
			case "aeore":
				return new FPCAeore(player);
    		//Healer
        	case "bs":
        		return new FPCBishop(player);
        	case "ee":
        		return new FPCEvaSaint(player);
        	case "se":
        		return new FPCShilenElder(player);
        	//Summoner
        	case "nec":
        		return new FPCSoultaker(player);
        	case "wlock":
        		return new FPCArcarnaLord(player);
        	case "ps":
        		return new FPCSpectraMaster(player);
        	//Nuker
        	case "soc":
        		return new FPCArchmage(player);
        	case "sps":
        		return new FPCMysticMuse(player);
        	case "sh":
        		return new FPCStormScreamer(player);
        	//Orcish Mystic
        	case "wc":
        		return new FPCDoomcryer(player);
        	case "ol":
        		return new FPCDominator(player);
    		//Ranger
        	case "he":
        		return new FPCSagittarius(player);
        	case "sr":
        		return new FPCMoonlightSentinel(player);
        	case "pr":
        		return new FPCGhostSentinel(player);
        	case "trick":
    			return new FPCTrickster(player);
    		//Dagger
        	case "aw":
        		return new FPCGhostHunter(player);
        	case "th":
        		return new FPCAdventurer(player);
        	case "pw":
        		return new FPCWindRider(player);
        	//Tanker
        	case "pa":
        		return new FPCPhoenixKnight(player);
        	case "da":
        		return new FPCHellKnight(player);
        	case "tk":
        		return new FPCEvaTemplar(player);
        	case "sk":
        		return new FPCShillienTemplar(player);
        	//Warrior
        	case "wlord":
        		return new FPCDreadnought(player);
        	case "gla":
        		return new FPCDuelist(player);
        	//Orcish Warrior
        	case "des":
        		return new FPCTitan(player);
        	case "tyrant":
        		return new FPCGrandKhauatari(player);
        	//Dwarven Warrior
        	case "bh":
        		return new FPCFortuneSeeker(player);
        	case "ws":
        		return new FPCMaestro(player);
        	//Orchestra
        	case "sing":
        		return new FPCSwordMuse(player);
        	case "dance":
        		return new FPCSpectraDancer(player);
        	//Kamael Warrior
        	case "ber":
        		return new FPCDoomBringer(player);
        	case "soul":
        		return new FPCMaleSoulhound(player);
        	case "insp":
        		return new FPCJudicator(player);
        	case "mk":
        		return new MarketFPC(player);
    		default:
            	return null;
    	}
       
	}

	public int getQuotaBase()
	{
		return _quota_base;
	}

	public void setQuotaBase(int _quota_base)
	{
		this._quota_base = _quota_base;
	}

	public int getQuotaAdjustRate()
	{
		return _quota_adjust_rate;
	}

	public void setQuotaAdjustRate(int _quota_adjust_rate)
	{
		this._quota_adjust_rate = _quota_adjust_rate;
	}

	public String getName()
	{
		return _name;
	}

	
}