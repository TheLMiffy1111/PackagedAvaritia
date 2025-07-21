package thelm.packagedavaritia.variant;

public enum AvaritiaVariant {

	NEO("AvaritiaNeo", "PackagedAvaritia:Neo", "net.byAqua3.avaritia.Avaritia"),
	RE("Re:Avaritia", "PackagedAvaritia:Re", "committee.nova.mods.avaritia.Avaritia"),

	X("Avaritia X", null, "com.coderdan.avaritia.Avaritia"),

	LITE("Avaritia Lite", null, "net.bullfighter.avaritia.AvaritiaMod"),
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
