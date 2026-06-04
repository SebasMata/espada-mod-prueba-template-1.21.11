package com.mataflex.entity;

import com.mataflex.ValhallaEchoes;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WitherSkeletonRenderer;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.Identifier;

public class DraugrRenderer extends WitherSkeletonRenderer {

    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(
                    ValhallaEchoes.MOD_ID,
                    "textures/entity/draugr.png"
            );

    public DraugrRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public Identifier getTextureLocation(SkeletonRenderState renderState) {
        return TEXTURE;
    }
}
