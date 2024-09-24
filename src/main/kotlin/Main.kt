package org.example

import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.interactions.commands.build.Commands
import org.example.events.SlashCommandInteractionEvent
import org.example.managers.DiscordManager

fun main() {
    val discordManager = DiscordManager()
    discordManager.makeTokenFile()

    val token = discordManager.acquisitionToken()
    val activity = Activity.playing("KotlinBot")
    val jdaBuilder = discordManager.bootBot(token = token, activity = activity) ?: return

    val jda = jdaBuilder.addEventListeners(SlashCommandInteractionEvent()).build() // JDAオブジェクトを取得

    val commands = listOf(
        Commands.slash("test", "テストコマンド"),
        Commands.slash("stop", "BOTを停止する")
        // Commands.slash("コマンド名", "コマンド説明"),
    )
    discordManager.addCommands(jda, commands)
}