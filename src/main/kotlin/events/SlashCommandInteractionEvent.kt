package org.example.events

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class SlashCommandInteractionEvent : ListenerAdapter() {
    override fun onSlashCommandInteraction(e: SlashCommandInteractionEvent) {
        val unknownMessage = "不明なコマンドです"
        when (e.name) {
            "test" -> testCommand(e)
            "stop" -> stopCommand(e)
            // "追加コマンド" -> 追加コマンドの処理関数
            else -> e.reply(unknownMessage).queue()
        }
    }

    private fun testCommand(e: SlashCommandInteractionEvent) {
        val message = "テスト"
        e.reply(message).queue()
    }

    private fun stopCommand(e: SlashCommandInteractionEvent) {
        val member = e.member ?: return

        if (member.isOwner) {
            val message = "シャットダウンします"
            e.reply(message).queue()
            val jda = e.jda
            jda.shutdown()
        } else {
            val message = "権限がありません"
            e.reply(message).queue()
        }
    }
}