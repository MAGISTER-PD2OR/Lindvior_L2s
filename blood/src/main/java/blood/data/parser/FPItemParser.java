package blood.data.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import l2s.commons.data.xml.AbstractFileParser;
import l2s.gameserver.Config;

import org.dom4j.Element;

import blood.data.holder.FPItemHolder;
import blood.model.FPRewardData;
import blood.model.FPRewardList;

public final class FPItemParser extends AbstractFileParser<FPItemHolder> 
{
	/**
	 * Field _instance.
	 */
	private static final FPItemParser _instance = new FPItemParser();
	
	/**
	 * Method getInstance.
	 * @return LevelBonusParser
	 */
	public static FPItemParser getInstance()
	{
		return _instance;
	}
	
	/**
	 * Constructor for LevelBonusParser.
	 */
	private FPItemParser()
	{
		super(FPItemHolder.getInstance());
	}
	
	/**
	 * Method getXMLFile.
	 * @return File
	 */
	@Override
	public File getXMLFile()
	{
		return new File(Config.DATAPACK_ROOT, "data/blood/fp_item.xml");
	}
	
	/**
	 * Method getDTDFileName.
	 * @return String
	 */
	@Override
	public String getDTDFileName()
	{
		return "fp_item.dtd";
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
			Element rewardElement = iterator.next();
			int lvl = Integer.parseInt(rewardElement.attributeValue("level"));
			List<FPRewardList> levelList = new ArrayList<FPRewardList>(); 
			for (Element classElement : rewardElement.elements())
			{
				boolean is_mage = Boolean.parseBoolean(classElement.attributeValue("is_mage"));
				
				FPRewardList rewardList = new FPRewardList(is_mage);
				String[] class_ids_str = classElement.attributeValue("ids").split(",");
				
				for(int i = 0;i < class_ids_str.length;i++)
				{
					rewardList.addClass(Integer.parseInt(class_ids_str[i]));
				}
				
				for (Element itemElement: classElement.elements())
				{
					int item_id = Integer.parseInt(itemElement.attributeValue("id"));
					int item_count = Integer.parseInt(itemElement.attributeValue("count"));
					rewardList.addItem(new FPRewardData(item_id, item_count));
				}
				
				levelList.add(rewardList);
			}
			getHolder().addLevelBonus(lvl, levelList);
		}
	}
}
