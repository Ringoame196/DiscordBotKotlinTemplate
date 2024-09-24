package org.example.managers

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import org.example.events.SlashCommandInteractionEvent
import java.io.File

class DiscordManager {
    private val tokenFile = File("./token.txt")

    fun makeTokenFile() {
        if (!tokenFile.exists()) {
            tokenFile.writeText("")
            println("tokenファイルを生成しました")
        }
    }

    fun acquisitionToken(): String {
        return tokenFile.readText()
    }

    fun bootBot(token:String, activity: Activity? = null): JDA? {
        if (token == "") {
            val message = "tokenが未設定のため 起動を停止します"
            println(message)
            return null
        }

        val jdaBuilder = JDABuilder.createDefault(token)

        if (activity != null) {
            jdaBuilder.setActivity(activity)
        }

        val jda = jdaBuilder.addEventListeners(SlashCommandInteractionEvent()).build() // JDAオブジェクトを取得

        return jda
    }

    fun addCommands(jda: JDA, commands:List<SlashCommandData>?) {
        if (commands == null) return

        jda.updateCommands().addCommands(commands).queue()
    }
}