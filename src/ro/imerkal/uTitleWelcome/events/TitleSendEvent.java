package ro.imerkal.uTitleWelcome.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TitleSendEvent extends Event implements Cancellable {
	
	private static final HandlerList handlers = new HandlerList();
	private final Player player;
	private String title;
	private String subtitle;
	private Integer fadeIn;
	private Integer stay;
	private Integer fadeOut;
	private boolean cancelled = false;
	
	public TitleSendEvent(Player player, String title, String subtitle, Integer fadeIn, Integer stay, Integer fadeOut) {
		this.player = player;
	    this.subtitle = subtitle;
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
	
	public String getTitle() {
		return title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public Integer getFadeIn() {
		return fadeIn;
	}

	public Integer getStay() {
		return stay;
	}

	public Integer getFadeOut() {
		return fadeOut;
	}
}