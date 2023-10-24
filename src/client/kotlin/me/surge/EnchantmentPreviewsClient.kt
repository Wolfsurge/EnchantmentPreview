package me.surge

import net.fabricmc.api.ClientModInitializer
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Formatting

object EnchantmentPreviewsClient : ClientModInitializer {

	override fun onInitializeClient() {}

	fun generateTooltip(stack: ItemStack, tooltip: MutableList<Text>) {
		val enchantments = Registries.ENCHANTMENT.filter { it.isAcceptableItem(stack) && EnchantmentHelper.isCompatible(EnchantmentHelper.get(stack).keys, it) }.sortedBy { Text.translatable(it.translationKey).string }

		if (enchantments.isNotEmpty()) {
			tooltip.add(Text.of(""))
			tooltip.add(MutableText.of(Text.of("Available Enchantments:").content).formatted(Formatting.GRAY))

			enchantments.forEach { enchantment ->
				val raw = Text
					.translatable(enchantment.translationKey)
					.formatted(
						if (enchantment.isCursed) Formatting.RED else Formatting.GRAY,
						Formatting.ITALIC
					)

				val tooltipText: Text = MutableText.of(
					Text.of(Formatting.GRAY.toString() + "  +").content
				).append(raw)

				tooltip.add(tooltipText)
			}
		}
	}

}