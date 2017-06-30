package com.cdc.naruto.client.render;

import javax.annotation.Nullable;

import com.cdc.naruto.client.model.ModelSubLog;
import com.cdc.naruto.entity.jutsu.EntitySubLog;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class EntitySubLogRender extends RenderLiving<EntitySubLog> {
	public static final Factory FACTORY = new Factory();
	
	protected EntitySubLogRender(RenderManager manager) {
		super(manager, new ModelSubLog(), 1.0F);
	}
	
	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntitySubLog entity) {
		return new ResourceLocation("textures/entity/substitute/log.png");
	}
	
	public static class Factory implements IRenderFactory<EntitySubLog> {
		@Override
		public RenderLiving<? super EntitySubLog> createRenderFor(RenderManager manager) {
			return new EntitySubLogRender(manager);
		}
	}
}
