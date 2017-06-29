package com.cdc.naruto.client.render;

import javax.annotation.Nullable;

import com.cdc.naruto.entity.EntitySubLog;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class EntitySubLogRender extends Render<EntitySubLog> {
	public static final Factory FACTORY = new Factory();
	
	protected EntitySubLogRender(RenderManager manager) {
		super(manager);
	}
	
	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntitySubLog entity) {
		return new ResourceLocation("textures/entity/substitute/log.png");
	}
	
	public static class Factory implements IRenderFactory<EntitySubLog> {
		@Override
		public Render<? super EntitySubLog> createRenderFor(RenderManager manager) {
			return new EntitySubLogRender(manager);
		}
	}
}
