package com.nmg.me.tileentity;

import com.nmg.me.Constants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nullable;

public class METileEntity extends TileEntityLockableLoot implements IInventory
{

	private final String ID;
	protected NonNullList<ItemStack> INVENTORY;
	protected ITextComponent customName;

	public METileEntity(TileEntityType type, String id, int inventorySize)
	{
		super(type);
		this.ID = id;
		this.INVENTORY = NonNullList.withSize(inventorySize, ItemStack.EMPTY);
	}

	@Override
	protected NonNullList<ItemStack> getItems()
	{
		return INVENTORY;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> itemsIn)
	{
		INVENTORY = itemsIn;
	}

	@Override
	public int getSizeInventory()
	{
		return INVENTORY.size();
	}

	@Override
	public boolean isEmpty()
	{
		for (ItemStack stack : INVENTORY)
		{
			if (!stack.isEmpty())
				return false;
		}

		return true;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public void read(NBTTagCompound compound)
	{
		super.read(compound);

		INVENTORY.clear();
		ItemStackHelper.loadAllItems(compound, INVENTORY);

		if (compound.hasKey("CustomName"))
		{
			this.customName = new TextComponentTranslation(compound.getString("CustomName"));
		}
	}

	@Override
	public NBTTagCompound write(NBTTagCompound compound)
	{
		super.write(compound);

		ItemStackHelper.saveAllItems(compound, INVENTORY);

		if (this.hasCustomName())
		{
			compound.setString("CustomName", this.customName.getString());
		}

		return compound;
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		this.read(pkt.getNbtCompound());
	}

	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		return new SPacketUpdateTileEntity(this.pos, 10, this.write(new NBTTagCompound()));
	}

	@Override
	public boolean hasCustomName()
	{
		return this.customName != null && !this.customName.getString().isEmpty();
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
	{
		return null;
	}

	@Override
	public String getGuiID()
	{
		return Constants.MODID + ":" + ID;
	}

	@Override
	public ITextComponent getDisplayName()
	{
		return this.hasCustomName() ? this.customName : this.getName();
	}

	@Override
	public void setCustomName(ITextComponent customName)
	{
		this.customName = customName;
	}

	@Override
	public ITextComponent getName()
	{
		return this.hasCustomName() ? this.customName : new TextComponentTranslation("container." + ID);
	}
}
