package me.Mammothskier.Giants.events;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;

public class EntityMoveEvent  extends EntityEvent implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private boolean cancel = false;
	private Location from;
	private Location to;
	
	public EntityMoveEvent (final Entity entity, final Location from, final Location to) {
		super(entity);
		this.from = from;
		this.to = to;
	}
	
	public boolean isCancelled() {
		return cancel;
	}
	
	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}
	
	public Location getFrom() {
		return from;
	}
	
	public void setFrom(Location from) {
		this.from = from;
	}
	
	public Location getTo() {
		return to;
	}
	
	public void setTo(Location to) {
		this.to = to;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
