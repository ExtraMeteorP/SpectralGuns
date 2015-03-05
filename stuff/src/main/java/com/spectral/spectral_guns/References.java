package com.spectral.spectral_guns;

import com.spectral.spectral_guns.proxy.ProxyCommon;

public class References extends ProxyCommon
{
	public static final String MODID = "spectralguns";
	public static final String VERSION = "1.1";
	public static final String Client = "com.spectral.spectral_guns.proxy.ProxyClient";
	public static final String Server = "com.spectral.spectral_guns.proxy.ProxyServer";
	
	/*
	 * CHANGELOG:
	 * 0.2 - sigurd4:
	 * *changed loads of stuff with the components. cleared stuff up. now uses material enum and more cool stuff.
	 * +added fireball projectile.
	 * +added food projectile
	 * +added additional food class to make custom food. currently used for the goopy food mush ammo for the food gun.
	 * +added various tooltip stuff for maximum user friendliness.
	 * +added snowy hud(not quite working)
	 * *i probably changed a bunch of other stuff that i can't remember. not to self: do keep track of changes next time.
	 * 
	 * 0.2.1 - sigurd4
	 * *fixed some bugs and crashes
	 * *minor adjustments
	 * 
	 * 1.0 (public release) - sigurd4
	 * *fixed textures!!! :DDDDDD
	 * +added some crafting recipes
	 * *fixed random gun generation from creative tab
	 * *fixed the check for making sure the gun component combination is valid and that all required component types are used
	 * +added throwable shurikens (not yet possible to shoot with a gun yet, cause i'm lazy)
	 * *changes to the food entity rendering code
	 * +added some crafting items, mostly related to optics which will be used for lasers and scopes
	 * +added scopes and zooming ability
	 * *changed a lot of the item registration code to make it tidier and allow textures
	 * +added automatic trigger mechanisms
	 * +added rubies, ruby ore and ruby blocks. i need help with world gen at some point. lover of beards, i summon you!! ...or anyone else who knows how to do this stuff!
	 * *bugfixes
	 * 
	 * 1.1 - sigurd4
	 * +added lasers in different colors and intensities
	 * +added laser scopes
	 * +made it so that the crosshair is removed when the gun is used to make it more ideal to use the aim
	 * +added laser guns
	 * *some small changes
	 * +added handler for moving sounds and such
	 * +added stuff for custom particles
	 * *fixed block textures
	 * +localization
	 * *fixed all bugs i could find related to servers (stuff being out of synch and annoying errors in your log and such)
	 * *fixed more bugs
	 * 
	 * 1.1.1 - sigurd4
	 * *fixed some issues with the laser guns
	 * *fixed blocks rendering with double (more or less) size when held in hand in third person
	 * *
	 */
}