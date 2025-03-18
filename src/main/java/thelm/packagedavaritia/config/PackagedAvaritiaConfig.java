package thelm.packagedavaritia.config;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import thelm.packagedavaritia.block.entity.ExtremeCrafterBlockEntity;

public class PackagedAvaritiaConfig {

	private PackagedAvaritiaConfig() {}

	private static ModConfigSpec serverSpec;

	public static ModConfigSpec.IntValue extremeCrafterEnergyCapacity;
	public static ModConfigSpec.IntValue extremeCrafterEnergyReq;
	public static ModConfigSpec.IntValue extremeCrafterEnergyUsage;
	public static ModConfigSpec.BooleanValue extremeCrafterDrawMEEnergy;

	public static void registerConfig(ModContainer modContainer) {
		buildConfig();
		modContainer.registerConfig(ModConfig.Type.SERVER, serverSpec);
	}

	private static void buildConfig() {
		ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

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
		ExtremeCrafterBlockEntity.energyCapacity = extremeCrafterEnergyCapacity.get();
		ExtremeCrafterBlockEntity.energyReq = extremeCrafterEnergyReq.get();
		ExtremeCrafterBlockEntity.energyUsage = extremeCrafterEnergyUsage.get();
		ExtremeCrafterBlockEntity.drawMEEnergy = extremeCrafterDrawMEEnergy.get();
	}
}
