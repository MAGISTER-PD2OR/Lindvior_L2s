package l2s.gameserver.network.l2.s2c;

public class ExBR_BuyProduct extends L2GameServerPacket
{
	public static final L2GameServerPacket RESULT_OK = new ExBR_BuyProduct(1); // ok
	public static final L2GameServerPacket RESULT_NOT_ENOUGH_POINTS = new ExBR_BuyProduct(-1);
	public static final L2GameServerPacket RESULT_WRONG_PRODUCT = new ExBR_BuyProduct(-2); // also -5
	public static final L2GameServerPacket RESULT_INVENTORY_FULL = new ExBR_BuyProduct(-4);
	public static final L2GameServerPacket RESULT_WRONG_ITEM = new ExBR_BuyProduct(-5);
	public static final L2GameServerPacket RESULT_SALE_PERIOD_ENDED = new ExBR_BuyProduct(-7); // also -8
	public static final L2GameServerPacket RESULT_WRONG_USER_STATE = new ExBR_BuyProduct(-9); // also -11
	public static final L2GameServerPacket RESULT_WRONG_PRODUCT_ITEM = new ExBR_BuyProduct(-10);
	public static final L2GameServerPacket RESULT_WRONG_DAY_OF_WEEK = new ExBR_BuyProduct(-12);
	public static final L2GameServerPacket RESULT_WRONG_SALE_PERIOD = new ExBR_BuyProduct(-13);
	public static final L2GameServerPacket RESULT_ITEM_WAS_SALED = new ExBR_BuyProduct(-14);

	private final int _result;

	public ExBR_BuyProduct(int result)
	{
		_result = result;
	}

	@Override
	protected void writeImpl()
	{
		writeEx(0xD9);
		writeD(_result);
	}
}