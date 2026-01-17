package com.example.hytaletemplate;

import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.Message;

/**
 * Main plugin class for HytaleTemplate.
 * 
 * This is a template plugin for developing Hytale plugins.
 * 
 * @author Your Name
 * @version 1.0.0
 */
public class HytaleTemplatePlugin extends JavaPlugin {

	public static String version = "1.0.0";
	public static String name = "HytaleTemplate";
	
	/**
	 * Creates a formatted message with plugin prefix.
	 * 
	 * @param message The message content
	 * @return Formatted message
	 */
	public static Message createMessage(String message) {
		return Message.raw("[" + name + "] " + message);
	}
	
    /**
     * Constructor - Called when plugin is loaded.
     */
    public HytaleTemplatePlugin(JavaPluginInit init) {
        super(init);
        getLogger().atInfo().log("["+name+"]"+" Plugin loaded!");
    }
    
    @Override
    protected void setup() {
        getLogger().atInfo().log("["+name+"]"+" Setting up Plugin...");
        
        // Register your commands here
        // getCommandRegistry().registerCommand(new YourCommand());
        
        getLogger().atInfo().log("["+name+"]"+" Setup complete!");
    }
    
    @Override
    protected void start() {
        getLogger().atInfo().log("["+name+"]"+" Plugin started!");
        
        // Register your events here
        // this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, YourEvents::onPlayerReady);
    }
    
    @Override
    protected void shutdown() {
        getLogger().atInfo().log("["+name+"]"+" Plugin shutting down...");
        
        // Cleanup resources
        // Stop services
    }
}
