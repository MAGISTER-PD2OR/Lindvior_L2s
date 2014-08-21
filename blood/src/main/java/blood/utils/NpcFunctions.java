package blood.utils;

import l2s.gameserver.model.GameObject;
import l2s.gameserver.model.GameObjectsStorage;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.utils.Location;

public class NpcFunctions {
	
	public static final int 	NPC_BUFFER 		= 32327;
	public static final int 	NPC_MILIA 		= 30006; // GK TI
	public static final int 	NPC_TRISHA 		= 30059; // GK Dion
	public static final int 	NPC_CLARISSA 	= 30080; // GK Giran
	public static final int 	NPC_KARIN 		= 30162; // GK Ivory
	public static final int 	NPC_VALENTINA 	= 30177; // GK Oren
	public static final int 	NPC_ESMERALDA 	= 30233; // GK Hunter
	public static final int 	NPC_BELLA 		= 30256; // GK Gludio
	public static final int 	NPC_RICHLIN 	= 30320; // GK Gludin
	public static final int 	NPC_MINERVA 	= 30836; // GK Hardin
	public static final int 	NPC_ELISA 		= 30848; // GK Aden
	public static final int 	NPC_ANGELINA 	= 30878; // GK Giran Harbor
	public static final int 	NPC_FLAUEN 		= 30899; // GK Heine
	public static final int 	NPC_TATIANA 	= 31275; // GK Goddard
	public static final int 	NPC_ILYANA 		= 31320; // GK Rune
	public static final int 	NPC_BILIA 		= 31964; // GK Shuttgard
	public static final int[] 	NPC_GKs			= new int[]{
		NPC_MILIA,
		NPC_TRISHA,
		NPC_CLARISSA,
		NPC_KARIN,
		NPC_VALENTINA,
		NPC_ESMERALDA,
		NPC_BELLA,
		NPC_RICHLIN,
		NPC_MINERVA,
		NPC_ELISA,
		NPC_ANGELINA,
		NPC_FLAUEN,
		NPC_TATIANA,
		NPC_ILYANA,
		NPC_BILIA,
		};

	public static NpcInstance getNearestNPCById(Location loc, int npcId)
	{
		double minDistance = Double.MAX_VALUE;
		NpcInstance nearestNpc = null;
		for(NpcInstance npc: GameObjectsStorage.getAllByNpcId(npcId, true))
		{
			double disatance = npc.getDistance(loc); 
			if(disatance < minDistance)
			{
				minDistance = disatance;
				nearestNpc = npc;
			}
		}
		
		return nearestNpc;
	}
	
	public static NpcInstance getNearestNPCById(Location loc, int[] npcIds)
	{
		double minDistance = Double.MAX_VALUE;
		NpcInstance nearestNpc = null;
		for(NpcInstance npc: GameObjectsStorage.getAllByNpcId(npcIds, true))
		{
			double disatance = loc.distance(npc.getLoc());
			System.out.println("getNearestNPCById: "+npc+" distance:"+disatance);
			if(disatance < minDistance)
			{
				minDistance = disatance;
				nearestNpc = npc;
			}
		}
		
		return nearestNpc;
	}
	
	public static NpcInstance getNearestNPCById(GameObject obj, int npcId)
	{
		return getNearestNPCById(obj.getLoc(), npcId);
	}
	
	public static NpcInstance getNearestNPCById(GameObject obj, int[] npcIds)
	{
		return getNearestNPCById(obj.getLoc(), npcIds);
	}
	
	public static NpcInstance getNearestBuffer(Location loc)
	{
		return getNearestNPCById(loc, NPC_BUFFER);
	}
	
	public static NpcInstance getNearestBuffer(GameObject obj)
	{
		return getNearestNPCById(obj.getLoc(), NPC_BUFFER);
	}
	
	public static NpcInstance getNearestGatekeeper(Location loc)
	{
		return getNearestNPCById(loc, NPC_GKs);
	}
	
	public static NpcInstance getNearestGatekeeper(GameObject obj)
	{
		return getNearestNPCById(obj.getLoc(), NPC_GKs);
	}

}
