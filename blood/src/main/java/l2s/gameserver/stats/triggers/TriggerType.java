package l2s.gameserver.stats.triggers;

/**
* @author VISTALL
* @date 15:05/22.01.2011
*/
public enum TriggerType
{
	ADD, // Срабатывает при добавлении скилла.
	ATTACK,
	RECEIVE_DAMAGE,
	CRIT,
	OFFENSIVE_PHYSICAL_SKILL_USE,
	OFFENSIVE_MAGICAL_SKILL_USE,
	SUPPORT_MAGICAL_SKILL_USE,
	UNDER_MISSED_ATTACK,
	DIE,
	IDLE, // Fires every time after a certain time. As the timer used cool down skills is bound to trigger.
    HAS_SUMMON,
	ON_START_EFFECT, // ​​triggered when starting the effect.
	ON_EXIT_EFFECT, // triggered by the completion of the effect (by any means: time out, force, etc.).
	ON_FINISH_EFFECT, // triggered by the completion time of the effect.
	ON_SUCCES_FINISH_CAST, // ​​Fires after successfully using this ability.
	ON_ENTER_WORLD; //Triggered when entering the game.
}
