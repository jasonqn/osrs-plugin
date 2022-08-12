package com.gim;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.DrawManager;

import java.util.ArrayList;
import java.util.Hashtable;

import static net.runelite.http.api.RuneLiteAPI.GSON;

@Slf4j
@PluginDescriptor(name = "GIM Discord")
public class GimMain extends Plugin
{

	private Hashtable<String, Integer> currentLevels;
	private ArrayList<String> leveledSkills;
	private boolean shouldSendLevelMessage = false;


	@Inject
	private Client client;

	@Inject
	private GimConfig config;

	@Inject
	private DrawManager drawManager;

	@Provides
	GimConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(GimConfig.class);
	}


	@Override
	protected void startUp() throws Exception
	{
		currentLevels = new Hashtable<String, Integer>();
		leveledSkills = new ArrayList<String>();
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
		}
	}

}
