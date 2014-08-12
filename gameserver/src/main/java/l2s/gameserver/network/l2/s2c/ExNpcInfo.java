package l2s.gameserver.network.l2.s2c;

import org.apache.commons.lang3.StringUtils;
import l2s.gameserver.Config;
import l2s.gameserver.model.Creature;
import l2s.gameserver.model.Servitor;
import l2s.gameserver.model.base.TeamType;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.pledge.Alliance;
import l2s.gameserver.model.pledge.Clan;
import l2s.gameserver.network.l2.components.NpcString;
import l2s.gameserver.skills.AbnormalEffect;
import l2s.gameserver.utils.Location;

/**
 * @reworked Bonux
 */
public class ExNpcInfo extends L2GameServerPacket
{
	private boolean can_writeImpl = false;
	private int _npcObjId, _npcId, running, incombat, dead, _showSpawnAnimation;
	private int _runSpd, _walkSpd, _mAtkSpd, _pAtkSpd, _rhand, _lhand, _enchantEffect;
	private int karma, pvp_flag, clan_id, clan_crest_id, ally_id, ally_crest_id, _formId, _titleColor;
	private double colHeight, colRadius, currentColHeight, currentColRadius;
	private double _atkSpdMul, _runSpdMul;
	private boolean _isAttackable, _isNameAbove, isFlying;
	private Location _loc;
	private String _name = StringUtils.EMPTY;
	private String _title = StringUtils.EMPTY;
	private boolean _showName, _targetable;
	private int _state;
	private NpcString _nameNpcString = NpcString.NONE;
	private NpcString _titleNpcString = NpcString.NONE;
	private TeamType _team;
	private int _currentHP, _currentMP, _currentCP, _maxHP, _maxMP, _maxCP, _transformId;
	private AbnormalEffect[] _abnormalEffects;

	public ExNpcInfo(NpcInstance cha, Creature attacker)
	{
		_npcId = cha.getDisplayId() != 0 ? cha.getDisplayId() : cha.getNpcId();
		_isAttackable = attacker != null && cha.isAutoAttackable(attacker);
		_rhand = cha.getRightHandItem();
		_lhand = cha.getLeftHandItem();
		if(Config.SERVER_SIDE_NPC_NAME || cha.getTemplate().displayId != 0 || cha.getName() != cha.getTemplate().name)
			_name = cha.getName();
		if(Config.SERVER_SIDE_NPC_TITLE || cha.getTemplate().displayId != 0 || cha.getTitle() != cha.getTemplate().title)
			_title = cha.getTitle();

		_showSpawnAnimation = cha.getSpawnAnimation();
		_showName = cha.isShowName();
		_targetable = cha.isTargetable(attacker);
		_state = cha.getNpcState();
		_nameNpcString = cha.getNameNpcString();
		_titleNpcString = cha.getTitleNpcString();

		common(cha);
	}

	public ExNpcInfo(Servitor cha, Creature attacker)
	{
		if(cha.getPlayer() != null && cha.getPlayer().isInvisible())
			return;

		_npcId = cha.getNpcId();
		_isAttackable = cha.isAutoAttackable(attacker);
		_rhand = 0;
		_lhand = 0;
		_showName = true;
		_name = cha.getName();
		_title = cha.getTitle();
		_showSpawnAnimation = cha.getSpawnAnimation();

		common(cha);
	}

	private void common(Creature cha)
	{
		colHeight = cha.getCollisionHeight();
		colRadius = cha.getCollisionRadius();
		currentColHeight = cha.getCurrentCollisionHeight();
		currentColRadius = cha.getCurrentCollisionRadius();
		_npcObjId = cha.getObjectId();
		_loc = cha.getLoc();
		_mAtkSpd = cha.getMAtkSpd();
		//
		Clan clan = cha.getClan();
		Alliance alliance = clan == null ? null : clan.getAlliance();
		//
		clan_id = clan == null ? 0 : clan.getClanId();
		clan_crest_id = clan == null ? 0 : clan.getCrestId();
		//
		ally_id = alliance == null ? 0 : alliance.getAllyId();
		ally_crest_id = alliance == null ? 0 : alliance.getAllyCrestId();

		_runSpd = cha.getRunSpeed();
		_walkSpd = cha.getWalkSpeed();
		karma = cha.getKarma();
		pvp_flag = cha.getPvpFlag();
		_pAtkSpd = cha.getPAtkSpd();
		running = cha.isRunning() ? 1 : 0;
		incombat = cha.isInCombat() ? 1 : 0;
		dead = cha.isAlikeDead() ? 1 : 0;
		_abnormalEffects = cha.getAbnormalEffectsArray();
		isFlying = cha.isFlying();
		_team = cha.getTeam();
		_formId = cha.getFormId();
		_isNameAbove = cha.isNameAbove();
		_titleColor = cha.isServitor() ? 1 : 0;

		_currentHP = (int) cha.getCurrentHp();
		_currentMP = (int) cha.getCurrentMp();
		_currentCP = (int) cha.getCurrentCp();
		_maxHP = cha.getMaxHp();
		_maxMP = cha.getMaxMp();
		_maxCP = cha.getMaxCp();

		_atkSpdMul = cha.getAttackSpeedMultiplier();;
		_runSpdMul = cha.getMovementSpeedMultiplier();

		_transformId = cha.getVisualTransformId();

		_enchantEffect = cha.getEnchantEffect();

		can_writeImpl = true;
	}

