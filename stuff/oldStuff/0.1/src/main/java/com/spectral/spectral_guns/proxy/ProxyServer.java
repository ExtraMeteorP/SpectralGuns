package com.spectral.spectral_guns.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;

import com.spectral.spectral_guns.event_handler.HandlerClient;
import com.spectral.spectral_guns.event_handler.HandlerClientFML;
import com.spectral.spectral_guns.event_handler.HandlerServer;
import com.spectral.spectral_guns.event_handler.HandlerServerFML;

public class ProxyServer extends ProxyCommon
{
	@Override
	public void init()
	{
		super.init();
		
		MinecraftForge.EVENT_BUS.register(new HandlerServer());
		FMLCommonHandler.instance().bus().register(new HandlerServerFML());
	}
}