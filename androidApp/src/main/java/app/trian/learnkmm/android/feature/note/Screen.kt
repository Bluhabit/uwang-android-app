package app.trian.learnkmm.android.feature.note

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.RestoreFromTrash
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.trian.learnkmm.android.base.BaseMainApp
import app.trian.learnkmm.entity.NoteModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ScreenNote(
    modifier: Modifier = Modifier,
    notes: List<NoteModel> = listOf(),
    onSubmit: (String) -> Unit = {},
    onDelete: (String) -> Unit = {}
) {
    var noteName by remember {
        mutableStateOf("")
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = {
            item {
                Row(
                    modifier=Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(value = noteName, onValueChange = {
                        noteName = it
                    })
                    Button(onClick = { onSubmit(noteName) }) {
                        Text(text = "Proses")
                    }
                }
            }
            items(notes) {
                ListItem(
                    headlineText = {
                        Text(text = it.noteName)
                    },
                    supportingText = {
                        Text(text = it.noteDescription)
                    },
                    trailingContent = {
                        IconButton(onClick = { onDelete(it.noteId) }) {
                            Icon(
                                imageVector = Icons.Outlined.RestoreFromTrash,
                                contentDescription = ""
                            )
                        }
                    }
                )
            }
        })


}

@Preview
@Composable
fun PreviewScreenNote() {
    BaseMainApp {
        ScreenNote()
    }

}