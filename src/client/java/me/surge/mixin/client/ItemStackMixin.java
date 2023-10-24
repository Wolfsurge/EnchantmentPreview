package me.surge.mixin.client;

import me.surge.EnchantmentPreviewsClient;
import me.surge.util.IThis;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemStack.class)
public class ItemStackMixin implements IThis<ItemStack> {

	@Inject(method = "getTooltip", at = @At("RETURN"))
	private void hookGetTooltip(PlayerEntity player, TooltipContext context, CallbackInfoReturnable<List<Text>> cir) {
		EnchantmentPreviewsClient.INSTANCE.generateTooltip(_this(), cir.getReturnValue());
	}

}