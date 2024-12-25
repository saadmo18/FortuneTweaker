package net.mo.supahotfortune;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SupaHotFortune implements ModInitializer {
	public static final String MOD_ID = "supahotfortune";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		BlockBreakEventHandler.register();

	}
}