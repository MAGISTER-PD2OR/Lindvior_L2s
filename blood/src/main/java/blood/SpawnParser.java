package blood;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import l2s.commons.data.xml.AbstractDirParser;
import l2s.commons.geometry.Polygon;
import l2s.gameserver.Config;
import l2s.gameserver.data.xml.holder.NpcHolder;
import l2s.gameserver.data.xml.holder.SpawnHolder;
import l2s.gameserver.model.Territory;
//import l2s.gameserver.model.instances.TrainerInstance;
import l2s.gameserver.model.instances.VillageMasterInstance;
import l2s.gameserver.model.instances.WarehouseInstance;
import l2s.gameserver.templates.npc.NpcTemplate;

import org.dom4j.Element;

/**
 * @author VISTALL
 * @date  18:38/10.12.2010
 */
public final class SpawnParser extends AbstractDirParser<SpawnHolder>
{
	private static final SpawnParser _instance = new SpawnParser();

	public static SpawnParser getInstance()
	{
		return _instance;
	}

	protected SpawnParser()
	{
		super(SpawnHolder.getInstance());
	}

	@Override
	public File getXMLDir()
	{
		return new File(Config.DATAPACK_ROOT, "data/spawn_bak/");
	}

	@Override
	public boolean isIgnored(File f)
	{
		return false;
	}

	@Override
	public String getDTDFileName()
	{
		return "spawn.dtd";
	}

	@SuppressWarnings("unused")
	@Override
	protected void readData(Element rootElement) throws Exception
	{
		Map<String, Territory> territories = new HashMap<String, Territory>();
		for(Iterator<Element> spawnIterator = rootElement.elementIterator(); spawnIterator.hasNext();)
		{
			Element spawnElement = spawnIterator.next();
			
			if(spawnElement.getName().equalsIgnoreCase("spawn"))
			{
				String group = spawnElement.attributeValue("group");
				int respawn = spawnElement.attributeValue("respawn") == null ? 60 : Integer.parseInt(spawnElement.attributeValue("respawn"));
				int respawnRandom = spawnElement.attributeValue("respawn_random") == null ? 0 : Integer.parseInt(spawnElement.attributeValue("respawn_random"));
				int count = spawnElement.attributeValue("count") == null ? 1 : Integer.parseInt(spawnElement.attributeValue("count"));

				for(Iterator<Element> subIterator = spawnElement.elementIterator(); subIterator.hasNext();)
				{
					Element subElement = subIterator.next();
					if(subElement.getName().equalsIgnoreCase("npc"))
					{
						int npcId = Integer.parseInt(subElement.attributeValue("id"));
						
						NpcTemplate npcTemplate = NpcHolder.getInstance().getTemplate(npcId);
						
						if(npcTemplate.getInstanceClass().equals(WarehouseInstance.class) || npcTemplate.getInstanceClass().equals(VillageMasterInstance.class))
						{
							System.out.println(spawnElement.asXML());
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("unused")
	private Territory parseTerritory(String name, Element e)
	{
		Territory t = new Territory();
		t.add(parsePolygon0(name, e));

		for(Iterator<Element> iterator = e.elementIterator("banned_territory"); iterator.hasNext();)
			t.addBanned(parsePolygon0(name, iterator.next()));

		return t;
	}

	private Polygon parsePolygon0(String name, Element e)
	{
		Polygon temp = new Polygon();
		for(Iterator<Element> addIterator = e.elementIterator("add"); addIterator.hasNext();)
		{
			Element addElement = addIterator.next();
			int x = Integer.parseInt(addElement.attributeValue("x"));
			int y = Integer.parseInt(addElement.attributeValue("y"));
			int zmin = Integer.parseInt(addElement.attributeValue("zmin"));
			int zmax = Integer.parseInt(addElement.attributeValue("zmax"));
			temp.add(x, y).setZmin(zmin).setZmax(zmax);
		}

		if(!temp.validate())
			error("Invalid polygon: " + name + "{" + temp + "}. File: " + getCurrentFileName());
		return temp;
	}
}
