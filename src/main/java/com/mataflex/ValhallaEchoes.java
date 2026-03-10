package com.mataflex;

import com.mataflex.entity.MysticalVikingEntity;
import com.mataflex.item.ModItems;
import com.mataflex.sound.CustomSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.level.levelgen.Heightmap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValhallaEchoes implements ModInitializer {
	public static final String MOD_ID = "valhalla-echoes";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final EntityType<MysticalVikingEntity> MYSTICAL_VIKING =
			Registry.register(
					BuiltInRegistries.ENTITY_TYPE,
					Identifier.fromNamespaceAndPath(MOD_ID, "mystical_viking"),
					EntityType.Builder.of(
									MysticalVikingEntity::new,
									MobCategory.MONSTER
							)
							.sized(0.6f, 1.95f)
							.build(ResourceKey.create(
									Registries.ENTITY_TYPE,
									Identifier.fromNamespaceAndPath(MOD_ID, "mystical_viking")
							))
			);

	@Override
	public void onInitialize() {

		LOGGER.info("Welcome to Valhalla Echoes! Initializing mod...");
		ModItems.initialize();
		ModCreativeTabs.register();

		FabricDefaultAttributeRegistry.register(
				MYSTICAL_VIKING,
				Piglin.createAttributes()
		);

		CustomSounds.initialize();

		SpawnPlacements.register(
				MYSTICAL_VIKING,
				SpawnPlacementTypes.ON_GROUND,
				Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				MysticalVikingEntity::checkVikingSpawnRules
		);


		BiomeModifications.addSpawn(
				BiomeSelectors.tag(BiomeTags.IS_TAIGA).or(BiomeSelectors.tag(BiomeTags.IS_MOUNTAIN)),
				MobCategory.MONSTER,
				MYSTICAL_VIKING,
				45,
				1,
				3
		);

	}

}