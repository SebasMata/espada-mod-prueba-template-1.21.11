package com.mataflex;

import com.mataflex.entity.MysticalVikingRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class EspadaModPruebaClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(
				EspadaModPrueba.MYSTICAL_VIKING,
				MysticalVikingRenderer::new
		);
	}
}