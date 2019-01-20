package ro.imerkal.uTitleWelcome.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ActionbarTitleSendEvent extends Event implements Cancellable {
	
	private static final HandlerList handlers = new HandlerList();
	private final Player player;
	private String text;
	private boolean cancelled = false;
	
	public ActionbarTitleSendEvent(Player player, String text) {
		this.player = player;
	    this.text = text;
	}
	
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public Player getPlayer() {
		return player;
	}

	public String getText() {
		return text;
	}
}