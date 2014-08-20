package blood.utils;

public class NameFunctions {

	public static boolean isHumanName(String name) {
		return name.toLowerCase().contains("human")
				|| isWarlordName(name)
				|| isGladiatorName(name)
				|| isPaladinName(name)
				|| isDarkAvengerName(name)
				|| isTreasureHunterName(name)
				|| isHawkeyeName(name)
				|| isSorcererName(name)
				|| isNecromancerName(name)
				|| isWarlockName(name)
				|| isBishopName(name)
				|| isProphetName(name);
	}

	public static boolean isElfName(String name) {
		return (name.toLowerCase().contains("elf") && !name.toLowerCase()
				.contains("darkelf"))
				|| isTempleKnightName(name)
				|| isSingerName(name)
				|| isPlainsWalkerName(name)
				|| isSilverRangerName(name)
				|| isSpellSingerName(name)
				|| isElementalSummonerName(name)
				|| isElvenElderName(name);
	}

	public static boolean isDarkElfName(String name) {
		return name.toLowerCase().contains("darkelf")
				|| isShillenKnightName(name)
				|| isDancerName(name)
				|| isAbyssWalkerName(name)
				|| isPhantomRangerName(name)
				|| isSpellhowlerName(name)
				|| isPhantomSummonerName(name)
				|| isShillienElderName(name);
	}

	public static boolean isOrcName(String name) {
		return name.toLowerCase().contains("orc")
				|| isDestroyerName(name)
				|| isTyrantName(name)
				|| isOverlordName(name)
				|| isWarcryerName(name);
	}

	public static boolean isDwarfName(String name) {
		return name.toLowerCase().contains("lun")
				|| name.toLowerCase().contains("dwarf")
				|| name.toLowerCase().contains("adena")
				|| isWarSmithName(name)
				|| isBountyHunterName(name);
	}

	public static boolean isKamaelName(String name) {
		return name.toLowerCase().contains("kamael")
				|| isBerserkerName(name)
				|| isSoulBreakerName(name)
				|| isTricksterName(name);
	}

	public static boolean isMaleName(String name) {
		return name.toLowerCase().contains("male")
				|| name.toLowerCase().contains("boy")
				|| name.toLowerCase().contains("trai")
				|| (name.toLowerCase().contains("mr") && !name.toLowerCase().contains("mrs")) 
				|| name.toLowerCase().contains("nam")
				|| name.toLowerCase().contains("hoangtu")
				|| name.toLowerCase().contains("king");
	}

	public static boolean isFemaleName(String name) {
		return name.toLowerCase().contains("female")
				|| name.toLowerCase().contains("ngoc")
				|| name.toLowerCase().contains("ngan")
				|| name.toLowerCase().contains("thao")
				|| name.toLowerCase().contains("tuyet")
				|| name.toLowerCase().contains("trinh")
				|| name.toLowerCase().contains("linh")
				|| name.toLowerCase().contains("lan")
				|| name.toLowerCase().contains("ly")
				|| name.toLowerCase().contains("vy")
				|| name.toLowerCase().contains("van")
				|| name.toLowerCase().contains("gai")
				|| name.toLowerCase().contains("cave")
				|| name.toLowerCase().contains("miss")
				|| name.toLowerCase().contains("mrs")
				|| name.toLowerCase().contains("baxa")
				|| name.toLowerCase().contains("congchua")
				|| name.toLowerCase().contains("queen");
	}

	// class by name
	public static boolean isMysticName(String name) {
		return name.toLowerCase().contains("mystic")
				|| isNukerName(name)
				|| isHealerName(name)
				|| isSummonerName(name);
	}

	public static boolean isFighterName(String name) {
		return name.toLowerCase().contains("fighter")
				|| isTankerName(name)
				|| isWarriorName(name)
				|| isDaggerName(name)
				|| isRangerName(name)
				|| isDancerName(name)
				|| isSingerName(name);
	}

	public static boolean isTankerName(String name) {
		return name.toLowerCase().contains("tank")
				|| isPaladinName(name)
				|| isDarkAvengerName(name)
				|| isTempleKnightName(name)
				|| isShillenKnightName(name);
	}

	public static boolean isWarriorName(String name) {
		return name.toLowerCase().contains("warrior")
				|| isWarlordName(name)
				|| isGladiatorName(name)
				|| isDestroyerName(name)
				|| isTyrantName(name)
				|| isWarSmithName(name)
				|| isBerserkerName(name);
	}

	public static boolean isDaggerName(String name) {
		return name.toLowerCase().contains("dagger")
				|| name.toLowerCase().contains("dao")
				|| name.toLowerCase().contains("assasin")
				|| name.toLowerCase().contains("satthu")
				|| isTreasureHunterName(name)
				|| isPlainsWalkerName(name)
				|| isAbyssWalkerName(name)
				|| isBountyHunterName(name);
	}

	public static boolean isRangerName(String name) {
		return name.toLowerCase().contains("ranger")
				|| name.toLowerCase().contains("bow")
				|| name.toLowerCase().contains("cung")
				|| isHawkeyeName(name)
				|| isSilverRangerName(name)
				|| isPhantomRangerName(name)
				|| isTricksterName(name);
	}

