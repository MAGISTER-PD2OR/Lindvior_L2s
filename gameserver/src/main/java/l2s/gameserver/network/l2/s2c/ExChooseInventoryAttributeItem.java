package l2s.gameserver.network.l2.s2c;

import gnu.trove.set.TIntSet;
import gnu.trove.set.hash.TIntHashSet;

import l2s.gameserver.model.Player;
import l2s.gameserver.model.base.Element;
import l2s.gameserver.model.items.ItemInstance;
import l2s.gameserver.templates.item.support.AttributeStone;

/**
 * @author Bonux
 */
public class ExChooseInventoryAttributeItem extends L2GameServerPacket
{
	private TIntSet _attributableItems;
	private int _itemId;
	private boolean _isFireStone;
	private boolean _isWaterStone;
	private boolean _isWindStone;
	private boolean _isEarthStone;
	private boolean _isHolyStone;
	private boolean _isDarkStone;
	private int _stoneLvl;

	public ExChooseInventoryAttributeItem(Player player, AttributeStone stone)
	{
		_attributableItems = new TIntHashSet();
		ItemInstance[] items = player.getInventory().getItems();
		for(ItemInstance i : items)
		{
			if(stone.getItemType() != null && stone.getItemType() != i.getTemplate().getQuality())
				continue;

			//TODO: [Bonux] На оффе показываются все шмотки, кроме НГ, но зачем? о.0 если атрибутить можно начиная только с S ранга.
			if(i.canBeAttributed())
				_attributableItems.add(i.getObjectId());
		}

		_itemId = stone.getItemId();
		_stoneLvl = stone.getStoneLevel();

		Element stoneElement = stone.getElement(true);
		_isFireStone = stoneElement == Element.FIRE;
		_isWaterStone = stoneElement == Element.WATER;
		_isWindStone = stoneElement == Element.WIND;
		_isEarthStone = stoneElement == Element.EARTH;
		_isHolyStone = stoneElement == Element.HOLY;
		_isDarkStone = stoneElement == Element.UNHOLY;
	}

	@Override
	protected final void writeImpl()
	{
		writeEx(0x63);
		writeD(_itemId);
		writeD(_isFireStone ? 1 : 0); //fire
		writeD(_isWaterStone ? 1 : 0); // water
		writeD(_isWindStone ? 1 : 0); //wind
		writeD(_isEarthStone ? 1 : 0); //earth
		writeD(_isHolyStone ? 1 : 0); //holy
		writeD(_isDarkStone ? 1 : 0); //dark
		writeD(_stoneLvl); //max enchant lvl
		writeD(_attributableItems.size()); //equipable items count
		for(int itemObjId : _attributableItems.toArray())
		{
			writeD(itemObjId); //itemObjId
		}
	}
}