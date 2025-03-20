package thelm.packagedavaritia.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import thelm.packagedavaritia.tile.EnderCrafterTile;
import thelm.packagedavaritia.tile.ExtremeCrafterTile;
import thelm.packagedavaritia.tile.NetherCrafterTile;

public class PackagedAvaritiaConfig {

	private PackagedAvaritiaConfig() {}

	private static ForgeConfigSpec serverSpec;

	public static ForgeConfigSpec.IntValue netherCrafterEnergyCapacity;
	public static ForgeConfigSpec.IntValue netherCrafterEnergyReq;
	public static ForgeConfigSpec.IntValue netherCrafterEnergyUsage;
	public static ForgeConfigSpec.BooleanValue netherCrafterDrawMEEnergy;

	public static ForgeConfigSpec.IntValue enderCrafterEnergyCapacity;
	public static ForgeConfigSpec.IntValue enderCrafterEnergyReq;
	public static ForgeConfigSpec.IntValue enderCrafterEnergyUsage;
	public static ForgeConfigSpec.BooleanValue enderCrafterDrawMEEnergy;

	public static ForgeConfigSpec.IntValue extremeCrafterEnergyCapacity;
	public static ForgeConfigSpec.IntValue extremeCrafterEnergyReq;
	public static ForgeConfigSpec.IntValue extremeCrafterEnergyUsage;
	public static ForgeConfigSpec.BooleanValue extremeCrafterDrawMEEnergy;

	public static void registerConfig() {
		buildConfig();
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, serverSpec);
	}

	private static void buildConfig() {
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

		builder.push("nether_crafter");
		builder.comment("How much FE the Nether Package Crafter should hold.");
		netherCrafterEnergyCapacity = builder.defineInRange("energy_capacity", 5000, 0, Integer.MAX_VALUE);
		builder.comment("How much total FE the Nether Package Crafter should use per operation.");
		netherCrafterEnergyReq = builder.defineInRange("energy_req", 1000, 0, Integer.MAX_VALUE);
		builder.comment("How much FE/t maximum the Nether Package Crafter can use.");
		netherCrafterEnergyUsage = builder.defineInRange("energy_usage", 125, 0, Integer.MAX_VALUE);
		builder.comment("Should the Nether Package Crafter draw energy from ME systems.");
		netherCrafterDrawMEEnergy = builder.define("draw_me_energy", true);
		builder.pop();

		builder.push("ender_crafter");
		builder.comment("How much FE the Ender Package Crafter should hold.");
		enderCrafterEnergyCapacity = builder.defineInRange("energy_capacity", 5000, 0, Integer.MAX_VALUE);
		builder.comment("How much total FE the Ender Package Crafter should use per operation.");
		enderCrafterEnergyReq = builder.defineInRange("energy_req", 2500, 0, Integer.MAX_VALUE);
		builder.comment("How much FE/t maximum the Ender Package Crafter can use.");
		enderCrafterEnergyUsage = builder.defineInRange("energy_usage", 250, 0, Integer.MAX_VALUE);
		builder.comment("Should the Ender Package Crafter draw energy from ME systems.");
		enderCrafterDrawMEEnergy = builder.define("draw_me_energy", true);
		builder.pop();

		builder.push("extreme_crafter");
		builder.comment("How much FE the Extreme Package Crafter should hold.");
		extremeCrafterEnergyCapacity = builder.defineInRange("energy_capacity", 5000, 0, Integer.MAX_VALUE);
		builder.comment("How much total FE the Extreme Package Crafter should use per operation.");
		extremeCrafterEnergyReq = builder.defineInRange("energy_req", 5000, 0, Integer.MAX_VALUE);
		builder.comment("How much FE/t maximum the Extreme Package Crafter can use.");
		extremeCrafterEnergyUsage = builder.defineInRange("energy_usage", 500, 0, Integer.MAX_VALUE);
		builder.comment("Should the Extreme Package Crafter draw energy from ME systems.");
		extremeCrafterDrawMEEnergy = builder.define("draw_me_energy", true);
		builder.pop();

		serverSpec = builder.build();
	}

	public static void reloadServerConfig() {
		NetherCrafterTile.energyCapacity = netherCrafterEnergyCapacity.get();
		NetherCrafterTile.energyReq = netherCrafterEnergyReq.get();
		NetherCrafterTile.energyUsage = netherCrafterEnergyUsage.get();
		NetherCrafterTile.drawMEEnergy = netherCrafterDrawMEEnergy.get();

		EnderCrafterTile.energyCapacity = enderCrafterEnergyCapacity.get();
		EnderCrafterTile.energyReq = enderCrafterEnergyReq.get();
		EnderCrafterTile.energyUsage = enderCrafterEnergyUsage.get();
		EnderCrafterTile.drawMEEnergy = enderCrafterDrawMEEnergy.get();

		ExtremeCrafterTile.energyCapacity = extremeCrafterEnergyCapacity.get();
		ExtremeCrafterTile.energyReq = extremeCrafterEnergyReq.get();
		ExtremeCrafterTile.energyUsage = extremeCrafterEnergyUsage.get();
		ExtremeCrafterTile.drawMEEnergy = extremeCrafterDrawMEEnergy.get();
	}
}
