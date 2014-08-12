package l2s.gameserver.network.l2.s2c;

public class TutorialShowHtml extends L2GameServerPacket
{
	public static int NORMAL_WINDOW = 0x01;
	public static int LARGE_WINDOW = 0x02;

	private int _windowType;
	private String _html;

	public TutorialShowHtml(int windowType, String html)
	{
		_windowType = windowType;
		_html = html;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xa6);
		writeD(_windowType);
		writeS(_html);
	}
}