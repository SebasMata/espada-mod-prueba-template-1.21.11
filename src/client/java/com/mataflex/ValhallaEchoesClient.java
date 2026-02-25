package com.mataflex;

import com.mataflex.entity.MysticalVikingRenderer;
import com.mataflex.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.resources.Identifier;

public class ValhallaEchoesClient implements ClientModInitializer {

	// 1. Registramos el "ID" de tu modelo 3D
	public static final net.minecraft.client.model.geom.ModelLayerLocation HORNED_HELMET_LAYER =
			new net.minecraft.client.model.geom.ModelLayerLocation(
					Identifier.fromNamespaceAndPath(ValhallaEchoes.MOD_ID, "horned_helmet"), "main"
			);

	@Override
	public void onInitializeClient() {

		// Tu registro del Vikingo (El tachado de deprecated no afecta, funcionará igual)
		EntityRendererRegistry.register(
				ValhallaEchoes.MYSTICAL_VIKING,
				MysticalVikingRenderer::new
		);

		// 2. Registramos la "geometría" del casco
		EntityModelLayerRegistry.registerModelLayer(
				HORNED_HELMET_LAYER,
				com.mataflex.model.HornedHelmetModel::createArmorLayer
		);

		// 3. El Renderizador (Ahora sí, toda la lógica DENTRO del método render)
		net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer.register((poseStack, submitNodeCollector, itemStack, humanoidRenderState, equipmentSlot, light, humanoidModel) -> {

			// 1. Cargamos tu modelo
			com.mataflex.model.HornedHelmetModel helmetModel = new com.mataflex.model.HornedHelmetModel(
					net.minecraft.client.Minecraft.getInstance().getEntityModels().bakeLayer(HORNED_HELMET_LAYER)
			);

			// 🌟 EL ARREGLO: Apagamos el resto del cuerpo para que no sea un bloque gigante
			helmetModel.body.visible = false;
			helmetModel.rightArm.visible = false;
			helmetModel.leftArm.visible = false;
			helmetModel.rightLeg.visible = false;
			helmetModel.leftLeg.visible = false;
			helmetModel.hat.visible = false;
			helmetModel.head.visible = true; // ¡Solo queremos la cabeza y los cuernos!

			// 2. Definimos la textura (Asegúrate de que la carpeta en assets se llame "valhalla_echoes" con guion bajo)
			net.minecraft.resources.Identifier texture = net.minecraft.resources.Identifier.fromNamespaceAndPath(
					com.mataflex.ValhallaEchoes.MOD_ID,
					"textures/entity/equipment/humanoid/horned_helmet_texture.png"
			);

			// 3. Obtenemos el RenderType automáticamente
			net.minecraft.client.renderer.rendertype.RenderType renderLayer = helmetModel.renderType(texture);

			// 4. Transformamos y dibujamos
			if (submitNodeCollector instanceof net.minecraft.client.renderer.OrderedSubmitNodeCollector orderedCollector) {
				orderedCollector.submitModel(
						helmetModel,
						humanoidRenderState,
						poseStack,
						renderLayer,
						light,
						net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY,
						0xFFFFFFFF,
						null
				);
			}

		}, ModItems.HORNED_HELMET);

	}
}