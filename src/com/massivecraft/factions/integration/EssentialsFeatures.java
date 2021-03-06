package com.massivecraft.factions.integration;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

import com.massivecraft.factions.P;

import com.earth2me.essentials.chat.EssentialsChat;
import com.earth2me.essentials.chat.IEssentialsChatListener;


public class EssentialsFeatures
{
	private static EssentialsChat essChat;

	public static void integrateChat(EssentialsChat instance)
	{
		essChat = instance;
		try
		{
			essChat.addEssentialsChatListener("Factions", new IEssentialsChatListener()
			{
				public boolean shouldHandleThisChat(PlayerChatEvent event)
				{
					return P.p.shouldLetFactionsHandleThisChat(event);
				}
				public String modifyMessage(PlayerChatEvent event, Player target, String message)
				{
					return message.replace("{FACTION}", P.p.getPlayerFactionTagRelation(event.getPlayer(), target)).replace("{FACTION_TITLE}", P.p.getPlayerTitle(event.getPlayer()));
				}
			});
			P.p.log("Found and will integrate chat with "+essChat.getDescription().getFullName());
		}
		catch (NoSuchMethodError ex)
		{
			essChat = null;
		}
	}

	public static void unhookChat()
	{
		if (essChat != null)
		{
			essChat.removeEssentialsChatListener("Factions");
		}
	}
}
