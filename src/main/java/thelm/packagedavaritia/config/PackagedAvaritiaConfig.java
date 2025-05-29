package thelm.packagedavaritia.config;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import thelm.packagedavaritia.block.entity.EndCrafterBlockEntity;
import thelm.packagedavaritia.block.entity.ExtremeCrafterBlockEntity;
import thelm.packagedavaritia.block.entity.NetherCrafterBlockEntity;
import thelm.packagedavaritia.block.entity.SculkCrafterBlockEntity;

public class PackagedAvaritiaConfig {

	private PackagedAvaritiaConfig() {}

	private static ModConfigSpec serverSpec;

	public static ModConfigSpec.IntValue sculkCrafterEnergyCapacity;
	public static ModConfigSpec.IntValue sculkCrafterEnergyReq;
	public static ModConfigSpec.IntValue sculkCrafterEnergyUsage;
	public static ModConfigSpec.BooleanValue sculkCrafterDrawMEEnergy;

	public static ModConfigSpec.IntValue netherCrafterEnergyCapacity;
	public static ModConfigSpec.IntValue netherCrafterEnergyReq;
	public static ModConfigSpec.IntValue netherCrafterEnergyUsage;
	public static ModConfigSpec.BooleanValue netherCrafterDrawMEEnergy;

	public static ModConfigSpec.IntValue endCrafterEnergyCapacity;
	public static ModConfigSpec.IntValue endCrafterEnergyReq;
	public static ModConfigSpec.IntValue endCrafterEnergyUsage;
	public static ModConfigSpec.BooleanValue endCrafterDrawMEEnergy;

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

		builder.push("sculk_crafter");
		builder.comment("How much FE the Sculk Package Crafter should hold.");
		sculkCrafterEnergyCapacity = builder.defineInRange("energy_capacity", 5000, 0, Integer.MAX_VALUE);
		builder.comment("How much total FE the Sculk Package Crafter should use per operation.");
		sculkCrafterEnergyReq = builder.defineInRange("energy_req", 500, 0, Integer.MAX_VALUE);
		builder.comment("How much FE/t maximum the Sculk Package Crafter can use.");
		sculkCrafterEnergyUsage = builder.defineInRange("energy_usage", 100, 0, Integer.MAX_VALUE);
		builder.comment("Should the Sculk Package Crafter draw energy from ME systems.");
		sculkCrafterDrawMEEnergy = builder.define("draw_me_energy", true);
		builder.pop();

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

		builder.push("end_crafter");
		builder.comment("How much FE the End Package Crafter should hold.");
		endCrafterEnergyCapacity = builder.defineInRange("energy_capacity", 5000, 0, Integer.MAX_VALUE);
		builder.comment("How much total FE the End Package Crafter should use per operation.");
		endCrafterEnergyReq = builder.defineInRange("energy_req", 2500, 0, Integer.MAX_VALUE);
		builder.comment("How much FE/t maximum the End Package Crafter can use.");
		endCrafterEnergyUsage = builder.defineInRange("energy_usage", 250, 0, Integer.MAX_VALUE);
		builder.comment("Should the End Package Crafter draw energy from ME systems.");
		endCrafterDrawMEEnergy = builder.define("draw_me_energy", true);
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
		SculkCrafterBlockEntity.energyCapacity = sculkCrafterEnergyCapacity.get();
		SculkCrafterBlockEntity.energyReq = sculkCrafterEnergyReq.get();
		SculkCrafterBlockEntity.energyUsage = sculkCrafterEnergyUsage.get();
		SculkCrafterBlockEntity.drawMEEnergy = sculkCrafterDrawMEEnergy.get();

		NetherCrafterBlockEntity.energyCapacity = netherCrafterEnergyCapacity.get();
		NetherCrafterBlockEntity.energyReq = netherCrafterEnergyReq.get();
		NetherCrafterBlockEntity.energyUsage = netherCrafterEnergyUsage.get();
		NetherCrafterBlockEntity.drawMEEnergy = netherCrafterDrawMEEnergy.get();

		EndCrafterBlockEntity.energyCapacity = endCrafterEnergyCapacity.get();
		EndCrafterBlockEntity.energyReq = endCrafterEnergyReq.get();
		EndCrafterBlockEntity.energyUsage = endCrafterEnergyUsage.get();
		EndCrafterBlockEntity.drawMEEnergy = endCrafterDrawMEEnergy.get();

		ExtremeCrafterBlockEntity.energyCapacity = extremeCrafterEnergyCapacity.get();
		ExtremeCrafterBlockEntity.energyReq = extremeCrafterEnergyReq.get();
		ExtremeCrafterBlockEntity.energyUsage = extremeCrafterEnergyUsage.get();
		ExtremeCrafterBlockEntity.drawMEEnergy = extremeCrafterDrawMEEnergy.get();
	}
}
