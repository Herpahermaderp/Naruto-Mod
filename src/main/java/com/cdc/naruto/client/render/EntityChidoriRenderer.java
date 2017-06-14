package com.cdc.naruto.client.render;

import com.cdc.naruto.entity.EntityChidori;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class EntityChidoriRenderer extends Render<EntityChidori> {

    public static final Factory FACTORY = new Factory();

    protected EntityChidoriRenderer(RenderManager renderManager) {
        super(renderManager);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityChidori entity) {
        return new ResourceLocation("textures/entity/jutsu/chidori");
    }

    public static class Factory implements IRenderFactory<EntityChidori>{

        @Override
        public Render<? super EntityChidori> createRenderFor(RenderManager manager) {
            return new EntityChidoriRenderer(manager);
        }
    }
}
