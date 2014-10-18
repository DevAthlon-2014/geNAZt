package net.cubespace.devathlon14.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Fabian on 18.10.2014.
 */
public class ItemstackUtils {

    /**
     * Rename the given ItemStack
     * @param itemStack The ItemStack which should be renamed
     * @param name      The name which should be applied on the ItemStack
     * @return The renamed ItemStack
     */
    public static ItemStack renameItem( ItemStack itemStack, String name ) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName( name );
        itemStack.setItemMeta( itemMeta );
        return itemStack;
    }

}
