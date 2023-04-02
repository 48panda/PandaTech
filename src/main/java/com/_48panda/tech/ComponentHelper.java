package com._48panda.tech;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

import java.util.List;

public class ComponentHelper {
    public static Component getComponentFromAddDouble(double toConvert, Component nonzero, boolean isGood) {
        String str;
        boolean doGreen;
        if (toConvert > 0) {
            str = String.format("+%.2f", toConvert);
            doGreen = isGood;
        } else if (toConvert < 0) {
            str = String.format("%.2f", toConvert);
            doGreen = !isGood;
        } else {
            return TextComponent.EMPTY;
        }
        return new TextComponent(str).withStyle(doGreen?ChatFormatting.GREEN:ChatFormatting.RED).append(nonzero);
    }
    public static void addToListIfNotEmpty(List<Component> list, Component toadd) {
        if (!toadd.equals(TextComponent.EMPTY)) {
            list.add(toadd);
        }
    }

    public static Component getComponentFromMulDouble(double toConvert, Component nonzero, boolean isGood) {
        String str;
        boolean doGreen;
        if (toConvert > 1) {
            str = String.format("x%.2f", toConvert);
            doGreen = isGood;
        } else if (toConvert < 1) {
            str = String.format("x%.2f", toConvert);
            doGreen = !isGood;
        } else {
            return TextComponent.EMPTY;
        }
        return new TextComponent(str).withStyle(doGreen?ChatFormatting.GREEN:ChatFormatting.RED).append(nonzero);
    }
}
