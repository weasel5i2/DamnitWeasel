package net.weasel.Damnit;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Weasel extends JavaPlugin
{
	@Override
	public void onDisable() 
	{
		System.out.println("[" + getDescription().getName() + "] " 
				+ getDescription().getName() + " v" + getDescription().getVersion() 
				+ " enabled."  );
	}

	@Override
	public void onEnable() 
	{
		System.out.println("[" + getDescription().getName() + "] " 
		+ getDescription().getName() + " v" + getDescription().getVersion() 
		+ " enabled."  );

	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		String pCommand = command.getName().toLowerCase();
	
		if( sender instanceof Player )
		{
			if( pCommand.equals( "fixgrass" ) )
			{
				Player player = (Player)sender;
				doGrassFix(player);
				return true;
			}
			
			if( pCommand.equals( "fixvines" ) )
			{
				Player player = (Player)sender;
				doVinesFix(player);
				return true;
			}
			
			if( pCommand.equals( "fixplants" ) )
			{
				Player player = (Player)sender;
				doPlantsFix(player);
				return true;
			}
			
			if( pCommand.equals( "flower" ) )
			{
				Player player = (Player)sender;
				growSinglePlant(player, Material.YELLOW_FLOWER.getId() );
				return true;
			}
			
			if( pCommand.equals( "rose" ) )
			{
				Player player = (Player)sender;
				growSinglePlant(player, Material.RED_ROSE.getId() );
				return true;
			}
			
			if( pCommand.equals( "rmushroom" ) )
			{
				Player player = (Player)sender;
				growSinglePlant(player, Material.RED_MUSHROOM.getId() );
				return true;
			}

			if( pCommand.equals( "bmushroom" ) )
			{
				Player player = (Player)sender;
				growSinglePlant(player, Material.BROWN_MUSHROOM.getId() );
				return true;
			}
		}
		
		return false;
	}
	
	public void doGrassFix( Player player )
	{
		Block targetBlock = null;
		double pX = player.getLocation().getX();
		double pY = player.getLocation().getY();
		double pZ = player.getLocation().getZ();
		
		player.sendMessage( "Fixing grass for 64 blocks around you.." );
		
		for( double X = (pX-64); X <= (pX+64); X++ )
		{
			for( double Y = (pY-4); Y <= (pY+4); Y++ )
			{
				for( double Z = (pZ-64); Z <= (pZ+64); Z++ )
				{
					targetBlock = player.getWorld().getBlockAt((int)X,(int)Y,(int)Z);
					
					if( targetBlock != null )
					{
						if( targetBlock.getTypeId() == 2 )
						{
							targetBlock.setData( (byte)0 );
						}
					}
				}
			}
		}
		player.sendMessage( "Done." );
	}
	
	public void doVinesFix( Player player )
	{
		Block targetBlock = null;
		
		double pX = player.getLocation().getX();
		double pY = player.getLocation().getY();
		double pZ = player.getLocation().getZ();
		
		player.sendMessage( "Fixing vines for 64 blocks around you.." );
		
		for( double X = (pX-64); X <= (pX+64); X++ )
		{
			for( double Y = (pY-4); Y <= (pY+4); Y++ )
			{
				for( double Z = (pZ-64); Z <= (pZ+64); Z++ )
				{
					targetBlock = player.getWorld().getBlockAt((int)X,(int)Y,(int)Z);
					
					if( targetBlock != null )
					{
						if( targetBlock.getTypeId() == 83 )
						{
							if( targetBlock.getData() == 15 )
								targetBlock.setTypeId( 0 );
						}
					}
				}
			}
		}
		player.sendMessage( "Done." );
	}
	
	public void doPlantsFix( Player player )
	{
		Block targetBlock = null;
		double pX = player.getLocation().getX();
		double pY = player.getLocation().getY();
		double pZ = player.getLocation().getZ();
		
		player.sendMessage( "Fixing plants for 64 blocks around you.." );
		
		for( double X = (pX-64); X <= (pX+64); X++ )
		{
			for( double Y = (pY-4); Y <= (pY+4); Y++ )
			{
				for( double Z = (pZ-64); Z <= (pZ+64); Z++ )
				{
					targetBlock = player.getWorld().getBlockAt((int)X,(int)Y,(int)Z);
					
					if( targetBlock != null )
					{
						if( targetBlock.getType() == Material.RED_ROSE 
						|| targetBlock.getType() == Material.YELLOW_FLOWER 
						|| targetBlock.getType() == Material.RED_MUSHROOM
						|| targetBlock.getType() == Material.BROWN_MUSHROOM )
						{
							targetBlock.setTypeId( 0 );
						}
					}
				}
			}
		}
		player.sendMessage( "Done." );
	}
	
	public void growSinglePlant( Player player, Integer plantType )
	{
		Block targetBlock = player.getTargetBlock( null, 20 );
		Block blockAbove = null;
		
		blockAbove = targetBlock.getRelative(BlockFace.UP); 
			
		if( blockAbove.getTypeId() == 0 )
		{
			String pTypeStr = "";
			
			if( plantType == Material.YELLOW_FLOWER.getId() )
				pTypeStr = "Yellow flower";
			else if( plantType == Material.RED_ROSE.getId() )
				pTypeStr = "Red rose";
			else if( plantType == Material.RED_MUSHROOM.getId() )
				pTypeStr = "Red mushroom";
			else if( plantType == Material.BROWN_MUSHROOM.getId() )
				pTypeStr = "Brown mushroom";
								
			blockAbove.setTypeId( plantType );
			player.sendMessage( pTypeStr + " spawned." );
		}
		else
			player.sendMessage( "You cannot put a plant there." );
	}
}
