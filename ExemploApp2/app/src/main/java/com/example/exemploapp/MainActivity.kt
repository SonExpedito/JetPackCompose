package com.example.exemploapp

import android.content.res.Configuration
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.Surface
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import com.example.exemploapp.ui.theme.ExemploAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                ExemploAppTheme{
                  Surface(modifier = Modifier.fillMaxSize()) {
                      Conversation(SampleData.conversationSample)
                  }
                }
          }

    }

}


data class Message(val author: String, val body: String)




@Composable
fun MessageCard(msg: Message){
    //Adicionando espaçamento em torno da nossa mensagem
             Row(modifier = Modifier.padding(all = 8.dp)) {
                       Image(painter = painterResource(R.drawable.destruidorderaluca)
                            ,contentDescription = "Contact Profile picture",
                            modifier = Modifier

                                .size(40.dp) //Setando o tamanho da imagem para 40dp

                                .clip(CircleShape) // Deixando as bordas arrendondadas

                                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)

                )


      //Adicionando um Espaço Horizontal entre a imagem e a coluna
              Spacer(modifier = Modifier.width(8.dp))

                 var isExpanded by remember { mutableStateOf(false) }
                 val surfaceColor by animateColorAsState(
                     if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
                 )


              Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                      Text(
                        text = msg.author,
                        color = MaterialTheme.colors.secondaryVariant,
                        style = MaterialTheme.typography.subtitle2
                      )



                      Spacer(modifier = Modifier.height(4.dp)) //Adicionando um Espaço Vertical entre o author e a mensagem.

                      Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp,
                          color = surfaceColor,
                          // animateContentSize will change the Surface size gradually
                          modifier = Modifier.animateContentSize().padding(1.dp)
                      ) {
                        Text(
                            text = msg.body,
                            style= MaterialTheme.typography.body2,
                            modifier = Modifier.padding(all = 4.dp),
                            // If the message is expanded, we display all its content
                            // otherwise we only display the first line
                            maxLines = if (isExpanded) Int.MAX_VALUE else 1,

                        )

                     }

              }

  }
}

@Preview (name = "Light Mode") //Para visualização de funções sem parâmetro
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable //Composta
fun PreviewMessageCard(){

        ExemploAppTheme() {
                    Surface() {
                              MessageCard(
                                msg = Message("Diggo", "Macetarei o Raluca com minhas mãos")
                                )
                        }
                }
}


@Composable
    fun Conversation(messages: List<Message>){
            LazyColumn{
                items(messages) {
                    message -> MessageCard(message)
                }

            }

    }

@Preview
@Composable
fun PreviewConversation() {
    ExemploAppTheme() {
        Conversation(SampleData.conversationSample)
    }
}

object SampleData {
    // Sample conversation data
    val conversationSample = listOf (
        Message("Diggo","PUTS RALUCA"),
        Message("Diggo","PUTS RALUCA 2 "),
        Message(
            "Colleague",
            "List of Android versions:\n" +
                    "Android KitKat (API 19)\n" +
                    "Android Lollipop (API 21)\n" +
                    "Android Marshmallow (API 23)\n" +
                    "Android Nougat (API 24)\n" +
                    "Android Oreo (API 26)\n" +
                    "Android Pie (API 28)\n" +
                    "Android 10 (API 29)\n" +
                    "Android 11 (API 30)\n" +
                    "Android 12 (API 31)\n"
        )
    )
}