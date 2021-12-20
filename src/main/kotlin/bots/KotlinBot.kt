package bots

import com.vdurmont.emoji.EmojiParser
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll
import org.telegram.telegrambots.meta.api.methods.send.SendAudio
import org.telegram.telegrambots.meta.api.methods.send.SendDocument
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendVideo

import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import java.io.File

class KotlinBot : TelegramLongPollingBot() {

    override fun getBotUsername(): String {
        //return bot username
        // If bot username is @HelloKotlinBot, it must return
        return "kotlinfabot"
    }

    override fun getBotToken(): String {
        // Return bot token from BotFather
        return "2138145157:AAF9AmicV8b38vIIyk65HgVFPTLphOf0jyE"
    }

    override fun onUpdateReceived(update: Update?) {
        // We check if the update has a message and the message has text
        val nameSender = update?.message?.from?.firstName
        val chatId = update?.message?.chatId.toString()
        val messageCommand = update?.message?.text
        val callBackData = update?.callbackQuery?.data

        try {
            if (messageCommand == "/start") {
                val welcome = EmojiParser.parseToUnicode(
                    """                 
                        *DÊ PLAY NO VIDEO, TEM SOM*
                        
                   FELIZ ANIVERSÁRIO $nameSender COISA MAI LINDA PERFEITA DO MUND, MINHA MUSA FITNESS :heart:
                    
                    *Escolhe um comando \-você pode apertar no nome do comando que também funciona*            
                        
                    /start \-  Página Inicial \- Mostra lista de comando novamente
                    /poll  \-  Descubra o seu presente de aniversário
                    /button \- Ainda não descobriu? Aperta aqui que eu te falo 
                    /video \-  Um vídeo especial para você :smile:  
                    /text \-   Teu texto :smile:  
                           
                """.trimIndent()
                )

                val sendDocument = SendDocument().apply {
                    this.chatId = chatId
                    caption = welcome
                    document = InputFile(File("C:\\Users\\SPACE\\OneDrive - SPACE NETWORK\\Área de Trabalho\\aniverbot\\aniverBot\\ebac-kotlin-telegram-bot-master\\ebac-kotlin-telegram-bot-master\\videoplayback.mp4"))
                    parseMode = "MarkdownV2"
                }

                             execute(sendDocument)
            }

            if (messageCommand == "/poll") {
                val sendPoll = SendPoll().apply {
                    this.chatId = chatId
                    question = "Devido o exposed do BOT anterior, foi necessário alterar o seu presente de aniversário"
                    options = listOf("Camiseta autografada da JESSICA DO ESCADÃO", " INGRESSO ROCK IN RIO PALCO JULLITE-Ganhe de brinde 1kg de cuzcuz", "CD Damares as melhores 2021", "Kit 3 calcinhas tamanho P - FICA BOLSONARO, BANDEIRA DO BRASIL, LULINDO")
                    type = "quiz"
                    correctOptionId = 3
                    isAnonymous = false
                }
                execute(sendPoll)
            }

            if (messageCommand == "/button") {
                val buttons = listOf(
                    listOf(
                        InlineKeyboardButton().apply {
                            text = "KUALÉ"  //NOME DO BOTAÃO PARA ESCOLHER
                            callbackData = "/matt"

                        },
                        InlineKeyboardButton().apply {
                            text = "KEROTO"
                            callbackData = "/david"

                        }
                    )
                )
                val options = InlineKeyboardMarkup().apply {
                    keyboard = buttons
                }

                val sendDocument = SendDocument().apply {

                    this.chatId = chatId
                    document = InputFile("https://tenor.com/bl6vG.gif")
                    caption = "Se prepara pro presente MARA que você vai ganhar... ESCOLHE"
                     replyMarkup = options
                }
                execute(sendDocument)
            }



            if (callBackData == "/matt"){  //primeira opção
                val callBackChatId = update?.callbackQuery?.message?.chatId.toString()

                val sendDocument = SendDocument().apply {
                    this.chatId = callBackChatId
                    caption = "VOCÊ ACABA DE GANHAR UMA SANDÁLIA PRÓPRIA PRO CULTO DE DOMINGO" //nome do loucuras de amor
                    document = InputFile("https://i.ibb.co/4Nv7nYC/SANDALIA-GOSPEL-GALOPADA-DE-JUDA.jpg")

                }
                execute(sendDocument)
            }

            if (callBackData == "/david"){
                val callBackChatId = update?.callbackQuery?.message?.chatId.toString()

                val sendVideo  = SendVideo ().apply {
                    this.chatId = callBackChatId
                    caption = "*ABRE O VIDEO, TEM SOM*" +
                            "" +
                            "FELIZ ANIVERSÁRIO MEU MOMO, EU TO CHEGANDO AÍ"
                    video = InputFile(File("C:\\Users\\SPACE\\OneDrive - SPACE NETWORK\\Área de Trabalho\\aniverbot\\aniverBot\\ebac-kotlin-telegram-bot-master\\ebac-kotlin-telegram-bot-master\\carrodoamor.mp4"))
                    parseMode = "MarkdownV2"
                }
                execute(sendVideo)
            }

             if (messageCommand == "/text"){

             val markdownV2text = """                 
            Lá em casa tem uma pedra que afuda. Lady Gaga é flopada, parabéns sua vagabunda
                 
             """.trimIndent()


                val sendMessage = SendMessage().apply {
                    this.chatId =   chatId
                    text = markdownV2text
                    parseMode = "MarkDownV2"


                }
                execute(sendMessage)
            }

        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }
}