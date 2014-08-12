package l2s.gameserver.network.l2.s2c;

import java.util.ArrayList;
import java.util.List;

public class ExAbnormalStatusUpdateFromTarget extends L2GameServerPacket
{
	public static final int INFINITIVE_EFFECT = -1;
	private List<Effect> _effects;
	private int _objectId;

	class Effect
	{
		int skillId;
		int dat;
		int duration;
		int effectorObjectId;
		int comboId;

		public Effect(int effectorObjectId, int skillId, int dat, int duration, int comboId)
		{
			this.skillId = skillId;
			this.dat = dat;
			this.duration = duration;
			this.effectorObjectId = effectorObjectId;
			this.comboId = comboId;
		}
	}

	public ExAbnormalStatusUpdateFromTarget(int objId)
	{
		_objectId = objId;
		_effects = new ArrayList<Effect>();
	}

	public void addEffect(int effectorObjectId, int skillId, int dat, int duration, int comboId)
	{
		_effects.add(new Effect(effectorObjectId, skillId, dat, duration, comboId));
	}

	@Override
	protected final void writeImpl()
	{
		writeEx(0xE6);
		writeD(_objectId);
		writeH(_effects.size());
		for(Effect temp : _effects)
		{
			writeD(temp.skillId);
			writeH(temp.dat);
			writeD(temp.comboId); // combo type ???
			writeD(temp.duration);
			writeD(temp.effectorObjectId); // Buffer OID
		}
	}
}