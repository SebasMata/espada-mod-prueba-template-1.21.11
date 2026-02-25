package com.mataflex;

import com.mataflex.entity.MysticalVikingRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.resources.Identifier;

public class ValhallaEchoesClient implements ClientModInitializer {

	public static final net.minecraft.client.model.geom.ModelLayerLocation HORNED_HELMET_LAYER =
			new net.minecraft.client.model.geom.ModelLayerLocation(
					Identifier.fromNamespaceAndPath(ValhallaEchoes.MOD_ID, "horned_helmet"), "main"
			);

	@Override
	public void onInitializeClient() {

		EntityRendererRegistry.register(
				ValhallaEchoes.MYSTICAL_VIKING,
				MysticalVikingRenderer::new
		);

		EntityModelLayerRegistry.registerModelLayer(
				HORNED_HELMET_LAYER,
				com.mataflex.model.HornedHelmetModel::createArmorLayer
		);

		ArmorRenderer.register((poseStack, submitNodeCollector, itemStack, humanoidRenderState, equipmentSlot, light, humanoidModel) -> {

			com.mataflex.model.HornedHelmetModel helmetModel = new com.mataflex.model.HornedHelmetModel(
					net.minecraft.client.Minecraft.getInstance().getEntityModels().bakeLayer(HORNED_HELMET_LAYER)
			);

			helmetModel.head.visible = true;

			Identifier texture = Identifier.fromNamespaceAndPath(
					ValhallaEchoes.MOD_ID,
					"textures/entity/equipment/humanoid/horned_helmet_texture.png"
			);

			net.minecraft.client.renderer.rendertype.RenderType renderLayer = helmetModel.renderType(texture);

			net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer.submitTransformCopyingModel(
					humanoidModel,
					humanoidRenderState,
					helmetModel,
					humanoidRenderState,
					true,
					submitNodeCollector,
					poseStack,
					renderLayer,
					light,
					net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY,
					0xFFFFFFFF,
					null
			);

		}, com.mataflex.item.ModItems.HORNED_HELMET);
	}
}