	public static boolean isNukerName(String name) {
		return name.toLowerCase().contains("nuker")
				|| name.toLowerCase().contains("phep")
				|| name.toLowerCase().contains("magic")
				|| name.toLowerCase().contains("wiz")
				|| isSorcererName(name)
				|| isNecromancerName(name)
				|| isSpellSingerName(name)
				|| isSpellhowlerName(name)
				|| isSoulBreakerName(name);
	}

	public static boolean isSummonerName(String name) {
		return name.toLowerCase().contains("sum")
				|| isElementalSummonerName(name)
				|| isWarlockName(name)
				|| isPhantomSummonerName(name);
	}

	public static boolean isHealerName(String name) {
		return name.toLowerCase().contains("heal")
				|| isBishopName(name)
				|| isElvenElderName(name)
				|| isShillienElderName(name);
	}

	public static boolean isBufferName(String name) {
		return name.toLowerCase().contains("support")
				|| name.toLowerCase().contains("buff")
				|| name.toLowerCase().contains("iss")
				|| name.toLowerCase().contains("cave")
				|| isSingerName(name)
				|| isDancerName(name)
				|| isProphetName(name)
				|| isOverlordName(name)
				|| isWarcryerName(name);
	}

	// specific class name
	public static boolean isWarlordName(String name) {
		return name.toLowerCase().contains("warlord")
				|| name.toLowerCase().contains("wl");
	}

	public static boolean isGladiatorName(String name) {
		return name.toLowerCase().contains("gla");
	}

	public static boolean isPaladinName(String name) {
		return name.toLowerCase().contains("pa");
	}

	public static boolean isDarkAvengerName(String name) {
		return name.toLowerCase().contains("darkavanger")
				|| name.toLowerCase().contains("da");
	}

	public static boolean isTreasureHunterName(String name) {
		return name.toLowerCase().contains("th");
	}

	public static boolean isHawkeyeName(String name) {
		return name.toLowerCase().contains("he");
	}

	public static boolean isSorcererName(String name) {
		return name.toLowerCase().contains("sorc");
	}

	public static boolean isNecromancerName(String name) {
		return name.toLowerCase().contains("nec");
	}

	public static boolean isWarlockName(String name) {
		return name.toLowerCase().contains("meo")
				|| name.toLowerCase().contains("cat");
	}

	public static boolean isBishopName(String name) {
		return name.toLowerCase().contains("bishop")
				|| name.toLowerCase().contains("bs");
	}

	public static boolean isProphetName(String name) {
		return name.toLowerCase().contains("prophet")
				|| name.toLowerCase().contains("pp");
	}

	public static boolean isTempleKnightName(String name) {
		return name.toLowerCase().contains("tk");
	}

	public static boolean isSingerName(String name) {
		return name.toLowerCase().contains("sing")
				|| name.toLowerCase().contains("song");
	}

	public static boolean isPlainsWalkerName(String name) {
		return name.toLowerCase().contains("pw");
	}

	public static boolean isSilverRangerName(String name) {
		return name.toLowerCase().contains("sr");
	}

	public static boolean isSpellSingerName(String name) {
		return false;
	}

	public static boolean isElementalSummonerName(String name) {
		return name.toLowerCase().contains("ngua");
	}

	public static boolean isElvenElderName(String name) {
		return name.toLowerCase().contains("ee");
	}

	public static boolean isShillenKnightName(String name) {
		return name.toLowerCase().contains("sk");
	}

	public static boolean isDancerName(String name) {
		return name.toLowerCase().contains("dance")
				|| name.toLowerCase().contains("nhay")
				|| name.toLowerCase().contains("mua");
	}

	public static boolean isAbyssWalkerName(String name) {
		return name.toLowerCase().contains("aw");
	}

	public static boolean isPhantomRangerName(String name) {
		return name.toLowerCase().contains("pr");
	}

	public static boolean isSpellhowlerName(String name) {
		return name.toLowerCase().contains("sh");
	}

	public static boolean isPhantomSummonerName(String name) {
		return false;
	}

	public static boolean isShillienElderName(String name) {
		return name.toLowerCase().contains("se");
	}

	public static boolean isDestroyerName(String name) {
		return name.toLowerCase().contains("des");
	}

	public static boolean isTyrantName(String name) {
		return name.toLowerCase().contains("tyr");
	}

	public static boolean isOverlordName(String name) {
		return name.toLowerCase().contains("ol");
	}

	public static boolean isWarcryerName(String name) {
		return name.toLowerCase().contains("wc");
	}

	public static boolean isWarSmithName(String name) {
		return name.toLowerCase().contains("che")
				|| name.toLowerCase().contains("tao")
				|| name.toLowerCase().contains("craft");
	}

	public static boolean isBountyHunterName(String name) {
		return name.toLowerCase().contains("spoil")
				|| name.toLowerCase().contains("bh");
	}

	public static boolean isBerserkerName(String name) {
		return name.toLowerCase().contains("ber");
	}

	public static boolean isSoulBreakerName(String name) {
		return name.toLowerCase().contains("soul");
	}

	public static boolean isTricksterName(String name) {
		return false;
	}

	public static boolean isName(String name) {
		return false;
	}

}
