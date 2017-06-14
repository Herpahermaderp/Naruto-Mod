package com.cdc.naruto.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSubLog extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape2;

    public ModelSubLog() {
        this.textureWidth = 32;
        this.textureHeight = 20;
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(0.0F, 9.0F, 0.0F);
        this.shape1.addBox(-4.0F, -6.0F, -4.0F, 8, 12, 8, 0.0F);
        this.shape2 = new ModelRenderer(this, 0, 0);
        this.shape2.setRotationPoint(-6.2F, 6.0F, -1.0F);
        this.shape2.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(shape2, 0.0F, 0.0F, -0.5585053606381855F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shape1.render(f5);
        this.shape2.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
