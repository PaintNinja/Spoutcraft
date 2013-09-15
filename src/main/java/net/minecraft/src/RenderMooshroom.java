package net.minecraft.src;

// MCPatcher Start

import org.lwjgl.opengl.GL11;

import com.prupe.mcpatcher.mob.MobOverlay;

// MCPatcher End

public class RenderMooshroom extends RenderLiving {
	private static final ResourceLocation field_110880_a = new ResourceLocation("textures/entity/cow/mooshroom.png");
	public RenderMooshroom(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
	}

	public void renderLivingMooshroom(EntityMooshroom par1EntityMooshroom, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(par1EntityMooshroom, par2, par4, par6, par8, par9);
	}

	protected ResourceLocation func_110879_a(EntityMooshroom par1EntityMooshroom) {
		return field_110880_a;
	}
	
	protected void renderMooshroomEquippedItems(EntityMooshroom par1EntityMooshroom, float par2) {
		super.renderEquippedItems(par1EntityMooshroom, par2);

		// MCPatcher Start
		if (par1EntityMooshroom.isChild()) {
			MobOverlay.finishMooshroom();
		} else {
			this.func_110776_a(MobOverlay.setupMooshroom(par1EntityMooshroom, TextureMap.field_110575_b));
		// MCPatcher End
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glPushMatrix();
			GL11.glScalef(1.0F, -1.0F, 1.0F);
			GL11.glTranslatef(0.2F, 0.4F, 0.5F);
			GL11.glRotatef(42.0F, 0.0F, 1.0F, 0.0F);
			// MCPatcher Start
			if (!MobOverlay.renderMooshroomOverlay()) {
				this.renderBlocks.renderBlockAsItem(Block.mushroomRed, 0, 1.0F);
			}
			// MCPatcher End
			GL11.glTranslatef(0.1F, 0.0F, -0.6F);
			GL11.glRotatef(42.0F, 0.0F, 1.0F, 0.0F);
			// MCPatcher Start
			if (!MobOverlay.renderMooshroomOverlay()) {
				this.renderBlocks.renderBlockAsItem(Block.mushroomRed, 0, 1.0F);
			}
			// MCPatcher End
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			((ModelQuadruped)this.mainModel).head.postRender(0.0625F);
			GL11.glScalef(1.0F, -1.0F, 1.0F);
			GL11.glTranslatef(0.0F, 0.75F, -0.2F);
			GL11.glRotatef(12.0F, 0.0F, 1.0F, 0.0F);
			// MCPatcher Start
			if (!MobOverlay.renderMooshroomOverlay()) {
				this.renderBlocks.renderBlockAsItem(Block.mushroomRed, 0, 1.0F);
			}
			// MCPatcher End
			GL11.glPopMatrix();
			GL11.glDisable(GL11.GL_CULL_FACE);
			// MCPatcher Start
			MobOverlay.finishMooshroom();
			// MCPatcher End
		}
	}

	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
		this.renderLivingMooshroom((EntityMooshroom)par1EntityLiving, par2, par4, par6, par8, par9);
	}
	
	protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2) {
		this.renderMooshroomEquippedItems((EntityMooshroom)par1EntityLivingBase, par2);
	}

	public void renderPlayer(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
		this.renderLivingMooshroom((EntityMooshroom)par1EntityLivingBase, par2, par4, par6, par8, par9);
	}

	protected ResourceLocation func_110775_a(Entity par1Entity) {
		return this.func_110879_a((EntityMooshroom)par1Entity);
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
	 * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
	 * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
	 * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.renderLivingMooshroom((EntityMooshroom)par1Entity, par2, par4, par6, par8, par9);
	}
}
