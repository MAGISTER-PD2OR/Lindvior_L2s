package l2s.gameserver.network.l2.s2c;

import l2s.gameserver.templates.item.support.AppearanceStone;

/**
 * @author Bonux
**/
public class ExChooseShapeShiftingItem extends L2GameServerPacket
{
	private final int _type;
	private final int _targetType;
	private final int _itemId;

	public ExChooseShapeShiftingItem(AppearanceStone stone)
	{
		_type = stone.getType().ordinal();
		_targetType = stone.getClientTargetType().ordinal();
		_itemId = stone.getItemId();
	}
	
	@Override
	protected void writeImpl()
	{
		writeEx(0x12F);
		writeD(_targetType); //ShapeType
		writeD(_type); //ShapeShiftingType
		writeD(_itemId); //ItemID
	}
}