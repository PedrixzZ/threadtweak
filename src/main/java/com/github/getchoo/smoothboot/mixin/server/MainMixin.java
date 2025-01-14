package com.github.getchoo.smoothboot.mixin.server;

import com.github.getchoo.smoothboot.SmoothBoot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.Main;

@Mixin(Main.class)
public class MainMixin {
    private static boolean initialized = false;

    @Inject(method = "main", at = @At("HEAD"), remap = false)
    private static void onMain(CallbackInfo ci) {
        if (!initialized) {
            SmoothBoot.regConfig();
            initialized = true;
        }

        Thread.currentThread().setPriority(SmoothBoot.config.threadPriority.game);
        SmoothBoot.LOGGER.debug("Initialized server game thread");
    }
}
