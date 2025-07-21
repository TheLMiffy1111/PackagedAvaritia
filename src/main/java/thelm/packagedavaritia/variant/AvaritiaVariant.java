package thelm.packagedavaritia.variant;

public enum AvaritiaVariant {

	UNIVERSAL("Avaritia Universal", "PackagedAvaritia:Universal", "avaritia.Avaritia"),
	//ENDLESS("Avaritia Endless", "PackagedAvaritia:Endless", "com.yuo.endless.Endless"),

	LITE("Avaritia Lite", null, "net.bullfighter.avaritia.AvaritiaMod"),
	//REMASTERED("Avaritia Remastered", null, "net.mcreator.avaritiaremastered.AvaritiaRemasteredMod"),
	;

	public final String avaritiaVariant;
	public final String modVariant;
	public final String mainClass;

	AvaritiaVariant(String avaritiaVariant, String modVariant, String mainClass) {
		this.avaritiaVariant = avaritiaVariant;
		this.modVariant = modVariant;
		this.mainClass = mainClass;
	}
}
