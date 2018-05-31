package com.chattriggers.ctjs.minecraft.mixins;

import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityPlayer.class)
public interface MixinEntityPlayer {
    @Accessor("displayname")
    String getDisplayName();

    @Accessor("displayname")
    void setDisplayName(String displayname);
}