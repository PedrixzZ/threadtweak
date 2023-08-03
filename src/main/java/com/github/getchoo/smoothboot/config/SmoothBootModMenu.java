package com.github.getchoo.smoothboot.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class SmoothBootModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> new ConfigScreen(Text.of("SmoothBoot Config")) {
            @Override
            protected void init() {
                super.init();
                this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 2, 200, 20, Text.of("Open Config File"), (button) -> {
                    ConfigHandler.openConfigFile();
                }));
            }

            @Override
            public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
                this.renderBackground(matrices);
                super.render(matrices, mouseX, mouseY, delta);
            }
        };
    }
}
