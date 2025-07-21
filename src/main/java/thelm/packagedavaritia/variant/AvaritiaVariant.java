package thelm.packagedavaritia.variant;

public enum AvaritiaVariant {

	MORPH("Avaritia 1.1x", "PackagedAvaritia:1.1x", "morph.avaritia.Avaritia"),
	UNIVERSAL("Avaritia Universal", "PackagedAvaritia:Universal", "avaritia.Avaritia"),
	RE("Re:Avaritia", "PackagedAvaritia:Re", "nova.committee.avaritia.Avaritia"),
	//ENDLESS("Avaritia Endless", "PackagedAvaritia:Endless", "com.yuo.endless.Endless"),

	DIAMONDAY("Avaritia 1.2x", null, "diamonday.AvaritiaMod"),
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
