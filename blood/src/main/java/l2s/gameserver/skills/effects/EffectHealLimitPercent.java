package l2s.gameserver.skills.effects;

import l2s.gameserver.model.actor.instances.creature.Effect;
import l2s.gameserver.network.l2.components.SystemMsg;
import l2s.gameserver.network.l2.s2c.SystemMessage2;
import l2s.gameserver.stats.Env;
import l2s.gameserver.stats.Stats;
import l2s.gameserver.templates.skill.EffectTemplate;

/**
 * Created by Archer on 8/15/2014.
 * Project
 */
public class EffectHealLimitPercent extends Effect
{
    public EffectHealLimitPercent(Env env, EffectTemplate template)
    {
        super(env, template);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @Override
    protected void onExit()
    {
        super.onExit();
    }

    @Override
    protected boolean onActionTime()
    {
        return false;
    }
}
