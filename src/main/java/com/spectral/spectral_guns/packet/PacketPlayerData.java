package com.spectral.spectral_guns.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.spectral.spectral_guns.entity.extended.EntityExtendedPlayer;

public class PacketPlayerData implements IMessage
{
	private EntityPlayer player;
	private ByteBuf buf;
	
	public PacketPlayerData()
	{
	}
	
	public PacketPlayerData(EntityPlayer player)
	{
		this.player = player;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.player = Minecraft.getMinecraft().thePlayer;
		this.buf = buf;
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		EntityExtendedPlayer.get(this.player).writeSpawnData(buf);
	}
	
	public static class Handler implements IMessageHandler<PacketPlayerData, IMessage>
	{
		public Handler()
		{
		}
		
		@Override
		public IMessage onMessage(PacketPlayerData message, MessageContext context)
		{
			EntityExtendedPlayer.get(message.player).readSpawnData(message.buf);
			return null;
		}
	}
}