	public ExNpcInfo update()
	{
		_showSpawnAnimation = 1;
		return this;
	}

	@Override
	protected final void writeImpl()
	{
		if(!can_writeImpl)
			return;

		writeC(0x0c);
		//ddddddddddddddddddffffdddcccccdSdSddddddddccffddddccd
		//ddddddddddddddddddffffdddcccccdSdSddddddddccffddddccdddddddcddd
		writeD(_npcObjId);
		writeD(_npcId + 1000000); // npctype id c4
		writeD(_isAttackable ? 1 : 0);
		writeD(_loc.x);
		writeD(_loc.y);
		writeD(_loc.z + Config.CLIENT_Z_SHIFT);
		writeD(_loc.h);
		writeD(0x00); //UNK
		writeD(_mAtkSpd);
		writeD(_pAtkSpd);
		writeD(_runSpd);
		writeD(_walkSpd);
		writeD(_runSpd /*_swimRunSpd*//*0x32*/); // swimspeed
		writeD(_walkSpd/*_swimWalkSpd*//*0x32*/); // swimspeed
		writeD(_runSpd/*_flRunSpd*/);
		writeD(_walkSpd/*_flWalkSpd*/);
		writeD(_runSpd/*_flyRunSpd*/);
		writeD(_walkSpd/*_flyWalkSpd*/);
		writeF(_runSpdMul);
		writeF(_atkSpdMul);
		writeF(colRadius);
		writeF(colHeight);
		writeD(_rhand); // right hand weapon
		writeD(0); //TODO chest
		writeD(_lhand); // left hand weapon
		writeC(_isNameAbove ? 1 : 0); // 2.2: name above char 1=true ... ??; 2.3: 1 - normal, 2 - dead
		writeC(running);
		writeC(incombat);
		writeC(dead);
		writeC(_showSpawnAnimation); // invisible ?? 0=false  1=true   2=summoned (only works if model has a summon animation)
		writeD(_nameNpcString.getId());
		writeS(_name);
		writeD(_titleNpcString.getId());
		writeS(_title);
		writeD(_titleColor); // 0- светло зеленый титул(моб), 1 - светло синий(пет)/отображение текущего МП
		writeD(pvp_flag);
		writeD(karma); // hmm karma ??
		writeD(clan_id);
		writeD(clan_crest_id);
		writeD(ally_id);
		writeD(ally_crest_id);
		writeD(0x00); // UNK
		writeC(isFlying ? 2 : 0); // C2
		writeC(_team.ordinal()); // team aura 1-blue, 2-red
		writeF(currentColRadius); // тут что-то связанное с colRadius
		writeF(currentColHeight); // тут что-то связанное с colHeight
		writeD(_enchantEffect); // C4
		writeD(0x00); // writeD(_npc.isFlying() ? 1 : 0); // C6
		writeD(0x00);
		writeD(_formId);// great wolf type
		writeC(_targetable ? 0x01 : 0x00); // targetable
		writeC(_showName ? 0x01 : 0x00); // show name
		writeD(_state);
		writeD(_transformId);
		writeD(_currentHP);
		writeD(_maxHP);
		writeD(_currentMP);
		writeD(_maxMP);
		writeD(_currentCP);
		writeD(_maxCP);
		writeH(0x00);
		writeH(0x00);
		writeC(0x00);

		writeD(0x00); // TAUTI
		writeD(0x00); // TAUTI

		writeD(_abnormalEffects.length);
		for(AbnormalEffect abnormal : _abnormalEffects)
			writeD(abnormal.ordinal());
	}
}