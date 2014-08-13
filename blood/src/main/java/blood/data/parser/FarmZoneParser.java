package blood.data.parser;

import java.io.File;
import java.util.Iterator;

import l2s.commons.data.xml.AbstractFileParser;
import l2s.gameserver.Config;
import l2s.gameserver.utils.Location;

import org.dom4j.Element;

import blood.data.holder.FarmZoneHolder;
import blood.model.FarmZone;

public final class FarmZoneParser  extends AbstractFileParser<FarmZoneHolder>{
	/**
	 * Field _instance.
	 */
	private static final FarmZoneParser _instance = new FarmZoneParser();
	
	/**
	 * Method getInstance.
	 * @return LevelBonusParser
	 */
	public static FarmZoneParser getInstance()
	{
		return _instance;
	}
	
	/**
	 * Constructor for LevelBonusParser.
	 */
	private FarmZoneParser()
	{
		super(FarmZoneHolder.getInstance());
	}
	
	/**
	 * Method getXMLFile.
	 * @return File
	 */
	@Override
	public File getXMLFile()
	{
		return new File(Config.DATAPACK_ROOT, "data/blood/fp_loc.xml");
	}
	
	/**
	 * Method getDTDFileName.
	 * @return String
	 */
	@Override
	public String getDTDFileName()
	{
		return "fp_loc.dtd";
	}
	
	/**
	 * Method readData.
	 * @param rootElement Element
	 * @throws Exception
	 */
	@Override
	protected void readData(Element rootElement) throws Exception
	{
		for (Iterator<Element> iterator = rootElement.elementIterator(); iterator.hasNext();)
		{
			Element groupElement = iterator.next();
			int min_level = Integer.parseInt(groupElement.attributeValue("min_level"));
			int max_level = Integer.parseInt(groupElement.attributeValue("max_level"));
			String[] class_ids_str = groupElement.attributeValue("class_ids").split(",");
			FarmZone farmZone = new FarmZone(min_level, max_level);
			
			for(int i = 0;i < class_ids_str.length;i++)
			{
				farmZone.addClass(Integer.parseInt(class_ids_str[i]));
			}
			 
			for (Element locElement : groupElement.elements())
			{
				int x = Integer.parseInt(locElement.attributeValue("x"));
				int y = Integer.parseInt(locElement.attributeValue("y"));
				int z = Integer.parseInt(locElement.attributeValue("z"));
				
				farmZone.addLocation(new Location(x, y, z));
			}
			getHolder().addZone(farmZone);
		}
	}

}
