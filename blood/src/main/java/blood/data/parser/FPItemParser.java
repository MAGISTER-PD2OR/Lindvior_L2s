package blood.data.parser;

import java.io.File;
import java.util.Iterator;

import l2s.commons.data.xml.AbstractFileParser;
import l2s.gameserver.Config;

import org.dom4j.Element;

import blood.data.holder.FPItemHolder;
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
			
			int reward_id = Integer.parseInt(rewardElement.attributeValue("id"));
			int min_level = Integer.parseInt(rewardElement.attributeValue("min_level"));
			int max_level = Integer.parseInt(rewardElement.attributeValue("max_level"));
			int weight = rewardElement.attributeValue("weight") != null ? Integer.parseInt(rewardElement.attributeValue("weight")) : 1;
			boolean is_mage = rewardElement.attributeValue("is_mage") != null ? Boolean.parseBoolean(rewardElement.attributeValue("is_mage")) : false;
			boolean is_abstract = rewardElement.attributeValue("is_abstract") != null ? Boolean.parseBoolean(rewardElement.attributeValue("is_abstract")) : false;
			int parent_id = rewardElement.attributeValue("parent_id") != null ? Integer.parseInt(rewardElement.attributeValue("parent_id")) : 0;
			
			FPRewardList rewardList = new FPRewardList(reward_id, min_level, max_level, parent_id, weight, is_abstract, is_mage);
			
			for (Element subElement: rewardElement.elements())
			{
				int sub_id = Integer.parseInt(subElement.attributeValue("id"));
				
				if(subElement.getName().equalsIgnoreCase("remove_item")){
					rewardList.addRemoveItem(sub_id);
				}
				
				if(subElement.getName().equalsIgnoreCase("classid")){
					rewardList.addClass(sub_id);
				}
				
				if(subElement.getName().equalsIgnoreCase("item")){
					int item_count = Integer.parseInt(subElement.attributeValue("count"));
					rewardList.addItem(sub_id, item_count);
				}
			}
			
			getHolder().add(reward_id, rewardList);
		}
	}
}
