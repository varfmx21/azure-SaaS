package com.cloudforge.api.forgetask.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.generics.TelegramClient;

/**
 * Baseline only. ReportController always asks for a TelegramClient, but the real bean exists
 * only when telegram.bot.enabled=true. With the bot disabled we provide an inert client
 * (it never calls Telegram unless a report is sent to a chat).
 */
@Configuration
@Profile("azure")
@ConditionalOnProperty(prefix = "telegram.bot", name = "enabled", havingValue = "false", matchIfMissing = true)
public class AzureBaselineConfig {

    @Bean
    public TelegramClient telegramClient() {
        return new OkHttpTelegramClient("000000:baseline-placeholder");
    }
}
