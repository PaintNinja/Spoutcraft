package org.getspout.spout.gui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;
import org.getspout.spout.packet.PacketUtil;

public abstract class GenericWidget implements Widget{
	protected int upperRightX = 0;
	protected int upperRightY = 0;
	protected int width = 0;
	protected int height = 0;
	protected boolean visible = true;
	protected transient boolean dirty = true;
	protected transient Screen screen = null;
	protected RenderPriority priority = RenderPriority.Normal;
	protected UUID id = UUID.randomUUID();
	protected String tooltip = "";
	
	public GenericWidget() {

	}
	
	public int getNumBytes() {
		return 37 + PacketUtil.getNumBytes(tooltip);
	}
	
	public GenericWidget(int upperRightX, int upperRightY, int width, int height) {
		this.upperRightX = upperRightX;
		this.upperRightY = upperRightY;
		this.width = width;
		this.height = height;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		setX(input.readInt());
		setY(input.readInt());
		setWidth(input.readInt());
		setHeight(input.readInt());
		setVisible(input.readBoolean());
		setPriority(RenderPriority.getRenderPriorityFromId(input.readInt()));
		long msb = input.readLong();
		long lsb = input.readLong();
		this.id = new UUID(msb, lsb);
		setTooltip(PacketUtil.readString(input));
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeInt(getX());
		output.writeInt(getY());
		output.writeInt(getWidth());
		output.writeInt(getHeight());
		output.writeBoolean(isVisible());
		output.writeInt(priority.getId());
		output.writeLong(getId().getMostSignificantBits());
		output.writeLong(getId().getLeastSignificantBits());
		PacketUtil.writeString(output, getTooltip());
	}
	
	@Override
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
	
	@Override
	public boolean isDirty() {
		return dirty;
	}
	
	@Override
	public UUID getId() {
		return id;
	}
	@Override
	public Screen getScreen() {
		return screen;
	}
	
	@Override
	public Widget setScreen(Screen screen) {
		this.screen = screen;
		return this;
	}
	
	@Override
	public RenderPriority getPriority() {
		return priority;
	}
	
	@Override
	public Widget setPriority(RenderPriority priority) {
		this.priority = priority;
		return this;
	}
	
	@Override
	public int getWidth() {
		return width;
	}
	
	@Override
	public Widget setWidth(int width) {
		this.width = width;
		return this;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public Widget setHeight(int height) {
		this.height = height;
		return this;
	}

	@Override
	public int getX() {
		return (int)(upperRightX * (getScreen() != null ? (getScreen().getHeight() / 427f) : 1) );
	}

	@Override
	public int getY() {
		return (int)(upperRightY * (getScreen() != null ? (getScreen().getWidth() / 240f) : 1 ));
	}

	@Override
	public Widget setX(int pos) {
		this.upperRightX = pos;
		return this;
	}

	@Override
	public Widget setY(int pos) {
		this.upperRightY = pos;
		return this;
	}

	@Override
	public Widget shiftXPos(int modX) {
		setX(getX() + modX);
		return this;
	}
	
	@Override
	public Widget shiftYPos(int modY) {
		setY(getY() + modY);
		return this;
	}
	
	@Override
	public boolean isVisible() {
		return visible;
	}
	
	@Override
	public Widget setVisible(boolean enable) {
		visible = enable;
		return this;
	}
	
	@Override
	public int hashCode() {
		return getId().hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof Widget && other.hashCode() == hashCode();
	}
	
	@Override
	public void onTick() {

	}
	
	@Override
	public void setTooltip(String tooltip){
		this.tooltip = tooltip;
	}
	
	@Override
	public String getTooltip(){
		return tooltip;
	}
}
