package markov

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import java.io.File

@Serializable
data class TGMessage(val id: Int, val from_id: Int, val body: String?)

class R

@UnstableDefault
fun main() {
    val file = File(R::class.java.classLoader.getResource("sh_messages_tg.json").file)
    val data = Json.nonstrict.parse(TGMessage.serializer().list, file.readText())
    runBlocking {
        data.filter { it.body != null && it.body.isNotEmpty() }.forEach {
            markovChain3.processText(it.body!!)
            markovChain5.processText(it.body)
        }
    }

}