package com.cdc.naruto.handlers;

import com.cdc.naruto.Naruto;
import com.cdc.naruto.client.render.EntitySubLogRender;
import com.cdc.naruto.entity.jutsu.EntitySubLog;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityHandler {
    private static int id = 0;

    public static void initRenderers() {
        //registerEntityRenderer(EntityChidori.class, EntityChidoriRenderer.FACTORY);
        registerEntityRenderer(EntitySubLog.class, EntitySubLogRender.FACTORY);
    }
    
    public static void initEntities() {
        //registerEntity(new ResourceLocation("entity/jutsu/chidori"), EntityChidori.class, "chidori", Naruto.INSTANCE, 100, 10, false);
        registerEntity(new ResourceLocation("cdc:entity/substitute/log"), EntitySubLog.class, "sublog", Naruto.INSTANCE, 100, 10, true);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	private static void registerEntityRenderer(Class clazz, IRenderFactory<? extends Entity> factory){
        RenderingRegistry.registerEntityRenderingHandler(clazz, factory);
    }

    private static void registerEntity(ResourceLocation resloc, Class<? extends Entity> entity, String name, Object instance, int trackingrange, int updatefrequency, boolean sendsVelocityUpdate){
        EntityRegistry.registerModEntity(resloc, entity, name, id++, instance, trackingrange, updatefrequency, sendsVelocityUpdate);
    }
}
