package com.github.getchoo.smoothboot.config;

import com.github.getchoo.smoothboot.SmoothBoot;
import net.minecraft.util.math.MathHelper;

public class SmoothBootConfig {
    private static final int MIN_PRIORITY = 1;
    private static final int MAX_PRIORITY = 10;
    private static final int MIN_THREAD_COUNT = 1;

    public ThreadCount threadCount = new ThreadCount();
    public ThreadPriority threadPriority = new ThreadPriority();

    public static class ThreadCount {
        public int bootstrap = 1;
        public int main = MathHelper.clamp(Runtime.getRuntime().availableProcessors() - 1, MIN_THREAD_COUNT,
                SmoothBoot.getMaxBackgroundThreads());
    }

    public static class ThreadPriority {
        public int game = 5;
        public int bootstrap = 1;
        public int main = 1;
        public int io = 1;
        public int integratedServer = 5;
    }

    public void validate() {
        threadCount.bootstrap = Math.max(threadCount.bootstrap, MIN_THREAD_COUNT);
        threadCount.main = Math.max(threadCount.main, MIN_THREAD_COUNT);

        threadPriority.game = MathHelper.clamp(threadPriority.game, MIN_PRIORITY, MAX_PRIORITY);
        threadPriority.integratedServer = MathHelper.clamp(threadPriority.integratedServer, MIN_PRIORITY, MAX_PRIORITY);
        threadPriority.bootstrap = MathHelper.clamp(threadPriority.bootstrap, MIN_PRIORITY, MAX_PRIORITY);
        threadPriority.main = MathHelper.clamp(threadPriority.main, MIN_PRIORITY, MAX_PRIORITY);
        threadPriority.io = MathHelper.clamp(threadPriority.io, MIN_PRIORITY, MAX_PRIORITY);
    }
}
