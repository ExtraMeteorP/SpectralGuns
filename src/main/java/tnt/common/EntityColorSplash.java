package tnt.common;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tnt.TNTMain;
import tnt.client.TNTDamageSource;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityColorSplash extends Entity
{
    private int xTile;
    private int yTile;
    private int zTile;
    private int inTile;
    private int inData;
    private boolean inGround;
    public boolean doesArrowBelongToPlayer;
    public int arrowShake;
    public Entity shootingEntity;
    private int ticksInGround;
    private int ticksInAir;
    public boolean field_35140_d;

    public EntityColorSplash(World world)
    {
        super(world);
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.inTile = 0;
        this.inData = 0;
        this.inGround = false;
        this.arrowShake = 0;
        this.ticksInAir = 0;
        this.doesArrowBelongToPlayer = false;
        this.arrowShake = 0;
        this.ticksInAir = 0;
        this.field_35140_d = false;
        this.setSize(0.5F, 0.5F);
        this.renderDistanceWeight = 10.0D;
    }

   

    public EntityColorSplash(World world, EntityLivingBase entityliving, float speed, int color1, int color2, int color3)
    {
        super(world);
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.inTile = 0;
        this.inData = 0;
        this.inGround = false;
        this.arrowShake = 0;
        this.ticksInAir = 0;
        this.field_35140_d = false;
        this.shootingEntity = entityliving;
        this.doesArrowBelongToPlayer = entityliving instanceof EntityPlayer;
        this.setSize(0.5F, 0.5F);
        this.setLocationAndAngles(entityliving.posX, entityliving.posY + (double)entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
        this.posZ -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.0F);
        this.posY -= 0.10000000149011612D;
        this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.0F);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI));
        this.setColor1(color1);
        this.setColor2(color2);
        this.setColor3(color3);
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, speed * 1.5F);
    }

    protected void entityInit() {
        this.dataWatcher.addObject(13, new Integer(0));
        this.dataWatcher.addObject(14, new Integer(0));
        this.dataWatcher.addObject(15, new Integer(0));
    }

    public void setThrowableHeading(double d, double d1, double d2, float f)
    {
        float f2 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
        d /= (double)f2;
        d1 /= (double)f2;
        d2 /= (double)f2;
        d *= (double)f;
        d1 *= (double)f;
        d2 *= (double)f;
        this.motionX = d;
        this.motionY = d1;
        this.motionZ = d2;
        float f3 = MathHelper.sqrt_double(d * d + d2 * d2);
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(d, d2) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(d1, (double)f3) * 180.0D / Math.PI);
        this.ticksInGround = 0;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
     * posY, posZ, yaw, pitch
     */
    public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9)
    {
        this.setPosition(par1, par3, par5);
        this.setRotation(par7, par8);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Sets the velocity to the args. Args: x, y, z
     */
    public void setVelocity(double par1, double par3, double par5)
    {
        this.motionX = par1;
        this.motionY = par3;
        this.motionZ = par5;

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            float var7 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(par3, (double)var7) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch;
            this.prevRotationYaw = this.rotationYaw;
            this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            this.ticksInGround = 0;
        }
    }

    public String getParticle1()
    {
    	return getColor1() == 0 ? "smoke" : getColor1() == 1 ? "reddust" : getColor1() == 2 ? "happyVillager" : getColor1() == 3 ? "depthsuspend" : getColor1() == 4 ? "splash" : getColor1() == 5 ? "witchMagic" : getColor1() == 6 ? "magicCrit" : getColor1() == 7 ? "crit" : getColor1() == 8 ? "townaura" : getColor1() == 9 ? "snowballpoof" : getColor1() == 10 ? "slime" : getColor1() == 11 ? "flame" : getColor1() == 12 ? "dripWater" : getColor1() == 13 ? "portal" : getColor1() == 14 ? "dripLava" : "explode";
    }
    
    public String getParticle2()
    {
    	return getColor2() == 0  ? "smoke" : getColor2() == 1 ? "reddust" : getColor2() == 2 ? "happyVillager" : getColor2() == 3 ? "depthsuspend" : getColor2() == 4 ? "splash" : getColor2() == 5 ? "witchMagic" : getColor2() == 6 ? "magicCrit" : getColor2() == 7 ? "crit" : getColor2() == 8 ? "townaura" : getColor2() == 9 ? "snowballpoof" : getColor2() == 10 ? "slime" : getColor2() == 11 ? "flame" : getColor2() == 12 ? "dripWater" : getColor2() == 13 ? "portal" : getColor2() == 14 ? "dripLava" : "explode";
    }
    
    public String getParticle3()
    {
    	return getColor3() == 0  ? "smoke" : getColor3() == 1 ? "reddust" : getColor3() == 2 ? "happyVillager" : getColor3() == 3 ? "depthsuspend" : getColor3() == 4 ? "splash" : getColor3() == 5 ? "witchMagic" : getColor3() == 6 ? "magicCrit" : getColor3() == 7 ? "crit" : getColor3() == 8 ? "townaura" : getColor3() == 9 ? "snowballpoof" : getColor3() == 10 ? "slime" : getColor3() == 11 ? "flame" : getColor3() == 12 ? "dripWater" : getColor3() == 13 ? "portal" : getColor3() == 14 ? "dripLava" : "explode";
    } 
    
    public void onUpdate()
    {
        super.onUpdate();
        	  this.worldObj.spawnParticle(getParticle1(), this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
              this.worldObj.spawnParticle(getParticle2(), this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
              this.worldObj.spawnParticle(getParticle3(), this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);	 
         
        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            float var161 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, (double)var161) * 180.0D / Math.PI);
        }

        int var16 = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);

        if (var16 > 0)
        {
            Block.blocksList[var16].setBlockBoundsBasedOnState(this.worldObj, this.xTile, this.yTile, this.zTile);
            AxisAlignedBB var17 = Block.blocksList[var16].getCollisionBoundingBoxFromPool(this.worldObj, this.xTile, this.yTile, this.zTile);

            if (var17 != null && var17.isVecInside(Vec3.fakePool.getVecFromPool(this.posX, this.posY, this.posZ)))
            {
                this.inGround = true;
            }
        }

        if (this.arrowShake > 0)
        {
            --this.arrowShake;
        }
        
        if(this.ticksExisted > 300)
        {
        this.setDead();
        }

        if (this.inGround)
        {     
           // this.setDead();
        }
        else
        {
            ++this.ticksInAir;
            Vec3 var171 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
            Vec3 vec3d1 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks_do_do(var171, vec3d1, false, true);
            var171 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
            vec3d1 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (movingobjectposition != null)
            {
                vec3d1 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
            }

            Entity entity = null;
            List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double d = 0.0D;
            float f6;

            for (int f3 = 0; f3 < list.size(); ++f3)
            {
                Entity var18 = (Entity)list.get(f3);

                if (var18.canBeCollidedWith() && var18 != this.shootingEntity)
                {
                    f6 = 0.3F;
                    AxisAlignedBB var20 = var18.boundingBox.expand((double)f6, (double)f6, (double)f6);
                    MovingObjectPosition el = var20.calculateIntercept(var171, vec3d1);

                    if (el != null)
                    {
                        double d1 = var171.distanceTo(el.hitVec);

                        if (d1 < d || d == 0.0D)
                        {
                            entity = var18;
                            d = d1;
                        }
                    }
                }
            }

            if (entity != null)
            {
                movingobjectposition = new MovingObjectPosition(entity);
            }

            float var181;

            if (movingobjectposition != null)
            {
                if (movingobjectposition.entityHit != null)
                {
                    var181 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                    int var19 = MathHelper.ceiling_double_int((double)var181 * 2.0D);

                    if (this.field_35140_d)
                    {
                        var19 = var19 * 3 / 2 + 1;
                    }

                    if (movingobjectposition.entityHit.attackEntityFrom(TNTDamageSource.causeBulletDamage(this, this.shootingEntity), 4.0F))
                    {
                        this.setDead();
                    }
                }
                else
                {
                      this.setDead();
                }
            }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            var181 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

            for (this.rotationPitch = (float)(Math.atan2(this.motionY, (double)var181) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
            {
                ;
            }

            while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
            {
                this.prevRotationPitch += 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw < -180.0F)
            {
                this.prevRotationYaw -= 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
            {
                this.prevRotationYaw += 360.0F;
            }

            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
            float var201 = 1.0F;
            f6 = 0.05F;
            this.motionX *= (double)var201;
            this.motionY *= (double)var201;
            this.motionZ *= (double)var201;
            this.setPosition(this.posX, this.posY, this.posZ);
            this.doBlockCollisions();
        }
    }

    public int getColor1()
    {
        return this.dataWatcher.getWatchableObjectInt(13);
    }

    public void setColor1(int par1)
    {
        this.dataWatcher.updateObject(13, par1);

    }

    public int getColor2()
    {
        return this.dataWatcher.getWatchableObjectInt(14);
    }

    public void setColor2(int par1)
    {
        this.dataWatcher.updateObject(14, par1);

    }
    
    public int getColor3()
    {
        return this.dataWatcher.getWatchableObjectInt(15);
    }

    public void setColor3(int par1)
    {
        this.dataWatcher.updateObject(15, par1);

    }
    
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setShort("xTile", (short)this.xTile);
        nbttagcompound.setShort("yTile", (short)this.yTile);
        nbttagcompound.setShort("zTile", (short)this.zTile);
        nbttagcompound.setByte("inTile", (byte)this.inTile);
        nbttagcompound.setByte("inData", (byte)this.inData);
        nbttagcompound.setByte("shake", (byte)this.arrowShake);
        nbttagcompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
        nbttagcompound.setBoolean("player", this.doesArrowBelongToPlayer);
        nbttagcompound.setInteger("color1", (Integer)this.getColor1());
        nbttagcompound.setInteger("color2", (Integer)this.getColor2());
        nbttagcompound.setInteger("color3", (Integer)this.getColor3());
    }


    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        this.xTile = nbttagcompound.getShort("xTile");
        this.yTile = nbttagcompound.getShort("yTile");
        this.zTile = nbttagcompound.getShort("zTile");
        this.inTile = nbttagcompound.getByte("inTile") & 255;
        this.inData = nbttagcompound.getByte("inData") & 255;
        this.arrowShake = nbttagcompound.getByte("shake") & 255;
        this.inGround = nbttagcompound.getByte("inGround") == 1;
        this.doesArrowBelongToPlayer = nbttagcompound.getBoolean("player");
        this.setColor1(nbttagcompound.getInteger("color1"));
        this.setColor2(nbttagcompound.getInteger("color2"));
        this.setColor3(nbttagcompound.getInteger("color3"));
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }


}
